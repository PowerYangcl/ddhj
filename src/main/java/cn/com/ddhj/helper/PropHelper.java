package cn.com.ddhj.helper;

import cn.com.ddhj.model.map.MStringMap;
import cn.com.ddhj.util.prop.PropLoad;

/**
 * 
 * 类: PropHelper <br>
 * 描述: properties文件读取帮助类 <br>
 * 作者: zhy<br>
 * 时间: 2017年2月13日 下午2:41:53
 */
public class PropHelper {

	/**
	 * 
	 * 方法: getValue <br>
	 * 描述: 根据key值查询value <br>
	 * 作者: zhy<br>
	 * 时间: 2017年2月13日 下午2:42:06
	 * 
	 * @param key
	 * @return
	 */
	public static String getValue(String key) {
		MStringMap map = PropLoad.instance().getData();
		return map.get(key);
	}
}
