package form.validation;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ValidateInput
 */
public class ValidateInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateInput() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
	    pw.println("<html><body>");  
	    pw.println("Invalid Input!<br><br>");
	    pw.println("Here goes some reasons...<br><br>");
	    pw.println("<a href='testValidation.html'>go back to input form</a>");
	    pw.println("</body></html>");  
	    pw.close();
	}

}
