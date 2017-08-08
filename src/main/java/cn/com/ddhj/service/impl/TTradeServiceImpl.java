package cn.com.ddhj.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.com.ddhj.base.BaseResult;
import cn.com.ddhj.dto.trade.TTradeBalanceDto;
import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.dto.trade.TTradeOrderDto;
import cn.com.ddhj.helper.WebHelper;
import cn.com.ddhj.mapper.trade.TTradeBalanceMapper;
import cn.com.ddhj.mapper.trade.TTradeCityMapper;
import cn.com.ddhj.mapper.trade.TTradeDealMapper;
import cn.com.ddhj.mapper.trade.TTradeOrderMapper;
import cn.com.ddhj.mapper.user.TUserCarbonOperationMapper;
import cn.com.ddhj.mapper.user.TUserLoginMapper;
import cn.com.ddhj.mapper.user.TUserMapper;
import cn.com.ddhj.model.trade.TTradeBalance;
import cn.com.ddhj.model.trade.TTradeCity;
import cn.com.ddhj.model.trade.TTradeDeal;
import cn.com.ddhj.model.trade.TTradeOrder;
import cn.com.ddhj.model.user.TUser;
import cn.com.ddhj.model.user.TUserCarbonOperation;
import cn.com.ddhj.model.user.TUserLogin;
import cn.com.ddhj.result.trade.TradeBalanceResult;
import cn.com.ddhj.result.trade.TradeCityResult;
import cn.com.ddhj.result.trade.TradeDealChartResult;
import cn.com.ddhj.result.trade.TradeDealChartResult.CityTradeDeals;
import cn.com.ddhj.result.trade.TradeDealResult;
import cn.com.ddhj.result.trade.TradeOrderResult;
import cn.com.ddhj.result.trade.TradePriceAvaiAmountResult;
import cn.com.ddhj.service.ITradeService;
import cn.com.ddhj.util.Constant;
import cn.com.ddhj.util.DateUtil;
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
	private TTradeBalanceMapper tradeBalanceMapper;
	@Autowired
	private TTradeDealMapper tradeDealMapper;
	@Autowired
	private TTradeCityMapper tradeCityMapper;
	@Autowired
	private TTradeOrderMapper tradeOrderMapper;
	@Autowired
	private TUserLoginMapper loginMapper;
	@Autowired
	private TUserMapper userMapper;	
	@Autowired
	private TUserCarbonOperationMapper userCarbonOperMapper;
	
	private static final Double ratio = new Double(1000);
	
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public int grabDealData(String url) {
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
			calOtherPrice();
		}
		return 0;
	}
	
	private void calOtherPrice() {
		//定时抓取数据后,计算市场均价和市场均价较上一交易日涨跌幅
		List<TTradeDeal> tradeDealDateList = tradeDealMapper.queryTradeDealDates();
		for(int i = 0; i < tradeDealDateList.size(); i++) {
			TTradeDeal tradeDate = tradeDealDateList.get(i);
			if(StringUtils.isBlank(tradeDate.getDealDate())) 
				continue;
			//计算当日市场均价
			BigDecimal marketAvgPrice = tradeDealMapper.queryMarketAvgPriceByDealDate(tradeDate.getDealDate());
			if(marketAvgPrice != null)
				marketAvgPrice = marketAvgPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
			
			if(i < tradeDealDateList.size() - 1) {
				//计算当日市场均价较上一交易日涨跌幅
				TTradeDeal pirortradeDate = tradeDealDateList.get(i + 1);
				if(StringUtils.isBlank(pirortradeDate.getDealDate())) 
					continue;
				BigDecimal pirorMarketAvgPrice = tradeDealMapper.queryMarketAvgPriceByDealDate(pirortradeDate.getDealDate());
				if(pirorMarketAvgPrice != null)
					pirorMarketAvgPrice = pirorMarketAvgPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
				
				BigDecimal marketAvgPriceUpDownRatio = BigDecimal.ZERO;
				if(marketAvgPrice != null && pirorMarketAvgPrice != null && pirorMarketAvgPrice.compareTo(BigDecimal.ZERO) != 0) {
					marketAvgPriceUpDownRatio = marketAvgPrice.subtract(pirorMarketAvgPrice)
							.divide(pirorMarketAvgPrice, 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
					marketAvgPriceUpDownRatio = marketAvgPriceUpDownRatio.setScale(2, BigDecimal.ROUND_HALF_UP);
					
				}
				
				//查询每个城市当日的成交价较上一交易日涨幅
				List<TTradeCity> cityList = tradeCityMapper.queryAllTradeCity();
				for(TTradeCity city : cityList) {
					TTradeDealDto dto = new TTradeDealDto();
					dto.setCityId(city.getCityId());
					dto.setDealDate(tradeDate.getDealDate());
					TTradeDeal curTradeDeal = tradeDealMapper.queryDealByCityIdAndDate(dto);
					if(curTradeDeal == null) {
						continue;
					}
					curTradeDeal = tradeDealMapper.selectByPrimaryKey(curTradeDeal.getId());
					if(curTradeDeal == null || curTradeDeal.getDealPrice() == null ) 
						continue;
					curTradeDeal.setDealPrice(curTradeDeal.getDealPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
					
					dto.setDealDate(pirortradeDate.getDealDate());
					TTradeDeal pirorTradeDeal = tradeDealMapper.queryDealByCityIdAndDate(dto);
					if(pirorTradeDeal == null) {
						continue;
					}
					pirorTradeDeal = tradeDealMapper.selectByPrimaryKey(pirorTradeDeal.getId());
					if(pirorTradeDeal == null || pirorTradeDeal.getDealPrice() == null) 
						continue;
					pirorTradeDeal.setDealPrice(pirorTradeDeal.getDealPrice().setScale(2, BigDecimal.ROUND_HALF_UP));
					
					if(pirorTradeDeal.getDealPrice().compareTo(BigDecimal.ZERO) != 0) {
						BigDecimal curCityPriceUpDownRatio = curTradeDeal.getDealPrice().subtract(pirorTradeDeal.getDealPrice())
								.divide(pirorTradeDeal.getDealPrice(), 5, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
						curCityPriceUpDownRatio = curCityPriceUpDownRatio.setScale(2, BigDecimal.ROUND_HALF_UP);
						
						curTradeDeal.setDealUpDownRatio(curCityPriceUpDownRatio);
					}
					curTradeDeal.setMarketAvgPrice(marketAvgPrice);
					curTradeDeal.setMarketAvgPriceUpDownRatio(marketAvgPriceUpDownRatio);
					curTradeDeal.setDealPrice(null);
					tradeDealMapper.updateByPrimaryKeySelective(curTradeDeal);
				}
				
			}
			
			
		}
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
			result.setResultCode(Constant.RESULT_SUCCESS);
		} else {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("获取数据为空");
			dealList = new ArrayList<TTradeDeal>();
		}
		result.setRepCount(total);
		result.setRepList(dealList);
		return result;
	}
	
	@Override
	public TradeDealChartResult queryDealsByCityIdAndPeriod(TTradeDealDto dto) {
		TradeDealChartResult result = new TradeDealChartResult();
		List<TTradeDeal> dealList = tradeDealMapper.queryDealsByCityIdAndPeriod(dto);
		if(dealList != null && !dealList.isEmpty()) {
			result.setResultCode(Constant.RESULT_SUCCESS);
			//按城市为行情数据分组
			Map<String, Integer> position = new HashMap<String, Integer>();
			for(TTradeDeal deal : dealList) {
				String cityName = deal.getCityName();
				if(position.containsKey(cityName)) {
					CityTradeDeals ctd = result.getList().get(position.get(cityName));
					ctd.getDeals().add(deal);
				} else {
					CityTradeDeals ctd = result.new CityTradeDeals();
					ctd.setCityName(cityName);
					ctd.getDeals().add(deal);
					result.getList().add(ctd);
					position.put(cityName, result.getList().size() - 1);
				}
			}
		} else {
			result.setResultCode(Constant.RESULT_NULL);
			result.setResultMessage("获取数据为空");
			
		}
		return result;
	}

	@Override
	public BaseResult sendTradeOrder(TTradeOrder order, String userToken) {
		BaseResult result = new BaseResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		
		if (login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if (user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}
		
		if(order == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("委托数据为空,无法报盘");
			return result;
		}
		//根据委托标的的现价计算当前用户可买或可卖数
		TTradeDealDto dealDto = new TTradeDealDto();
		dealDto.setObjectCode(order.getObjectCode());
		TradePriceAvaiAmountResult avaiResult = this.getCurrentPriceAndAvailableAmount(dealDto, userToken);
		if(order.getBuySell().equals("B")) {
			if(order.getAmount().compareTo(avaiResult.getBuyAmount()) > 0) {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("委买量超过最大可买量");
				return result;
			}
		} else if(order.getBuySell().equals("S")) {
			if(order.getAmount().compareTo(avaiResult.getSellAmount()) > 0) {
				result.setResultCode(Constant.RESULT_ERROR);
				result.setResultMessage("委卖量超过最大可卖量");
				return result;
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("只能买入或卖出,委托类型不合法!");
			return result;
		}
		
		String createTime = format.format(new Date());
		order.setOrderCode(WebHelper.getInstance().getUniqueCode("DD"));
		order.setCreateTime(createTime);
		order.setCreateUser(user.getUserCode());
		order.setUserCode(user.getUserCode());
		order.setStatus(1);
		order.setUuid(UUID.randomUUID().toString().replace("-", ""));
		int success = tradeOrderMapper.insertSelective(order);
		if(success == 1) {
			//根据用户编号和委托标的查询该用户该标的的持仓记录
			TTradeBalanceDto balanceDto = new TTradeBalanceDto();
			balanceDto.setObjectCode(order.getObjectCode());
			balanceDto.setUserCode(order.getUserCode());
			List<TTradeBalance> balanceList = tradeBalanceMapper.selectByUserCodeAndObjCode(balanceDto);
			TTradeBalance balance = null;
			if(balanceList == null || balanceList.isEmpty()) {
				balance = new TTradeBalance();
				balance.setUuid(UUID.randomUUID().toString().replace("-", ""));
				balance.setObjectCode(order.getObjectCode());
				balance.setPrice(order.getPrice());
				balance.setAmount(order.getAmount());
				balance.setUserCode(order.getUserCode());
				//多头持仓,目前暂不支持空头
				balance.setBuySell("B");
				balance.setCreateTime(createTime);
				balance.setCreateUser(order.getUserCode());
				tradeBalanceMapper.insertSelective(balance);
			} else {
				balance = balanceList.get(0);
				balance = calculateBalance(balance, order, user.getUserCode());
				tradeBalanceMapper.updateByPrimaryKey(balance);
			}
			
			BigDecimal remainCarbon = null;
			if(order.getBuySell().equals("B")) {
				//委买,减扣用户碳币数
				BigDecimal consumeCarbon = order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())).divide(BigDecimal.valueOf(ratio)).setScale(2, BigDecimal.ROUND_DOWN);
				remainCarbon = user.getCarbonMoney().subtract(consumeCarbon).setScale(2,  BigDecimal.ROUND_DOWN);
				if(remainCarbon.compareTo(BigDecimal.valueOf(0)) == -1) {
					remainCarbon = BigDecimal.ZERO;
				}
				//记录用户碳币操作记录
				TUserCarbonOperation entity = new TUserCarbonOperation();
				entity.setUuid(WebHelper.getInstance().genUuid());
				entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
				entity.setUserCode(user.getUserCode());
				entity.setOperationType("DC170208100003");
				entity.setOperationTypeChild("DC170208100009");
				entity.setCarbonSum(consumeCarbon);
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				userCarbonOperMapper.insertSelective(entity);
			} else if(order.getBuySell().equals("S")) {
				//委卖增加用户碳币数
				BigDecimal earnCarbon = order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())).divide(BigDecimal.valueOf(ratio)).setScale(2, BigDecimal.ROUND_DOWN);
				remainCarbon = user.getCarbonMoney().add(earnCarbon).setScale(2, BigDecimal.ROUND_DOWN);
				//记录用户碳币操作记录
				TUserCarbonOperation entity = new TUserCarbonOperation();
				entity.setUuid(WebHelper.getInstance().genUuid());
				entity.setCode(WebHelper.getInstance().getUniqueCode("LC"));
				entity.setUserCode(user.getUserCode());
				entity.setOperationType("DC170208100002");
				entity.setOperationTypeChild("DC170208100010");
				entity.setCarbonSum(earnCarbon);
				entity.setCreateUser(user.getUserCode());
				entity.setCreateTime(DateUtil.getSysDateTime());
				userCarbonOperMapper.insertSelective(entity);				
			}
	
			if(remainCarbon != null) {
				user.setCarbonMoney(remainCarbon);
				userMapper.updateByCode(user);
			}
			
			result.setResultCode(Constant.RESULT_SUCCESS);
			result.setResultMessage("委托成功");
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("委托失败");
		}
		
		return result;
	}
	
	/**
	 * 
	 * 方法: calculateBalance<br>
	 * 描述: 用户已有某标的的持仓根据用户委托单计算该标的的均价<br>
	 * 作者: 海涛<br>
	 * 时间: 2017年3月16日 上午9:40:41
	 * 
	 * @param 
	 * @return
	 */
	private TTradeBalance calculateBalance(TTradeBalance balance, TTradeOrder order, String userCode) {
		if(order.getBuySell().equals("B")) {
			//委买单
			BigDecimal total = balance.getPrice().multiply(BigDecimal.valueOf(balance.getAmount())).add(order.getPrice().multiply(BigDecimal.valueOf(order.getAmount())));
			BigDecimal avgPrice = total.divide(BigDecimal.valueOf(balance.getAmount() + order.getAmount()));
			balance.setPrice(avgPrice);
			balance.setAmount(balance.getAmount() + order.getAmount());
			balance.setUpdateTime(format.format(new Date()));
			balance.setUpdateUser(userCode);
		} else {
			//委卖单
			balance.setAmount(balance.getAmount() - order.getAmount());
		}
		return balance;
	}

	@Override
	public TradePriceAvaiAmountResult getCurrentPriceAndAvailableAmount(TTradeDealDto dto, String userToken) {
		TradePriceAvaiAmountResult result = new TradePriceAvaiAmountResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if(login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}
		
		if(StringUtils.isBlank(dto.getObjectCode())) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("查询交易标的参数为空");
			return result;
		}
		/////////////////////////
		dto.setCityId(dto.getObjectCode());
		/////////////////////////
		BigDecimal carbonMoney = user.getCarbonMoney();
//		String ratio = PropHelper.getValue("carbon_money_ratio");
		BigDecimal actualMoney = carbonMoney.multiply(BigDecimal.valueOf(ratio));
		dto.setPageIndex(0);
		dto.setPageSize(1);
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TTradeDeal> dealList = tradeDealMapper.queryDealsByCityId(dto);
		if(dealList != null && !dealList.isEmpty()) {
			//标的现价和计算可买数量
			TTradeDeal tradeDeal = dealList.get(0);
			BigDecimal buyAmount = actualMoney.divide(tradeDeal.getClosePrice(), 0, BigDecimal.ROUND_DOWN);
			result.setBuyAmount(buyAmount.intValue());
			result.setCurrentPrice(tradeDeal.getClosePrice());
			//查询用户当前标的持仓数,为可卖数
			TTradeBalanceDto balanceDto = new TTradeBalanceDto();
			balanceDto.setUserCode(user.getUserCode());
			balanceDto.setObjectCode(dto.getCityId());
			List<TTradeBalance> balanceList = tradeBalanceMapper.selectByUserCodeAndObjCode(balanceDto);
			if(balanceList != null && !balanceList.isEmpty()) {
				TTradeBalance tradeBalance = balanceList.get(0);
				result.setSellAmount(tradeBalance.getAmount());
			} else {
				result.setSellAmount(0);
			}
		} else {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("未查询到指定的交易标的");
		}
		return result;
	}

	@Override
	public TradeBalanceResult getUserBalance(String userToken) {
		TradeBalanceResult result = new TradeBalanceResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if(login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}
		
		TTradeBalanceDto dto = new TTradeBalanceDto();
		dto.setUserCode(user.getUserCode());
		List<TTradeBalance> balanceList = tradeBalanceMapper.selectByUserCodeAndObjCode(dto);
		if(balanceList != null && !balanceList.isEmpty()) {
			for(TTradeBalance balance : balanceList) {
				Integer amount = balance.getAmount();
				String objectCode = balance.getObjectCode();
				//持仓价
				BigDecimal price = balance.getPrice();
				//查询持仓标的最新价格
				TTradeDealDto ddt = new TTradeDealDto();
				ddt.setCityId(objectCode);
				TTradeDeal newDeal = getObjectNewPrice(ddt);
				balance.setLastPrice(newDeal.getClosePrice());
				//查询持仓标的的盈亏
				BigDecimal profitLoss = newDeal.getClosePrice().subtract(price).multiply(BigDecimal.valueOf(amount)).setScale(2, BigDecimal.ROUND_DOWN);
				balance.setProfitLoss(profitLoss);
			}
		}
		result.setObjects(balanceList);
		return result;
	}

	@Override
	public TradeOrderResult getUserTradeOrder(TTradeOrderDto dto, String userToken) {
		TradeOrderResult result = new TradeOrderResult();
		TUserLogin login = loginMapper.findLoginByUuid(userToken);
		if(login == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户未登录");
			return result;
		}
		
		TUser user = userMapper.findTUserByUuid(login.getUserToken());
		if(user == null) {
			result.setResultCode(Constant.RESULT_ERROR);
			result.setResultMessage("用户不存在");
			return result;
		}
		
		dto.setUserCode(user.getUserCode());
		List<TTradeOrder> orderList = tradeOrderMapper.selectUserTradeOrder(dto);
		result.setOrders(orderList);
		return result;
	}
	
	private TTradeDeal getObjectNewPrice(TTradeDealDto dto) {
		TTradeDeal tradeDeal = null;
		dto.setPageIndex(0);
		dto.setPageSize(1);
		dto.setStart(dto.getPageIndex() * dto.getPageSize());
		List<TTradeDeal> dealList = tradeDealMapper.queryDealsByCityId(dto);
		if(dealList != null && !dealList.isEmpty()) {
			tradeDeal = dealList.get(0);
		}
		return tradeDeal;
	}
}
