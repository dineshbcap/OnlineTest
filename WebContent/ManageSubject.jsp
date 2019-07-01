<%@page import="com.aspiresys.onlinetest.model.dao.StoreSubjectDetails"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	errorPage="ErrorDisplayPage.jsp" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Subject</title>
</head>
<body>

	<form>
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h3>
				<u>Complete Subject List</u>
			</h3>
		</center>
		<table id="organizationTable" border="1" align="center" width="60%">
			<tr>
				<th>Subject Name</th>
				<th>Organization Name</th>
			</tr>

			<%
				ArrayList<StoreSubjectDetails> fullList = (ArrayList<StoreSubjectDetails>) session
						.getAttribute("completeSubjectDetails");
				int initialNumber = (Integer) (session
						.getAttribute("initialNumberSubject"));
				int lastNumber = (Integer) (session
						.getAttribute("lastNumberSubject"));
				for (int i = initialNumber; i < lastNumber; i++) {
			%>
			<tr>
				<td><%=fullList.get(i).getSubjectName()%></td>
				<td><%=fullList.get(i).getOrganizationName()%></td>
			</tr>
			<%
				}
			%>


		</table>

		<br> <br>
		<table width="90%">
			<tr>
				<c:set var="numberOfSubject" value="${numberOfSubject}"></c:set>


				<c:if test="${numberOfSubject>10}">

					<c:if test="${initialNumberSubject!=0}">

						<td align="left">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberSubject},${lastNumberSubject},${numberOfSubject},'previous','subjectSlider')">Previous
								Slot</button></td>
					</c:if>

				</c:if>

				<c:if test="${numberOfSubject>10}">

					<c:if test="${lastNumberSubject!=numberOfSubject}">

						<td align="right" class="alignRight">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberSubject},${lastNumberSubject},${numberOfSubject},'next','subjectSlider')">Next
								Slot</button></td>
					</c:if>

				</c:if>
			</tr>
		</table>
	</form>

</body>
</html>