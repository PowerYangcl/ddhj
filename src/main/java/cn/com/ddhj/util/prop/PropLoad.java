package cn.com.ddhj.util.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

import cn.com.ddhj.helper.IoHelper;
import cn.com.ddhj.model.map.MStringMap;

/**
 * 
 * 类: LoadProp <br>
 * 描述: 读取properties配置文件 <br>
 * 作者: zhy<br>
 * 时间: 2016年9月22日 下午10:37:56
 */
public class PropLoad {

	private static PropLoad self;

	public static PropLoad instance() {
		if (self == null) {
			synchronized (PropLoad.class) {
				self = new PropLoad();
			}
		}
		return self;
	}

	/**
	 * 
	 * 方法: loadProperties <br>
	 * 描述: 读取配置文件 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年9月21日 下午10:30:38
	 * 
	 * @param path
	 * @return
	 */
	private MStringMap loadProperties(String path) {
		MStringMap map = new MStringMap();
		FileInputStream fis = null;
		try {
			/**
			 * 遍历所有properties后缀的文件
			 */
			Collection<File> files = FileUtils.listFiles((new File(path)), new String[] { "properties" }, true);
			for (File file : files) {
				PropertiesConfiguration config = new PropertiesConfiguration();
				fis = FileUtils.openInputStream(file);
				/**
				 * 读取文件
				 */
				config.load(fis, "UTF-8");
				Iterator<String> keys = config.getKeys();
				// 定义命名空间
				String nameSpace = StringUtils.defaultString(config.getString("@this$namespace"), "");
				/**
				 * 遍历config的所有key
				 */
				while (keys.hasNext()) {
					String key = keys.next();
					// 定义是否强制覆盖
					boolean overWriteFlag = false;
					String value = StringUtils.join(config.getStringArray(key), ",");
					if (StringUtils.isNotEmpty(nameSpace)) {
						if (!StringUtils.startsWith(key, "@") && !StringUtils.startsWith(key, nameSpace)) {
							key = nameSpace + "." + key;
						}
					}
					// 进行特殊判断模式
					if (StringUtils.startsWith(key, "@")) {
						String sTarget = StringUtils.substringBetween(key, "@", "$");
						// 覆写配置
						if (sTarget.equals("override")) {
							// override
							overWriteFlag = true;
						}
						// 本配置指向
						else if (sTarget.equals("this")) {
						}
						key = StringUtils.substringAfter(key, "$");
					}
					if (overWriteFlag || !map.containsKey(key)) {
						map.put(key, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}

	/**
	 * 
	 * 方法: getData <br>
	 * 描述: 根据类型查询配置文件内容信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月24日 上午10:50:57
	 * 
	 * @param type
	 * @return
	 */
	public MStringMap getData() {
		MStringMap map = new MStringMap();
		try {
			System.out.println("开始读取properties配置文件");
			Resource[] resources = IoHelper.upResources("classpath*:properties/dev.properties");
			for (int i = 0; i < resources.length; i++) {
				map.putAll(loadProperties(resources[i].getFile().getParent()));
			}
			System.out.println("读取properties配置文件结束");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("读取properties配置文件报错,错误原因:" + e.getMessage());
		}
		return map;
	}
}
