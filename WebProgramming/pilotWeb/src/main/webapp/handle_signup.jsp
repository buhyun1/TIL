<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Sign-up</title>
</head>
<body>
<%
String email = request.getParameter("email");
String pw = request.getParameter("pw");
String birthdate = request.getParameter("birthdate");
String fullname = request.getParameter("fullname");
String sql = String.format(
   "INSERT INTO userlogin(email, user_password, birth_date, full_name) " + "VALUES('%s', '%s', '%s', '%s')", 
   email, pw, birthdate, fullname);
String driverName = "org.mariadb.jdbc.Driver";
String dbName = "pilotweb";
String driverURL = "jdbc:mariadb://localhost/" + dbName;
String username = "root";
String password = "secret";

try {
   Class.forName(driverName);
} catch (ClassNotFoundException e) {
   out.println("driver class not found!");
   e.printStackTrace();
}

try (
   Connection conn = DriverManager.getConnection(driverURL, username, password);
   Statement state = conn.createStatement();
) {
   out.println("driverURL: " + driverURL + ", connected!");
      
   int count = state.executeUpdate(sql);
   out.println(count == 1 ? "Registration Success!" : "Registration Failed.");
} catch (SQLException e) {
   out.println("Invalid SQL: check SQL");
   e.printStackTrace();
}
%>
</body>
</html>