<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/Style.css">
<script type="text/javascript" src="JavaScript/DisableBackButton.js"></script>
<script type="text/javascript" src="JavaScript/CheckValues.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error Page</title>
</head>
<body onload="changeHashOnLoad()">
	<center style="color: red; margin-top: 10%;">
		<img alt="" src="Images/SorryImage.jpeg">
		<h2>Sorry! For The Inconvenience. There Is Some Problem Occured. This May Because, </h2>
		<ul type="square" style="text-align: left;">
			<li>Your Session May Expire!</li>
			<li>Or May Be Technical Problem From Our Side!</li>
		</ul>
	</center>
		Click Here For Go To<a href="LoginPage.jsp"> Login Page.</a>
	<div
		style="border: 1px solid gray; background-color: #ccccd0;position:fixed; position:absolute;bottom:5px;width: 100%; text-align: center;">©
		2012 Aspire Systems</div>
</body>
</html>