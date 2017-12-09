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
			<div class="col-md-10" id="leftDiv">
				<!--
                	作者：2574772445@qq.com
                	时间：2017-11-12
                	描述：右侧设计
                -->
				<div class="panel panel-default">
					<div class="panel-heading">查询条件</div>
					<div class="panel-body form-group" style="margin-bottom: 0px;">
						<form class="form-inline">
							<div class="form-group col-md-4">
								<label for="day">开课时间：</label> 
								<select class="combobox form-control" id="courseTerm">
									<option value="">----</option>
									<c:forEach items="${terms }" var="term" >
										<option value="${term}">${term }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-md-4">
								<label for="courseKind">课程类别：</label> <select
									class="combobox form-control" id="courseKind">
									<option value="">-----</option>
									<c:forEach items="${courseKinds}" var="item">
										<option value="${item}">${item }</option>
									</c:forEach>
								</select>
							</div>
							<button class="btn btn-default" id="search_btn">查询</button>
							&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" id="reset_btn">重置</button>
						</form>
						<br>
						<form class="form-inline">
							<div class="form-group col-md-5">
								<label for="courseName">课程名称： </label> <input type="text"
									class="form-control" id="courseName">
							</div>
						</form>
						<br>
					</div>
				</div>
				<table id="mytab" class="table table-hover"></table>
				<!-- 右侧设计结束 -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	//查询条件重置
	$("#reset_btn").click(function(){
		document.getElementById("courseName").value="";
		document.getElementById("courseTerm").value="";
		document.getElementById("courseKind").value="";
		return false;
	});

	
	//生成用户数据
	$('#mytab').bootstrapTable({
		method : 'post',
		contentType : "application/x-www-form-urlencoded",//必须要有！！！！
		url : "<%=request.getContextPath()%>/stu/courseScore/scoreRes.json",//要请求数据的文件路径
		height : tableHeight(),//高度调整
		//toolbar : '#toolbar',//指定工具栏
		striped : true, //是否显示行间隔色
		dataField : "res",//bootstrap table 可以前端分页也可以后端分页，这里
		//我们使用的是后端分页，后端分页时需返回含有total：总记录数,这个键值好像是固定的  
		//rows： 记录集合 键值可以修改  dataField 自己定义成自己想要的就好
		queryParams : queryParams,//请求服务器时所传的参数
		pageNumber : 1, //初始化加载第一页，默认第一页
		pagination : true,//是否分页
		queryParamsType : 'limit',//查询参数组织方式
		sidePagination : 'server',//指定服务器端分页
		pageSize : 8,//单页记录数
		pageList : [2, 5, 8, 10, 20, 30 ],//分页步进值
		/*     showRefresh:true,//刷新按钮
		 showColumns:true, */

		clickToSelect : true,//是否启用点击选中行
		toolbarAlign : 'right',//工具栏对齐方式
		buttonsAlign : 'right',//按钮对齐方式
		//toolbar : '#toolbar',//指定工作栏
		columns : [
		{
			title : '序号',
			formatter:function(value, row, index){
				return index + 1;
			}
		},  {
			title : '开课学期',
			field : 'course.term'
		}, {
			title : '课程编号',
			field : 'course.courseTemple.id'
		},
		{
			title : '课程名称',
			field : 'course.courseTemple.name'
		}, {
			title : '成绩',
			field : 'score',
			formatter:function(value, row, index){
				return '<font color="blue">' + value + '</font>';
			}
		}, {
			title : '学分',
			field : 'course.courseTemple.point'
		}, {
			title : '学时',
			field : 'course.courseTemple.hour'
		}, {
			title : '属性',
			field : 'course.courseTemple.importance',
			formatter:function(value, row, index){
				if(value == 0){
					return '选修';
				}
				else{
					return '必修';
				}
			}
		}, {
			title : '类别',
			field : 'course.courseTemple.courseKind'
		}],
		locales : 'zh-CN', //中文支持,
		responseHandler : function(res) {
			//在ajax获取到数据，渲染表格之前，修改数据源
			return res;
		}
	})
	
	function queryParams(params) {
		return {
			//每页多少条数据
			pageSize : params.limit,
			//请求第几页
			offset : params.offset,
			
			//查询条件
 			courseTerm: $('#courseTerm').val(),
			courseKind: $('#courseKind').val(),
			courseName: $('#courseName').val()
		}
	}
	
	$('#search_btn').click(function() {
		console.log("按钮被点击了！");
		$('#mytab').bootstrapTable('refreshOptions', {pageNumber: 1});
		//由于search_btn在表单中,不return false会导致页面被刷新
		return false;
	})
	
	//tableHeight函数
	function tableHeight() {
		//可以根据自己页面情况进行调整
		return $(window).height() - 254;
	}
  </script>
</html>