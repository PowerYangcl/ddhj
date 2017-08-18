package cn.com.ddhj.service.search;

import java.util.List;

import cn.com.ddhj.dto.SearchLandPropertyDto;
import cn.com.ddhj.solr.data.SolrData;

public interface ISearchService {
	/**
	 * 搜索楼盘接口
	 * @param dto
	 * @author zht
	 * @return
	 */
	List<SolrData> search(SearchLandPropertyDto dto, String userToken);
}
