<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>购物车列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>购物车</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a href="<c:url value="/Servlet/CartServlet?m=removeAllCart"/>">清空购物车</a>
		</td>
	</tr>
	<tr>
		<th>图片</th>
		<th>书名</th>
		<th>作者</th>
		<th>单价</th>
		<th>数量</th>
		<th>小计</th>
		<th>操作</th>
	</tr>
	<c:forEach var="i" items="${sessionScope.cart.map}">
		<tr name="orderitem">
			<th><div><img src="<c:url value='../../${i.value.book.image}'/>"/></div></th>
			<td>${i.value.book.bname}</td>
			<td>${i.value.book.author}</td>
			<td>${i.value.book.price}元</td>
			<td>${i.value.count}</td>
			<td name="rmbNub">${i.value.count * i.value.book.price}元</td>
			<td><a href="<c:url value="/Servlet/CartServlet?m=removeCart&bid=${i.value.book.bid}"/> ">删除</a></td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			合计: <span id="allRmbNub"></span>元
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a id="buy" href=""></a>
		</td>
	</tr>
</table>
  <script type="text/javascript">
	  var allRmbNub = document.getElementById("allRmbNub");
	  var rmbNubList = document.getElementsByName("rmbNub");
	  var subLinkObj = document.getElementById("buy");

	  var tempNub = 0;
	  for(var i = 0; i < rmbNubList.length; i ++){
	  	tempNub = tempNub + Number(rmbNubList[i].innerHTML.replace("元",""));
	  }

	  subLinkObj.onclick = function (ev) {
	  	var orderitemList = document.getElementsByName("orderitem");
	  	if (orderitemList.length === 0){
	  		alert("购物车为空,请购买一些商品后提交!")
			return false;
		}
	  };

	  allRmbNub.innerHTML = tempNub.toFixed(2);
	  subLinkObj.setAttribute("href","${pageContext.request.contextPath}/Servlet/OrderServlet?m=addOrder&price=" + tempNub.toFixed(2));
  </script>
  </body>
</html>
