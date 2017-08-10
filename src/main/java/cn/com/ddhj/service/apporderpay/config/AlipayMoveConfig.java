package cn.com.ddhj.service.apporderpay.config;

import java.util.HashMap;
import java.util.Map;

import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.helper.PropHelper;

public class AlipayMoveConfig extends BaseClass {
	public  Map<String,String> alipayConfig(String sellerCode) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("partner", PropHelper.getValue("partner_Huijiayou"));
		map.put("seller_email", PropHelper.getValue("seller_email_Huijiayou"));
		map.put("key", PropHelper.getValue("ordercenter.key_move"));
		map.put("public_key", PropHelper.getValue("ordercenter.public_key"));
		return map;
	}
}


