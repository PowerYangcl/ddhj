package cn.com.ddhj.helper;

import java.util.HashMap;
import java.util.Map;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.ISysWebcodeService;

public class WebHelper extends BaseClass {

	@Inject
	private ISysWebcodeService codeService;

	private static WebHelper self;

	public static WebHelper getInstance() {
		if (self == null) {
			synchronized (WebHelper.class) {
				if (self == null)
					self = new WebHelper();
			}
		}
		return self;
	}

	/**
	 * 
	 * 方法: getUniqueCode <br>
	 * 描述: 获取唯一编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月2日 上午10:00:17
	 * 
	 * @param codeStart
	 * @return
	 */
	public String getUniqueCode(String codeStart) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", codeStart);
		return codeService.callUniqueCode(map);
	}
}
