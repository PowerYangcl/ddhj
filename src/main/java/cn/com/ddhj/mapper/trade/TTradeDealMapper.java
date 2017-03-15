package cn.com.ddhj.mapper.trade;

import java.util.List;

import cn.com.ddhj.dto.trade.TTradeDealDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.trade.TTradeDeal;

public interface TTradeDealMapper extends BaseMapper<TTradeDeal, TTradeDealDto> {
    int deleteByPrimaryKey(Integer id);

    int insert(TTradeDeal record);

    TTradeDeal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTradeDeal record);
    
    
    int updateByPrimaryKey(TTradeDeal record);
    
    /**
     * 
     * 方法: deleteByCityAndDealDate<br>
     * 描述: 按城市和交易日期删除交易数据<br>
     * 作者: zht<br>
     * 时间: 2017年3月12日 下午11:28:02
     * 
     * @param record 一个城市的一天碳交易记录 
     * @return
     */
    int deleteByCityAndDealDate(TTradeDeal record);
    
    /**
     * 
     * 方法: insertSelective<br>
     * 描述: 插入交易数据<br>
     * 作者: zht<br>
     * 时间: 2017年3月12日 下午11:28:02
     * 
     * @param record 一个城市的一天碳交易记录 
     * @return
     */
    int insertSelective(TTradeDeal record);
	
    /**
     * 
     * 方法: queryDealsByCityId<br>
     * 描述: 按城市Id分页查询该城市所有碳交易记录,按交易日期倒序,无城市ID时查询所有城市<br>
     * 作者: 海涛<br>
     * 时间: 2017年3月14日 下午2:29:42
     * 
     * @param 
     * @return
     */
    List<TTradeDeal> queryDealsByCityId(TTradeDealDto dto);
    
    /**
     * 
     * 方法: queryDealsByCityIdCount<br>
     * 描述: 按城市Id分页查询该城市所有碳交易记录总数,无城市ID时查询所有城市<br>
     * 作者: 海涛<br>
     * 时间: 2017年3月15日 下午9:47:32
     * 
     * @param 
     * @return
     */
    Integer queryDealsByCityIdCount(TTradeDealDto dto);
    
//    /**
//     * 
//     * 方法: queryDealsByDefault<br>
//     * 描述: 查询<br>
//     * 作者: zht<br>
//     * 时间: 2017年3月15日 下午8:59:52
//     * 
//     * @param 
//     * @return
//     */
//    List<TTradeDeal> queryDealsByDefault(TTradeDealDto dto);
}