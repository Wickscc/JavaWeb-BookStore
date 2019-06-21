<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>订单详细</title>
    
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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#pay {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -412px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#pay:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -448px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="gray" bordercolor="gray">
		<td colspan="6">
			订单编号：${requestScope.order.oid}　成交时间：${requestScope.order.ordertime}　金额：<font color="red"><b>${requestScope.order.price}元</b></font>
		</td>
	</tr>

<%--	<tr bordercolor="gray" align="center">--%>
<%--		<td width="15%">--%>
<%--			<div><img src="<c:url value='/book_img/9317290-1_l.jpg'/>" height="75"/></div>--%>
<%--		</td>--%>
<%--		<td>书名：Java详解</td>--%>
<%--		<td>单价：39.9元</td>--%>
<%--		<td>作者：张孝祥</td>--%>
<%--		<td>数量：2</td>--%>
<%--		<td>小计：79.8元</td>--%>
<%--	</tr>--%>
	<c:forEach var="i" items="${requestScope.order.orderitemList}">
		<tr bordercolor="gray" align="center">
			<td width="15%">
				<div><img src="<c:url value='/${i.book.image}'/>" height="75"/></div>
			</td>
			<td>书名：${i.book.bname}</td>
			<td>单价：${i.book.price}元</td>
			<td>作者：${i.book.author}</td>
			<td>数量：${i.count}</td>
			<td>小计：${i.book.price * i.count}元</td>
		</tr>
	</c:forEach>

</table>
<br/>
<form method="post" action="${pageContext.request.contextPath}/Servlet/OrderServlet?m=pay&oid=${requestScope.order.oid}" id="form" target="_parent">

	收货地址：<input type="text" id="address" name="address" value="广东省深圳市XX街道XX路XX号XXXX" size="50"><br>
	收货　人：<input type="text" id="expName" name="expName" value="Wicks" size="50"><br>
	收货电话：<input type="text" id="expPthone" name="expPthone" value="13412345678" size="50">
	<br>

	选择银行：<br/>
	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
	<img src="${pageContext.request.contextPath}/bank_img/icbc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
	<img src="${pageContext.request.contextPath}/bank_img/bc.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
	<img src="${pageContext.request.contextPath}/bank_img/abc.bmp" align="middle"/>
	<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
	<img src="${pageContext.request.contextPath}/bank_img/ccb.bmp" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
	<img src="${pageContext.request.contextPath}/bank_img/bcc.bmp" align="middle"/><br/>
</form>
<a id="pay" href="javascript:document.getElementById('form').submit();"></a>
  <script type="text/javascript">
	  var
  </script>
  </body>
</html>

