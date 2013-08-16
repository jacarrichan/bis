<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="../inc/header.jsp"%>
<html>
    <head>
        <title>删除</title>
    </head>
    <body>
            =============<br/>
            确认删除吗?<br/>
             <form:form method="DELETE">
                <input type="submit" value="确认"/>|<a href="<c:url value="/user"/>">取消</a><br/>
            </form:form>
            =============<br/>
    </body>
</html>
