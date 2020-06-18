<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
    
	<title>甜品列表显示</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
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
			<!-- 菜系循环开始 -->
			<c:forEach var="mealSeries" items="${requestScope.page.mealSeries}">
			<br>
			<a href="${pageContext.request.contextPath}/show.html?querySeriesId=${mealSeries.seriesId}">${mealSeries.seriesName }</a>
			<br>
			</c:forEach>
			<!-- 菜系循环结束 -->

	</td>
	<td valign="top" width="80%">
		<img src="images/001.jpg" width="595" height="72" />
		<br />
		<div align="left">
			<form method="post" action="${pageContext.request.contextPath }/show.html">
				<label>输入菜名：</label>
				<input type="text" name="queryMealName" value="${requestScope.page.queryMealName}"/>
				<input type="hidden" name="querySeriesId" value="${requestScope.page.querySeriesId}">
				<input type="submit" value="查询">
			</form>
		</div>
		<br />
		<div style="background-image: url(images/004.gif)">
			&nbsp;
		</div>
		<div style="background-color: #FFCC99;" align="right">
			<a href="javascript:alert('长得帅的才点得进去！');"><img src="images/lcart_cn.gif"
														   width="97" height="30" border="0" /> </a>
		</div>
		<br />
		<table cellpadding="5" cellspacing="1" style="font-size: 12px;">
			<!-- 餐品循环开始 -->
			<c:forEach var="meal" items="${requestScope.page.meals }" varStatus="status">
				<c:if test="${status.index%3==0 }">
					<tr>
				</c:if>
				<td>
					<a href="${pageContext.request.contextPath }/showDetails?mealId=${meal.mealId}">
						<img src="mealimages/${meal.mealImage }" width="148" height="126" border="0" />
					</a>
				</td>
				<td>
					<div>
							${ meal.mealId}
						:
							${ meal.mealName}
						<br />
						<span style="text-decoration: line-through; color: gray;">原价：人民币${ meal.mealPrice}元</span>
						<br />
						现价：人民币
						<fmt:formatNumber value="${ meal.mealPrice*0.9}" pattern="0.0"/>
						元
					</div>
					<a href="${pageContext.request.contextPath }/showDetails?mealId=${meal.mealId}"><img src="images/detail_cn.gif" border="0" width="60" height="20" /></a>
					<a  href="javascript:fun(${meal.mealId});"><img src="images/buy_cn.gif" border="0" width="60" height="20" /></a>
				</td>
				<c:if test="${status.index%3==2 }">
					</tr>
				</c:if>
			</c:forEach>
			<!-- 餐品循环开始 -->

			<!-- 分页超链接开始 -->
			<table align="right">
				<tr>
					<td width="130"></td>
					<td width="80">
						<c:if test="${page.pageNum>1}">
							<a href='${pageContext.request.contextPath}/show.html?pageNum=1&querySeriesId=${requestScope.page.querySeriesId}&queryMealName=${requestScope.page.queryMealName}'>首页</A>&nbsp;&nbsp;
							<a href='${pageContext.request.contextPath}/show.html?pageNum=${requestScope.page.pageNum-1 }&querySeriesId=${requestScope.page.querySeriesId}&queryMealName=${requestScope.page.queryMealName}'>上一页</a>
						</c:if>
					</td>
					<td width="80">
						<c:if test="${page.pageNum < page.pageCountNum}">
							<a href='${pageContext.request.contextPath}/show.html?pageNum=${requestScope.page.pageNum+1 }&querySeriesId=${requestScope.page.querySeriesId}&queryMealName=${requestScope.page.queryMealName}'>下一页</a>&nbsp;&nbsp;
							<a href='${pageContext.request.contextPath}/show.html?pageNum=${requestScope.pageCountNum }&querySeriesId=${requestScope.page.querySeriesId}&queryMealName=${requestScope.page.queryMealName}'>尾页</a>
						</c:if>
					</td>
					<td>共${requestScope.page.countNum}个记录，共${requestScope.page.pageNum}/${requestScope.page.pageCountNum}页&nbsp;&nbsp;

					</td>
				</tr>
			</table>
			<!-- 分页超链接结束-->

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
