package cn.com.ddhj.mapper.trade;

import java.math.BigDecimal;
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
	 * 方法: queryDealsByCityIdAndPeriod<br>
	 * 描述: 按城市Id和时间周期(1月|3月|6月|1年)查询该城市所有碳交易记录,按交易日期倒序,无城市ID时查询所有城市<br>
	 * 作者: zht<br>
	 * 时间: 2017年8月3日 下午9:35:05
	 * 
	 * @param 
	 * @return
	 */
    List<TTradeDeal> queryDealsByCityIdAndPeriod(TTradeDealDto dto);
    
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
    
    /**
     * 查询系统中所有的交易日期,并按日期倒序
     * @return
     */
    List<TTradeDeal> queryTradeDealDates();
    
    /**
     * 按交易日期查询该日期市场成交均价
     * 作者: 海涛<br>
     * @param dealDate
     */
    BigDecimal queryMarketAvgPriceByDealDate(String dealDate);
    
    /**
     * 按城市ID和交易时间查询该交易日的成交价
     * 作者: 海涛<br>
     * @param dto
     * @return
     */
    TTradeDeal queryDealByCityIdAndDate(TTradeDealDto dto);
}