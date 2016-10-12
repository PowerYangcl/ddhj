package cn.com.ddhj.dto.user;

import cn.com.ddhj.dto.BaseDto;

public class TMessageDto extends BaseDto {

	private Integer isRead;
	private String receivedUser;

	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public String getReceivedUser() {
		return receivedUser;
	}

	public void setReceivedUser(String receivedUser) {
		this.receivedUser = receivedUser;
	}

}
