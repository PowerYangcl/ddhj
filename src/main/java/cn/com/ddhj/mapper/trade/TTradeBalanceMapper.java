package cn.com.ddhj.mapper.trade;

import java.util.List;

import cn.com.ddhj.dto.trade.TTradeBalanceDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.trade.TTradeBalance;

public interface TTradeBalanceMapper extends BaseMapper<TTradeBalance, TTradeBalanceDto> {
    int deleteByPrimaryKey(Integer id);

    int insert(TTradeBalance record);

    TTradeBalance selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTradeBalance record);
    
    /**
     * 
     * 方法: insertSelective<br>
     * 描述: 插入用户持仓记录<br>
     * 作者: 海涛<br>
     * 时间: 2017年3月16日 上午9:01:23
     * 
     * @param 
     * @return
     */
    int insertSelective(TTradeBalance record);
    
    /**
     * 
     * 方法: updateByPrimaryKey<br>
     * 描述: 根据主键更新用户持仓记录<br>
     * 作者: 海涛<br>
     * 时间: 2017年3月16日 上午9:01:23
     * 
     * @param 
     * @return
     */
    int updateByPrimaryKey(TTradeBalance record);
    
    /**
     * 
     * 方法: selectByUserCodeAndObjCode<br>
     * 描述: 根据用户编号和交易标的编码查询用户持仓记录<br>
     * 作者: 海涛<br>
     * 时间: 2017年3月16日 上午9:07:21
     * 
     * @param 
     * @return
     */
    List<TTradeBalance> selectByUserCodeAndObjCode(TTradeBalanceDto record);
}