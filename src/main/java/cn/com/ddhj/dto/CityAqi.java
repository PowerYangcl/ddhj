package cn.com.ddhj.dto;

import java.util.List;

public class CityAqi {
	private String name; // city name

	private CityAqiData entity;
	
	private List<CityAqiData> list;

	public CityAqiData getEntity() {
		return entity;
	}
	public void setEntity(CityAqiData entity) {
		this.entity = entity;
	}
	public List<CityAqiData> getList() {
		return list;
	}
	public void setList(List<CityAqiData> list) {
		this.list = list;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
