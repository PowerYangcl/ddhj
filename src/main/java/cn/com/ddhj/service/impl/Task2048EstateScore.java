package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.util.PureNetUtil;

public class Task2048EstateScore implements Callable<TLandedProperty> {

	private TLandedProperty entity;
	private String hourAqi;
	private String dayAqi;
	
	
	
	public Task2048EstateScore(TLandedProperty entity, String hourAqi, String dayAqi) {
		this.entity = entity;
		this.hourAqi = hourAqi;
		this.dayAqi = dayAqi;
	}

	public TLandedProperty call() throws Exception {
		Thread.currentThread().setName(this.entity.getCity() + "|" + this.entity.getTitle() + "|三级线程已经启动 - 执行中...."); 
		String name = Thread.currentThread().getName();
		String score = "70"; 
		try {
			String greeningRate = "1";  // 如下条件不满足则用默认值
			String volumeRate = "0.4";	   // 如下条件不满足则用默认值
			if(StringUtils.isNotBlank(entity.getGreeningRate()) && StringUtils.isNotBlank(entity.getGreeningRate().split("%")[0]) ){
				if(Double.valueOf(entity.getGreeningRate().split("%")[0])/100 < 0.5){   // 潜在的异常点
					greeningRate = "0.5"; //教授接口返回HTTP Status 500 - 绿化率指数l只能是0.5或1|真坑爹
				}
			}
			if (entity.getVolumeRate() != null) {
				volumeRate = entity.getVolumeRate();
				if (volumeRate.length() > 2) {
					volumeRate = volumeRate.substring(0, 2);
					if (StringUtils.isNumeric(volumeRate)) {
						volumeRate = Double.valueOf(volumeRate) + "";
						if (Double.valueOf(volumeRate) > 9) {
							volumeRate = 8.9 + "";
						}
					}else{
						System.out.println("错误的容积率数据 | 非数字：" + entity.getId());
						volumeRate = "0.4";
					}

				}else{
					System.out.println("错误的容积率数据 | 空：" + entity.getId());
					volumeRate = "0.4";
				}
			}
			
			long start = System.currentTimeMillis(); 
			score = this.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate);
			long end = System.currentTimeMillis();
			System.out.println(name + " | 教授接口耗时：" + +(end - start) + " 毫秒");
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		entity.setScore(Double.valueOf(score));
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setUpdateTime(sdf.format(new Date()));
		
		return entity; 
	}

	/**
	 * @descriptions 获取教授数学模型综合评分|噪音和水质暂时默认为2
	 *
	 * @param a hourAQI
	 * @param b dayAQI   @教授的接口文档有问题，暂时放一个参数
	 * @param c  l  生态状况:绿化率指数 0.5或1  【地产检索接口->"greeningRate":"50%"】
	 * @param d  j 生态状况:容积率指数  0~9之间 【地产检索接口->"volumeRate":"0.46"】
	 * @return
	 * @date 2016年10月4日 下午10:18:29
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String getDoctorScore(String a ,String b ,String c , String d){
		String url = "http://123.56.169.49:8338/environment/servlet/environmentZHInterface";
		JSONObject obj = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("hourAQI", a);		 
		param.put("dayAQI", b);	 
		param.put("s", "2");						 
		param.put("z1", "2");							 
		param.put("z2", "2");							 
		param.put("l", c);	
		param.put("j", d);	
		param.put("t", "2");
		
		String result = PureNetUtil.get(url, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result); 
			if(obj.getString("flag").equals("true")){
				return obj.getString("message");
			}
		}
		return "0";
	}
}
 















