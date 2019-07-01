<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Organization Name</title>
</head>
<body
	onload="organizationSuccessMessage('${param.organizationSuccess}','${param.dummy}');">
	<form method="post" action="CommonServlet" name="frm">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h3>
				<u>Modify Organization Name</u>
			</h3>
		</center>
		<table cellpadding="5" style="margin-top: 80px" align="center">
			<tr>
				<td>Choose the Organization name</td>
				<c:set var="organiName" value="${param.organizationName }"></c:set>
				<td><select style="width: 200px"
					name="selectedOrganizationName" id="selectedOrganizationName"
					onchange="selectOrganization()"
					onclick="document.getElementById('modifyOrganizationSuccess').innerHTML=''">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name">
							<c:choose>
								<c:when test="${name==organiName}">
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
				<td>Enter the new for the Organization</td>
				<td><c:choose>
						<c:when test="${param.duplicateOrganization!=null}">
							<input type="text" size="20" maxlength="15"
								name="modifiedOrganizationName" id="userName"
								value="${param.newOrganizationName}"
								onkeyup="return checkOrganizationName()"
								onclick="removeVariableFromURL('duplicateOrganization');">
							<br>
							<font color="red"><c:out
									value="${param.duplicateOrganization}"></c:out> </font>
						</c:when>
						<c:otherwise>
							<input type="text" size="20" maxlength="15"
								name="modifiedOrganizationName" id="userName"
								value="${param.newOrganizationName}"
								onkeyup="return checkOrganizationName()">

						</c:otherwise>
					</c:choose> <br> <label id="userNameError"></label></td>
			</tr>
			<tr>
				<td>Enter The Description(optional)</td>
				<td><input type="text" size="20" maxlength="100" id=description
					name="description"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="ModifyOrganization"
					name="submit" id="MediumButton" onclick="return modifyOrgCheck()">
					<br> <br> <label id="modifyOrganizationSuccess"></label>
				</td>

			</tr>
		</table>
	</form>
</body>
</html>