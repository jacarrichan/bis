package com.avicit.framework.support.matchrule.annotation;

import com.avicit.framework.support.matchrule.HQLParameter.Type;

public @interface Rule {
	/**
	 * 参数名,从request中获取
	 * **/
	String name();

	/**
	 * 参数查询类型
	 * 
	 * @see {@link com.avicit.framework.support.matchrule.HQLParameter.Type}
	 * **/
	Type type() default Type.LIKE;

	/**
	 * 查询参数逃逸<br />
	 * {@link java.lang.Boolean.TRUE} 如果参数值为空则不做为查询条件<br />
	 * {@link java.lang.Boolean.FALSE} 不管如何都做为查询条件<br />
	 * */
	boolean escape() default true;

	/**
	 * 忽略参数名大小写
	 * **/
	boolean ignore() default true;

	/**
	 * 如果查询类型中有IN必须设置分隔符
	 * */
	String splitter() default ",";

	String format() default "yyyy-MM-dd";

}
