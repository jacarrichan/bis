<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@ include file="../inc/header.jsp"%>
<html>
<head>
<title>列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<a href="<c:url value='/user/add'/>">新增</a>
	<br />
	<table border="1">
		<tr>
			<th>编号</th>
			<th>用户名</th>
			<th>电子邮箱</th>
			<th>密码</th>
			<th>注册时间</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${page.items}" var="t" varStatus="status">
			<tr>
				<td>${ t.id }</td>
				<td>${ t.username }</td>
				<td>${ t.email }</td>
				<td>${ t.password }</td>
				<td>${ t.registerDate }</td>
				<td><a href="<c:url value='/user/${t.id}/delete'/>">删除</a>|<a
					href="<c:url value='/user/${t.id}/update'/>">修改</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<common:pageV2 url="/user" optimize="true" />
</body>
</html>
