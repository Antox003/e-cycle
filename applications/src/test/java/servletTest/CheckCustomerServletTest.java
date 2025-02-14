package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO2;
import carzone.com.entity.customer;
import carzone.com.servlet.checkcustomer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

public class CheckCustomerServletTest {

    private checkcustomer servlet;  // La servlet da testare
    private DAO2 daoMock;  // Mock del DAO2

    @Before
    public void setUp() throws Exception {
        servlet = new checkcustomer();  // Crea l'istanza della servlet

        daoMock = mock(DAO2.class);  // Crea il mock del DAO2
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("John");

        // Simula che l'utente esista nel database (checkcust ritorna true)
        customer c = new customer();
        c.setEmail_Id("johndoe@example.com");
        c.setPassword("password123");

        when(daoMock.checkcust(any(customer.class))).thenReturn(true);  // Successo nel login

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie venga aggiunto con l'email dell'utente
        Cookie cus = new Cookie("cname", "johndoe@example.com");
        cus.setMaxAge(9999);
        verify(response).addCookie(cus);

        // Verifica che il reindirizzamento vada a customerhome.jsp
        verify(response).sendRedirect("customerhome.jsp");
    }

    @Test
    public void testDoPostSuccessShippingAddress() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Password")).thenReturn("password123");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("empty");

        // Simula che l'utente esista nel database (checkcust ritorna true)
        customer c = new customer();
        c.setEmail_Id("johndoe@example.com");
        c.setPassword("password123");

        when(daoMock.checkcust(any(customer.class))).thenReturn(true);  // Successo nel login

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie venga aggiunto con l'email dell'utente
        Cookie cus = new Cookie("cname", "johndoe@example.com");
        cus.setMaxAge(9999);
        verify(response).addCookie(cus);

        // Verifica che il reindirizzamento vada a ShippingAddress.jsp con i parametri corretti
        verify(response).sendRedirect("ShippingAddress.jsp?Total=100&CusName=empty");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Password")).thenReturn("wrongpassword");
        when(request.getParameter("Total")).thenReturn("100");
        when(request.getParameter("CusName")).thenReturn("John");

        // Simula che l'utente non esista nel database (checkcust ritorna false)
        customer c = new customer();
        c.setEmail_Id("johndoe@example.com");
        c.setPassword("wrongpassword");

        when(daoMock.checkcust(any(customer.class))).thenReturn(false);  // Fallimento nel login

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie di errore venga aggiunto
        Cookie up = new Cookie("un", "up");
        up.setMaxAge(10);
        verify(response).addCookie(up);

        // Verifica che il reindirizzamento vada a customerlogin.jsp
        verify(response).sendRedirect("customerlogin.jsp?Total=100&CusName=John");
    }
}

