<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
	String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);

	Enumeration<String> params = request.getParameterNames();

	String FileName = "";
	String pass_code = request.getParameter("pass_code");

	while (params.hasMoreElements()) {
		String param = params.nextElement();

		if ("pass_code".equals(param)) {

			String value = request.getParameter(param);

			FileName = value;

		}
	}

	File userFolder = new File(UploadFolderPath + "\\" + pass_code);

	System.out.println(userFolder);

	long currentTime = System.currentTimeMillis();
	long folderCreationTime = userFolder.lastModified();
	long timeDifference = (currentTime - folderCreationTime) / 1000 / 60;

	File[] files = userFolder.listFiles();
	String Firstfile = files[0].getName();
	File userFolderFile = new File(userFolder + "\\" + Firstfile);
	if (userFolder.exists() && timeDifference <= 5) {

		out.println("<html>");
		out.println("<body>");
		out.println("<div style=\"width: 400px;\" align=\"center\">");
		out.println("<strong><ins>Download File</ins></strong><br> <br>");
		out.println("</div>");
		out.println(
		"다운 받기: [<a href= " + "upload\\" + pass_code + "\\" + Firstfile + " download>" + Firstfile + "</a>]\r\n");
		out.println("</body>");
		out.println("</html>");

		// TxRequest 쿠키 삭제
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("TxRequest")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
	}  else {
		out.println("<html>");
		out.println("<body>");
		out.println("<div style=\"width: 400px;\" align=\"center\">");
		out.println(" <strong><ins>Download Error</ins></strong><br><br>");
		out.println("</div>");
		out.println("그런 파일이 없거나, 제한시간 5분이 경과하였습니다.");
		out.println("</body>");
		out.println("</html>");
		 if (timeDifference > 5) {

			// 폴더 삭제
			if (userFolder.exists()) {
				File[] files1 = userFolder.listFiles();
				if (files1 != null) {
					for (File file1 : files) {
						file1.delete(); // 폴더 내 파일 삭제
					}
				}
				userFolder.delete(); // 폴더 삭제
			}
		} 
	}
	
	%>

</body>
</html>