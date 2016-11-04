package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.StringUtils;

import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.util.CommonUtil;

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
			}else{ //  if(e.getFlag() == 1 || e.getFlag() == 3)
				areaList.add(e);
			}
		}
		
		TreeMap<Integer , String> map = new TreeMap<Integer , String>();
		String  level = "I类@45/55";
		for(TAreaNoise e : areaList){
			Integer distance = CommonUtil.getMeterDistance(lat, lng, e.getLat() , e.getLng());
			if(distance < 2000){
				if(e.getFlag() == 3){ //e.getLevel().equals("III类")  
					level = e.getLevel() + "@55/65";
				}else if(e.getFlag() == 1){ // e.getLevel().equals("0类")
					level = e.getLevel() + "@40/50";
				}else if(e.getFlag() == 5){ // IV类 距离候车站地点2km以内的，4类 
					level = e.getLevel() + "@55/70"; 
				}
			}else if(e.getFlag() == 4 && distance < 5000){  // 机场5km以内 4类
				level = e.getLevel() + "@55/70"; 
			}
			map.put(distance , level);
		}
		if(map.size() > 0){
			return map.get(map.firstKey());
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
