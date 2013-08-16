package com.avicit.framework.support.matchrule;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;

import com.avicit.framework.context.spring.SpringDispatcherContextHolder;
import com.avicit.framework.support.matchrule.HQLParameter.Type;
import com.avicit.framework.support.matchrule.annotation.MatchRule;
import com.avicit.framework.support.matchrule.annotation.Rule;
import com.avicit.framework.support.matchrule.context.HibernateMatchRuleContext;

public class MatchRuleProcessor {

	private HQLParameter parameter;

	private Map<Field, MatchRule> matchRuleMap;

	public HQLParameter process(HQLParameter parameter) {
		this.matchRuleMap = HibernateMatchRuleContext.getMatchRuleMap(parameter
				.getTargetClass());
		this.parameter = parameter;
		Set<Field> keySet = this.matchRuleMap.keySet();
		for (Field field : keySet) {
			Rule[] rules = this.matchRuleMap.get(field).value();
			for (Rule rule : rules) {
				Object[] values = this.getTransformValue(field, rule);
				if (values != null && values.length > 0) {
					for (Object object : values) {
						parameter.add(field.getName(), rule.type(), object);
					}
				}
			}
		}
		return this.parameter;
	}

	public Object[] getSplitedValue(Field field, String[] values, MatchRule rule) {
		List<Object> result = new ArrayList<Object>();
		return result.toArray();
	}

	public boolean useSplitter(Type type) {
		return type.equals(Type.IN);
	}

	public Object[] getTransformValue(Field field, Rule rule) {
		return this.getTransformValue(field,
				this.getRequestParameterValue(rule.name()), rule);
	}

	public Object[] getTransformValue(Field field, String[] values,
			String format) {
		Class<?> clazz = field.getType();
		if (values != null && values.length > 0) {
			if (clazz.equals(Integer.class)) {
				Integer[] result = new Integer[values.length];
				int i = 0;
				for (String value : values) {
					result[i] = Integer.valueOf(value);
					i++;
				}
				return result;
			}

			if (clazz.equals(Long.class)) {
				Long[] result = new Long[values.length];
				int i = 0;
				for (String value : values) {
					result[i] = Long.valueOf(value);
					i++;
				}
				return result;
			}

			if (clazz.equals(Double.class)) {
				Double[] result = new Double[values.length];
				int i = 0;
				for (String value : values) {
					result[i] = Double.valueOf(value);
					i++;
				}
				return result;
			}

			if (clazz.equals(Date.class)) {
				Date[] result = new Date[values.length];
				int i = 0;
				for (String value : values) {
					try {
						result[i] = DateUtils.parseDate(value,
								new String[] { format });
					} catch (ParseException e) {
						e.printStackTrace();
					}
					i++;
				}
				return result;
			}
			
			return values;
		}
		return null;
	}

	public Object[] getTransformValue(Field field, String[] values, Rule rule) {
		if (values != null && values.length > 0) {
			if (!rule.type().equals(Type.IN)) {
				return this.getTransformValue(field, values, rule.format());
			} else {
				List<Object> result = new ArrayList<Object>();
				for (String value : values) {
					result.add(this.getTransformValue(field,
							value.split(rule.splitter()), rule.splitter()));
				}
				return result.toArray();
			}
		}
		return null;
	}

	public Map<String, String[]> getRequestParameterMap() {
		return SpringDispatcherContextHolder.getRequest().getParameterMap();
	}

	public String[] getRequestParameterValue(String name) {
		return this.getRequestParameterMap().get(name);
	}
}
