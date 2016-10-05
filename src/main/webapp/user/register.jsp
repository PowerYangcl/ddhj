<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
2<html>
<head>
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="assets/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/commons/css/basic.css" />
<link rel="stylesheet" href="assets/commons/fonts/font-awesome/4.5.0/css/font-awesome.css" />
<link rel="stylesheet" href="assets/commons/css/custom.css" />
<link rel="stylesheet" href="assets/commons/css/mricode.pagination.css" />
<script type="text/javascript" src="assets/jquery/1.12.2/jquery-1.12.2.min.js"></script>
<script type="text/javascript" src="assets/commons/js/mricode.pagination.js"></script>
<script type="text/javascript" src="assets/bootstrap/3.3.5/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<script type="text/javascript">
function register(){
	alert(JSON.stringify($("#register").serialize()));
	$.ajax({
		url : "user/register.htm",
		type : "POST",
		data : $("#register").serialize(),
		success : function(data) {
			alert(data);
			data = JSON.parse(data);
			alert(data.resultMessage);
		},
		error : function(data) {
			alert("注册失败");
		}
	});
}
</script>

<body>
	<form action="" id="register">
		<input type="text" id="phone" name="phone" value=""><br>
		<input type="password" id="password" name="password" value=""><br>
	</form>
	<a href="javascript:void(0)" onclick="register()">注册</a>
</body>
</html>