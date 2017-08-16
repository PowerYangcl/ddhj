package cn.com.ddhj.solr.util;

import org.apache.commons.lang.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.common.params.FacetParams;

import com.qhsy.solr.dao.impl.TextGeneral;

import cn.com.ddhj.solr.data.SolrParams;

/**
 * 查询参数封装<br/>
 * 描述 : 参数封装类<br/>
 * 创建时间 : 2015-09-01 17:44:31 <br/>
 * 文件版本 : V1.0 <br/>
 * 修改历史 : V1.0
 */
public class SolrParamsUtil {

	/**
	 * 参数封装方法
	 * @param params 实体类对象
	 * @return SolrQuery查询参数字符串
	 */
	public SolrQuery getParams(SolrParams params){
		SolrQuery solrQuery = new SolrQuery();
		
		/**如果没有搜索关键词 直接返回为空**/
		if(params.getKeyWord()==null){
			return solrQuery;
		}
		
		/**参数base64加密**/
		if(params.getBase64()!= null && params.getBase64().equals("base64")){
			params.setKeyWord(Base64Util.getFromBASE64(params.getKeyWord()));
		}
		params.setKeyWord(params.getKeyWord().toLowerCase());
		
		/**如果搜索为特殊字符直接返回**/
		if(!StringUtil.IsSpecial(params.getKeyWord())){
			return solrQuery;
		}
		
		/**联想出商品词**/
		if(params.getLxc() != null && params.getLxc().equals("lxc")){
			solrQuery.set("q", "s1:"+params.getKeyWord().replace(" ", "")+"* OR p1:"+params.getKeyWord().replace(" ", "")+" OR p2:"+params.getKeyWord().replace(" ", ""));	
			solrQuery.set("fl", "s1");
			solrQuery.setStart(1); 
			solrQuery.setRows(params.getPageSize()); 
			solrQuery.set("defType","edismax");
			solrQuery.set("bf","abs(i1)^40");
			solrQuery.set("wt","json");
			return solrQuery;
		/**联想出商品名称**/
		}else if(params.getLxc() != null && params.getLxc().equals("lxcProductName")){
			solrQuery.set("qt", "/terms"); 
			solrQuery.set("terms.prefix", params.getKeyWord());		
			solrQuery.set("terms.fl", "s1");			
			solrQuery.set("terms.mincount", "1");  
			solrQuery.set("terms.maxcount", "100");  
			solrQuery.set("terms.limit", params.getPageSize());  
			solrQuery.set("terms.raw", "true");  
			solrQuery.set("terms.sort", "count");  
			solrQuery.set("wt","json");
			return solrQuery;
		}
		
		
		/**都在买**/
		if(params.getTop50()!=null && params.getTop50().equals("top50")){
			solrQuery.setQuery("*:*");
			solrQuery.addSort(new SortClause("i1", ORDER.desc));
			solrQuery.setStart(params.getPageNo());
			solrQuery.setRows(params.getPageSize());
			return solrQuery;
		}
		
		/**按分类搜索**/
		if(params.getCategory()!=null && params.getCategory().equals("category")){
			String price = "";
			if(params.getStartPrice()!=0.0 && params.getEndPrice()!=0.0){//按价格查询
		    	if(params.getStartPrice()>=params.getEndPrice()){
		    		price = "d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    	}else{
		    		price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    	}
		    }else if(params.getStartPrice()==0.0 && params.getEndPrice()!=0.0){
		    	    price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    }else if(params.getStartPrice()!=0.0 && params.getEndPrice()==0.0){
		    	    price = "  d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    }
			
			/**当只传递category值时 默认是分类搜索   当getCategerkeyWord不为空的时间说明是商品名称加分类 搜索**/
			if(params.getCategory()!=null && params.getCategory().equals("category") && StringUtils.isEmpty(params.getCategerkeyWord())){
				if(price!=null && !price.equals("") && price.length()>0){
					solrQuery.setQuery("(l1:*"+params.getKeyWord()+"* OR l2:*"+params.getKeyWord()+"* l3:*"+params.getKeyWord()+"* OR l4:*"+params.getKeyWord()+"*) AND ("+price+")");
				}else{
					solrQuery.setQuery("l1:*"+params.getKeyWord()+"* OR l2:*"+params.getKeyWord()+"* l3:*"+params.getKeyWord()+"* OR l4:*"+params.getKeyWord()+"*");
				}
				solrQuery.set("defType","edismax");
				solrQuery.set("qf","l1^50.0 l2^50.0 l3^50.0 l4^50.0");	
				solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
			}else{
				String keyWord = StringUtil.StringFilter(params.getKeyWord());
				// String str = TextGeneral.getTextGeneral(keyWord);
			        
				if(price!=null && !price.equals("") && price.length()>0){
					solrQuery.setQuery("("+price+")   AND (l2:*"+params.getCategerkeyWord()+"* AND l4:*"+params.getCategerkeyWord()+"* )");
				}else{
					solrQuery.setQuery(" l2:*"+keyWord+"* AND l4:*"+params.getCategerkeyWord()+"* ");
				}
				solrQuery.set("defType","edismax");
				solrQuery.set("qf","v1^100.0 l2^40 l4^40 v2^10.0");	
				solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
			}
			setSolrQuery(params,solrQuery);
			solrQuery.setStart(params.getPageNo());
			solrQuery.setRows(params.getPageSize());
			/*solrQuery.set("defType","edismax");
			solrQuery.set("qf","l1^50.0 l2^50.0 l3^50.0 l4^50.0");	
			solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");*/
			return solrQuery;
		}
		
		/**按品牌搜索**/
		if(params.getBrand()!=null && params.getBrand().equals("brand")){
			
			String price = "";
			if(params.getStartPrice()!=0.0 && params.getEndPrice()!=0.0){//按价格查询
		    	if(params.getStartPrice()>=params.getEndPrice()){
		    		price = "d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    	}else{
		    		price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    	}
		    }else if(params.getStartPrice()==0.0 && params.getEndPrice()!=0.0){
		    	    price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    }else if(params.getStartPrice()!=0.0 && params.getEndPrice()==0.0){
		    	    price = "  d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    }
			
			if(params.getBrand()!=null && params.getBrand().equals("brand") && StringUtils.isEmpty(params.getBrandKeyWord())){
				if(price!=null && !price.equals("") && price.length()>0){
					solrQuery.setQuery("(s2:*"+params.getKeyWord().replace(" ","*")+"* OR s3:*"+params.getKeyWord().replace(" ","*")+"*) AND ("+price+")");
				}else{
					solrQuery.setQuery("s2:*"+params.getKeyWord().replace(" ","*")+"* OR s3:*"+params.getKeyWord().replace(" ","*")+"*");
				}
				solrQuery.set("defType","edismax");
				solrQuery.set("qf","s2^100 s3^100");
				solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
			}else{
				String keyWord = StringUtil.StringFilter(params.getKeyWord());
				 String str = TextGeneral.getTextGeneral(keyWord);
			        
				if(price!=null && !price.equals("") && price.length()>0){
					solrQuery.setQuery("(v1:"+str+" OR v2:"+str+" OR l2:*"+keyWord+"* OR l4:*"+keyWord+"* OR v4:"+str+") AND ("+price+")   AND  (s3:*"+params.getBrandKeyWord()+"*) ");
				}else{
					solrQuery.setQuery("(v1:"+str+" OR v2:"+str+" OR l2:*"+keyWord+"* OR l4:*"+keyWord+"* OR v4:"+str+")  AND  (s3:*"+params.getBrandKeyWord()+"*) ");
				}
				solrQuery.set("defType","edismax");
				solrQuery.set("qf","v1^100.0 l2^40 l4^40 v2^10.0 s3^10");
				solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
				
			}
			setSolrQuery(params,solrQuery);
			solrQuery.setStart(params.getPageNo());
			solrQuery.setRows(params.getPageSize());
			/*solrQuery.set("defType","edismax");
			solrQuery.set("qf","s2^100 s3^100");
			solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");*/
			return solrQuery;
		}
		
		/**关键字拆字搜索**/
		if(params.getKey()!=null &&params.getKey().equals("key")){
			String keyWord = StringUtil.StringFilter(params.getKeyWord());
			String price = "";
			if(params.getStartPrice()!=0.0 && params.getEndPrice()!=0.0){//按价格查询
		    	if(params.getStartPrice()>=params.getEndPrice()){
		    		price = "d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    	}else{
		    		price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    	}
		    }else if(params.getStartPrice()==0.0 && params.getEndPrice()!=0.0){
		    	    price = "d2:["+params.getStartPrice()+" TO "+params.getEndPrice()+"]";
		    }else if(params.getStartPrice()!=0.0 && params.getEndPrice()==0.0){
		    	    price = "  d2:["+params.getEndPrice()+" TO "+params.getStartPrice()+"]";
		    }
			/**全为数字**/
			if(StringUtil.isNumber(keyWord)){
				if(price!=null && !price.equals("") && price.length()>0){
					
					solrQuery.setQuery("(k1:*"+params.getKeyWord()+"* OR l1:*"+params.getKeyWord()+"* OR l3:*"+params.getKeyWord()+"* OR s2:*"+params.getKeyWord()+"* OR s5:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*) AND ("+price+")");
				
				}else{
					
					solrQuery.setQuery("k1:*"+params.getKeyWord()+"* OR l1:*"+params.getKeyWord()+"* OR l3:*"+params.getKeyWord()+"* OR s2:*"+params.getKeyWord()+"* OR s5:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*");
			
				}
			/**全为字母**/	
			}else if(StringUtil.IsLetter(keyWord)){
				String keyLower = params.getKeyWord().toLowerCase();
				String keyUpper = params.getKeyWord().toUpperCase();
				if(price!=null && !price.equals("") && price.length()>0){
					
					solrQuery.setQuery("(v1:*"+keyLower+"* OR s3:"+keyLower+" OR l2:"+keyLower+" OR l4:"+keyLower+" OR k1:"+keyUpper+" OR p1:*"+keyLower+"* OR p2:*"+keyUpper+"* OR s5:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*) AND ("+price+")");
				
				}else{
				
					solrQuery.setQuery("v1:*"+keyLower+"* OR s3:"+keyLower+" OR l2:"+keyLower+" OR l4:"+keyLower+" OR k1:"+keyUpper+" OR p1:*"+keyLower+"* OR p2:*"+keyUpper+"* OR s5:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*");
				
				}	
			/**字母数字组合   必须以字母开头**/
			}else if(StringUtil.IsString(keyWord)){
				if(price!=null && !price.equals("") && price.length()>0){
					
					solrQuery.setQuery("(k1:*"+params.getKeyWord()+"* OR s5:*"+params.getKeyWord()+"* OR s3:"+params.getKeyWord()+" OR s6:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*) AND ("+price+")");
					
				}else{
				
					solrQuery.setQuery("k1:*"+params.getKeyWord()+"* OR s5:*"+params.getKeyWord()+"* OR s3:"+params.getKeyWord()+" OR s6:*"+params.getKeyWord()+"* OR s8:*"+keyWord+"*");
				
				}	
			}else{
		       /* StringBuilder str = new StringBuilder();
		        for(int i=0;i<keyWord.length();i++){
		        	 str.append(keyWord.charAt(i)).append(" ");
		        }*/
		       String str = TextGeneral.getTextGeneral(keyWord);
		        
		       if(str==null){
		    	   return solrQuery;
		       }
				if(price!=null && !price.equals("") && price.length()>0){
					solrQuery.setQuery("(v1:"+str+" OR v2:"+keyWord+" OR s3:"+keyWord+" OR l2:"+keyWord+" OR l4:"+keyWord+" OR v4:"+str+") AND ("+price+")");
				}else{
					solrQuery.setQuery("v1:"+str+" OR v2:"+keyWord+" OR s3:"+keyWord+" OR l2:"+keyWord+" OR l4:"+keyWord+" OR v4:"+str);
				}
				
				
			}
			
			setSolrQuery(params,solrQuery);
		    solrQuery.set("defType","edismax");
			solrQuery.set("qf","v1^100.0 l2^40 l4^40 v2^10.0 ");	
			solrQuery.set("bf", "sum(abs(i1))^1500.0  sum(sqrt(log(ms(t1))))^10.0");
		    solrQuery.setStart(params.getPageNo()); // query的开始行数(分页使用)
		    solrQuery.setRows(params.getPageSize()); // query的返回行数(分页使用)
			
		}
		
		
		
		return solrQuery;
	}
	
	
	/**
	 * 参数封装
	 * @param params
	 */
	public void setSolrQuery(SolrParams params,SolrQuery solrQuery){
		if(params.getSortType()==1){//按销量排序
			if(params.getSortFlag()==1){
				solrQuery.addSort(new SortClause("i2", ORDER.asc));
        	}else{
        		solrQuery.addSort(new SortClause("i2", ORDER.desc));
        	}
			
	    }
	    
	    if(params.getSortType()==2){//按时间排序
	    	if(params.getSortFlag()==1){
				solrQuery.addSort(new SortClause("t1", ORDER.asc));
        	}else{
        		solrQuery.addSort(new SortClause("t1", ORDER.desc));
        	}
	    }
	    
	    if(params.getSortType()==3){//按价格排序
	    	if(params.getSortFlag()==1){
				solrQuery.addSort(new SortClause("d2", ORDER.asc));
        	}else{
        		solrQuery.addSort(new SortClause("d2", ORDER.desc));
        	}
	    }
	    
	    if(params.getSortType()==4){//人气排序
	    	if(params.getSortFlag()==1){
	    		solrQuery.addSort(new SortClause("d3", ORDER.asc));
        	}else{
        		solrQuery.addSort(new SortClause("d3", ORDER.desc));
        	}
	    }
	    
	    
	    solrQuery.setFacet(true); // 设置使用facet
	    solrQuery.setFacetMinCount(1); // 设置facet最少的统计数量
	    solrQuery.setFacetLimit(params.getPageSize()); // facet结果的返回行数
	   // solrQuery.addFacetField("l3"); // facet的字段
	    solrQuery.addFacetField("l4"); // facet的字段
	  //  solrQuery.addFacetField("s2"); // facet的字段
	    solrQuery.addFacetField("s3"); // facet的字段
	    solrQuery.setFacetSort(FacetParams.FACET_SORT_COUNT);
	}
	
}
