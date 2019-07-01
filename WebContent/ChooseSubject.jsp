<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<style type="text/css">
</style>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Choose Subject</title>
</head>
<body onmousedown="disableRightClick(event)"
	onkeydown="disableRefresh(event)"
	onload="changeHashOnLoad();userAnswered('${param.userAnswered}','${param.subjectName}');userMark('${param.userMark}','${param.test}')">
	<h4 style="text-align: right;">
		Welcome, <b style="text-transform: uppercase; text-decoration: blink;">${firstName}
			!</b> &nbsp;&nbsp;<input type="button" value="Logout" onclick="logout()">
	</h4>
	<form method="get" action="CommonServlet" name="frm">

		<center>
			<fieldset class="AlignFieldset">
				<legend>Choose Subject</legend>
				<table>

					<tr>
						<td><label id="userMark"></label></td>
					</tr>
				</table>
				<table>

					<tr>
						<td align="left">Select the subject</td>
						<td><select style="width: 200px" name="selectedSubjectName1"
							id="selectedSubjectName1">
								<option selected="selected" value="-select-">-select-</option>
								<c:forEach items="${sessionScope.listOfSubjectForUser}"
									var="name">
									<option value="${name}">${name}</option>

								</c:forEach>
						</select>
						</td>
					</tr>


					<tr></tr>
					<tr>
						<td></td>
						<td><input type="button" value="submit" id="NormalButton"
							onclick="return getNumberOfQuestion('writeTest')"></td>
					</tr>
				</table>
				<table>

					<tr>
						<td><label id="answeredError"></label></td>
					</tr>
				</table>
			</fieldset>
		</center>
	</form>

</body>
</html>