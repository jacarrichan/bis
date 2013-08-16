package com.avicit.framework.context.spring;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 存放DispatcherSerlvlet执行的上下文
 * 
 * @see org.springframework.web.context.request.RequestContextHolder
 * **/
public class SpringDispatcherContextHolder {

	protected static final Log logger = LogFactory
			.getLog(SpringDispatcherContextHolder.class);

	private static final ThreadLocal<HttpServletResponse> responseHolder = new NamedThreadLocal<HttpServletResponse>(
			"Response Holder");

	public static void initDispatcherContext(HttpServletResponse response) {
		if (response != null) {
			responseHolder.set(response);
		}
	}

	public static void resetDispatcherContext() {
		responseHolder.remove();
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	public static HttpServletResponse getResponse() {
		return responseHolder.get();
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	/**
	 * 获取DispathcerSerlvet的WebApplicationContext，而非全局的
	 * **/
	public static WebApplicationContext getWebApplicationContext() {
		return RequestContextUtils.getWebApplicationContext(getRequest());
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T) getWebApplicationContext().getBean(name);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<?> clazz) {
		return (T)getWebApplicationContext().getBean(clazz);
	}
}
