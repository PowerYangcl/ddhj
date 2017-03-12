package cn.com.ddhj.service;

/**
 * 
 * 类: ITTradeService <br>
 * 描述: 碳交易接口<br>
 * 作者: zht<br>
 * 时间: 2017年3月12日
 */
public interface ITradeService {
	
	/**
	 * 
	 * 方法: grabDealData<br>
	 * 描述: 定时从中国碳交易网抓取行情数据<br>
	 * 作者: zht<br>
	 * 时间: 2017年3月12日 下午10:49:01
	 * 
	 * @param url 数据抓取URL 
	 * @return 1.成功  0.失败
	 */
	public int grabDealData(String url);
}