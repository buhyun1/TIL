

<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<h2>uploaded files</h2>
	<%
	String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
	String uploadPath = this.getServletContext().getRealPath(uploadFolder);
	  Cookie[] c = request.getCookies();
	  String UserID="";
		if (c != null) {
			
			for(int i = 0; i < c.length; i++) {
				//Cookie중 LoginOK 쿠키가 있는 경우에만 진행
				if(c[i].getName().equals("LoginOK")){
					out.println(" >> Total Cookies (found at the server): " + c.length + "<br>");
					String message = null;
					
				message = String.format("[%d] %s= %s", i, c[i].getName(), c[i].getValue());
				//UserID변수에 Cookie에 들어있는 ID저장
				UserID=c[i].getValue();
				System.out.println(message);

				}
				
			}
			}
	Random random = new Random();
	for (Part part : request.getParts()) {
		if ("upfile".equals(part.getName())) {

			String fileName = part.getSubmittedFileName();
			int dot = fileName.lastIndexOf(".");
			String newName = uploadPath +"\\"+UserID+"\\"+ fileName.substring(0, dot) + "_New" + fileName.substring(dot);
			File file = new File(uploadPath + "\\" +UserID+"\\"+ fileName);

			//Upload한 파일과 같은 이름의 파일이 있는지 확인
			if (file.exists()) {
				System.out.println("already have samename File");
				File exists = new File(newName);
		
				// _new가 붙은 이름의 파일이 있는지 확인
		
				if (exists.exists()) {
					//파일 마다 난수 생성을 위해서 for 문 안에서 난수 생성
					int number = random.nextInt(100); // random값은 0~100사이로 지정한다.
					File IsSameNumber = new File(
							uploadPath +"\\"+UserID+"\\"+ fileName.substring(0, dot) + "_New_" + number + fileName.substring(dot));
					//난수가 붙은 파일명도 동일한 경우 다음 파일로 넘어간다.
					if (IsSameNumber.exists()) {
						continue;
					}
					// 아닌 경우 난수가 붙은 파일을 저장
					else {
						part.write(uploadPath +"\\"+UserID+"\\"+ fileName.substring(0, dot) + "_New_" + number + fileName.substring(dot));
						//파일 명이 변경되어 저장되는 경우 해당 파일명으로 출력시킨다.
						out.println("<li>File: " + fileName.substring(0, dot) + "_New_" + number + fileName.substring(dot)
								+ " uploaded.</li><br>");
		
					}
				}
		
				else {
					part.write(newName);
					out.println("<li>File: " + fileName.substring(0, dot) + "_New" + fileName.substring(dot)
							+ " uploaded.</li><br>");
				}
			
			}
			else{
			part.write(uploadPath + "\\" +UserID+"\\"+ fileName);
			out.println("<li>File: " + fileName + " uploaded.</li><br>");
			}

		}

	}

	out.println("</ul>");
	%>
	Goto:
	<a href="lookupFiles.jsp">File Upload Management</a>

</body>
</html>
