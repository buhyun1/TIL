package form.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import site.manage.ValidUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

/**
 * Servlet implementation class ProcessLogin
 */
public class ProcessLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessLogin() {
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
		//driver 정보
		String driverName = "org.mariadb.jdbc.Driver";
		String dbName = "fruitdb";
		String driverURL = "jdbc:mariadb://localhost:3306/" + dbName;
		String username = "root";
		String password = "secret";
		
		//출력 확인용
		PrintWriter pw = response.getWriter();
		
		//한글 출력
		response.setContentType("text/html; charset=UTF-8");
		
		//db 정보 받아들이기
		int dbid = 0;
		String dbemail = null;
		String dbpassword = null;
		String dbfull_name = null;
		
		int dbid2 = 0;
		String dbemail2 = null;
		String dbpassword2 = null;
		String dbfull_name2 = null;
		
		// request 파라미터 받아오기
		Enumeration<String> params = request.getParameterNames();
		String paramemail = request.getParameter("email");
		String parampassword = request.getParameter("pw");
		//pw.println(paramemail);
		//pw.println(parampassword);
		
		//db 연결
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			System.out.println("driver class not found!");
			System.out.println(e.getStackTrace());
		}
		
		//db에서 user_login 정보 받아오기
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(driverURL, username, password);
				Statement state = conn.createStatement();) {
				String sql = "SELECT * FROM user_login";
				ResultSet resultSet = state.executeQuery(sql);
				
				resultSet.next();// db 첫번째 user 불러오기 
				dbid = resultSet.getInt(1);
				dbemail = resultSet.getString(2);
				dbpassword = resultSet.getString(3);
				dbfull_name = resultSet.getString(4);
				//pw.println(dbid);
				//pw.println(dbemail);
				//pw.println(dbpassword);
				//pw.println(dbfull_name);
				
				resultSet.next();// db 두번째 user 불러오기
				dbid2 = resultSet.getInt(1);
				dbemail2 = resultSet.getString(2);
				dbpassword2 = resultSet.getString(3);
				dbfull_name2 = resultSet.getString(4);
				
		//sql 오류 확인
		} catch (SQLException e) {
			System.out.println("Invalid SQL: check SQL");
			e.printStackTrace();
		}
		
		//이메일과 비밀번호 같은지 확인절차
		if ( paramemail.equals(dbemail) & parampassword.equals(dbpassword) || paramemail.equals(dbemail2) & parampassword.equals(dbpassword2)) {
			//ValidUser 객체 생성
			ValidUser user = (ValidUser) new ValidUser(dbid, dbemail, dbfull_name);
			
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // 세션에 user attribute 추가
			
			//manageOrder.jsp 성공시 이동 경로 제공
			pw.println("<html>\r\n" 
					+ "<body>\r\n"
					+ "Goto: <a href=\"manageOrder.jsp\">manageOrder</a>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			pw.println("same");
		}else {
			//login.html 실패시 이동 경로 제공
			pw.println("<html>\r\n" 
					+ "<body>\r\n"
					+ "Goto: <a href=\"login.html\">login</a>\r\n"
					+ "</body>\r\n"
					+ "</html>");
			pw.println("no");
		}
	}

}