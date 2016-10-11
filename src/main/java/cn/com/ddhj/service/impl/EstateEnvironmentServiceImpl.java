package cn.com.ddhj.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.CityAqi;
import cn.com.ddhj.dto.CityAqiData;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.mapper.report.TReportMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.model.report.TReport;
import cn.com.ddhj.result.estateInfo.EData;
import cn.com.ddhj.service.ICityAirService;
import cn.com.ddhj.service.IEstateEnvironmentService;
import cn.com.ddhj.service.IEstateInfoService;
import cn.com.ddhj.service.ILongitudeLatitudeService;
import cn.com.ddhj.service.IWeatherAreaSupportService;
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
//			JSONObject addr = llService.getCurrentPositionInfo(lng, lat, "2");
//			JSONObject obj = wasService.getWeatherWithPosition(lng, lat);
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
			
			JSONObject addr = posTask.get();
			JSONObject obj = weaTask.get();
	        executor.shutdown();
long end = System.currentTimeMillis();
System.out.println("1025接口 - 聚合接口耗时：" + (end - start) + " 毫秒"); 
	        			
			
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
				
				String score = this.getDoctorScore(hourAqi, hourAqi, "0.5", "0.5");
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
	public JSONObject apiEnvScore(String position , String city){
		JSONObject result = new JSONObject();
		if(StringUtils.isAnyBlank(position , city)){
			result.put("resultCode", -1); 
			result.put("resultMessage", "参数不得为空"); 
			return result;
		}
		try {
//			JSONObject weather = cityAirService.getWeatherInfo(city);   // TODO 耗时接口
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
	        
	        JSONObject weather = weaTask.get();
	        CityAqi aqi = aqiFuture.get();
	        executor.shutdown();
long end = System.currentTimeMillis();
System.out.println("1032号接口 - 聚合接口耗时：" + (end - start) + " 毫秒"); 
	        
	        
//	        CityAqi aqi = cityAirService.getCityAqi(city);
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
			JSONObject estate = this.estateList(position, "1" , "1"); // 获取楼盘信息
			if(estate.getString("code").equals("1")) {
				List<EData> estateList = JSONArray.parseArray(estate.getString("list"), EData.class);
				try {
					if(estateList != null && estateList.size() > 0){
						EData e = estateList.get(0);               // 因为精度是1米 所以只有一条记录，且就是这个楼盘
						result.put("name", e.getTitle()); // 位置名称
						if(StringUtils.isNoneBlank(e.getGreeningRate())){
							if(Integer.valueOf(e.getGreeningRate().split("%")[0])/100 < 0.5){   // 潜在的异常点
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
			
start = System.currentTimeMillis();
			String score = this.getDoctorScore(hourAqi, hourAqi, greeningRate, volumeRate);
end = System.currentTimeMillis();
System.out.println("1032号接口 - 教授接口耗时：" + (end - start) + " 毫秒"); 
	        
	        
			result.put("score", score); // 环境综合评分
			result.put("level", this.scoreLevel(score));  // 环境等级
			if(aqi.getList() != null){
				result.put("AQIList", this.initAqiList(aqi.getList()));  
			}
			
			
			List<EnvInfo> envList = new ArrayList<>();
			EnvInfo air = new EnvInfo();
			air.setName("空气");
			if(aqi.getEntity() != null){
				air.setMemo(aqi.getEntity().getAQI());
				air.setLevel(aqi.getEntity().getQuality()); 
			}
			envList.add(air);
			EnvInfo wea = new EnvInfo();
			wea.setName("天气");
			wea.setMemo(weather.getString("info"));
			wea.setLevel(weather.getString("wind")); 
			envList.add(wea);
			// 数据模糊，暂时写死
			EnvInfo gar = new EnvInfo();
			gar.setName("垃圾");
			gar.setMemo("2Km以外");
			gar.setLevel("较远"); 
			envList.add(gar);
			EnvInfo water = new EnvInfo();
			water.setName("水质");
			water.setMemo("色度低"); 
			water.setLevel("优良");  
			envList.add(water);
			EnvInfo noise = new EnvInfo();
			noise.setName("噪音");
			noise.setMemo("2Km以外"); 
			noise.setLevel("I类/优");  
			envList.add(noise);
			result.put("detailList", envList);  // 环境明细
			
			result.put("level", this.scoreLevel(score));  // 环境等级
			result.put("tiptitle", weather.getString("des"));  // 提示标题
			
			result.put("resultCode", 0); 
			result.put("resultMessage", "SUCCESS"); 
//			System.out.println("1032接口：" + result); 
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
	public JSONObject apiEstateList(String position , String city , String page , String count){
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
			
			JSONObject eListInfo = this.estateList(position, page , count); // 经纬度周边地产信息
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
					List<TReport> rList  = reportMapper.findPriceByCode(lpcodes);
					
					List<Estate> projectList = new ArrayList<>();
					// 开始组建数据
					for(EData e : list){
						String position_ = e.getLat() + "," + e.getLng();
						String distance = this.getDistance(lat, lng, e.getLat(), e.getLng());
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
						projectList.add(new Estate(e.getTitle() , distance , e.getAddressFull() , price , lpcode, position_, img) );		
					}
					if(projectList.size() != 0){
						result.put("resultCode", 0);
						result.put("resultMessage", "SUCCESS");
						result.put("projectlist", projectList); 
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
		
//		System.out.println(result);  
		return  result; 
	}
	
	private JSONObject estateList(String position , String page , String count){
		return  this.estateList(position, page , count , "2000");  
	}
	
	
	private JSONObject estateList(String position , String page , String count , String radius){
		String[] arr = position.split(",");
		String lat = arr[0];
		String lng = arr[1]; 
		
		return  estateService.estateInfoList(lng, lat, page , count ,radius); 
	}
	
	/**
	 * @descriptions 根据两个位置的经纬度，来计算两地的距离（单位为KM）
	 *
	 * @param lat1_ 用户经度
	 * @param lng1_ 用户纬度
	 * @param lat2_ 商家经度
	 * @param lng2_ 商家纬度
	 * @return
	 * @date 2016年10月7日 下午10:25:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
    public String getDistance(String lat1_, String lng1_, String lat2_, String lng2_) {
    	double earthRadius = 6378.137; // 地球半径
    	
        Double lat1 = Double.parseDouble(lat1_);
        Double lng1 = Double.parseDouble(lng1_);
        Double lat2 = Double.parseDouble(lat2_);
        Double lng2 = Double.parseDouble(lng2_); 
         
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * earthRadius;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
         
        return distanceStr;
    }
    
    private double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
    
	
	/**
	 * @descriptions 获取教授数学模型综合评分|噪音和水质暂时默认为2
	 *
	 * @param a hourAQI
	 * @param b dayAQI   @教授的接口文档有问题，暂时放一个参数
	 * @param c  l  生态状况:绿化率指数 0.5或1  【地产检索接口->"greeningRate":"50%"】
	 * @param d  j 生态状况:容积率指数  0~9之间 【地产检索接口->"volumeRate":"0.46"】
	 * @return
	 * @date 2016年10月4日 下午10:18:29
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	private String getDoctorScore(String a ,String b ,String c , String d){
		String url = "http://123.56.169.49:8338/environment/servlet/environmentZHInterface";
		JSONObject obj = null;
		Map<String, String> param = new HashMap<String, String>();
		param.put("hourAQI", a);		 
		param.put("dayAQI", b);	 
		param.put("s", "2");						 
		param.put("z1", "2");							 
		param.put("z2", "2");							 
		param.put("l", c);	
		param.put("j", d);	
		param.put("t", "2");
		
		String result = PureNetUtil.get(url, param);
		if (result != null && !"".equals(result)) {
			obj = JSONObject.parseObject(result); 
			if(obj.getString("flag").equals("true")){
				return obj.getString("message");
			}
		}
		return "0";
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
class Estate{
	private String name;
	private String distance;
	private String address;
	private String price; // 报告最低价格
	private String number; // 楼盘在数据库里的编号
	private String position;
	private String img;
	
	public Estate(String name, String distance, String address, String price, String number, String position, String img) {
		this.name = name;
		this.distance = distance;
		this.address = address;
		this.price = price;
		this.number = number;
		this.position = position;
		this.img = img;
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
}



/**
	
	// 收录此代码 
 	重组日期
	public String showWeekday2(String date) {
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
		
		return str;
	}
	
	
	
 */


















