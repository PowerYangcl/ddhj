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
		var url_ = 'data.htm';
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
		var data_ = null;
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init();
		draw(obj);
	}
	
	function createReport(val){
		var url_ = '${basePath}report/create.htm';
		var type_ = 'post';
		var data_ = {"lpCode":val,"levelCode":"RL161006100001"};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		if(obj.resultCode == 0){
			alert(obj.resultMessage);
		}else if(obj.resultCode == 1){
			if(confirm("楼盘没有报告是否生成？")){
				window.open("${basePath}report/add.htm","_self")
			}
		}
	}

	// 画表格
	function draw(obj) {
		$('#data tr').remove();
		var html = '';
		var list = obj.page.list;
		if (list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				var obj = list[i];
				html +="<tr class=\"gradeX\">";
				html +="<td align=\"center\"><span class=\"center\"> <input type=\"checkbox\"/> </span></td>";
				html +="<td width=\"100px\">"+obj.code+"</td>";
				html +="<td>"+obj.title+"</td>";
				html +="<td>"+obj.propertyType+"</td>";
				html +="<td>"+obj.city+"</td>";
				html +="<td>"+obj.addressFull+"</td>";
				html +="<td>"+obj.completion+"</td>";
				html +="<td>";
				html +="<a href=\"javascript:void(0)\" onclick=\"createReport('"+obj.code+"')\">生成</a>";
				html +="</td>";
				html +="<td>";
				html +="<a href=\"editindex.htm?code="+obj.code+"\">编辑</a>  <a>删除</a>";
				html +="</td>";
				html +="</tr>";
			}
		} else {
			html = "<tr><td colspan=\"9\" style=\"text-align: center;\">"
					+ obj.msg + "</td></tr>";
		}

		$('#data').append(html);
	}

	//暂时不做，保留
	function del(id_) {
		if (confirm('您确定要删除这条记录吗？')) {
			var type_ = 'post';
			var url_ = 'delete.htm';
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
	
	function searchLp(){
		var type_ = 'post';
		var url_ = 'data.htm';
		var data_ = {"pageIndex":0,"pageSize":10,"city":$("#city").val(),"title":$("#title").val()};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(loadTable);		
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
                            <label>楼盘名称：</label>
                            <span class="field"><input id="title" type="text" name="title"  class="form-search"/></span>
						</p>
						<p style="margin: 0px">
                            <label>城市名称：</label>
                            <span class="field"><input id="city" type="text" name="city"  class="form-search"/></span>
						</p>
                        <p style="margin:5px 0px 0px 0px;">
                            <a href="javascript:void(0)" onclick="searchLp()" class="btn btn_orange btn_search radius50" style="float:right">
                                <span>Search</span>
                            </a>
                        </p>
					</div>
					<div>
						<p style="margin:5px 0px 0px 0px;">
							<a href="addindex.htm" class="btn" style="width:100px;text-align: center;">添加</a>
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
					        <th class="head0 nosort">编码</th>
					        <th class="head1 nosort">楼盘名称</th>
					        <th class="head0 nosort">类型</th>
					        <th class="head1 nosort">所在城市</th>
					        <th class="head0 nosort">详细地址</th>
					        <th class="head1 nosort">完成时间</th>
					        <th class="head0 nosort">生成报告</th>
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