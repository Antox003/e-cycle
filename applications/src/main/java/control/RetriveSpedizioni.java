package control;

import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SpedizioneBean;
import model.SpedizioneDAODataSource;

import model.PagamentoBean;
import model.PagamentoDAODataSource;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profilo")
public class RetriveSpedizioni extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SpedizioneDAODataSource spedizioneDAO = new SpedizioneDAODataSource();
    private PagamentoDAODataSource pagamentoDAO = new PagamentoDAODataSource();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("ID_ACCOUNT") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int idAccount = (int) session.getAttribute("ID_ACCOUNT");

        try {
            SpedizioneBean spedizione = spedizioneDAO.doRetrieveByKey(idAccount);
            request.setAttribute("spedizione", spedizione);
            
            PagamentoBean pagamento = pagamentoDAO.doRetrieveByKey(idAccount);
            request.setAttribute("pagamento", pagamento);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve spedizione from database", e);
        }

        request.getRequestDispatcher("/profilo.jsp").forward(request, response);
    }

}
