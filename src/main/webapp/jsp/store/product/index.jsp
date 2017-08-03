<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信商城-商品管理</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>

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
                            <label>商品编码：</label>
                            <span class="field"><input id="productCode" type="text" name="productCode"  class="form-search"/></span>
						</p>
						<p style="margin: 0px">
                            <label>商品名称：</label>
                            <span class="field"><input id="productName" type="text" name="productName"  class="form-search"/></span>
						</p>
                        <p style="margin:5px 0px 0px 0px;">
                            <a href="javascript:void(0)" onclick="Product.search()" class="btn btn_orange btn_search radius50" style="float:right">
                                <span>Search</span>
                            </a>
                        </p>
					</div>
					<div>
						<p style="margin:5px 0px 0px 0px;">
							<button onclick="window.open('addindex.htm','_self')" class="stdbtn" style="width:100px;text-align: center;">添加</button>
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
					        <th class="head0 nosort">商品编码</th>
					        <th class="head1 nosort">商品名称</th>
					        <th class="head0 nosort">当前价格</th>
					        <th class="head1 nosort">库存</th>
					        <th class="head1 nosort">是否可售</th>
					        <th class="head0 nosort">创建人</th>
					        <th class="head1 nosort">创建时间</th>
					        <th class="head0 nosort">修改人</th>
					        <th class="head1 nosort">修改时间</th>
					        <th class="head1 " width="100px">操作</th>
					    </tr>
					</thead>
					<tbody id="data">
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript" src="${js}/commons/store/product/product.js"></script>
<script type="text/javascript">
$(function(){
	Product.init();
});
</script>
</body>
</html>