package cn.com.ddhj.solr.query;

import org.apache.solr.client.solrj.SolrQuery;

import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.util.Base64Util;
import cn.com.ddhj.solr.util.StringUtil;

/***
 * solr查询参数封装类
 *
 */
public class SolrParamsQuery {

	/**
	 * 拿到solr查询方法  
	 * @param params  为前端查询条件参数对象
	 * @return
	 */
	public static SolrQuery getParams(SolrParams params){
		SolrQuery solrQuery = new SolrQuery();
		
		/**如果搜索关键词为空    直接返回        即使点击分类或者品牌该字段都会有值   不存在无值的情况**/
		if(params.getKeyWord()==null){
			return solrQuery;
		}
		
		/**如果搜索为特殊字符直接返回**/
		if(!StringUtil.IsSpecial(params.getKeyWord())){
			return solrQuery;
		}
		/**如果搜索为英文字符将里面包含的空格转义    暂时发现品牌中如果有空格的话会造成这个问题，防止其他未知情况发生，所有都在此转义，待优化ing。。。。**/
		if(!StringUtil.IsLetter(params.getKeyWord())){
			params.setKeyWord(params.getKeyWord().replace(" ", "\\ "));
		}
		/**如果搜索关键词用base64加密   解密**/
		if(params.getBase64()!= null && params.getBase64().equals("base64")){
			params.setKeyWord(Base64Util.getFromBASE64(params.getKeyWord()));
		}
		
		/**搜索不管为什么  都先转化为小写**/
		params.setKeyWord(params.getKeyWord().toLowerCase());
		
//		/**联想出词  或 联想商品名称  或  top50**/
//		if((params.getLxc() != null && params.getLxc().equals("lxc")) || (params.getLxc() != null && params.getLxc().equals("lxcProductName")) || (params.getTop50()!=null && params.getTop50().equals("top50"))){
//			LxcQuery.setQuery(solrQuery,params);
//		}
//		
//		/**按分类搜索**/
//		if(params.getCategory()!=null && params.getCategory().equals("category")){
//			CategoryQuery.setQuery(solrQuery, params);
//		}
//		
//		/**按品牌搜索**/
//		if(params.getBrand()!=null && params.getBrand().equals("brand")){
//			BrandQuery.setQuery(solrQuery, params);
//		}
		
		/**关键字拆字搜索**/
		if(params.getKey()!=null &&params.getKey().equals("key")){
			KeyQuery.setQuery(solrQuery, params);
		}
		return solrQuery;
	}
}
