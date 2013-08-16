package com.avicit.framework.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.avicit.framework.util.WebUtils;

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

	private static Log logger = LogFactory.getLog(GlobalExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("用户" + WebUtils.getSessionId(request) + "访问"
				+ request.getRequestURI() + "发生错误,错误信息:" + ex.getMessage());
		ex.printStackTrace();
		return null;
	}
}
