package cn.com.ddhj.service.file;

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
}
