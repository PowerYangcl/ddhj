package cn.com.ddhj.mapper.search;

import java.util.List;

import cn.com.ddhj.dto.search.TSearchHistoryDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.search.TSearchHistory;

public interface TSearchHistoryMapper extends BaseMapper<TSearchHistory, TSearchHistoryDto> {
	List<TSearchHistory> getSearchHistoryByUserCode(String userCode);
	
	Integer delSearchHistoryByUserCode(String userCode);
}
