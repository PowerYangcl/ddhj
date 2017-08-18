package cn.com.ddhj.service.impl.search;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.ddhj.dto.SearchLandPropertyDto;
import cn.com.ddhj.dto.search.TSearchHistoryDto;
import cn.com.ddhj.mapper.search.ISearchHistoryMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.search.TSearchHistory;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.service.impl.BaseServiceImpl;
import cn.com.ddhj.service.search.ISearchService;
import cn.com.ddhj.solr.data.SolrData;
import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.query.SolrParamsQuery;
import cn.com.ddhj.solr.server.SolrSearchServer;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;

@Service
public class ISearchServiceImpl implements ISearchService {
	@Autowired
	private ISearchHistoryMapper mapper;
	@Autowired
	private TUserMapper userMapper;
	
	@Override
	public List<SolrData> search(SearchLandPropertyDto dto, String userToken) {
		//楼盘搜索接口
		Map<String,Object> map = null;
		List<SolrData> list = null;
		
		SolrParams solrparams = new SolrParams();
		if(StringUtils.isNotBlank(dto.getKeyWord()))
			solrparams.setKeyWord(dto.getKeyWord());
		solrparams.setPageSize(dto.getPageSize());
		solrparams.setPageNo(dto.getPageIndex());
		solrparams.setSortType(dto.getSortType());
		solrparams.setSortFlag(dto.getSortFlag());
		solrparams.setKey("key");
		solrparams.setSellercode(Constant.SELLER_CODE);

		if(StringUtils.isNoneBlank(dto.getBase64())){
			solrparams.setBase64(dto.getBase64());
		}
		if(dto.getMinScore() != null) {
			solrparams.setMinScore(dto.getMinScore());
		}
		if(dto.getMaxScore() != null) {
			solrparams.setMaxScore(dto.getMaxScore());
		}
		SolrQuery solrQuery = SolrParamsQuery.getParams(solrparams);
		
		//记录搜索历史
		String userCode = "";
		if(StringUtils.isNotBlank(userToken)) {
			TUser user = userMapper.findTUserByUuid(userToken);
			userCode = user.getUserCode();
		}
		TSearchHistory entity = new TSearchHistory();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setCreateTime(DateUtil.getSysDateTime());
		entity.setCreateUser(userCode);
		entity.setUpdateTime(DateUtil.getSysDateTime());
		entity.setUpdateUser(userCode);
		mapper.insertSelective(entity);
		
		map = new SolrSearchServer().getSolrSearchData(solrQuery, Constant.SELLER_CODE);
		if(map!=null && !map.isEmpty()) {
			if(map.containsKey("items_rep")){
				list=(List<SolrData>)map.get("items_rep");
			}
			
			if(map.containsKey("counts")){
				list.get(0).setCounts(Long.parseLong(map.get("counts")==null?"0":map.get("counts").toString()));
			}
			
		}
	 
		return list;
	}
}
