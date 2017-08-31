<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function(){
    $('.vernav2 > ul > li > ul > li').removeClass("current");
    $("#" + getCookie('liselect')).addClass("current");

    $('.vernav2 > ul > li > ul > li').click("onclick" , function(){
        clearCookie('liselect');
        setCookie('liselect' , this.id , '1'); 
    });

});
function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + "; " + expires + "; path=/";
}

function getCookie(c_name) {
    if (document.cookie.length > 0) {
        var c_start = document.cookie.indexOf(c_name + "=");
        if (c_start != -1) {
          c_start = c_start + c_name.length + 1;
          var c_end = document.cookie.indexOf(";", c_start);
          if (c_end == -1)
              c_end = document.cookie.length;
          return unescape(document.cookie.substring(c_start, c_end));
        }
    }
  return "";
}

function clearCookie(name) {
  setCookie(name, "", -1);
}
</script>
<div id="nav-bar-1" class="vernav2 iconmenu nav">
    <ul class="nav-bar-ul">
        <li class="current">
            <a href="#environmental" class="editor">环境相关</a>
            <span class="arrow"></span>
            <ul id=environmental>
                <li id="li-1"><a href="${basePath}user/index.htm">用户管理</a></li>
                <li id="li-2"><a href="${basePath}order/index.htm">订单管理</a></li>
                <li id="li-3"><a href="${basePath}lp/index.htm">楼盘管理</a></li>
                <li id="li-4"><a href="${basePath}sys/report/index.htm">报告管理</a></li> 
            </ul>
        </li>
        
        <li class="current">
            <a href="#store" class="inbox">微信商城</a>
            <span class="arrow"></span>
            <ul id="store">
            	<li id="li-5">
                	<a href="${basePath}store/product/index.htm">商品管理</a>
                </li> 
            	<li id="li-5">
                	<a href="${basePath}store/order/index.htm">订单管理</a>
                </li> 
            </ul>
		</li>
    </ul>
    <a class="togglemenu"></a>
    <br />
    <br />
</div>
















