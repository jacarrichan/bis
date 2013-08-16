package com.avicit.bis.system.spring.vo;

public class URLMapping {

	private String url;
	
	private String className;
	
	private String methodName;
	
	private String returnType;
	
	private String annotationName;
	
	private String consumes;
	
	private String custom;
	
	private String headers;
	
	private String params;
	
	private String methods;
	
	private String produces;
	
	private String parameters;

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public String getConsumes() {
		return consumes;
	}

	public void setConsumes(String consumes) {
		this.consumes = consumes;
	}

	public String getCustom() {
		return custom;
	}

	public void setCustom(String custom) {
		this.custom = custom;
	}

	public String getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = headers;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMethods() {
		return methods;
	}

	public void setMethods(String methods) {
		this.methods = methods;
	}

	public String getProduces() {
		return produces;
	}

	public void setProduces(String produces) {
		this.produces = produces;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getAnnotationName() {
		return annotationName;
	}

	public void setAnnotationName(String annotationName) {
		this.annotationName = annotationName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
