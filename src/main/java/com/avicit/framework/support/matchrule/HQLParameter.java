package com.avicit.framework.support.matchrule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 查询参数配置
 * */
public class HQLParameter{

	private String alias;
	
	private Class<?> targetClass;

	/**
	 * 查询参数类型
	 * **/
	public static enum Type {
		EQUALS, NOTEQUALS, LESSTHAN, MORETHAN, LIKE, IN, NOTNULL, NULL
	}

	public static Map<Type, String> typeMapping = new HashMap<Type, String>();

	static {
		typeMapping.put(Type.EQUALS, " = ");
		typeMapping.put(Type.NOTEQUALS, " <> ");
		typeMapping.put(Type.LESSTHAN, " < ");
		typeMapping.put(Type.MORETHAN, " > ");
		typeMapping.put(Type.LIKE, " like ");
		typeMapping.put(Type.IN, " in ");
		typeMapping.put(Type.NOTNULL, " is not null ");
		typeMapping.put(Type.NULL, " is null");
	}

	private StringBuilder query = new StringBuilder();

	private List<Object> parameterValues = new ArrayList<Object>();

	private boolean exist = false;

	public Class<?> getTargetClass() {
		return this.targetClass;
	}
	
	public HQLParameter(Class<?> clazz){
		this.targetClass = clazz;
		this.alias = this.getTargetClassName().toLowerCase()+"_";
		this.query.append("select * from " + this.getTargetClassName() + " " + this.alias);
		new MatchRuleProcessor().process(this);
	}

	public String getTargetClassName() {
		return this.getTargetClass().getSimpleName();
	}

	public String toString() {
		return this.query.toString();
	}

	public HQLParameter add(String field, HQLParameter.Type type, Object... values) {
		try {
			if (exist) {
				query.append(" and ");
			}else{
				query.append(" where ");
			}
			query.append(alias + "." + field + " " + typeMapping.get(type));
			if (type.equals(Type.IN)) {
				query.append(" (");
				boolean added = false;
				for (Object value : values) {
					Object[] v = (Object[]) value;
					for (Object object : v) {
						if (added) {
							query.append(",");
						}
						query.append("?");
						parameterValues.add(object);
						added = true;
					}
				}
				query.append(")");
			}

			else if (type.equals(Type.LIKE)) {
				query.append(" %?% ");
				parameterValues.add(values);
			}

			else if (!type.equals(Type.NOTNULL) && !type.equals((Type.NULL))) {
				query.append(" ? ");
				parameterValues.add(values);
			}

			exist = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return this;
	}
}
