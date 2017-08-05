package cn.com.ddhj.dto.store;

import cn.com.ddhj.model.TUserAddress;

public class TUserAddressUpdateDto extends TUserAddress{
	private String addressID;

	public String getAddressID() {
		return addressID;
	}
	public void setAddressID(String addressID) {
		this.addressID = addressID;
	}
	
}
