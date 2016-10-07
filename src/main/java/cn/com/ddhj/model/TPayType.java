package cn.com.ddhj.model;

/**
 * 
 * 类: TPayType <br>
 * 描述: 支付方式 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月7日 下午5:28:46
 */
public class TPayType extends BaseModel {

	private String code;

	private String name;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}