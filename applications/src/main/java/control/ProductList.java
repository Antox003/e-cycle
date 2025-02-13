package control;

import java.io.IOException;

import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDAODataSource;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/home")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAODataSource productDAO = new ProductDAODataSource();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 try {
	            List<ProductBean> productList = productDAO.doRetrieveAll("Nome");
	            request.setAttribute("productList", productList);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Database error: " + e.getMessage());
	        }

	        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
	        dispatcher.forward(request, response);
	        
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
