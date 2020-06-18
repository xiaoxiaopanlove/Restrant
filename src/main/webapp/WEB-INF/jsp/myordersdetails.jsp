<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
	<title>我的订单明细</title>
	<!--
    <link rel="stylesheet" type="text/css" href="CSS/styles.css">
     -->
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

		<div style="background-image:url(images/004.gif)">
			&nbsp;
		</div>

		<br />
		<img src="images/icon_order.gif" align="top" />
		订单明细
		<br />
		<hr />
		<table align="center" width="95%" cellspacing="0" cellpadding="3"
			   style="text-align:center; border:1px #cccccc solid;">
			<tr style="background-color:#CCCCFF;">
				<td>明细编号</td>
				<td>菜名</td>
				<td>价格</td>
				<td>数量</td>
				<td>总额</td>
			</tr>
			<c:forEach var="orderdts" items="${requestScope.orderdts }">
				<tr style="background-color:#FFFFFF;">
					<td>
							${orderdts.odId }
					</td>
					<td>
							${orderdts.meal.mealName }
					</td>
					<td>
							${orderdts.mealPrice }
					</td>
					<td>
							${orderdts.mealCount}
					</td>
					<td>
							${orderdts.mealPrice*orderdts.mealCount}
					</td>
				</tr>
			</c:forEach>
			<tr style="background-color:#CCCCFF;">
				<td>
					合计
				</td>
				<td>
					-
				</td>
				<td>
					-
				</td>

				<td>
					-
				</td>
				<td>
					￥：${requestScope.orderSum}
				</td>

			</tr>
		</table>
		<br />

	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<br>
		<hr width=100%>
		<br>
		<br>
	</td>
</tr>
</table>
</body>
</html>
