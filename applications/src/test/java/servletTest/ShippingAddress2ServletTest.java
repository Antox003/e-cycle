package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.servlet.ShippingAddress2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class ShippingAddress2ServletTest {

    private ShippingAddress2 servlet;  // La servlet da testare

    @Before
    public void setUp() throws Exception {
        servlet = new ShippingAddress2();  // Crea l'istanza della servlet
    }

    @Test
    public void testDoPostCashPayment() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("john123");
        when(request.getParameter("cash")).thenReturn("true");  // Pagamento in contante

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a confirmpayment.jsp con i parametri corretti
        verify(response).sendRedirect("confirmpayment.jsp?CName=John Doe&City=New York&Total=100&CusName=john123");
    }

    @Test
    public void testDoPostOnlinePayment() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("john123");
        when(request.getParameter("online")).thenReturn("true");  // Pagamento online

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a confirmonline.jsp con i parametri corretti
        verify(response).sendRedirect("confirmonline.jsp?CName=John Doe&City=New York&Total=100&CusName=john123");
    }

    @Test
    public void testDoPostNoPaymentMethod() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta senza cash o online
        when(request.getParameter("CName")).thenReturn("John Doe");
        when(request.getParameter("City")).thenReturn("New York");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("john123");
        when(request.getParameter("cash")).thenReturn(null);  // Nessun pagamento in contante
        when(request.getParameter("online")).thenReturn(null);  // Nessun pagamento online

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che la servlet non faccia nulla o gestisca il caso in cui non ci sono metodi di pagamento.
        // (Nel tuo codice, non gestisci questa situazione, ma sarebbe una buona idea aggiungere un messaggio o gestione)
        verify(response, never()).sendRedirect(anyString());
    }
}

