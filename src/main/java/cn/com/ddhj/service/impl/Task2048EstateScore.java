package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.util.DoctorScoreUtil;
import cn.com.ddhj.util.PureNetUtil;

public class Task2048EstateScore implements Callable<TLandedProperty> {

	private TLandedProperty entity;
	private String hourAqi;
	private String dayAqi;
	private ExecutorService executor;
	private String city;
	private String position;
	private ITAreaNoiseMapper noiseMapper;
	private TWaterEnviromentMapper waterEnvMapper;   
	private TRubbishRecyclingMapper rubbishMapper;
	private TChemicalPlantMapper chemicalMapper; 

	public Task2048EstateScore(TLandedProperty entity, String hourAqi, String dayAqi, ExecutorService executor,
			String city, String position, ITAreaNoiseMapper noiseMapper, TWaterEnviromentMapper waterEnvMapper,
			TRubbishRecyclingMapper rubbishMapper, TChemicalPlantMapper chemicalMapper) {
		this.entity = entity;
		this.hourAqi = hourAqi;
		this.dayAqi = dayAqi;
		this.executor = executor;
		this.city = city;
		this.position = position;
		this.noiseMapper = noiseMapper;
		this.waterEnvMapper = waterEnvMapper;
		this.rubbishMapper = rubbishMapper;
		this.chemicalMapper = chemicalMapper;
	}


	public TLandedProperty call() throws Exception {
		Thread.currentThread().setName(this.entity.getCity() + "|" + this.entity.getTitle() + "|三级线程已经启动 - 执行中...."); 
		String name = Thread.currentThread().getName();
		
		Task1032Noise noi = new Task1032Noise();
        noi.setCity(city);
        noi.setNoiseMapper(noiseMapper);
        noi.setPosition(position);
        Future<String> noiFuture = executor.submit(noi);
		
        Task1032WaterEnv w = new Task1032WaterEnv();
        w.setWaterEnvMapper(waterEnvMapper);
        w.setCity(city);
        w.setPosition(position);
        Future<Map<String , String>> wFuture = executor.submit(w);
        
        Task1032Rubbish rub = new Task1032Rubbish();
        rub.setCity(city);
        rub.setPosition(position);
        rub.setMapper(rubbishMapper);
        rub.setChemicalMapper(chemicalMapper); 
        Future<Map<String , String>> rubFuture = executor.submit(rub);
        
		
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
			while(!noiFuture.isDone()){
				System.out.println(name + "|noiFuture = " + noiFuture.isDone() + "|wFuture = " + wFuture.isDone() + "|rubFuture = " + rubFuture.isDone());
				Thread.sleep(1000); 
			}
			while(!wFuture.isDone() ){
				System.out.println(name + "|noiFuture = " + noiFuture.isDone() + "|wFuture = " + wFuture.isDone() + "|rubFuture = " + rubFuture.isDone());
				Thread.sleep(1000); 
			}
			while(!rubFuture.isDone()){
				System.out.println(name + "|noiFuture = " + noiFuture.isDone() + "|wFuture = " + wFuture.isDone() + "|rubFuture = " + rubFuture.isDone());
				Thread.sleep(1000); 
			}
			
			String z1 = "1";
			String z2 = "0";
			String nlevel = noiFuture.get().split("@")[1];
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
			score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , wFuture.get().get("s") , z1 , z2);
			if(rubFuture.get() != null){ // 污染源，针对最后的综合评分 距离500米 得出分-30
				score = String.valueOf( (Double.valueOf(score) + Double.valueOf(rubFuture.get().get("score"))) ); 
				if(score.length() > 5){
					System.out.println("exception score = " + score); 
					score = score.substring(0, 5);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		entity.setScore(Double.valueOf(score));
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		entity.setUpdateTime(sdf.format(new Date()));
		
		return entity; 
	}

}
 















