package cn.com.ddhj.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.util.CommonUtil;

public class Task1032Rubbish implements Callable<Map<String , String>> {

	private TRubbishRecyclingMapper mapper;
	
	private TChemicalPlantMapper chemicalMapper; 
	
	private String city;
	
	private String position;
	
	
	public Map<String , String> call() throws Exception {
		Map<String , String> r = new HashMap<String , String>();
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		List<TRubbishRecycling> list = this.getMapper().findListByCity(this.getCity());
		String msg = "5Km以内";
		TreeMap<Integer , String> map = new TreeMap<Integer , String>();
		for(TRubbishRecycling e : list){
			Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			map.put(d , "垃圾站");
		}
		List<TChemicalPlant> clist = this.getChemicalMapper().findListByCity(this.getCity());
		for(TChemicalPlant e : clist){
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
		
		r.put("level", map.get(map.firstKey()));
		r.put("memo", msg);
		/*
		 * 污染源，针对最后的综合评分 
		 * 距离500米 得出分-30
		 * 距离1公里 得出分-20 
		 * 距离3公里 得出分-5 
		 */
		r.put("score", score);
		return r;
	}
	

	public TRubbishRecyclingMapper getMapper() {
		return mapper;
	}
	public void setMapper(TRubbishRecyclingMapper mapper) {
		this.mapper = mapper;
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

	public TChemicalPlantMapper getChemicalMapper() {
		return chemicalMapper;
	}
	public void setChemicalMapper(TChemicalPlantMapper chemicalMapper) {
		this.chemicalMapper = chemicalMapper;
	}
	
}













