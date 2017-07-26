<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div class="topheader">
            <div class="left">
                <h1 class="logo"><a id="menu-open-close" style="cursor:pointer;" >Ddhj.</a><span>manageSystem</span></h1>
                <span class="slogan">点点环境后台管理系统</span>
                <br clear="all" />

            </div><!--left-->

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
                                <%-- TODO 非开发权限人员此处需要隐藏！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！--%>
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

        <div class="header">
            <ul class="headermenu">
                <li class="current">
                    <!--示例：href="****\***.do"-->
                    <a href="">点点环境</a>
                </li>
            </ul>
        </div>	




























