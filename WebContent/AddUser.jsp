<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Admin/User Deatils</title>
</head>
<body
	onload="setaddUserSuccessMessage('${param.addUserSuccess}','${param.dummy}');">
	<jsp:include page="Logout.jsp"></jsp:include>
	<jsp:include page="AdminView.html"></jsp:include>
	<form method="post" action="CommonServlet" name="frm" style="overflow: auto;"
		onsubmit="return validateUserDetails()" autocomplete="off">
		<center>
			<center>
				<h3>
					<u>Add Admin/User Details</u>
				</h3>
			</center>
			<table cellspacing="19px">
				<tr>
					<td align="right">First Name</td>
					<td><input type="text" size="20" maxlength="20"
						name="selectedName" value="${param.name}" id="selectedName"
						onkeyup="return checkName()"> <br> <label
						id="namError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Last Name</td>
					<td><input type="text" size="20" maxlength="20"
						name="lastName" value="${param.lastName }" id="lastName"
						onkeyup="return checkLastName()"> <br> <label
						id="lastNameError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Email_Id</td>
					<td><c:choose>
							<c:when test="${param.duplicateMailId!=null}">
								<input type="text" id="emailId" size="20" maxlength="35"
									name="emailId" onkeyup="return checkEmailId()"
									value="${param.mailId }"
									onclick="removeVariableFromURL('duplicateMailId')">
								<br>
								<font color="red"><c:out value="${param.duplicateMailId}"></c:out>
								</font>
							</c:when>
							<c:otherwise>
								<input type="text" id="emailId" size="20" maxlength="35"
									name="emailId" onkeyup="return checkEmailId()"
									value="${param.mailId }">
							</c:otherwise>
						</c:choose><br> <label id="mailIdError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Mobile Number</td>
					<td><c:choose>
							<c:when test="${param.duplicateMobNum!=null}">
								<input type="text" id="mobileNumber" size="20" maxlength="10"
									name="mobileNumber" onkeyup="return checkMobileNumber()"
									value="${param.mobileNumber }"
									onclick="removeVariableFromURL('duplicateMobNum')">
								<br>
								<font color="red"><c:out value="${param.duplicateMobNum}"></c:out>
								</font>
							</c:when>
							<c:otherwise>
								<input type="text" id="mobileNumber" size="20" maxlength="10"
									name="mobileNumber" onkeyup="return checkMobileNumber()"
									value="${param.mobileNumber }">
							</c:otherwise>
						</c:choose> <br> <label id="mobileNumberError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">User Name</td>
					<td><c:choose>
							<c:when test="${param.duplicateUserName!=null}">
								<input type="text" id="userName" size="20" maxlength="15"
									name="userName" onkeyup="return checkUserName1()"
									value="${param.userName }"
									onclick="removeVariableFromURL('duplicateUserName')">
								<br>
								<font color="red"><c:out
										value="${param.duplicateUserName}"></c:out> </font>
							</c:when>
							<c:otherwise>
								<input type="text" id="userName" size="20" maxlength="15"
									name="userName" onkeyup="return checkUserName1()"
									value="${param.userName }">
							</c:otherwise>
						</c:choose> <br> <label id="userNameError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Password</td>
					<td><input type="password" id="passWord" size="20"
						maxlength="10" name="passWord" onkeyup="checkPassWord()">
						<br> <label id="passWordError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Re-enter Password</td>
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
							<option>-select-</option>
							<c:forEach items="${sessionScope.listOfOrganization}" var="name">
								<c:choose>
									<c:when test="${param.organizationName==name}">

										<option selected="selected">${name}</option>
									</c:when>
									<c:otherwise>
										<option>${name}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
					</select>
						<div id="orgMsg"></div>
					</td>

				</tr>
				<tr>
					<td align="right">Is Admin</td>
					<td><input type="checkbox" name="isAdmin" id="isAdmin"
						onclick="hideSelect()">
				</tr>
				<tr></tr>
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="AddUser"
						id="NormalButton"> <br> <br> <font color="green">
							${param.addUserSuccess}</font>
					</td>
				</tr>

			</table>


		</center>
	</form>
</body>
</html>