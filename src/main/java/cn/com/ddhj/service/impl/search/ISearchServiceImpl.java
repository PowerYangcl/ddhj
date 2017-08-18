package cn.com.ddhj.service.impl.search;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.search.SearchLandPropertyDto;
import cn.com.ddhj.mapper.search.TSearchHistoryMapper;
import cn.com.ddhj.mapper.search.TSearchHotWordMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.search.TSearchHistory;
import cn.com.ddhj.model.search.TSearchHotWord;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.search.SearchResult;
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
	private TSearchHistoryMapper mapper;
	@Autowired
	private TSearchHotWordMapper hotWordMapper;
	@Autowired
	private TUserMapper userMapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	
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
			TUserLogin login = loginMapper.findLoginByUuid(userToken);
			if(login != null) {
				TUser user = userMapper.findTUserByUuid(login.getUserToken());
				userCode = user.getUserCode();
			}
		}
		TSearchHistory entity = new TSearchHistory();
		entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
		entity.setKeyWord(solrparams.getKeyWord());
		entity.setUserCode(userCode);
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

	@Override
	public SearchResult<TSearchHistory> getSearchHistoryByUserToken(String userToken) {
		SearchResult<TSearchHistory> result = new SearchResult<TSearchHistory>();
		if(StringUtils.isBlank(userToken)) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户token为空");
		}
		
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if(login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无法查询到用户登录信息");
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("无法查询到用户信息");
		}
		
		String userCode = user.getUserCode();
		List<TSearchHistory> list = mapper.getSearchHistoryByUserCode(userCode);
		result.setList(list);
		return result;
	}

	@Override
	public SearchResult<TSearchHotWord> getSearchHotWord() {
		// TODO Auto-generated method stub
		SearchResult<TSearchHotWord> result = new SearchResult<TSearchHotWord>();
		List<TSearchHotWord> list = hotWordMapper.getSearchHotWord();
		result.setList(list);
		return result;
	}

	@Override
	public JSONObject delSearchHistoryByUserToken(String userToken) {
		JSONObject result = new JSONObject();
		if(StringUtils.isBlank(userToken)) {
			result.put("resultCode", Constant.RESULT_ERROR);
			result.put("resultMessage", "用户token为空");
		}
		
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if(login == null) {
			result.put("resultCode", Constant.RESULT_ERROR);
			result.put("resultMessage", "无法查询到用户登录信息");
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.put("resultCode", Constant.RESULT_ERROR);
			result.put("resultMessage", "无法查询到用户信息");
		}
		
		String userCode = user.getUserCode();
		Integer count = mapper.delSearchHistoryByUserCode(userCode);
		result.put("resultCode", Constant.RESULT_SUCCESS);
		result.put("resultMessage", "删除了" + count + "条收货地址");
		return result;
	}
}
