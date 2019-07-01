<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Questions In The Subject</title>
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
					onchange="getListOfSubject('ModifyQuestion')">
						<option value="-select-">-select-</option>
						<c:forEach items="${sessionScope.listOfOrganization}" var="name">
							<option value="${name}">${name}</option>

						</c:forEach>
				</select>
					<div id="orgMsg"></div></td>
				<td align="right" valign="top">Choose the subject name</td>
				<td align="left" valign="top"><select style="width: 200px"
					name="selectedSubjectName1" id="selectedSubjectName1"
					onchange="getNumberOfQuestion('ModifyQuestion')">

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
				<u>Modify Questions In The Subject</u>
			</h4>
		</center>
		<table style="margin-left: 150px">
			<tr>
				<td></td>
				<td align="center" style="font-size: x-large;"><c:out
						value="${param.questionNum}"></c:out></td>

				<td style="height: 40px"><textarea rows="3" cols="85"
						id="question" name="question" onfocus="infoToSelect()">${question}</textarea>
				</td>
				<td id="theTime" valign="top"></td>
			</tr>
			<tr>
				<td></td>
				<td align="center">A )</td>
				<td style="height: 30px; width: 400px"><textarea rows="2"
						cols="70" id="option1" name="option1" onfocus="infoToSelect()">${option1}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">B )</td>
				<td style="height: 30px"><textarea rows="2" cols="70"
						id="option2" name="option2" onfocus="infoToSelect()">${option2}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">C )</td>
				<td style="height: 30px"><textarea rows="2" cols="70"
						id="option3" name="option3" onfocus="infoToSelect()">${option3}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="center">D )</td>
				<td style="height: 30px"><textarea rows="2" cols="70"
						id="option4" name="option4" onfocus="infoToSelect()">${option4}</textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td align="right" valign="top">Answer(s) :</td>
				<td style="height: 50px"><input type="radio" name="rb" id="cb0"
					value="rb0"> A <input type="radio" name="rb" id="cb1"
					value="rb1"> B <input type="radio" name="rb" id="cb2"
					value="rb2"> C <input type="radio" name="rb" id="cb3"
					value="rb3"> D &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="submit" name="submit" value="ModifyQuestion"
					id="MediumButton" onclick="return checkQuestion1()"> <label
					id="modifyQuestionSuccess"></label>
				</td>
			</tr>

		</table>
		<table style="margin-left: 150px">

			<tr>
				<c:set var="numberOfQues" value="${numberOfQuestions}"></c:set>

				<c:if test="${param.beginNumber!=null}">
					<c:if test="${numberOfQues>10}">

						<c:if test="${param.beginNumber!=1}">

							<td><button type="button"
									onclick="previousSlot('${param.beginNumber}','${param.endNumber}','${numberOfQues}','modify')"
									style="background-color: #FF99FF; width: 100px; height: 35px;">Previous
									Slot</button>
							</td>
						</c:if>

					</c:if>



					<c:if test="${param.beginNumber!=0}">
						<c:forEach var="j" begin="${param.beginNumber}"
							end="${param.endNumber}" step="1">

							<td><input type="button" id="goToQuestionButton"
								name="goToQuestionButton[${j}]"
								style="height: 25px; width: 45px" value="${j}"
								onclick="call(${j},'modify',${param.beginNumber},${param.endNumber})">
							</td>
						</c:forEach>
					</c:if>



					<c:if test="${numberOfQues>10}">

						<c:if test="${param.endNumber!=numberOfQues}">
							<td  class="alignRight">
								<button type="button"
									onclick="nextSlot('${param.beginNumber}','${param.endNumber}','${numberOfQues}','modify')"
									style="background-color: #FF99FF; width: 100px; height: 35px;">Next
									Slot</button>
							</td>
						</c:if>

					</c:if>

				</c:if>


			</tr>

		</table>
	</form>
</body>
</html>