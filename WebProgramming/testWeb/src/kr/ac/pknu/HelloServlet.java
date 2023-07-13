package kr.ac.pknu;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws ServletException, IOException {
		res.setContentType("text/html"); // set the content type
		PrintWriter pw = res.getWriter(); // get the stream to write the data
		// writing a html document to the stream
		pw.println("<html><body>");
		pw.println("Hello Servlet!");
		pw.println("</body></html>");
		pw.close(); // close the stream 
	}
}