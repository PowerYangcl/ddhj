package cn.com.ddhj.service.file;

import javax.servlet.http.HttpServletRequest;

public interface IFileService {

	/**
	 * 手机端上传用户头像
	 * 
	 * @param data
	 * @return
	 */
	String uploadUserHeader(HttpServletRequest request);
}
