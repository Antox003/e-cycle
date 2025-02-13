package control;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDAODataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/ModificaProdotto")
public class ModificaProdotto extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

    	ProductDAODataSource productDAO = new ProductDAODataSource();
    	ProductBean prodotto = null;
    	try {
    	    prodotto = productDAO.doRetrieveByKey(idProdotto);
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    request.setAttribute("error", "Errore durante il recupero del prodotto.");
    	    request.getRequestDispatcher("/admin.jsp").forward(request, response);
    	    return;
    	}

    	request.setAttribute("prodotto", prodotto);
    	request.getRequestDispatcher("/admin.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
        	Enumeration<String> parameterNames = request.getParameterNames();
        	List<String> parameterNamesList = Collections.list(parameterNames);
        
        	int ID_PRODOTTO = Integer.parseInt(request.getParameter("ID_PRODOTTO"));

            String Nome = request.getParameter("nome");
            String Descrizione = request.getParameter("descrizione");;
            int Prezzo = Integer.parseInt(request.getParameter("prezzo"));
            String Casa = request.getParameter("casa");
            String Display = request.getParameter("display");
            String Fotocamera = request.getParameter("fotocamera");
            String Archiviazione = request.getParameter("archiviazione");
            String Autenticazione = request.getParameter("autenticazione");
            String Chip = request.getParameter("chip");
            String SIM = request.getParameter("sim");
            String Bluetooth = request.getParameter("bluetooth");
            String Connettori = request.getParameter("connettori");
            String Rete = request.getParameter("rete");
            String Batteria = request.getParameter("batteria");
            String DIMPES = request.getParameter("DimPes");
            String SO = request.getParameter("so");
            String Acqua = request.getParameter("acqua");
           
            
            ProductBean prodotto = new ProductBean();
            prodotto.setCode(ID_PRODOTTO);
            prodotto.setNome(Nome);
            prodotto.setDescrizione(Descrizione);
            prodotto.setPrezzo(Prezzo);
            prodotto.setCasa(Casa);
            prodotto.setDisplay(Display);
            prodotto.setFotocamera(Fotocamera);
            prodotto.setArchiviazione(Archiviazione);
            prodotto.setAutenticazione(Autenticazione);
            prodotto.setChip(Chip);
            prodotto.setSIM(SIM);
            prodotto.setBluetooth(Bluetooth);
            prodotto.setConnettori(Connettori);
            prodotto.setRete(Rete);
            prodotto.setBatteria(Batteria);
            prodotto.setDimPes(DIMPES);
            prodotto.setSO(SO);
            prodotto.setAcqua(Acqua);

            
            ProductDAODataSource productDAO = new ProductDAODataSource();
            int editResult = productDAO.doEdit(prodotto);
            
            // Reindirizza alla home.jsp dopo l'aggiornamento
            response.sendRedirect(request.getContextPath() + "/ProductDetail?ID_PRODOTTO="+prodotto.getCode());
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore durante l'aggiornamento del prodotto.");
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }
    }
}