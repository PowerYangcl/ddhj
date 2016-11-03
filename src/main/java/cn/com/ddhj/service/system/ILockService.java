package cn.com.ddhj.service.system;


public interface ILockService{

	/**
	 * 
	 * 方法: addLock <br>
	 * 描述: 添加锁 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:49:30
	 * 
	 * @param keycode
	 * @param timeoutSecond
	 * @param uuid
	 * @return
	 */
	String addLock(String keycode, Integer timeoutSecond, String uuid);

	/**
	 * 
	 * 方法: unLock <br>
	 * 描述: 删除锁 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:49:38
	 * 
	 * @param uuid
	 * @return
	 */
	String unLock(String uuid);
	
	/**
	 * 
	 * 方法: clearLock <br>
	 * 描述: 清空系统锁 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月31日 上午9:06:47
	 * @return
	 */
	int clearLock();
}
