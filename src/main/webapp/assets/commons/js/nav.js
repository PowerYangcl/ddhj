/*
 * 左侧菜单操作函数
 * @author zhy
 * @date 2016-04-06
 * @version 1.0.0
 */
var Nav = {
	Menu: new Array(),
	//添加元素到菜单数组
	setMenu: function() {
		this.Menu = [{
			id: "0000",
			title: "首页",
			href: "index/index.htm",
			ico_class: "fa-dashboard",
			items: ""
		}, {
			id: "1000",
			title: "项目管理",
			href: "javascript:void(0)",
			ico_class: "fa-archive",
			items: [{
				id: "1001",
				title: "项目管理",
				href: "project/project/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}, {
				id: "1002",
				title: "类型管理",
				href: "project/type/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}]
		}, {
			id: "2000",
			title: "开发计划",
			href: "javascript:void(0)",
			ico_class: "fa-book",
			items: [{
				id: "2001",
				title: "计划管理",
				href: "dev/plan/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}, {
				id: "2002",
				title: "任务管理",
				href: "dev/task/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}]
		}, {
			id: "3000",
			title: "测试计划",
			href: "javascript:void(0)",
			ico_class: "fa-book",
			items: [{
				id: "3001",
				title: "计划管理",
				href: "test/plan/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}, {
				id: "3002",
				title: "任务管理",
				href: "test/task/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}]
		}, {
			id: "4000",
			title: "用户管理",
			href: "javascript:void(0)",
			ico_class: "fa-user",
			items: [{
				id: "4001",
				title: "用户管理",
				href: "user/user/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}, {
				id: "4002",
				title: "部门管理",
				href: "user/department/index.htm",
				ico_class: "fa-chevron-right",
				items: ""
			}]
		}, {
			id: "5000",
			title: "系统设置",
			href: "javascript:void(0)",
			ico_class: "fa-cog",
			items: [{
				id: "5001",
				title: "实时监控",
				href: "system/index.htm",
				ico_class: "fa-dashboard",
				items: ""
			}, {
				id: "5002",
				title: "系统日志",
				href: "system/log.htm",
				ico_class: "fa-dashboard",
				items: ""
			}]
		}];
	},
	init: function() {
		var html = "";
		for (var key in this.Menu) {
			var item = this.Menu[key];
			html += "<li>";
			html += "<a code='"+item.href+"' href='" + item.href + "'><i class='fa " + item.ico_class + " '></i>" + item.title;
			if (item.items) {
				html += "<span class='fa arrow'></span></a>";
				html += "<ul class='nav nav-second-level'>";
				for (var i in item.items) {
					var itemChild = item.items[i];
					html += "<li>";
					html += "<a code='"+itemChild.href+"' href='" + itemChild.href + "'><i class='fa " + itemChild.ico_class + " '></i>" + itemChild.title + "</a>";
					html += "</li>";
				}
				html += "</ul>";
			} else {
				html += "</a>";
			}
			html += "</li>";
		}
		$("#main-menu").html(html);
	}
};
var MenuMap = {
	//首页
	"index/index.htm": "index/index.htm",
	//项目管理
	//==============start==========
	"project/project/index.htm": "project/project/index.htm",
	"project/project/addindex.htm": "project/project/index.htm",
	"project/project/editindex.htm": "project/project/index.htm",
	"project/type/index.htm": "project/type/index.htm",
	"project/type/addindex.htm": "project/type/index.htm",
	"project/type/editindex.htm": "project/type/index.htm",
	//==============end==========
	//开发管理
	//==============start==========
	"dev/plan/index.htm": "dev/plan/index.htm",
	"dev/plan/addindex.htm": "dev/plan/index.htm",
	"dev/plan/editindex.htm": "dev/plan/index.htm",
	"dev/task/index.htm": "dev/task/index.htm",
	"dev/task/addindex.htm": "dev/task/index.htm",
	"dev/task/editindex.htm": "dev/task/index.htm",
	//==============end==========
	//测试管理
	//==============start==========
	"test/plan/index.htm": "test/plan/index.htm",
	"test/plan/addindex.htm": "test/plan/index.htm",
	"test/plan/editindex.htm": "test/plan/index.htm",
	"test/task/index.htm": "test/task/index.htm",
	"test/task/addindex.htm": "test/task/index.htm",
	"test/task/editindex.htm": "test/task/index.htm",
	//==============end==========
	//用户管理
	//==============start==========
	"user/user/index.htm": "user/user/index.htm",
	"user/user/addindex.htm": "user/user/index.htm",
	"user/user/editindex.htm": "user/user/index.htm",
	"user/department/index.htm": "user/department/index.htm",
	"user/department/addindex.htm": "user/department/index.htm",
	"user/department/editindex.htm": "user/department/index.htm",
	//==============end==========
	//系统设置
	//==============start==========
	"system/index.htm": "system/index.htm",
	"system/log.htm": "system/index.htm"
	//==============end==========
};
