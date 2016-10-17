package cn.com.ddhj.service;

import java.util.List;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.util.CommonUtil;

public class Task1032Rubbish implements Callable<String> {

	private TRubbishRecyclingMapper mapper;
	
	private String city;
	
	private String position;
	
	
	public String call() throws Exception {
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		List<TRubbishRecycling> list = this.getMapper().findListByCity(this.getCity());
		String msg = "无污染源";
		for(TRubbishRecycling e : list){
			Integer distance = CommonUtil.getDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
			if(distance < 5000){
				return "距污染源" + Double.valueOf(distance)/1000 + "Km";
			}
		}
		
		return msg;
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
	
}













