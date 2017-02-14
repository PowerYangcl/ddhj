package cn.com.ddhj.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class IoHelper {
	/**
	 * 
	 * 方法: createDir <br>
	 * 描述: 目录地址 创建目录<br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月24日 上午8:14:05
	 * 
	 * @param sDir
	 */
	public static void createDir(String sDir) {
		try {
			FileUtils.forceMkdir(new File(sDir));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 方法: upResources <br>
	 * 描述: 根据名称获取资源 名称支持正则表达式 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月24日 上午8:14:22
	 * 
	 * @param sResourceName
	 * @return
	 * @throws IOException
	 */
	public static Resource[] upResources(String sResourceName) throws IOException {

		Resource[] resources = null;

		PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

		resources = patternResolver.getResources(sResourceName);

		return resources;
	}

	/**
	 * 
	 * 方法: copyResources <br>
	 * 描述: 复制资源 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月24日 上午8:14:33
	 * 
	 * @param sFromClass
	 * @param sToPath
	 * @param sKeyName
	 */
	public void copyResources(String sFromClass, String sToPath, String sKeyName) {
		try {
			Resource[] resources = upResources(sFromClass);

			for (Resource r : resources) {

				String sUrlString = StringUtils.substringAfter(r.getURI().toString(), sKeyName);

				InputStream inStream = r.getInputStream(); // 读入原文件

				new File(sToPath + sUrlString).getParentFile().mkdirs();
				FileOutputStream fs = new FileOutputStream(sToPath + sUrlString);

				IOUtils.copy(inStream, fs);
				fs.flush();
				fs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
