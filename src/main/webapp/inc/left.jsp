<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<%-- nav 类代表导航栏 无任何样式应用|每个导航栏对应的菜单显示与否由id来决定--%>
        <div id="nav-bar-1" class="vernav2 iconmenu nav">
            <ul class="nav-bar-ul">
                <li class="current">
                    <a href="#formsub222222" class="editor">一级菜单栏A</a>
                    <span class="arrow"></span>
                    <ul id="formsub222222">
                        <li class="current">
                        	<a href="">二级菜单栏A</a>   <!-- 必须添加 $ { basePath } -->
                        </li>
                        <li><a href="">二级菜单栏B</a></li>
                        <li><a href="${basePath}kjt/query.do"  target="_blank">跨境通问题查询</a></li>
                        <li><a href="${basePath}kjt/validate.do">跨境通临时问题维护</a></li>
                        <li><a href="${basePath}seller/index.do">商户管理</a></li>
                        <li><a href="${basePath}api/index.do">商户管理</a></li>
                    </ul>
                </li>

                <li>
                    <a href="" class="gallery">File Manager</a>
                </li>
            </ul>
            <a class="togglemenu"></a>

            <br /><br />
        </div>


        <%-- 切换导航栏的时候 这里的菜单来应该默认打开 从而配合【Plus】按钮进行菜单的全部打开或者全部收起--%>
        <div id="nav-bar-2" class="vernav2 iconmenu nav" style="display: none">

        </div>

        <div id="nav-bar-3" class="vernav2 iconmenu nav" style="display: none">

        </div>

        <div id="nav-bar-4" class="vernav2 iconmenu nav" style="display: none">

        </div>

















