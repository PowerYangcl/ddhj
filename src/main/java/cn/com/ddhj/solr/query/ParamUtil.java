package cn.com.ddhj.solr.query;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.common.params.FacetParams;

import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.util.SolrSearch;

/**
 * solr查询共用部分
 *
 */
public class ParamUtil {

	/***是否有楼盘分值区间查询**/
	public static String setScoreQuery(SolrParams params){

		String price = null;
		if(params.getMinScore() != 0.0 && params.getMaxScore() != 0.0) {
			//按楼盘分值查询
	    	if(params.getMinScore() >= params.getMaxScore()) {
	    		price = "d1:["+params.getMaxScore()+" TO "+params.getMinScore()+"]";
	    	} else {
	    		price = "d1:["+params.getMinScore()+" TO "+params.getMaxScore()+"]";
	    	}
	    }else if(params.getMinScore() == 0.0 && params.getMaxScore() != 0.0) {
    	    price = "d1:["+params.getMinScore()+" TO "+params.getMaxScore()+"]";
	    }else if(params.getMinScore()!=0.0 && params.getMaxScore()==0.0){
    	    price = "d1:["+params.getMaxScore()+" TO "+params.getMinScore()+"]";
	    }
		return price;
	}
	
	
	/**
	 * 参数封装
	 * @param params
	 */
	public static void setQuery(SolrParams params,SolrQuery solrQuery){
		solrQuery.setStart(params.getPageNo());
		solrQuery.setRows(params.getPageSize());
		
//		if(params.getSortType()==1){//按销量排序
//			if(params.getSortFlag()==1){
//				solrQuery.addSort(new SortClause("i2", ORDER.asc));
//        	}else{
//        		solrQuery.addSort(new SortClause("i2", ORDER.desc));
//        	}
//			
//	    }
//	    
//	    if(params.getSortType()==2){//按时间排序
//	    	if(params.getSortFlag()==1){
//				solrQuery.addSort(new SortClause("t1", ORDER.asc));
//        	}else{
//        		solrQuery.addSort(new SortClause("t1", ORDER.desc));
//        	}
//	    }
//	    
//	    if(params.getSortType()==3){//按价格排序
//	    	if(params.getSortFlag()==1){
//				solrQuery.addSort(new SortClause("d2", ORDER.asc));
//        	}else{
//        		solrQuery.addSort(new SortClause("d2", ORDER.desc));
//        	}
//	    }
//	    
//	    if(params.getSortType()==4){//人气排序
//	    	if(params.getSortFlag()==1){
//	    		solrQuery.addSort(new SortClause("d3", ORDER.asc));
//        	}else{
//        		solrQuery.addSort(new SortClause("d3", ORDER.desc));
//        	}
//	    }
//	    
//	    if(params.getSortType()==5){//top50
//	    	if(params.getSortFlag()==1){
//	    		solrQuery.addSort(new SortClause("i4", ORDER.asc));
//        	}else{
//        		solrQuery.addSort(new SortClause("i4", ORDER.desc));
//        	}
//	    }
	    
//	    solrQuery.setFacet(true); // 设置使用facet
//	    solrQuery.setFacetMinCount(1); // 设置facet最少的统计数量
//	    solrQuery.setFacetLimit(params.getPageSize()); // facet结果的返回行数
//	    solrQuery.addFacetField(SolrSearch.l4.toString()); // facet的字段
//	    solrQuery.addFacetField(SolrSearch.s3.toString()); // facet的字段
//	    solrQuery.setFacetSort(FacetParams.FACET_SORT_COUNT);
	}
	
	
}
