<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String js = path + "/js";
	String css = path + "/css";
	String img = path + "/images";
	String resources = path + "/resources";
	pageContext.setAttribute("js", js);
	pageContext.setAttribute("css", css);
	pageContext.setAttribute("img", img);
	pageContext.setAttribute("basePath", basePath);
	pageContext.setAttribute("resources", resources);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>点点环境后台管理系统</title>
<link rel="stylesheet" href="${css}/style.default.css" type="text/css" />
<script type="text/javascript" src="${js}/jquery-1.10.2.js"></script> 
<script type="text/javascript" src="${js}/plugins/jquery-ui.js"></script>
<script type="text/javascript" src="${js}/plugins/colorpicker.js" ></script>
<script type="text/javascript" src="${js}/plugins/jquery.alerts.js" ></script>
<!-- ajax相关方法 jsonp等 -->
<script type="text/javascript" src="${js}/utils/ajaxs.js"></script> 
<script type="text/javascript" src="${js}/system/general.js"></script>
<!-- 退出相关方法 -->
<script type="text/javascript" src="${js}/system/login.js"></script>
