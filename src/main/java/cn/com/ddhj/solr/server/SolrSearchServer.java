package cn.com.ddhj.solr.server;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrQuery;

import cn.com.ddhj.solr.client.SolrClientAgent;
import cn.com.ddhj.solr.data.SolrData;

/**
 * solr搜索server层 <br/>
 * 描述 : solr搜索核心业务处理类 <br/>
 * 文件版本 : V1.0 <br/>
 * 修改历史 : V1.0
 */
public class SolrSearchServer {
	
	/**
	* @param keyWord  搜索关键字
    * @return
    *    返回值Map集合里面有两个1：product为查询出来的数据信息
    *                     2：count为查询信息条数总和  用于分页时使用
    */
	public Map<String,Object> getSolrSearchData(SolrQuery solrQuery,String sellerCode){
		return new SolrClientAgent().getSearchData(solrQuery,sellerCode);
	}
	
	/**
	 * 联想词 
	 * 描述：该方法联想的为商品名称，不是单个的词
	 * @param params  参数封装类  
	 * @param sellerCode  索引库分片
	 * @return
	 */
	public List<String> getSuggestProductName(SolrQuery solrQuery,String sellerCode){
		return new SolrClientAgent().getSuggestProductName(solrQuery,sellerCode);
	}
	/**
	 * 联想词 可根据拼音 拼音首字母联想
	 * 描述：该方法联想的为词组，不是商品名称
	 * @param params  参数封装类  
	 * @param sellerCode  索引库分片
	 * @return
	 */
	public List<String> getSuggestLxc(SolrQuery solrQuery,String sellerCode){
		return new SolrClientAgent().getSuggestLxc(solrQuery,sellerCode);
	}
	/**
	 * 全量更新solr索引库
	 * @param product    数据封装类
	 * @param sellerCode 索引库分片
	 */
	public void addSolrDataIndex(List<SolrData> data,String sellerCode){
		new SolrClientAgent().addSolrIndexList(data, sellerCode);
	}
	/**
	 * 修改或添加一条索引信息
	 * @param product    数据封装类
	 * @param sellerCode 索引库分片
	 */
	public void addSolrDataOne(SolrData data,String sellerCode){
		new SolrClientAgent().addSolrIndexOne(data, sellerCode);
	}
	/**
	 * 删除全部的索引的库
	 * @param sellerCode 索引库分片
	 */
	public void delSolrDataIndex(String sellerCode){
		new SolrClientAgent().delSolrIndexAll(sellerCode);
	}
	/**
	 * 删除单个索引库数据
	 * @param peodutCode  唯一编号ID
	 * @param sellerCode  索引库分片
	 */
	public void delSolrDataOne(String productCode,String sellerCode){
		new SolrClientAgent().delSolrIndexOne(productCode, sellerCode);
	}
}
