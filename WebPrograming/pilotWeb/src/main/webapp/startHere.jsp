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
      document.cookie = pair[0] + "= ; Expires= Thu, 01 Jan 1970 00:00:00 GMT";
    }
    
    setCookieSectionMessage('');
  }
  
  function findCookieValue(name) {
    const value = document.cookie.split('; ')
      .find(pair => pair.startsWith(name + '='))
      .split('=')[1];
    
    return value;
  }

  var _popup_window = null;
  function openVisitPopup(url, windowName) {
    if (_popup_window == null || _popup_window.closed) {
      _popup_window = window.open(url, windowName, "width=300,height=200,left=200,top=200,menubar=no");
    } else {
      _popup_window.focus();
    }
  }
</script>
</head>
<body>
<h1>Main shopping page</h1>

<a href="showBasket.jsp">Goto Basket</a><br><br>

<button onclick="showAllCookies()">Show all cookies</button>&nbsp&nbsp
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
    for(int i = 0; i < c.length; i++) {
      message = String.format("[%d] %s= %s", i, c[i].getName(), c[i].getValue());
      out.println("<br>" + message);

      if ("visit".equalsIgnoreCase(c[i].getName()))
        visitCookie = c[i];
    }
    out.println("<hr>");
  }
  
  if (visitCookie == null) {
    visitCookie = new Cookie("visit", "1");
  } else {
    int counter = Integer.parseInt(visitCookie.getValue()) + 1;
    visitCookie.setValue("" + counter);
  }
  out.println("cookie for visit is set to " + visitCookie.getValue() + ".<br>");
  
  response.addCookie(visitCookie);
%>

<script type="text/javascript">
  document.cookie = 'test=ok; SameSite=None; Secure';
  
  var visit = findCookieValue('visit');
  if (visit == 1) {
    openVisitPopup('', '_welcome_first_visit');
    _popup_window.document.write("<h3>Welcome your first visit!</h3>");
  }
</script>
</body>
</html>

<!-- // 삭제할 쿠키의 이름을 설정합니다.
String cookieName = "쿠키 이름";

// 쿠키 생성
Cookie cookie = new Cookie(cookieName, null);
cookie.setMaxAge(0);

// 응답 헤더에 쿠키를 추가하여 삭제합니다.
response.addCookie(cookie); -->