package cn.com.ddhj.model.user;

import cn.com.ddhj.model.BaseModel;

/**
 * 
 * 类: TUserStep <br>
 * 描述: 用户计步表 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月14日 下午4:22:04
 */
public class TUserStep extends BaseModel {

	private String equipmentCode;

	private Integer step;

	private String createDate;

	private Integer isBinding;

	private String userCode;

	public Integer getStep() {
		return step;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getEquipmentCode() {
		return equipmentCode;
	}

	public void setEquipmentCode(String equipmentCode) {
		this.equipmentCode = equipmentCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(Integer isBinding) {
		this.isBinding = isBinding;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

}