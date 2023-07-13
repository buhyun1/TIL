<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your basket data</title>
<script type="text/javascript">
  function checkBasket() {
    let totalOutput = document.querySelector("output[name='total']");
    if (totalOutput.value == "0") {
      alert("No items to purchase!");
      return false;
    }
    return true;
  }

  function continueShopping() {
    let inputElements = document.querySelectorAll("input[type='number']");
    let anInput = null;
    for (let x = 0; x < inputElements.length; x++) {
      anInput = inputElements.item(x);
      document.cookie = anInput.name + "=" + anInput.value + "; SameSite=None; Secure";
    }
    
    let hiddenForm = document.createElement('form');
    hiddenForm.action = 'index.jsp';
    hiddenForm.method = 'post';
    hiddenForm.innerHTML = '<input type="submit">';
    document.body.append(hiddenForm);
    hiddenForm.submit();
  }
</script>
</head>
<body>
<%
  int apple = 0, orange = 0, pear = 0;
  int appleTotal = 0, orangeTotal = 0, pearTotal = 0;
  
  Cookie[] c = request.getCookies();
  for (int i = 0; i < c.length; i++) {
    if ("apple".equalsIgnoreCase(c[i].getName())) {
      apple = Integer.parseInt(c[i].getValue());
      appleTotal = apple * 2000;
    }
    
    if ("orange".equalsIgnoreCase(c[i].getName())) {
      orange = Integer.parseInt(c[i].getValue());
      orangeTotal = orange * 3000;
    }
    
    if ("pear".equalsIgnoreCase(c[i].getName())) {
      pear = Integer.parseInt(c[i].getValue());
      pearTotal = pear * 2500;
    }
  }
  
  int total = appleTotal + orangeTotal + pearTotal;
%>
Your basket:<br><br>
<form name="basketForm" action="purchaseItem.do" onsubmit="return checkBasket()" method="post"
  oninput="total.value=parseInt(totalApple.value)+parseInt(totalOrange.value)+parseInt(totalPear.value)">
  <fieldset style="width:270px">
    <legend>Choose Your Item</legend>
	<table>
	  <tr><td>Item</td><td>Quantity</td><td>Price</td><td>Subtotal</td></tr>
	  <tr>
	    <td style="text-align:right"><label for="apple"><small>Apple:</small></label></td>
		<td><input type="number" id="apple" name="apple" value="<%=apple%>" min="0" style="width:5em"
		  oninput="totalApple.value=apple.value*priceApple.value"></td>
        <td><input id="priceApple" value="2000" style="width:5em" readonly></td>
        <td style="text-align:right" ><output name="totalApple" for="apple priceApple"><%=appleTotal%></output></td>
	  </tr>
	  <tr>
	    <td style="text-align:right"><label for="orange"><small>Orange:</small></label></td>
		<td><input type="number" id="orange" name="orange" value="<%=orange%>" min="0" style="width:5em"
		  oninput="totalOrange.value=orange.value*priceOrange.value"></td>
        <td><input id="priceOrange" value="3000" style="width:5em" readonly></td>
        <td style="text-align:right"><output name="totalOrange" for="orange priceOrange"><%=orangeTotal%></output></td>        
	  </tr>
	  <tr>
	    <td style="text-align:right"><label for="pear"><small>Pear:</small></label></td>
		<td><input type="number" id="pear" name="pear" value="<%=pear%>" min="0" style="width:5em"
		  oninput="totalPear.value=pear.value*pricePear.value"></td>
        <td><input id="pricePear" value="2500" style="width:5em" readonly></td>
        <td style="text-align:right"><output name="totalPear" for="pear pricePear"><%=pearTotal%></output></td>
	  </tr>
	  <tr>
	    <td style="text-align:right" colspan="3"><label for="total"><small>Total:</small></label></td>
        <td style="text-align:right"><output name="total" for="totalApple totalOrange totalPear"><%=total%></output></td>
	  </tr>
	</table><br>
	
    <div style="width:270px;" align="right">
      <input type="submit" id="shopping" value="Continue shopping" onclick="continueShopping(); return false;">&nbsp&nbsp
      <input type="submit" id="submit" value="Click to checkout">
    </div>  
  </fieldset><br>    
</form>
</body>
</html>