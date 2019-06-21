<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>left</title>
    <base target="body"/>
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
		*{
			font-size:10pt;
			text-align: center;
		}
		div {
			background: #87CEFA; 
			margin: 3px; 
			padding: 3px;
		}
		a {
			text-decoration: none;
		}
	</style>
  </head>
  
	<body>
		<div>
			<a href="<c:url value='/Servlet/BookServlet?m=findCategoryList&CategoryName=all'/>">全部分类</a>
		</div>


		<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxutils.js"></script>
		<script type="text/javascript">
			var bodyObj = document.getElementsByTagName("body")[0];
			window.onload = function (ev) {
				ajax({
					url : "${pageContext.request.contextPath}/Servlet/CategoryServlet?m=allCategory",
					type : "JSON",
					method : "GET",
					callback : function (data) {
						var List = data.CategoryList;
						for (var i = 0; i < List.length; i++) {
							var name = List[i].cname;
							var divObj = document.createElement("div");
							var aObj = document.createElement("a");
							aObj.setAttribute("href", "${pageContext.request.contextPath}/Servlet/BookServlet?m=findCategoryList&CategoryName=" + name);
							aObj.innerHTML = name + "分类";

							divObj.appendChild(aObj);
							bodyObj.appendChild(divObj);
						}
					}
				});
			}
		</script>

 	</body>
</html>
