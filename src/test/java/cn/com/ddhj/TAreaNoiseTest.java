package cn.com.ddhj;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.model.TAreaNoise;
import cn.com.ddhj.service.IEstateInfoService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TAreaNoiseTest extends BaseTest {

	@Autowired
	private ITAreaNoiseMapper noiseMapper;
	@Resource
	private IEstateInfoService estateService; 
	
	@Test
	public void insert() {
//		List<TAreaNoise> list = this.readExcel("D:/g.xlsx");
//		for(TAreaNoise e : list){
//			noiseMapper.insertSelective(e);
//		}
		String lat1 = "39.754462"; 
		String lng1 = "116.767682";   
		JSONObject object = estateService.estateInfoList(lng1, lat1, "1" , "10" ,"10000");  
		 
		 
		List<TAreaNoise> list = noiseMapper.selectByArea("北京");
		System.out.println(JSONObject.toJSONString(list)); 
	}
	
	public static void main(String[] args) {
		Double lat1 = 39.754462; 
		Double lng1 = 116.767682;  
		Double lat2 = 39.774501 ; 
		Double lng2 = 116.353456; 
		
		
		
		
		String dist = getDistance(lat1, lng1, lat2, lng2) ;
		
		System.out.println(dist); 
	}
  
	/**
	 * @descriptions 根据两个位置的经纬度，来计算两地的距离（单位为KM）
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
    public static String getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
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
         
        return distanceStr;
    }
    
    private static double rad(double d) { 
        return d * Math.PI / 180.0; 
    }
	
	
	
	
	
	
	
	private List<TAreaNoise> readExcel(String fileName) {
		List<TAreaNoise> list = new ArrayList<TAreaNoise>();

		boolean isE2007 = false; // 判断是否是excel2007格式
		if (fileName.endsWith("xlsx"))
			isE2007 = true;
		try {
			InputStream input = new FileInputStream(fileName); // 建立输入流
			Workbook wb = null;
			// 根据文件格式(2003或者2007)来初始化
			if (isE2007)
				wb = new XSSFWorkbook(input);
			else
				wb = new HSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0); // 获得第一个表单
			Iterator<Row> rows = sheet.rowIterator(); // 获得第一个表单的迭代器
			while (rows.hasNext()) {
				Row row = rows.next(); // 获得行数据
				TAreaNoise e = new TAreaNoise();
				e.setArea(String.valueOf(row.getCell(0))); 
				e.setFlag(3); 
				e.setName(String.valueOf(row.getCell(1))); 
				e.setLat(Double.valueOf(String.valueOf(row.getCell(3))));
				e.setLng(Double.valueOf(String.valueOf(row.getCell(4)))); 
				if(row.getCell(2) != null)
					e.setAddress(String.valueOf(row.getCell(2)));  
				e.setLevel("III类");  
				list.add(e);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		String json = JSON.toJSONString(list);

		System.out.println(json); 
		return list;
	}
}
















