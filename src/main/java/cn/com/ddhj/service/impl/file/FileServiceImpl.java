package cn.com.ddhj.service.impl.file;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.helper.PropHelper;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.result.file.FileResult;
import cn.com.ddhj.service.file.IFileService;
import cn.com.ddhj.util.DateUtil;

@Service
public class FileServiceImpl extends BaseClass implements IFileService {

	@Override
	public FileResult uploadUserHeader(HttpServletRequest request) {
		String time = DateUtil.sysDateToStrForFile();
		String path = PropHelper.getValue("user_header_path") + time + "/";
		String visitPath = PropHelper.getValue("user_header_visit_path") + time + "/";
		return upload(request, path, visitPath);
	}

	private FileResult upload(HttpServletRequest request, String path, String visitPath) {
		FileResult result = new FileResult();
		List<FileItem> fileItems = getFileFromRequest(request);
		if (fileItems != null && fileItems.size() != 0) {
			FileItem item = fileItems.get(0);
			result = saveFile(item.getName(), item.get(), path, visitPath);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("未发现要上传的文件，请核实");
		}
		return result;
	}

	/**
	 * @descriptions 获取request的上传文件
	 *
	 * @param request
	 * @date 2017年8月2日 下午1:46:04
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	private List<FileItem> getFileFromRequest(HttpServletRequest request) {
		List<FileItem> items = null; // 得到所有的文件
		String contentType = request.getContentType();
		if (StringUtils.contains(contentType, "multipart/form-data")) { // 如果文件是以二进制方式上传的
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				items = upload.parseRequest(request);
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		return items;
	}

	/**
	 * @descriptions 持久化一个文件到硬盘
	 *
	 * @param fileName
	 *            要保存文件的名称
	 * @param file
	 *            该文件的二进制流
	 * @date 2017年8月2日 下午2:39:57
	 * @author Yangcl
	 * @version 1.0.0.1
	 */
	private FileResult saveFile(String fileName, byte[] file, String path, String visitPath) {
		FileResult result = new FileResult();
		if (StringUtils.isBlank(fileName)) {
			result.setResultCode(-1);
			result.setResultMessage("文件名称不得为空");
			return result;
		}
		if (fileName.split("\\.").length < 2) {
			result.setResultCode(-1);
			result.setResultMessage("文件名称错误，缺少后缀");
			return result;
		}
		String postfix = StringUtils.substringAfterLast(fileName, ".").toLowerCase(); // 取得文件后缀名
		try {
			String name = WebHelper.getInstance().genUuid();
			FileUtils.forceMkdir(new File(path));
			File out = new File(path + name + "." + postfix);
			FileCopyUtils.copy(file, out); // 复制文件到服务器
			result.setResultCode(1);
			result.setResultMessage("文件上传完成");
			result.setTitle(name);
			result.setPath(path);
			result.setOriginal(fileName);
			result.setType(postfix);
			result.setPath(path);
			String url = PropHelper.getValue("user_header_url") + visitPath + name + "." + postfix;
			result.setUrl(url);
		} catch (IOException e) {
			e.printStackTrace();
			result.setResultCode(-1);
			result.setResultMessage("文件持久化异常");
			return result;
		}
		return result;
	}
}
