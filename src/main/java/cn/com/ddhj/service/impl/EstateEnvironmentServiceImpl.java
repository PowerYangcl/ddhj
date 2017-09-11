package cn.com.ddhj.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.dto.CityAqi;
import cn.com.ddhj.dto.CityAqiData;
import cn.com.ddhj.dto.LandedScoreAverageDto;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TCityWeatherForecastMapper;
import cn.com.ddhj.mapper.THighVoltageMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TLandedScoreJobMapper;
import cn.com.ddhj.mapper.TLandedScoreMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TCityWeatherForecast;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TLandedScoreJob;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.LandedScoreResult;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.IEstateEnvironmentService;
import cn.com.ddhj.service.IEstateInfoService;
import cn.com.ddhj.service.ILongitudeLatitudeService;
import cn.com.ddhj.service.IWeatherAreaSupportService;
import cn.com.ddhj.util.CommonUtil;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DCacheEnum;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.DoctorScoreUtil;
import cn.com.ddhj.util.PureNetUtil;


/**
 * @descriptions 地产环境信息相关
 *
 * @date 2016年10月4日 下午5:13:23
 * @author Yangcl 
 * @version 1.0.1
 */
@Service
public class EstateEnvironmentServiceImpl implements IEstateEnvironmentService	{
	
	private static Logger logger=Logger.getLogger(EstateEnvironmentServiceImpl.class);
	
	/**
	 * 生态状况:绿化率指数 	0.5或1  【地产检索接口->"greeningRate":"50%"】
	 * 生态状况:容积率指数 	0~9之间 【地产检索接口->"volumeRate":"0.46"】
	 */
	@Resource
	private IEstateInfoService estateService;  // 地产信息相关接口 
	
	@Resource
	private ICityAirService cityAirService;
	
	@Resource
	private ILongitudeLatitudeService llService;
	
	@Resource
	private TLandedPropertyMapper lrMapper;
	
	@Resource
	private IWeatherAreaSupportService wasService;
	
	@Resource
	private TReportMapper reportMapper;
	
	@Resource
	private ITAreaNoiseMapper noiseMapper;
	
	@Resource
	private TRubbishRecyclingMapper rubbishMapper; // 污染源-垃圾站|焚化厂等
	
	@Resource
	private TChemicalPlantMapper chemicalMapper; // 污染源-化工厂
	
	@Resource
	private THighVoltageMapper highVoltageMapper;  // 高压线路 辐射污染  
	
	@Resource
	private TWaterEnviromentMapper waterEnvMapper;  // 水质量信息
	
	@Resource
	private TCityWeatherForecastMapper cityWeatherForecastMapper; // 【7*24小时城市天气空气质量预报】
	
	@Resource
	private TLandedScoreMapper landedScoreMapper;
	
	@Resource
	private TLandedScoreJobMapper landedScoreJobMapper;
	
	
	/**
	 * @descriptions 地区环境接口|1025
	 * @test
	 * 	http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=1025&api_key=appfamilyhas&apiInput=%7b%22position%22%3a%2239.91488908%2c116.40387397%22%2c%22city%22%3a%22%E5%8C%97%E4%BA%AC%22%2c%22radius%22%3a2000%2c%22pageIndex%22%3a0%7d&api_timespan=2017-03-23+17%3a27%3a20&api_secret=6e4d37dbc6b5d7a02c853b7dd0495b64
	 * 	
	 * 	http://localhost:8080/ddhj/api.htm?apiTarget=1025&api_key=appfamilyhas&apiInput=%7b%22position%22%3a%2239.91488908%2c116.40387397%22%2c%22city%22%3a%22%e5%8c%97%e4%ba%ac%22%2c%22radius%22%3a2000%2c%22pageIndex%22%3a0%7d&api_timespan=2017-03-23+17%3a27%3a20&api_secret=6e4d37dbc6b5d7a02c853b7dd0495b64
	 *
	 * @param position
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @return
	 * @date 2016年10月7日 下午8:19:29
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject apiAreaEnv(String position , String city , ServletContext application) {
		JSONObject result = new JSONObject();
		if(StringUtils.isAnyBlank(position)){
			result.put("resultCode", -1); 
			result.put("resultMessage", "参数不得为空"); 
			return result;
		}
		
		try {
			String[] arr = position.split(",");
			String lat = arr[0];
			String lng = arr[1];
			long start = System.currentTimeMillis();
			ExecutorService executor = Executors.newCachedThreadPool();
			Task1025Position pos = new Task1025Position();
			pos.setLlService(llService);
			pos.setLat(lat);
			pos.setLng(lng); 
			Future<JSONObject> posTask =  executor.submit(pos); 
			
			Task1025Weather wea = new Task1025Weather();
			wea.setWasService(wasService);
			wea.setLat(lat);
			wea.setLng(lng);
			Future<JSONObject> weaTask =  executor.submit(wea);  
			
			Task1032WaterEnv w = new Task1032WaterEnv();
	        w.setWaterEnvMapper(waterEnvMapper);
	        w.setCity(city);
	        w.setPosition(position);
	        Future<Map<String , String>> wFuture = executor.submit(w);
	        
	        Task1032Noise noi = new Task1032Noise();
	        noi.setCity(city);
	        noi.setNoiseMapper(noiseMapper);
	        noi.setPosition(position);
	        Future<String> noiFuture = executor.submit(noi);
	        
	        Task1032Rubbish rub = new Task1032Rubbish();
	        rub.setCity(city);
	        rub.setPosition(position);
	        rub.setMapper(rubbishMapper);
	        rub.setChemicalMapper(chemicalMapper); 
	        rub.setApplication(application);
	        rub.setHighVoltageMapper(highVoltageMapper); 
	        Future<Map<String , String>> rubFuture = executor.submit(rub);
	        
	        Task1032Weather tw_ = new Task1032Weather();
			tw_.setCity(city);
			tw_.setCityAirService(cityAirService); 
			Future<JSONObject> twTask =  executor.submit(tw_); 
			
	        Task1032Estate est = new Task1032Estate();
	        est.setPosition(position);
	        est.setRadius("2000");
	        est.setEstateService(estateService);
	        Future<List<EnvInfo>> estFuture = executor.submit(est);
			
			JSONObject addr = posTask.get();
			JSONObject obj = weaTask.get();
			JSONObject tw = twTask.get();
	        executor.shutdown();
	        long end = System.currentTimeMillis();
	        logger.info("1025接口 - 聚合接口耗时：" + (end - start) + " 毫秒"); 
	        			
			
			if(addr.getString("code").equals("1") && StringUtils.isNotBlank(addr.getString("business")) ){
				result.put("name", addr.getString("business").split(",")[0]); // 位置名称
			}else{
				if(StringUtils.isNotBlank(addr.getString("address"))){
					result.put("name", addr.getString("address")); // 位置名称
				}else{
					result.put("resultCode", -1); 
					result.put("resultMessage", "附近未发现可用位置信息"); 
					return result;
				}
			}
			
			if(obj == null || obj.getString("resultcode") == null || !obj.getString("resultcode").equals("200")) {
				result.put("resultCode", -1); 
				result.put("resultMessage", "天气查询接口返回空"); 
				return result;
			}
			
			JSONObject result_ = JSONObject.parseObject(obj.getString("result")); 
			JSONObject today = JSONObject.parseObject(result_.getString("today")); 
			String weather = today.getString("weather");
			JSONObject weatherId = JSONObject.parseObject(today.getString("weather_id"));  // 序列话成对象，避免出现转义json串的情况，方便页面调用
			String windpower = today.getString("wind");
			String tempmin = today.getString("temperature").split("~")[0].split("℃")[0];
			String tempmax = today.getString("temperature").split("~")[1].split("℃")[0];
			String tiptitle = today.getString("dressing_advice");
			SimpleDateFormat sdf= new SimpleDateFormat("yyyy.MM.dd");
			String tiptime = sdf.format(new Date()) + today.getString("week");  
			
			String hourAqi = "80";
			String dayAqi = "";
			CityAqi aqi = cityAirService.getCityAqi(city);
			if(aqi.getEntity() != null) {
				hourAqi = aqi.getEntity().getAQI();
				for(CityAqiData d : aqi.getList()){
					dayAqi += d.getAQI() + ",";
				}
				dayAqi = dayAqi.substring(0 , dayAqi.length()-1);
			}
			
			String greeningRate = "1";  // 如下条件不满足则用默认值
			String volumeRate = estFuture.get().get(0).getMemo();	   // 如下条件不满足则用默认值
			JSONObject estate = this.estateList(position, "1" , "1" , "1000"); // 获取楼盘信息 
			if(estate.getString("code").equals("1")) {
				List<EData> estateList = JSONArray.parseArray(estate.getString("list"), EData.class);
				try {
					if(estateList != null && estateList.size() > 0){
						EData e = estateList.get(0);               // 因为精度是1米 所以只有一条记录，且就是这个楼盘
						result.put("name", e.getTitle()); // 位置名称
						if(StringUtils.isNoneBlank(e.getGreeningRate())){
							if(Double.valueOf(e.getGreeningRate().split("%")[0])/100 < 0.5){   // 潜在的异常点
								greeningRate = "0.5"; //教授接口返回HTTP Status 500 - 绿化率指数l只能是0.5或1|真坑爹
							}
						}
						// 聚合接口的容积率均返回错误数据，"volumeRate": "一期2.45元/平米/月;二期2.45/平米/月；三期3.1元/",	
						// volumeRate = "0.4"; // 这里写定一个默认值		
					}
				} catch (Exception e) {
					greeningRate = "1";
				}
			}
			
			String z1 = "1";
			String z2 = "0";
			Double nscore = 0.00;
			String nlevel = noiFuture.get().split("@")[0];
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
				nscore = -10.00;
			}
//			String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , wFuture.get().get("s") , z1 , z2);
			String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, "0.5", volumeRate , wFuture.get().get("s") , z1 , z2);
			if(rubFuture.get() != null){ // 污染源，针对最后的综合评分 距离500米 得出分-30
				score = String.valueOf( (Double.valueOf(score) + nscore + Double.valueOf(rubFuture.get().get("score"))) );
				if(score.length() > 5){
					logger.info("exception score = " + score); 
					score = score.substring(0, 5);
				}
			}
			result.put("score", score); // 环境综合评分
			result.put("level", this.scoreLevel(score));  // 环境等级
//				result.put("value", "16");// 环境值 经过讨论，这个值不再显示
			
			result.put("humidity", tw.getString("humidity"));
			result.put("temperature", tw.getString("temperature"));
			result.put("updateTime", tw.getString("updateTime"));  
			result.put("pm25", tw.getString("pm25"));   
			result.put("weather", weather); 
			result.put("weatherId", weatherId);
			result.put("windpower", windpower); 
			result.put("tempmin", tempmin); 
			result.put("tempmax", tempmax); 
			result.put("tiptitle", tiptitle); 
			result.put("tiptime", tiptime); 
			
			// 明日数据 tw
			JSONObject futureData = new JSONObject();
			JSONObject objFuture = JSONObject.parseObject(result_.getString("future")); 
			JSONObject tomorrow = JSONObject.parseObject(objFuture.getString("day_" + this.getNextDate(new Date(), "yyyyMMdd")));   
			if(tomorrow != null){
				futureData.put("futureTemperature", tw.getString("futureTemperature"));  
				futureData.put("futureHumidity", "0");
				futureData.put("wind", tomorrow.getString("wind")); 
				futureData.put("weather", tomorrow.getString("weather")); 
				futureData.put("weatherId", JSONObject.parseObject(tomorrow.getString("weather_id")));     
				futureData.put("pm25", tw.getString("pm25"));  //  今日pm2.5数据，但无法取得明日的数据 
			}
			result.put("futureData", futureData);  
			
			result.put("resultCode", 1); 
			result.put("resultMessage", "SUCCESS"); 
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resultCode", -1); 
			result.put("resultMessage", "平台内部错误"); 
			return result;
		}
	}
	
	
	/**
	 * @descriptions 环境综合评分接口|1032
	 * 
	 * 	 如果 lpcode不为空 则为 楼盘实时得分接口
	 *
	 * @test
	 * 	http://stockwyz.xicp.net/ddhj/api.htm?apiTarget=1032&api_key=appfamilyhas&apiInput=%7b%22position%22%3a%2239.91488908%2c116.40387397%22%2c%22city%22%3a%22%e5%8c%97%e4%ba%ac%22%2c%22radius%22%3a2000%7d&api_timespan=2017-02-16+15%3a28%3a25&userToken=f32639fa4e494bec9d188c44417077c1&api_secret=6e832b4938e1c9d361cd0e0c6093e09b
	 * 	http://localhost:8080/ddhj/api.htm?apiTarget=1032&api_key=appfamilyhas&apiInput=%7b%22position%22%3a%2239.91488908%2c116.40387397%22%2c%22city%22%3a%22%e5%8c%97%e4%ba%ac%22%2c%22radius%22%3a2000%7d&api_timespan=2017-02-16+15%3a28%3a25&userToken=f32639fa4e494bec9d188c44417077c1&api_secret=6e832b4938e1c9d361cd0e0c6093e09b
	 * 
	 * @param position|经纬度逗号分隔
	 * @param title | 地产名称
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @return
	 * @date 2016年10月4日 下午5:30:13
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */ 
	public JSONObject apiEnvScore(String position , String city , String radius , ServletContext application , String lpcode){
		JSONObject result = new JSONObject();
		if(StringUtils.isAnyBlank(position , city)){
			result.put("resultCode", -1); 
			result.put("resultMessage", "参数不得为空"); 
			return result;
		}
		try {
			long start = System.currentTimeMillis();
			ExecutorService executor = Executors.newCachedThreadPool();
			Task1032Weather twea = new Task1032Weather();
			twea.setCityAirService(cityAirService);
			twea.setCity(city); 
			Future<JSONObject> weaTask =  executor.submit(twea); 
	        
			// 666
	        Task1032Aqi taqi = new Task1032Aqi();
	        taqi.setCityAirService(cityAirService);
	        taqi.setCity(city); 
	        Future<CityAqi> aqiFuture = executor.submit(taqi);
	        
	        Task1032Noise noi = new Task1032Noise();
	        noi.setCity(city);
	        noi.setNoiseMapper(noiseMapper);
	        noi.setPosition(position);
	        Future<String> noiFuture = executor.submit(noi);
	        
	        Task1032Rubbish rub = new Task1032Rubbish();
	        rub.setCity(city);
	        rub.setPosition(position);
	        rub.setMapper(rubbishMapper);
	        rub.setChemicalMapper(chemicalMapper); 
	        rub.setApplication(application);
	        rub.setHighVoltageMapper(highVoltageMapper); 
	        Future<Map<String , String>> rubFuture = executor.submit(rub);
	        
	        Task1032Estate est = new Task1032Estate();
	        est.setPosition(position);
	        est.setRadius(radius);
	        est.setEstateService(estateService);
	        Future<List<EnvInfo>> estFuture = executor.submit(est);
	        
	        // 666
	        Task1032WaterEnv w = new Task1032WaterEnv();
	        w.setWaterEnvMapper(waterEnvMapper);
	        w.setCity(city);
	        w.setPosition(position);
	        Future<Map<String , String>> wFuture = executor.submit(w);
	        
	        
	        JSONObject weather = weaTask.get();
	        CityAqi aqi = aqiFuture.get();
	        executor.shutdown();
			long end = System.currentTimeMillis();
			logger.info("1032号接口 - 聚合接口耗时：" + (end - start) + " 毫秒"); 
	        
	        
	        String hourAqi = "80";
			String dayAqi = "";
			if(aqi.getEntity() != null) {
				hourAqi = aqi.getEntity().getAQI();
				for(CityAqiData d : aqi.getList()){
					dayAqi += d.getAQI() + ",";
				}
				dayAqi = dayAqi.substring(0 , dayAqi.length()-1);
			}
			
			
			
			String greeningRate = "1";  // 如下条件不满足则用默认值
			String volumeRate = "1.2";   // 如下条件不满足则用默认值 
			if(StringUtils.isBlank(lpcode)){
				volumeRate = estFuture.get().get(0).getMemo();	   // 如下条件不满足则用默认值
				JSONObject estate = this.estateList(position, "1" , "1" , radius); // 获取楼盘信息
				if(estate.getString("code").equals("1")) {
					List<EData> estateList = JSONArray.parseArray(estate.getString("list"), EData.class);
					try {
						if(estateList != null && estateList.size() > 0){
							EData e = estateList.get(0);               // 因为精度是1米 所以只有一条记录，且就是这个楼盘
							result.put("name", e.getTitle()); // 位置名称
							if(StringUtils.isNoneBlank(e.getGreeningRate())){
								if(Double.valueOf(e.getGreeningRate().split("%")[0])/100 < 0.5){   // 潜在的异常点
									greeningRate = "0.5"; //教授接口返回HTTP Status 500 - 绿化率指数l只能是0.5或1|真坑爹
								}
							}
							// 聚合接口的容积率均返回错误数据，"volumeRate": "一期2.45元/平米/月;二期2.45/平米/月；三期3.1元/",	
							// volumeRate = "0.4"; // 这里写定一个默认值		
						}
					} catch (Exception e) {
						greeningRate = "1";
					}
				}
			}else{
				TLandedProperty lp = lrMapper.selectByCode(lpcode);
				if(lp != null){
					greeningRate = lp.getGreeningRate();
					volumeRate = this.getRjLevel(lp.getPropertyType()).split("@")[1]; 
					result.put("name", lp.getTitle()); // 位置名称
				}else{
					result.put("resultCode", -1); 
					result.put("resultMessage", "楼盘编号错误"); 
					return result;
				}
			}
			
			 
			String z1 = "1";
			String z2 = "0";
			Double nscore = 0.00;
			String nlevel = noiFuture.get().split("@")[0];
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
				nscore = -10.00;
			}
//			String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , wFuture.get().get("s") , z1 , z2);
			String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, "0.5", volumeRate , wFuture.get().get("s") , z1 , z2);
			if(rubFuture.get() != null){ // 污染源，针对最后的综合评分 距离500米 得出分-30
				score = String.valueOf( (Double.valueOf(score) + nscore + Double.valueOf(rubFuture.get().get("score"))) );
				if(score.length() > 5){
					logger.info("exception score = " + score); 
					score = score.substring(0, 5);
				}
			}
			result.put("score", score); // 环境综合评分
			result.put("level", this.scoreLevel(score));  // 环境等级
			if(aqi.getList() != null){
				result.put("AQIList", this.initAqiList(aqi.getList()));  
			}
			
			
			List<EnvInfo> envList = new LinkedList<>();  
			EnvInfo air = new EnvInfo();
			air.setName("空气");
			if(aqi.getEntity() != null){
				air.setMemo(aqi.getEntity().getAQI());
				air.setLevel(aqi.getEntity().getQuality()); 
			}
			envList.add(air);
			
			EnvInfo noise = new EnvInfo();
			noise.setName("噪音");
			noise.setMemo(noiFuture.get().split("@")[1]);  
			noise.setLevel(noiFuture.get().split("@")[0]);  
			envList.add(noise);
			
			// 污染源
			EnvInfo gar = new EnvInfo();
			gar.setName("污染源");
			if(rubFuture.get() != null){
				Map<String , String> rmap = rubFuture.get(); 
				gar.setMemo(rmap.get("memo"));
				gar.setLevel(rmap.get("level")); 
			}else{
				gar.setMemo("5Km以内");
				gar.setLevel("无"); 
			}
			envList.add(gar);
			
			EnvInfo water = new EnvInfo();  
			water.setName("水质");
			water.setMemo(wFuture.get().get("memo"));   // "溶解氧"
			water.setLevel(wFuture.get().get("level"));
			envList.add(water);
			
			// 新版需求
			EnvInfo land = new EnvInfo();    // 土壤
			land.setName("土壤");
			land.setMemo("良");
			land.setLevel("ll类"); 
			
			EnvInfo dang = new EnvInfo(); // 危险品
			dang.setName("危险品");
			dang.setMemo("无");
			dang.setLevel("安全");  
			
			EnvInfo radiation  = new EnvInfo();    // 辐射
			radiation.setName("辐射");
			radiation.setMemo("无");
			radiation.setLevel("优"); 

			envList.add(land);
			envList.add(dang);
			envList.add(radiation);
			// 绿化率 和 容积率 (距离最近的楼盘)
			envList.addAll(estFuture.get());
		
			result.put("detailList", envList);  // 环境明细
			result.put("level", this.scoreLevel(score));  // 环境等级
			result.put("tiptitle", weather.getString("des"));  // 提示标题
			result.put("resultCode", 1); 
			result.put("resultMessage", "SUCCESS"); 
			logger.info("1032接口：" + result); 
			return  result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("resultCode", -1); 
			result.put("resultMessage", "系统内部错误");   
			return  result;
		}
	}
	
	
	/**
	 * @descriptions 楼盘列表|检索该经纬度附近10Km内的楼盘信息|1033
	 * 								 城市名称|北京，上海，广州，深圳
	 *@测试路径 http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=1033&api_key=appfamilyhas&apiInput={"position":"39.91433068500728,116.51123072649193" , "city":"北京" , "page":"1" , "radius":"5000" , "count":"100"}
	 *
	 *
	 * @param position|经纬度逗号分隔，纬度在前经度在后
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @param page | 当前第几页，每页数据10条
	 * @return
	 * @date 2016年10月4日 下午5:30:13
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */ 
	public JSONObject apiEstateList(String position , String city , String page , String count , String radius, String lpCode, ServletContext application)  {
		JSONObject result = new JSONObject();
		if(StringUtils.isAnyBlank(position , page)){
			result.put("resultCode", -1); 
			result.put("resultMessage", "参数不得为空"); 
			return result;
		}
		
		String[] arr = position.split(",");
		String lat = arr[0];
		String lng = arr[1];
		
		JSONObject addr = llService.getCurrentPositionInfo(lng, lat, "2");     
		if(addr.getString("code").equals("1")){
			result.put("currname", addr.getString("address")); // 当前位置信息
			//将查询结果扩大10登,解决小结点集中一边的问题
			String extend = String.valueOf(Integer.parseInt(count) * 10);
			JSONObject eListInfo = this.estateList(position, page , extend, radius); // 经纬度周边地产信息
			if(eListInfo.getString("code").equals("1")) {
				List<EData> list = JSONArray.parseArray(eListInfo.getString("list") , EData.class); // 获取地产信息列表
				if(list != null && list.size() > 0) {  
					List<Integer> choose = new ArrayList<Integer>();
					List<EData> chooseList = new ArrayList<EData>();
					boolean found = false;
					EData target = null;
					for(int i = 0; i < Integer.parseInt(count); i++) {
						//从10倍的list中,随机选 择count个
						int suffix = RandomUtils.nextInt(list.size());
						int retry = 0;
						while(choose.contains(suffix) && retry < 10) {
							suffix = RandomUtils.nextInt(list.size());
							retry++;
						}
						//根据随机生成的下标去扩大的列表中找到选中的楼盘
						EData edata = list.get(suffix);
						choose.add(suffix);
						chooseList.add(edata);
						
						if(StringUtils.isNotBlank(lpCode) && lpCode.equals(edata.getCode())) {
							//比本次随机选中的楼盘编号跟api传过来的关注楼盘编号
							//随机选中的楼盘包含了api传过来的用户关注楼盘
							target = edata;
							found = true;
						}
					}
					
					if(chooseList.size() == Integer.parseInt(count)) {
						list = chooseList;
					}
					
					JSONObject obj = apiEnvScore(position, city, radius, application, lpCode);
					if(!found) {
						//随机选中的楼盘列表中不包含api传过来的用户关注楼盘
						//查询用户关注的楼盘加到返回的楼盘列表中
						EData edata = lrMapper.selectByCodeForEData(lpCode);
						if(edata != null && obj != null) {
							String score = obj.getString("score");
							if(StringUtils.isNotBlank(score)) {
								try {
									edata.setScore(Double.parseDouble(score));
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
							list.add(edata);
						}
					} else {
						if(obj != null) {
							String score = obj.getString("score");
							if(StringUtils.isNotBlank(score)) {
								try {
									target.setScore(Double.parseDouble(score));
								} catch(Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					
					List<String> titles = new ArrayList<>();
					for(int i = 0 ; i < list.size() ; i ++) {
						titles.add(list.get(i).getTitle() );
					}
					List<TLandedProperty> lpList = lrMapper.findCodeByTitle(titles); // 楼盘编号|楼盘名称
					List<String> lpcodes = new ArrayList<>();
					for(TLandedProperty p : lpList){
						if(p.getCity().equals(city)){
							lpcodes.add(p.getCode());
						}
					}
					List<TReport> rList = null;
					if(null != lpcodes && !lpcodes.isEmpty()) {
						rList = reportMapper.findPriceByCode(lpcodes);
					}
					
					SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
					List<Estate> projectList = new ArrayList<>();
					// 开始组建数据
					for(EData e : list) {
						String position_ = e.getLat() + "," + e.getLng();
						String distance = CommonUtil.getDistance(lat, lng, e.getLat(), e.getLng());
						String lpcode = "";
						String price = "￥200";
						String updateTime = sdf.format(new Date());  // 默认为今天
						if(lpList != null && lpList.size() > 0){
							for(TLandedProperty p : lpList){
								String lpname = p.getTitle();
								if(e.getTitle().equals(lpname)){
									lpcode = p.getCode();
									if(rList != null && rList.size() > 0){
										for(TReport r : rList){
											if(r.getHousesCode().equals(lpcode)){
												price = String.valueOf(r.getPrice());
												updateTime = r.getUpdateTime();
												break;
											}
										}
									}
									break;
								}
							}
						}
						String img = "";
						if(e.getImages() != null && e.getImages().size() > 0){
							img = e.getImages().get(0);
						}
						projectList.add(new Estate(e.getTitle() , distance , e.getAddressFull() , price , lpcode, position_, img , e.getScore() , updateTime) );		
					}

//					System.out.println(JSONObject.toJSONString(projectList));   
					Collections.sort(projectList, new Comparator<Estate>() {
						@Override
						public int compare(Estate o1, Estate o2) {
							if(o1.getScore() > o2.getScore()) {
								return -1;
							} else if(o1.getScore() < o2.getScore()) {
								return 1;
							} else {
								return 0;
							}
						}
						
					}); 
//					Collections.reverse(projectList); 
//					for(Estate e : projectList){
//						logger.info(e.getScore() + "|" + e.getDistance()); 
//					}
					if(projectList.size() != 0){
						result.put("resultCode", 1);
						result.put("resultMessage", "SUCCESS");
						result.put("projectlist",  projectList); 
					}
				}else{
					result.put("resultCode", -1);
					result.put("resultMessage", "周围10Km没有地产信息");
				}
			}else{
				result.put("resultCode", -1);
				result.put("resultMessage", "检索周边地产信息失败" + eListInfo.getString("msg"));
			}
		}else{
			result.put("resultCode", -1);
			result.put("resultMessage", "经纬度地址解析失败，无法获取当前地理位置信息");
		}
		
		logger.info("1033接口：" + result);  
		return  result; 
	}
	
	
	private JSONObject estateList(String position , String page , String count , String radius){
		String[] arr = position.split(",");
		String lat = arr[0];
		String lng = arr[1]; 
		
		return  estateService.estateInfoList(lng, lat, page , count ,radius); 
	}
	
	
	/**
	 * @description:  手动刷新楼盘评分 | 接口号 2048 
	 * 本地测试地址：
	 *  http://localhost:8080/ddhj/api.htm?apiTarget=2048&api_key=appfamilyhas 
	 *  线上访问地址：
	 *  http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=2048&api_key=appfamilyhas
	 * @author Yangcl 
	 * @date 2016年10月18日 下午4:29:29 
	 * @version 1.0.0.1
	 */
	public void resyncEstateScore(){ 
		List<String> clist = new ArrayList<String>();
		clist.add("北京");
		clist.add("天津");
		clist.add("深圳");
		clist.add("唐山");
		clist.add("廊坊");
		clist.add("沈阳");
		clist.add("武汉");
		clist.add("烟台");
		clist.add("长沙");
		clist.add("青岛");
		clist.add("重庆");
		clist.add("大连");
		clist.add("成都"); 
		
		List<Future<CityAqi>> futureList = new ArrayList<Future<CityAqi>>();   
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0 ; i < clist.size() ; i ++){
			Task1032Aqi taqi = new Task1032Aqi();
	        taqi.setCityAirService(cityAirService);
	        taqi.setCity(clist.get(i));  
	        futureList.add(executor.submit(taqi));
		}

		Map<String , List<TLandedProperty>> map = new TreeMap<String , List<TLandedProperty>>();
		for(String city : clist){  // 默认初始化
			List<TLandedProperty> elist = new ArrayList<TLandedProperty>();
			map.put(city, elist);
		}
		List<TLandedProperty> estateList = lrMapper.selectAllEstateInfo();
		for(TLandedProperty e : estateList){
			if(map.containsKey(e.getCity())){
				map.get(e.getCity()).add(e);
			}
		}
		
		List<TLandedProperty> nestateList = new ArrayList<>();
		List<Future<List<TLandedProperty>>> tlpFutureList = new ArrayList<Future<List<TLandedProperty>>>();   
		try {
			for (Future<CityAqi> fs : futureList){  
				CityAqi aqi = null;
				while(!fs.isDone()){
					logger.info("等待中");
					Thread.sleep(100); 
				}
				aqi = fs.get();
				String hourAqi = "80";
				String dayAqi = "";
				if(aqi.getEntity() != null) {
					hourAqi = aqi.getEntity().getAQI();
					for(CityAqiData d : aqi.getList()){
						dayAqi += d.getAQI() + ",";
					}
					dayAqi = dayAqi.substring(0 , dayAqi.length()-1);
				}
				// 按照city名称 分为N个线程，一共会启动N*20个线程|TODO 注意：此处线程数量不建议超过120个  
				if(map.containsKey(aqi.getName())){
					List<TLandedProperty> tlpList = map.get(aqi.getName());
					List<TAreaNoise> noiseList = noiseMapper.selectByArea(aqi.getName()); 
					List<TWaterEnviroment> waterEnvList = waterEnvMapper.selectByCity(aqi.getName());
					List<TRubbishRecycling> rubbishList = rubbishMapper.findListByCity(aqi.getName()); 
					List<TChemicalPlant> chemicalList = chemicalMapper.findListByCity(aqi.getName());  
					
					Task2048EstateArea tea = new Task2048EstateArea(executor, tlpList, hourAqi, dayAqi, noiseList, waterEnvList, rubbishList, chemicalList); 
					tlpFutureList.add(executor.submit(tea));
				}
			} 
			
			// 组合nestateList 然后批量更新数据库
			for(Future<List<TLandedProperty>> fut : tlpFutureList){
				while(!fut.isDone()){
					Thread.sleep(1000); 
				}
				nestateList.addAll(fut.get());
			}
			
			int size = 5000; // 单组list大小
			int count = nestateList.size() / size; // TreeMap 的分组数       10008/20 = 500 余 8 
			int count_ = nestateList.size() - count * size; // 余数 
			Map<Integer , List<TLandedProperty>> mapgroup = new TreeMap<Integer , List<TLandedProperty>>();
			for(int i = 0 ; i < count ; i ++){
				mapgroup.put(i , nestateList.subList(i*size , size*(i+1)));
			}
			if(count_ != 0){
				mapgroup.put(count, nestateList.subList(count*size, nestateList.size())); 
			}
			for (Map.Entry<Integer, List<TLandedProperty>> entry : mapgroup.entrySet()) {
				Task2048LandedPropertyUpdate lpu = new Task2048LandedPropertyUpdate(entry.getValue(), lrMapper , landedScoreMapper);
				executor.submit(lpu);
			}
			
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}finally{
			executor.shutdown();  
		}
	}
	
	public void ressssyncEstateLoad(String city){
		List<String> list = new ArrayList<>();
		String begin = "2017-01-01 06:06:08";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try {
			list.add(begin);
			list.add("2017-01-15 06:06:08");
			list.add("2017-01-30 06:06:08");
			list.add("2017-02-01 06:06:08");
			list.add("2017-02-15 06:06:08");
			list.add("2017-02-28 06:06:08");
			list.add("2017-03-01 06:06:08");
			list.add("2017-03-15 06:06:08");
			list.add("2017-03-31 06:06:08");
			list.add("2017-04-01 06:06:08");
			list.add("2017-04-15 06:06:08");
			list.add("2017-04-30 06:06:08");
			list.add("2017-05-01 06:06:08");
			list.add("2017-05-15 06:06:08");
			list.add("2017-05-31 06:06:08");
			list.add("2017-06-01 06:06:08");
			list.add("2017-06-15 06:06:08");
			list.add("2017-06-30 06:06:08");
			list.add("2017-07-01 06:06:08");
			list.add("2017-07-15 06:06:08");
			list.add("2017-07-31 06:06:08");
			list.add("2017-08-01 06:06:08");
			list.add("2017-08-15 06:06:08");
			list.add("2017-08-30 06:06:08");
			list.add("2017-09-01 06:06:08"); 
			
			for(String s : list){
				System.out.println(s + "  数据开始刷新"); 
				this.resyncEstateScoredddddd(sdf.parse(s) , city);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(city +  "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + JSONObject.toJSONString(list)); 
	}
	
	 
	
	private String getNextDate(Date date){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE , 1);   // 把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime();      // 这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		 return formatter.format(date);
	}
	
	private void resyncEstateScoredddddd(Date currentDate , String city_){ 
		List<String> clist = new ArrayList<String>();
		clist.add(city_);
//		clist.add("北京");
//		clist.add("天津");
//		clist.add("深圳");
//		clist.add("唐山");
//		clist.add("廊坊");
//		clist.add("沈阳");
//		clist.add("武汉");
//		clist.add("烟台");
//		clist.add("长沙");
//		clist.add("青岛");
//		clist.add("重庆");
//		clist.add("大连");
//		clist.add("成都"); 
		
		List<Future<CityAqi>> futureList = new ArrayList<Future<CityAqi>>();   
		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0 ; i < clist.size() ; i ++){
			Task1032Aqi taqi = new Task1032Aqi();
	        taqi.setCityAirService(cityAirService);
	        taqi.setCity(clist.get(i));  
	        futureList.add(executor.submit(taqi));
		}

		Map<String , List<TLandedProperty>> map = new TreeMap<String , List<TLandedProperty>>();
		for(String city : clist){  // 默认初始化
			List<TLandedProperty> elist = new ArrayList<TLandedProperty>();
			map.put(city, elist);
		}
//		List<TLandedProperty> estateList = lrMapper.selectAllEstateInfo();
		List<TLandedProperty> estateList = lrMapper.selectAllEstateInfoByCity(city_);
		for(TLandedProperty e : estateList){
			if(map.containsKey(e.getCity())){
				map.get(e.getCity()).add(e);
			}
		}
		
		List<TLandedProperty> nestateList = estateList ;   //new ArrayList<>();
		List<Future<List<TLandedProperty>>> tlpFutureList = new ArrayList<Future<List<TLandedProperty>>>();   
			int size = 5000; // 单组list大小
			int count = nestateList.size() / size; // TreeMap 的分组数       10008/20 = 500 余 8 
			int count_ = nestateList.size() - count * size; // 余数 
			Map<Integer , List<TLandedProperty>> mapgroup = new TreeMap<Integer , List<TLandedProperty>>();
			for(int i = 0 ; i < count ; i ++){
				mapgroup.put(i , nestateList.subList(i*size , size*(i+1)));
			}
			if(count_ != 0){
				mapgroup.put(count, nestateList.subList(count*size, nestateList.size())); 
			}
			for (Map.Entry<Integer, List<TLandedProperty>> entry : mapgroup.entrySet()) {
				Task2048LandedPropertyUpdate lpu = new Task2048LandedPropertyUpdate(entry.getValue(), lrMapper , landedScoreMapper , currentDate);
				executor.submit(lpu);
			}
			
	}
	
	
	
	
	/**
	 * @description:  手动刷新水环境信息 | 接口号 2049 
	 * http://localhost:8080/ddhj/api.htm?apiTarget=2049&api_key=appfamilyhas
	 * @author Yangcl 
	 * @date 2016年11月3日 下午3:07:54 
	 * @version 1.0.0.1
	 */
	public void resyncWaterEnviroment() {
		List<String> stateList = new ArrayList<String>(); // 保存检测站点名称
		String key = "935ddb80b6c973938852bd9e38d1777b";
		String url = "http://web.juhe.cn:8080/environment/water/stateList";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);				 	 
		String responseJson = PureNetUtil.post(url , param);
		if (responseJson != null && !"".equals(responseJson)) {
			JSONObject obj = JSONObject.parseObject(responseJson);
			if(obj.getString("reason").equals("SUCCESSD!") || obj.getInteger("error_code") == 0){
				stateList = JSONObject.parseArray(obj.getString("result"), String.class);
			}
		}
		
		if(stateList != null && stateList.size() != 0){
			key = "935ddb80b6c973938852bd9e38d1777b";
			url = "http://web.juhe.cn:8080/environment/water/state";
			for(String s : stateList){
				param.put("key", key);
				param.put("state", s);
				responseJson = PureNetUtil.get(url , param);
				if (responseJson != null && !"".equals(responseJson)){
					JSONObject obj = JSONObject.parseObject(responseJson);
					if(obj.getString("resultcode").equals("200")){ 
						List<TWaterEnviroment> list_ = new ArrayList<TWaterEnviroment>();
						list_ = JSONObject.parseArray(obj.getString("result"), TWaterEnviroment.class);  
						TWaterEnviroment e = list_.get(0);
						e.setUid(UUID.randomUUID().toString().replace("-", ""));
						e.setCreateTime(new Date());
						e.setUpdateTime(new Date());
						e.setType(1);
						waterEnvMapper.insertSelective(e);
					}
				}
			}
		}
	}
	
	/**
	 * @descriptions 【7*24小时城市天气空气质量预报】
	 * 										A【根据地区编码查询每日AQI 均值】未来7天的数据
	 *								  			B【15天概览天气预报数据】
	 * @param city_ 
	 * @param area_
	 * @param type A SevenAqi| B FifteenWeather
	 * @test http://localhost:8080/ddhj/api.htm?apiTarget=2050&api_key=appfamilyhas
	 * @date 2016年12月7日 下午10:57:16
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject getFutureSevenAqi(String city_ , String area_ , String type){
		JSONObject result = new JSONObject();
		result.put("resultCode", -1);
		String city = StringUtils.substringBefore(city_, "市");
		String area = "";
		if(StringUtils.endsWith(area_, "区")){
			area = StringUtils.substringBefore(area_, "区");
		}else if(StringUtils.endsWith(area_,   "县" )){
			area = StringUtils.substringBefore(area_, "县");
		}else if(StringUtils.endsWith(area_, "市" )){
			area = StringUtils.substringBefore(area_, "市");
		}else if(StringUtils.endsWith(area_, "旗")){
			area = StringUtils.substringBefore(area_, "旗");
		}
		Map<String , String > map = new HashMap<String , String>();
		map.put("tcity", city);
		map.put("tarea", area);
		TCityWeatherForecast cwf = cityWeatherForecastMapper.selectByCityArea(map);
		if(cwf == null){
			result.put("resultMessage", "cwf 为空 | " + city + " | " + area);
			return result;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String today = formatter.format(new Date());
		String url = "";
		String key = "d0949e3362e8a98f426273b7763fec1e";
		Map<String, String> param = new HashMap<String, String>(); 		 
		param.put("key", key);		
		param.put("areaid" , cwf.getAreaid());
		String responseJson = ""; 
		try {
			if(type.equals("A")){
				if(StringUtils.isNotBlank(cwf.getSevenAqi()) && cwf.getSevenAqi().startsWith(today)){
					logger.info("2050接口 type = A 开始读取缓存");
					result.put("resultCode", 1);
					result.put("date", JSONArray.parseArray(StringUtils.substringAfter(cwf.getSevenAqi(), today))); 
				}else{ 
					param.put("startTime" , this.getSpecifyDate(new Date(), 0, "yyyyMMdd"));     // 更新今天的数据
					param.put("endTime" , this.getSpecifyDate(new Date(), 6, "yyyyMMdd")); 
					url = "http://v.juhe.cn/xiangji_weather/aqi_average_byAreaid.php";
					responseJson = PureNetUtil.post(url , param);
					if (responseJson != null && !"".equals(responseJson)) {
						JSONObject obj = JSONObject.parseObject(responseJson);
						if(obj.getString("reason").equals("success")){
							obj = JSONObject.parseObject(obj.getString("result"));
							String sevenAqi = today + obj.getString("series");
							TCityWeatherForecast e = new TCityWeatherForecast();
							e.setId(cwf.getId());
							e.setSevenAqi(sevenAqi); 
							e.setUpdateTime(new Date()); 
							cityWeatherForecastMapper.updateSelective(e);
							result.put("resultCode", 1);
							result.put("date", JSONArray.parseArray(obj.getString("series"))); 
						}
					} 
				}
			}else if(type.equals("B")){
				if(StringUtils.isNotBlank(cwf.getFifteenWeather()) && cwf.getFifteenWeather().startsWith(today)){
					logger.info("2050接口 type = B 开始读取缓存");
					result.put("resultCode", 1);
					result.put("date", JSONArray.parseArray(StringUtils.substringAfter(cwf.getFifteenWeather(), today))); 
				}else{ 
					logger.info("2050接口 type = B 开始请求聚合数据");
					param.put("startTime" , this.getSpecifyDate(new Date(), 0, "yyyyMMdd"));  // 更新今天的数据
					param.put("endTime" , this.getSpecifyDate(new Date(), 13, "yyyyMMdd"));  
					url = "http://v.juhe.cn/xiangji_weather/15_area.php";
					responseJson = PureNetUtil.post(url , param);
					if (responseJson != null && !"".equals(responseJson)) {
						JSONObject obj = JSONObject.parseObject(responseJson);
						if(obj.getString("reason").equals("success")){
							obj = JSONObject.parseObject(obj.getString("result"));
							String fifteenWeather = today + obj.getString("series");
							TCityWeatherForecast e = new TCityWeatherForecast();
							e.setId(cwf.getId());
							e.setFifteenWeather(fifteenWeather); 
							e.setUpdateTime(new Date()); 
							cityWeatherForecastMapper.updateSelective(e);
							result.put("resultCode", 1);
							result.put("date", JSONArray.parseArray(obj.getString("series")));
							logger.info("2050接口 type = B 请求结果：" + fifteenWeather);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return result;
	}
	
	
	/**
	 * @description: 万年历接口 
	 * 
	 * @test http://localhost:8080/ddhj/api.htm?apiTarget=2051&api_key=appfamilyhas  
	 * 	线上访问地址：
	 * 	http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=2051&api_key=appfamilyhas
	 * @author Yangcl 
	 * @date 2016年12月27日 上午10:17:54 
	 * @version 1.0.0.1
	 */
	public JSONObject perpetualCalendar(ServletContext application){
		JSONObject result = new JSONObject();
		result.put("resultCode", 1);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String today = format.format(new Date());
		today = today.split("-")[0] + "-" + Integer.valueOf(today.split("-")[1]) + "-" + Integer.valueOf( today.split("-")[2] );
		JSONObject calendar = null;
		Object o = application.getAttribute(DCacheEnum.perpetual_calendar.toString());  //  null ;
		if(o != null){
			calendar = JSONObject.parseObject(String.valueOf( o ));
		}
		
		if( o == null || StringUtils.isBlank(calendar.getString("date")) || !calendar.getString("date").equals(today) || !calendar.getBoolean("flag")){
			ExecutorService executor = Executors.newCachedThreadPool();
			Task2051Calendar cale_ = new Task2051Calendar(today);
			Future<JSONObject> caleTask =  executor.submit(cale_); 
			
			Task2051Holiday holiday_ = new Task2051Holiday(today);
			Future<JSONObject> holidayTask =  executor.submit(holiday_); 
			
			try {
				JSONObject cale = caleTask.get();
				JSONObject holiday = holidayTask.get();
				executor.shutdown();
				if(cale.getBoolean("flag") && holiday.getBoolean("flag")){
					result.putAll(cale);
					result.putAll(holiday); 
				}else if(cale.getBoolean("flag") && !holiday.getBoolean("flag")){
					result.put("flag", false); 
					result.putAll(cale);
					result.put("holidayName", "近期暂无节日");  //  元旦
					result.put("holidayFestival", "");  // 2017-1-1
					result.put("holidayDesc", "");  // 1月1日放假，1月2日（星期一）补休，共3天。
				}else if(!cale.getBoolean("flag") && holiday.getBoolean("flag")){
					result.put("flag", false); 
					result.putAll(holiday); 
					String today_ = format.format(new Date());
					result.put("avoid", "上班、写代码、做文案、挤公交");  // 开市.纳采.订盟.作灶.造庙.造船.经络
					result.put("animalsYear", "猫");  // 猴
					result.put("weekday", this.weekday(today_));  // 星期六 
					result.put("suit", "看电影、逛街、逗女友、吃大餐");  
					result.put("lunarYear", "东汉末年");  // 丙申年 
					result.put("lunar", "七月初七");  // 十一月廿六
					result.put("date", today_ );  // 2016-12-24 
				} else{
					String today_ = format.format(new Date());
					result.put("flag", false); //  标识字段，如果为false 则代表聚合请求失败，给的是假数据，再次请求的时候会作为判断依据
					result.put("avoid", "上班、写代码、做文案、挤公交");  // 开市.纳采.订盟.作灶.造庙.造船.经络
					result.put("animalsYear", "猫");  // 猴
					result.put("weekday", this.weekday(today_));  // 星期六 
					result.put("suit", "看电影、逛街、逗女友、吃大餐");  //  嫁娶.冠笄.祭祀.祈福.求嗣.雕刻.开光.安香.出行.入学.修造.动土.竖柱.上梁.造屋.起基.安门.出火.移徙.入宅.掘井.造畜椆栖.安葬.破土.除服.成服
					result.put("lunarYear", "东汉末年");  // 丙申年 
					result.put("lunar", "七月初七");  // 十一月廿六
					result.put("date", today_ );  // 2016-12-24 
					result.put("holidayName", "");  //  元旦
					result.put("holidayFestival", "");  // 2017-1-1
					result.put("holidayDesc", "");  // 1月1日放假，1月2日（星期一）补休，共3天。
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.println(result.toJSONString()); 
			application.setAttribute(DCacheEnum.perpetual_calendar.toString(), result.toJSONString());  
		}else{
			return calendar;  // 数据存在直接取缓存中的信息 
		}
		
		return result;
	}
	
	/**
	 * @description: 楼盘分数平局值统计
	 * @test: http://api.sys.ecomapit.com/ddhj/api.htm?apiTarget=2052&api_key=appfamilyhas&apiInput={"city":"北京","type":"year","date":"2017","pageIndex":0,"pageSize":10}
	 * 
	 * @param city "北京"
	 * @param type year：按年份平均|quarter：按季度平均|month：按月份平均  
	 * @param date      2016                     1|2|3|4                        2016-01     
	 * 
	 * @author Yangcl 
	 * @date 2017年3月17日 下午6:33:48 
	 * @version 1.0.0.1
	 */
	public JSONObject landedScoreAverageForJob(String city, String type, String date_ , String year , Integer pageIndex ,  Integer pageSize) {
		JSONObject result = new JSONObject();
		result.put("resultCode", 1);
		try {
			String startTime = "";
			String endTime = "";
			if(type.equals("year")){ 
				startTime = date_ + "-01-01 00:00:00" ;
				endTime = date_ + "-12-31 23:59:59" ; 
			}else if(type.equals("quarter")){
				if(date_.equals("1")){ 
					startTime = year +"-01-01 00:00:00";   
					endTime = year +"-03-31 23:59:59"; 
				}else if(date_.equals("2")){
					startTime = year +"-04-01 00:00:00";   
					endTime = year +"-06-30 23:59:59";  
				}else if(date_.equals("3")){
					startTime = year +"-07-01 00:00:00";   
					endTime = year +"-09-30 23:59:59";  
				}else {
					startTime = year +"-10-01 00:00:00";     
					endTime = year +"-12-31 23:59:59";  
				}
			}else{  // month  
				startTime = date_ + "-01 00:00:00";   
				endTime = this.dateCount(startTime, 1); 
			}
			
			LandedScoreAverageDto dto = new LandedScoreAverageDto(); 	// 保存数据库查询条件
			dto.setCity(city);
			dto.setPageIndex(pageIndex);
			dto.setPageSize(pageSize);
			dto.setStartTime(startTime);
			dto.setEndTime(endTime);

			PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
			List<LandedScoreResult> list = landedScoreMapper.findLandedScoreAverage(dto);
			
			if(list != null && list.size() != 0) {
				result.put("data", list);
			}else {
				result.put("resultCode", Constant.RESULT_NULL);
				result.put("resultMessage", "数据为空");
			}
			PageInfo<LandedScoreResult> page = new PageInfo<LandedScoreResult>(list);
			result.put("data", page);

		} catch (ParseException e) {
			e.printStackTrace();
			result.put("resultCode", -1);  // 系统异常
			result.put("resultMessage", e.getLocalizedMessage());
		}
		return result;
	}
	
	
	/**
	 * @description 根据接口查询条件找出楼盘分数平均值(从年季月三张表中查询静态数据,接口专用)
	 * @author zht 
	 */
	public JSONObject landedScoreAverage(String city, String type, String date_ , String year , Integer pageIndex ,  Integer pageSize) {
		JSONObject result = new JSONObject();
		result.put("resultCode", 1);
			String startTime = "";
			String endTime = "";
			String querydate = "";
			if(type.equals("year")){ 
				querydate = date_;
			}else if(type.equals("quarter")){
				if(date_.equals("1")){ 
					querydate = year + "Q1";
				}else if(date_.equals("2")){
					querydate = year + "Q2";
				}else if(date_.equals("3")){
					querydate = year + "Q3";
				}else {
					querydate = year + "Q4";
				}
			}else{  // month  
				querydate = date_;
			}
			
			LandedScoreAverageDto dto = new LandedScoreAverageDto(); 	// 保存数据库查询条件
			dto.setCity(city);
			dto.setPageIndex(pageIndex);
			dto.setPageSize(pageSize);
			dto.setQuerydate(querydate);

			PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
			List<LandedScoreResult> list = null;
			if(type.equals("year")) { 
				//年
				list = landedScoreMapper.findLandedScoreAverageYear(dto);
			} else if(type.equals("quarter")) {
				//季
				list = landedScoreMapper.findLandedScoreAverageQuarter(dto);
			} else if(type.equals("month")) {
				//月
				list = landedScoreMapper.findLandedScoreAverageMonth(dto);
			}
			
			if(list != null && list.size() != 0) {
				result.put("data", list);
			}else {
				result.put("resultCode", Constant.RESULT_NULL);
				result.put("resultMessage", "数据为空");
			}
			PageInfo<LandedScoreResult> page = new PageInfo<LandedScoreResult>(list);
			result.put("data", page);
		return result;
	}

	
	/**
	 * @描述: 当前月份加减
	 * @作者: Yangcl
	 * @时间: 2015-11-09
	 * @param date 当前日期
	 * @param flag 当前月份加减 1,2,3,-1,-2,-3 等  
	 */
	public String dateCount(String date , int flag) throws ParseException{ 
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-01 00:00:00");  
	    Calendar calendar = Calendar.getInstance();       // 日历对象
	    calendar.setTime(sdf.parse(date));       // 设置当前日期
	    calendar.add(Calendar.MONTH ,  flag); // 月份减一 或 加一  
	    return  sdf.format(calendar.getTime());   
	}
	
	
	/**
	 * @descriptions 重组日期
	 *
	 * @param date 2014-05-08
	 * @date 2016年10月4日 下午10:43:34
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String showWeekday(String date) {
		Date d = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String current = formatter.format(d);
		if(date.equals(current)){
			return "今日@" + this.formateDay(date); 
		}
		
		String[] arr = date.split("-");
		Calendar temp = Calendar.getInstance();
		temp.set(Integer.valueOf(arr[0]) , Integer.valueOf(arr[1]) - 1, Integer.valueOf(arr[2]));
		int x = temp.get(Calendar.DAY_OF_WEEK);
		String str = "";
		switch (x){
			case Calendar.SUNDAY:
				str = "周日";
				break;
			case Calendar.MONDAY:
				str = "周一";
				break;
			case Calendar.TUESDAY:
				str = "周二";
				break;
			case Calendar.WEDNESDAY:
				str = "周三";
				break;
			case Calendar.THURSDAY:
				str = "周四";
				break;
			case Calendar.FRIDAY:
				str = "周五";
				break;
			case Calendar.SATURDAY:
				str = "周六";
				break; 
		}
		
		return str + "@" + this.formateDay(date);
	}
	
	private String weekday(String date) {
		String[] arr = date.split("-");
		Calendar temp = Calendar.getInstance();
		temp.set(Integer.valueOf(arr[0]) , Integer.valueOf(arr[1]) - 1, Integer.valueOf(arr[2]));
		int x = temp.get(Calendar.DAY_OF_WEEK);
		String str = "";
		switch (x){
			case Calendar.SUNDAY:
				str = "星期日";
				break;
			case Calendar.MONDAY:
				str = "星期一";
				break;
			case Calendar.TUESDAY:
				str = "星期二";
				break;
			case Calendar.WEDNESDAY:
				str = "星期三";
				break;
			case Calendar.THURSDAY:
				str = "星期四";
				break;
			case Calendar.FRIDAY:
				str = "星期五";
				break;
			case Calendar.SATURDAY:
				str = "星期六";
				break; 
		}
		
		return str ;
	} 
	
	/**
	 * @descriptions 格式化月份日期为：8.29
	 *
	 * @param date
	 * @return
	 * @date 2016年10月4日 下午10:57:18
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String formateDay(String date){
		String[] arr = date.split("-");
		String month = arr[1];
		String day = arr[2];
		if(StringUtils.startsWith(month, "0")){
			month = month.substring(1, 2);
		}
		if(StringUtils.startsWith(day, "0")){
			day = day.substring(1, 2);
		}
		
		return month + "." + day;
	}
	
	
	/**
	 * @descriptions 重组aqi显示信息
	 *
	 * @param list
	 * @return
	 * @date 2016年10月4日 下午11:18:42
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private List<AqiInfo> initAqiList(List<CityAqiData> list){
		List<AqiInfo> list_ = new ArrayList<>();
		for(CityAqiData d : list ){
			String[] arr = this.showWeekday(d.getDate()).split("@");
			AqiInfo i = new AqiInfo();
			i.setWeek(arr[0]);
			i.setDate(arr[1]);
			i.setLevel(d.getQuality());
			list_.add(i);
		}
		return list_;
	}
	
	/**
	 * @descriptions 根据教授接口返回的综合评分，返回环境等级。
	 *
	 * @param score
	 * @return
	 * @date 2016年10月5日 下午2:06:35
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String scoreLevel(String score_){
		Double score = Double.valueOf(score_);
		String level = "优";
		if(score >= 60 && score <= 80){
			level = "良";
		}else if(score < 60){
			level = "差";
		}
		
		return level;
	}
	
	/**
	 * @descriptions
	 *
	 * @param date
	 * @param num  把日期往后增加 num 天.  整数往后推, 负数往前移动
	 * @param format_ "yyyyMMdd"
	 * @return
	 * @date 2016年12月7日 下午11:31:11
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String getSpecifyDate(Date date , int num , String format_){
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE , num); 
		 date=calendar.getTime();  
		 SimpleDateFormat formatter = new SimpleDateFormat(format_);
		 
		 return formatter.format(date);
	}

	// "yyyyMMdd"
	private String getNextDate(Date date , String formate_){  
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE , 1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat(formate_);
		 
		 return formatter.format(date);
	}


	@Override
	public void jobForStatLandScore() {
		List<String> citys = lrMapper.getAllCity();
		if(citys == null || citys.isEmpty()) {
			return ;
		}
		
		for(String city : citys) {
			//年排行
			String type = "year"; // year：按年份平均|quarter：按季度平均|month：按月份平均
			String date = "2017"; // 2016 1|2|3|4 2016-01
			String year = "";
			Integer pageIndex = 1;
			Integer pageSize = 1000000;
			JSONObject result_ = landedScoreAverageForJob(city, type, date, year, pageIndex, pageSize);
			PageInfo<LandedScoreResult> page = (PageInfo<LandedScoreResult>) result_.get("data");
			if(page.getList() != null && !page.getList().isEmpty()) {
				for(LandedScoreResult result : page.getList()) {
					TLandedScoreJob job = new TLandedScoreJob();
					BeanUtils.copyProperties(result, job);
					job.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
					job.setCreateTime(DateUtil.getSysDateTime());
					job.setCreateUser("sysjob");
					job.setUpdateTime(DateUtil.getSysDateTime());
					job.setUpdateUser("sysjob");
					job.setCity(city);
					job.setQuerydate("2017");
					landedScoreJobMapper.insertSelectiveYear(job);
				}
			}
			
			//季排行
			type = "quarter"; // year：按年份平均|quarter：按季度平均|month：按月份平均
			year = "2017";
//			pageIndex = 1;
//			pageSize = 100000;
			for(int i = 1; i <= 4; i++) {
				date = String.valueOf(i); // 2016 1|2|3|4 2016-01
				result_ = landedScoreAverageForJob(city, type, date, year, pageIndex, pageSize);
				page = (PageInfo<LandedScoreResult>) result_.get("data");
				if(page.getList() != null && !page.getList().isEmpty()) {
					for(LandedScoreResult result : page.getList()) {
						TLandedScoreJob job = new TLandedScoreJob();
						BeanUtils.copyProperties(result, job);
						job.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
						job.setCreateTime(DateUtil.getSysDateTime());
						job.setCreateUser("sysjob");
						job.setUpdateTime(DateUtil.getSysDateTime());
						job.setUpdateUser("sysjob");
						job.setCity(city);
						job.setQuerydate("2017Q" + String.valueOf(i));
						landedScoreJobMapper.insertSelectiveQuarter(job);
					}
				}
			}
			
			//月排行
			type = "month"; // year：按年份平均|quarter：按季度平均|month：按月份平均
			year = "2017";
			pageIndex = 1;
			pageSize = 100000;
			for(int i = 1; i <= 12; i++) {
				date = year + (i<10?"-0":"-") + String.valueOf(i); // 2016 1|2|3|4 2016-01
				result_ = landedScoreAverageForJob(city, type, date, year, pageIndex, pageSize);
				page = (PageInfo<LandedScoreResult>) result_.get("data");
				if(page.getList() != null && !page.getList().isEmpty()) {
					for(LandedScoreResult result : page.getList()) {
						TLandedScoreJob job = new TLandedScoreJob();
						BeanUtils.copyProperties(result, job);
						job.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
						job.setCreateTime(DateUtil.getSysDateTime());
						job.setCreateUser("sysjob");
						job.setUpdateTime(DateUtil.getSysDateTime());
						job.setUpdateUser("sysjob");
						job.setCity(city);
						job.setQuerydate(date);
						landedScoreJobMapper.insertSelectiveMonth(job);
					}
				}
			}
		}
	}
	
	// 1.2那里要么显示普通or公寓or别墅  楼盘信息那里就是显示一个数字 - 程艳
	private String getRjLevel(String str){
		String re = "";
		if(str.equals("普通住宅")){
			re = "住宅@2";
		}else if(str.equals("公寓、普通住宅")){
			re = "公寓@1.6";
		}else if(str.equals("公寓")){
			re = "公寓@0.9";
		}else if(str.equals("别墅")){
			re = "别墅@0.3";
		}else if(str.equals("别墅、普通住宅")){
			re = "别墅@0.5";
		}else if(str.equals("公寓、其它")){
			re = "公寓@1.4";
		}else{
			re = "住宅@2";
		}
		return re;
	}
}


class AqiInfo{
	private String week ;
	private String date;
	private String level;
	
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}


/**
 * @descriptions 环境描述明细
 *
 * @date 2016年10月4日 下午11:31:40
 * @author Yangcl 
 * @version 1.0.1
 */
class EnvInfo{
	private String name;
	private String level;
	private String memo;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
}

/**
 * @descriptions 接口1033 | 响应消息体封装
 *
 * @date 2016年10月5日 下午4:24:51
 * @author Yangcl 
 * @version 1.0.1
 */
class Estate implements Comparable{
	private String name;
	private String distance;
	private String address;
	private String price; // 报告最低价格
	private String number; // 楼盘在数据库里的编号
	private String position;
	private String img;
	private Double score;
	private String updateTime;
	
	public Estate(String name, String distance, String address, String price, String number, String position, String img ,Double score , String updateTime) {
		this.name = name;
		this.distance = distance;
		this.address = address;
		this.price = price;
		this.number = number;
		this.position = position;
		this.img = img;
		this.score = score;
		this.updateTime = updateTime;
	}

	public int compareTo(Object o){  // 分数相同则以距离作为排序依据 
		Estate estate = (Estate) o;
		double score = estate.getScore();
		if(!this.score.equals(score)){
			return this.score.compareTo(score);
		}else{
			return estate.getDistance().compareTo(this.distance); 
		}
	}

	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}


}
 


















