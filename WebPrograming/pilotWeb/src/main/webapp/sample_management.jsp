<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
	<%
    String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
    String uploadPath = this.getServletContext().getRealPath(uploadFolder);
    
    String userID = request.getParameter("userid");
    String password = request.getParameter("pw");
    
    Cookie[] c = request.getCookies();
    String UserID="";
    
    String driverName = "org.mariadb.jdbc.Driver";
    String dbName = "manager";
    String dbName2 = "fileservice";
    String driverURL = "jdbc:mariadb://localhost:3306/" + dbName;
    String driverURL2 = "jdbc:mariadb://localhost:3306/" + dbName2;
    String dbusername = "root";
    String dbpassword = "secret";
    String dbname=null;
    String dbpw=null;
    String fileName = null;
    try (Connection conn = DriverManager.getConnection(driverURL, dbusername, dbpassword);
            Statement state = conn.createStatement();) {
        // pw.println("driverURL: " + driverURL + ", connected!<br>");
        String sql = "select username, password from manager";
        ResultSet rs = state.executeQuery(sql);
        rs.next();
        dbname = rs.getString(1);
        dbpw = rs.getString(2);
        // pw.println(dbname);
        // pw.println(dbpw);
        rs.close();
        state.close();
        conn.close();
    } catch (SQLException e) {c
        //pw.println("Invalid SQL: check SQL");
        e.printStackTrace();
    }
    // ������ ���� Ȯ�� �� ���� ��� ����Ʈ ��
    if (userID.equals(dbname) & password.equals(dbpw)) {
        File file= new File(uploadPath+"\\"+UserID);
        out.println("<div style=\"width:400px;\" align=\"center\"> \r\n"
                + "  <strong><ins>���� ���</ins></strong><br><br>\r\n"
                + "</div>\r\n"
                + "\r\n"
                + "<form action=\"deleteFiles.jsp\" method=\"post\">\r\n"
                + "  <fieldset style=\"width:400px\">\r\n"
                + "    <legend>List of uploaded files</legend>"
                );
        for(String s: file.list()){
             out.println("<input type=\"checkbox\" id="+s+" name="+s+" value="+s+">\r\n"
                     + " <label for="+s+">"+s+"</label><br>");
             fileName = s;
                    		 
             try (Connection conn = DriverManager.getConnection(driverURL2, dbusername, dbpassword);
                     Statement state = conn.createStatement();) {
                    String deleteSql = "DELETE FROM filelist WHERE pass_code = '" + fileName + "'";
                    System.out.print(deleteSql);
                    int rowsAffected = state.executeUpdate(deleteSql);
                    if (rowsAffected > 0) {
                        System.out.println("���� �̸� '" + fileName + "'��(��) �����ͺ��̽����� �����߽��ϴ�.");
                    } else {
                        System.out.println("���� �̸� '" + fileName + "'��(��) �����ͺ��̽����� �����ϴµ� �����߽��ϴ�.");
                    }
                    state.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

        }
        out.print("<hr>\r\n"
                + "    <div style=\"width:400px;\" align=\"right\"> \r\n"
                + "      <input type=\"submit\" value=\"Delete\">&nbsp;&nbsp;\r\n"
                + "      <input type=\"reset\" value=\"Reset\">\r\n"
                + "    </div>   \r\n"
                + "  </fieldset><br>  \r\n"
                		+ "  <input type=\"hidden\" name=\"userid\" value=\"" + userID + "\"/>" 
                        + "  <input type=\"hidden\" name=\"pw\" value=\"" + password + "\"/>" 
                        
                + "</form>\r\n"
                + "<br>\r\n");
        
    }

        /**
        try (Connection conn = DriverManager.getConnection(driverURL2, dbusername, dbpassword);
                Statement state = conn.createStatement();)
        {
            String sql = "DELETE FROM filelist WHERE pass_code = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, pass_code);
            ResultSet rs = state.executeQuery(sql);
            rs.next();
            System.out.print("hhi");
            rs.close();
            state.close();
            conn.close();
            

    }catch (SQLException e) {
        //pw.println("Invalid SQL: check SQL");
        e.printStackTrace();
    } 
        **/



    
        
    /**
      if (c != null) {
          
          for(int i = 0; i < c.length; i++) {
              //Cookie�� LoginOK ��Ű�� �ִ� ��쿡�� ����
              if(c[i].getName().equals("LoginOK")){
                  out.println(" >> Total Cookies (found at the server): " + c.length + "<br>");
                  String message = null;
                  
              message = String.format("[%d] %s= %s", i, c[i].getName(), c[i].getValue());
              //UserID������ Cookie�� ����ִ� ID����
              UserID=c[i].getValue();
              System.out.println(message);

              }
              
          }
          }
    **/ 
  
          /*  + "<form action=\"fileUpload.jsp\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
           + "  <fieldset style=\"width:400px\">\r\n"
           + "    <legend>Upload a new file:</legend>\r\n"
           + "    <label for=\"upfile\"><small>Choose Files:</small></label><br>\r\n"
           + "    <input type=\"file\" name=\"upfile\" id=\"upfile\" multiple/><br>\r\n"
           + "    <hr>\r\n"
           + "    <div style=\"width:400px;\" align=\"right\"> \r\n"
           + "      <input type=\"submit\" value=\"Upload\">&nbsp;&nbsp;\r\n"
           + "      <input type=\"reset\" value=\"Clear\">\r\n"
           + "    </div> \r\n"
           + "  </fieldset>\r\n"
           + "</form>"); */
    
    %>

</body>
</html>