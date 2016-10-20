package cn.com.ddhj.service.impl;

import java.util.List;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.util.CommonUtil;

public class Task1032Rubbish implements Callable<EnvInfo> {

	private TRubbishRecyclingMapper mapper;
	
	private TChemicalPlantMapper chemicalMapper; // TODO 这里的数据忘记加了，记得加上
	
	private String city;
	
	private String position;
	
	
	public EnvInfo call() throws Exception {
		EnvInfo r = new EnvInfo();
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		List<TRubbishRecycling> list = this.getMapper().findListByCity(this.getCity());
		String msg = "5Km以内";
		String level = "无";
		for(TRubbishRecycling e : list){
			Integer distance = CommonUtil.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			level = "垃圾站";
			if(4000< distance &&distance < 5000){
				msg = "4Km以外";
				break;
			}else if(3000< distance &&distance < 4000){
				msg = "3Km以外";
				break;
			}else if(2000< distance &&distance < 3000){
				msg = "2Km以外";
				break;
			}else if (distance < 2000){
				msg = "1Km以外";
				break;
			}
		}
		List<TChemicalPlant> clist = this.getChemicalMapper().findListByCity(this.getCity());
		for(TChemicalPlant e : clist){
			Integer distance = CommonUtil.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			level = "化工厂";
			if(4000< distance &&distance < 5000){
				msg = "4Km以外";
				break;
			}else if(3000< distance &&distance < 4000){
				msg = "3Km以外";
				break;
			}else if(2000< distance &&distance < 3000){
				msg = "2Km以外";
				break;
			}else if (distance < 2000){
				msg = "1Km以外";
				break;
			}
		}
		
		r.setName("污染源"); 
		r.setLevel(level);
		r.setMemo(msg); 
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













