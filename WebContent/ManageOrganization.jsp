<%@page
	import="com.aspiresys.onlinetest.model.dao.StoreOrganizationDetails"%>
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
<title>Manage Organization</title>
</head>
<body>

	<form>

		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h3>
				<u>Complete Organization List</u>
			</h3>
		</center>
		<table id="organizationTable" border="1" align="center" width="60%">
			<tr>
				<th>Organization Name</th>
				<th>Description</th>
			</tr>

			<%
				ArrayList<StoreOrganizationDetails> fullList = (ArrayList<StoreOrganizationDetails>) session
						.getAttribute("completeOrganizationDetails");
				int initialNumber = (Integer) (session
						.getAttribute("initialNumberOrganization"));
				int lastNumber = (Integer) (session
						.getAttribute("lastNumberOrganization"));
				for (int i = initialNumber; i < lastNumber; i++) {
			%>
			<tr>
				<td><%=fullList.get(i).getName()%></td>
				<td><%=fullList.get(i).getDescription()%></td>
			</tr>
			<%
				}
			%>


		</table>

		<br> <br>
		<table width="90%">
			<tr>
				<c:set var="numberOfOrganization" value="${numberOfOrganization}"></c:set>


				<c:if test="${numberOfOrganization>10}">

					<c:if test="${initialNumberOrganization!=0}">

						<td align="left">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberOrganization},${lastNumberOrganization},${numberOfOrganization},'previous','organizationSlider')">Previous
								Slot</button></td>
					</c:if>

				</c:if>

				<c:if test="${numberOfOrganization>10}">

					<c:if test="${lastNumberOrganization!=numberOfOrganization}">

						<td align="right" class="alignRight">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberOrganization},${lastNumberOrganization},${numberOfOrganization},'next','organizationSlider')">Next
								Slot</button></td>
					</c:if>

				</c:if>
			</tr>
		</table>
	</form>

</body>
</html>