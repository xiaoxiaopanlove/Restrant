<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>

	<title>餐品详细页面</title>
	<script type="text/javascript">
		function fun(mealId){
			if(${sessionScope.user==null}){
				if(confirm("请先登录，再进行此操作！")){
					location.href="${pageContext.request.contextPath}/to_login?role=user";
				}
			}else{
				var reg = /^[1-9]\d*$/;
				var flag=true;
				while(flag){
					var mealCount=prompt("请输入订购数量");
					if(reg.exec(mealCount)){
						flag=false;
					}else if(!mealCount){
						flag=false;
					}else{
						alert("输入错误！请重新输入...")
					}
				}
				$.ajax({
					data:{"mealId":mealId,"mealCount":mealCount},
					type:"post",
					url:"${pageContext.request.contextPath}/add_to_shopcart",
					success:function(data){
						alert("成功添加到购物车！")
					}
				})
			}
		}
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
		<div style="background-image:url(images/004.gif)">
			&nbsp;
		</div>
		<div style="background-color:#FFCC99;" align="center">
			餐品详情
		</div>
		<br>
		<br />
		<table width="616" border="0" cellspacing="0" cellpadding="3">
			<tr>
				<td width="148">
					<img src="mealimages/${requestScope.meal.mealImage}" width="148" height="126" />
				</td>
				<td width="468" valign="top">
					${requestScope.aMeal.mealName }
					<br />
					<span style="text-decoration:line-through;color:gray;">原价：人民币${requestScope.meal.mealPrice }元</span>
					<br />
					现价：人民币
					<fmt:formatNumber value="${requestScope.meal.mealPrice*0.9 }" pattern="0.0"/>
					元
					<br />
					${requestScope.meal.mealSummarize }
				</td>
			</tr>
			<tr>
				<td>
					编号：${requestScope.meal.mealId}
				</td>
				<td>
					<a href="javascript:fun(${requestScope.meal.mealId})"><img src="images/buy_cn.gif" border="0" width="60" height="20" /></a>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<div align="center">
						详细资料
					</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<hr />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<img src="mealimages/${requestScope.meal.mealImage}" width="228" height="169" />
					<br />
					<br />
					${requestScope.meal.mealDescription }
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<br>
		<hr width=100%>
	</td>
</tr>
</table>
</body>
</html>
