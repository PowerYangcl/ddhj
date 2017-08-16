package cn.com.ddhj.solr.query;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import com.qhsy.solr.dao.impl.TextGeneral;
import com.qhsy.solr.entity.SolrParams;
import com.qhsy.solr.tools.SolrSearch;
import com.qhsy.solr.util.StringUtil;

/****
 * 品牌查询
 *
 */
public class BrandQuery{

	public static  void setQuery(SolrQuery solrQuery,SolrParams params){
		
		/**是否有价格查询**/
		String price = ParamUtil.setQuery(params);
		
		if(params.getBrand()!=null && params.getBrand().equals("brand") && StringUtils.isEmpty(params.getBrandKeyWord())){
			
			if(price!=null){
				solrQuery.setQuery( SolrSearch.s3+":"+params.getKeyWord()+" AND "+price);
			}else{
				solrQuery.setQuery( SolrSearch.s3+":"+params.getKeyWord());
			}
				solrQuery.set("defType","edismax");
			    solrQuery.set("qf","s3^100");
			    
		}else{
			 String keyWord = StringUtil.StringFilter(params.getKeyWord());
			 String str = TextGeneral.getTextGeneral(keyWord);
		        
			 if(price!=null){
				solrQuery.setQuery("("+price+") AND  ("+SolrSearch.v1 +":"+str+" OR "+ SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+" ) AND ("+SolrSearch.s3+":"+params.getBrandKeyWord() +")");
			 }else{
				solrQuery.setQuery(" ("+ SolrSearch.v1 +":"+str +" OR "+SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+") AND ( "+SolrSearch.s3+":"+params.getBrandKeyWord() +")");
			 }
				solrQuery.set("defType","edismax");
			    solrQuery.set("qf","v1^100.0 l2^40 l4^40 s3^40 l8^10.0");
			
		}
		
		
		solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
		ParamUtil.setQuery(params,solrQuery);
		
		
	}

}
