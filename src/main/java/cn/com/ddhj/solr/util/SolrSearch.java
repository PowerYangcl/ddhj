package cn.com.ddhj.solr.util;

/**
 * solr检索字段
 *
 */
public enum SolrSearch {
	/***
	 * 以下字段为solr  schema.xml中
	 * field 字段的定义  具体每个字段代表什么意识 
	 * 在创建索引库的时间需要自己定义
	 */
	k1,
	s1,
	s2,
	s3,
	s4,
	s5,
	s6,
	s7,
	s8,
	s9,
	s10,
	s11,
	s12,
	s13,
	s14,
	s15,
	s16,
	i1,
	i2,
	i3,
	i4,
	i5,
	d1,
	d2,
	d3,
	d4,
	t1,
	l1,
	l2,
	l3,
	l4,
	l5,
	l6,
	l7,
	l8,
	/************以下字段为copyField字段**************/
	/**
	 * solr默认的拆字  字段
	 */
	v0,
	/**
	 * solr默认的拆字  值来源于s1字段
	 */
	v1,
	/**
	 * solr默认的拆字 字段  值来源于s5字段
	 */
	v2,
	/**
	 * solr默认的拆字  字段 
	 */
	v3,
	/**
	 * solr默认的拆字  字段   值来源于s8字段
	 */
	v4,
	/**
	 * ik分词  字段    值来源于s1
	 */
	ik1,
	/**
	 * 拼音字段  值来源于s1
	 */
	p1,
	/**
	 * 拼音首字母字段  值来源于s1
	 */
	p2,
	
	
}
