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
import java.util.Calendar;

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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>"); 
		
		HttpSession session = request.getSession();
		WebUser user = (WebUser) session.getAttribute("user");
		if (user == null) {  // check if already logged-in
			String userID = request.getParameter("userID");
			String password = request.getParameter("password");
		    
			// check validity of this (userID, password) pair.
			if (WebUser.isValidUser(userID, password)) {
				user = new WebUser(userID);
				session.setAttribute("user", user);
				out.print("login success!<br>");
				out.println("<script type='text/javascript'>");
//				String url = response.encodeURL("index.jsp");
//				String toSend = String.format("setTimeout(function() { window.opener.location='%s'; self.close(); }, 1000);", url);
//				out.println(toSend);
				out.println("setTimeout(function() { window.opener.location.reload(); self.close(); }, 1000);");
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

/*
 * <%@ page contentType="text/html; charset=UTF-8" %> <%@ page
 * import="com.example.LoginDAO" %>
 * 
 * <% String username = request.getParameter("username"); String password =
 * request.getParameter("password");
 * 
 * LoginDAO loginDAO = new LoginDAO(); boolean isValid =
 * loginDAO.validate(username, password);
 * 
 * if (isValid) { // 로그인 성공 session.setAttribute("username", username);
 * response.sendRedirect("main.jsp"); } else { // 로그인 실패
 * out.print("<script>alert('로그인 실패!');</script>");
 * out.print("<script>window.close();</script>"); } %>
 */


/*
 * <!DOCTYPE html> <html> <head> <title>Index Page</title> </head> <body>
 * <h1>Welcome to Index Page</h1> <table border="1"> <tr> <th>Fruit</th>
 * <th>Price</th> <th>Order Qty</th> </tr> <tr> <td>Apple</td> <td>$1.00</td>
 * <td><input type="number" name="appleQty" value="0"></td> </tr> <tr>
 * <td>Banana</td> <td>$0.50</td> <td><input type="number" name="bananaQty"
 * value="0"></td> </tr> <tr> <td>Orange</td> <td>$0.75</td> <td><input
 * type="number" name="orangeQty" value="0"></td> </tr> </table> <br> <button
 * onclick="checkOrder()">To my basket</button>
 * 
 * <script> function checkOrder() { var fruits = ['Apple', 'Banana', 'Orange'];
 * var orderQty = [document.getElementsByName('appleQty')[0].value,
 * document.getElementsByName('bananaQty')[0].value,
 * document.getElementsByName('orangeQty')[0].value];
 * 
 * var hasOrder = false; for (var i = 0; i < fruits.length; i++) { if
 * (orderQty[i] > 0) { document.cookie = fruits[i] + "=" + orderQty[i]; hasOrder
 * = true; } }
 * 
 * if (hasOrder) { alert('Your order has been added to the basket.'); } else {
 * alert('There is no item in the basket.'); } } </script> </body> </html>
 */