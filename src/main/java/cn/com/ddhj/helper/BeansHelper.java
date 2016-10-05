package cn.com.ddhj.helper;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.ddhj.base.BaseClass;

/**
 * @author HJY
 * 
 */
public class BeansHelper extends BaseClass {

	private static BeanFactory beanFactory = null;

	private static int flagInit = 0;

	private static Object getBeanObject(String name) {

		if (flagInit < 1 || beanFactory == null) {
			flagInit = 2;
			if (flagInit == 2) {
				flagInit = 3;
				if (beanFactory == null) {
					new BeansHelper().initBeanFactory();
				}
			}
		}

		Object oReturn = null;

		// 尝试返回 如果失败二次返回
		try {
			oReturn = beanFactory.getBean(name);
		} catch (Exception e) {
			e.printStackTrace();
			beanFactory = null;
			new BeansHelper().initBeanFactory();
			oReturn = beanFactory.getBean(name);

		}

		return oReturn;
	}

	// alias upBean
	@SuppressWarnings("unchecked")
	public static <T> T upBean(String sBeanName) {
		return (T) getBeanObject(sBeanName);
	}

	@SuppressWarnings("resource")
	private synchronized void initBeanFactory() {

		if (beanFactory == null) {
			String[] sSpringConfig = new String[] { "classpath*:/spring/spring/*.xml" };
			beanFactory = new ClassPathXmlApplicationContext(sSpringConfig).getBeanFactory();

		}

	}

}
