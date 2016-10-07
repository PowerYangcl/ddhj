<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="assets/bootstrap/3.3.5/css/bootstrap.min.css" />
<link rel="stylesheet" href="assets/commons/css/basic.css" />
<link rel="stylesheet"
	href="assets/commons/fonts/font-awesome/4.5.0/css/font-awesome.css" />
<link rel="stylesheet" href="assets/commons/css/custom.css" />
<link rel="stylesheet" href="assets/commons/css/mricode.pagination.css" />
<script type="text/javascript"
	src="assets/jquery/1.12.2/jquery-1.12.2.min.js"></script>
<script type="text/javascript"
	src="assets/commons/js/mricode.pagination.js"></script>
<script type="text/javascript"
	src="assets/bootstrap/3.3.5/js/bootstrap.js"></script>
<title>api接口测试</title>
<script type="text/javascript">
	function api() {
		$.ajax({
			url : "api.htm",
			type : "POST",
			data : $("#apiForm").serialize(),
			success : function(data) {
				data = JSON.parse(data);
				if (data.resultCode == 0) {
					alert(JSON.stringify(data));
				} else {
					alert("调用接口失败");
				}
			},
			error : function(data) {
				alert("调用接口失败");
			}
		});
	}
</script>
</head>
<body>
	<form id="apiForm" action="">
		<div style="text-align: center;">
			<table style="text-align: center;">
				<tr>
					<td>api_key:</td>
					<td><input type="text" name="apiKey" id="apiKey" value="">
					</td>
				</tr>
				<tr>
					<td>api_target:</td>
					<td><input type="text" name="apiTarget" id="apiTarget" value="">
					</td>
				</tr>
				<tr>
					<td>api_input</td>
					<td style="width: 200px;"><textarea id="apiInput" name="apiInput" rows="3" cols=""></textarea>
					</td>
				</tr>
				<tr>
					<td>user_token:</td>
					<td><input type="text" name="userToken" id="userToken" value="">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" onclick="api();" value=" 测试 " style="width:50px;">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>