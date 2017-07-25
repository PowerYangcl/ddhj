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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
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
</head>
<body class="withvernav">
	<div class="bodywrapper">
		<div class="topheader">
            <div class="left">
                <h1 class="logo"><a id="menu-open-close" style="cursor:pointer;" >Ddhj.</a><span>manageSystem</span></h1>
                <span class="slogan">点点环境后台管理系统</span>
                <br clear="all" />
            </div>
            <div class="right">
                <!--用户摘要信息-->
                <div class="userinfo">
                    <img src="${img}/thumbs/avatar.png" alt="" />
                    <span>Juan Dela Cruz</span>
                </div>
                <!--用户详细信息弹窗-->
                <div class="userinfodrop">
                    <div class="avatar">
                        <a href="">
                            <img src="${img}/thumbs/avatarbig.png" alt="用户头像" />  <!--用户头像-->
                        </a>
                        <div class="changetheme">
                            Change theme: <br />
                            <a class="default"></a>
                            <a class="blueline"></a>
                            <a class="greenline"></a>
                            <a class="contrast"></a>
                            <a class="custombg"></a>
                        </div>
                    </div><!--avatar-->
                    <!--登录用户信息-->
                    <div class="userdata">
                        <h4>Juan Dela Cruz</h4>
                        <span class="email">youremail@yourdomain.com</span>
                        <ul>
                            <li>
                                <a href="">修改资料</a>
                            </li>
                            <li>
                                <a href="${basePath}sysfunction/showview.do">功能树维护</a>
                            </li>
                            <li>
                                <a href="">帮助</a>
                            </li>
                            <li>
                                <a href="">退出</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- 头部导航 -->
        <div class="header">
            <ul class="headermenu">
            	<c:forEach items="${menus }" var="m" varStatus="status">
            			<li <c:if test="${status.first}">class="current"</c:if>>
            				<a class="J_menuItem" href="${m.url }">${m.name }</a>
            			</li>
            	</c:forEach>
            </ul>
        </div>
		<iframe id="J_iframe" width="100%" height="100%" src="" frameborder="0" data-id="sub_index" seamless></iframe>
	</div>
<script>
	$(function() {
		//菜单点击
		$(".J_menuItem").on('click', function() {
			var url = $(this).attr('href');
			$("#J_iframe").attr('src', url);
			return false;
		});
	});
</script>
</body>
</html>