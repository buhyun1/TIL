package exam.mid;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/**
 * Servlet implementation class FileKeep
 */
public class FileKeep extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileKeep() {
		super();
		// TODO Auto-generated constructor stub
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

		java.util.Enumeration<String> params = request.getParameterNames();
		String FileName = "";
		String pass_code = request.getParameter("pass_code");
		PrintWriter pw = response.getWriter();

	    // TxRequest Cookie에서 pass_code 값을 가져오기
	    Cookie[] cookies = request.getCookies();
	    String passcode = "";
	    boolean IsCookied = false;
	    if (cookies != null) {
	      for (Cookie cookie : cookies) {
	        if (cookie.getName().equals("TxRequest")) {
	        	if (cookie.getValue().equals(pass_code)) {
		          passcode = cookie.getValue();
		          IsCookied = true;
		          pw.print("<html>"
		  				+"<head>"
		  				+"<meta charset=\"UTF-8\">"
		  				+" <title>파일 전송 횟수 제한</title>"
		  				+"</head>"
		  				+"<body>"
		  				+"<div style=\"width:400px;\" align=\"center\"> "
		  				+"<strong>이전에 업로드한 다른 파일이 이미 존재합니다.</strong><br><br>"
		  				+"</div>"
		  				+"5분간 추가 업로드가 제한됩니다. 5분 후에 다시 시도해 주세요.<br><br>"
		  				+"<form action=\"deleteFile.do\" method=\"post\">"
		  				+"<input type=\"hidden\" name=\"pass_code\" id=\"pass_code\" value=\"" + pass_code +"\">"
		  				+"<label for=\"delete\"><small>이전에 업로드한 파일을 지금 삭제하기:</small></label>"
		  				+"<input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Delete\">"
		  				+"</form>"
		  				+"<br>"
		  				+"</body>"
		  				+"</html>");
	        	}
	        }
	      }
	    }
	    if(IsCookied == false) {
	    	while (params.hasMoreElements()) {
				String param = params.nextElement();
				System.out.println(param);
				if ("pass_code".equals(param)) {

					String value = request.getParameter(param);

					FileName = value;
					File userFolder = new File(UploadFolderPath + "\\" + FileName);
					userFolder.mkdir();

					for (Part part : request.getParts()) {
						if ("upfile".equals(part.getName())) {
		
						String fileName = part.getSubmittedFileName();
						part.write(userFolder +"\\"+ fileName);

						}
					}

				}
				
			}
			

		    // TxRequest Cookie 생성
		    Cookie txRequestCookie = new Cookie("TxRequest", pass_code);

		    // TxRequest Cookie의 유효기간 설정
		    //int cookieMaxAge = 5 * 60; // 5분 (단위: 초)
	        Calendar cal = Calendar.getInstance();

	        // 5분을 더하기 위해 Calendar 객체에 5분을 더함
	        cal.add(Calendar.MINUTE, 5);

	        // 5분을 더한 시간 구하기
	        Date after5m = cal.getTime();
	        txRequestCookie.setMaxAge((int) (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000);
	        
	        response.addCookie(txRequestCookie);

			pw.print("<html>"
					+"<head>"
					+"<meta charset=\"UTF-8\">"
					+" <title>파일 전송 성공</title>"
					+"</head>"
					+"<body>"
					+"<div style=\"width:400px;\" align=\"center\"> "
					+"<strong>파일이 성공적으로 업로드 되었습니다.</strong><br><br>"
					+"</div>"
					+"pass_code: <strong style=\"color:red;\">"+FileName+"</strong><br>"
					+"삭제 예정 시간: <strong style=\"color:red;\">"+after5m+"</strong><br>"
					+"바로 다운 받기: <a href=\"receiveFile.html\">다운 받기</a><br>"
					+"<br>"
					+"</body>"
					+"</html>");
	    }
		System.out.println(cookies);
		pw.close();
	}
	   

}
	