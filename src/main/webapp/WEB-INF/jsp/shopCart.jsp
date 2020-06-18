<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
	<title>购物车页面</title>
</head>
<script type="text/javascript">
	var reg = /^[1-9]\d*$/;
	function onchangeNum(mealId,mealCount){
		if(reg.exec(mealCount)){
			location.href="updateMealCount?mealId="+mealId+"&mealCount="+mealCount;
		}else{
			alert("请输入一个正整数！")
		}
	}
</script>

<%@include file="common/head.jsp" %>
<tr>
	<td valign="top" align="center" >
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
		<img src="images/zdkf.jpg" align="top" />
		您的购物车中有以下商品
		<br />
		<hr />
		<table align="center" width="95%" cellspacing="0" cellpadding="3"
			   style="text-align:center; border:1px #cccccc solid;">
			<tr style="background-color:#CCCCFF;">
				<td>编号</td>
				<td>商品名称</td>
				<td>单价</td>
				<td>数量</td>
				<td>金额</td>
				<td>删除</td>
			</tr>
			<c:forEach var="shopCart" items="${sessionScope.shopCart}" varStatus="count">
				<tr style="background-color:#FFFFFF;">
					<td>
							${count.index+1}
					</td>
					<td>
							${shopCart.value.meal.mealName}
					</td>
					<td>
						￥<fmt:formatNumber value="${shopCart.value.meal.mealPrice}" pattern="0.00"/>
					</td>
					<td>
						<input type="text"  value="${shopCart.value.mealCount}" size="10"
							   onchange="javascript:onchangeNum(${shopCart.key},this.value);">

					</td>
					<td>
						￥<fmt:formatNumber value="${shopCart.value.meal.mealPrice*shopCart.value.mealCount}" pattern="0.00"/>
					</td>
					<td>
						<a href="deleteOrders?mealId=${shopCart.key}">删除</a>
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
					￥：<fmt:formatNumber value="${sessionScope.sumprice}" pattern="0.00"/>
				</td>
				<td>
					-
				</td>
			</tr>
		</table>

		<br />
		<table width="300" cellspacing="0" cellpadding="4" align="center"
			   style="text-align:center; border:1px #cccccc solid;">
			<tr style="background-color:#CCCCFF;">
				<td>
					<a href="${pageContext.request.contextPath}/clearCart">清空购物车</a>
				</td>
				<td>
					<a href="${sessionScope.showurl}">继续购物</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/addOrders">生成订单</a>
				</td>
			</tr>
		</table>
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
