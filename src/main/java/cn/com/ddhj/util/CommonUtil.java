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
}