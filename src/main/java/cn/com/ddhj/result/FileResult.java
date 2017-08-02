package cn.com.ddhj.result;

import java.util.List;

import cn.com.ddhj.base.BaseResult;

public class FileResult extends BaseResult {

	private List<String> filePaths;

	public List<String> getFilePaths() {
		return filePaths;
	}

	public void setFilePaths(List<String> filePaths) {
		this.filePaths = filePaths;
	}

}
