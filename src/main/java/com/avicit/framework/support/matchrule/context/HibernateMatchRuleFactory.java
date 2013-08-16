package com.avicit.framework.support.matchrule.context;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.avicit.framework.support.matchrule.annotation.MatchRule;

public class HibernateMatchRuleFactory {

	private Map<Field, MatchRule> matchRuleMapping = new HashMap<Field, MatchRule>();

	private List<Class<?>> matchRuleClasses = new ArrayList<Class<?>>();
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	public void addMatchRuleClass(Class<?> clazz) {
		this.matchRuleClasses.add(clazz);
		logger.info("Loading matchrule class : " + clazz.getName());
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			this.addMatchRule(field);
		}
	}

	public void addMatchRule(Field field){
		MatchRule rule = field.getAnnotation(MatchRule.class);
		if (rule != null) {
			logger.info("Loading matchrule field : " + field.getName() + " mapping : " + rule.toString());
			this.matchRuleMapping.put(field, rule);
		}
	}

	public Map<Class<?>, Map<Field, MatchRule>> getMatchRuleMap() {
		Map<Class<?>, Map<Field, MatchRule>> map = new HashMap<Class<?>, Map<Field, MatchRule>>();
		for (Class<?> clazz : this.matchRuleClasses) {
			Map<Field, MatchRule> mapping = this.getMatchRuleMap(clazz);
			map.put(clazz, mapping);
		}
		return map;
	}

	public Map<Field, MatchRule> getMatchRuleMap(Class<?> clazz) {
		Map<Field, MatchRule> map = new HashMap<Field, MatchRule>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (this.matchRuleMapping.get(field) != null) {
				map.put(field, this.matchRuleMapping.get(field));
			}
		}
		return map;
	}

	public MatchRule getMatchRule(Field field) {
		return this.matchRuleMapping.get(field);
	}
}
