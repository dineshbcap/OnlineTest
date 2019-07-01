<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body onload="changeHashOnLoad()" 
	onmousedown="disableRightClick(event)" onkeydown="disableRefresh(event)" >
	<form method="post" action="CommonServlet" onsubmit="" autocomplete="off">
		<div id="display" style="width: 20%; height: 20%"></div>
		<center>
			<h1 style="color: green">
				<u>Online Test</u>
			</h1>
			<fieldset class="AlignFieldset">
				<legend align="left">LOGIN PAGE</legend>
				<table>
					<tr>
						<td align="right">Username</td>
						<td><input type="text" name="userName" id="userName"
							value="${param.userName}" size="20" maxlength="15">
						</td>
					</tr>
					<tr>
						<td align="right">Password</td>
						<td><input type="password" name="passWord" id="passWord"
							size="20" maxlength="10">
						</td>
					</tr>
					<tr></tr>

					<tr>
						<!-- <td><input type="button" value="newUser" id="NormalButton"
							onclick="newUser()">
						</td> -->
						<td></td>
						<td><input type="submit" name="submit" value="signin"
							id="NormalButtonw">
						</td>
					</tr>
				</table>
				<font color="red">${msg}</font> <font color="green">${param.userAdded}</font>
			</fieldset>
		</center>

	</form>
</body>
<c:remove var="msg" scope="session" />
</html>