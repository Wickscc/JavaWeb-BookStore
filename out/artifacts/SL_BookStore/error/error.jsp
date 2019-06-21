<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wicks
  Date: 2019/4/10
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>error</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link href="${pageContext.request.contextPath}/error/css/moom.css"/>
</head>

<%
    String errorinfo = (String) request.getAttribute("error");

    if (errorinfo == null){
        errorinfo = "未知错误";
    }
%>

<body>
    <%--错误处理页面,在此页面获取在session中设置的错误信息并且打出来--%>
    <%--如果没有错误信息,但是跳转过来的则报"未知错误"--%>
    <img src="${pageContext.request.contextPath}/error/img/pic.jpg" alt="" style="margin-left: auto;margin-right: auto;display: block"/>

    <h1 style="text-align: center">你跑的太快了,服务器还没有跟上你的速度</h1>
    <h2 style="text-align: center">一个人的浏览啊，当然要靠输入正确的地址，但是也要考虑到作者的行程</h2>
    <hr/>
    <h2 style="text-align: center">因为:<%=errorinfo%></h2>
</body>
</html>
