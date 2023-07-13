<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Withdrawal</title>
</head>
<body>
<%
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
   PreparedStatement psm = conn.prepareStatement("DELETE FROM userlogin WHERE id = ?");
) {
   String userID = request.getParameter("userID");
   int id = Integer.parseInt(userID);
   psm.setInt(1, id);
      
   int count = psm.executeUpdate();
   if (count == 1)
      out.print("Member withdrawal success!");
   else
      out.print("Member withdrawal failed!");
} catch (SQLException e) {
   out.println("Invalid SQL: check SQL");
   e.printStackTrace();
}
%>
</body>
</html>
