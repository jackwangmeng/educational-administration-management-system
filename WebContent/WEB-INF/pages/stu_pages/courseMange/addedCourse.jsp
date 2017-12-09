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
				<table id="mytab" class="table table-hover"></table>
				<!-- 右侧设计结束 -->
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	//表格中按钮的点击事件
	//必须写在$('#mytab').bootstrapTable的前面不然会报错
/* 	window.events = {
		"click .addCourse":function(value){
			console.log("被点击的按钮： " + value);
			console.log("row： " + row);
			console.log("index: "  + index);
			//ajax选课
	 		var url='studentAddCourse';
			var args={'openedCourseId':value};
			$.post(url, args, function(data){
				alert(data.alertMessage);		
			}); 
		}
	}; */
	
	function deleteCourse(value, index){
		var tag = confirm("确定要退选");
		if(tag == false){
			return false;
		}
		var url = "<%=request.getContextPath()%>/stu/courseMange/deleteCourse.do";
		var args = {"courseId": value};
		$.post(url, args, function(data){
			if(data == "failed"){
				alert("退选失败！");
			}
			else if(data == "success"){
				alert("退选成功！");
				$('#mytab').bootstrapTable('hideRow', {index:index});
			}
			else{
				alert("系统异常！");
			}
		});
	}
	
	//生成用户数据
	$('#mytab').bootstrapTable({
		method : 'post',
		contentType : "application/x-www-form-urlencoded",//必须要有！！！！
		url : "<%=request.getContextPath()%>/stu/courseMange/addedCourseTableRes.json",//要请求数据的文件路径
		height : tableHeight(),//高度调整
		//toolbar : '#toolbar',//指定工具栏
		striped : true, //是否显示行间隔色
		dataField : "res",//bootstrap table 可以前端分页也可以后端分页，这里
		//我们使用的是后端分页，后端分页时需返回含有total：总记录数,这个键值好像是固定的  
		//rows： 记录集合 键值可以修改  dataField 自己定义成自己想要的就好
		pageNumber : 1, //初始化加载第一页，默认第一页
		pagination : true,//是选否分页
		queryParamsType : 'limit',//查询参数组织方式
		queryParams : queryParams,//请求服务器时所传的参数
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
		/*         {
		 title:'全选',
		 field:'select',
		 //复选框
		 checkbox:true,
		 width:25,
		 align:'center',
		 valign:'middle'
		 }, */
		{
			title : '课程ID',
			field : 'course.courseTemple.id'
		},{
			title : '课程类别',
			field : 'course.courseTemple.courseKind'
		},{
			title : '课程名',
			field : 'course.courseTemple.name'
		},{
			title : '学分',
			field : 'course.courseTemple.point'
		},{
			title : '老师',
			field : 'course.teacher.name'
		},{
			title : '上课时间',
			field : 'course.time'
		},{
			title : '上课地点',
			field : 'course.room'
		},{
			title :'操作',
			field :'course.id',
			formatter: operateFormatter
		}],
		locales : 'zh-CN', //中文支持,
		responseHandler : function(res) {
			//在ajax获取到数据，渲染表格之前，修改数据源
			return res;
		}
	});
	
	//三个参数，value代表该列的值
	function operateFormatter(value, row, index) {
		console.log("value: " + value);
		var button =  '<button class="btn btn-sm" onclick=deleteCourse('  +  value  
			+ ',' +  index +')>退选</button>';
		console.log(button);
		return button;
	}

	function queryParams(params) {
		return {
			//每页多少条数据
			pageSize : params.limit,
			//请求第几页
			offset : params.offset
		}
	}
	
	//tableHeight函数
	function tableHeight() {
		//可以根据自己页面情况进行调整
		return $(window).height() - 254;
	}
	
  </script>
</html>