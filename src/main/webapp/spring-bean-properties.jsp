<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.springframework.beans.PropertyAccessorFactory"%>
<%@page import="org.springframework.beans.BeanWrapper"%>
<%@page import="java.beans.PropertyDescriptor"%>
<%
	Object bean = request.getAttribute("bean");
	String beanName = (String)request.getAttribute("beanName");
	BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(bean);
	PropertyDescriptor[] propertiesDesc = beanWrapper.getPropertyDescriptors();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查看Bean的属性信息</title>
	</head>
	<body>
		<center><h3><%=beanName %> 的属性信息</h3></center>
		<p> id : <%=beanName %> : <%=bean.toString() %></p>
		<p> Class : <%=bean.getClass().getCanonicalName() %> </p>
		
		<table border="1px">
			<tr>
				<td>属性</td>
				<td>值</td>
				<td>类型</td>
			</tr>
			<%
				for(PropertyDescriptor desc : propertiesDesc){
					try{
						out.println("<tr>");
						out.println("<td> " + desc.getName() + " </td>");
						out.println("<td> " + beanWrapper.getPropertyValue(desc.getName()) + " </td>");
						out.println("<td> " + beanWrapper.getPropertyType(desc.getName()).getCanonicalName() + " </td>");
						out.println("</tr>");
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
				
				}
			%>
		</table>
	</body>
</html>