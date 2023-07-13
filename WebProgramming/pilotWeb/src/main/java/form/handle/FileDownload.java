package form.handle;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownload
 */
public class FileDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		String fileName = request.getParameter("file");
		String filePath = context.getRealPath("/image/" + fileName);
		System.out.println("file path = " + filePath);

		File toDownload = new File(filePath);
		if (toDownload.exists()) {
			String mimeType = context.getMimeType(filePath);
			System.out.println("mimeType = " + mimeType);

			response.setContentType((mimeType != null) ? mimeType : "application/octet-stream");
			response.setContentLength((int) toDownload.length());

			String encFileName = URLEncoder.encode(toDownload.getName(), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + encFileName + "\"");
			//response.setHeader("Content-Disposition", "attachment; filename=\"" + toDownload.getName() + "\"");
			
			try (ServletOutputStream outS = response.getOutputStream();
					FileInputStream fin = new FileInputStream(toDownload)) {
				byte[] buffer = new byte[1024];
				int read = 0;
				while ((read = fin.read(buffer)) != -1) {
					outS.write(buffer, 0, read);
				}
				fin.close();
				outS.flush();
				outS.close();
			}
		} else {
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("file not exist!");
		}
	}
}
