package site.manage;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;

/**
 * Servlet implementation class FileKeep
 */
public class FileKeep extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driverName = "org.mariadb.jdbc.Driver";
	String dbName = "fileservice";
	String driverURL = "jdbc:mariadb://localhost:3306/" + dbName;
	String username = "root";
	String password = "secret";
	Connection conn = DBConnectionContextListener.getDBConnection();
	String code;
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
		response.setContentType("text/html; charset=UTF-8");
		String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String uploadFolderPath = this.getServletContext().getRealPath(uploadFolder);
		String ipAddress = request.getRemoteAddr();
		Enumeration<String> params = request.getParameterNames();
		PrintWriter pw = response.getWriter();
		Timestamp uploadTime = new Timestamp(System.currentTimeMillis());
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(uploadTime.getTime());
		calendar.add(Calendar.MINUTE, 5);
		Timestamp expirationTime = new Timestamp(calendar.getTimeInMillis());
		// 쿠키

		
		Cookie[] cookies = request.getCookies();
		boolean isRequest = false;
		// 쿠키 시간 확인
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("TxRequest")) {
					// 쿠키가 있다면 만료시간 확인
					long cookieExpiration = Long.parseLong(cookie.getValue());
					long fiveMinutesInMillis = 5 * 60 * 1000; // 5분 in 밀리초
					long currentTime = System.currentTimeMillis();
					if (currentTime - cookieExpiration < fiveMinutesInMillis) {
						// 5분보다 작다면 중복 true
						isRequest = true;
						break;
					}
				}
			}
		}
		if(isRequest==false){
			long currentTime = System.currentTimeMillis();
			Cookie txRequestCookie = new Cookie("TxRequest", String.valueOf(currentTime));
			txRequestCookie.setMaxAge(5 * 60); // 5분 유지되는 쿠키 설정
			System.out.println("isRequest"+isRequest);
			isRequest = true;
			response.addCookie(txRequestCookie);
		}
		
		// 중복 쿠키, IP 동일한지 확인 후 폴더 생성
		if (isRequest) {
			if (isIpSame(ipAddress) ) {
				pw.print("<html>" + "<head>" + "<meta charset=\"UTF-8\">" + " <title>파일 전송 횟수 제한</title>"
						+ "</head>" + "<body>" + "<div style=\"width:400px;\" align=\"center\"> "
						+ "<strong>이전에 업로드한 다른 파일이 이미 존재합니다.</strong><br><br>" + "</div>"
						+ "5분간 추가 업로드가 제한됩니다. 5분 후에 다시 시도해 주세요.<br><br>"
						+ "<form action=\"deleteFile.do\" method=\"post\">"
						+ "<input type=\"hidden\" name=\"pass_code\" id=\"pass_code\" value=\"" + code
						+ "\">" + "<label for=\"delete\"><small>이전에 업로드한 파일을 지금 삭제하기:</small></label>"
						+ "<input type=\"submit\" name=\"delete\" id=\"delete\" value=\"Delete\">" + "</form>"
						+ "<br>" + "</body>" + "</html>");
			} else { // IP가 동일하지 않거나, 5분 지나서 시도할때.
				//downloadlink String
				String downloadLink="";

				// 중복되지 않는 ID 생성
				int passCode = generateRandomId();
				while (isIDSame(passCode)) { // passCode 생성 후 동일 한 code가 있는지 데이터베이스에서 확인
					passCode = generateRandomId(); // 동일하다면 다시 생성
				}
				// code번호 담은 파일 생성
				File numFolder = new File(uploadFolderPath + "\\" + String.valueOf(passCode));
				// 폴더 생성
				if (!numFolder.exists()) {
					numFolder.mkdir();
					System.out.println("폴더 생성완료");

				}
				
				// 파일 업로드
				for (Part part : request.getParts()) {
					if ("upfile".equals(part.getName())) {
						String fileName = part.getSubmittedFileName();
						downloadLink="http://localhost:8080/pilotWeb/FileUrlDownload?passcode="+passCode;
						part.write(numFolder + "\\" + fileName);
					}
				}
				// pass_code와 파일 정보를 데이터베이스에 저장
				saveToDatabase(passCode, ipAddress, uploadTime);
				pw.print("<html>" + "<head>" + "<meta charset=\"UTF-8\">" + " <title>파일 전송 성공</title>" + "</head>"
						+ "<body>" + "<div style=\"width:400px;\" align=\"center\"> "
						+ "<strong>파일이 성공적으로 업로드 되었습니다.</strong><br><br>" + "</div>"
						+ "pass_code: <strong style=\"color:red;\">" + passCode + "</strong><br>"
						+ "삭제 예정 시간: <strong style=\"color:red;\">" + expirationTime + "</strong><br>"
						+ "바로 다운 받기: <a href=\"receiveFile.html\">다운 받기</a><br>" + "<br>" + "</body>" 
						+"다운로드 공유 링크:"+downloadLink
						+"</html>");
					
				pw.close();
			}

		} else { // 쿠키가 없을떄 ,txRequest 쿠키 생성



		}

	}

	// 5자리의 랜덤한 숫자 생성 함수
	private static int generateRandomId() {
		Random random = new Random();
		return random.nextInt(90000) + 10000; // 10000 이상 99999 이하의 값 반환
	}

	// DB의 ID와 중복여부 확인 함수
	private boolean isIDSame(int randID) {
		try {
			Statement state = null;
			ResultSet resultSet = null;

			try {
				// 중복 여부 확인을 위한 쿼리 실행
				String sql = "SELECT COUNT(*) FROM filelist WHERE pass_code=" + randID;
				state = conn.createStatement();
				resultSet = state.executeQuery(sql);

				// 결과 처리
				resultSet.next();// Count(*) 이므로 next를 통해 행의 값을 얻을 수 있다.
				int count = resultSet.getInt(1); // 행의 첫번째 열의 값 여기서는 갯수를 얻을 수 있다.
				if (count > 0) {
					System.out.println("랜덤한 ID가 이미 존재합니다.");
					return true;
				} else {
					System.out.println("랜덤한 ID는 사용할 수 있습니다.");
					return false;
				}
			} finally {
				// ResultSet, Statement, Connection 닫기
				if (resultSet != null) {
					resultSet.close();
				}
				if (state != null) {
					state.close();
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // sql 예외 처리
		}
	}

	private void saveToDatabase(int passCode, String ipAddress, Timestamp uploadTime) {
		try {
			
			// System.out.println("driverURL: " + driverURL + ", connected!");
			String sql = "INSERT INTO filelist (pass_code, ip_addr, upload_time) VALUES (?, ?, ?)";
			PreparedStatement stat = conn.prepareStatement(sql);
			stat.setInt(1, passCode); // 1번 자리에 pass code 넣기 / prepare 뒤에 pass code 입력
			stat.setString(2, ipAddress);
			stat.setTimestamp(3, uploadTime);
			stat.executeUpdate();
			stat.close();
			
		}  catch(SQLException e) {
			e.printStackTrace(); // 데이터베이스 저장 실패 처리
		} finally { 
			DBConnectionContextListener.returnDBConnection(conn);
		}
	}

	public boolean isIpSame(String ipAddr) {
		// 현재 시간 가져오기
		long currentTime = new Date().getTime();
		// long fiveMinutesInMillis = 5 * 60 * 1000;
		long fiveMinutesInMillis = 1 * 60 * 1000;

		PreparedStatement stat = null;
		ResultSet rs = null;

		try {
			// JDBC 드라이버 로드
			Class.forName("org.mariadb.jdbc.Driver");

			// SQL 쿼리 작성
			String sql = "SELECT upload_time, pass_code FROM filelist WHERE ip_addr = ? ORDER BY upload_time DESC LIMIT 1";
			// PreparedStatement 객체 생성
			stat = conn.prepareStatement(sql);

			// PreparedStatement 매개변수 설정
			stat.setString(1, ipAddr);

			// SQL 쿼리 실행
			rs = stat.executeQuery();

			// 결과 확인
			if (rs.next()) { 
				//int count = rs.getInt(1);
				Timestamp iptime = rs.getTimestamp(1);
				
				code = rs.getString(2);
				int Stringcode = Integer.parseInt(code);
				// 5분 이내에 동일한 IP 주소로 온 파일 업로드 요청이 있는 경우
				if (iptime != null) {
					long calTime = currentTime - iptime.getTime();
					if (Stringcode > 0) {
						if (calTime < fiveMinutesInMillis) {
							// 거부 처리 코드 작성
							System.out.println("5분내 같은 ip");
							return true;
						} else {
							System.out.println("5분밖의 ip");
							return false;
						}
					}
				}
			}
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false; // 예외 발생 시에도 false를 반환하도록 수정
		} finally {
			// 리소스 해제
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stat != null) {
				try {
					stat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
// 새로고침해야 디비에 들어감
// pw 출력 페이지들 수정