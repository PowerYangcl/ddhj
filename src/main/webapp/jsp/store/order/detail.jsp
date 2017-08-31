<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>订单详情</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
</head>
<body class="withvernav">
    <div class="bodywrapper">
		<%@ include file="/inc/top.jsp" %>
		<%@ include file="/inc/left.jsp" %>
      	<div class="centercontent tables">
			<div id="contentwrapper" class="contentwrapper">
				<table style="margin-top: 25px;border: 1px solid;" cellpadding="0" cellspacing="0" border="0" class="stdtable">
					<tbody>
						<tr>
							<td>订单编码</td>
							<td>${order.code }</td>
						</tr>
						<tr>
							<td>订单金额</td>
							<td>${order.payMoney }</td>
						</tr>
						<tr>
							<td>订单状态</td>
							<td><span>
								<c:choose>
									<c:when test="${order.orderStatus =='8866001' }">下单成功(已付款)</c:when>
									<c:when test="${order.orderStatus == '8866002' }">下单成功(待付款)</c:when>
									<c:when test="${order.orderStatus == '8866003' }">订单作废</c:when>
									<c:when test="${order.orderStatus == '8866004' }">订单已确认</c:when>
									<c:when test="${order.orderStatus == '866005' }">订单已取消</c:when>
									<c:otherwise>-</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>收货人姓名</td>
							<td>${order.address.name }</td>
						</tr>
						<tr>
							<td>收货人手机号</td>
							<td>${order.address.phone }</td>
						</tr>
					</tbody>
				</table>
				<p></p>
				<table cellpadding="0" cellspacing="0" border="0" class="stdtable">
					<thead>
					    <tr>
					        <th class="head0 nosort">商品编码</th>
					        <th class="head1 nosort">商品名称</th>
					        <th class="head0 nosort">价格</th>
					        <th class="head1 nosort">数量</th>
					    </tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${order.details }">
							<tr>
								<td>${p.productCode }</td>
								<td>${p.product.productName }</td>
								<td>${p.currentPrice }</td>
								<td>${p.buyNum }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div style="margin: 10px;text-align: center;">
					<button class="stdbtn btn_blue" onclick="window.open('index.htm','_self')">返回</button>
				</div>
			</div>
        </div>
	</div>
</body>
</html>