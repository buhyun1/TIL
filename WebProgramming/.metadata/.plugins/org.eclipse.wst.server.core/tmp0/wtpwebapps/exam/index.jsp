<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="site.manage.WebUser, shop.fruit.Inventory" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seasonal Fruit Megastore</title>
<style>
  table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
  }
</style>
<script type="text/javascript">
  var _loginPopup = null;
  function loginPopup() {
    if (_loginPopup == null || _loginPopup.closed) {
        _loginPopup = window.open("tryLogin.jsp", "_loginPopup", "width=230,height=200,left=100,top=100,menubar=no");
    } else {
        _loginPopup.focus();
    }
  }
  
  function checkOrder() {
    alert("check order!");
    return true;
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
    name = "customer";
%>
<h1>Welcome <%=name%>!</h1>
<p>Current inventory status. Order our fruits!</p>

<table style="width:300px">
  <tr>
    <td style='text-align:center'>Fruit</td>
    <td style='text-align:center;width:4em'>Price</td>
    <td style='text-align:center'>On Stock</td>
    <td style='text-align:center'>Order Qty</td>
  </tr>
<%
  String toPrint = null;
  Inventory[] p = Inventory.allItems;
  for (int i = 0; i < p.length; i++) {
    toPrint = String.format("<tr><td style='text-align:right'>%s</td>" + 
      "<td style='text-align:right'>%d</td>" + 
      "<td style='text-align:right'>%d</td>" + 
      "<td><input type='number' name='%s' value='0' min='0' max='%d' style='width:6em'></td></tr>", 
      p[i].getName(), p[i].getPrice(), p[i].getOnHandStock(), p[i].getName(), p[i].getOnHandStock());
    out.println(toPrint);
  }
%>
</table><br>

<div style="width:300px;" align="right">
  <form name="addBasketForm" action="showBasket.jsp" onsubmit="return checkOrder()" method="post">
    <input type="submit" value="To my basket">
  </form>
</div>
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