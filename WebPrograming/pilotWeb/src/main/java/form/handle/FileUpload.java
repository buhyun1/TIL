package form.handle;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Receive files from the HTML5 form
		String uploadFolder = this.getServletContext().getInitParameter("uploadFolder");
		String uploadPath = this.getServletContext().getRealPath(uploadFolder);
		int counter = 0;
		for (Part part : request.getParts()) {
			if ("upfile".equals(part.getName())) {
				counter++;
				String fileName = part.getSubmittedFileName();
				part.write(uploadPath + fileName);
				System.out.println("File: " + fileName + " uploaded.");
			}
		}
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.println("<html><body>");
		pw.print(request.getParameter("fname") + "'s " + counter + " files uploaded.");
		pw.print("<br>Please check: " + uploadPath);
		pw.println("</body></html>");
		pw.close();

	}

}
