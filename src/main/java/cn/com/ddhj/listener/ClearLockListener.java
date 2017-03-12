package cn.com.ddhj.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.com.ddhj.init.ServerletLoader;
import cn.com.ddhj.service.impl.system.LockServiceImpl;
import cn.com.ddhj.util.SpringCtxUtil;

/**
 * 
 * 类: ClearLockListener <br>
 * 描述: 清除系统锁监听器 <br>
 * 作者: zhy<br>
 * 时间: 2016年10月31日 上午9:17:39
 */
//@WebListener
public class ClearLockListener extends ServerletLoader implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//这个类先于ServerletListener被调用就报空指针，把下面的代码拿到ServerletListener中
//		LockServiceImpl service = SpringCtxUtil.getBean(LockServiceImpl.class);
//		service.clearLock();
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
