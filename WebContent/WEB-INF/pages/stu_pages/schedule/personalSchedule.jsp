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
	padding-top: 60px;
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/framework/bootstrap/css/bootstrap.min.css" />
<script
	src="<%=request.getContextPath()%>/framework/jquery-1.11.3.min.js"></script>
<script
	src="<%=request.getContextPath()%>/framework/bootstrap/js/bootstrap.min.js"></script>
<script src="<%=request.getContextPath()%>/framework/holder.min.js"></script>
<script
	src="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table.min.css" />
<script
	src="<%=request.getContextPath()%>/framework/bootstrap-table/bootstrap-table-zh-CN.js"
	charset="UTF-8"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<%--上方的导航栏 --%>
		<%@ include file="../public/top.html"%>
		<div class="row">
			<%--左边的菜单 --%>
			<%@ include file="../public/left.html"%>
			<div class="col-md-9" id="leftDiv">
				<!--
                	作者：2574772445@qq.com
                	时间：2017-11-12
                	描述：右侧设计
                -->
				<table id="mytab" class="table table-hover"></table>
				<!-- 右侧设计结束 -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//生成用户数据
	$('#mytab').bootstrapTable({
		method : 'post',
		contentType : "application/x-www-form-urlencoded",//必须要有！！！！
		url : "<%=request.getContextPath()%>/stu/schedule/personalScheduleRes.json",//要请求数据的文件路径
		striped : true, //是否显示行间隔色
		dataField : "res",
		pagination : false,//是否分页
		queryParamsType : 'limit',//查询参数组织方式
		sidePagination : 'server',//指定服务器端分页

		clickToSelect : true,//是否启用点击选中行
		toolbarAlign : 'right',//工具栏对齐方式
		buttonsAlign : 'right',//按钮对齐方式
		//toolbar : '#toolbar',//指定工作栏
		width: 500,
		columns : [
		{
			title : '',
			field: 'time',
			width: '80'
		},  {
			title : '星期一',
			field : 'mon',
			formatter: formatter,
			width: '80'
		}, {
			title : '星期二',
			field : 'tues',
			formatter: formatter,
			width: '80'
		},
		{
			title : '星期三',
			field : 'wed',
			formatter: formatter,
			width: '80'
		}, {
			title : '星期四',
			field : 'thur',
			formatter: formatter,
			width: '80'
		}, {
			title : '星期五',
			field : 'fri',
			formatter: formatter,
			width: '80'
		}, {
			title : '星期六',
			field : 'sat',
			formatter: formatter,
			width: '80'
		}, {
			title : '星期天',
			field : 'sun',
			formatter: formatter,
			width: '80'
		}],
		locales : 'zh-CN', //中文支持,
		responseHandler : function(res) {
			//在ajax获取到数据，渲染表格之前，修改数据源
			return res;
		}
	})
	function formatter(value, row, index){
		if(value == null){
			return '<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h4>';
		}
		else{
			return value;
		}
	}
	
  </script>
</html>