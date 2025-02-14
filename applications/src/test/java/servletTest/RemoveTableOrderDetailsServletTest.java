package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO5;
import carzone.com.entity.order_details;
import carzone.com.servlet.removetable_order_details;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class RemoveTableOrderDetailsServletTest {

    private removetable_order_details servlet;  // La servlet da testare
    private DAO5 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new removetable_order_details();  // Crea l'istanza della servlet

        daoMock = mock(DAO5.class);  // Crea il mock del DAO5
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("id")).thenReturn("2021-08-23");
        when(request.getParameter("ie")).thenReturn("car_image.jpg");

        // Crea un oggetto order_details da passare al DAO
        order_details c = new order_details();
        c.setDate("2021-08-23");
        c.setCarimage("car_image.jpg");

        // Simula che il DAO esegua correttamente la rimozione (removeorder_details ritorna 1)
        when(daoMock.removeorder_details(c)).thenReturn(1);  // Rimozione riuscita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a table_order_details.jsp
        verify(response).sendRedirect("table_order_details.jsp");
    }

    @Test
    public void testDoGetDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("id")).thenReturn("2021-08-23");
        when(request.getParameter("ie")).thenReturn("car_image.jpg");

        // Crea un oggetto order_details da passare al DAO
        order_details c = new order_details();
        c.setDate("2021-08-23");
        c.setCarimage("car_image.jpg");

        // Simula che il DAO fallisca nel rimuovere il dettaglio dell'ordine (removeorder_details ritorna 0)
        when(daoMock.removeorder_details(c)).thenReturn(0);  // Rimozione fallita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a table_order_details.jsp
        verify(response).sendRedirect("table_order_details.jsp");
    }
}
