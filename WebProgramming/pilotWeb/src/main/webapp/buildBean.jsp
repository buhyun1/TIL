<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
	<div align="center">
		<form name="buildForm" action="testBean.jsp" align="left">
			<fieldset style="width: 180px">
				<legend>Bean Information</legend>
				<label for="name"><small>Person's name:</small></label><br> <input
					type="text" id="name" name="name" required><br> <label
					for="age"><small>Age:</small></label><br> <input type="number"
					id="age" name="age" min="0" required><br> <label
					for="id"><small>Student ID:</small></label><br> <input
					type="number" id="id" name="id" required><br> <label
					for="major"><small>Major:</small></label><br> <input
					type="text" id="major" name="major" required><br> <label
					for="fullTime"><small>Full Time:</small></label><br> <input
					type="checkbox" id="fullTim" name="fullTime" value="true"><small>true</small><br>
				<br>
				<div align="right">
					<input type="submit" value="build">
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>