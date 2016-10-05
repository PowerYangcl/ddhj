package cn.com.ddhj.base;

import org.apache.commons.logging.LogFactory;

/**
 * 
 * 类: BaseLog <br>
 * 描述: 日志基础类 <br>
 * 作者: zhy<br>
 * 时间: 2016年9月2日 下午3:38:40
 */
public class BaseLog extends BaseClass {

	/**
	 * 
	 * 方法: logInfo <br>
	 * 描述: info类型日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:39:41
	 * 
	 * @param clazz
	 *            类对象
	 * @param info
	 *            日志内容
	 */
	public void logInfo(Class<?> clazz, String info) {
		LogFactory.getLog(clazz).info(info);
	}

	/**
	 * 
	 * 方法: logInfo <br>
	 * 描述: info类型日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:39:44
	 * 
	 * @param info
	 *            日志内容
	 */
	public void logInfo(String info) {
		LogFactory.getLog(this.getClass()).info(info);
	}

	/**
	 * 
	 * 方法: logDebug <br>
	 * 描述: debug类型日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:40:39
	 * 
	 * @param clazz
	 *            类对象
	 * @param debug
	 *            日志内容
	 */
	public void logDebug(Class<?> clazz, String debug) {
		LogFactory.getLog(clazz).debug(debug);
	}

	/**
	 * 
	 * 方法: logDebug <br>
	 * 描述: debug类型日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:40:50
	 * 
	 * @param debug
	 *            日志内容
	 */
	public void logDebug(String debug) {
		LogFactory.getLog(this.getClass()).debug(debug);
	}

	/**
	 * 
	 * 方法: logWarn <br>
	 * 描述: 警告日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:42:49
	 * 
	 * @param clazz
	 *            类对象
	 * @param warn
	 *            日志内容
	 */
	public void logWarn(Class<?> clazz, String warn) {
		LogFactory.getLog(clazz).warn(warn);
	}

	/**
	 * 
	 * 方法: logWarn <br>
	 * 描述: 警告日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:43:22
	 * 
	 * @param warn
	 *            日志内容
	 */
	public void logWarn(String warn) {
		LogFactory.getLog(this.getClass()).warn(warn);
	}

	/**
	 * 
	 * 方法: logError <br>
	 * 描述: 错误日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:43:59
	 * 
	 * @param clazz
	 * @param error
	 */
	public void logError(Class<?> clazz, String error) {
		LogFactory.getLog(clazz).error(error);
	}

	/**
	 * 
	 * 方法: logError <br>
	 * 描述: 错误日志输出 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月2日 下午3:44:28
	 * 
	 * @param error
	 */
	public void logError(String error) {
		LogFactory.getLog(this.getClass()).error(error);
	}
}
