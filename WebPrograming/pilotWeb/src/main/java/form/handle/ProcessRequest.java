package form.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class ProcessRequest
 */
public class ProcessRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProcessRequest() {
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
		pw.println("I've received these parameters:<br><br>");

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if ("reason".equals(param)) {
				String[] values = request.getParameterValues(param);
				for (String v : values) {
					pw.append(param + ": " + v + "<br>");
				}
			} else {
				String value = request.getParameter(param);
				pw.append(param + ": " + value + "<br>");
			}
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
		System.out.println("Handled in doPost().");
	}

}
