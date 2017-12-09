<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>南昌大学教务管理系统登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
* {
	font-family: "微软雅黑";
}

.navbar-brand {
	color: white !important;
	font-weight: bold;
}

body {
	background-image:url("images/login_bg.jpeg");
	background-position:center;
	background-size:100% 100%;
	background-attachment: fixed;
	background-repeat:no-repeat;      
}

.login {
	width: 400px;
	height: 400px;
	background: #ccc;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-left: -200px;
	margin-top: -200px;
	border-radius: 10px;
}

.goto_enter {
	text-align: center;
}
</style>

<link rel="stylesheet" href="framework/bootstrap/css/bootstrap.min.css" />
<script src="framework/jquery-1.11.3.min.js"></script>
<script src="framework/bootstrap/js/bootstrap.min.js"></script>
<script src="framework/holder.min.js"></script>
</head>
<body>
	<!-- <img src="images/login_bg.jpeg" width="100%" height="100%"> -->
	<div class="panel panel-primary login">
		<div class="panel-heading">
			<div class="panel-title goto_enter">
				<h2>登录</h2>
			</div>
		</div>
		<br>
		<div class="panel-body">
			<form action="login.do" method="post">
				<div class="form-group">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-user"></span>
						</span> 
						<input type="text" name="id" class="form-control" placeholder="用户名">
					</div>
				</div>

				<br>

				<div class="form-group">
					<div class="input-group input-group-lg">
						<span class="input-group-addon"> <span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" name="pwd" class="form-control"
							placeholder="密码">
					</div>
				</div>
				<div class="form-group">
					<label class="radio-inline"> 
						<input type="radio"
						name="role" value="student" checked="checked">
						学生&nbsp
					</label> 
					<label class="radio-inline"> 
						<input type="radio"
						name="role" value="teacher">老师&nbsp
					</label> 
					<label class="radio-inline"> 
						<input type="radio"
						name="role" value="admin">管理员
					</label>
				</div>
				<font id="message" size="4" color="red" >&nbsp</font>
				<br>
				<div class="form-group">
					<button type="button" class="btn btn-primary btn-block btn-lg" id="btn_submit">登录</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
<%--获取radio的值 --%>
$().ready(function(){
	function getRadioValue(radioName) {
   		var radios = document.getElementsByName(radioName);
    	var value;
    	for(var i=0;i<radios .length;i++){
    		if(radios[i].checked){
     	 		value = radios[i].value;
   	 			break;
   	 	 	}
   		}
    	return value;
	}
	$("#btn_submit").click(function(){
		var id = document.getElementsByName("id")[0].value;
		var pwd =  document.getElementsByName("pwd")[0].value;
		var role = getRadioValue("role");
		console.log("id: " + id + "  " + id.length);
		console.log("pwd: " + pwd);
		console.log("role: " + role);
		if(id.length == 0){
			alert("请输入用户名！");
			return;
		}
		if(pwd.length == 0){
			alert("请输入密码！");
			return;
		}
		var pattern = /^[0-9]+$/;
		if(!pattern.test(id)){
			alert("用户名不合法！");
			return;
		}
		var url = "login.do";
		var args={"id":id, "pwd":pwd, "role":role};
		$.post(url, args, function(data){
			console.log("data: " + data);
			var msg = document.getElementById("message");
			if(data == "no_id"){
				msg.innerHTML = "用户名不存在";
			}
			if(data == "wrong_password"){
				msg.innerHTML = "用户名或密码错误";
			}
			if(data == "login_failed"){
				msg.innerHTML = "登录失败";
			}
			if(data == "success"){
				msg.innerHTML = "登录成功";
				//根据角色的不同跳转到不同的首页
				if(role == "student"){
					window.location.href = "stu/indexPage.do";
				}
			}
		});
	});
});
</script>
</html>