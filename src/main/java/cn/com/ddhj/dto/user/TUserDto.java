package cn.com.ddhj.dto.user;

import cn.com.ddhj.dto.BaseDto;

public class TUserDto extends BaseDto {

	private String uuid;
	/**
	 * 用户编码
	 */
	private String userCode;

	/**
	 * 用户手机号
	 */
	private String phone;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 用户昵称
	 */
	private String nickName;

	/**
	 * 用户头像
	 */
	private String headPic;

	/**
	 * 用户电子邮箱
	 */
	private String eMail;

	/**
	 * 设备识别码
	 */
	private String equipmentCode;

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getUserCode() {
		return userCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadPic() {
		return headPic;
	}

	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

}
