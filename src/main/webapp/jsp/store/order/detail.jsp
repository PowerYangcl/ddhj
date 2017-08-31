<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品详情</title>
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
							<td>商品编码</td>
							<td>${product.productCode }</td>
						</tr>
						<tr>
							<td>商品名称</td>
							<td>${product.productName }</td>
						</tr>
						<tr>
							<td>商品图片</td>
							<td>${product.mainPicUrl }</td>
						</tr>
						<tr>
							<td>当前价格</td>
							<td>${product.currentPrice }</td>
						</tr>
						<tr>
							<td>库存</td>
							<td>${product.stockNum }</td>
						</tr>
						<tr>
							<td>当前价格</td>
							<td>
								<c:choose>
									<c:when test="${product.flagSellable == 0 }">可售</c:when>
									<c:otherwise>不可售</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td>说明提示</td>
							<td>${product.productTip }</td>
						</tr>
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