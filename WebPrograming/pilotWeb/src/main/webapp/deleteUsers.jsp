<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"  %>
<%@ page import="java.io.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
    <%
    String UserFolder = this.getServletContext().getInitParameter("UserFolder");
    String UserFolderPath = this.getServletContext().getRealPath(UserFolder);
    
    String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
    String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);
    
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
        String param = params.nextElement();
        File file = new File(UserFolderPath + "\\" + param);
        File userFolder = new File(UploadFolderPath + "\\" + param);
        if(file.exists()){
            file.delete();
            System.out.println(param+"file is deleted");
            
            File[] files = userFolder.listFiles();
            // 폴더 삭제
            if (userFolder.exists()) {
                if (files != null) {
                    for (File file1 : files) {
                        file1.delete(); // 폴더 내 파일 삭제
                    }
                }
                userFolder.delete(); // 폴더 삭제
        }
    }
    }
    out.println("<html>\r\n" + "<body>\r\n" + "Goto: <a href=\"adminLookup.jsp\">subscribe</a>\r\n" + "\r\n"
            + "<script type=\"text/javascript\">\r\n"
            + "  setTimeout(function(){ window.location.href=\"adminLookup.jsp\" }, 1000);\r\n" + "</script>\r\n"
            + "</body>\r\n" + "</html>");
    %>

</body>
</html>