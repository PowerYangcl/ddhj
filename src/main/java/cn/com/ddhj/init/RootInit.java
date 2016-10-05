package cn.com.ddhj.init;

import cn.com.ddhj.base.BaseClass;

/**
 * 各种初始化调用类
 * 
 * @author HJY
 *
 */
public abstract class RootInit extends BaseClass {

	public RootInit() {
		getLogger().logInfo("init class : " + this.getClass().getName());
	}

	public boolean init() {
		return onInit();
	}

	/**
	 * 当系统初始化时调用
	 * 
	 * @return
	 */
	public abstract boolean onInit();

	public boolean destory() {
		return onDestory();
	}

	/**
	 * 当容器关闭时调用
	 * 
	 * @return
	 */
	public abstract boolean onDestory();

}
