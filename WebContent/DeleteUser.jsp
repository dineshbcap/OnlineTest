<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="com.aspiresys.onlinetest.model.beanclass.CommonBeanClass"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Admin/User Details</title>
</head>
<body
	onload="setAdminOrUser('${param.user}','${param.type}','${param.orgId}');setaddUserSuccessMessage('${param.addUserSuccess}','${param.dummy}');setUserNameFor('${param.userName}');">
	<jsp:include page="Logout.jsp"></jsp:include>
	<jsp:include page="AdminView.html"></jsp:include>
	<form method="post" action="CommonServlet" name="frm">
		<center>
			<center>
				<h3>
					<u>Delete User</u>
				</h3>
			</center>
			<table cellspacing="10px">
				<tr>
					<td align="right">Choose the type of User</td>
					<td><select style="width: 200px" name="typeOfUser"
						id="typeOfUser" onchange="adminOrUser()">
							<c:choose>
								<c:when test="${param.userName=='user'}">
									<option value="-select-">-select-</option>
									<option value="admin">admin</option>
									<option selected="selected" value="user">user</option>
								</c:when>
								<c:when test="${param.userName=='admin'}">
									<option value="-select-">-select-</option>
									<option selected="selected" value="admin">admin</option>
									<option value="user">user</option>
								</c:when>
								<c:otherwise>
									<option value="-select-">-select-</option>
									<option value="admin">admin</option>
									<option value="user">user</option>
								</c:otherwise>
							</c:choose>
					</select>
						<div id="userTypeErr"></div> <input type="hidden" name="userType"
						id="userType">
					</td>

				</tr>

				<tr>
					<td align="right">Select Name</td>
					<td><select style="width: 200px" name="deleteName"
						id="deleteName">

							<option value="-name-">-name-</option>
							<c:forEach items="${sessionScope.listOfUserName}" var="name">
								<option value="${name}">${name}</option>
							</c:forEach>

					</select>
						<div id="nameErr"></div>
					</td>
				</tr>

				<tr>
					<td></td>

					<td><input type="submit" name="submit" value="DeleteUser"
						id="NormalButton" onclick="return confirmationUserDelete()">
						<br> <br> <font color="green">
							${param.deleteUserSuccess}</font>
					</td>

				</tr>
			</table>

			<c:remove var="listOfUserName" scope="session" />
		</center>
	</form>
</body>
</html>