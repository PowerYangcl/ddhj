package cn.com.ddhj.service.search;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.search.SearchLandPropertyDto;
import cn.com.ddhj.model.search.TSearchHistory;
import cn.com.ddhj.model.search.TSearchHotWord;
import cn.com.ddhj.result.search.SearchResult;
import cn.com.ddhj.solr.data.SolrData;

public interface ISearchService {
	/**
	 * 搜索楼盘接口
	 * @param dto
	 * @author zht
	 * @return
	 */
	List<SolrData> search(SearchLandPropertyDto dto, String userToken);
	
	/**
	 * 返回用户搜索历史
	 * @param userToken
	 * @author stockwyz
	 * @return
	 */
	SearchResult<TSearchHistory> getSearchHistoryByUserToken(String userToken);
	
	/**
	 * 删除用户搜索历史记录
	 * @param userToken
	 * @return
	 */
	JSONObject delSearchHistoryByUserToken(String userToken);
	
	/**
	 * 返回系统热搜关键词
	 * @author stockwyz
	 * @return
	 */
	SearchResult<TSearchHotWord> getSearchHotWord(String city);
	
	/**
	 * 定时统计搜索热词
	 */
	void statSearchHotWord();
}
