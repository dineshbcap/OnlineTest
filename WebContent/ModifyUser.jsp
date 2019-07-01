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
<title>Modify User Details</title>
</head>
<body
	onload="hideAdminUserDetails();changeHashOnLoad();setAdminOrUser('${param.user}','${param.type}','${param.orgId}');setaddUserSuccessMessage('${param.addUserSuccess}','${param.dummy}');setUserNameFor('${param.userName}');">
	<jsp:include page="Logout.jsp"></jsp:include>
	<jsp:include page="AdminView.html"></jsp:include>
	<form method="post" action="CommonServlet" name="frm"
		onsubmit="return validateUserDetails()" autocomplete="off">
		<center>
			<center>
				<h3>
					<u>Modify User Details</u>
				</h3>
			</center>
			<table cellspacing="10px">
				<tr>
					<td align="right">User Name</td>
					<td><select style="width: 200px" name="selectedName" onchange="getDetailOfAdminOrUser('user')"
						id="selectedName">

							<option selected="selected" value="-select-">-select-</option>
							<c:forEach items="${sessionScope.listOfUser}" var="name">
								<c:choose>
									<c:when test="${param.name==name}">

										<option selected="selected" value="${name}">${name}</option>
									</c:when>
									<c:otherwise>
										<option value="${name}">${name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
					</select> <br> <label id="namError"></label></td>

				</tr>
				<tr>
					<td align="right">First Name</td>
					<td><c:choose>
							<c:when test="${param.firstName!=null}">
								<input type="text" name="userName" id="userName" size="20"
									maxlength="15" onkeyup="return checkUserName()"
									value="${param.firstName}">
							</c:when>
							<c:otherwise>
								<input type="text" name="userName" id="userName"
									value="${firstNamee}" size="20" maxlength="15"
									onkeyup="return checkUserName()">
							</c:otherwise>

						</c:choose> <br> <label id="userNameError"></label>
					</td>
				</tr>

				<tr>
					<td align="right">Last Name</td>
					<td><c:choose>
							<c:when test="${param.lastName!=null}">
								<input type="text" size="20" maxlength="20" name="lastName"
									id="lastName" onkeyup="return checkLastName()"
									value="${param.lastName }">
							</c:when>
							<c:otherwise>
								<input type="text" size="20" maxlength="20" name="lastName"
									value="${lastName }" id="lastName"
									onkeyup="return checkLastName()">
							</c:otherwise>

						</c:choose> <br> <label id="lastNameError"></label>
					</td>
				</tr>
				<tr>
					<td align="right">Email_Id</td>
					<td><c:choose>
							<c:when test="${param.emailId!=null}">
								<input type="text" name="emailId" id="emailId"
									value="${param.emailId}" size="20" maxlength="35"
									onclick="removeVariableFromURL('duplicateMailId')"
									onkeyup="return checkEmailId()">
							</c:when>
							<c:otherwise>
								<input type="text" name="emailId" id="emailId"
									value="${emailId}" size="20" maxlength="35"
									onkeyup="return checkEmailId()">
							</c:otherwise>

						</c:choose> <br> <label id="mailIdError"></label> <font color="red"><c:out
								value="${param.duplicateMailId}"></c:out> </font></td>
				</tr>
				<tr>
					<td align="right">Mobile Number</td>
					<td><c:choose>
							<c:when test="${param.mobileNumber!=null}">
								<input type="text" name="mobileNumber" id="mobileNumber"
									value="${param.mobileNumber}" size="20" maxlength="10"
									onkeyup="return checkMobileNumber()"
									onclick="removeVariableFromURL('duplicateMobNum')">
							</c:when>
							<c:otherwise>
								<input type="text" name="mobileNumber" id="mobileNumber"
									value="${mobileNumber}" size="20" maxlength="10"
									onkeyup="return checkMobileNumber()">
							</c:otherwise>
						</c:choose> <br> <label id="mobileNumberError"></label> <font
						color="red"><c:out value="${param.duplicateMobNum}"></c:out>
					</font></td>
				</tr>

				<tr>
					<td align="right">Password</td>
					<td><input type="password" name="passWord" id="passWord"
						value="" size="20" maxlength="10" onkeyup="checkPassWord()">
						<br> <label id="passWordError"></label></td>
				</tr>
				<tr>
					<td align="right">Re-enter Password</td>
					<td><input type="password" name="rePassWord" id="rePassWord"
						value="" size="20" maxlength="10" onkeyup="checkRePassWord()">
						<br> <label id="rePassWordError"></label></td>
				</tr>
				<tr>
					<td align="right" valign="top">Organization name</td>
					<td><select style="width: 200px"
						name="selectedOrganizationName" id="selectedOrganizationName"
						onclick="checkOrganizationName1()">
							<option value="-select-">-select-</option>
							<c:forEach items="${sessionScope.listOfOrganization}" var="name">
								<c:choose>
									<c:when test="${param.organizationName==name}">

										<option selected="selected" value="${name}">${name}</option>
									</c:when>
									<c:otherwise>
										<option value="${name}">${name}</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
					</select>
						<div id="orgMsg"></div></td>

				</tr>
				<tr>
					<td align="right">Is Admin</td>
					<td><input type="checkbox" name="isAdmin"
						onclick="hideSelect()">
				</tr>
				<tr></tr>
				<tr>
					<td></td>
					<td><input type="submit" name="submit" value="ModifyUser" 
						id="NormalButton"> <br> <br> <font color="green">
							${param.modifyUserSuccess}</font></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>