package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.entity.customer;
import carzone.com.servlet.deletecustomer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class DeleteCustomerServletTest {

    private deletecustomer servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new deletecustomer();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Name")).thenReturn("John Doe");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");

        // Crea un oggetto customer da passare al DAO
        customer c = new customer();
        c.setName("John Doe");
        c.setEmail_Id("johndoe@example.com");

        // Simula che l'eliminazione del cliente vada a buon fine (deleteCustomer ritorna true)
        when(daoMock.deleteCustomer(any(customer.class))).thenReturn(true);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a managecustomers.jsp
        verify(response).sendRedirect("managecustomers.jsp");
    }

    @Test
    public void testDoGetDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Name")).thenReturn("John Doe");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");

        // Crea un oggetto customer da passare al DAO
        customer c = new customer();
        c.setName("John Doe");
        c.setEmail_Id("johndoe@example.com");

        // Simula che l'eliminazione del cliente fallisca (deleteCustomer ritorna false)
        when(daoMock.deleteCustomer(any(customer.class))).thenReturn(false);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a managecustomers.jsp (anche in caso di fallimento)
        verify(response).sendRedirect("managecustomers.jsp");
    }
}
