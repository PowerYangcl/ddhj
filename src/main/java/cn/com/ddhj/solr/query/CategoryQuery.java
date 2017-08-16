package cn.com.ddhj.solr.query;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import com.qhsy.solr.dao.impl.TextGeneral;
import com.qhsy.solr.tools.SolrSearch;

import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.util.StringUtil;

/***
 * 分类查询
 *
 */
public class CategoryQuery {

	public static void setQuery(SolrQuery solrQuery,SolrParams params){

		/**是否有价格查询**/
		String price = ParamUtil.setQuery(params);
		
		/**当只传递category值时 默认是分类搜索   当getCategerkeyWord不为空       是搜索一级和二级分类 **/
		if(params.getCategory()!=null && params.getCategory().equals("category") && StringUtils.isEmpty(params.getCategerkeyWord())){
			
			if(price!=null){
				solrQuery.setQuery("("+SolrSearch.l2+":"+params.getKeyWord()+" OR "+SolrSearch.l4+":"+params.getKeyWord()+") AND ("+price+")");
			}else{
				solrQuery.setQuery(SolrSearch.l2+":"+params.getKeyWord()+" OR "+SolrSearch.l4+":"+params.getKeyWord());
			}
				solrQuery.set("defType","edismax");
			    solrQuery.set("qf","l2^50.0 l4^50.0");
		
		}else{
			
			String keyWord = StringUtil.StringFilter(params.getKeyWord());
			String str = TextGeneral.getTextGeneral(keyWord);
			
			if(price!=null){
				solrQuery.setQuery("("+price+") AND ("+SolrSearch.v1 +":"+str+" OR "+SolrSearch.s3+":"+keyWord + " OR "+ SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+" ) AND ( "+SolrSearch.l2+":"+params.getCategerkeyWord()+" OR "+SolrSearch.l4+":"+params.getCategerkeyWord()+" )");
			}else{
				solrQuery.setQuery(" ("+ SolrSearch.v1 +":"+str +"OR "+SolrSearch.s3+":"+keyWord +" OR "+SolrSearch.l8+":"+keyWord+ " OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+") AND ( "+SolrSearch.l2+":"+params.getCategerkeyWord()+" OR "+SolrSearch.l4+":"+params.getCategerkeyWord()+")");
			}
			solrQuery.set("defType","edismax");
			    solrQuery.set("qf","v1^100.0 l2^40 l4^40 s3^40 l8^10.0");	
		}
		
		solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
		ParamUtil.setQuery(params,solrQuery);
		
	}
}
