<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%="I've received these parameters:<br><br>"%>
	<%
	java.util.Enumeration<String> params = request.getParameterNames();
	while (params.hasMoreElements()) {
		String param = params.nextElement();
		if ("reason".equals(param)) {
			String[] values = request.getParameterValues(param);
			for (String v : values) {
		out.append(param + ": " + v + "<br>");
			}
		} else {
			String value = request.getParameter(param);
			out.append(param + ": " + value + "<br>");
		}
	}
	%>

</body>
</html>