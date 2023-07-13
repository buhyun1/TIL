package form.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import site.manage.WebUser;

import java.io.IOException;
import java.io.PrintWriter;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		HttpSession session = request.getSession();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user == null) { // check if already logged-in
			String userID = request.getParameter("userID");
			String password = request.getParameter("password");
			// check validity of this (userID, password) pair.
			if (WebUser.isValidUser(userID, password)) {
				user = new WebUser(userID);
				session.setAttribute("user", user);
				out.print("login success!<br>");
				out.println("<script type='text/javascript'>");
				String url = response.encodeURL("index.jsp");
				String toSend = String.format(
					"setTimeout(function() { window.opener.location='%s'; self.close(); }, 1000);",
					url);
				out.println(toSend);
				out.println("</script>");
			} else {
				System.out.println("oops! invalid login trial.");
				out.println("login failed!<br>");
				out.println("<script type='text/javascript'>");
				out.println("setTimeout(function() { location = 'tryLogin.jsp'; }, 1000);");
				out.println("</script>");
			}
		}
		out.println("</body></html>");
		out.close();
	}

}
