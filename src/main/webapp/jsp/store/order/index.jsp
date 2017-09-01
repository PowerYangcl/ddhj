<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>微信商城-订单管理</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
<script src="${js}/blockUI/jquery.blockUI.js" type="text/javascript"></script>

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
						<p style="margin: 2px">
                            <label>收货人手机号：</label>
                            <span class="field"><input id="buyerPhone" type="text" name="buyerPhone"  class="form-search"/></span>
						</p>
						<p style="margin: 2px">
                            <label>收货人姓名：</label>
                            <span class="field"><input style="margin-left: 11px;" id="nickName" type="text" name="nickName"  class="form-search"/></span>
						</p>
						<p style="margin: 2px">
                            <label>订单编码：</label>
                            <span class="field"><input style="margin-left: 22px;" id="code" type="text" name="code"  class="form-search"/></span>
						</p>
						<p style="margin: 2px">
                            <label>订单状态：</label>
                            <span class="field">
                            	<select style="margin-left: 22px;padding: 8px 5px;" id="orderStatus" name="orderStatus" class="form-search">
                            		<option value="">请选择</option>
                            		<option value="8866002">请下单成功(待付款)</option>
                            		<option value="8866001">下单成功(已付款)</option>
                            		<option value="8866003">订单作废</option>
                            		<option value="8866004">订单已确认</option>
                            		<option value="866005">订单已取消</option>
                            	</select>
                            </span>
						</p>
                        <p style="margin:5px 0px 0px 0px;">
                            <a href="javascript:void(0)" onclick="ProductOrder.search()" class="btn btn_orange btn_search radius50" style="float:right">
                                <span>Search</span>
                            </a>
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
					        <th class="head1 nosort">订单金额</th>
					        <th class="head0 nosort">订单状态</th>
					        <th class="head1 nosort">购买人</th>
					        <th class="head1 nosort">手机号</th>
					        <th class="head1 nosort">创建时间</th>
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
	<div id="editOrderStatus" class="bodywrapper" style="margin-top: 39px;display: none;">
		<input type="hidden" name="orderCode" id="orderCode" value=""/>
		<div>
			<span style="font-size:14px;">订单状态</span>
	       	<select style="margin-left: 22px;padding: 8px 5px;" id="ediStatus" name="ediStatus">
	       		<option value="">请选择</option>
	       		<option value="8866002">请下单成功(待付款)</option>
	       		<option value="8866001">下单成功(已付款)</option>
	       		<option value="8866003">订单作废</option>
	       		<option value="8866004">订单已确认</option>
	       		<option value="866005">订单已取消</option>
	       	</select>
		</div>
       	<div style="margin-top:55px;">
       		<button type="button" class="stdbtn btn_blue" onclick="updateOrderStatus();">确认</button>
       		<button style="margin:10px;" type="button" onclick="closeDialog();" class="stdbtn">取消</button>
       	</div>
	</div>
<script type="text/javascript" src="${js}/commons/store/order/product.order.js"></script>
<script type="text/javascript">
$(function(){
	ProductOrder.init();
});
function openUdateStatus(code){
	$("#orderCode").val(code);
    $.blockUI({
        showOverlay:true ,
        css:  {
            cursor:'auto',
            left: ($(window).width() - 300) / 2 + 'px',
            top: ($(window).height() - 300) / 2 + 'px',
            width:'300px',
            height:'220px',
            position:'fixed', //居中
            border: '3px solid #FB9337'  // 边界
        },
        message: $('#editOrderStatus'),
        fadeIn: 500,//淡入时间
        fadeOut: 1000  //淡出时间
    });
}
function updateOrderStatus(){
	if($("#ediStatus").val() == ""){
		jAlert("请选择要修改的订单状态");
		return false;
	}
	var param = {"code":$("#orderCode").val(),"orderStatus":$("#ediStatus").val()};
	$.ajax({
		url : "editOrderStatus.htm",
		type : "POST",
		dataType : "json",
		data : param,
		success : function(result) {
			$("#orderCode").val(code);
			jAlert(result.resultMessage,"提示",function(){
				closeDialog();
			});
		}
	});
}
function closeDialog(){
	$("#orderCode").val(code);
    $.unblockUI();
    ProductOrder.search()
}
</script>
</body>
</html>