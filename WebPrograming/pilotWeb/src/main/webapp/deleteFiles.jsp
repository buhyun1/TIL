<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>deleted files</h2>
	<%
	String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
	String uploadPath = this.getServletContext().getRealPath(uploadFolder);
	File folders = new File(uploadPath);
	String userID = request.getParameter("userid");
	String password = request.getParameter("pw");
	//System.out.println(file.getAbsolutePath());
	Enumeration<String> params = request.getParameterNames();
	//Cookie�� ���� �α��� �� ���� Email �޾ƿ���
	Cookie[] c = request.getCookies();
	String UserID = "";
	if (c != null) {
		for (int i = 0; i < c.length; i++) {
			//Cookie�� LoginOK ��Ű�� �ִ� ��쿡�� ����
			if (c[i].getName().equals("LoginOK")) {
		//UserID������ Cookie�� ����ִ� ID����
		UserID = c[i].getValue();
		System.out.println("Logined User:" + UserID);
			}

		}
	}

	out.println("<ul>");
	while (params.hasMoreElements()) {
		String param = params.nextElement();
		System.out.println(param);

		int count = param.length();

		if (count == 5) {
		      File files = new File(uploadPath + UserID + "\\" + param);

		        File[] files1 = files.listFiles();
		        if (files1 != null) {
		            for (File file1 : files1) {
		                file1.delete(); // ���� �� ���� ����
		            }
		        }
		        files.delete(); // ���� ����
		        out.println("<li>" + param+ "</li>");
		}

        /**
		if (file3.exists()) {
			System.out.print("�ִ�!" + file3.getAbsolutePath());
			
		} else {
			System.out.print("����!" + file3.getAbsolutePath());
		}
		
		file3.delete();
		**/

	}
	out.println("</ul>");
	%>

	<form action="sample_management.jsp" method="post">
		<input type="hidden" name="userid" value="<%=userID%>" /> <input
			type="hidden" name="pw" value="<%=password%>" /> <input
			type="submit" value="Go back to file list" />
	</form>
</body>
</html>