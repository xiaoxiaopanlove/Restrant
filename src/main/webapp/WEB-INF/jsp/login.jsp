<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>登录页面</title>
</head>
<%@include file="common/head.jsp" %>
<tr>
	<td valign="top" align="center">
		<p>
			<img src="images/left_top.jpg" width="215" height="100" />
	</td>
	<td valign="top" width="80%">
		<img src="images/001.jpg" width="595" height="72" />
		<br />

		<div style="background-image:url(images/004.gif)">
			&nbsp;
		</div>
		<div style="background-color:#FFCC99;" align="center">
			网上订餐系统用户请直接登录
		</div>
		<br>
		<br />
		<c:if test="${requestScope.role=='user' }">
			<form action="user_login" method="post" name="ufrm">
				<table width="263" border="0" cellspacing="0" cellpadding="4"
					   align="center">
					<tr>
						<td width="74">
							用户名：
						</td>
						<td width="189">
							<input type="text" name="loginName" style="width:150;" />
						</td>
					</tr>
					<tr>
						<td>
							密 &nbsp;&nbsp;码：
						</td>
						<td>
							<input type="password" name="loginPwd" style="width:150;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							<input type="submit" name="login" value="登 录" />
						</td>
					</tr>
				</table>
			</form>
		</c:if>


		<c:if test="${requestScope.role=='admin' }">
			<form action="admin_login" method="post" name="afrm">
				<table width="263" border="0" cellspacing="0" cellpadding="4"
					   align="center">
					<tr>
						<td width="74">
							登录名：
						</td>
						<td width="189">
							<input type="text" name="loginName" style="width:150;" />
						</td>
					</tr>
					<tr>
						<td>
							密 &nbsp;&nbsp;码：
						</td>
						<td>
							<input type="password" name="loginPwd" style="width:150;" />
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
						<td>
							<input type="submit" name="login" value="登 录" />
						</td>
					</tr>

					<tr>
						<td colspan="2">

						</td>
					</tr>

				</table>
			</form>
		</c:if>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<br>
		<hr width=100%>
		<br>
		<br>
		<br>
	</td>
</tr>
</table>
</body>
</html>
