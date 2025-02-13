package control;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Boolean isAdmin = (Boolean) request.getSession().getAttribute("isAdmin");
		if (isAdmin == null){	
		    response.sendRedirect(request.getContextPath() + "/login.jsp"); 
		    return;
		}
		
		HttpSession session = request.getSession();
        session.removeAttribute("registeredUser");
        session.removeAttribute("role");
        session.invalidate();
		response.sendRedirect(request.getContextPath() + "/login.jsp");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private static final long serialVersionUID = 1L;
	
}
