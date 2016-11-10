package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.CityAqi;
import cn.com.ddhj.dto.CityAqiData;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.mapper.TChemicalPlantMapper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.TRubbishRecyclingMapper;
import cn.com.ddhj.mapper.TWaterEnviromentMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.model.TChemicalPlant;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.TRubbishRecycling;
import cn.com.ddhj.model.TWaterEnviroment;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.IEstateEnvironmentService;
import cn.com.ddhj.service.IEstateInfoService;
import cn.com.ddhj.service.ILongitudeLatitudeService;
import cn.com.ddhj.service.IWeatherAreaSupportService;
import cn.com.ddhj.util.CommonUtil;
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
	private TWaterEnviromentMapper waterEnvMapper;  // 水质量信息
	
	
	/**
	 * @descriptions 地区环境接口|1025
	 *
	 * @param position
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @return
	 * @date 2016年10月7日 下午8:19:29
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public JSONObject apiAreaEnv(String position , String city) {
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
	        Future<Map<String , String>> rubFuture = executor.submit(rub);
			
			JSONObject addr = posTask.get();
			JSONObject obj = weaTask.get();
	        executor.shutdown();
long end = System.currentTimeMillis();
logger.info("1025接口 - 聚合接口耗时：" + (end - start) + " 毫秒"); 
	        			
			
			if(addr.getString("code").equals("1") && StringUtils.isNotBlank(addr.getString("business")) ){
				result.put("name", addr.getString("business").split(",")[0]); // 位置名称
			}else{
				result.put("resultCode", -1); 
				result.put("resultMessage", "附近未发现可用位置信息"); 
				return result;
			}
			
			if(obj.getString("resultcode").equals("200")){
				JSONObject result_ = JSONObject.parseObject(obj.getString("result")); 
				JSONObject today = JSONObject.parseObject(result_.getString("today")); 
				String weather = today.getString("weather");
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
				String volumeRate = "0.4";	   // 如下条件不满足则用默认值
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
				String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , wFuture.get().get("s") , z1 , z2);
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
				
				result.put("weather", weather); 
				result.put("windpower", windpower); 
				result.put("tempmin", tempmin); 
				result.put("tempmax", tempmax); 
				result.put("tiptitle", tiptitle); 
				result.put("tiptime", tiptime); 
				result.put("resultCode", 0); 
				result.put("resultMessage", "SUCCESS"); 
				return result;
			}else{
				result.put("resultCode", -1); 
				result.put("resultMessage", "天气查询接口返回空"); 
				return result;
			}
		} catch (Exception e) {
			result.put("resultCode", -1); 
			result.put("resultMessage", "平台内部错误"); 
			return result;
		}
	}
	
	
	/**
	 * @descriptions 环境综合评分接口|1032
	 *
	 * @param position|经纬度逗号分隔
	 * @param title | 地产名称
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @return
	 * @date 2016年10月4日 下午5:30:13
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */ 
	public JSONObject apiEnvScore(String position , String city , String radius){
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
	        Future<Map<String , String>> rubFuture = executor.submit(rub);
	        
	        Task1032Estate est = new Task1032Estate();
	        est.setPosition(position);
	        est.setRadius(radius);
	        est.setEstateService(estateService);
	        Future<List<EnvInfo>> estFuture = executor.submit(est);
	        
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
			String volumeRate = "0.4";	   // 如下条件不满足则用默认值
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
			String score = DoctorScoreUtil.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate , wFuture.get().get("s") , z1 , z2);
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
			water.setMemo("溶解氧"); 
			water.setLevel(wFuture.get().get("level"));
			envList.add(water);
			
			// 新版需求
			EnvInfo land = new EnvInfo();    // 土壤
			land.setName("土壤");
			land.setMemo("无污染");
			land.setLevel("良"); 
			
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
			result.put("resultCode", 0); 
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
	 *
	 * @param position|经纬度逗号分隔，纬度在前经度在后
	 * @param city |当前城市名称 如：北京，注意不是北京市
	 * @param page | 当前第几页，每页数据10条
	 * @return
	 * @date 2016年10月4日 下午5:30:13
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */ 
	public JSONObject apiEstateList(String position , String city , String page , String count , String radius){
		JSONObject result = new JSONObject();
		if(StringUtils.isAnyBlank(position , page)){
			result.put("resultCode", -1); 
			result.put("resultMessage", "参数不得为空"); 
			return result;
		}
		
		String[] arr = position.split(",");
		String lat = arr[0];
		String lng = arr[1];
		
		JSONObject addr = llService.getCurrentPositionInfo(lng, lat, "2");     // TODO 耗时接口  
		if(addr.getString("code").equals("1")){
			result.put("currname", addr.getString("address")); // 当前位置信息
			
			JSONObject eListInfo = this.estateList(position, page , count , radius); // 经纬度周边地产信息
			if(eListInfo.getString("code").equals("1")){
				List<EData> list = JSONArray.parseArray(eListInfo.getString("list") , EData.class); // 获取地产信息列表
				if(list != null && list.size() > 0){  
					List<String> titles = new ArrayList<>();
					for(int i = 0 ; i < list.size() ; i ++){
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
					
					
					List<Estate> projectList = new ArrayList<>();
					// 开始组建数据
					for(EData e : list){
						String position_ = e.getLat() + "," + e.getLng();
						String distance = CommonUtil.getDistance(lat, lng, e.getLat(), e.getLng());
						String lpcode = "";
						String price = "￥200";
						if(lpList != null && lpList.size() > 0){
							for(TLandedProperty p : lpList){
								String lpname = p.getTitle();
								if(e.getTitle().equals(lpname)){
									lpcode = p.getCode();
									if(rList != null && rList.size() > 0){
										for(TReport r : rList){
											if(r.getHousesCode().equals(lpcode)){
												price = String.valueOf(r.getPrice());
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
						projectList.add(new Estate(e.getTitle() , distance , e.getAddressFull() , price , lpcode, position_, img , e.getScore()) );		
					}

					Collections.sort(projectList); 
					Collections.reverse(projectList); 
					for(Estate e : projectList){
						logger.info(e.getScore() + "|" + e.getDistance()); 
					}
					if(projectList.size() != 0){
						result.put("resultCode", 0);
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
	 *  http://localhost:8080/ddhj/api.htm?apiTarget=2048&api_key=appfamilyhas
	 * @author Yangcl 
	 * @date 2016年10月18日 下午4:29:29 
	 * @version 1.0.0.1
	 */
	public void resyncEstateScore(){ 
		List<String> clist = new ArrayList<String>();
		clist.add("北京");
		clist.add("天津");
		
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
				Task2048LandedPropertyUpdate lpu = new Task2048LandedPropertyUpdate(entry.getValue(), lrMapper);
				executor.submit(lpu);
			}
			
		} catch (InterruptedException | ExecutionException e1) {
			e1.printStackTrace();
		}finally{
			executor.shutdown();  
		}
	}
	
	
	/**
	 * @description:  手动刷新水环境信息 | 接口号 2049 
	 * http://localhost:8080/ddhj/api.htm?apiTarget=2049&api_key=appfamilyhas
	 * @author Yangcl 
	 * @date 2016年11月3日 下午3:07:54 
	 * @version 1.0.0.1
	 */
	public void resyncWaterEnviroment(){
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
		if(score > 120 && score < 200){
			level = "良";
		}else if(score > 200 && score <300){
			level = "中";
		}else if(score > 300){
			level = "差";
		}
		
		return level;
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
	
	public Estate(String name, String distance, String address, String price, String number, String position, String img ,Double score) {
		this.name = name;
		this.distance = distance;
		this.address = address;
		this.price = price;
		this.number = number;
		this.position = position;
		this.img = img;
		this.score = score;
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


 


















