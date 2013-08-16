package com.avicit.framework.context.spring;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext; // Spring应用上下文环境

	protected static Log logger = LogFactory
			.getLog(SpringApplicationContextHolder.class);

	public static ApplicationContext getWebApplicationContext() {
		return applicationContext;
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;		
	}
}
