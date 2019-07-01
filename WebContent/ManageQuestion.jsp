<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Manage Questions</title>


</head>
<body
	onload="changeHashOnLoad();subjectNameAndAnswer('${param.subjectName}','${param.organizationName}','${param.check1}','${param.check2}','${param.check3}','${param.check4}','${param.beginNumber}','${param.endNumber}');test();subjectNameToSet('${param.subjectName}','${param.questionSuccess}')">

	<form method="post" action="CommonServlet" name="frm"
		onsubmit="return checkQuestion()">
		<jsp:include page="Logout.jsp"></jsp:include>
		<fieldset
			style="margin-top: 30PX;">
			<legend>Manage Question</legend>
			<table>
				<tr>
					<td><a
						href="ManageQuestion.jsp?page=AddQuestions"><input
							type="button" value="Add Questions" id="LargeButton">
					</a>
					</td>
					<td><a
						href="CommonServlet?commonName=ModifyQuestions"><input
							type="button" value="Modify Questions" id="LargeButton">
					</a>
					</td>
					<td><a
						href="CommonServlet?commonName=DeleteQuestions"><input
							type="button" value="delete Questions" id="LargeButton">
					</a>
					</td>
					<td><a
						href="AdminPage.jsp"><input
							type="button" value="Main Page" id="LargeButton">
					</a>
					</td>
				</tr>
			</table>
			<hr>
			<c:set var="pageName" value="${param.page}" />
			<c:choose>
				<c:when test="${pageName eq 'AddQuestions' }">


					<jsp:include page="AddQuestions.jsp"></jsp:include>

				</c:when>
				<c:when test="${pageName eq 'ModifyQuestions' }">


					<jsp:include page="ModifyQuestions.jsp"></jsp:include>



				</c:when>
				<c:when test="${pageName eq 'DeleteQuestions' }">



					<jsp:include page="DeleteQuestions.jsp"></jsp:include>


				</c:when>
			</c:choose>
		</fieldset>
	</form>

</body>
</html>