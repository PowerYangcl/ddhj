package cn.com.ddhj.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.base.BaseClass;
import cn.com.ddhj.service.ISysWebcodeService;
import cn.com.ddhj.service.system.ILockService;

public class WebHelper extends BaseClass {

	@Inject
	private ISysWebcodeService codeService;
	@Inject
	private ILockService lockService;

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

	/**
	 * 
	 * 方法: genUuid <br>
	 * 描述: 获取uuid <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:45:41
	 * 
	 * @return
	 */
	public String genUuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 锁定某些编号 防止并发处理 <br/>
	 * 返回锁定的唯一编号 如果返回的是非空表示锁定成功 在业务逻辑执行完成后调用unlock解锁<br/>
	 * 返回如果是空需要自行处理失败的消息
	 * 
	 * @param timeOutSeconds
	 * @param keys
	 * @return 返回非空表示锁定成功 否则表示锁定失败
	 */
	public String addLock(int timeOutSeconds, String... keys) {
		String sReturn = "";
		String sUid = genUuid();
		boolean bFlagLocked = true;

		for (String sKey : keys) {
			if (bFlagLocked) {
				try {
					String outFlag = lockService.addLock(sKey, timeOutSeconds, sUid);
					if (!outFlag.equals("1")) {
						bFlagLocked = false;
					}
				} catch (Exception e) {
					e.printStackTrace();
					bFlagLocked = false;
				}
			}
		}

		if (bFlagLocked) {
			sReturn = sUid;
		}
		return sReturn;
	}

	/**
	 * 解锁
	 * 
	 * @param uuid
	 *            要解锁的uuid
	 * @return
	 */
	public String unLock(String uuid) {
		try {
			String outFlag = lockService.unLock(uuid);
			if (outFlag.equals("1"))
				return uuid;
			else
				return "";
		} catch (Exception ex) {
			return "";
		}
	}
}
