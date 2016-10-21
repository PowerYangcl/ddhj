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
<script type="text/javascript" src="assets/commons/js/metisMenu.js"></script>
<script type="text/javascript" src="assets/commons/js/usepublic.js"></script>
<script type="text/javascript" src="assets/commons/js/commons.js"></script>
<style type="text/css">
.table td{
	text-align: center;
}
</style>
<script type="text/javascript">
	//公共每页显示条数
	var PAGE_SIZE = 10;
</script>
</head>
<body>
<div id="wrapper">
	<!-- 标题logo -->
	<nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                  <span class="sr-only">Toggle navigation</span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
              </button>
			<a class="navbar-brand" href="index.html">PMC</a>
		</div>
	</nav>
	<!-- 左侧菜单栏 -->
	<nav class="navbar-default navbar-side" role="navigation">
		<div class="sidebar-collapse">
			<ul class="nav" id="main-menu">
			</ul>
		</div>
	</nav>
</div>
<div id="page-wrapper">
	<div id="page-inner">