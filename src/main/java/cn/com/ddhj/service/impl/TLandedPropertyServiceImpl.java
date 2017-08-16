package cn.com.ddhj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.ddhj.dto.SearchLandPropertyDto;
import cn.com.ddhj.dto.TLandedPropertyDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.TLandedPropertyMapper;
import cn.com.ddhj.model.TLandedProperty;
import cn.com.ddhj.result.lp.TLandedPropertyDataResult;
import cn.com.ddhj.service.ITLandedPropertyService;
import cn.com.ddhj.solr.data.SolrData;
import cn.com.ddhj.solr.data.SolrParams;
import cn.com.ddhj.solr.query.SolrParamsQuery;
import cn.com.ddhj.solr.server.SolrSearchServer;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;
import cn.com.ddhj.util.PureNetUtil;

/**
 * 
 * 类: TLandedPropertyServiceImpl <br>
 * 描述: 地产楼盘列表业务逻辑处理接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午5:31:35
 */
@Service
public class TLandedPropertyServiceImpl extends
		BaseServiceImpl<TLandedProperty, TLandedPropertyMapper, TLandedPropertyDto> implements ITLandedPropertyService {

	@Autowired
	private TLandedPropertyMapper mapper;

	/**
	 * 
	 * 方法: insertDataFromAPI <br>
	 * 
	 * @see cn.com.ddhj.service.ITLandedPropertyService#insertDataFromAPI()
	 */
	@Override
	public void insertDataFromAPI(String cityName) {
		int page = 1;
		boolean flag = true;
		while (flag) {
			String key = "f74ac8fbf0d992b02420a03387ed8341";
			String url = "http://v.juhe.cn/estate/query";
			Map<String, String> param = new HashMap<String, String>();
			param.put("city", cityName);
			param.put("q", "");
			param.put("key", key);
			param.put("page", String.valueOf(page));
			param.put("dtype", "json");
			String responseJson = PureNetUtil.post(url, param);
			if (responseJson != null && !"".equals(responseJson)) {
				JSONObject result = JSONObject.parseObject(responseJson);
				if (result != null && result.getString("result") != null && !"".equals(result.getString("result"))) {
					JSONArray array = JSONArray.parseArray(result.getString("result"));
					if (array != null && array.size() > 0) {
						List<TLandedProperty> list = new ArrayList<TLandedProperty>();
						for (int i = 0; i < array.size(); i++) {
							JSONObject obj = array.getJSONObject(i);
							TLandedProperty model = obj.toJavaObject(TLandedProperty.class);
							model.setUuid(UUID.randomUUID().toString().replace("-", ""));
							model.setCode(WebHelper.getInstance().getUniqueCode("LP"));
							model.setCreateUser("system");
							model.setCreateTime(DateUtil.getSysDateTime());
							model.setUpdateUser("system");
							model.setUpdateTime(DateUtil.getSysDateTime());
							list.add(model);
						}
						mapper.batchInsertTLandedProperty(list);
					} else {
						flag = false;
						break;
					}
				} else {
					flag = false;
					break;
				}
			}
			if (page == 20000) {
				flag = false;
				break;
			}
			page++;
		}
	}

	/**
	 * 
	 * 方法: getLpData <br>
	 * 
	 * @param dto
	 * @return
	 * @see cn.com.ddhj.service.ITLandedPropertyService#getLpData(cn.com.ddhj.dto.TLandedPropertyDto)
	 */
	@Override
	public TLandedPropertyDataResult getLpData(TLandedPropertyDto dto) {
		TLandedPropertyDataResult result = new TLandedPropertyDataResult();
		PageHelper.startPage(dto.getPageIndex(), dto.getPageSize());
		List<TLandedProperty> list = mapper.findEntityAll(dto);
		if (list != null && list.size() > 0) {
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			list = new ArrayList<TLandedProperty>();
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("查询楼盘列表为空");
		}
		PageInfo<TLandedProperty> page = new PageInfo<TLandedProperty>(list);
		result.setPage(page);
		return result;
	}

	@Override
	public List<SolrData> search(SearchLandPropertyDto dto) {
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
		solrparams.setSellercode(Constant.SELLER_CODE);

		if(StringUtils.isNoneBlank(dto.getBase64())){
			solrparams.setBase64(dto.getBase64());
		}
		if(dto.getMinScore() != 0) {
			solrparams.setMinScore(dto.getMinScore());
		}
		if(dto.getMaxScore() != 0) {
			solrparams.setMaxScore(dto.getMaxScore());
		}
		
//		if(mRequestDate.containsKey("key")){
//			solrparams.setKey(mRequestDate.get("key"));
//		}
		map = new SolrSearchServer().getSolrSearchData(SolrParamsQuery.getParams(solrparams), Constant.SELLER_CODE);
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
