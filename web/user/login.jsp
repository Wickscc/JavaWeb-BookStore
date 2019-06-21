<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
	<body>
		<h1>登录</h1>
		<p id="msg" style="color: red; font-weight: 900">${msg }</p>
		<form action="<c:url value='/Servlet/UserServlet?m=login'/>" method="post" id="form">
			用户名：<input type="text" name="username" id="username" value=""/><span id="usernameMsg"></span><br/>
			密　码：<input type="password" id="password" name="password"/><span id="passwordMsg"></span><br/>
			<input type="button" id="subBut" value="登录"/>
		</form>
		<script type="text/javascript" src="/js/ajaxutils.js"></script>
         <script type="text/javascript">
            var usernameInput = document.getElementById("username");
            var passwordInput = document.getElementById("password");
			var subButObj = document.getElementById("subBut");
			var formObj = document.getElementById("form");
			var usernameMsgObj = document.getElementById("usernameMsg");
			var passwordMsgObj = document.getElementById("passwordMsg");

			function usernameIsNull(){
				if (usernameInput.value.trim() === "" ){
					usernameMsgObj.innerHTML = "用户名不能为空!"
				}else{
					usernameMsgObj.innerHTML = ""
				}
			}

			function passwordIsNull(){
				if (passwordInput.value.trim() === "" ){
					passwordMsgObj.innerHTML = "密码不能为空!"
				}else{
					passwordMsgObj.innerHTML = ""
				}
			}

			// passwordInput.onchange = passwordIsNull();
			// usernameInput.onchange = usernameIsNull();

			subButObj.onclick = function (ev) {
				if (usernameInput.value.trim() === "" || passwordInput.value.trim() === ""){
					usernameIsNull()
					passwordIsNull()
					alert("请输入一些内容然后在提交!");
				}else{
					formObj.submit()
				}
			}
        </script>
	</body>
</html>
