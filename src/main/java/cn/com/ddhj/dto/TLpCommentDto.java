package cn.com.ddhj.dto;

/**
 * 
 * 类: TLpCommentDto <br>
 * 描述: 楼盘评价参数 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月10日 下午12:03:37
 */
public class TLpCommentDto extends BaseDto {

	/**
	 * 楼盘编码
	 */
	private String lpCode;

	/**
	 * 评价等级 0 好评 1 中评 2 差评
	 */
	private Integer level;

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getLpCode() {
		return lpCode;
	}

	public void setLpCode(String lpCode) {
		this.lpCode = lpCode;
	}

}
