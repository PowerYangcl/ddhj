package cn.com.ddhj.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Callable;

import org.apache.log4j.Logger;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TLandedScoreMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TLandedScore;

public class Task2048LandedPropertyUpdate2 implements Callable<Integer> {
	private static Logger logger = Logger.getLogger(Task2048LandedPropertyUpdate2.class);
	
	private List<TLandedProperty> list;
	
	private TLandedPropertyMapper lrMapper;
	
	private TLandedScoreMapper landedScoreMapper;

	private Date currentDate;

	public Task2048LandedPropertyUpdate2(List<TLandedProperty> list, TLandedPropertyMapper lrMapper , TLandedScoreMapper landedScoreMapper , Date currentDate) {
		this.list = list;
		this.lrMapper = lrMapper;
		this.landedScoreMapper = landedScoreMapper;
		this.currentDate = currentDate;
	}


	public Integer call() throws Exception {
		Integer flag = 0;
		try {
			flag = this.getLrMapper().batchUpdateScore(this.getList());
			for(TLandedProperty e : list){
				TLandedScore ls = new TLandedScore();
				ls.setUuid(UUID.randomUUID().toString().replace("-", ""));
				ls.setCode(e.getCode());
				ls.setScore(e.getScore()); 
				ls.setCreateTime(currentDate);
				ls.setCity(e.getCity());  
				landedScoreMapper.insertSelective(ls);
				logger.info(e.getScore() + " - " + currentDate + " - " + e.getCode() + " - " + e.getTitle());
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return flag; 
	}
	
	
	
	
	public TLandedScoreMapper getLandedScoreMapper() {
		return landedScoreMapper;
	}


	public void setLandedScoreMapper(TLandedScoreMapper landedScoreMapper) {
		this.landedScoreMapper = landedScoreMapper;
	}


	public Date getCurrentDate() {
		return currentDate;
	}


	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
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












