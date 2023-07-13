<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DB Connection Test Page</title>
</head>
<body>
	<%
	String driverName = "org.mariadb.jdbc.Driver";
	String dbName = "pilotweb";
	String driverURL = "jdbc:mariadb://localhost:3306/" + dbName;
	String username = "root";
	String password = "secret";
	
	try {
		Class.forName(driverName);
	} catch (ClassNotFoundException e) {
		out.println("driver class not found!");
		System.out.println(e.getStackTrace());
	}
	ResultSet rs = null;
	try (Connection conn = DriverManager.getConnection(driverURL, username, password);
		Statement statement = conn.createStatement();) {
		out.println("driverURL: " + driverURL + ", connected!");
		boolean isResultSetReturned = statement.execute("SELECT * FROM userlogin");
	    System.out.println(isResultSetReturned ? "ResultSet returned." : "Not a ResultSet.");
	} catch (SQLException e) {
		out.println("Invalid SQL: check SQL");
		e.printStackTrace();
	}
	%>
</body>
</html>