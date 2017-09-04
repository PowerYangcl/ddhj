package cn.com.ddhj.mapper.search;

import java.util.List;

import cn.com.ddhj.dto.search.TSearchHotWordDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.search.TSearchHotWord;

public interface TSearchHotWordMapper extends BaseMapper<TSearchHotWord, TSearchHotWordDto> {
	List<TSearchHotWord> getSearchHotWord(String city);
	
	void deleteAll();
}
