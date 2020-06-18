<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css" />

<body>
<table width="95%" height="170" border="0" cellpadding="0"
	   cellspacing="0" align="center">
	<tr>
		<td width="200" height="101">
			<img src="images/jb_logo.jpg" width="64" height="32" />
			<strong><span
					style="font-size: 20px;">风入松手工烘培店</span> </strong>
		</td>
		<td width="640" style="padding-left: 40px;">

		</td>
	</tr>
	<tr>
		<td height="41" colspan="2"
			style="background-image:url(images/001.gif);" align="center">
			|
			<a href="${pageContext.request.contextPath}/show.html">网站首页</a> |
			<c:if test="${sessionScope.admin==null && sessionScope.user==null}">
				<a href="${pageContext.request.contextPath}/to_register">用户注册</a> |
				<a href="${pageContext.request.contextPath}/to_login?role=user">用户登录</a> |
				<a href="${pageContext.request.contextPath}/to_login?role=admin">管理员登录</a> |
			</c:if>
			<c:if test="${sessionScope.user!=null}">
				<a href="${pageContext.request.contextPath}/update_userInfo">修改个人信息</a> |
				<a href="${pageContext.request.contextPath}/toMyShopCart">我的购物车</a> |
				<a href="${pageContext.request.contextPath}/toMyOrders">我的订单</a> |
				<a href="${pageContext.request.contextPath}/login_out?role=user">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
				<font style="color: red">欢迎您：${sessionScope.user.trueName }</font>
			</c:if>
			<c:if test="${sessionScope.admin!=null}">
				<a href="${pageContext.request.contextPath}/toAddMeal">添加甜品</a> |
				<a href="${pageContext.request.contextPath}/toManageMeal">管理商品</a> |
				<a href="${pageContext.request.contextPath}/toManageOrders">订单处理</a> |
				<a href="${pageContext.request.contextPath}/login_out?role=admin">注销</a> &nbsp;&nbsp; &nbsp;&nbsp;
				<font style="color: red">欢迎您：${sessionScope.admin.loginName }</font>
			</c:if>

		</td>
	</tr>
