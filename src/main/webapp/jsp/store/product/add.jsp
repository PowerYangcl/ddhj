<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品添加</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
</head>
<body class="withvernav">
    <div class="bodywrapper">
		<%@ include file="/inc/top.jsp" %>
		<%@ include file="/inc/left.jsp" %>
        <div class="centercontent">
        	<div id="validation" class="subcontent" style="display: block">
        		<form id="addFrm" class="stdform" >
					<p>
						<label>商品名称</label>
						<span class="field">
							<input type="text" name="productName" id="productName" class="longinput" value=""/>
						</span>
					</p>
					<p>
						<label>商品主图</label>
						<span class="field">
							<input type="text" name="mainPicUrl" id="mainPicUrl" class="longinput" value=""/>
						</span>
					</p>
					<p>
						<label>当前价格</label>
						<span class="field">
							<input type="text" name="currentPrice" id="currentPrice" class="longinput" value=""/>
						</span>
					</p>
					<p>
						<label>库存</label>
						<span class="field">
							<input type="text" name="stockNum" id="stockNum" class="longinput" value=""/>
						</span>
					</p>
					<p>
						<label>是否可售</label>
						<span class="field">
							<select id="flagSellable" name="flagSellable">
								<option value="0">可售</option>
								<option value="1">不可售</option>
							</select>
						</span>
					</p>
					<p>
						<label>商品说明提示</label>
						<span class="field">
							<input type="text" name="productTip" id="productTip" class="longinput" value=""/>
						</span>
					</p>
					<p class="stdformbutton">
						<button type="button" class="submit radius2" onclick="Product.add()">添加</button>
					</p>
        		</form>
        	</div>
        </div>
	</div>
<script type="text/javascript" src="${js}/commons/store/product.js"></script>
</body>
</html>