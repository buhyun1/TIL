<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="site.manage.WebUser"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>First pilotWeb Project</title>
<script type="text/javascript">
	var _loginPopup = null;
	function loginPopup() {
		if (_loginPopup == null || _loginPopup.closed) {
			_loginPopup = window.open("tryLogin.jsp", "_loginPopup",
					"width=230,height=200,left=100,top=100,menubar=no");
		} else {
			_loginPopup.focus();
		}
	}
</script>
</head>
<body>
	<%
	String name;
	WebUser user = (WebUser) session.getAttribute("user");
	if (user != null)
		name = user.getName();
	else
		name = "visitor";
	%>
	<h1>
		Welcome
		<%=name%>!
	</h1>
	<p>Some contents here.</p>
	<%
	if (user != null) {
		out.append(name + " logged-in at " + user.getLastLoggedIN() + ".<br>");
		out.append("<a href='processLogout.do'>log out</a>");
	} else {
		out.append("<a href='javascript:loginPopup()'>login</a>");
	}
	%>
</body>
</html>