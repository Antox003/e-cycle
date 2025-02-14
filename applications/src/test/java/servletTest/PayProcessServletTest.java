package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO4;
import carzone.com.entity.order_details;
import carzone.com.entity.orders;
import carzone.com.servlet.payprocess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class PayProcessServletTest {

    private payprocess servlet;  // La servlet da testare
    private DAO4 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new payprocess();  // Crea l'istanza della servlet

        daoMock = mock(DAO4.class);  // Crea il mock del DAO
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostSuccessEmpty() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("CusName")).thenReturn("empty");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("N2")).thenReturn("Product 1");

        // Simula che il DAO esegua correttamente le operazioni
        when(daoMock.checkcart()).thenReturn(true);  // Carrello non vuoto
        when(daoMock.addOrders(any(orders.class))).thenReturn(1);  // Ordine aggiunto correttamente
        when(daoMock.addOrder_details()).thenReturn(1);  // Dettagli ordine aggiunti
        when(daoMock.deletecart()).thenReturn(1);  // Carrello eliminato correttamente
        when(daoMock.updateOrder_details(any(order_details.class))).thenReturn(1);  // Dettagli ordine aggiornati

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a orders.jsp
        verify(response).sendRedirect("orders.jsp");
    }

    @Test
    public void testDoPostSuccessNotEmpty() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("CusName")).thenReturn("John");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("N2")).thenReturn("Product 1");

        // Simula che il DAO esegua correttamente le operazioni
        when(daoMock.checkcart2(anyString())).thenReturn(true);  // Carrello non vuoto
        when(daoMock.addOrders(any(orders.class))).thenReturn(1);  // Ordine aggiunto correttamente
        when(daoMock.addOrder_details2(anyString())).thenReturn(1);  // Dettagli ordine aggiunti
        when(daoMock.deletecart2(anyString())).thenReturn(1);  // Carrello eliminato correttamente
        when(daoMock.updateOrder_details2(any(order_details.class))).thenReturn(1);  // Dettagli ordine aggiornati

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a orders.jsp
        verify(response).sendRedirect("orders.jsp");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("CusName")).thenReturn("John");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("N2")).thenReturn("Product 1");

        // Simula che il DAO fallisca nell'aggiungere un ordine
        when(daoMock.checkcart2(anyString())).thenReturn(true);  // Carrello non vuoto
        when(daoMock.addOrders(any(orders.class))).thenReturn(0);  // Fallimento nell'aggiungere ordine

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a paymentfail.jsp
        verify(response).sendRedirect("paymentfail.jsp?msgf=Something went wrong. while adding in Orders.");
    }
}
