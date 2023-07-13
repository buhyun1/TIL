<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
jakarta.servlet.ServletContext context = this.getServletContext();
String fname = request.getParameter("file");
java.io.File toDownload = new java.io.File(context.getRealPath("/image/" + fname));
System.out.println("toDownload = " + toDownload);

if (toDownload.exists()) {
   response.setContentType("application/octet-stream");
   response.setContentLength((int) toDownload.length());
   
   String headerKey = "Content-Disposition";
   
   String encFileName = java.net.URLEncoder.encode(toDownload.getName(), "UTF-8");
   String headerValue = String.format("attachment; filename=\"%s\"", encFileName);
   
   response.setHeader(headerKey, headerValue);
   System.out.println("filename = " + toDownload.getName());
   System.out.println("filelength = " + toDownload.length());
   
   try {
	  java.io.OutputStream outS = response.getOutputStream();
      java.io.FileInputStream fin = new java.io.FileInputStream(toDownload);
      int i;
      while ((i = fin.read()) != -1) {
         outS.write(i);
      }
      fin.close();
      outS.close();
   } catch(Exception e) {
	   e.printStackTrace();
   }
   
// try (PrintWriter out = response.getWriter()) {
//    FileInputStream fin = new FileInputStream(toDownload);
//    int i;
//    while ((i = fin.read()) != -1) {
//       out.write(i);
//    }
//    fin.close();
//    out.close();
// }
} else {
   response.setContentType("text/html;charset=UTF-8");
   response.getWriter().println("file not exist!");
}

%>
</body>
</html>