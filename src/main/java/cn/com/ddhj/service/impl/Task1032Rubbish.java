package cn.com.ddhj.service.impl;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TRubbishRecycling;

public class Task1032Rubbish implements Callable<EnvInfo> {

	private TRubbishRecyclingMapper mapper;
	
	private TChemicalPlantMapper chemicalMapper; 
	
	private String city;
	
	private String position;
	
	
	public EnvInfo call() throws Exception {
		EnvInfo r = new EnvInfo();
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		List<TRubbishRecycling> list = this.getMapper().findListByCity(this.getCity());
		String msg = "5Km以内";
		TreeMap<Integer , String> map = new TreeMap<Integer , String>();
		for(TRubbishRecycling e : list){
			Integer d = this.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			map.put(d , "垃圾站");
		}
		List<TChemicalPlant> clist = this.getChemicalMapper().findListByCity(this.getCity());
		for(TChemicalPlant e : clist){
			Integer d = this.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			map.put(d , "化工厂"); 
		}
		
		
		Integer distance = map.firstKey();
		if(distance > 5000){
			msg = "5Km以外";
		}else if(4000< distance &&distance < 5000){
			msg = "4Km以外";
		}else if(3000< distance &&distance < 4000){
			msg = "3Km以外";
		}else if(2000< distance &&distance < 3000){
			msg = "2Km以外";
		}else if (distance < 2000){
			msg = "1Km以外";
		}
		
		r.setName("污染源"); 
		r.setLevel(map.get(map.firstKey()));
		r.setMemo(msg); 
		return r;
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













