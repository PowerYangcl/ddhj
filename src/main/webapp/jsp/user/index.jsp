<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>用户列表</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
 <script src="${js}/blockUI/jquery.blockUI.js" type="text/javascript"></script>
<script type="text/javascript">
	/**
	 * Ajax 页面分页示例
	 *
	 * var data_ = null; 这里暂设置为null，这两处为空的地方可以根据实际情况处理。注意loadTable()也有。
	 */
	$(function() {
		var type_ = 'post';
		var url_ = '${basePath}user/data.htm';
		var data_ = {"pageIndex":0,"pageSize":10};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(loadTable);
	});

	// 回调函数
	function loadTable(url_) {
		if (url_ == undefined) { // 首次加载表单
			draw(aForm.jsonObj);
			return;
		}
		// 这种情况是响应上一页或下一页的触发事件
		var type_ = 'post';
		var data_ = {
			userCode: $("#user-code").val(),
			phone : $("#phone").val(),
			nickName : $("#nick-name").val()
		}
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init();
		draw(obj);
	}

	// 画表格
	function draw(obj) {
		$('#data tr').remove();
		var html = '';
		var list = obj.page.list;
		if (list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				var obj = list[i];
				html +='<tr class="gradeX">';
				html +='<td align="center"><span class="center"> <input type="checkbox"/> </span></td>';
				html +='<td width="100px">'+obj.userCode+'</td>';
				html +='<td>'+obj.phone+'</td>';
				html +='<td>'+obj.nickName+'</td>';
				html +='<td>'+obj.eMail+'</td>';
				html +='<td>'+obj.createTime+'</td>';
				html +='<td>'+obj.updateTime+'</td>';
				html +='<td>';
				html +='<a onclick="openDialogPage(\'' + obj.uuid + '\')" style="cursor: pointer;" >编辑</a>  <a onclick="deleteOne(\'' + obj.id + '\')" style="cursor: pointer;" >删除</a>';
				html +='</td>';
				html +='</tr>';
			}
		} else {
			html = '<tr><td colspan="11" style="text-align: center;">'
					+ obj.resultMessage + '</td></tr>';
		}

		$('#data').append(html);
	}

	function searchUser(){
		aForm.formPaging(0);
	}

	function deleteOne(id_) {
		if (confirm('您确定要删除这条记录吗？')) {
			var type_ = 'post';
			var url_ = '${basePath}user/deleteOne.htm';
			var data_ = {
				id : id_
			};
			var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
			if (obj.code == '1') {
				alert(obj.msg);
				var pageIndex = $(".paginate_active").html();
				aForm.formPaging(pageIndex);
			} else {
				alert(obj.msg);
			}
		}
	}

	/**
	 * @描述: 关闭BlockUI弹框
	 * @作者: Yangcl
	 * @时间: 2016-08-19 : 15-20-56
	 */
	function closeDialog(){
		$.unblockUI();
	}

	/**
	 * @描述: 打开dialog insert BlockUI弹框
	 * @作者: Yangcl
	 * @时间: 2016-08-19 : 15-20-56
	 */
	function openDialogPage(uuid){
		var type_ = 'post';
		var url_ = '${basePath}user/getUserInfo.htm';
		var data_ = {uuid:uuid};
    	var obj = JSON.parse(ajaxs.sendAjax(type_ , url_ , data_));

		$("#e-uuid").val(obj.data.uuid);
    	$("#e-user-code").val(obj.data.userCode);
    	$("#e-phone").val(obj.data.phone);
    	$("#e-nick-name").val(obj.data.nickName);
    	$("#e-email").val(obj.data.eMail);
    	$("#e-password").val("");
    	
    	
		$.blockUI({
			showOverlay:true ,
			css:  {
				cursor:'auto',
				left:($(window).width() - $("#dialog-page-div").width())/2 + 'px',
				width:$("#dialog-page-div").width()+'px',
				top:($(window).height()-$("#dialog-page-div").height())/2 + 'px',
				position:'fixed', //居中
				border: '3px solid #FB9337'  // 边界
			},
			message: $('#dialog-page-div'),
			fadeIn: 500,//淡入时间
			fadeOut: 1000  //淡出时间
		});
	}

	function updateUserInfo(){
		var type_ = 'post';
		var url_ = '${basePath}user/updateUserInfo.htm';
		var data_ = $("#edit-form").serializeArray();
		var obj = JSON.parse(ajaxs.sendAjax(type_ , url_ , data_));
		var pageIndex = $(".paginate_active").html();
		aForm.formPaging(pageIndex);
		closeDialog();
	}


</script>

</head>
<body class="withvernav">

	<div class="bodywrapper">
		<%@ include file="/inc/top.jsp"%>
		<%@ include file="/inc/left.jsp"%>
		<div class="centercontent tables">
			<div id="contentwrapper" class="contentwrapper">
				<div id="table-form" class="dataTables_wrapper" >
					<!-- 查询条件 -->
					<div class="contenttitle2">
						<p style="margin: 0px">
							<label>用户编码：</label>
							<span class="field">
								<input id="user-code" type="text" name="userCode"  class="form-search"/>
							</span>

							<label>手机号：</label>
							<span class="field">
								<input id="phone" type="text" name="phone"  class="form-search"/>
							</span>

							<label>昵称：</label>
							<span class="field">
								<input id="nick-name" type="text" name="nickName"  class="form-search"/>
							</span>

							<a onclick="searchUser()" style="cursor: pointer;">【搜索】</a>
						</p>
					</div>
				</div>
				<!-- 设置页面显示数据数量 -->
				<div class="dataTables_length">
					<label>
						当前显示
						<%-- TODO 注意：select-page-size 这个ID是写定的，如果没有这个显示条数，则默认显示10条 - Yangcl --%>
						<select id="select-page-size" size="1" name="dyntable2_length" onchange="aForm.formPaging('1')">
							<option value="10">10</option>
							<option value="25" >25</option>
							<option value="50">50</option>
							<option value="100">100</option>
						</select>
						条记录
					</label>
				</div>
				<table cellpadding="0" cellspacing="0" border="0" class="stdtable">
					<thead>
					    <tr>
					        <th class="head0 nosort">
					            <input type="checkbox" id="checkedAll"/>
					        </th>
					        <th class="head0 nosort">用户编码</th>
					        <th class="head1 nosort">手机号</th>
					        <th class="head0 nosort">昵称</th>
					        <th class="head1 nosort">电子邮箱</th>
					        <th class="head0 nosort">创建时间</th>
					        <th class="head1 nosort">修改时间</th>
					        <th class="head1 " width="100px" style="cursor: pointer;" >操作</th>
					    </tr>
					</thead>
					<tbody id="data">
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>




<%-- 编辑 --%>
<div id="dialog-page-div" class="dialog-page-div" style="display: none;width: 600px;height: 600px">
    <p class="dialog-title">
        <a href="#" onclick="closeDialog()" class="dialog-close"></a>
        修改用户信息
    </p>

    <div id="dialog-content-wrapper" class="contentwrapper">
        <div id="dialog-table-form" class="dataTables_wrapper" >
            <%-- 以下内容根据你自己的业务需要进行修改--%>
			<form id="edit-form">
				<table id="dialog-table" cellpadding="0" cellspacing="0" border="0">
					<tbody id="dialog-ajax-tbody">
					<%-- 等待填充 --%>
					<input type="hidden" id="e-uuid" name="uuid" value=""/>
					<tr >
						<td width="100px">用户编号</td>
						<td><input id="e-user-code"  type="text" name="userCode" value=""/></td>
					</tr>
					<tr >
						<td width="100px">手机号</td>
						<td><input id="e-phone" type="text" name="phone" value=""/></td>
					</tr>
					<tr >
						<td width="100px">用户昵称</td>
						<td><input id="e-nick-name" type="text" name="nickName" value=""/></td>
					</tr>
					<tr >
						<td width="100px">用户密码</td>
						<td><input id="e-password" type="text" name="password" value=""/></td>
					</tr>
					<tr >
						<td width="100px">邮箱</td>
						<td><input id="e-email" type="text" name="eMail" value=""/></td>
					</tr>
					</tbody>
				</table>
			</form>
            <a onclick="updateUserInfo()">【提交】</a>

        </div>
    </div>

</div>












