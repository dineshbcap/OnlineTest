<%@ page import="java.util.ArrayList"%>
<%@ page import="com.aspiresys.onlinetest.model.dao.StoreUsersDetails"%>
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
<title>Admin Add New Subject</title>
</head>
<body>
	<form action="">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>
		<center>
			<h3>
				<u>Complete User List</u>
			</h3>
		</center>
		<table id="organizationTable" border="1" align="center" width="60%">
			<tr>
				<th>Name</th>
				<th>Mail Id</th>
				<th>Mobile Number</th>
				<th>Organization Name</th>
			</tr>

			<%
				ArrayList<StoreUsersDetails> fullList = (ArrayList<StoreUsersDetails>) session
						.getAttribute("completeUserDetails");
				int initialNumber = (Integer) (session
						.getAttribute("initialNumberUser"));
				int lastNumber = (Integer) (session.getAttribute("lastNumberUser"));
				for (int i = initialNumber; i < lastNumber; i++) {
			%>
			<tr>
				<td><%=fullList.get(i).getName()%></td>
				<td><%=fullList.get(i).getMailId()%></td>
				<td><%=fullList.get(i).getMobileNumber()%></td>
				<td><%=fullList.get(i).getOrganizationName()%></td>
			</tr>
			<%
				}
			%>


		</table>

		<br> <br>
		<table width="90%">
			<tr>
				<c:set var="numberOfUser" value="${numberOfUser}"></c:set>


				<c:if test="${numberOfUser>10}">

					<c:if test="${initialNumberUser!=0}">

						<td align="left">

							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberUser},${lastNumberUser},${numberOfUser},'previous','userSlider')">Previous
								Slot</button></td>
					</c:if>

				</c:if>

				<c:if test="${numberOfUser>10}">

					<c:if test="${lastNumberUser!=numberOfUser}">

						<td align="right" class="alignRight">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="resultSlider(${initialNumberUser},${lastNumberUser},${numberOfUser},'next',userSlider')">Next
								Slot</button></td>
					</c:if>

				</c:if>
			</tr>
		</table>

	</form>

</body>
</html>