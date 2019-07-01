<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Organization</title>
</head>
<body
	onload="organizationSuccessMessage('${param.organizationSuccess}','${param.dummy}');">
	<form method="post" action="CommonServlet" name="frm"
		onsubmit="return checkOrganizationName()">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h3>
				<u>Add New Organization</u>
			</h3>
		</center>
		<table cellpadding="5" style="margin-top: 80px" align="center">
			<tr>
				<td>Enter the Organization name</td>
				<td><c:choose>
						<c:when test="${param.duplicateOrganization!=null}">
							<input type="text" name="addNewOrganization" size="20"
								value="${param.organizationName }" maxlength="15" id="userName"
								onkeyup="return checkOrganizationName()" value="${param.name }"
								onclick="removeVariableFromURL('duplicateOrganization');">
							<br>
							<font color="red"><c:out
									value="${param.duplicateOrganization}"></c:out> </font>
						</c:when>
						<c:otherwise>
							<input type="text" name="addNewOrganization" size="20"
								value="${param.organizationName }" maxlength="15" id="userName"
								onkeyup="return checkOrganizationName()" value="${param.name }"
								onclick="document.getElementById('addOrganizationSuccess').innerHTML='';">
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
				<td><input type="submit" value="AddOrganization" name="submit"
					id="MediumButton"> <br> <br> <label
					id="addOrganizationSuccess"></label></td>

			</tr>
		</table>
	</form>
</body>
</html>