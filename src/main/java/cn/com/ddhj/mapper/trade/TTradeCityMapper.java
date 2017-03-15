package cn.com.ddhj.mapper.trade;

import java.util.List;

import cn.com.ddhj.dto.trade.TTradeCityDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.trade.TTradeCity;

public interface TTradeCityMapper extends BaseMapper<TTradeCity, TTradeCityDto> {
    int deleteByPrimaryKey(Integer id);

    int insert(TTradeCity record);

    int insertSelective(TTradeCity record);

    TTradeCity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTradeCity record);

    int updateByPrimaryKey(TTradeCity record);
    
    TTradeCity selectByCityName(String cityName);
    
    List<TTradeCity> queryAllTradeCity();
}