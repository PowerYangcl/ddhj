/**
 * 微信商城-商品管理
 */
var ProductOrder={
	init:function(){
		var type_ = 'post';
		var url_ = 'data.htm';
		var data_ = {"pageIndex":0,"pageSize":10};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(ProductOrder.loadTable);
	},
	loadTable:function(url_){
		if (url_ == undefined) { // 首次加载表单
			ProductOrder.drawTable(aForm.jsonObj);
			return;
		}
		// 这种情况是响应上一页或下一页的触发事件
		var type_ = 'post';
		var data_ = null;
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init();
		ProductOrder.drawTable(obj);
	},
	drawTable:function(obj){
		$('#data tr').remove();
		var html = '';
		var list = obj.page.list;
		if (list.length > 0) {
			for (var i = 0; i < list.length; i++) {
				var obj = list[i];
				html +="<tr class=\"gradeX\">";
				html +="<td align=\"center\"><span class=\"center\"> <input type=\"checkbox\"/> </span></td>";
				html +="<td width=\"100px\">"+obj.code+"</td>";
				html +="<td width=\"100px\">"+obj.payMoney+"</td>";
				html +="<td>";
				if(obj.orderStatus == '8866001'){
					html +="下单成功(已付款)";
				}else if(obj.orderStatus == '8866002'){
					html +="下单成功(待付款)";
				}else if(obj.orderStatus == '8866003'){
					html +="订单作废";
				}else if(obj.orderStatus == '8866004'){
					html +="订单已确认";
				}else if(obj.orderStatus == '866005'){
					html+="订单已取消";
				}else{
					html+"-";
				}
				html +="</td>";
				html +="<td>"+obj.user.nickName+"</td>";
				html +="<td>"+obj.buyerPhone+"</td>";
				html +="<td>"+obj.createTime+"</td>";
				html +="<td>"+obj.updateTime+"</td>";
				html +="<td>";
				html +="<a style=\"padding-right: 5px;\" href=\"detail.htm?code="+obj.code+"\">查看</a>";
				html +="</td>";
				html +="</tr>";
			}
		} else {
			html = "<tr><td colspan=\"10\" style=\"text-align: center;\">"
					+ obj.resultMessage + "</td></tr>";
		}
		$('#data').append(html);
	},
	search:function(){
		var type_ = 'post';
		var url_ = 'data.htm';
		var data_ = {"buyPhone":$("#buyPhone").val(),"nickName":$("#nickName").val(),"code":$("#code").val(),"orderStatus":$("#orderStatus").val(),"pageIndex":0,"pageSize":10};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(ProductOrder.loadTable);
	},
	add:function(){
		if(files.length>0){
			var images = new Array();
			for(var key in files){
				var obj = files[key];
				images.push(obj.path+obj.name);
			}
			$("#images").val(images.join(","));
		}
		var param = $("#addFrm").serializeArray();
		$.ajax({
			url : "add.htm",
			type : "POST",
			dataType : "json",
			data : param,
			success : function(result) {
				if(result.resultCode == 1){
					jAlert(result.resultMessage,"提示",function(){
						window.open("index.htm","_self");						
					});
				}else{
					jAlert(result.resultMessage,"提示");
				}
			}
		});
	},
	edit:function(){
		if(files.length>0){
			var images = new Array();
			for(var key in files){
				var obj = files[key];
				images.push(obj.path+obj.name);
			}
			$("#images").val(images.join(","));
		}
		jConfirm('您确定要编辑此商品吗?', '提示', function(r) {
			if(r){
				var param = $("#editFrm").serializeArray();
				$.ajax({
					url : "edit.htm",
					type : "POST",
					data : param,
					dataType : "json",
					success : function(result) {
						if(result.resultCode == 1){
							jAlert(result.resultMessage, '提示',function(){
								window.open("index.htm","_self");
							});
							
						}else{
							jAlert(result.resultMessage, '提示');
						}
					}
				});
			}
		});
	},
	del:function(orderCode){
		jConfirm('您确定要删除此商品吗?', '提示', function(r) {
			if(r){
				var param = {"code":orderCode};
				$.ajax({
					url : "del.htm",
					type : "POST",
					data : param,
					dataType : "json",
					success : function(result) {
						if(result.resultCode == 1){
							jAlert(result.resultMessage, '提示',function(){
								window.open("index.htm","_self");
							});
						}else{
							jAlert(result.resultMessage, '提示');
						}
					}
				});
			}
		});
	}
};