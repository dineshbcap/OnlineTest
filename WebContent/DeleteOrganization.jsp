<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete The Organization</title>
</head>
<body
	onload="organizationSuccessMessage('${param.organizationSuccess}','${param.dummy}');">
	<form method="post" action="CommonServlet" name="frm">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h4>
				<u>Delete The Organization</u>
			</h4>
		</center>
		<table cellpadding="5" style="margin-top: 80px" align="center">
			<tr>
				<td>Choose the organization name</td>
				<td><select style="width: 200px"
					name="selectedOrganizationName" id="selectedOrganizationName"
					onchange="selectOrganization()"
					onclick="document.getElementById('deleteOrganizationSuccess').innerHTML=''">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select>
					<div id="orgMsg"></div></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="DeleteOrganization"
					name="submit" id="MediumButton"
					onclick="return confirmationOrgDelete()"> <br> <br>
					<label id="deleteOrganizationSuccess"></label></td>

			</tr>
		</table>
	</form>
</body>
</html>