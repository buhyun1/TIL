<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.io.File"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<body>
	<h2>deleted files</h2>

	<form action="deleteFiles.jsp" method="post">
		<%
		Cookie[] c = request.getCookies();
		//Cookie�� ���� UserID ����;
		String UserID="";
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
			
		
		
	
		String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String uploadPath = this.getServletContext().getRealPath(uploadFolder);
		File file = new File(uploadPath);     
		Enumeration<String> params = request.getParameterNames();
	    
	    out.println("<ul>");
	    //LoginOK Cookie�� �ִ� ��쿡�� while�� �۵�
	    if(UserID!=""){
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			out.println("<li>" + param + "</li>");
			File file3 = new File(uploadPath + "\\" + UserID+"\\"+param);
			if (file3.exists()) {
				System.out.print("�ִ�!" + file3.getAbsolutePath());
			} else {
				System.out.print("����!" + file3.getAbsolutePath());
			}

			file3.delete();

		}
	    }
		out.println("</ul>");
		/*   for (String fileName: files){
	            out.print("<input type='checkbox' id="+fileName+"name="+fileName+"value="+fileName+">");
	            out.print("<label for="+fileName+">"+fileName+"</label><br>");
	            File file1 = new File(uploadPath + "\\" + fileName);
	            	
		  };
		  file1.delete(); 
		 */
		 %>
		Goto: <a href="lookupFiles.jsp">File Upload Management</a>


		<hr>

		</fieldset>
		<br>
	</form>
	<br>
</body>
</html>

