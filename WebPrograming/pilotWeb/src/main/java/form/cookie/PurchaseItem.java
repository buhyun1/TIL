package form.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class PurchaseItem
 */
public class PurchaseItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PurchaseItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    		Cookie[] allCookies = request.getCookies(); //null 체크
    		for (Cookie c: allCookies) {
    		if ("apple".equalsIgnoreCase(c.getName())) {
    		c.setMaxAge(0);
    		response.addCookie(c);
    		}
    		if ("orange".equalsIgnoreCase(c.getName())) {
    		c.setMaxAge(0);
    		response.addCookie(c);
    		}
    		if ("pear".equalsIgnoreCase(c.getName())) {
    		c.setMaxAge(0);
    		response.addCookie(c);
    		}
    		}
    		System.out.println("PurchaseItem.doPost(): order completed.");
    		response.sendRedirect("startHere.jsp");
    		}


}
