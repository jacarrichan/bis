package com.avicit.bis.system.simple.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.avicit.bis.system.simple.service.SimpleService;
import com.avicit.framework.context.spring.SpringContextBeanFactory;

@Controller
public class SimpleController {

	protected static final Log logger = LogFactory.getLog(SimpleController.class);

	@Autowired
	protected SimpleService simpleService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// HibernateMatchRuleFactory factory =
		// HibernateMatchRuleContext.getMatchRuleFactory();
		return "index";
	}

	@RequestMapping(value = "/spring-application-browser", method = RequestMethod.GET)
	public ModelAndView browserSpringApplication(HttpServletRequest request, HttpServletResponse response) {
		Map<String, BeanDefinition> map = SpringContextBeanFactory.getApplicationBeanDefinitions();
		ModelAndView mv = new ModelAndView();
		mv.addObject("beanMap", map);
		return mv;
	}

	@RequestMapping(value = "/spring-dispatcher-browser", method = RequestMethod.GET)
	public Model browserSpringDispatcher(HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, BeanDefinition> map = SpringContextBeanFactory.getDispatcherBeanDefinitions();
		model.addAttribute("beanMap", map);
		return model;
	}

	@RequestMapping(value = "/spring-bean-properties", method = RequestMethod.GET)
	public Model browserBeanProperties(@RequestParam("beanName") String beanName, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Object bean = SpringContextBeanFactory.getBean(beanName);
		logger.debug(bean);
		model.addAttribute("bean", bean);
		model.addAttribute("beanName", beanName);
		return model;
	}
}
