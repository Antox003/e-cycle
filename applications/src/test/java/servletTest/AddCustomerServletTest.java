package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO2;
import carzone.com.entity.customer;
import carzone.com.servlet.addcustomer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

public class AddCustomerServletTest {

    private addcustomer servlet;  // La servlet da testare
    private DAO2 daoMock;  // Mock del DAO2

    @Before
    public void setUp() throws Exception {
        servlet = new addcustomer();  // Crea l'istanza della servlet

        daoMock = mock(DAO2.class);  // Crea il mock del DAO2
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostCustomerExists() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Username")).thenReturn("johndoe");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Contact_No")).thenReturn("1234567890");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("John");

        // Simula che il cliente esista già nel sistema
        when(daoMock.checkcust2(any(customer.class))).thenReturn(true);

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a fail.jsp
        verify(response).sendRedirect("fail.jsp");
    }

    @Test
    public void testDoPostCustomerDoesNotExist() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Username")).thenReturn("johndoe");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Contact_No")).thenReturn("1234567890");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("John");

        // Simula che il cliente non esista nel sistema
        when(daoMock.checkcust2(any(customer.class))).thenReturn(false);

        // Simula che l'inserimento nel database vada a buon fine
        when(daoMock.addcustomer(any(customer.class))).thenReturn(1);  // Inserimento riuscito

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie venga aggiunto
        Cookie creg = new Cookie("creg", "creg");
        creg.setMaxAge(10);
        verify(response).addCookie(creg);

        // Verifica che il reindirizzamento vada a customerlogin.jsp
        verify(response).sendRedirect("customerlogin.jsp?Total=100&CusName=John");
    }

    @Test
    public void testDoPostFailedInsertion() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Username")).thenReturn("johndoe");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Contact_No")).thenReturn("1234567890");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("John");

        // Simula che il cliente non esista nel sistema
        when(daoMock.checkcust2(any(customer.class))).thenReturn(false);

        // Simula che l'inserimento nel database fallisca
        when(daoMock.addcustomer(any(customer.class))).thenReturn(0);  // Inserimento fallito

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a fail.jsp
        verify(response).sendRedirect("fail.jsp");
    }
}
