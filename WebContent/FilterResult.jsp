<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Result Filter</title>
</head>
<body
	onload="changeHashOnLoad();setSubjectName('${param.organizationName}')">
	<form name="frm" method="post" action="CommonServlet"
		onsubmit="return checkMark()">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>

		<center>
			<center>
				<h3>
					<u>Result Filter</u>
				</h3>
			</center>
			<table cellspacing="20">
				<tr>
					<td align="right" valign="top">Organization name</td>
					<td><select style="width: 200px"
						name="selectedOrganizationName1" id="selectedOrganizationName1"
						onchange="getListOfSubject('FilterResult')">
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
					<td align="right" valign="top">Subject name</td>
					<td><select style="width: 200px" name="SubjectName"
						id="SubjectName">
							<option selected="selected" value="-select-">-select-</option>
							<c:forEach items="${sessionScope.listOfSubject}" var="name1">
								<option value="${name1}">${name1}</option>

							</c:forEach>
					</select>
						<div id="subjectError"></div></td>

				</tr>
				<tr>
					<td align="right" valign="top">From Date</td>
					<td><select style="width: 80px" name="FromDate" id="FromDate">
							<option selected="selected" value="-DD-">-DD-</option>
							<c:forEach var="j" begin="01" end="31" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select> <select style="width: 80px" name="FromMonth" id="FromMonth">
							<option selected="selected" value="-MM-">-MM-</option>
							<c:forEach var="j" begin="01" end="12" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select> <select style="width: 80px" name="FromYear" id="FromYear">
							<option selected="selected" value="-YYYY-">-YYYY-</option>
							<c:forEach var="j" begin="2012" end="2030" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right" valign="top">To Date</td>
					<td><select style="width: 80px" name="ToDate" id="ToDate">
							<option selected="selected" value="-DD-">-DD-</option>
							<c:forEach var="j" begin="01" end="31" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select> <select style="width: 80px" name="ToMonth" id="ToMonth">
							<option selected="selected" value="-MM-">-MM-</option>
							<c:forEach var="j" begin="01" end="12" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select> <select style="width: 80px" name="ToYear" id="ToYear">
							<option selected="selected" value="-YYYY-">-YYYY-</option>
							<c:forEach var="j" begin="2012" end="2030" step="1">

								<option value="${j}">${j}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right" valign="top">Mark Above</td>
					<td><input type="text" id="MarkRange" size="20" maxlength="3"
						name="MarkRange" onkeyup="checkMark()"> <br> <label
						id="markRangeError"></label>
					</td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" value="Generate Report" name="submit"
						id="MediumButton">
					</td>
				</tr>
			</table>
			<h3 style="color: red">If You Not Selected Any Dropdown Values,
				All Results Will Be Fetched!!</h3>
		</center>
	</form>


</body>
</html>