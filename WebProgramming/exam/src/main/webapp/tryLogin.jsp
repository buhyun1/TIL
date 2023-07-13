<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Form</title>
</head>
<body>
<div align="center"> 
  <form name="loginForm" action="processLogin.do" method="post" align="left">
    <fieldset style="width:180px">
      <legend>Login Information</legend>

      <label for="userID"><small>User ID:</small></label><br>
      <input type="text" id="userID" name="userID" required><br>
  
      <label for="password"><small>Password:</small></label><br>
      <input type="password" id="password" name="password" required><br><br>
    
      <div align="right"> 
        <input type="submit" value="Login">
      </div> 
    </fieldset>
  </form>
</div>
</body>
</html>
