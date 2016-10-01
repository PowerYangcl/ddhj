package cn.com.ddhj.model;

/**
 * 
 * 类: BaseModel <br>
 * 描述: 持久化对象基类 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月1日 上午10:57:45
 */
public class BaseModel {

	private Integer id;

	private String uuid;

	private String createUser;

	private String createTime;

	private String updateUser;

	private String updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
