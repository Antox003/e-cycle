package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CartBean;
import model.CartModel;
import model.ProductBean;
import model.UserBean;

/**
 * Servlet implementation class CartControl
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CartControl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "diminuisci":
                    modificaQuantita(request, response, -1);
                    break;
                case "aumenta":
                    modificaQuantita(request, response, 1);
                    break;
                case "rimuovere":
                    rimuoviProdotto(request, response);
                    break;
                case "aggiungi":
                    aggiungiProdotto(request, response);
                    break;
                case "svuotare":
                    svuotaCarrello(request, response);
                    break;
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
		// TODO Auto-generated method stubCartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
        UserBean user = (UserBean) request.getSession().getAttribute("registeredUser");
        CartModel cartModel = new CartModel();
        Ordini orderModel = new Ordini();
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");


        if (carrello != null && user != null) {
            try {
                // Crea l'ordine utilizzando il modello dei carrelli
                cartModel.doOrder(carrello, user);

                // Salva l'ordine nel database utilizzando il modello degli ordini
                orderModel.saveOrder(carrello, user);

                // Pulisce il carrello dopo aver completato l'ordine
                carrello.clear();
                request.getSession().setAttribute("carrello", carrello);

                // Reindirizza alla pagina di conferma dell'ordine o ad un'altra pagina appropriata
                response.sendRedirect(request.getContextPath() + "/check.jsp");
            } catch (SQLException e) {
                throw new ServletException("Errore durante il salvataggio dell'ordine nel database", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Impossibile completare l'ordine: carrello o utente non validi");
        }
    }
		

	private void modificaQuantita(HttpServletRequest request, HttpServletResponse response, int modifica) throws ServletException, IOException {
        try {
            String codiceStr = request.getParameter("codice");
            int codice = Integer.parseInt(codiceStr);
            CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");

            if (carrello != null) {
                ProductBean prodotto = carrello.retriveByKey(codice);
                if (prodotto != null) {
                    if (modifica < 0) {
                        prodotto.decreaseQuantity();
                        if (prodotto.getQuantity() == 0) {
                            carrello.removeItem(codice);
                        }
                    } else if (modifica > 0 && prodotto.getQuantity() < 99) {
                        prodotto.addQuantity();
                    }
                    request.getSession().setAttribute("carrello", carrello);
                }
            }
            request.getRequestDispatcher("/carrello.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Codice prodotto non valido");
        }
    }

    private void rimuoviProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String codiceStr = request.getParameter("codice");
            int codice = Integer.parseInt(codiceStr);
            CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");

            if (carrello != null && !carrello.isEmpty()) {
                carrello.removeItem(codice);
                request.getSession().setAttribute("carrello", carrello);
            }
            request.getRequestDispatcher("/carrello.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Codice prodotto non valido");
        }
    }

    private void aggiungiProdotto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String codiceStr = request.getParameter("codice");
            int codice = Integer.parseInt(codiceStr);
            CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");
            CartModel model = new CartModel();

            if (carrello == null) {
                carrello = new CartBean();
            }

            carrello = model.aggiungiAlCarrello(carrello, codice);
            request.getSession().setAttribute("carrello", carrello);
            request.getRequestDispatcher("/carrello.jsp").forward(request, response);  // Forward alla pagina del carrello
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Codice prodotto non valido");
        } catch (SQLException e) {
            throw new ServletException("Errore nel database", e);
        }
    }

    private void svuotaCarrello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CartBean carrello = (CartBean) request.getSession().getAttribute("carrello");

        if (carrello != null) {
            carrello.clear();
            request.getSession().setAttribute("carrello", carrello);
        }
        request.getRequestDispatcher("/carrello.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}


