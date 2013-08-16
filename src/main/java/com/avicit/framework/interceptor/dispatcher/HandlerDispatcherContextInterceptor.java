package com.avicit.framework.interceptor.dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avicit.framework.context.spring.SpringDispatcherContextHolder;
import com.avicit.framework.interceptor.AbstractHandlerPreparInterceptor;

public class HandlerDispatcherContextInterceptor extends
		AbstractHandlerPreparInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) {
		SpringDispatcherContextHolder.initDispatcherContext(response);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		SpringDispatcherContextHolder.resetDispatcherContext();
	}
}
