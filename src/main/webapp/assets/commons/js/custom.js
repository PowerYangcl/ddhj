//初始化菜单
$(function() {
	//填充左侧菜单栏数组
	Nav.setMenu();
	//初始化左侧菜单栏
	Nav.init();
	$('#main-menu').metisMenu();
	//添加样式
	$(window).bind("load resize", function() {
		if ($(this).width() < 768) {
			$('div.sidebar-collapse').addClass('collapse')
		} else {
			$('div.sidebar-collapse').removeClass('collapse')
		}
		//获取当前url
		var url = window.location;
		//根据url判定添加选中样式
		var element = $('ul.nav a').filter(function() {
			var code = $(this).attr("code");
			var url_parent = "";
			for(var key in MenuMap){
				if(key == url || url.href.indexOf(key)>=0){
					url_parent = MenuMap[key];
					break;
				}
			}
			return code == url_parent;
		}).addClass('active-menu').parent().parent().addClass('in').parent().addClass("in");
		if (element.is('li')) {
			element.addClass('active-menu');
		}
	});
});