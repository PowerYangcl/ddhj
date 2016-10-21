<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>楼盘报告列表</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
<script type="text/javascript">
	/**
	 * Ajax 页面分页示例
	 *
	 * var data_ = null; 这里暂设置为null，这两处为空的地方可以根据实际情况处理。注意loadTable()也有。
	 */
	$(function() {
		var type_ = 'post';
		var url_ = '${basePath}report/data.htm';
		var data_ = null;
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
		var data_ = null;
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init();
		draw(obj);
	}

	// 画表格
	function draw(obj) {
		$('#ajax-tbody-1 tr').remove();
		var html_ = '';
		var list = obj.data.list;
		if (list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				html_ += '<tr id="tr-' + list[i].id + '" class="gradeX">'
						+ '<td align="center"><span class="center"> <input type="checkbox"/> </span></td>'
						+ '<td width="100px">'
						+ list[i].id
						+ '</td>'
						+ '<td>'
						+ list[i].userName
						+ '</td>'
						+ '<td>'
						+ list[i].mobile
						+ '</td>'
						+ '<td class="center">'
						+ list[i].idNumber
						+ '</td>'
						+ '<td class="center">'
						+ list[i].email
						+ '</td>'
						+ '<td width="150px" align="center">'
						+ '<a onclick="deleteOne(\''
						+ list[i].id
						+ '\')" title="删除"  style="cursor: pointer;">删除</a> | '
						+ '<a href="${basePath}example/editInfoPage.do?id='
						+ list[i].id
						+ '" title="修改"  style="cursor: pointer;">修改</a> '
						+ '</td></tr>'
			}
		} else {
			html_ = '<tr><td colspan="11" style="text-align: center;">'
					+ obj.msg + '</td></tr>';
		}

		$('#ajax-tbody-1').append(html_);
	}

	function deleteOne(id_) {
		if (confirm('您确定要删除这条记录吗？')) {
			var type_ = 'post';
			var url_ = '${basePath}example/deleteOne.do';
			var data_ = {
				id : id_
			};
			var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
			if (obj.status == 'success') {
				alert(obj.msg);
				$("#tr-" + id_).remove();
			} else {
				alert(obj.msg);
			}
		}
	}
</script>

</head>
<body>

</body>
</html>