package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.THighVoltageMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.THighVoltage;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.DCacheEnum;

public class Task1032Rubbish implements Callable<Map<String , String>> {

	private static Logger logger=Logger.getLogger(Task1032Rubbish.class);
	
	private TRubbishRecyclingMapper mapper;
	private TChemicalPlantMapper chemicalMapper; 
	private THighVoltageMapper highVoltageMapper;  // 高压线路 辐射污染  
	private String city;
	private String position;
	private ServletContext application;
	
	public Map<String , String> call() throws Exception {
		Map<String , String> r = new HashMap<String , String>();
		String[] arr = this.getPosition().split(",");
		Double lat = Double.valueOf(arr[0]);
		Double lng = Double.valueOf(arr[1]); 
		String msg = "5Km以内";
		TreeMap<Integer , String> map = new TreeMap<Integer , String>();
		List<TRubbishRecycling> list = null;
		List<TChemicalPlant> clist = null;
		List<THighVoltage> vlist = null;
		
//		TreeMap<Integer , String> rmap = new TreeMap<Integer , String>();   // 垃圾回收站
//		TreeMap<Integer , String> cmap = new TreeMap<Integer , String>();  // 化工厂
		TreeMap<Integer , String> hmap = new TreeMap<Integer , String>();  // 高压电
		
		try {
			String cdate = this.getCurrentDate(new Date());  // 当前时间 
			/*
			 * 回收站相关
			 */
			Object rubbish = application.getAttribute(DCacheEnum.rubbish_recycling.toString() + cdate);  //  null ;
			if(rubbish != null){
				list = JSONObject.parseArray(String.valueOf( rubbish ), TRubbishRecycling.class);
			}else{
				list = this.getMapper().findListByCity(this.getCity());
				// 设置今天的缓存 
				application.setAttribute(DCacheEnum.rubbish_recycling.toString() + cdate , JSONObject.toJSONString(list));  
				// 删除前一天的缓存
				application.removeAttribute(DCacheEnum.rubbish_recycling.toString() + this.getYesterday(new Date())); 
			}
			if(list != null && list.size() != 0){
				for(TRubbishRecycling e : list){
					Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
					map.put(d , "垃圾站");
				}
			}
			
			/*
			 * 化工厂相关
			 */
			Object chemical = application.getAttribute(DCacheEnum.chemical_plant.toString() + cdate);  //  null ;
			if(chemical != null){
				clist = JSONObject.parseArray(String.valueOf( chemical ), TChemicalPlant.class);
			}else{
				clist = this.getChemicalMapper().findListByCity(this.getCity());
				// 设置今天的缓存 
				application.setAttribute(DCacheEnum.chemical_plant.toString() + cdate , JSONObject.toJSONString(clist));  
				// 删除前一天的缓存
				application.removeAttribute(DCacheEnum.chemical_plant.toString() + this.getYesterday(new Date())); 
			}
			if(clist != null && clist.size() != 0){
				for(TChemicalPlant e : clist){
					Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
					map.put(d , "化工厂"); 
				}
			}
			
			/*
			 * 高压电线相关
			 */
			Object voltage = application.getAttribute(DCacheEnum.high_voltage.toString() + cdate);  //  null ;
			if(voltage != null){
				vlist = JSONObject.parseArray(String.valueOf(voltage), THighVoltage.class);
			}else{
				vlist = this.getHighVoltageMapper().findListByCity(this.getCity());
				// 设置今天的缓存 
				application.setAttribute(DCacheEnum.high_voltage.toString() + cdate , JSONObject.toJSONString(vlist));  
				// 删除前一天的缓存
				application.removeAttribute(DCacheEnum.high_voltage.toString() + this.getYesterday(new Date())); 
			}
			if(vlist != null && vlist.size() != 0){
				for(THighVoltage e : vlist){
					Integer d = CommonUtil.getMeterDistance(lat, lng, Double.valueOf(e.getLat()), Double.valueOf(e.getLng())); 
//					map.put(d , "高压电@" + e.getDeductScore()); 
					hmap.put(d, "高压电@" + e.getScore());  
				}
			}
			
			
		} catch (Exception e) {
			logger.info( "list = " + list.toString() + " | clist = " + clist.toString() );  
		}
		
		String score = "0";  
		if(map.size() == 0){
			logger.info(city + " map.firstKey() |" + this.getPosition()); 
			r.put("level", "无");
			r.put("memo", msg); 
			r.put("score", score);
			return r;
		}
		
		Integer distance = map.firstKey();
		if(distance > 5000){
			r.put("level", "无");
			r.put("memo", msg); 
			r.put("score", score);
			return r;
		}
		
		if(4000< distance &&distance < 5000){
			msg = "5Km以内";
		}else if(3000< distance &&distance < 4000){
			msg = "4Km以内";
		}else if(2000< distance &&distance < 3000){
			msg = "3Km以内";
			score = "-5";
		}else if (1000 < distance && distance < 2000){
			msg = "2Km以内";
			score = "-5";
		}else if ( distance < 1000 ){
			msg = "1Km以内";
			score = "-20";
		} else if(distance < 500){
			score = "-30"; 
		}
		
		String value = map.get(map.firstKey());
		String level_ = value;
		if(hmap.size() != 0){
			map.put(hmap.firstKey() , hmap.get(hmap.firstKey()));
			level_ = map.get(map.firstKey());
			if(StringUtils.startsWith(level_ , "高压电")){
				distance = map.firstKey();
				if(distance <= 520){
					msg = "500m以内";
					score = (Integer.valueOf(level_.split("@")[1]) + Integer.valueOf(score)) + "" ;  
					level_ = "高压电";
				}else{
					level_ = value;
				}
			}
			
		}
		
		
		r.put("level", level_);
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
	
	// 获取当天日期
	private String getCurrentDate(Date date){ 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 return formatter.format(date);
	}
	
	// 找到前一天
	private String getYesterday(Date date){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE , -1); 
		 date=calendar.getTime();  
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 
		 return formatter.format(date);
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
	public ServletContext getApplication() {
		return application;
	}
	public void setApplication(ServletContext application) {
		this.application = application;
	}
	public THighVoltageMapper getHighVoltageMapper() {
		return highVoltageMapper;
	}
	public void setHighVoltageMapper(THighVoltageMapper highVoltageMapper) {
		this.highVoltageMapper = highVoltageMapper;
	}
	
}













