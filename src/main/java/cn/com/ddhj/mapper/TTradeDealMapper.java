package cn.com.ddhj.mapper;

import cn.com.ddhj.dto.TTradeDealDto;
import cn.com.ddhj.model.TTradeDeal;

public interface TTradeDealMapper extends BaseMapper<TTradeDeal, TTradeDealDto> {
    int deleteByPrimaryKey(Integer id);

    int insert(TTradeDeal record);

    TTradeDeal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTradeDeal record);
    
    
    int updateByPrimaryKey(TTradeDeal record);
    
    /**
     * 按城市和交易日期删除交易数据
     * 方法: deleteByCityAndDealDate<br>
     * 描述: <br>
     * 作者: zht<br>
     * 时间: 2017年3月12日 下午11:28:02
     * 
     * @param record 一个城市的一天碳交易记录 
     * @return
     */
    int deleteByCityAndDealDate(TTradeDeal record);
    
    /**
     * 插入交易数据
     * 方法: deleteByCityAndDealDate<br>
     * 描述: <br>
     * 作者: zht<br>
     * 时间: 2017年3月12日 下午11:28:02
     * 
     * @param record 一个城市的一天碳交易记录 
     * @return
     */
    int insertSelective(TTradeDeal record);
}