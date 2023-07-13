<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Start Page</title>
<script type="text/javascript">
	function setCookieSectionMessage(message) {
		let output = document.getElementById('cookieSection');
		output.textContent = message;
	}
	function showAllCookies() {
		setCookieSectionMessage("cookies: " + document.cookie);
	}
	function clearAllCookies() {
		const cookies = document.cookie.split('; ');
		let pair;
		for (let i = 0; i < cookies.length; i++) {
			pair = cookies[i].split("=");
			document.cookie = pair[0]
					+ "= ; Expires= Thu, 01 Jan 1970 00:00:00 GMT";
		}
		setCookieSectionMessage('');
	}
</script>
</head>
<body>
	<h1>Main shopping page</h1>
	<a href="showBasket.jsp">Goto Basket</a>

	<button onclick="showAllCookies()">Show all cookies</button>

	<button onclick="clearAllCookies()">Clear all cookies</button>
	
	<div><code id="cookieSection"></code></div>

	<%
	java.util.Random rand = new java.util.Random();
	Cookie secretCookie = new Cookie("secret", "" + rand.nextInt());
	secretCookie.setHttpOnly(true);
	response.addCookie(secretCookie);
	Cookie visitCookie = null;

	
	out.println("<hr>");
	Cookie[] c = request.getCookies();
	
	if (c != null) {
		out.println(" >> Total Cookies (found at the server): " + c.length + "<br>");
		String message = null;
		for (int i = 0; i < c.length; i++) {
			message = String.format("[%d] %s= %s", i, c[i].getName(), c[i].getValue());
			out.println("<br>" + message);
			
			if ("visit".equalsIgnoreCase(c[i].getName())) visitCookie = c[i];
		}
		out.println("<hr>");
	}
	
	if (visitCookie == null) {
		visitCookie = new Cookie("visit", "1");
		} else {
		int counter = Integer.parseInt(visitCookie.getValue()) + 1;
		visitCookie.setValue("" + counter);
		}
		response.addCookie(visitCookie);
		out.println("cookie for visit is set to " + visitCookie.getValue() + ".<br>");
	
	%>
	<script type="text/javascript">
		document.cookie = 'test=ok; SameSite=None; Secure';
	</script>

</body>
</html>