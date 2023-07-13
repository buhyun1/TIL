<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="site.manage.ValidUser"%>
<%@ page import="jakarta.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your order data</title>
<script type="text/javascript">
	function deleteOrder(orderID) {
		let hiddenDeleteForm = document.createElement('form');
		hiddenDeleteForm.action = 'manageFruit.jsp';
		hiddenDeleteForm.method = 'post';
		hiddenDeleteForm.innerHTML = '<input type="hidden" name="orderID" value="' + orderID + '"><br><input type="submit">';
		document.body.append(hiddenDeleteForm);
		hiddenDeleteForm.submit();
	}
</script>
</head>
<body>
	<%
	//세션 불러오기
	HttpSession session2 = request.getSession();
	ValidUser user = (ValidUser) session2.getAttribute("user");
	
    int userID = user.getId();
    String userEmial = user.getEmail();
    String userFullName = user.getFullName();

    out.println(userFullName +"'s order:"); // 세션에서 이름 넣는 것 까지 성공!
    // 뒤에 주문 목록 수정 못함
    
	%>
	<br>
	<br>
	<form name="basketForm" action="shopping.jsp" method="post">
		<fieldset style="width: 300px">
			<legend>Click delete to cancel your order</legend>
			<table>
				<tr>
					<td>Order ID</td>
					<td>Fruit</td>
					<td>Quantity</td>
					<td>Delete</td>
				</tr>
				<tr>
					<td style="text-align: left; width: 20em">3</td>
					<td style="text-align: left; width: 20em">Orange</td>
					<td style="text-align: left; width: 7em">1</td>
					<td><input type="button" style="width: 5em" value="delete"
						onclick="deleteOrder(3); return true;"></td>
				</tr>
				<tr>
					<td style="text-align: left; width: 20em">4</td>
					<td style="text-align: left; width: 20em">Papaya</td>
					<td style="text-align: left; width: 7em">4</td>
					<td><input type="button" style="width: 5em" value="delete"
						onclick="deleteOrder(4); return true;"></td>
				</tr>
			</table>
			<br>

			<div style="width: 270px;" align="right">
				<input type="submit" value="Continue shopping">
			</div>
		</fieldset>
	</form>
	<br>
	<p>
		<a href='requestLogout.do'>Log out</a>
	</p>

</body>
</html>

