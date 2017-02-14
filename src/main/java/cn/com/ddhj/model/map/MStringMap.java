package cn.com.ddhj.model.map;

import java.util.Map;

/**
 * 
 * 类: MStringMap <br>
 * 描述: 定义mapmodel模型 <br>
 * 作者: zhy<br>
 * 时间: 2016年9月21日 下午10:43:53
 */
public class MStringMap extends MObjMap<String, String> implements Map<String, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1113400831514224701L;

	public MStringMap() {

	}

	public MStringMap(Map<String, Object> mData) {

		for (String sKyey : mData.keySet()) {
			this.put(sKyey, mData.get(sKyey).toString());
		}
	}

	/**
	 * 转换主键到数组
	 * 
	 * @return
	 */
	public String[] convertKeysToStrings() {
		return this.getKeys().toArray(new String[] {});
	}

}
