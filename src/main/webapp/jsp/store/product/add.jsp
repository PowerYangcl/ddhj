<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>商品添加</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
<link rel="stylesheet" href="${basePath }bootstrap/3.3.5/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${basePath }bootstrap/fileinput/css/fileinput.min.css" type="text/css" />
<script type="text/javascript" src="${basePath }bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body class="withvernav">
    <div class="bodywrapper">
		<%@ include file="/inc/top.jsp" %>
		<%@ include file="/inc/left.jsp" %>
        <div class="centercontent">
        	<div id="validation" class="subcontent" style="display: block">
        		<form id="addFrm" class="stdform" enctype="multipart/form-data">
        			<input type="hidden" id="images" name="images" value="" />
					<p>
						<label>商品名称</label>
						<span class="field">
							<input type="text" name="productName" id="productName" class="longinput" value=""/>
						</span>
					</p>
					<p>
						<label>商品主图</label>
						<span class="field">
							<input class="form-control file" type="file" name="uploadFile[]" id="uploadFile" value="" multiple />
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
					<p class="stdformbutton" style="text-align:center;">
						<button style="margin:10px;" type="button" class="stdbtn btn_blue" onclick="Product.add()">添加</button>
						<button style="margin:10px;" type="button" class="stdbtn" onclick="window.open('index.htm','_self')">取消</button>
					</p>
        		</form>
        	</div>
        </div>
	</div>
<script type="text/javascript" src="${basePath }bootstrap/fileinput/js/fileinput.min.js"></script>
<script type="text/javascript" src="${basePath }bootstrap/fileinput/js/fileinput_locale_zh.js"></script>
<script type="text/javascript">
var fileSize = 10 * 1024 * 1024;//设置文件上传大小
$('#uploadFile').fileinput({
	language: 'zh',
	//上传的地址
	uploadUrl: 'upload.htm',
	////接收的文件后缀,
	allowedFileExtensions: ['jpg'],
	//最大上传数量
	maxFileCount: 5,
	maxFileSize: fileSize,
	enctype: 'multipart/form-data',
	showUpload: true, //是否显示上传按钮
	showCaption: false, //是否显示标题
	browseClass: "btn btn-primary", //按钮样式             
	previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
	msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
});
var files = new Array();//上传文件路径数组
//上传回调函数
$("#uploadFile").on("fileuploaded", function(event, data, previewId, index) {
	//将上传完成的文件信息添加到files
	initFileArray(data.response);
});
$("#uploadFile").on("fileclear",function(){
	if(files.length>0){
		$.ajax({
			url : "file/del.htm",
			dataType :"json",
			data:{"file":JSON.stringify(files)},
			success:function(data){
				if(data > 0){
					//初始化files
					files = new Array();					
				}
			}
		});
	}
});
//删除上传文件函数
$("#uploadFile").on("filesuccessremove",function(event, previewId, index){
	if(files[index]){
		var file = new Array();
		file.push(files[index]);
		$.ajax({
			url : "file/del.htm",
			dataType :"json",
			data:{"file":JSON.stringify(file)},
			success:function(data){
				delete files[index];
			}
		});
	}
});
function initFileArray(data){
	if(data){
		for(var key in data){
			var obj = data[key];
			if($.inArray(obj,files)<0){
				files.push(obj);
			}
		}
	}
}
</script>
<script type="text/javascript" src="${js}/commons/store/product.js"></script>
</body>
</html>