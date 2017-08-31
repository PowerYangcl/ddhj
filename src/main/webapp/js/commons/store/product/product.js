/**
 * 微信商城-商品管理
 */
var Product={
	init:function(){
		var type_ = 'post';
		var url_ = 'data.htm';
		var data_ = {"pageIndex":0,"pageSize":10};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(Product.loadTable);
	},
	loadTable:function(url_){
		if (url_ == undefined) { // 首次加载表单
			Product.drawTable(aForm.jsonObj);
			return;
		}
		// 这种情况是响应上一页或下一页的触发事件
		var type_ = 'post';
		var data_ = null;
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init();
		Product.drawTable(obj);
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
				html +="<td width=\"100px\">"+obj.productCode+"</td>";
				html +="<td>"+obj.productName+"</td>";
				html +="<td>"+obj.currentPrice+"</td>";
				html +="<td>"+obj.stockNum+"</td>";
				if(obj.flagSellable == 0){
					html +="<td>可售</td>";	
				}else{
					html +="<td>不可售</td>";
				}
				html +="<td>"+obj.createUser+"</td>";
				html +="<td>"+obj.createTime+"</td>";
				html +="<td>"+obj.updateUser+"</td>";
				html +="<td>"+obj.updateTime+"</td>";
				html +="<td>";
				html +="<a style=\"padding-right: 5px;\" href=\"detail.htm?productCode="+obj.productCode+"\">查看</a>";
				html +="<a style=\"padding-left: 5px;padding-right: 5px;\" href=\"editindex.htm?productCode="+obj.productCode+"\">编辑</a>";
				html +="<a style=\"padding-left: 5px;\" href=\"javascript:void(0)\" onclick=\"Product.del('"+obj.productCode+"')\">删除</a>";
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
		var data_ = {"productCode":$("#productCode").val(),"productName":$("#productName").val(),"pageIndex":0,"pageSize":10};
		var obj = JSON.parse(ajaxs.sendAjax(type_, url_, data_));
		aForm.launch(url_, 'table-form', obj).init().drawForm(Product.loadTable);
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
	del:function(productCode){
		jConfirm('您确定要删除此商品吗?', '提示', function(r) {
			if(r){
				var param = {"productCode":productCode};
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