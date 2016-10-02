package cn.com.ddhj.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import cn.com.ddhj.util.SpringCtxUtil;

public class BaseTest implements ApplicationContextAware {
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		SpringCtxUtil.setApplicationContext(context);
	}
}
