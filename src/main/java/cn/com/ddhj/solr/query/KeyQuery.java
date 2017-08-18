package cn.com.ddhj.solr.query;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;

import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.util.SolrSearch;
import cn.com.ddhj.solr.util.StringUtil;

/***
 * 关键词查询
 *
 */
public class KeyQuery {

	public static void setQuery(SolrQuery solrQuery,SolrParams params){
		String keyWord = StringUtil.StringFilter(params.getKeyWord());
		String city = StringUtil.StringFilter(params.getCity());
		
		if(StringUtils.isNotBlank(keyWord) || StringUtils.isNotBlank(city)) {
			/**是否有楼盘分值查询**/
			String price = ParamUtil.setScoreQuery(params);
			
			if(keyWord.startsWith("lp")) {
				//keyWord以lp开头,应该为楼盘编号
				if(price != null) {
					solrQuery.setQuery("("+ SolrSearch.k1 +":"+keyWord+") AND ("+price+")");
				}else {
					solrQuery.setQuery(SolrSearch.k1+":"+keyWord);
				}
				solrQuery.set("defType","edismax");
			    solrQuery.set("qf","k1^100.0");
				
			} else if(StringUtil.isNumber(keyWord)) {
				//keyWord纯数字
				if(price != null) {
					solrQuery.setQuery("("+ SolrSearch.k1 +":lp"+keyWord+" OR "+SolrSearch.s2+":*"+keyWord+"*) AND ("+price+")");
				}else {
					solrQuery.setQuery(SolrSearch.k1+":lp"+keyWord+" OR "+SolrSearch.s2+":*"+keyWord+"*");
				}
				solrQuery.set("defType","edismax");
			    solrQuery.set("qf","k1^100.0 s2^100.0");
			} else if(StringUtil.IsLetter(keyWord)){
				/**全为字母   只查询商品名称   商品名称拼音  和 拼音首字母   品牌   关键词**/
				String keyLower = keyWord.toLowerCase();
				String keyUpper = keyWord.toUpperCase();
				
				if(price!=null){
					solrQuery.setQuery("("+SolrSearch.s1+":" + keyLower + " OR " + SolrSearch.s2 + ":*"+keyLower+"*) AND ("+price+")" );
				}else{
					solrQuery.setQuery(SolrSearch.s1 + ":" + keyLower + " OR " + SolrSearch.s2 + ":*" + keyLower + "*");
				}	
				solrQuery.set("defType","edismax");
				solrQuery.set("qf","s1^100.0 s2^100");
			/**字母数字组合   必须以字母开头  关键词  商品名称**/
			}else if(StringUtil.IsString(keyWord)){
//				String query = "";
//				if(keyWord.startsWith("si") || keyWord.startsWith("sf") || keyWord.startsWith("hf") || keyWord.startsWith("pf")) {
//					//按供应商查询商品.add by zht
//					if(keyWord.equals("sf03100063")) {
//						//用马甲公司搜索的LD商品,把该公司转成LD的编号
//						keyWord = "si2003";
//					}
//					query = "(" + SolrSearch.s16+":"+keyWord + ")";
//					solrQuery.set("defType","edismax");
//					solrQuery.set("qf","s16^100.0");
//				} else {
//					query = "(" + SolrSearch.s1+":*"+keyWord+"* OR "+SolrSearch.l8+":" + keyWord + ")";
//					solrQuery.set("defType","edismax");
//					solrQuery.set("qf","s1^100.0 l8^10");
//				}
//				
//				if(StringUtils.isNotBlank(price)) {
//					query += " AND ("+price+")";
//				}
//				solrQuery.setQuery(query);
			} else {
				if(price!=null){
					//solrQuery.setQuery("("+keyValue+" OR "+SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.s3+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+" OR "+SolrSearch.s5+":*"+keyWord+"* ) AND ("+price+")");
					/**分字**/
//					solrQuery.setQuery("("+SolrSearch.v1+":"+str+" OR "+SolrSearch.l8+":"+str+" OR "+SolrSearch.s3+":"+str+" OR "+SolrSearch.l2+":"+str+" OR "+SolrSearch.l4+":"+keyWord+" OR "+SolrSearch.s5+":*"+keyWord+"*) AND ("+price+")");
					/**分词**/
					if(StringUtils.isNotBlank(keyWord) || StringUtils.isNotBlank(city)) {
						solrQuery.setQuery("("+SolrSearch.s1+":"+city+" AND "+SolrSearch.s2+":*"+keyWord+"*) AND ("+price+")");
					} else if(StringUtils.isBlank(keyWord)) {
						solrQuery.setQuery("("+SolrSearch.s1+":"+city+") AND ("+price+")");
					}
					
				}else{
					//solrQuery.setQuery(keyValue+" OR "+SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.s3+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord+" OR "+SolrSearch.s5+":*"+keyWord+"*");
//					solrQuery.setQuery(SolrSearch.v1+":"+str+" OR "+SolrSearch.l8+":"+keyWord+" OR "+SolrSearch.s3+":"+keyWord+" OR "+SolrSearch.l2+":"+keyWord+" OR "+SolrSearch.l4+":"+keyWord +" OR "+SolrSearch.s5+":*"+keyWord+"*");
					if(StringUtils.isNotBlank(keyWord) || StringUtils.isNotBlank(city)) {
						solrQuery.setQuery(SolrSearch.s1+":"+city+" AND "+SolrSearch.s2+":*"+keyWord+"*");
					} else if(StringUtils.isBlank(keyWord)) {
						solrQuery.setQuery(SolrSearch.s1+":"+city);
					}

				}
				solrQuery.set("defType","edismax");
//					solrQuery.set("qf","v1^100.0 s3^40 l2^40 l4^40 l8^10.0 ");
				solrQuery.set("qf","s1^100.0 s2^40");
			}
//			solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
			ParamUtil.setQuery(params,solrQuery);
		}
	}
}
