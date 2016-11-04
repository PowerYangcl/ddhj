package cn.com.ddhj.util;

import java.text.DecimalFormat;

/**
 * 
 * 类: CommonUtil <br>
 * 描述: 公共方法类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月4日 上午9:24:27
 */
public class CommonUtil {

	/**
	 * 
	 * 方法: getDistanceFromLL <br>
	 * 描述: 经纬度换算距离 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月4日 上午9:25:47
	 * 
	 * @param lon1
	 * @param lat1
	 * @param lon2
	 * @param lat2
	 * @return
	 */
	public static Double getDistanceFromLL(double lat1, double lon1, double lat2, double lon2) {
		double ew1, ns1, ew2, ns2;
		double dx, dy, dew;
		double distance;
		// 角度转换为弧度
		ew1 = lon1 * Constant.DEF_PI180;
		ns1 = lat1 * Constant.DEF_PI180;
		ew2 = lon2 * Constant.DEF_PI180;
		ns2 = lat2 * Constant.DEF_PI180;
		// 经度差
		dew = ew1 - ew2;
		// 若跨东经和西经180 度，进行调整
		if (dew > Constant.DEF_PI)
			dew = Constant.DEF_2PI - dew;
		else if (dew < -Constant.DEF_PI)
			dew = Constant.DEF_2PI + dew;
		dx = Constant.DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
		dy = Constant.DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
		// 勾股定理求斜边长
		distance = Math.sqrt(dx * dx + dy * dy);
		return Double.valueOf(new DecimalFormat(".00").format(distance));
	}

	/**
	 * 
	 * 方法: getAround <br>
	 * 描述: 根据经纬度和距离获取经纬度范围的最大值和最小值 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月6日 上午12:22:17
	 * 
	 * @param lat
	 * @param lng
	 * @param raidus
	 * @return
	 */
	public static double[] getAround(double lat, double lng, int raidus) {
		Double latitude = lat;
		Double longitude = lng;
		Double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;
		Double dpmLat = 1 / degree;
		Double radiusLat = dpmLat * raidusMile;
		Double minLat = latitude - radiusLat;
		Double maxLat = latitude + radiusLat;
		Double mpdLng = degree * Math.cos(latitude * (Constant.DEF_PI / 180));
		Double dpmLng = 1 / mpdLng;
		Double radiusLng = dpmLng * raidusMile;
		Double minLng = longitude - radiusLng;
		Double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}
	
	/**
	 * @descriptions 根据两个位置的经纬度，来计算两地的距离（单位为米）|返回具体的米数
	 *
	 * @param lat1  用户纬度
	 * @param lng1 用户经度
	 * @param lat2 商家纬度
	 * @param lng2 商家经度
	 * @return
	 * @date 2016年10月7日 下午10:25:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
	public static Integer getMeterDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
    	double earthRadius = 6378.137; // 地球半径 
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * earthRadius;
        distance = Math.round(distance * 10000) / 10;   
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
        
        return Integer.valueOf(distanceStr);
    }
	
	/**
	 * @descriptions 根据两个位置的经纬度，来计算两地的距离（单位为米）
	 *
	 * @param lat1  用户纬度
	 * @param lng1 用户经度
	 * @param lat2 商家纬度
	 * @param lng2 商家经度
	 * @return
	 * @date 2016年10月7日 下午10:25:46
	 * @author Yangcl 
	 * @version 1.0.0.1
	 */
    public static Integer getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
    	double earthRadius = 6378.137; // 地球半径 
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double difference = radLat1 - radLat2;
        double mdifference = rad(lng1) - rad(lng2);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(difference / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(mdifference / 2), 2)));
        distance = distance * earthRadius;
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
        
        return Integer.valueOf(distanceStr) * 1000;
    }
    
 
    public static String getDistance(String lat1_, String lng1_, String lat2_, String lng2_) {
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
        System.out.println(Math.round(distance * 10000) / 10 + " 米"); 
        distance = Math.round(distance * 10000) / 10000;
        String distanceStr = distance+"";
        distanceStr = distanceStr. substring(0, distanceStr.indexOf("."));
         
        return distanceStr;
    }
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
}





















