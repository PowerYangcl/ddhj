package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.mapper.trade.TTradeCityMapper;
import cn.com.ddhj.mapper.trade.TTradeDealMapper;
import cn.com.ddhj.mapper.trade.TTradeMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.TOrder;
import cn.com.ddhj.model.trade.TTradeCity;
import cn.com.ddhj.model.trade.TTradeDeal;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.trade.TradeCityResult;
import cn.com.ddhj.result.trade.TradeDealResult;
import cn.com.ddhj.service.ITradeService;
import cn.com.ddhj.util.PureNetUtil;

/**
 * 
 * 类: TTradeServiceImpl <br>
 * 描述: 碳交易服务类<br>
 * 作者: zht<br>
 * 时间: 2017年3月12日
 */
@Service
public class TTradeServiceImpl implements ITradeService {

	@Autowired
	private TTradeMapper mapper;
	
	@Autowired
	private TTradeDealMapper tradeDealMapper;
	@Autowired
	private TTradeCityMapper tradeCityMapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;	
	
	@Override
	public int grabDealData(String url) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject result = getCarbonDealData(url);
		if(result != null) {
			Set<String> keys = result.keySet();
			for(String key : keys) {
				boolean existCity = true;
				TTradeCity city = tradeCityMapper.selectByCityName(key);
				if(city == null) {
					city = new TTradeCity();
					city.setCityName(key);
					city.setUuid(UUID.randomUUID().toString().replace("-", ""));
					city.setCreateTime(format.format(new Date()));
					city.setCreateUser("job.CarbonDealData");
					existCity = false;
				}
				
				JSONArray array = result.getJSONArray(key);
				for(int i = 0; i < array.size(); i++) {
					JSONObject dealObj = array.getJSONObject(i);
					TTradeDeal entity = new TTradeDeal();
					if(!existCity) {
						city.setCityId(dealObj.getString("HOUSEID"));
					}
					entity.setCityId(dealObj.getString("HOUSEID"));
					entity.setCityName(dealObj.getString("HOUSENAME"));
					BigDecimal price =BigDecimal.valueOf(dealObj.getDouble("deal"));
					price = price.setScale(2, BigDecimal.ROUND_HALF_UP);
					entity.setOpenPrice(price);
					entity.setDealPrice(price);
					entity.setHighPrice(price);
					entity.setLowPrice(price);
					entity.setClosePrice(price);
					
					BigDecimal amount = BigDecimal.valueOf(dealObj.getDouble("DEALAMOUNT"));
					amount = amount.setScale(2, BigDecimal.ROUND_HALF_UP);
					entity.setDealAmount(amount);
					
					BigDecimal numb = BigDecimal.valueOf(dealObj.getDouble("DEALNUM"));
					numb = numb.setScale(2, BigDecimal.ROUND_HALF_UP);
					entity.setDealNum(numb);
					entity.setDealDate(dealObj.getString("INDATE"));
					entity.setCreateTime(format.format(new Date()));
					entity.setCreateUser("job.CarbonDealData");
					
					tradeDealMapper.deleteByCityAndDealDate(entity);
					entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
					tradeDealMapper.insertSelective(entity);
				}
				
				if(!existCity) {
					tradeCityMapper.insertSelective(city);
				}
			}
		}
		return 0;
	}
	
	private JSONObject getCarbonDealData(String url) {
		JSONObject obj = null;
		try {
//			Map<String, String> param = new HashMap<String, String>();
			String result = PureNetUtil.post(url, null);
			if (StringUtils.isNotBlank(result)) {
				result = StringUtils.substringAfter(result, "(");
				result = StringUtils.substringBeforeLast(result, ")");
				obj = JSONObject.parseObject(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public TradeCityResult queryAllTradeCity() {
		List<TTradeCity> list = tradeCityMapper.queryAllTradeCity();
		TradeCityResult result = new TradeCityResult();
		result.setCitys(list);
		return result;
	}

	@Override
	public TradeDealResult queryDealsByCityId(TTradeDealDto dto) {
		TradeDealResult result = new TradeDealResult();
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TTradeDeal> dealList = tradeDealMapper.queryDealsByCityId(dto);
		Integer total = tradeDealMapper.queryDealsByCityIdCount(dto);
		if(dealList != null && !dealList.isEmpty()) {
			result.setResultCode(0);
		} else {
			result.setResultCode(-1);
			result.setResultMessage("获取数据为空");
			dealList = new ArrayList<TTradeDeal>();
		}
		result.setRepCount(total);
		result.setRepList(dealList);
		return result;
	}
	
}
