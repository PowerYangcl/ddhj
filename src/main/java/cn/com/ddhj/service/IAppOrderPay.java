package cn.com.ddhj.service;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.apppay.TPayInfoDto;
import cn.com.ddhj.model.apppay.TPayInfo;

public interface IAppOrderPay extends IBaseService<TPayInfo, TPayInfoDto> {
	BaseResult doPay(TPayInfoDto dto, String userToken, String remoteIp);
	
	BaseResult doPay(TPayInfoDto dto, String userToken);
}
