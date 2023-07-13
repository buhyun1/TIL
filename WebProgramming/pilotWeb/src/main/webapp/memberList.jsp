<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lookup Member List</title>
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
   Statement statement = conn.createStatement();
) {
   String str = "";
   ResultSet rs = statement.executeQuery("SELECT * from userlogin");
   while (rs.next()) {
      str += rs.getInt("id") + ": " + rs.getString("email") + ", " + 
             rs.getDate("birth_date") + ", " + rs.getString("full_name") + "<br>";
   }
   out.println("All member list<hr>");
   out.println(str);     
   rs.close();
} catch (SQLException e) {
   out.println("Invalid SQL: check SQL");
   e.printStackTrace();
}
%>
</body>
</html>
