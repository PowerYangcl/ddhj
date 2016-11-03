<%@ include file="/inc/resource.inc"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>楼盘编辑</title>
<%@ include file="/inc/head.jsp"%>
<script type="text/javascript" src="${js}/system/ajax-form.js"></script>
<script type="text/javascript">
	function edit(){
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
</script>
</head>
<body class="withvernav">
    <div class="bodywrapper">
		<%@ include file="/inc/top.jsp" %>
		<%@ include file="/inc/left.jsp" %>
        <div class="centercontent">
        	<div id="validation" class="subcontent" style="display: block">
        		<form id="edit" class="stdform" >
        			<input type="hidden" name="code" id="code" value="${r.code }"/>
					<p>
						<label>楼盘名称</label>
						<span class="field">
							<input type="text" disabled="disabled" class="longinput" value="${r.lpName }"/>
							<input type="hidden" name="housesCode" id="housesCode" value="${r.housesCode }"></input>
						</span>
					</p>
					<p>
						<label>报告名称</label>
						<span class="field">
							<input type="text" name="title" id="title" class="longinput" value="${r.title }"/>
						</span>
					</p>
					<p>
						<label>报告等级</label>
						<span class="field">
							<select id="levelCode" name="levelCode">
								<c:forEach var="level" items="${rl }">
									<option <c:if test="${r.levelCode == level.code }">selected="selected"</c:if> value="${level.code }">${level.name }</option>
								</c:forEach>
							</select>
						</span>
					</p>
					<p>
						<label>图标</label>
						<span class="field">
							<input type="text" name="pic" id="pic" class="longinput" value="${r.pic }"/>
						</span>
					</p>
					<p>
						<label>大图</label>
						<span class="field">
							<input type="text" name="image" id="image" class="longinput" value="${r.image }"/>
						</span>
					</p>
					<p>
						<label>范围</label>
						<span class="field">
							<input type="text" name="rang" id="rang" class="longinput" value="${r.rang }"/>
						</span>
					</p>
					<p>
						<label>价格</label>
						<span class="field">
							<input type="text" name="price" id="price" class="longinput" value="${r.price }"/>
						</span>
					</p>
					<p>
						<label>报告说明</label>
						<span class="field">
							<input type="text" name="detail" id="detail" class="longinput" value="${r.rDesc }"/>
						</span>
					</p>
					<p class="stdformbutton">
						<button type="button" class="submit radius2" onclick="edit()">编辑</button>
					</p>
        		</form>
        	</div>
        </div>
	</div>
</body>
</html>