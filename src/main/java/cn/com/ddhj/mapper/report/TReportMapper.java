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
	 * @param list
	 * @return
	 */
	TReport findReportByLpCodeAndLevelCode(List<String> list);

	/**
	 * 
	 * 方法: batchInsertReportToTmp <br>
	 * 描述: 存储数据到报告临时表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:12:01
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertReportToTmp(List<TReport> list);

	/**
	 * 批量生成H5环境报告
	 * 
	 * @param list
	 * @return
	 */
	int batchInsertH5ReportToTmp(List<TReport> list);

	/**
	 * 
	 * 方法: importReportFormTmp <br>
	 * 描述: 从报告临时表将数据同步到报告表 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:12:16
	 * 
	 * @return
	 */
	int importReportFormTmp();

	/**
	 * 
	 * 方法: delReportTmp <br>
	 * 描述: 删除临时表数据 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年10月26日 下午9:12:20
	 * 
	 * @return
	 */
	int delReportTmp();

	/**
	 * 
	 * 方法: updateReportTime <br>
	 * 描述: 批量修改环境报告的修改时间 <br>
	 * 作者: zhy<br>
	 * 时间: 2016年11月23日 下午4:31:48
	 * 
	 * @return
	 */
	int updateReportTime();

	/**
	 * 根据楼盘和报告等级修改报告信息
	 * 
	 * @param entity
	 * @return
	 */
	int updateReportByLpAndLevel(TReport entity);
	
	List<Map<String, Object>> fineTreportTmp();
}