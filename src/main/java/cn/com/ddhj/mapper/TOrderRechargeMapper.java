package cn.com.ddhj.mapper;

import cn.com.ddhj.model.TOrderRecharge;

public interface TOrderRechargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TOrderRecharge record);

    int insertSelective(TOrderRecharge record);

    TOrderRecharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TOrderRecharge record);

    int updateByPrimaryKey(TOrderRecharge record);
    
    TOrderRecharge selectByOrderCode(String orderCode);
}