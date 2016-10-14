package cn.com.ddhj;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;

import cn.com.ddhj.base.BaseTest;
import cn.com.ddhj.mapper.ITAreaNoiseMapper;
import cn.com.ddhj.model.TAreaNoise;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring.xml", "classpath:spring/spring-mybatis.xml" })
public class TAreaNoiseTest extends BaseTest {

	@Autowired
	private ITAreaNoiseMapper noiseMapper;

	@Test
	public void insert() {
		List<TAreaNoise> list = this.readExcel("D:/g.xlsx");
		for(TAreaNoise e : list){
			noiseMapper.insertSelective(e);
		}
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
















