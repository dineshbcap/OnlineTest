<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New User</title>
</head>
<body id="displayImage" onload="changeHashOnLoad()">
	<form method="post" action="CommonServlet"
		onsubmit="return validateUserDetails1()" autocomplete="off">

		<center>
			<fieldset class="AlignFieldset">
				<legend>User Details</legend>

				<table>
					<tr>
						<td align="right" valign="top">First Name</td>
						<td><input type="text" id="selectedName" size="20"
							maxlength="20" name="selectedName" onkeyup="return checkName()"
							value="${param.name }"> <br> <label id="namError"></label>

						</td>
					</tr>
					<tr>
						<td align="right">Last Name</td>
						<td><input type="text" size="20" maxlength="20"
							name="lastName" value="${param.lastName }" id="lastName"
							onkeyup="return checkLastName()"> <br> <label
							id="lastNameError"></label></td>
					</tr>
					<tr>
						<td align="right" valign="top">Email_Id</td>
						<td><input type="text" id="emailId" size="20" maxlength="35"
							name="emailId" onblur="return checkEmailId()"
							value="${param.mailId }"
							onclick="removeVariableFromURL('duplicateMailId')"> <br>
							<label id="mailIdError"></label> <font color="red"><c:out
									value="${param.duplicateMailId}"></c:out>
						</font></td>
					</tr>
					<tr>
						<td align="right" valign="top">Mobile Number</td>
						<td><input type="text" id="mobileNumber" size="20"
							maxlength="10" name="mobileNumber"
							onkeyup="return checkMobileNumber()"
							value="${param.mobileNumber }"
							onclick="removeVariableFromURL('duplicateMobNum')"> <br>
							<label id="mobileNumberError"></label> <font color="red"><c:out
									value="${param.duplicateMobNum}"></c:out>
						</font></td>
					</tr>
					<tr>
						<td align="right" valign="top">User Name</td>
						<td><input type="text" id="userName" size="20" maxlength="15"
							name="userName" onkeyup="return checkUserName1()"
							value="${param.userName }"
							onclick="removeVariableFromURL('duplicateUserName')"> <br>
							<label id="userNameError"></label> <font color="red"><c:out
									value="${param.duplicateUserName}"></c:out>
						</font></td>
					</tr>
					<tr>
						<td align="right" valign="top">Password</td>
						<td><input type="password" id="passWord" size="20"
							maxlength="10" name="passWord" onkeyup="checkPassWord()">
							<br> <label id="passWordError"></label>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">Re-enter Password</td>
						<td><input type="password" id="rePassWord" size="20"
							maxlength="10" name="rePassWord" onblur="checkRePassWord()">
							<br> <label id="rePassWordError"></label>
						</td>
					</tr>
					<tr>
						<td align="right" valign="top">Organization name</td>
						<td><select style="width: 200px"
							name="selectedOrganizationName" id="selectedOrganizationName"
							onclick="checkOrganizationName1()">
								<option selected="selected">-select-</option>
								<c:forEach items="${sessionScope.listOfOrganization}" var="name">
									<option>${name}</option>

								</c:forEach>
						</select>
							<div id="orgMsg"></div></td>

					</tr>

					<tr>
						<td><input type="hidden" name="isAdmin" id="isAdmin"
							onclick="hideSelect()" value="0">
						</td>
					</tr>
					<tr>
						<td align="right"><a href="LoginPage.jsp"><input
								type="button" value="LoginPage" id="NormalButton"
								onclick="return confirmDataLoss()"> </a>
						</td>
						<td><input type="submit" name="submit" value="userSubmit"
							id="NormalButton">
						</td>
					</tr>
				</table>

			</fieldset>
		</center>
	</form>
</body>
</html>