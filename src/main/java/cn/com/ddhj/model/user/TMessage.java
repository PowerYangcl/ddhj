package cn.com.ddhj.model.user;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TMessage <br>
 * 描述: 消息表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月12日 上午10:43:34
 */
public class TMessage extends BaseModel {

	private String code;

	private String content;

	private String typeName;

	private String typeCode;

	private Integer isRead;

	private String receivedUser;

	private String outerCode;

	public String getOuterCode() {
		return outerCode;
	}

	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

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