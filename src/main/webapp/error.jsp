<%@ page language="java" pageEncoding="utf-8" isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="com.avicit.framework.util.WebUtils"%>
<%
	String code = request.getParameter("code");
	StringWriter sw = new StringWriter();
	String error = "";
	if(exception != null){
		exception.printStackTrace(new PrintWriter(sw));
		error = sw.toString();
	}
	if (WebUtils.isAsynRequest(request)) {
		if (exception != null) {
			out.println(exception.getMessage());
		}else{
			out.println("访问" + request.getRequestURI() + "发生错误!");
		}
	} else {
%>
<html>
<head>
<title>错误页面</title>
</head>
<body>
	<%
		out.println("程序发生错误，错误代码：" +code+ "，错误信息:访问" + request.getRequestURI() + "发生错误!<br />" + error);
	%>
</body>
</html>
<%
	}
%>
