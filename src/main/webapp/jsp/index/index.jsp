<%@ include file="/inc/resource.inc" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/inc/head.jsp" %>
		<script type="text/javascript">
			function iframeChange(){
				var lw = $("#left-menu div:visible")[0]; // 取左侧菜单的宽度
				var toph = $(".header")[0].offsetHeight + $(".topheader")[0].offsetHeight;  // 取顶部高 
				var ifm= document.getElementById("sub-page"); 
			    ifm.height = document.documentElement.clientHeight - toph;
			    ifm.width = document.documentElement.clientWidth - lw.clientWidth;
			}
			window.onresize=function(){  
				iframeChange();  
			} 
		</script>
	</head>

	<body class="withvernav" style="overflow-x:hidden">
	
	    <div class="bodywrapper">
			<div class="topheader">
				<div class="left">
				<h1 class="logo"><a id="menu-open-close" style="cursor:pointer;" >点点环境后台管理系统</a></h1>
				<span class="slogan"  style="display:none">点点环境后台管理系统</span>
				    <br clear="all" />
				</div>
				<div class="right">
				    <!--用户摘要信息-->
					<div class="userinfo">
					    <img src="${img}/thumbs/avatar.png" alt="" />
						<span id="simple-user-name">
					</div>
					<!--用户详细信息弹窗-->
					<div class="userinfodrop">
					    <div class="avatar">
					        <a href="">
					            <img src="${img}/thumbs/avatarbig.png" alt="用户头像" />
							</a>
							<div class="changetheme">
						    	更换主题: <br />
						    	<a class="default"></a>
						    	<a class="blueline"></a>
						    	<a class="greenline"></a>
						    	<a class="contrast"></a>
						    </div>
						</div>
						<!--登录用户信息-->
						<div class="userdata">
						    <h4 id="user-name"></h4>
						    </br>
							<span id="user-email" class="email"></span>
							<ul>
							    <li>
							        <a href="javascript:void(0)">修改资料</a>
							    </li>
							    <li>
							        <a href="javascript:void(0)" onclick="pageInit.menuOnclick(this)"  target_="${basePath}manager/page_manager_index.do">返回主页</a>
								</li>
								<li>
								    <a href="javascript:void(0)">帮助</a> 
								</li>
								<li>
								    <a href="javascript:void(0)" onclick="login.logout('${basePath}')">退出</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="header">
			    <ul id="nav-list" class="headermenu">
			    	
			    </ul>
			</div>
			<iframe style="margin-left:231px;overflow-x:hidden" id="sub-page"  frameborder="0" onload="iframeChange()" src="${basePath}manager/page_manager_index.do" ></iframe>
	    </div>
	
	</body>
</html>