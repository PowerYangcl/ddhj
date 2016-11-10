package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

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
import cn.com.ddhj.util.PureNetUtil;

/**
 * @descriptions 北上广深津，每个区域对应一个此线程，每个该线程将启动20个子线程访问教授接口|启动线程数量：n * 20
 *
 * @date 2016年10月19日 上午12:15:46
 * @author Yangcl 
 * @version 1.0.1
 */
public class Task2048EstateArea implements Callable<List<TLandedProperty>> {
	private static Logger logger=Logger.getLogger(Task2048EstateArea.class);
	
	private ExecutorService executor;
	private List<TLandedProperty> list;
	private String hourAqi;
	private String dayAqi;
	private List<TAreaNoise> noiseList;
	private List<TWaterEnviroment> waterEnvList;
	private List<TRubbishRecycling> rubbishList;
	private List<TChemicalPlant> chemicalList;
	

	public Task2048EstateArea(ExecutorService executor, List<TLandedProperty> list, String hourAqi, String dayAqi,
			List<TAreaNoise> noiseList, List<TWaterEnviroment> waterEnvList, List<TRubbishRecycling> rubbishList,
			List<TChemicalPlant> chemicalList) {
		this.executor = executor;
		this.list = list;
		this.hourAqi = hourAqi;
		this.dayAqi = dayAqi; 
		this.noiseList = noiseList;
		this.waterEnvList = waterEnvList;
		this.rubbishList = rubbishList;
		this.chemicalList = chemicalList;
	}



	public List<TLandedProperty> call() throws Exception {
		Thread.currentThread().setName(this.list.get(0).getCity() + "二级线程已经启动 - 99999999999999999"); 
		int size = 20; // 单组list大小
		int count = list.size() / size; // TreeMap 的分组数       10008/20 = 500 余 8 
		int count_ = list.size() - count * size; // 余数 
		Map<Integer , List<TLandedProperty>> map = new TreeMap<Integer , List<TLandedProperty>>();
		for(int i = 0 ; i < count ; i ++){
			map.put(i , list.subList(i*size , size*(i+1)));
		}
		if(count_ != 0){
			map.put(count, list.subList(count*size, list.size())); 
		}
		
		List<TLandedProperty> list_ = new ArrayList<TLandedProperty>(); 
		for (Map.Entry<Integer, List<TLandedProperty>> entry : map.entrySet()) {
			List<Future<TLandedProperty>> futList = new ArrayList<>();
			List<TLandedProperty> subList = entry.getValue();
			for(TLandedProperty e : subList){
				if(StringUtils.isAnyBlank(e.getLat() , e.getLng())){
					logger.info("该楼盘经纬度信息不全：" + e.getCity() + "|" + e.getTitle()); 
					continue;
				}
				String position = e.getLat() + "," + e.getLng();  
				Task2048EstateScore score = new Task2048EstateScore(e, hourAqi, dayAqi, e.getCity(), position, noiseList, waterEnvList, rubbishList, chemicalList);
						// 每个TreeMap分组对应20个线程去请求教授接口
				futList.add(executor.submit(score));
			}
			for(Future<TLandedProperty> fut : futList){
				while(!fut.isDone()){
				}
				list_.add(fut.get()); 
			}
		}
		
		return list_; 
	}

}

 















