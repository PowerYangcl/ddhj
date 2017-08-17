package cn.com.ddhj.solr.util;

import java.util.ResourceBundle;


/**
 * 资源文件管理类 <br/>
 * 描述 : 初始化所有的配置文件中的数据<br/>
 * 创建时间 : 2015-08-15 09:25:31 <br/>
 * 文件版本 : V1.0 <br/>
 * 修改历史 : V1.0
 */
public class ConfigUtil {

	 private static ResourceBundle prb = null;
	 private static boolean loaded = false;
	
	 private ConfigUtil() {
	 }

	 /**
	  * 加载资源文件
	  */
	 public final static void loadRes() {
	     prb = ResourceBundle.getBundle("com.qhsy.solr.properties.solrconfig");
	 }

	 /**
	  * 加载国际化资源文件
	  */
	 public static String get(String key) {
		  if (!loaded) {
		   loadRes();
		   loaded = true;
		  }
		  String result = null;
		  if (prb != null)
		   result = prb.getString(key);
		  if (result == null)
		   result = "";
		  try {
		   return new String(result.trim().getBytes("utf-8"), "utf-8");
		  } catch (Exception e) {
		   return "";
		  }
	 }

	 /**
	  * 获取资源文件key=value.
	  */
	 public ResourceBundle getResourceBundle() {
	  return prb;
	 }
	 
}
