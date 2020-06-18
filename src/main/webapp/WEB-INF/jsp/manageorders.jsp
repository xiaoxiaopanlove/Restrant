<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
	<title>订单处理</title>
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
		<div align="left">
			<form name="simple" method="post" action="${pageContext.request.contextPath}/toManageOrders">
				<label>订单号：</label>
				<input type="text" name="oId"
					   <c:if test="${requestScope.page.oId>0 }">value="${requestScope.page.oId }"</c:if>
				/>
				<label>订单状态：</label>
				<select name="orderState">
					<option value="全部" <c:if test="${requestScope.page.orderState=='全部'}">
						selected="selected"
					</c:if>>全部
					</option>
					<option value="已处理"
							<c:if test="${requestScope.page.orderState=='已处理'}">
								selected="selected"
							</c:if>>已处理
					</option>
					<option value="未处理"
							<c:if test="${requestScope.page.orderState=='未处理'}">
								selected="selected"
							</c:if>>未处理
					</option>
				</select>
				<input type="submit" value="查询" />
			</form>
		</div>

		<div style="background-image:url(images/004.gif)">
			&nbsp;
		</div>
		<br />
		<img src="images/icon_order.gif" align="top" />
		订单列表
		<br />
		<hr />
		<table align="center" width="95%" cellspacing="0" cellpadding="3"
			   style="text-align:center; border:1px #cccccc solid;">
			<tr style="background-color:#CCCCFF;">
				<td>订单编号</td>
				<td>订单时间</td>
				<td>订单状态</td>
				<td>总额</td>
				<td>处理</td>
			</tr>
			<c:forEach var="order" items="${requestScope.page.orders }">
				<tr style="background-color:#FFFFFF;">
					<td>
							${order.oId}
					</td>
					<td>
						<fmt:formatDate value="${order.orderTime }" pattern="yyyy-MM-dd HH:mm:SS"/>
					</td>
					<td>
							${order.orderState}
					</td>
					<td>
							${order.orderPrice}
					</td>
					<td>
						<c:if test="${order.orderState=='未处理'}">
							<a href="${pageContext.request.contextPath}/handleOrders?oId=${order.oId}"><img src="images/handle.gif"
																											width="12" height="12" /></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<!-- 分页超链接开始 -->
			<table align="right">
				<tr>
					<td width="130"></td>
					<td width="80">
						<c:if test="${page.pageNum>1}">
							<a href='${pageContext.request.contextPath}/toManageOrdersl?pageNum=1&oId=${requestScope.page.oId}&orderState=${requestScope.page.orderState}'>首页</A>&nbsp;&nbsp;
							<a href='${pageContext.request.contextPath}/toManageOrders?pageNum=${requestScope.page.pageNum-1 }&oId=${requestScope.page.oId}&orderState=${requestScope.page.orderState}'>上一页</a>
						</c:if>
					</td>
					<td width="80">
						<c:if test="${page.pageNum < page.pageCountNum}">
							<a href='${pageContext.request.contextPath}/toManageOrders?pageNum=${requestScope.page.pageNum+1 }&oId=${requestScope.page.oId}&orderState=${requestScope.page.orderState}'>下一页</a>&nbsp;&nbsp;
							<a href='${pageContext.request.contextPath}/toManageOrders?pageNum=${requestScope.pageCountNum }&oId=${requestScope.page.oId}&orderState=${requestScope.page.orderState}'>尾页</a>
						</c:if>
					</td>
					<td>共${requestScope.page.countNum}个记录，共${requestScope.page.pageNum}/${requestScope.page.pageCountNum}页&nbsp;&nbsp;

					</td>
				</tr>
			</table>

			<!-- 分页超链接结束-->

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
