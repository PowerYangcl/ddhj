<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<title>文件上传下载测试</title>
<script type="text/javascript">
function createPDF(){
	var codeVal = "LP161003100031";
	$.ajax({
		url : "report/createpdf.htm",
		type : "POST",
		data : {
			code:codeVal
		},
		success : function(data) {
			data = JSON.parse(data);
			if (data.resultCode == 0) {
				alert("创建pdf成功,文件地址:"+data.path);
				$("#downFile").attr("href",'<%=basePath %>'+data.path);
			} else {
				alert("创建pdf失败");
			}
		},
		error : function(data) {
			alert("创建pdf失败");
		}
	});
}
</script>
</head>
<body>
	<h1>报告上传下载</h1>
	<a href="javascript:void(0)" onclick="createPDF();">创建pdf报告</a>
	<a id="downFile" href="javascript:void(0)">下载</a>
</body>
</html>