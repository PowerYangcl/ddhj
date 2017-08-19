<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单列表</title>
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
		var url_ = '${basePath}order/data.htm';
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
			code: $("#code").val(),
			phone: $("#phone").val(),
			reportTitle: $("#report-title").val(),
			level: $("#level").val()
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
				html +='<td width="100px">'+obj.code+'</td>';
				if(obj.status == '0'){
					html +='<td width="100px">未支付</td>';
				}else if(obj.status == '1'){
					html +='<td width="100px"> 已支付未下载</td>';
				}else if(obj.status == '2'){
					html +='<td width="100px">已支付已下载</td>';
				}else{
					html +='<td width="100px"> 已取消</td>';
				}
				html +='<td>'+obj.payName+'</td>';
				html +='<td>'+obj.payPrice+'</td>';
				html +='<td>'+obj.phone+'</td>';
				html +='<td>'+obj.reportTile+'</td>';
				html +='<td>'+obj.levelName+'</td>';
				html +='<td>'+obj.createTime+'</td>';
				html +='<td>';
				html +='<a onclick="refreshReport(\'' + obj.code + '\')" style="cursor: pointer;margin-right:10px" >刷新报告</a>';
				html +='<a onclick="deleteOne(\'' + obj.id + '\')" style="cursor: pointer;" >删除</a>';
				html +='</td>';
				html +='</tr>';
			}
		} else {
			html = '<tr><td colspan="11" style="text-align: center;">' +  obj.resultMessage + '</td></tr>';
		}

		$('#data').append(html);
	}

	function deleteOne(id_) {
		if (confirm('您确定要删除这条记录吗？')) {
			var type_ = 'post';
			var url_ = '${basePath}order/deleteOne.htm';
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
	
	function refreshReport(code_){
		if (confirm('您确定要刷新这条记录吗？')) {
			var type_ = 'post';
			var url_ = '${basePath}order/refresh_report.htm';
			var data_ = {
				code : code_
			};
			var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
			if (obj.resultCode == '1') {
				alert(obj.resultMessage);
				var pageIndex = $(".paginate_active").html();
				aForm.formPaging(pageIndex);
			} else {
				alert(obj.resultMessage);
			}
		}
	}

	function searchReport(){
		aForm.formPaging(0);
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
							<label>订单编码：</label>
							<span class="field">
								<input id="code" type="text" name="code"  class="form-search"/>
							</span>

							<label>手机号：</label>
							<span class="field">
								<input id="phone" type="text" name="phone"  class="form-search"/>
							</span>

							<label>报告名称：</label>
							<span class="field">
								<input id="report-title" type="text" name="reportTitle"  class="form-search"/>
							</span>

							<label>报告等级：</label>
							<span class="field">
								<select id="level" name="level" class="form-search">
									<option value="">---请选择---</option>
									<option value="RL161006100001">普通</option>
									<option value="RL161006100002">高级</option>
									<option value="RL161006100003">专业</option>
								</select>
							</span>

							<a onclick="searchReport()" style="cursor: pointer;">【搜索】</a>
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
					        <th class="head0 nosort">订单编码</th>
							<th class="head1 nosort">支付状态</th>
					        <th class="head1 nosort">支付类型</th>
					        <th class="head0 nosort">支付金额</th>
					        <th class="head1 nosort">手机号</th>
					        <th class="head0 nosort">报告名称</th>
					        <th class="head1 nosort">报告等级</th>
					        <th class="head0 nosort">创建时间</th>
					        <th class="head1 " width="100px">操作</th>
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