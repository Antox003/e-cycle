package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.entity.Product;
import carzone.com.servlet.updateproductquantity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class UpdateProductQuantityServletTest {

    private updateproductquantity servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new updateproductquantity();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("carid")).thenReturn("1");
        when(request.getParameter("carquantity")).thenReturn("10");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarquantity(10);

        // Simula che il DAO esegua correttamente l'aggiornamento (updateproductquantity ritorna 1)
        when(daoMock.updateproductquantity(p)).thenReturn(1);  // Successo

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a updatec.jsp
        verify(response).sendRedirect("updatec.jsp");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("carid")).thenReturn("1");
        when(request.getParameter("carquantity")).thenReturn("10");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarquantity(10);

        // Simula che il DAO fallisca nell'aggiornamento (updateproductquantity ritorna 0)
        when(daoMock.updateproductquantity(p)).thenReturn(0);  // Fallimento

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }

    @Test
    public void testDoPostException() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("carid")).thenReturn("1");
        when(request.getParameter("carquantity")).thenReturn("10");

        // Simula che si verifichi un'eccezione nel DAO
        when(daoMock.updateproductquantity(any(Product.class))).thenThrow(new RuntimeException("Database error"));

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }
}
