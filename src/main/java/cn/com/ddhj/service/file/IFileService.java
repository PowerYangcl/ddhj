package cn.com.ddhj.service.file;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.com.ddhj.result.file.FileResult;

public interface IFileService {

	/**
	 * 手机端上传用户头像
	 * 
	 * @param data
	 * @return
	 */
	FileResult uploadUserHeader(HttpServletRequest request);

	/**
	 * 
	 * 方法: uploadProductImg <br>
	 * 描述: 上传微信商城-商品图片上传 <br>
	 * 作者: zhy<br>
	 * 时间: 2017年8月31日 上午9:39:57
	 * 
	 * @param request
	 * @return
	 */
	List<FileResult> uploadProductImg(HttpServletRequest request);
}
