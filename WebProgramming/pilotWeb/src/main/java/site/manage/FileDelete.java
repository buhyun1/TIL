package site.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
/**
 * Servlet implementation class FileDelete
 */
public class FileDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String UploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String UploadFolderPath = this.getServletContext().getRealPath(UploadFolder);
		String pass_code = request.getParameter("pass_code");
		File userFolder = new File(UploadFolderPath + "\\" + pass_code);
		System.out.println("delete"+ UploadFolderPath + "\\" + pass_code);
		File[] files = userFolder.listFiles();
		PrintWriter pw = response.getWriter();
		// 폴더 삭제
		if (userFolder.exists()) {
			File[] files1 = userFolder.listFiles();
			if (files1 != null) {
				for (File file1 : files) {
					file1.delete(); // 폴더 내 파일 삭제
				}
			}
			userFolder.delete(); // 폴더 삭제
			
			
			//쿠키 삭제
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("TxRequest")) {
						cookie.setMaxAge(0);
						response.addCookie(cookie);
					}
				}
			}
			
			
			//DB에서 삭제
			Connection conn = DBConnectionContextListener.getDBConnection();

			try {
				String sql = "DELETE FROM filelist WHERE pass_code = ?";
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, pass_code);
				int rowsAffected = statement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println(pass_code+"행이 삭제되었습니다.");
				} else {
					System.out.println(pass_code+"와"+"일치하는 행이 없습니다.");
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				DBConnectionContextListener.returnDBConnection(conn);
			}

			
			pw.print("<html>"
					+"<meta charset=\"UTF-8\">"
					+"<body>"
					+"<h2>파일 삭제</h2>"
					+"이전에 업로드한 파일을 삭제하였습니다.<br><br>"
					+"새 파일 전송하러 가기: <a href=\"sendFile.html\">파일 전송하기</a>"
					+"</body>"
					+"</html>");
			
			pw.close();
			
		}pw.print("<html>"
				+"<meta charset=\"UTF-8\">"
				+"<body>"
				+"파일이 없습니다<br><br>"
				+"새 파일 전송하러 가기: <a href=\"sendFile.html\">파일 전송하기</a>"
				+"</body>"
				+"</html>");
		
		pw.close();
	}
	
}
