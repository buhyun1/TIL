<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="site.manage.DBConnectionContextListener" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lookup USERLOGIN Table</title>
</head>
<body>
<%
/*    String driverName = this.getServletContext().getInitParameter("dbclass");
   String driverURL = this.getServletContext().getInitParameter("dburl");
   String username = this.getServletContext().getInitParameter("dbuser");
   String password = this.getServletContext().getInitParameter("dbpwd");
   String dbName = "pilotweb";
   
// String driverName = this.getServletContext().getInitParameter("dbclass");
   try {
      Class.forName(driverName);
   } catch(ClassNotFoundException e) {
      out.println("driver class not found!");
      e.printStackTrace();
   } */
   
   Connection conn = site.manage.DBConnectionContextListener.getDBConnection(); // 리스너 불러오기

//   String driverURL = this.getServletContext().getInitParameter("dburl");
   
   out.println("Driver Loaded.<br>");
   try (
		   //Connection conn = DriverManager.getConnection(driverURL);
		   Statement state = conn.createStatement(); 
		) {
		   //out.println("driverURL: " + driverURL + ", connected!<br>");
		   String str = "";
		   ResultSet rs = state.executeQuery("SELECT id, email, birth_date, full_name from userlogin");
		   while (rs.next()) {
		      str += rs.getInt("id") + ": " + rs.getString("email") + ", " + 
		             rs.getDate("birth_date") + ", " + rs.getString("full_name") + "<br>\n";
		   }
		   out.println("All member list<hr>");
		   out.println(str);     
		   rs.close();      
		}  catch(SQLException e) {
		   out.println("Invalid SQL: check SQL");	 
		   e.printStackTrace();
		} finally{
			  DBConnectionContextListener.returnDBConnection(conn);
		}

%>
</body>
</html>
