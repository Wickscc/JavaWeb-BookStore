<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'msg.jsp' starting page</title>

        <meta http-equiv="content-type" content="text/html;charset=utf-8">
        <meta http-equiv="pragma" content="no-cache">
	    <meta http-equiv="cache-control" content="no-cache">
	    <meta http-equiv="expires" content="0">
	    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	    <meta http-equiv="description" content="This is my page">
      <!--
      <link rel="stylesheet" type="text/css" href="styles.css">
      -->

  </head>
  
  <body>
    <h1>${msg}</h1>
    <ul>
      <li><a href="<c:url value='/jsps/cart/list.jsp'/>">继续购物</a></li>
      <li><a href="<c:url value='/jsps/cart/list.jsp'/>">到购物车</a></li>
    </ul>
  </body>
</html>
