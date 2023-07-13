<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="jakarta.servlet.http.HttpServletResponse"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.IOException"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.sql.*"%>
<%@ page import="site.manage.*"%>
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
	//System.out.println(UploadFolderPath);
	Enumeration<String> params = request.getParameterNames();

	String FileName = "";
	String pass_code = request.getParameter("pass_code");

	File userFolder = new File(UploadFolderPath + "\\" + pass_code);

	long currentTime = System.currentTimeMillis();
	long CreationTime = userFolder.lastModified();

	boolean isCodeSame = false;
	//PASS_CODE 일치 여부 확인
	Connection conn = DBConnectionContextListener.getDBConnection();

	try {
		String sql = "select count(*) from filelist where pass_code = ?";
		String sqlTime = "select upload_time from filelist where pass_code = ?";

		PreparedStatement psm = conn.prepareStatement(sql);
		PreparedStatement psmTime = conn.prepareStatement(sqlTime);
		psmTime.setString(1, pass_code);
		psm.setString(1, pass_code);

		ResultSet resultSet = psm.executeQuery();

		// 결과 처리
		resultSet.next(); // Count(*) 이므로 next를 통해 행의 값을 얻을 수 있다.
		int count = resultSet.getInt(1); // 얘는 행의 첫번째 열의 값 여기서는 갯수를 얻을 수 있다.
		//pass_code가 일치한다.
		if (count > 0) {
			ResultSet resultSetTime = psmTime.executeQuery();
			resultSetTime.next();
			Timestamp timestamp = resultSetTime.getTimestamp("upload_time");
			CreationTime = timestamp.getTime();
			isCodeSame = true;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}

	long timeDifference = (currentTime - CreationTime) / 1000 / 60;

	if (isCodeSame && timeDifference <5) {
		File[] files = userFolder.listFiles();
		String Firstfile = files[0].getName();
		File userFolderFile = new File(userFolder + "\\" + Firstfile);

		//바로 파일 다운로드 위한 코드
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + Firstfile + "\"");

		// 입력 스트림 및 출력 스트림 생성
		InputStream inputStream = new FileInputStream(userFolderFile);
		OutputStream outputStream = response.getOutputStream();

		// 입력 스트림에서 읽은 데이터를 출력 스트림으로 전송
		byte[] buffer = new byte[4096];
		int bytesRead;
		while ((bytesRead = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, bytesRead);
		}

		// 스트림 닫기
		inputStream.close();
		outputStream.close();

		//out.println("<html>");
		//out.println("<body>");
		//out.println("<div style=\"width: 400px;\" align=\"center\">");
		//out.println("<strong><ins>Download File</ins></strong><br> <br>");
		//out.println("</div>");
		//out.println(
		//"다운 받기: [<a href= " + "upload\\" + pass_code + "\\" + Firstfile + " download>" + Firstfile + "</a>]\r\n");
		//out.println("</body>");
		//out.println("</html>");

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

		// 폴더 삭제
		if (userFolder.exists()) {
			File[] files1 = userFolder.listFiles();
			if (files1 != null) {
		for (File file : files1) {
			file.delete(); // 폴더 내 파일 삭제
		}
			}
			userFolder.delete(); // 폴더 삭제
		}
		//DB에서 삭제
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

	} else {
		out.println("<html>");
		out.println("<body>");
		out.println("<div style=\"width: 400px;\" align=\"center\">");
		out.println(" <strong><ins>Download Error</ins></strong><br><br>");
		out.println("</div>");
		out.println("그런 파일이 없거나, 제한시간 5분이 경과하였습니다.");
		out.println("</body>");
		out.println("</html>");
		//뒤로 돌아가는 버튼 UI추가
		out.println("<br><a href=\"receiveFile.html\">뒤로 돌아가기</a>");

	}
	%>

</body>
</html>