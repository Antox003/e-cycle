package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.entity.Product;
import carzone.com.servlet.updateproductprice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class UpdateProductPriceServletTest {

    private updateproductprice servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new updateproductprice();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("carid")).thenReturn("1");
        when(request.getParameter("carprice")).thenReturn("20000");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarprice(20000);

        // Simula che il DAO esegua correttamente l'aggiornamento (updateproductprice ritorna 1)
        when(daoMock.updateproductprice(p)).thenReturn(1);  // Successo

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
        when(request.getParameter("carprice")).thenReturn("20000");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarprice(20000);

        // Simula che il DAO fallisca nell'aggiornamento (updateproductprice ritorna 0)
        when(daoMock.updateproductprice(p)).thenReturn(0);  // Fallimento

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
        when(request.getParameter("carprice")).thenReturn("20000");

        // Simula che si verifichi un'eccezione nel DAO
        when(daoMock.updateproductprice(any(Product.class))).thenThrow(new RuntimeException("Database error"));

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }
}
