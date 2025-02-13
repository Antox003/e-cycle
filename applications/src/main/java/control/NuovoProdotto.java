package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductBean;
import model.ProductDAODataSource; 

@WebServlet("/NuovoProdotto")
public class NuovoProdotto extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	/*
    	int idProdotto = Integer.parseInt(request.getParameter("idProdotto"));

    	ProductDAODataSource productDAO = new ProductDAODataSource();
    	ProductBean prodotto = null;
    	try {
    	    prodotto = productDAO.doRetrieveByKey(idProdotto);
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	    request.setAttribute("error", "Errore durante il recupero del prodotto.");
    	    request.getRequestDispatcher("/home.jsp").forward(request, response);
    	    return;
    	}

    	request.setAttribute("prodotto", prodotto);
    	*/
    	request.getRequestDispatcher("/admin.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        	
        	Enumeration<String> parameterNames = request.getParameterNames();
        	List<String> parameterNamesList = Collections.list(parameterNames);
        
        	

            String nome = request.getParameter("nome");
            String descrizione = request.getParameter("descrizione");
            double prezzo = Double.parseDouble(request.getParameter("prezzo"));
            String casa = request.getParameter("casa");
            String display = request.getParameter("display");
            String fotocamera = request.getParameter("fotocamera");
            String archiviazione = request.getParameter("archiviazione");
            String autenticazione = request.getParameter("autenticazione");
            String chip = request.getParameter("chip");
            String sim = request.getParameter("sim");
            String bluetooth = request.getParameter("bluetooth");
            String connettori = request.getParameter("connettori");
            String rete = request.getParameter("rete");
            String batteria = request.getParameter("batteria");
            String DimPes = request.getParameter("DimPes");
            String so = request.getParameter("so");
            String acqua = request.getParameter("acqua");
            
            
            ProductBean prodotto = new ProductBean();
         
            prodotto.setNome(nome);
            prodotto.setDescrizione(descrizione);
            prodotto.setPrezzo(prezzo);
            prodotto.setCasa(casa);
            prodotto.setDisplay(display);
            prodotto.setFotocamera(fotocamera);
            prodotto.setArchiviazione(archiviazione);
            prodotto.setAutenticazione(autenticazione);
            prodotto.setChip(chip);
            prodotto.setSIM(sim);
            prodotto.setBluetooth(bluetooth);
            prodotto.setConnettori(connettori);
            prodotto.setRete(rete);
            prodotto.setBatteria(batteria);
            prodotto.setDimPes(DimPes);
            prodotto.setSO(so);
            prodotto.setAcqua(acqua);
            
            ProductDAODataSource productDAO = new ProductDAODataSource();
            productDAO.doSave(prodotto);
            
            // Reindirizza alla home.jsp dopo l'aggiornamento
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Errore durante l'aggiornamento del prodotto.");
            request.getRequestDispatcher("/admin.jsp").forward(request, response);
        }
    }

}
