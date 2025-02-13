package control;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDAODataSource;

@WebServlet("/ProductDetail")
public class ProductDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera l'ID del prodotto dalla richiesta HTTP
        int productId = Integer.parseInt(request.getParameter("ID_PRODOTTO"));

        // Istanzia il DAO per i prodotti
        ProductDAODataSource dao = new ProductDAODataSource();
        ProductBean product = null;
        try {
            // Recupera il prodotto dal database utilizzando l'ID
            product = dao.doRetrieveByKey(productId);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante il recupero del prodotto.");
            return;
        }
        
        // Se il prodotto non è stato trovato, restituisci un errore 404
        if (product == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato.");
            return;
        }
        
        // Imposta l'attributo 'product' nella richiesta per la pagina JSP
        request.setAttribute("product", product); 
        // Inoltra alla pagina JSP per i dettagli del prodotto
        request.getRequestDispatcher("prodotto.jsp").forward(request, response);
    }
}
