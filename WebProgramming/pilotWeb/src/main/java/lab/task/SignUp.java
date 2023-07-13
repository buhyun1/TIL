package lab.task;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class SignUp
 */
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration<String> params = request.getParameterNames();
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		
		String email = ""; // email 저장 변수
		String pwd = ""; // password 저장 변수
		String remember = ""; // 재입력 password 저장 변수
		Boolean IsEmailOK = true; // Email 체크 확인하는 변수
		Boolean IsPasswordOK = true; // password 체크 확인하는 변수

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			// email 확인
			if ("email".equals(param)) {
				String[] value = request.getParameterValues(param);

				if (!value[0].contains("@")) { // E-mail에 '@' 포함 여부 판단
					IsEmailOK = false; // '@' 없다면 false 저장

				} else {
					email = value[0]; // '@' 있다면 value값 email로 저장

				}
			}
			// 비밀번호 확인
			if ("psw".equals(param)) {
				String value = request.getParameter(param);
				pwd = value; // password 입력값 저장
				pwd = value.toString(); // equal 사용을 위해 문자열 변환
			}
			// 비밀번호 일치 확인
			if ("psw-repeat".equals(param)) {
				String value = request.getParameter(param);
				if (!pwd.equals(value.toString())) { // 저장해둔 password와 value값 비교
					IsPasswordOK = false; // 비밀번호 같지 않음 false 저장
				}

			}
			// 체크박스 확인
			if ("remember".equals(param)) {
				String value = request.getParameter(param);
				remember = value; // 체크 박스 value 저장
				if (remember.equals("on")) { // 저장해둔 remember와 remember value인 on 비교
					remember = "체크됨";
				} else {
					remember = "체크 안됨";
				}

			}

		}
		// 이메일과 비밀번호가 모두 옳으면 가입 페이지 출력
		if (IsEmailOK && IsPasswordOK) {
			pw.println("<div style=\"width:400px;\" align=\"center\">");
			pw.println("<strong>성공적으로 가입처리 되었습니다.</strong><br><br>");
			pw.println("</div>");
			pw.println("이메일 주소: " + email + "<br>");
			pw.println("비밀번호: " + pwd + "<br>");
			pw.println("Remember me: " + remember + "<br>");
		} else {
			// 둘중 하나라도 틀린 것이 있으면 뒤로 가는 링크 출력
			// 위에 비밀번호나 이메일 문구에 입력하니까 두개가 틀린 경우 중복해서 출력되서 수정함
			pw.println("<div style=\"width:400px;\" align=\"center\">");
			pw.println("<strong>입력값에 오류가 있습니다.</strong><br><br>");
			pw.println("</div>");
			if (!IsEmailOK) {
				pw.println("이메일주소가 유효하지 않습니다.<br>");

			}
			if (!IsPasswordOK) {
				pw.println("비밀 번호가 서로 일치하지 않습니다.<br>");

			}
			pw.println("<br><a href=http://localhost:8080/pilotWeb/signup.html>다시 가입신청하러 가기</a>");
			// pilotweb 혹은 pilotWeb으로 파일이름 맞춰주고 html파일로 연결시키면 페이지 연결됨.
		}

		pw.println("</body></html>");
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
