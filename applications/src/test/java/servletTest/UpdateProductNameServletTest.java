package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.entity.Product;
import carzone.com.servlet.updateproductname;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class UpdateProductNameServletTest {

    private updateproductname servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new updateproductname();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("carid")).thenReturn("1");
        when(request.getParameter("carname")).thenReturn("New Car Name");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarname("New Car Name");

        // Simula che il DAO esegua correttamente l'aggiornamento (updateaproductname ritorna 1)
        when(daoMock.updateaproductname(p)).thenReturn(1);  // Successo

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
        when(request.getParameter("carname")).thenReturn("New Car Name");

        // Crea un oggetto Product da passare al DAO
        Product p = new Product();
        p.setCarid(1);
        p.setCarname("New Car Name");

        // Simula che il DAO fallisca nell'aggiornamento (updateaproductname ritorna 0)
        when(daoMock.updateaproductname(p)).thenReturn(0);  // Fallimento

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
        when(request.getParameter("carname")).thenReturn("New Car Name");

        // Simula che si verifichi un'eccezione nel DAO
        when(daoMock.updateaproductname(any(Product.class))).thenThrow(new RuntimeException("Database error"));

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }
}
