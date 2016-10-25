package cn.com.ddhj.mapper.report;

import java.util.List;
import java.util.Map;

import cn.com.ddhj.dto.BaseDto;
import cn.com.ddhj.dto.report.TReportDto;
import cn.com.ddhj.mapper.BaseMapper;
import cn.com.ddhj.model.report.TReport;

/**
 * 
 * 类: TReportMapper <br>
 * 描述: 环境报告数据库访问接口 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月3日 下午1:57:48
 */
public interface TReportMapper extends BaseMapper<TReport, BaseDto> {

	/**
	 * 
	 * 方法: findRreportByChart <br>
	 * 描述: 根据编码集合获取报告信息 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月7日 下午4:25:04
	 * 
	 * @param list
	 * @return
	 */
	List<TReport> findRreportByChart(List<String> list);

	/**
	 * 
	 * 根据楼盘编码查询所属楼盘的报告列表<br>
	 * 
	 * @param housesCode
	 * @return
	 */
	List<TReport> findReportByHousesCode(String housesCode);

	// 根据楼盘code取报告的最低价格
	public List<TReport> findPriceByCode(List<String> lpcode);

	/**
	 * 
	 * 方法: insertReportData <br>
	 * 描述: 批量生成环境报告 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月9日 下午6:48:59
	 * 
	 * @param list
	 * @return
	 */
	int insertReportData(List<TReport> list);

	/**
	 * 
	 * 方法: findLevel <br>
	 * 描述: 根据报告编码查询等级名称 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月15日 下午5:27:50
	 * 
	 * @param code
	 * @return
	 */
	String findLevel(String code);

	/**
	 * 
	 * 方法: findTReportAll <br>
	 * 描述: 获取报告编码 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月15日 下午6:14:40
	 * 
	 * @return
	 */
	List<TReport> findTReportAll();

	/**
	 * 
	 * 方法: findReportDataAll <br>
	 * 描述: 获取所有环境报告数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月21日 下午11:31:44
	 * 
	 * @param dto
	 * @return
	 */
	List<Map<String, String>> findReportDataAll(TReportDto dto);

	/**
	 * 
	 * 方法: findReportByLpCodeAndLevelCode <br>
	 * 描述: 根据楼盘编码和等级编码查询报告是否存在 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月22日 下午8:34:25
	 * 
	 * @param dto
	 * @return
	 */
	TReport findReportByLpCodeAndLevelCode(TReportDto dto);
}