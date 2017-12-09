<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
* {
	font-family: "微软雅黑";
}

.navbar-brand {
	color: white !important;
	font-weight: bold;
}

body {
	padding-top: 70px;
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/framework/bootstrap/css/bootstrap.min.css" />
<script
	src="<%=request.getContextPath()%>/framework/jquery-1.11.3.min.js"></script>
<script
	src="<%=request.getContextPath()%>/framework/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/framework/holder.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%--上方的导航栏 --%>
		<%@ include file="public/top.html"%>
		<div class="row">
			<%--左边的菜单 --%>
			<%@ include file="public/left.html"%>
			<div class="col-md-10" id="leftDiv">
			</div>
		</div>
	</div>
</body>
</html>