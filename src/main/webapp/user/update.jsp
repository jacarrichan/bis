<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="../inc/header.jsp"%>
<html>
    <head>
        <title>修改</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
    </head>
    <body>
            修改<br/>
            =============<br/>
            <form:form method="PUT">
                <form:errors path="*"/><br/>

                <label for="username" class="label">用户名:</label>
                <form:input path="username"/><br/>

                <label for="email" class="label">电子邮箱:</label>
                <form:input path="email"/><br/>

                <label for=password class="label">密码:</label>
                <form:input path="password"/><br/>


                <label for="registerDate" class="label">注册时间:</label>
                <input type="text" name="registerDate" value="${command.registerDate}"
                                readonly="readonly"
                                class="Wdate"
                                onFocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>(格式2010-1-1 11:1:1)<br/>

                <label class="label"/><input type="submit" value="提交"/>&nbsp;<a href="<c:url value="/user"/>">取消</a><br/>
            </form:form>
            =============<br/>
    </body>
</html>
