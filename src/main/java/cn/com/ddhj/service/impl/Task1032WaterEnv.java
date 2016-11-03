package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.model.TWaterEnviroment;


/**
 * @description:  但启动一个线程去调用水质量信息 
 * 
 * @author Yangcl
 * @date 2016年11月3日 下午5:01:08 
 * @version 1.0.0
 */
public class Task1032WaterEnv implements Callable<Map<String , String>>{

	private TWaterEnviromentMapper waterEnvMapper;  // 水质量信息
	private String city;
	private String position;
	
	public Map<String , String> call() throws Exception {
		Map<String , String> map = new HashMap<String , String>();
		map.put("北京", "北京");
		map.put("上海", "上海");
		map.put("天津", "天津");
		map.put("广州", "广州");  
		map.put("深圳", "广州"); 		// 广州和深圳的水源地取同一个
		Map<String , String> water = new HashMap<String , String>(); 
		String city_ = map.get(this.city);
		if(StringUtils.isBlank(city_)){   // 不在我定义的城市中则给默认值
			water.put("level", "II");
			water.put("s", "2");
		}else{
			 List<TWaterEnviroment> list = this.waterEnvMapper.selectByCity(city_);
			 if(list != null && list.size() != 0){
				 String[] arr = this.getPosition().split(",");
				 Double lat = Double.valueOf(arr[0]);
				 Double lng = Double.valueOf(arr[1]); 
				 TreeMap<Integer , TWaterEnviroment> map_ = new TreeMap<Integer , TWaterEnviroment>();
				 for(TWaterEnviroment e : list){
					 Integer d = this.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
					 map_.put(d, e);
				 }
				 TWaterEnviroment w = map_.get(map_.firstKey());
				 String level = w.getOxygenquality();
				 water.put("level", level);
				 if(level.equals("Ⅰ")){
					 water.put("level", "I");
					 water.put("s", "1");
				 }else if(level.equals("-") || level.equals("Ⅱ")){
					 water.put("level", "II");
					 water.put("s", "2");
				 }else if(StringUtils.isBlank(level)){
					 water.put("level", "II");
					 water.put("s", "2");
				 }else if(level.equals("Ⅲ")){
					 water.put("level", "III");
					 water.put("s", "3");
				 }else if(level.equals("Ⅳ")){
					 water.put("level", "IV");
					 water.put("s", "4");
				 }else {
					 water.put("level", "V");
					 water.put("s", "5");
				 }
				 
			 }else{
				 water.put("level", "II");
				 water.put("s", "2");
			 }
		}
		
		return water;
	}


	public Integer getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
    	double earthRadius = 6378.137; // 地球半径 
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * earthRadius;
        distance = Math.round(distance * 10000) / 10;   
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
        
        return Integer.valueOf(distanceStr);
    }
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }


	public TWaterEnviromentMapper getWaterEnvMapper() {
		return waterEnvMapper;
	}
	public void setWaterEnvMapper(TWaterEnviromentMapper waterEnvMapper) {
		this.waterEnvMapper = waterEnvMapper;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
}























