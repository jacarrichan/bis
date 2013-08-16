package com.avicit.framework.context.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringContextBeanFactory {

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		try {
			return (T) SpringApplicationContextHolder
					.getWebApplicationContext().getBean(name);
		} catch (Exception e) {
			return (T) SpringDispatcherContextHolder.getWebApplicationContext()
					.getBean(name);
		}
	}

	public static <T> T getBean(Class<T> clazz) {
		try {
			return SpringApplicationContextHolder.getWebApplicationContext()
					.getBean(clazz);
		} catch (Exception e) {
			return SpringDispatcherContextHolder.getWebApplicationContext()
					.getBean(clazz);
		}

	}

	public static <T> T getBean(String name, Class<T> clazz) {
		try {
			return getBean(name);
		} catch (Exception e) {
			try {
				return getBean(clazz);
			} catch (Exception ex) {
				return null;
			}
		}
	}

	public static Map<String, BeanDefinition> getApplicationBeanDefinitions() {
		Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();
		XmlWebApplicationContext context = (XmlWebApplicationContext) SpringApplicationContextHolder
				.getWebApplicationContext();
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		String[] names = factory.getBeanDefinitionNames();
		for (String name : names) {
			map.put(name, factory.getBeanDefinition(name));
		}
		return map;
	}

	public static Map<String, BeanDefinition> getDispatcherBeanDefinitions() {
		Map<String, BeanDefinition> map = new HashMap<String, BeanDefinition>();
		XmlWebApplicationContext context = (XmlWebApplicationContext) SpringDispatcherContextHolder
				.getWebApplicationContext();
		ConfigurableListableBeanFactory factory = context.getBeanFactory();
		String[] names = factory.getBeanDefinitionNames();
		for (String name : names) {
			map.put(name, factory.getBeanDefinition(name));
		}
		return map;
	}

}
