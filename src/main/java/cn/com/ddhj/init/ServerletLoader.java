package cn.com.ddhj.init;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.ddhj.service.impl.system.LockServiceImpl;
import cn.com.ddhj.util.SpringCtxUtil;

/**
 * Serverlet加载时调用
 * 
 * 该类兼容两种调用模式 一种是由<br>
 * {@link ServerletListener#contextInitialized} 调用,<br>
 * 此调用模式需要在web.xml增加listener <br>
 * 另外一种是由继承{@link WebApplicationInitializer#onStartup}的调用<br>
 * 此调用模式支持spring的注解式调用<br>
 * 两种调用模式用{@linkplain #bFlagLoad}参数来防止重复调用
 * 
 * 
 * @author HJY
 * 
 */
public class ServerletLoader {

	/**
	 * 是否已加载 该参数标记该初始化操作是否已加载 默认初始化只调用一次
	 */
	private static boolean bFlagLoad = false;

	/**
	 * 初始化类
	 * 
	 * 
	 * @param servletContext
	 */
	public synchronized boolean init(ServletContext servletContext) {
		boolean bFlagSuccess = true;
		if (!bFlagLoad) {
			bFlagLoad = true;
			try {
				servletContext.log("Initializing HJY web core");
				WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
				SpringCtxUtil.setApplicationContext(wac);

				servletContext.log(servletContext.getRealPath(""));

				servletContext.log("Initializing HJY web core finished");
				
				LockServiceImpl service = SpringCtxUtil.getBean(LockServiceImpl.class);
				service.clearLock();
			} catch (RuntimeException ex) {
				bFlagSuccess = false;
				servletContext.log("Error occurs in initializing HJY web core" + ex.getMessage());
			}
		}

		return bFlagSuccess;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	public void onStartup(ServletContext servletContext) throws ServletException {

		if (!bFlagLoad) {
			if (!init(servletContext)) {
				servletContext.log(this.getClass().getName() + " Error onStartup");
				throw new ServletException("init error");
			}
		}
	}
}
