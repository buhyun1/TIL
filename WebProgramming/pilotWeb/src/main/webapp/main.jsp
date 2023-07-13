<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main JSP File</title>
</head>
<body>
	<%@ include file="header.jspf"%><hr>
	<br>
	<em>You are in the main.jsp page.</em>
	<br>
	<br>
	<hr>
	<br>
	<jsp:include page="footer.jsp">
		<jsp:param name="localTime"
			value="<%=new java.util.Date().toLocaleString()%>" />

		<jsp:param name="userName" value="John Doe" />
	</jsp:include>
</body>
</html>
