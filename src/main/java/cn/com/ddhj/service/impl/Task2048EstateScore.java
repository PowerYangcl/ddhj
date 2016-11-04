package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.DoctorScoreUtil;
import cn.com.ddhj.util.PureNetUtil;

public class Task2048EstateScore implements Callable<TLandedProperty> {

	private TLandedProperty entity;
	private String hourAqi;
	private String dayAqi;
	private String city;
	private String position;
	private List<TAreaNoise> noiseList;
	private List<TWaterEnviroment> waterEnvList;
	private List<TRubbishRecycling> rubbishList;
	private List<TChemicalPlant> chemicalList;



	public Task2048EstateScore(TLandedProperty entity, String hourAqi, String dayAqi, String city, String position,
			List<TAreaNoise> noiseList, List<TWaterEnviroment> waterEnvList, List<TRubbishRecycling> rubbishList,
			List<TChemicalPlant> chemicalList) {
		this.entity = entity;
		this.hourAqi = hourAqi;
		this.dayAqi = dayAqi;
		this.city = city;
		this.position = position;
		this.noiseList = noiseList;
		this.waterEnvList = waterEnvList;
		this.rubbishList = rubbishList;
		this.chemicalList = chemicalList;
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

			
			String z1 = "1";
			String z2 = "0";
			String nlevel = this.noise(position, noiseList).split("@")[1]; 
			if(nlevel.equals("0类")){
				z1 = "0"; 
				z2 = "0"; 
			}else if(nlevel.equals("I类")){
				z1 = "1"; 
				z2 = "0"; 
			}else if(nlevel.equals("II类")){
				z1 = "2"; 
				z2 = "1"; 
			}else if(nlevel.equals("III类")){
				z1 = "3"; 
				z2 = "2"; 
			}else if(nlevel.equals("IV类")){
				z1 = "4"; 
				z2 = "3"; 
			}
			Map<String , String> waterEnv = this.waterEnv(position, city, waterEnvList);
			score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , waterEnv.get("s") , z1 , z2);
			score = String.valueOf( (Double.valueOf(score) + Double.valueOf(this.rubbish(position, rubbishList, chemicalList).get("score"))) );  // 污染源，针对最后的综合评分 距离500米 得出分-30
			if(score.length() > 5){
				System.out.println("exception score = " + score); 
				score = score.substring(0, 5);
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		entity.setScore(Double.valueOf(score));
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setUpdateTime(sdf.format(new Date()));
		
		return entity; 
	}
	
	
	private String noise(String position , List<TAreaNoise> list) {
		if(StringUtils.isBlank(position) || list == null || list.size() == 0){
			return "I类@45/55";
		}
		String[] arr = position.split(",");
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
	
	private Map<String , String> waterEnv(String position , String city , List<TWaterEnviroment> list) {
		Map<String , String> water = new HashMap<String , String>(); 
		if(StringUtils.isAnyBlank(position , city) || list == null || list.size() == 0){
			 water.put("level", "II");
			 water.put("s", "2");
			 return water;
		}
		
		Map<String , String> map = new HashMap<String , String>();
		map.put("北京", "北京");
		map.put("上海", "上海");
		map.put("天津", "天津");
		map.put("广州", "广州");  
		map.put("深圳", "广州"); 		// 广州和深圳的水源地取同一个
		String city_ = map.get(this.city);
		if(StringUtils.isBlank(city_)){   // 不在我定义的城市中则给默认值
			water.put("level", "II");
			water.put("s", "2");
		}else{
			 if(list != null && list.size() != 0){
				 String[] arr = position.split(",");
				 Double lat = Double.valueOf(arr[0]);
				 Double lng = Double.valueOf(arr[1]); 
				 TreeMap<Integer , TWaterEnviroment> map_ = new TreeMap<Integer , TWaterEnviroment>();
				 for(TWaterEnviroment e : list){
					 Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
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
	
	private Map<String , String> rubbish(String position , List<TRubbishRecycling> rubbishList , List<TChemicalPlant> chemicalList) {
		Map<String , String> r = new HashMap<String , String>();
		if(StringUtils.isBlank(position)){
			r.put("score", "0");
			return r;
		}
		if((rubbishList == null || rubbishList.size() == 0) && (chemicalList == null || chemicalList.size() == 0) ){
			r.put("score", "0");
			return r;
		}
		
		
		String[] arr = position.split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		String msg = "5Km以内";
		TreeMap<Integer , String> map = new TreeMap<Integer , String>();
		for(TRubbishRecycling e : rubbishList){
			Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			map.put(d , "垃圾站");
		}
		for(TChemicalPlant e : chemicalList){
			Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			map.put(d , "化工厂"); 
		}
		
		String score = "0";  
		Integer distance = map.firstKey();
		if(distance > 5000){
			msg = "5Km以外";
		}else if(4000< distance &&distance < 5000){
			msg = "4Km以外";
		}else if(3000< distance &&distance < 4000){
			msg = "3Km以外";
		}else if(2000< distance &&distance < 3000){
			msg = "2Km以外";
			score = "-5";
		}else if (1000 < distance && distance < 2000){
			msg = "1Km以外";
			score = "-20";
		}else if(distance < 500){
			score = "-30"; 
		}
		
//		r.put("level", map.get(map.firstKey()));
//		r.put("memo", msg);
		/*
		 * 污染源，针对最后的综合评分 
		 * 距离500米 得出分-30
		 * 距离1公里 得出分-20 
		 * 距离3公里 得出分-5 
		 */
		r.put("score", score);
		return r;
	}
}
 















