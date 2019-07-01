<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Subject</title>
</head>
<body
	onload="setSubjectAndOrg('${param.organizationName}','${param.subjectName}');setSubjectName1('${param.organizationName}');subjectSuccessMessage('${param.subjectSuccess}');">
	<form method="post" action="CommonServlet" name="frm">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h4>
				<u>Add New Subject</u>
			</h4>
		</center>
		<table cellpadding="5" style="margin-top: 80px" align="center">
			<tr>
				<td>Enter the subject name</td>
				<td><c:choose>
						<c:when test="${param.duplicateSubject!=null}">
							<input type="text" name="addNewSubject" id="userName"
								value="${param.subjectName }" size="20" maxlength="15"
								onkeyup="return checkSubjectName()"
								onfocus="removeAddSubError()"
								onclick="removeVariableFromURL('duplicateSubject');">
							<br>
							<font color="red"><c:out value="${param.duplicateSubject}"></c:out>
							</font>
						</c:when>
						<c:otherwise>
							<input type="text" name="addNewSubject" id="userName"
								value="${param.subjectName }" size="20" maxlength="15"
								onkeyup="return checkSubjectName()"
								onclick="document.getElementById('addSubjectSuccess').innerHTML='';">

						</c:otherwise>
					</c:choose> <br> <label id="userNameError"></label></td>
			</tr>
			<tr>
				<td>choose the organization name</td>
				<td><select style="width: 200px"
					name="selectedOrganizationName" id="selectedOrganizationName"
					onchange="selectOrganization()">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name"
							varStatus="i">
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
					<div id="orgMsg"></div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="AddSubject" name="submit"
					id="MediumButton" onclick="return addSubject()"> <br>
					<br> <label
					id="addSubjectSuccess"><font color="green">${param.subjectSuccess}</font></label></td>
			</tr>
		</table>
	</form>
</body>
</html>