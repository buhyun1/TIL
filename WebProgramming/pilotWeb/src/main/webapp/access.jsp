<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="site.manage.WebUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Access Page</title>
</head>
<body>
	<%
	WebUser user = (WebUser) session.getAttribute("user");
	if (user == null) {
	%>
	<jsp:forward page="index.jsp" />
	<%
	}
	%>
	<h1><%=user.getName()%>'s access is granted!
	</h1>
</body>
</html>