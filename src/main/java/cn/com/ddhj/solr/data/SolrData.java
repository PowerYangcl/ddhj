package cn.com.ddhj.solr.data;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 声明：Field注解为solrj创建索引库的时间会校验的字段  
 *     k开头的属性名代表为唯一的主键Key
 *     s开头的属性名代表为字符串
 *     i开头的属性名代表为int类型的数字
 *     d开头的属性名代表为双精度的数字
 *     t开头的属性名代表为日期字段   日期格式为 yyyy-MM-dd'T'HH:mm:ss'Z'
 *     l开头的属性名代表为集合类  只支持List<String> 这样的类型  泛型只支持string
 *     
 * 备注：这样设计是为了在不重新solr集群服务器的前提下，可以任意的添加无数个索引分片，为
 *     后期的搜索提供方便
 * 
 * solr索引库创建数据源 <br/>
 * 描述 : SolrData所有的字段为SolrInputDocument提供数据的封装 <br/>
 */
public class SolrData {
	/**
	 * solr唯一标示key
	 */
	@Field
	private String k1 ;//对应楼盘编号landCode
	/***
	 * s开头的为字符串
	 */
	@Field
	private String s1;	//所在城市
	@Field
	private String s2;	//楼盘名称
	@Field
	private String s3;	//地址
	@Field
	private String s4;  //经度
	@Field
	private String s5;  //纬度

	
	/**
	 * i开头的数字
	 */
	@Field
	private int i1;    //i1
	
	/**
	 * d开头的为双精度数字
	 */
	@Field
	private Double d1; //楼盘的综合评分

	/**
	 * t为日期格式
	 */
	@Field
	private Date t1;
	/**
	 * l为list集合里面封装String字符串
	 */
//	@Field
	private String l1;  //主图
//	@Field
//	private List<String> l2; //l2对应一级分类的编号
//	@Field
//	private List<String> l3; //l3对应二级分类的名称
//	@Field
//	private List<String> l4; //l4对应二级分类的编号
//	@Field
//	private List<String> l5;
//	@Field
//	private List<String> l6;
//	@Field
//	private List<String> l7;
//	@Field
//	private List<String> l8;
	/**
	 * 该字段不会创建索引
	 * 是为后期搜索返回多少条提供set方法
	 */
	private long counts=0;
//	/**
//	 * 该字段不会创建索引
//	 * 是为后期搜索返回分类集合提供set方法
//	 */
//	private List<String> categoryName=new ArrayList<String>();
//	/**
//	 * 该字段不会创建索引
//	 * 是为后期搜索返回品牌集合提供set方法
//	 */
//	private List<String> brandName=new ArrayList<String>();
//	
//	
//	/**
//	 * @return the categoryName
//	 */
//	public List<String> getCategoryName() {
//		return categoryName;
//	}
//	/**
//	 * @param categoryName the categoryName to set
//	 */
//	public void setCategoryName(List<String> categoryName) {
//		this.categoryName = categoryName;
//	}
//	/**
//	 * @return the brandName
//	 */
//	public List<String> getBrandName() {
//		return brandName;
//	}
//	/**
//	 * @param brandName the brandName to set
//	 */
//	public void setBrandName(List<String> brandName) {
//		this.brandName = brandName;
//	}
	/**
	 * @return the k1
	 */
	public String getK1() {
		return k1;
	}
	/**
	 * @param k1 the k1 to set
	 */
	public void setK1(String k1) {
		this.k1 = k1;
	}
	/**
	 * @return the s1
	 */
	public String getS1() {
		return s1;
	}
	/**
	 * @param s1 the s1 to set
	 */
	public void setS1(String s1) {
		this.s1 = s1;
	}
	/**
	 * @return the s2
	 */
	public String getS2() {
		return s2;
	}
	/**
	 * @param s2 the s2 to set
	 */
	public void setS2(String s2) {
		this.s2 = s2;
	}
	/**
	 * @return the s3
	 */
	public String getS3() {
		return s3;
	}
	/**
	 * @param s3 the s3 to set
	 */
	public void setS3(String s3) {
		this.s3 = s3;
	}
	/**
	 * @return the s4
	 */
	public String getS4() {
		return s4;
	}
	/**
	 * @param s4 the s4 to set
	 */
	public void setS4(String s4) {
		this.s4 = s4;
	}
	/**
	 * @return the s5
	 */
	public String getS5() {
		return s5;
	}
	/**
	 * @param s5 the s5 to set
	 */
	public void setS5(String s5) {
		this.s5 = s5;
	}
	
	/**
	 * @return the i1
	 */
	public int getI1() {
		return i1;
	}
	/**
	 * @param i1 the i1 to set
	 */
	public void setI1(int i1) {
		this.i1 = i1;
	}
	
	/**
	 * @return the d1
	 */
	public Double getD1() {
		return d1;
	}
	/**
	 * @param d1 the d1 to set
	 */
	public void setD1(Double d1) {
		this.d1 = d1;
	}
	
	/**
	 * @return the t1
	 */
	public Date getT1() {
		return t1;
	}
	/**
	 * @param t1 the t1 to set
	 */
	public void setT1(Date t1) {
		this.t1 = t1;
	}
	
	/**
	 * @return the counts
	 */
	public long getCounts() {
		return counts;
	}
	/**
	 * @param counts the counts to set
	 */
	public void setCounts(long counts) {
		this.counts = counts;
	}
	public String getL1() {
		return l1;
	}
	public void setL1(String l1) {
		this.l1 = l1;
	}
}
