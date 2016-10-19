package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.model.TAreaNoise;

public class Task1032Noise implements Callable<String> {
	
	private ITAreaNoiseMapper noiseMapper;
	private String city;
	private String position;
	

	public String call() throws Exception {
		
		List<TAreaNoise> list = this.getNoiseMapper().selectByArea(this.getCity());
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		
		Double nlat = null; // 北纬
		Double slat = null ; // 南纬
		Double elng = null; // 东经
		Double wlng = null; // 西经
		List<TAreaNoise> areaList = new ArrayList<>();
		for(TAreaNoise e : list){
			if(e.getFlag() == 2){
				if(e.getName().equals("WN")){ // 坐标西北点
					nlat = e.getLat();
					wlng = e.getLng();
				}else if(e.getName().equals("ES")){// 坐标东南点
					elng = e.getLng();
					slat = e.getLat();
				}
			}else{
				areaList.add(e);
			}
		}
		String  level = "";
		for(TAreaNoise e : areaList){
			if(this.getDistance(lat, lng, e.getLat() , e.getLng()) < 2000){
				if(e.getLevel().equals("III类")){
					level = e.getLevel() + "@55/65";
				}else if(e.getLevel().equals("0类")){
					level = e.getLevel() + "@40/50";
				}
			}
		}
		if(StringUtils.isBlank(level)){
			if((slat<lat && lat < nlat) &&(wlng < lng && lng < elng)){ // 五环里
				level = "II类@50/60";
			}else{ 	// 北京：五环外 |上海：外环外 |广州：外环外|天津：外环外|深圳：关外全部划为I类标准
				level = "I类@45/55";
			}
		}
		
		return level;
	}
	
	
	/**
	 * @descriptions 根据两个位置的经纬度，来计算两地的距离（单位为米）
	 *
	 * @param lat1  用户纬度
	 * @param lng1 用户经度
	 * @param lat2 商家纬度
	 * @param lng2 商家经度
	 * @return
	 * @date 2016年10月7日 下午10:25:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
    public Integer getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
    	double earthRadius = 6378.137; // 地球半径 
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * earthRadius;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
        
        return Integer.valueOf(distanceStr) * 1000;
    }
    
    private double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
	
	
	
	
	


	public ITAreaNoiseMapper getNoiseMapper() {
		return noiseMapper;
	}
	public void setNoiseMapper(ITAreaNoiseMapper noiseMapper) {
		this.noiseMapper = noiseMapper;
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
