package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import control.DriverManagerConnectionPool;
import model.UserBean;

@WebServlet("/PagaOra")
public class PagaOra extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PagaOra() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "paga":
                    effettuaPagamento(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Azione non valida");
                    break;
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parametro azione mancante");
        }
    }

    private void effettuaPagamento(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UserBean user = (UserBean) request.getSession().getAttribute("registeredUser");

        if (user == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Utente non autenticato");
            return;
        }

        // Recupera i dati dell'ordine dalla richiesta
        String productIdStr = request.getParameter("productId");
        String quantityStr = request.getParameter("quantity");
        String productName = request.getParameter("productName");

        // Verifica che tutti i parametri siano presenti e non nulli
        if (productIdStr == null || quantityStr == null || productName == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dati dell'ordine mancanti");
            return;
        }

        int productId;
        int quantity;

        try {
            productId = Integer.parseInt(productIdStr);
            quantity = Integer.parseInt(quantityStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato dei dati dell'ordine non valido");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DriverManagerConnectionPool.getConnection();

            // SQL per inserire l'ordine
            String insertOrderQuery = "INSERT INTO ORDINI (Numero_prodotti, Data_acquisto, ID_ACCOUNT, Nome_prodotto) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(insertOrderQuery);
            stmt.setInt(1, quantity);
            stmt.setDate(2, new java.sql.Date(System.currentTimeMillis())); 
            stmt.setInt(3, user.getCode());
            stmt.setString(4, productName);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                // Reindirizza alla pagina di conferma dell'ordine
                response.sendRedirect(request.getContextPath() + "/check.jsp");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore durante l'inserimento dell'ordine");
            }
        } catch (SQLException e) {
            throw new ServletException("Errore durante il salvataggio dell'ordine nel database", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
          
        }
    }

}
