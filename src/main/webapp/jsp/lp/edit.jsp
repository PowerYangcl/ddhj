<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>楼盘编辑</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript">
	function edit(id_){
		if(confirm('您确定要修改楼盘信息吗？')){
			var param = $("#edit").serialize();
			$.ajax({
				url : "edit.htm",
				type : "POST",
				data : param,
				success : function(data) {
					var result = JSON.parse(data);
					if(result.resultCode == 0){
						alert(result.resultMessage);
						window.open("index.htm","_self");
					}else{
						alert(result.resultMessage);
					}
				}
			});
		}
	}
</script>
</head>
<body class="withvernav">
    <div class="bodywrapper">
		<%@ include file="/inc/top.jsp" %>
		<%@ include file="/inc/left.jsp" %>
        <div class="centercontent">
        	<div id="validation" class="subcontent" style="display: block">
        		<form id="edit" class="stdform" >
        			<input type="hidden" id="code" name="code" value="${lp.code}"/>
					<p>
						<label>楼盘名称</label>
						<span class="field">
							<input type="text" name="title" id="title" class="longinput" value="${lp.title}"/>
						</span>
					</p>
					<p>
						<label>城市</label>
						<span class="field">
							<input type="text" name="city" id="city" class="longinput" value="${lp.city}"/>
						</span>
					</p>
					<p>
						<label>详细地址</label>
						<span class="field">
							<input type="text" name="addressFull" id="addressFull" class="longinput" value="${lp.addressFull}"/>
						</span>
					</p>
					<p>
						<label>户数</label>
						<span class="field">
							<input type="text" name="total" id="total" class="longinput" value="${lp.total}"/>
						</span>
					</p>
					<p>
						<label>楼盘完成时间</label>
						<span class="field">
							<input type="text" name="completion" id="completion" class="longinput" value="${lp.completion}"/>
						</span>
					</p>
					<p>
						<label>类型</label>
						<span class="field">
							<input type="text" name="propertyType" id="propertyType" class="longinput" value="${lp.propertyType}"/>
						</span>
					</p>
					<p>
						<label>物业公司</label>
						<span class="field">
							<input type="text" name="propertyCompany" id="propertyCompany" class="longinput" value="${lp.propertyCompany}"/>
						</span>
					</p>
					<p>
						<label>价格</label>
						<span class="field">
							<input type="text" name="price" id="price" class="longinput" value="${lp.price}"/>
						</span>
					</p>
					<p>
						<label>容积率</label>
						<span class="field">
							<input type="text" name="volumeRate" id="volumeRate" class="longinput" value="${lp.volumeRate}"/>
						</span>
					</p>
					<p>
						<label>物业费</label>
						<span class="field">
							<input type="text" name="propertyCosts" id="propertyCosts" class="longinput" value="${lp.propertyCosts}"/>
						</span>
					</p>
					<p>
						<label>停车位</label>
						<span class="field">
							<input type="text" name="parking" id="parking" class="longinput" value="${lp.parking}"/>
						</span>
					</p>
					<p>
						<label>绿化率</label>
						<span class="field">
							<input type="text" name="greeningRate" id="greeningRate" class="longinput" value="${lp.greeningRate}"/>
						</span>
					</p>
					<p>
						<label>楼盘面积</label>
						<span class="field">
							<input type="text" name="gfa" id="gfa" class="longinput" value="${lp.gfa}"/>
						</span>
					</p>
					<p>
						<label>周边地铁</label>
						<span class="field">
							<input type="text" name="metro" id="metro" class="longinput" value="${lp.metro}"/>
						</span>
					</p>
					<p>
						<label>公交车</label>
						<span class="field">
							<input type="text" name="bus" id="bus" class="longinput" value="${lp.bus}"/>
						</span>
					</p>
					<p>
						<label>维度</label>
						<span class="field">
							<input type="text" name="lat" id="lat" class="longinput" value="${lp.lat}"/>
						</span>
					</p>
					<p>
						<label>经度</label>
						<span class="field">
							<input type="text" name="lng" id="lng" class="longinput" value="${lp.lng}"/>
						</span>
					</p>
					<p>
						<label>图片列表</label>
						<span class="field">
							<input type="text" name="images" id="images" class="longinput" value="${lp.images}"/>
						</span>
					</p>
					<p>
						<label>楼盘综述</label>
						<span class="field">
							<input type="text" name="overview" id="overview" class="longinput" value="${lp.overview}"/>
						</span>
					</p>
					<p class="stdformbutton">
						<button type="button" class="submit radius2" onclick="edit()">修改</button>
					</p>
        		</form>
        	</div>
        </div>
	</div>
</body>
</html>