package com.avicit.framework.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.avicit.framework.context.spring.SpringDispatcherContextHolder;

public class WebUtils extends org.springframework.web.util.WebUtils {

	private static Log logger = LogFactory.getLog(WebUtils.class);

	/**
	 * 判断是否是异步的请求、AJAX请求
	 * 
	 * @param request
	 * @return boolean
	 */
	public static boolean isAsynRequest(HttpServletRequest request) {
		if (request == null) {
			request = SpringDispatcherContextHolder.getRequest();
		}
		return (request.getHeader("x-requested-with") != null && request
				.getHeader("x-requested-with").equalsIgnoreCase(
						"XMLHttpRequest"));
	}

	public static void send(String text) {
		try {
			HttpServletResponse response = SpringDispatcherContextHolder
					.getResponse();
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write(text);
			out.close();
		} catch (Exception e) {
			logger.error("Output something to client error,error message:"
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	public static void sendFailure(String text) {
		send(JsonUtil.buildFailure(text));
	}

	public static void sendSuccess(String text) {
		send(JsonUtil.buildSuccess(text));
	}

	public static void sendPagination(List<?> list) {
		send(JsonUtil.buildPagination(list));
	}

	public static void sendArrayList(List<?> list) {
		send(JsonUtil.buildArrayList(list));
	}
}
