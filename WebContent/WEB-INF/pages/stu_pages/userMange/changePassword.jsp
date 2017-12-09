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
			<div class="col-md-7" id="leftDiv">
				<div class="panel panel-default">
					<div class="panel-heading">修改密码</div>
					<div class="panel-body form-group" style="margin-bottom: 0px;">
						<form class="form-horizontal myform">
							<div class="form-group">
								<label for="id" class="col-md-2 control-label">帐号:</label>
								<div class="col-md-4">
									<input type="text" class="form-control" id="id" value="${id}"
										disabled>
								</div>
							</div>
							<div class="form-group">
								<label for="oldPwd" class="col-md-2 control-label">旧密码:</label>
								<div class="col-md-4">
									<input type="password" class="form-control" id="oldPwd">
								</div>
							</div>
							<div class="form-group">
								<label for="newPwd" class="col-md-2 control-label">新密码:</label>
								<div class="col-md-4">
									<input type="password" class="form-control" id="newPwd">
								</div>
							</div>
							<div class="form-group">
								<label for="repeatPwd" class="col-md-2 control-label">确认密码:</label>
								<div class="col-md-4">
									<input type="password" class="form-control" id="repeatPwd">
								</div>
							</div>
						</form>
							&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
							<button class="btn btn-default" id="submit">修改</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="btn btn-default" id="reset">重置</button>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	function reset() {
		document.getElementById("oldPwd").value = "";
		document.getElementById("newPwd").value = "";
		document.getElementById("repeatPwd").value = "";
	}

	$("#reset").click(function() {
		reset();
		return false;
	});

	$("#submit").click(function() {
		var oldPwd = $("#oldPwd").val();
		var newPwd = $("#newPwd").val();
		var repeatPwd = $("#repeatPwd").val();

		if (oldPwd == null || oldPwd == "") {
			alert("请输入旧密码");
			$("#oldPwd").focus();
			return false;
		}
		if (newPwd == null || newPwd == "") {
			alert("新密码不能为空");
			reset();
			$("#newPwd").focus();
			return false;
		}
		//如果新旧密码一样
		if(oldPwd == newPwd){
			alert("新旧密码不能一样！");
			reset();
			$("#newPwd").focus();
			return false;
		}
		//检验密码长度
		if(!checkStringLen(document.getElementById("oldPwd"), "旧密码", 50, 0)){
			return false;
		}
		if(!checkStringLen(document.getElementById("newPwd"), "新密码", 50, 5)){
			return false;
		}
		if(!checkStringLen(document.getElementById("repeatPwd"), "重复密码", 50, 5)){
			return false;
		}
		console.log("到了这里");
		//检验新旧密码是否一致
		if(newPwd == repeatPwd){
			//ajax修改密码
			var args = {"oldPwd":oldPwd, "newPwd":newPwd};
			var url = "<%=request.getContextPath()%>/stu/userMange/changePassword.do";
			$.post(url, args, function(data){
				if(data == "success"){
					alert("修改成功！");
					window.location.href = "<%=request.getContextPath()%>/logout.do";
				}
				else if(data == "failed"){
					alert("修改失败！");
				}
				else{
					alert("系统错误！");
				}
			});
			
		}else{
			alert("新旧密码不一致！");
			reset();
			$("#oldPwd").focus();
			return false;
		}
		return false;
	});

	//检验密码长度
	function checkStringLen(obj, msg, maxLen, minLen) {
		var str = obj.value;
		var len2 = 0;
		for (var i = 0; i < str.length; i++) {
			if (str.charCodeAt(i) > 127 || str.charCodeAt(i) == 94) {
				len2 += 2;
			} else {
				len2++;
			}
		}
		if (len2 > maxLen) {
			alert(msg + "长度过长，请重新输入");
			obj.focus();
			return false;
		}if(len2 < minLen){
			alert(msg + "长度过短，请重新输入");
			obj.focus();
			return false;
		}
		else {
			return true;
		}
	}
</script>
</html>