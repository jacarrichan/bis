<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="org.springframework.beans.factory.config.BeanDefinition"%>
<%@page import="com.avicit.framework.context.spring.SpringApplicationContextHolder"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	 Map<String, BeanDefinition> beanMap = (Map<String, BeanDefinition>)request.getAttribute("beanMap");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Spring Beans Browser</title>
	</head>
	<body>
		<center><h3>Spring 容器中 Bean 定义信息 (共 <%=beanMap.size() %> 个Bean)</h3></center>
		<table border="1px">
			<tr>
				<td width="10%">ID</td>
				<td>class</td>
				<td>scope</td>
				<td nowrap="nowrap"> 配置是否正确?</td>
				<td>configLocation</td>
			</tr>
			<%
				Object[] beanNames = beanMap.keySet().toArray();
				Arrays.sort(beanNames);
				for(Object o : beanNames){
					String beanName = (String)o;
					BeanDefinition beanDef = beanMap.get(beanName);
					
					out.println("<tr>");
					
					out.println("<td><a href=\"spring-bean-properties?beanName=" + beanName + "\">" + beanName + "</a></td>");
					out.println("<td>" + beanDef.getBeanClassName() + "</td>");
					out.println("<td>" + beanDef.getScope() + "</td>");
					
					//bean配置是否正确？ Bean名称为*Action的bean必须定义为原型模式
					boolean isCorrect = beanName.indexOf("Action") > 0 ? beanDef.getScope().equalsIgnoreCase("prototype") : true;
					if (isCorrect){
						out.println("<td>" + isCorrect  + "</td>");
					}else{
						out.println("<td style=\"background-color: red;\"><strong>" + isCorrect  + "</strong></td>");
					}
					out.println("<td>" + beanDef.getResourceDescription() + "</td>");
					
					out.println("</tr>");
				}
			%>
		</table>
	</body>
</html>