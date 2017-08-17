package cn.com.ddhj.solr.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.solr.data.SolrData;
import cn.com.ddhj.util.DateUtil;

/***
 * 该方法只为使用solrj创建索引库提供基础数据
 * 描述：该方法为solr提供数据的方法封装，该方法查询出来的所有的楼盘数据添加到索引库
 */
public class SolrDataUtil {

	public List<SolrData> addSolrData(String lpCode, TLandedPropertyMapper lrMapper){
		List<SolrData> list = new ArrayList<SolrData>();

		List<TLandedProperty> landList = getAllLandProperty(lpCode, lrMapper);
		if(landList == null || landList.isEmpty()) {
			return null;
		}
		
		for(TLandedProperty land : landList){
			SolrData sd = new SolrData();
			//楼盘编号
			String landCode = land.getCode();
			sd.setK1(landCode.toLowerCase());
			//所在城市
			String city = land.getCity();
			if(StringUtils.isNotBlank(city))
				sd.setS1(city);
			//楼盘名称
			String title = land.getTitle();
			if(StringUtils.isNotBlank(title))
				sd.setS2(title.toLowerCase());
			//地址
			String address = land.getAddressFull();
			if(StringUtils.isNotBlank(address))
				sd.setS3(address.toLowerCase());
			//经度
			String lat = land.getLat();
			if(StringUtils.isNotBlank(lat))
				sd.setS4(lat);
			//纬度
			String lng = land.getLng();
			if(StringUtils.isNotBlank(lng))
				sd.setS5(lng);
			//楼盘综合评分
			Double score = land.getScore();
			if(score != null)
				sd.setD1(score);
			//主图
			String images = land.getImages();
			if(StringUtils.isNotBlank(images))
				sd.setL1(images);
			String updateTime = land.getUpdateTime();
			if(StringUtils.isNotBlank(updateTime)) {
				Date up;
				try {
					up = DateUtil.strToDate(updateTime);
					sd.setT1(up);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			list.add(sd);
		}
		return list;
		
	}
	
	
	
	/**
	 * 查询当前单一或者所有楼盘
	 */
	public List<TLandedProperty> getAllLandProperty(String lpCode, TLandedPropertyMapper lrMapper) {
		List<TLandedProperty> list = new ArrayList<TLandedProperty>();
		if(StringUtils.isBlank(lpCode)) {
			list = lrMapper.findTLandedPropertyAll();
		} else {
			TLandedProperty tp = lrMapper.selectByCode(lpCode);
			list.add(tp);
		}
		return list;
	}
	
}
