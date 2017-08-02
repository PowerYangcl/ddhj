package cn.com.ddhj.result.tuser;

import java.util.List;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.model.TUserAddress;

public class UseAddressResult extends BaseResult {
	private List<TUserAddress> addressList;

	public List<TUserAddress> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<TUserAddress> addressList) {
		this.addressList = addressList;
	}
}
