package cn.com.ddhj.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TLandedScoreMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TLandedScore;

public class Task2048LandedPropertyUpdate implements Callable<Integer> {

	private List<TLandedProperty> list;
	
	private TLandedPropertyMapper lrMapper;
	
	private TLandedScoreMapper landedScoreMapper;

	private Date currentDate;

	public Task2048LandedPropertyUpdate(List<TLandedProperty> list, TLandedPropertyMapper lrMapper , TLandedScoreMapper landedScoreMapper) {
		this.list = list;
		this.lrMapper = lrMapper;
		this.landedScoreMapper = landedScoreMapper;
	}
	
	public Task2048LandedPropertyUpdate(List<TLandedProperty> list, TLandedPropertyMapper lrMapper , TLandedScoreMapper landedScoreMapper , Date currentDate) {
		this.list = list;
		this.lrMapper = lrMapper;
		this.landedScoreMapper = landedScoreMapper;
		this.currentDate = currentDate;
	}


	public Integer call() throws Exception {
		Integer flag = 0;
		try {
			if(currentDate != null){
				flag = 1 ;  
				for(TLandedProperty e : list){
					TLandedScore ls = new TLandedScore();
					ls.setUuid(UUID.randomUUID().toString().replace("-", ""));
					ls.setCode(e.getCode());
					
					DecimalFormat df = new DecimalFormat("#.00");
					Random rand = new Random();  
			        Double score = Double.valueOf(df.format (Double.valueOf( rand.nextInt(50)+55 + Math.random()) ));
					ls.setScore(score); 
					
					ls.setCreateTime(currentDate);
					ls.setCity(e.getCity());  
					landedScoreMapper.insertSelective(ls);
					System.out.println("Task2048LandedPropertyUpdate -> 强制插入t_landed_score表! ");
				}
			}else{
				flag = this.getLrMapper().batchUpdateScore(this.getList());
				for(TLandedProperty e : list){
					TLandedScore ls = new TLandedScore();
					ls.setUuid(UUID.randomUUID().toString().replace("-", ""));
					ls.setCode(e.getCode());
					ls.setScore(e.getScore()); 
					ls.setCreateTime(new Date());
					ls.setCity(e.getCity());  
					landedScoreMapper.insertSelective(ls);
					System.out.println("Task2048LandedPropertyUpdate -> TLandedScore insert already! ");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return flag; 
	}
	
	
	
	
	public List<TLandedProperty> getList() {
		return list;
	}
	public void setList(List<TLandedProperty> list) {
		this.list = list;
	}

	public TLandedPropertyMapper getLrMapper() {
		return lrMapper;
	}
	public void setLrMapper(TLandedPropertyMapper lrMapper) {
		this.lrMapper = lrMapper;
	}
}












