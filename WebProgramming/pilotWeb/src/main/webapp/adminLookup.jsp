<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	out.println("<form action=\"deleteUsers.jsp\" name=\"unregisterForm\" method=\"post\">");
	out.println("<div style=\"width:400px;\" align=\"center\">"); 
	out.println("<strong><ins>Registered Users</ins></strong><br><br>");
	out.println("</div>");
	
	String uploadFolder = this.getServletContext().getInitParameter("UserFolder");
	String uploadPath = this.getServletContext().getRealPath(uploadFolder);
	File file = new File(uploadPath);
	String[] files = file.list();
	
	out.println("<fieldset style=\"width:400px\">");
	out.println("<legend>List of registered users</legend>");
	
	for (String fileName: files){
		
    	
    	File file1 = new File(uploadPath + "\\" + fileName);   
        FileReader reader = new FileReader(file1);
        BufferedReader br = new BufferedReader(reader);
        String line="";
        String[] words = null;
        while ((line = br.readLine()) != null) {
            words = line.split("\\s");
            
        }
      
        String name = Arrays.toString(words);
        int idx = name.indexOf(";");
        String name1 = name.substring(1, idx);
        
        name1 = name1.replaceAll("[^\\w+]", " ");   
        out.println("<input type=\"checkbox\" id="+fileName+" name="+fileName+" value="+fileName+">");
        out.println("<label for="+fileName+">"+name1+"</label>"+"[<strong style=\"color:red;\">"+fileName+"</strong>]<br>");
        br.close();

       
	};
   
	
	out.println("<hr>");
	out.println("<div style=\"width:400px;\" align=\"right\">");
	out.println("<input type=\"submit\" value=\"Delete\">&nbsp;&nbsp;");
    out.println("<input type=\"reset\" value=\"Reset\">");
    out.println("</div>"); 	
    out.println("</fieldset><br>"); 
    out.println("</form>");
	%>
</body>
</html>