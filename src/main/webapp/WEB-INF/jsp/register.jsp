<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>用户注册页面</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function usercheck(){
			if(document.ufrm.loginPwd.value.length==0){
				$("#loginPwdError").text("密码不能为空！");
				$("#loginPwdError").css("color","red");
				return false;
			}else{
				$("#loginPwdError").text("^_^");
				$("#loginPwdError").css("color","green");
				return true;
			}
			if(document.ufrm.utrueName.value.length==0){
				$("#trueNameError").text("真实姓名为空！");
				$("#trueNameError").css("color","yellow");
			}else{
				$("#trueNameError").text("^_^");
				$("#trueNameError").css("color","green");
			}
			if(document.ufrm.utrueName.value.length==0){
				$("#phoneError").text("电话为空！");
				$("#phoneError").css("color","yellow");
			}else{
				$("#phoneError").text("^_^");
				$("#phoneError").css("color","green");
			}
			if(document.ufrm.utrueName.value.length==0){
				$("#emailError").text("真实姓名为空！");
				$("#emailError").css("color","yellow");
			}else{
				$("#emailError").text("^_^");
				$("#emailError").css("color","green");
			}
			if(document.ufrm.utrueName.value.length==0){
				$("#addressError").text("地址为空！");
				$("#addressError").css("color","yellow");
			}else{
				$("#addressError").text("^_^");
				$("#addressError").css("color","green");
			}
		}
		$(document).ready(function(){
			var loginName=$("input[name='loginName']");
			$("input[name='loginName']").on("blur",function(){
				$.ajax({
					type:"post",
					url:"${pageContext.request.contextPath}/checkloginName",
					data:{"loginName":loginName.val()},
					dataType:"text",
					success:function(data){
						if(data=="yes"){
							$("#loginNameError").text("该用户名可以使用！");
							$("#loginNameError").css("color","green");
						}else if(data=="no"){
							$("#loginNameError").text("该用户名已存在！");
							$("#loginNameError").css("color","red");
							loginName.focus();
						}else if(data="kong"){
							$("#loginNameError").text("用户名不能为空！");
							$("#loginNameError").css("color","red");
							loginName.focus();
						}
					}
				})
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

		<div style="background-image:url(images/004.gif)">
			&nbsp;
		</div>
		<form action="${pageContext.request.contextPath}/register" method="post" name="ufrm"
			  onsubmit="return usercheck();">
			<table width="100%" cellspacing="0" cellpadding="3" align="center"
				   style="text-align:center; border:1px #cccccc solid;">
				<tr style="background-color:#CCCCFF;">
					<td colspan="3">
						填写注册信息
					</td>
				</tr>
				<tr>
					<td align="right" style="width:320px;" >
						登录名称：
					</td>
					<td align="left">
						<input type="text" name="loginName" style="width:220px;" />
					</td>
					<td id="loginNameError" align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						登录密码：
					</td>
					<td align="left">
						<input type="password" name="loginPwd" style="width:220px;" />
					</td>
					<td id="loginPwdError" align="left" ></td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						真实姓名：
					</td>
					<td align="left">
						<input type="text" name="trueName" style="width:220px;" />
					</td>
					<td id="trueNameError" align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						电话号码：
					</td>
					<td align="left">
						<input type="text" name="phone" style="width:220px;" />
					</td>
					<td id="phoneError" align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						电子邮件：
					</td>
					<td align="left">
						<input type="text" name="email" style="width:220px;" />
					</td>
					<td id="emailError" align="left"></td>
				</tr>
				<tr>
					<td align="right" style="width:320px;">
						默认地址：
					</td>
					<td align="left">
						<input type="text" name="address" style="width:220px;" />
					</td>
					<td id="addressError" align="left"></td>
				</tr>
				<tr style="background-color:#CCCCFF;">
					<td colspan="3">
						<input name="register" type="submit" id="register" value="注册" />
					</td>
				</tr>
			</table>
		</form>
		</div>
	</td>
</tr>
<tr>
	<td colspan="3" align="center">
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
