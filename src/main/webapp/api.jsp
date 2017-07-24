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
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
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
		<div>
			<table border="1" cellpadding="3" cellspacing="0"
				style="width: 60%; margin: auto">
				<tr>
					<td style="width: 100px;">api_key:</td>
					<td style="padding: 10px"><input type="text" name="apiKey"
						id="apiKey" value=""></td>
				</tr>
				<tr>
					<td style="width: 100px;">api_target:</td>
					<td style="padding: 10px"><input type="text" name="apiTarget"
						id="apiTarget" value=""></td>
				</tr>
				<tr>
					<td style="width: 100px;">api_input</td>
					<td style="padding: 10px"><textarea
							style="width: 500px; height: 100px;" id="apiInput"
							name="apiInput" rows="3" cols=""></textarea></td>
				</tr>
				<tr>
					<td style="width: 100px;">user_token:</td>
					<td style="padding: 10px"><input type="text" name="userToken"
						id="userToken" value=""></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
						type="button" onclick="api();" value=" 测试 " style="width: 50px;">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>