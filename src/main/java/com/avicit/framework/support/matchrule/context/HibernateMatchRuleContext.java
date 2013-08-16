package com.avicit.framework.support.matchrule.context;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.avicit.framework.support.matchrule.annotation.MatchRule;

@Component
public class HibernateMatchRuleContext implements ServletContextAware {

	public static final String HIBERNATE_MATCHRULE_CONTEXT = "hiberate_match_rule_context";

	private static ServletContext servletContext;

	@Override
	public void setServletContext(ServletContext servletContext) {
		HibernateMatchRuleContext.servletContext = servletContext;
	}
	
	public static ServletContext getServletContext() {
		return HibernateMatchRuleContext.servletContext;
	}

	public static void setMatchRuleFactory(HibernateMatchRuleFactory factory) {
		servletContext.setAttribute(HIBERNATE_MATCHRULE_CONTEXT, factory);
	}

	public static HibernateMatchRuleFactory getMatchRuleFactory() {
		return (HibernateMatchRuleFactory) servletContext
				.getAttribute(HIBERNATE_MATCHRULE_CONTEXT);
	}
	
	public static Map<Field,MatchRule> getMatchRuleMap(Class<?> clazz){
		return getMatchRuleFactory().getMatchRuleMap(clazz);
	}
}
