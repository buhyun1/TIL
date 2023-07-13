package user.manage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.*;

/**
 * Servlet implementation class Unsubscribe
 */
public class Unsubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Unsubscribe() {
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
		// doGet(request, response);
		// 한글 출력이 깨져서 추가한 코드
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter pw = response.getWriter();
		String userFolder = this.getServletContext().getInitParameter("UserFolder");
		String userPath = this.getServletContext().getRealPath(userFolder);

		String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String uploadPath = this.getServletContext().getRealPath(uploadFolder);

		Cookie[] allCookies = request.getCookies(); // null 체크

		Enumeration<String> params = request.getParameterNames();
		String FileName = "";
		String FullName = "";
		String pwd = "";
		String date = "";
		File file = new File(userPath);
		String[] files = file.list();

		boolean IsSameID = false;
		boolean IsChecked = false;
		// 첫번째 줄=비밀번호를 담을 문자열 미리 생성해서 if밖과 while 문에서 회차 관계없이 쓸 수 있도록
		String firstLine = "";
		String secondLine = "";
		// 두번째 줄=이름과 날짜를 담을 문자열 미리 생성

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			// 콘솔 출력 결과 useid, pw, reason 순서로 param으로 들어오기 때문에 기존 코드는 userid 확인하는 코드일 때는
			// pwd변수에 pw값을 저장 못하는 상태였다.

			if ("pw".equals(param)) {
				String value = request.getParameter(param);
				pwd = value;
				// pw.println(pwd);
				// "pwd" 문자열을 pwd 변수로 수정
				// 비밀번호를 받아온 뒤 미리 받아둔 문자열(파일속 비밀번호)과 비교
				if (firstLine.equals(pwd)) {
					IsChecked = true;
				}

			} else if ("userid".equals(param)) {

				String value = request.getParameter(param);
				for (String names : files) {
					// 입력한 이메일 파일이 있는경우
					if (names.equals(value)) {
						IsSameID = true;
						File file1 = new File(userPath + "\\" + names);

						FileReader fileReader1 = new FileReader(file1);
						BufferedReader bufReader = new BufferedReader(fileReader1);
						firstLine = bufReader.readLine();
						secondLine = bufReader.readLine();
						// pw.println(firstLine);
						bufReader.close();
						// 파일 삭제를 위해 파일이름 저장
						FileName = names;
						// fullname도 저장
//						if ( firstLine.equals(pwd) ) {
//							IsChecked = true;
//						}
					}

				}
				FileName = value;
			}

			else if ("fullname".equals(param)) {
				String value = request.getParameter(param);
				FullName = value;
			} else if ("birthdate".equals(param)) {
				String value = request.getParameter(param);
				date = value;

			}

		}
		// While 문 바깥=> 파라미터를 다 읽고 나서
		// 이메일과 비밀번호까지 같은 경우
		if (IsSameID && IsChecked) {
			int idx = secondLine.indexOf(";");
			String name1 = secondLine.substring(0, idx);

			try {
				// 파일을 삭제시키기

				File file2 = new File(userPath + "\\" + FileName);
				// pw.print(FileName);
				file2.delete();

				File folder = new File(uploadPath + "\\" + FileName);

				while (folder.exists()) {
					File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기

					for (int j = 0; j < folder_list.length; j++) {
						folder_list[j].delete(); // 파일 삭제
						// System.out.println("파일이 삭제되었습니다.");

					}

					if (folder_list.length == 0 && folder.isDirectory()) {
						folder.delete(); // 대상폴더 삭제

						if (allCookies != null) {
							for (Cookie c : allCookies) {
								if (c.getName().equals("LoginOK")) {
									c.setMaxAge(0);
									response.addCookie(c);
									System.out.print("hi");
								}
							}

						}
					}

				}

			}

			catch (Exception e) {

				pw.println("<html>\r\n" + "<body>\r\n" + "<h2>Oops!</h2>\r\n" + "\r\n"
						+ "Account deletion failed.<br><br>\r\n" + "ID: <strong style=\"color:blue;\">" + FileName
						+ "</strong><br><br>\r\n"
						+ "Reason: <strong style=\"color:red;\">failed delete file</strong><br><br>\r\n" + "\r\n"
						+ "Goto: <a href=\"unsubscribe.html\">unsubscribe</a>\r\n" + "\r\n" + "</body>\r\n"
						+ "</html>");
				return;
			}
			pw.print("<html>\r\n" + "<body>\r\n" + "<h2>Good bye " + name1 + "!</h2>\r\n" + "\r\n"
					+ "Your account successfully deleted.<br><br>\r\n" + "ID: <strong style=\"color:blue;\">" + FileName
					+ "</strong><br><br>\r\n" + "\r\n" + "Goto: <a href=\"subscribe.html\">subscribe</a>\r\n" + "\r\n"
					+ "<script type=\"text/javascript\">\r\n"
					+ "  setTimeout(function(){ window.location.href=\"subscribe.html\" }, 3000);\r\n" + "</script>\r\n"
					+ "</body>\r\n" + "</html>");
		}
		// 이메일은 같지만 비밀번호는 다른 경우
		else if (IsSameID) {

			if (!IsChecked) {
				// 비밀번호가 다릅니다.
				pw.println("<html>\r\n" + "<body>\r\n" + "<h2>Oops!</h2>\r\n" + "\r\n"
						+ "Account deletion failed.<br><br>\r\n" + "ID: <strong style=\"color:blue;\">" + FileName
						+ "</strong><br><br>\r\n"
						+ "Reason: <strong style=\"color:red;\">wrong password.</strong><br><br>\r\n" + "\r\n"
						+ "Goto: <a href=\"unsubscribe.html\">unsubscribe</a>\r\n" + "\r\n" + "</body>\r\n"
						+ "</html>");

			}

		}
		// 이메일이 다른 경우
		else if (!IsSameID) {
			pw.println(
					"<html>\r\n" + "<body>\r\n" + "<h2>Oops!</h2>\r\n" + "\r\n" + "Account deletion failed.<br><br>\r\n"
							+ "ID: <strong style=\"color:blue;\">" + FileName + "</strong><br><br>\r\n"
							+ "Reason: <strong style=\"color:red;\">no such user.</strong><br><br>\r\n" + "\r\n"
							+ "Goto: <a href=\"unsubscribe.html\">unsubscribe</a>\r\n" + "\r\n"

							+ "</body>\r\n" + "</html>");
		}
	}
}
