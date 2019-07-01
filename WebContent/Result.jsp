<%@page import="com.aspiresys.onlinetest.model.dao.StoreResult"%>
<%@page import="java.util.ArrayList"%>
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
<title>Final Result</title>
</head>
<body onmousedown="disableRightClick(event)"
	onkeydown="disableRefresh(event)">
	<form method="post" action="CommonServlet" name="frm">

		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>

		<center>
			<center>
				<h3>
					<u>Final Result</u>
				</h3>
			</center>
			<table cellpadding="5" border="3" width="80%">
				<tr>
					<th>UserName</th>
					<th>EMail-Id</th>
					<th>Organization Name</th>
					<th>Subject Name</th>
					<th>Mark</th>
				</tr>

				<%
					ArrayList<StoreResult> fullList = (ArrayList<StoreResult>) session
							.getAttribute("FinalReport");
					int initialNumber = (Integer) (session
							.getAttribute("initialNumber"));
					int lastNumber = (Integer) (session.getAttribute("lastNumber"));
					for (int i = initialNumber; i < lastNumber; i++) {
				%>
				<tr>
					<td><%=fullList.get(i).getName()%></td>
					<td><%=fullList.get(i).getMailId()%></td>
					<td><%=fullList.get(i).getOrganizationName()%></td>
					<td><%=fullList.get(i).getSubjectName()%></td>
					<td><%=fullList.get(i).getMark()%></td>
				</tr>
				<%
					}
				%>



			</table>
			<c:if test="${numberOfResults==0}">
				<center>
					<h3 style="color: red">Sorry! There Is No Result Found For
						Your Search</h3>
				</center>
			</c:if>
			<br> <br>
			<table>
				<tr>
					<c:set var="numberOfResults" value="${numberOfResults}"></c:set>


					<c:if test="${numberOfResults>10}">

						<c:if test="${param.initialNumber!=0}">

							<td>

								<button type="button"
									style="background-color: #FF99FF; width: 100px; height: 35px;"
									onclick="resultSlider(${param.initialNumber},${param.lastNumber},${numberOfResults},'previous','getResult')">Previous
									Slot</button>
							</td>
						</c:if>

					</c:if>
					<td><input type="button" id="MediumButton" name="Print"
						value="Print" onclick="window.print()">
					</td>
					<c:if test="${numberOfResults>10}">

						<c:if test="${param.lastNumber!=numberOfResults}">

							<td  class="alignRight"> 
								<button type="button"
									style="background-color: #FF99FF; width: 100px; height: 35px;"
									onclick="resultSlider(${param.initialNumber},${param.lastNumber},${numberOfResults},'next','getResult')">Next
									Slot</button>
							</td>
						</c:if>

					</c:if>
				</tr>
			</table>
		</center>
	</form>

</body>
</html>