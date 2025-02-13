package control;

import model.ProductDAODataSource;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EliminaProdotto")
public class EliminaProdotto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAODataSource productDAO = new ProductDAODataSource();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recupera l'id del prodotto dalla richiesta
        String idProdottoParam = request.getParameter("idProdotto");
        int idProdotto = Integer.parseInt(idProdottoParam);

        try {
            // Tenta di eliminare il prodotto
            boolean deleted = productDAO.doDelete(idProdotto);

            if (deleted) {
                // Prodotto eliminato con successo
                request.getSession().setAttribute("message", "Prodotto eliminato con successo.");
            } else {
                // Prodotto non trovato o non eliminato
                request.getSession().setAttribute("message", "Errore: Prodotto non trovato o non eliminato.");
            }
        } catch (SQLException e) {
            // Gestione dell'eccezione SQL
            e.printStackTrace();
            request.getSession().setAttribute("error", "Errore durante l'eliminazione del prodotto: " + e.getMessage());
        }

        // Reindirizza alla pagina amministratore.jsp
        response.sendRedirect(request.getContextPath() + "/admin.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
