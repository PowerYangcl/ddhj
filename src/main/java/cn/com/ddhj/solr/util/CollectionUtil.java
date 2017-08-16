package cn.com.ddhj.solr.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CollectionUtil {

	/**
	 * 判断一个集合是否为空
	 * @param col
	 * @return
	 */
	public static <T> boolean isEmpty(Collection<T> col) {
		if(col == null || col.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断Map是否为空
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isEmpty(Map<K, V> map) {
		if(map == null || map.isEmpty()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 去除list中的重复数据
	 * @param <T>
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeRepeat(List<T> list) {
		if(isEmpty(list)) return list;
		
		List<T> result = new ArrayList<T>();
		for(T e: list) {
			if(! result.contains(e)) {
				result.add(e);
			}
		}
		
		return result;
	}
	
	/**
	 * 将list中的数据作为map的key保存到map中
	 * @param map
	 * @param list
	 * @param value
	 */
	public static <K, V> void putListToMap(Map<K, V> map, List<K> list, V value) {
		if(map == null || isEmpty(list)) {
			return;
		}
		
		for(K key : list) {
			if(map.get(key) == null) {// 主要是用于去除重复的数据
				map.put(key, value);
			}
		}
	}
	
	/**
	 * 合并两个map中的数据，如果key相同的话，则value相加
	 * 结果保存在map1中，所以map1不能为null
	 * @param map1
	 * @param map2
	 */
	public static <K> void merge(Map<K, Float> map1, Map<K, Float> map2) {
		if(map1 == null || isEmpty(map2)) {
			return;
		}
		
		for(Entry<K, Float> entry : map2.entrySet()) {
			K key = entry.getKey();
			Float value = entry.getValue();
			Float value2 = map1.get(key);
			
			if(value2 != null) {
				value += value2;
			}			
			map1.put(key, value);
		}
	}
	
	/**
	 * 按照map的value进行排序
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @param reverse: true降序，false升序
	 * @return
	 */
	public static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> sortMapByValue(Map<K, V> map, final boolean reverse) {
		if(isEmpty(map)) {
			return null;
		}
		
		List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<K, V>>(){
			
				public int compare(Map.Entry<K, V> entry1, Map.Entry<K, V> entry2) {
					int result = entry1.getValue().compareTo(entry2.getValue());
					if(reverse) {
						result *= -1;
					}
					
					return result;
				}
		});
		
		return list;
	}
	
	/**
	 * 将List中的所有元素按照顺序转换为String
	 * @param list
	 * @param retSplitter：元素之间的分隔符
	 * @return
	 */
	public static <T> String listToString(List<T> list, String retSplitter) {
		if(isEmpty(list)) return null;
		
		if(StringUtil.isEmpty(retSplitter)) {
			retSplitter = " ";// 默认的分隔符是空格
		}
		
		StringBuffer result = new StringBuffer();
		result.append(list.get(0).toString());
		
		for(int i=1;i < list.size();i ++) {
			result.append(retSplitter).append(list.get(i).toString());
		}
		
		return result.toString();
	}
	
	/**
	 * 将一串字符串转换为List
	 * @param str：一长串字符串，字符串之间以retSplitter分隔开
	 * @param retSplitter：字符串之间的分隔符
	 * @return
	 */
	public static List<String> stringToList(String str, String retSplitter) {
		if(StringUtil.isEmpty(str)) return null;
		
		String[] array = str.split(retSplitter);
		List<String> result = new ArrayList<String>();
		for(String s: array) {
			result.add(s);
		}
		
		return result;
	}
	
}
