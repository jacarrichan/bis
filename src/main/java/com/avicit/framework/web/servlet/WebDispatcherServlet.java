package com.avicit.framework.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.avicit.framework.context.spring.SpringDispatcherContextHolder;

@Deprecated
public class WebDispatcherServlet extends DispatcherServlet{

	private static final long serialVersionUID = 1L;

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SpringDispatcherContextHolder.initDispatcherContext(response);
		super.doService(request, response);
	}
}
