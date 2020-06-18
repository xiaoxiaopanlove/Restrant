<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>修改餐品</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			var mealPrice=$("input[name='mealPrice']");
			mealPrice.on("blur",function() {
				var reg=/^\d+(\.\d+)?$/;
				if(mealPrice.val().search(reg)){
					alert("请输入一个正数！")
				}
			})
		})
	</script>
</head>

<%@include file="common/head.jsp" %>
<tr>
	<td valign="top" align="center">
		<p>
			<img src="images/left_top.jpg" width="215" height="100" />
			<br>
			<img src="images/003.gif" width="191" height="8">
	</td>
	<td valign="top" width="80%">
		<img src="images/001.jpg" width="595" height="72" />
		<br />
		<div style="background-image: url(images/004.gif)">
			&nbsp;
		</div>
		<div style="background-color: #FFCC99;" align="center">
			修改餐品
		</div>
		<br />
		<form action="${pageContext.request.contextPath}/doUpdateMeal" method="post" enctype="multipart/form-data">
			<table align="center" >
				<tr>
					<td>菜名:</td><td>
					<input name="mealId" type="hidden" value="${requestScope.meal.mealId }"/>
					<input name="mealName" type="text" value="${requestScope.meal.mealName}"/></td>
				</tr><tr>
				<td>菜系:</td><td><select name="mealSeriesId">
				<c:forEach var="mealSeries" items="${requestScope.mealSeries}">
					<option value="${mealSeries.seriesId}"
							<c:if test="${mealSeries.seriesId==meal.mealSeriesId}">
								selected="selected"
							</c:if>
					>${mealSeries.seriesName } </option>
				</c:forEach>
			</select></td>
			</tr><tr>
				<td>摘要:</td><td><textarea name="mealSummarize">${requestScope.meal.mealName}</textarea></td>
			</tr><tr>
				<td>介绍:</td><td><textarea name="mealDescription">${requestScope.meal.mealDescription}</textarea></td>
			</tr><tr>
				<td>价格:</td><td><input name="mealPrice" type="text" value="${requestScope.meal.mealPrice}"/></td>
			</tr><tr>
				<td>图片:</td><td>
				<input name="mealImage" type="hidden" value="${requestScope.meal.mealImage}"/>
				<input name="images" type="file" />
			</td>
			</tr><tr>
				<td colspan="2" align="center"><input type="submit" value="确定"/></td>
			</tr>
			</table>
		</form>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<br>
		<hr width=100%>
		<br>
	</td>
</tr>
</table>
</body>
</html>
