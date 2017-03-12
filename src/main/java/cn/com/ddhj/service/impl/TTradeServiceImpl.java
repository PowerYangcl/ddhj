package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.mapper.TTradeDealMapper;
import cn.com.ddhj.mapper.TTradeMapper;
import cn.com.ddhj.model.TTradeDeal;
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
	
	@Override
	public int grabDealData(String url) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONObject result = getCarbonDealData(url);
		if(result != null) {
			Set<String> keys = result.keySet();
			for(String key : keys) {
				JSONArray array = result.getJSONArray(key);
				for(int i = 0; i < array.size(); i++) {
					JSONObject dealObj = array.getJSONObject(i);
					TTradeDeal entity = new TTradeDeal();
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
					entity.setDealDate(dealObj.getDate("INDATE"));
					entity.setCreateTime(format.format(new Date()));
					entity.setCreateUser("job.CarbonDealData");
					
					tradeDealMapper.deleteByCityAndDealDate(entity);
					entity.setUuid(UUID.randomUUID().toString().replace("-", ""));
					tradeDealMapper.insertSelective(entity);
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
}
