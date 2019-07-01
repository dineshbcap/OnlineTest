<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Questions In The Subject</title>
</head>
<body
	onload="subjectNameAndAnswer('${param.subjectName}','${param.organizationName}','${param.check1}','${param.check2}','${param.check3}','${param.check4}','${param.beginNumber}','${param.endNumber}');test();subjectNameToSet('${param.subjectName}','${param.questionSuccess}')">

	<form method="post" action="CommonServlet" name="frm"
		onsubmit="return checkQuestion()">
		<jsp:include page="Logout.jsp"></jsp:include>
		<jsp:include page="AdminView.html"></jsp:include>

		<table style="margin-left: 150px">
			<tr>
				<td align="right" valign="top">Organization name</td>
				<td valign="top"><select style="width: 200px"
					name="selectedOrganizationName" id="selectedOrganizationName"
					onchange="getListOfSubject('AddQuestion')">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select>
					<div id="orgMsg"></div></td>
				<td align="right" valign="top">Choose the subject name</td>
				<td valign="top"><select style="width: 200px"
					name="selectedSubjectName1" id="selectedSubjectName1"
					onchange="getNumberOfQuestion('AddQuestion')">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfSubject}" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select>
					<div id="subjectError" align="left"></div></td>
				<td><input type="hidden" name="selectedSubjectNameInAdmin1"
					value="">
				</td>

			</tr>
		</table>
		<center>
			<h4>
				<u>Add Questions In The Subject</u>
			</h4>
		</center>

		<table style="margin-left: 150px">

			<tr>
				<td></td>
				<td align="center" style="font-size: x-large;"><c:out
						value="${param.questionNumber}"></c:out></td>

				<td style="height: 50px"><textarea rows="3" cols="85"
						name="adminQuestion" id="adminQuestion"
						onclick="document.getElementById('addQuestionSuccess').innerHTML=''"></textarea>
				</td>

			</tr>
			<tr>
				<td></td>
				<td align="center">A )</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						name="option1"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">B )</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						name="option2"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">C )</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						name="option3"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">D )</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						name="option4"></textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="right">Answer(s) :</td>
				<td style="height: 50px"><input type="radio" name="rb" id="cb0"
					value="rb0"> A <input type="radio" name="rb" id="cb1"
					value="rb1"> B <input type="radio" name="rb" id="cb2"
					value="rb2"> C <input type="radio" name="rb" id="cb3"
					value="rb3"> D</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td style="height: 50px"><input type="submit" name="submit"
					value="AddQuestion" id="MediumButton" onclick="addQuestionVerify()">
					<br> <br> <label id="addQuestionSuccess"></label>
				</td>

			</tr>
		</table>
	</form>
</body>
</html>