package cn.com.ddhj.solr.data;


/**
 * 查询参数封装<br/>
 * 描述 : 参数封装类 搜索关键词必须为base64加密<br/>
 */
public class SolrParams {
	/**
	 * 搜索关键字 如果该字段用base64加密  base64字段值为：base64
	 */
	private String keyWord;
	/**
	 * 按那个字段排序   0、分值
	 */
	private int sortType;
	/**
	 * 排序方向   1、正序；2、倒序    默认为：2
	 */
	private int sortFlag;
	/**
	 * 每页显示读取记录数  默认为10 
	 */
	private int pageSize;
	/**
	 * 读取的页码数   默认为1
	 */
	private int pageNo;
	/**
	 * 如果keyWord字段加密该字段的值为：base64
	 */
	private String base64;
	/**
	 * 对应系统和solr索引库的分片名称  如：ddhj
	 */
	private String sellercode;
	
	/**
	 *  猜字查询  值为：key  否则为空
	 */
	private String key;
	
	/***
	 * 查询开始楼盘分数
	 */
	private double minScore;
	/**
	 * 查询结束楼盘分数
	 */
	private double maxScore;
	
	private String city;
	
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	public int getSortType() {
		return sortType;
	}
	public void setSortType(int sortType) {
		this.sortType = sortType;
	}
	public int getSortFlag() {
		return sortFlag;
	}
	public void setSortFlag(int sortFlag) {
		this.sortFlag = sortFlag;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	public String getSellercode() {
		return sellercode;
	}
	public void setSellercode(String sellercode) {
		this.sellercode = sellercode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public double getMinScore() {
		return minScore;
	}
	public void setMinScore(double minScore) {
		this.minScore = minScore;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
