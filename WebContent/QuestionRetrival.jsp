<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/time.js"></script>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Retrival</title>
</head>
<body onmousedown="disableRightClick(event)"
	onkeydown="disableRefresh(event)"
	onload="changeHashOnLoad();setTiming('${param.min}','${param.sec}');checkBox('${param.check1}','${param.check2}','${param.check3}','${param.check4}','${param.questionNumber }','${param.totQuestion}','${param.beginNumber}','${param.endNumber}')">
	<form method="post" action="CommonServlet" name="frm">
		<jsp:include page="Logout.jsp"></jsp:include>
		<fieldset
			style="width: 1200PX; height: 650PX; margin-top: 50PX; margin-left: 30PX;border-color:black;">
			<legend style="font-size: 20px;color: red">
				Subject Name: ${subjectNameSelected}
			</legend>
			<table>
				<tr>
					<td align="right"><c:out value="${questionPossition}"></c:out>)</td>
					<td></td>
					<td style="height: 50px"><textarea rows="4" cols="90"
							disabled="disabled" id="question" name="question"
							style="font-size: 16px;background-color: white;;color: black;resize: none;">${question}</textarea>
					</td>
					<td id="theTime" valign="top"></td>
					<td><input type="hidden" id="minute" name="minute">
					<td><input type="hidden" id="second" name="second">
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="radio" name="rb" id="cb0"
						value="cb0"></td>
					<td style="height: 50px; width: 400px"><textarea rows="3"
							cols="80" disabled="disabled" id="option1" name="option1"
							style="font-size: 16px;background-color: white;;color: black;resize: none;">${option1}</textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="radio" name="rb" id="cb1"
						value="cb1"></td>
					<td style="height: 50px"><textarea rows="3" cols="80"
							disabled="disabled" id="option2" name="option2"
							style="font-size: 16px;background-color: white;;color: black;resize: none;">${option2}</textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="radio" name="rb" id="cb2"
						value="cb2"></td>
					<td style="height: 50px"><textarea rows="3" cols="80"
							disabled="disabled" id="option3" name="option3"
							style="font-size: 16px;background-color: white;;color: black;resize: none;">${option3}</textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="right"><input type="radio" name="rb" id="cb3"
						value="cb3"></td>
					<td style="height: 50px"><textarea rows="3" cols="80"
							disabled="disabled" id="option4" name="option4"
							style="font-size: 16px;background-color: white;color: black;resize: none;">${option4}</textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td style="height: 50px"><input type="submit" name="submit"
						value="previous" id="NormalButton1"> &nbsp; <input
						type="submit" name="submit" value="next" id="NormalButton2">
						&nbsp; <input type="submit" name="submit" value="Complete Test"
						id="CompleteTest" onclick="return confirmationSubmit()">
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<c:set var="numberOfQues" value="${numberOfQuestions}"></c:set>
					<c:if test="${numberOfQues>10}">
						<c:if test="${param.beginNumber!=1}">
							<td>
								<button type="button"
									style="background-color: #FF99FF; width: 100px; height: 35px;"
									onclick="previousSlot(${param.beginNumber},${param.endNumber},${numberOfQues},'writeTest')">Previous
									Slot</button></td>
						</c:if>
					</c:if>
					<c:if test="${param.beginNumber!=null}">
						<c:if test="${param.beginNumber!=0}">
							<c:forEach var="j" begin="${param.beginNumber}"
								end="${param.endNumber}" step="1">
								<td><input type="submit" name="goQuestion" value="${j}"
									style="height: 25px; width: 45px"></td>
							</c:forEach>
						</c:if>
					</c:if>
					<c:if test="${numberOfQues>10}">
						<c:if test="${param.endNumber!=numberOfQues}">
							<td class="alignRight">
								<button type="button"
									style="background-color: #FF99FF; width: 100px; height: 35px;"
									onclick="nextSlot(${param.beginNumber},${param.endNumber},${numberOfQues},'writeTest')">Next
									Slot</button></td>
						</c:if>
					</c:if>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>