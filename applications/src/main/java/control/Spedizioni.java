package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.SpedizioneDAODataSource;
import model.SpedizioneBean;

@WebServlet("/Spedizioni")
public class Spedizioni extends HttpServlet {
    private SpedizioneDAODataSource spedizioneDAO = new SpedizioneDAODataSource();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<SpedizioneBean> spedizioni = spedizioneDAO.doRetrieveAll(null);
            if (!spedizioni.isEmpty()) {
                SpedizioneBean spedizione = spedizioni.get(0); // Assumi che vogliamo il primo risultato
                request.getSession().setAttribute("Stato", spedizione.getStato());
                request.getSession().setAttribute("Provincia", spedizione.getProvincia());
                request.getSession().setAttribute("Citta", spedizione.getCitta());
                request.getSession().setAttribute("Cap", spedizione.getCap());
                request.getSession().setAttribute("Indirizzo", spedizione.getIndirizzo());
                request.getSession().setAttribute("Civico", spedizione.getCivico());
                
                System.out.println("Stato");
            }
            request.setAttribute("spedizioni", spedizioni);
            RequestDispatcher dispatcher = request.getRequestDispatcher("profilo.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Cannot retrieve spedizioni from database", e);
        }
    }
}
