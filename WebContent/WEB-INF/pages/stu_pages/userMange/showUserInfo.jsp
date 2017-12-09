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
				<div class="panel panel-default">
					<div class="panel-heading">个人信息</div>
					<div class="panel-body form-group" style="margin-bottom: 0px;">
						<table class="table table-bordered table-striped">
							<tr>
								<td>学号：</td>
								<td>${student.id }</td>
							</tr>
							<tr>
								<td>姓名：</td>
								<td>${student.name }</td>
							</tr>
							<tr>
								<td>学院：</td>
								<td>${student.cls.major.college }</td>
							</tr>
							<tr>
								<td>专业：</td>
								<td>${student.cls.major.name }</td>
							</tr>
							<tr>
								<td>班级：</td>
								<td>${student.cls.grade }级${student.cls.major.name}${student.cls.number}班</td>
							</tr>
							<tr>
								<td>类型：</td>
								<td>${student.kind}</td>
							</tr>
						</table>
						<br>
						<form>
							<table class="table table-bordered table-striped">
								<tr>
									<td>电话号码：</td>
									<td>
										<div class="col-sm-5">
											<input type="text" class="form-control" value="${student.phone}" id="phone"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>电子邮件：</td>
									<td>
										<div class="col-sm-5">
											<input type="email" class="form-control" value="${student.email }" id="email"/>
										</div>
									</td>
								</tr>
								<tr>
									<td>家庭住址</td>
									<td>
										<div class="col-sm-5">
											<input type="text" class="form-control" value="${student.adress }" id="adress"/>
										</div>
									</td>
								</tr>
							</table>
							<button class="btn btn-default" id="submit">提交</button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" id="reset">重置</button>
						</form>
						<div></div>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	var email = "${student.email}";
	var phone = "${student.phone}";
	var adress = "${student.adress}";
	$("#submit").click(function(){
		var tag = confirm("确定要修改吗？");
		if(tag == false){
			return false;
		}
		var email = $("#email").val();
		var phone = $("#phone").val();
		var adress = $("#adress").val();
		
		var args = {"email":email, "phone":phone, "adress":adress};
		var url = "<%=request.getContextPath()%>/stu/userMange/changeUserInfo.do";
		
		$.post(url, args, function(data){
			if(data == "success"){
				alert("修改成功！");
			}
			else if(data == "failed"){
				alert("修改失败！");
			}
			else{
				alert("系统错误！");
			}
		})
		return false;
	});
	
	$("#reset").click(function(){
		console.log("reset点击了！");
		document.getElementById("phone").value = phone;
		document.getElementById("adress").value = adress;
		document.getElementById("email").value = email;
		return false;
	});
</script>
</html>