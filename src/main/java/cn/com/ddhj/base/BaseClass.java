package cn.com.ddhj.base;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

import cn.com.ddhj.annotation.Inject;
import cn.com.ddhj.util.SpringCtxUtil;

public class BaseClass {
	public static BaseLog logger = new BaseLog();

	public BaseClass() {
		inject(this.getClass());
	}

	public void inject(Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(Inject.class)) {
				Inject inject = field.getAnnotation(Inject.class);
				String className = inject.className();
				try {
					if (StringUtils.isNotBlank(className)) {
						Object obj = this.getBean(className);
						field.setAccessible(true);
						field.set(this, obj);
					} else {
						Object obj = this.getBean(field.getType());
						field.setAccessible(true);
						field.set(this, obj);
					}
				} catch (NoSuchBeanDefinitionException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		Class<?> parentClazz = clazz.getSuperclass();
		if (parentClazz != null)
			inject(parentClazz);
	}

	public <T> T getBean(Class<T> clazz) throws BeansException {
		return (T) SpringCtxUtil.getBean(clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName) throws BeansException {
		return (T) SpringCtxUtil.getBean(beanName);
	}

	public static BaseLog getLogger() {
		return logger;
	}

}
