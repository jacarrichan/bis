package com.avicit.bis.system.spring.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;


import com.avicit.bis.system.spring.vo.URLMapping;
import com.avicit.framework.context.spring.SpringContextBeanFactory;
import com.avicit.framework.util.ResponseUtils;

/***
 * Spring相关控制器
 * */
@Controller
@RequestMapping("spring")
public class SpringController {

	/**
	 * 获取Spring映射
	 * **/
	@RequestMapping("url-mapping")
	public @ResponseBody
	Object getURLMapping() {
		List<URLMapping> list = new ArrayList<URLMapping>();
		Map<RequestMappingInfo, HandlerMethod> map2 = SpringContextBeanFactory
				.getBean(RequestMappingHandlerMapping.class)
				.getHandlerMethods();
		for (Iterator<RequestMappingInfo> iterator = map2.keySet().iterator(); iterator
				.hasNext();) {
			RequestMappingInfo info = iterator.next();
			URLMapping m = new URLMapping();
			m.setConsumes(String.valueOf(info.getConsumesCondition()));
			m.setCustom(String.valueOf(info.getCustomCondition()));
			m.setHeaders(String.valueOf(info.getHeadersCondition()));
			m.setMethods(String.valueOf(info.getMethodsCondition()));
			m.setParams(String.valueOf(info.getParamsCondition()));
			m.setProduces(String.valueOf(info.getProducesCondition()));
			m.setUrl(info.getPatternsCondition().toString());
			HandlerMethod method = map2.get(info);
			m.setMethodName(method.getMethod().getName());
			m.setClassName(method.getBeanType().getName());
			m.setReturnType(method.getReturnType().getParameterType()
					.toString());
			MethodParameter[] parameters = method.getMethodParameters();
			List<String> list2 = new ArrayList<String>();
			for (MethodParameter methodParameter : parameters) {
				list2.add(methodParameter.getParameterType().getName());
			}
			m.setParameters(String.valueOf(list2));
			ResponseBody annotationClass = method.getMethodAnnotation(ResponseBody.class);
			if(annotationClass != null){
				m.setAnnotationName(annotationClass.toString());
			}
			list.add(m);
		}
		return ResponseUtils.sendList(list);
	}
}
