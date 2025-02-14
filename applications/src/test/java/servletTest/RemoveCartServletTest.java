package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO2;
import carzone.com.entity.cart;
import carzone.com.servlet.removecart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class RemoveCartServletTest {

    private removecart servlet;  // La servlet da testare
    private DAO2 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new removecart();  // Crea l'istanza della servlet

        daoMock = mock(DAO2.class);  // Crea il mock del DAO2
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("product_image.jpg");

        // Crea un oggetto cart da passare al DAO
        cart d = new cart();
        d.setName("1");
        d.setCarimage("product_image.jpg");

        // Simula che il DAO esegua correttamente la rimozione (removecart ritorna 1)
        when(daoMock.removecart(d)).thenReturn(1);  // Rimozione riuscita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a cart.jsp
        verify(response).sendRedirect("cart.jsp");
    }

    @Test
    public void testDoGetDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("product_image.jpg");

        // Crea un oggetto cart da passare al DAO
        cart d = new cart();
        d.setName("1");
        d.setCarimage("product_image.jpg");

        // Simula che il DAO fallisca nel rimuovere l'articolo (removecart ritorna 0)
        when(daoMock.removecart(d)).thenReturn(0);  // Rimozione fallita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a cart.jsp
        verify(response).sendRedirect("cart.jsp");
    }
}
