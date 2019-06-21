<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxutils.js"></script>
  	<script type="text/javascript">
		var hasUsername = false;
		var hasEmail = false;
		var hasInputPassword = false;
	</script>
  </head>
  
  <body>
  	<h1>注册</h1>
	<p style="color: red; font-weight: 900">${msg }</p>
	<form action="${pageContext.request.contextPath}/Servlet/UserServlet?m=regist" method="post" id="regForm">
		<input type="hidden" name="method" value="regist"/>
		用户名：<input type="text" name="username" value="" id="username"/> <span id="usernameMsg"></span><br/>
		密　码：<input type="password" name="password" id="password"/><span id="passwordMsg"></span><br/>
		邮　箱：<input type="text" name="email" value="" id="email"/><span id="emailMsg"></span><br/>
		<input type="button" value="注册" id="subButton"/>
	</form>
	  <script type="text/javascript">
		  var usernameInput = document.getElementById("username");
		  var emailInput = document.getElementById("email");
		  var passwordInput = document.getElementById("password");
		  var subButton = document.getElementById("subButton");
		  var formObj = document.getElementById("regForm");
          var passwordMsgObj = document.getElementById("passwordMsg");

          usernameInput.onchange = function(){
			var usernameValue = usernameInput.value;
			  ajax(
					{
						url : "${pageContext.request.contextPath}/Servlet/UserServlet?m=isHasUsername",
						method : "POST",
						params : "username="+usernameValue,
						type : "json",
						callback : function (data) {
							var UsernameMsgObj = document.getElementById("usernameMsg");
							UsernameMsgObj.innerHTML = data.msg;
							if (data.ok === true){
								hasUsername = true;
							}
						}
					}
			  );
		  };

		  emailInput.onchange = function(){
			  var emailValue = emailInput.value;
			  ajax(
					  {
						  url : "${pageContext.request.contextPath}/Servlet/UserServlet?m=isHasEmail",
						  method : "POST",
						  params : "email="+emailValue,
						  type : "json",
						  callback : function (data) {
							  var UsernameMsgObj = document.getElementById("emailMsg");
							  UsernameMsgObj.innerHTML = data.msg;
							  if (data.ok === true){
								  hasEmail = true;
							  }
						  }
					  }
			  );
		  };

          function hasPassword(ev) {
              if (passwordInput.value.trim().length === 0) {
                  passwordMsgObj.innerHTML = "密码不能为空!"
                  hasInputPassword = false;
              }else{
                  passwordMsgObj.innerHTML = ""
                  hasInputPassword = true
              }
          }

          passwordInput.onchange = function (ev) {
              if(passwordInput.value.trim().length < 8) {
                  passwordMsgObj.innerHTML = "密码不能小于8位!";
                  hasInputPassword = false;
              }else {
                  passwordMsgObj.innerHTML = "";
                  hasInputPassword = true;
              }
          };

		  subButton.onclick = function (ev) {
                hasPassword();
                if (hasInputPassword === true && hasEmail === true&& hasUsername === true){
                    formObj.submit();
                }else{
                    alert("请检查提示信息后在提交!")
                }
		  }
	  </script>
  </body>
</html>
