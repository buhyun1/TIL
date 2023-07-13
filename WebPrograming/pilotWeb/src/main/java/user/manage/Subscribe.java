package user.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Servlet implementation class Subscribe
 */
public class Subscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscribe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);

		String UserFolder = this.getServletContext().getInitParameter("UserFolder");
		String UserFolderPath = this.getServletContext().getRealPath(UserFolder);
		java.util.Enumeration<String> params = request.getParameterNames();
		String FileName="";
		String pw="";
		String FullName="";
		String date="";
		File file = new File(UserFolderPath);
		
		String[] files=file.list();
		boolean IsSameID=false;
		
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			
			if ("userid".equals(param)) {
				
				String value = request.getParameter(param);
				for (String names: files){
		
					if(names.equals(value)) {
						IsSameID=true;
						
					}
					
				}
				FileName=value;
				}		
			
			else if("pw".equals(param)){
				String value = request.getParameter(param);
				pw=value;

				
			}
			else if("fullname".equals(param)) {
				String value = request.getParameter(param);
				FullName=value;
			}
			else if("birthdate".equals(param)) {
				String value = request.getParameter(param);
				date=value;
				
			}
		}
		PrintWriter printW = response.getWriter();
		try {
		FileWriter fw = new FileWriter(UserFolderPath+"\\"+FileName);
		fw.write(pw+"\n");
		fw.write(FullName+"; ");
		fw.write(date);
		fw.flush();
		}
		catch(Exception e){
			printW.print("<html>\r\n"
	    			+ "<body>\r\n"
	    			+ "<h2>Oops!</h2>\r\n"
	    			+ "\r\n"
	    			+ "Account creation failed.<br><br>\r\n"
	    			+ "ID: <strong style=\"color:blue;\">"+FileName+"</strong><br>\r\n"
	    			+ "Reason: <strong style=\"color:red;\">account creation failure</strong><br><br>\r\n"
	    			+ "\r\n"
	    			+ "Goto: <a href=\"subscribe.html\">subscribe</a>\r\n"
	    			+ "\r\n"
	    			+ "<script type=\"text/javascript\">\r\n"
	    			+ "  setTimeout(function(){ window.location.href=\"subscribe.html\" }, 3000);\r\n"
	    			+ "</script>\r\n"
	    			+ "</body>\r\n"
	    			+ "</html>");
		
		}
		
	     
	    finally {
	    	 response.setContentType("text/html");
	    
	    if(IsSameID) {
	    	printW.print("<html>\r\n"
	    			+ "<body>\r\n"
	    			+ "<h2>Oops!</h2>\r\n"
	    			+ "\r\n"
	    			+ "Account creation failed.<br><br>\r\n"
	    			+ "ID: <strong style=\"color:blue;\">"+FileName+"</strong><br>\r\n"
	    			+ "Reason: <strong style=\"color:red;\">duplicated user account.</strong><br><br>\r\n"
	    			+ "\r\n"
	    			+ "Goto: <a href=\"subscribe.html\">subscribe</a>\r\n"
	    			+ "\r\n"
	    			+ "<script type=\"text/javascript\">\r\n"
	    			+ "  setTimeout(function(){ window.location.href=\"subscribe.html\" }, 3000);\r\n"
	    			+ "</script>\r\n"
	    			+ "</body>\r\n"
	    			+ "</html>");
	    }
	    else {
    	Cookie LoginCookie  = new Cookie("LoginOK", FileName);
    	response.addCookie(LoginCookie);

	    File userFolder =new File(UploadFolderPath+"\\"+FileName);
	    userFolder.mkdir();
	    printW.print("<html>\r\n"
	    		+ "<body>\r\n"
	    		+ "<h2>Welcome"+FullName+"</h2>\r\n"
	    		+ "\r\n"
	    		+ "Your account successfully created.<br><br>\r\n"
	    		+ "ID: <strong style=\"color:blue;\">"+FileName+"</strong><br><br>\r\n"
	    		+ "\r\n"
	    		+ "Goto: <a href=\"login.html\">login</a>\r\n"
	    		+ "\r\n"
	    		+ "<script type=\"text/javascript\">\r\n"
	    		+ "  setTimeout(function(){ window.location.href=\"sample_management.html\" }, 3000);\r\n"
	    		+ "</script>\r\n"
	    		+ "</body>\r\n"
	    		+ "</html>");
	    }
		
	    printW.close();
	    }
	}

}
