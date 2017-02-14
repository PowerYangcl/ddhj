package cn.com.ddhj.model.map;

import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class MDataMap extends MObjMap<String, String> implements Map<String, String> {

	private static final long serialVersionUID = 5055977734242728639L;

	/**
	 * 
	 * 方法: convertKeysToStrings <br>
	 * 描述: 转换主键到数组 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:45:38
	 * 
	 * @return
	 */
	public String[] convertKeysToStrings() {
		return this.getKeys().toArray(new String[] {});
	}

	/**
	 * 
	 * 方法: getSubMap <br>
	 * 描述: 获取子数据 根据传入参数取得以该参数开头的集合 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:45:52
	 * 
	 * @param sStartString
	 * @return
	 */
	public MDataMap getSubMap(String sStartString) {
		MDataMap mReturn = new MDataMap();
		for (String sKey : this.getKeys()) {
			if (StringUtils.startsWith(sKey, sStartString)) {
				mReturn.put(StringUtils.substringAfter(sKey, sStartString), this.get(sKey));
			}
		}
		return mReturn;
	}

	/**
	 * 
	 * 方法: toStringArray <br>
	 * 描述: 转换为字符串数组 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:46:52
	 * 
	 * @return
	 */
	public String[] toStringArray() {
		ArrayList<String> aList = new ArrayList<String>();
		for (String sKey : this.getKeys()) {
			aList.add(sKey);
			aList.add(this.get(sKey));
		}
		return aList.toArray(new String[] {});
	}

	public MDataMap() {

	}

	/**
	 * 
	 * 标题: 构造器 <br>
	 * 描述: TODO <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:48:18
	 * @param sInputs
	 */
	public MDataMap(String... sInputs) {
		initKeyValues(sInputs);
	}

	/**
	 * 
	 * 标题: 构造器 <br>
	 * 描述: 将map对象转换为mdatamap对象 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:47:56
	 * 
	 * @param mInput
	 */
	public MDataMap(Map<String, Object> mInput) {
		for (String s : mInput.keySet()) {
			if (mInput.get(s) != null) {
				put(s, mInput.get(s).toString());
			} else {
				put(s, "");
			}
		}
	}

	/**
	 * 
	 * 方法: getValue <br>
	 * 描述: 根据key读取value值 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:47:33
	 * 
	 * @param sKey
	 * @return
	 */
	public String getValue(String sKey) {
		return getValue(sKey, "");
	}

	/**
	 * 
	 * 方法: getValue <br>
	 * 描述: 根据key读取value值 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:47:18
	 * 
	 * @param sKey
	 * @param sDefault
	 * @return
	 */
	public String getValue(String sKey, String sDefault) {
		String sReturn = sDefault;
		if (this.containsKey(sKey)) {
			sReturn = this.get(sKey);
		}
		return sReturn;
	}

}
