package cn.com.ddhj.solr.query;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;

import com.qhsy.solr.entity.SolrParams;
import com.qhsy.solr.tools.SolrSearch;

/***
 * 联想词query拼接
 *
 */
public class LxcQuery {

	public static void setQuery(SolrQuery solrQuery,SolrParams params){
		/**联想出商品词**/
		if(params.getLxc() != null && params.getLxc().equals("lxc")){
			solrQuery.set("q", SolrSearch.s1+":"+params.getKeyWord()+"* OR "+SolrSearch.p1+":"+params.getKeyWord()+" OR "+SolrSearch.p2+":"+params.getKeyWord().replace(" ", ""));	
			solrQuery.set("fl", SolrSearch.s1.toString());
			solrQuery.setStart(1); 
			solrQuery.setRows(params.getPageSize()); 
			solrQuery.set("defType","edismax");
			solrQuery.set("bf","abs(i1)^40");
			solrQuery.set("wt","json");
		/**联想出商品名称**/
		}else if(params.getLxc() != null && params.getLxc().equals("lxcProductName")){
			solrQuery.set("qt", "/terms"); 
			solrQuery.set("terms.prefix", params.getKeyWord());		
			solrQuery.set("terms.fl", SolrSearch.s9.toString());			
			solrQuery.set("terms.mincount", "1");  
			solrQuery.set("terms.maxcount", "100");  
			solrQuery.set("terms.limit", params.getPageSize());  
			solrQuery.set("terms.raw", "true");  
			solrQuery.set("terms.sort", "count");  
			solrQuery.set("wt","json");
		}
		
		
		/**都在买**/
		if(params.getTop50()!=null && params.getTop50().equals("top50")){
			solrQuery.setQuery("*:*");
			solrQuery.addSort(new SortClause(SolrSearch.i4.toString(), ORDER.desc));
			solrQuery.setStart(params.getPageNo());
			solrQuery.setRows(params.getPageSize());
		}
		
	}
}
