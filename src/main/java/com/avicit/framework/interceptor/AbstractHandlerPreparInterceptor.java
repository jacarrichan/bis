package com.avicit.framework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 抽象类，在用户访问系统资源之前进行拦截
 * @author liqiu
 * @version 1.0
 * @since 1.0
 * */
public abstract class AbstractHandlerPreparInterceptor  implements HandlerInterceptor{

	@Override
	public abstract boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler);

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public  void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception{
	}
	
}
