<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Questions In The Subject</title>
<script type="text/javascript" src="CheckValues.js"></script>
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
				<td align="left" valign="top"><select style="width: 200px"
					name="selectedOrganizationName" id="selectedOrganizationName"
					onchange="getListOfSubject('DeleteQuestion')">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select>
					<div id="orgMsg"></div></td>
				<td align="right" valign="top">Choose the subject name</td>
				<td align="left" valign="top"><select style="width: 200px"
					name="selectedSubjectName1" id="selectedSubjectName1"
					onchange="getNumberOfQuestion('DeleteQuestion')">

						<option value="-Subject Name-">-Subject Name-</option>
						<c:forEach items="${sessionScope.listOfSubject }" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select> <input type="hidden" name="selectedSubjectNameInAdmin" value="">
					<div id="subMsg"></div></td>

			</tr>
		</table>
		<center>
			<h4>
				<u>Delete Questions In The Subject</u>
			</h4>
		</center>
		<table style="margin-left: 150px">
			<tr>
				<td></td>
				<td align="center" style="font-size: x-large;"><c:out
						value="${param.questionNum}"></c:out></td>

				<td style="height: 50px"><textarea rows="3" cols="85"
						id="question" name="question" disabled="disabled">${question}</textarea>
				</td>
				<td id="theTime" valign="top"></td>
			</tr>
			<tr>
				<td></td>
				<td align="center">A)</td>
				<td style="height: 50px; width: 400px"><textarea rows="2"
						cols="70" id="option1" name="option1" disabled="disabled">${option1}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">B)</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						id="option2" name="option2" disabled="disabled">${option2}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">C)</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						id="option3" name="option3" disabled="disabled">${option3}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">D)</td>
				<td style="height: 50px"><textarea rows="2" cols="70"
						id="option4" name="option4" disabled="disabled">${option4}</textarea>
				</td>
			</tr>

		</table>

		<table style="margin-left: 150px">

			<tr>
				<c:if test="${param.beginNumber>10}">
					<td></td>
				</c:if>
				<c:if test="${param.beginNumber!=null}">
					<c:if test="${param.beginNumber!=0}">
						<c:forEach var="j" begin="${param.beginNumber}"
							end="${param.endNumber}" step="1">

							<td><input type="checkbox" name="goTo[${j}]" id="goTo[${j}]">
							</td>
						</c:forEach>
					</c:if>
				</c:if>
			</tr>

			<tr>

				<c:set var="numberOfQues" value="${numberOfQuestions}"></c:set>


				<c:if test="${numberOfQues>10}">

					<c:if test="${param.beginNumber!=1}">

						<td>
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="previousSlot(${param.beginNumber},${param.endNumber},${numberOfQues},'delete')">Previous
								Slot</button></td>
					</c:if>

				</c:if>


				<c:if test="${param.beginNumber!=null}">
					<c:if test="${param.beginNumber!=0}">
						<c:forEach var="j" begin="${param.beginNumber}"
							end="${param.endNumber}" step="1">

							<td><input type="button" id="goToQuestionButton"
								name="goToQuestionButton[${j}]"
								style="height: 25px; width: 45px" value="${j}"
								onclick="return call(${j},'delete',${param.beginNumber},${param.endNumber})">
							</td>
						</c:forEach>
					</c:if>
				</c:if>


				<c:if test="${numberOfQues>10}">

					<c:if test="${param.endNumber!=numberOfQues}">

						<td  class="alignRight">
							<button type="button"
								style="background-color: #FF99FF; width: 100px; height: 35px;"
								onclick="nextSlot(${param.beginNumber},${param.endNumber},${numberOfQues},'delete')">
								Next Slot</button></td>
					</c:if>

				</c:if>
			</tr>
		
		</table>
		<input type="hidden" name="beginNum" id="beginNum" value="${param.beginNumber}">
		<input type="hidden" name="endNum" id="endNum" value="${param.endNumber}">
		<center>
			<input type="submit" name="submit" value="DeleteQuestion"
				id="MediumButton"
				onclick="return verifyDelete()">
		</center>

		<label id="deleteQuestionSuccess"></label>
	</form>
</body>
</html>