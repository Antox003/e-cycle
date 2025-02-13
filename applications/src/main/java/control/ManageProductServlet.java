package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductDAODataSource;
import model.IBeanDAO;

/**
 * Servlet implementation class ProductControl
 */
public class ManageProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static IBeanDAO<ProductBean> productDAO = new ProductDAODataSource();

    public ManageProductServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            if (action != null) {
                if (action.equalsIgnoreCase("read")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.removeAttribute("product");
                    request.setAttribute("product", productDAO.doRetrieveByKey(id));
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    productDAO.doDelete(id);
                } else if (action.equalsIgnoreCase("insert")) {
                    String name = request.getParameter("Nome");
                    String description = request.getParameter("Descrizione");
                    String house = request.getParameter("Casa");
                    double price = Double.parseDouble(request.getParameter("Prezzo"));
                    String display = request.getParameter("Display");
                    String camera = request.getParameter("Fotocamera");
                    String storage = request.getParameter("Archiviazione");
                    String auth = request.getParameter("Autenticazione");
                    String chip = request.getParameter("Chip");
                    String sim = request.getParameter("SIM");
                    String bluetooth = request.getParameter("Bluetooth");
                    String connect = request.getParameter("Connettori");
                    String network = request.getParameter("Rete");
                    String battery = request.getParameter("Batteria");
                    String dimPes = request.getParameter("DimPes");
                    String so = request.getParameter("SO");
                    String water = request.getParameter("Acqua");

                    ProductBean bean = new ProductBean();
                    bean.setNome(name);
                    bean.setDescrizione(description);
                    bean.setCasa(house);
                    bean.setPrezzo(price);
                    bean.setDisplay(display);
                    bean.setFotocamera(camera);
                    bean.setArchiviazione(storage);
                    bean.setAutenticazione(auth);
                    bean.setChip(chip);
                    bean.setSIM(sim);
                    bean.setBluetooth(bluetooth);
                    bean.setConnettori(connect);
                    bean.setRete(network);
                    bean.setBatteria(battery);
                    bean.setDimPes(dimPes);
                    bean.setSO(so);
                    bean.setAcqua(water);
                    
                    productDAO.doSave(bean);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        String sort = request.getParameter("sort");

        try {
            request.removeAttribute("products");
            request.setAttribute("products", productDAO.doRetrieveAll(sort));
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/home");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
