package cn.com.ddhj.model.map;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * 类: MObjMap <br>
 * 描述: 定义map obj模型 <br>
 * 作者: zhy<br>
 * 时间: 2016年9月21日 下午10:44:16
 * 
 * @param <K>
 * @param <V>
 */
public class MObjMap<K, V> extends ConcurrentHashMap<K, V> {

	private static final long serialVersionUID = 2251639910081587304L;

	/**
	 * 
	 * 方法: getKeys <br>
	 * 描述: 获取所有key <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:44:28
	 * 
	 * @return
	 */
	public List<K> getKeys() {

		List<K> lReturnsKs = new ArrayList<K>();
		Enumeration<K> eKey = this.keys();

		while (eKey.hasMoreElements()) {
			lReturnsKs.add(eKey.nextElement());
		}
		return lReturnsKs;

	}

	/**
	 * 
	 * 方法: initKeyValues <br>
	 * 描述: 该方法适合批量添加 注意V必须能转换成K时才能调用 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:44:46
	 * 
	 * @param vInputs
	 */
	@SuppressWarnings("unchecked")
	public void initKeyValues(V... vInputs) {

		for (int i = 0, j = vInputs.length; i < j; i = i + 2) {
			this.put((K) vInputs[i], vInputs[i + 1]);
		}

	}
}