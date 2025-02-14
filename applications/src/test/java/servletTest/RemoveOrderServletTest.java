package carzone.com.servletTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;

import carzone.com.dao.DAO2;
import carzone.com.entity.orders;
import carzone.com.servlet.removeorders;

public class RemoveOrderServletTest {

    private removeorders servlet;  // La servlet da testare
    private DAO2 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new removeorders();  // Crea l'istanza della servlet

        daoMock = mock(DAO2.class);  // Crea il mock del DAO2
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro id della richiesta
        when(request.getParameter("id")).thenReturn("1");

        // Crea un oggetto orders da passare al DAO
        orders od = new orders();
        od.setOrder_Id(1);

        // Simula che il DAO esegua correttamente la rimozione (removeorders ritorna 1)
        when(daoMock.removeorders(od)).thenReturn(1);  // Rimozione riuscita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a orders.jsp
        verify(response).sendRedirect("orders.jsp");
    }

    @Test
    public void testDoGetDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro id della richiesta
        when(request.getParameter("id")).thenReturn("1");

        // Crea un oggetto orders da passare al DAO
        orders od = new orders();
        od.setOrder_Id(1);

        // Simula che il DAO fallisca nel rimuovere l'ordine (removeorders ritorna 0)
        when(daoMock.removeorders(od)).thenReturn(0);  // Rimozione fallita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a orders.jsp
        verify(response).sendRedirect("orders.jsp");
    }
}