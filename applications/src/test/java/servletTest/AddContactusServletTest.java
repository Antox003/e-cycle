package carzone.com.servletTest;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import carzone.com.dao.DAO5;
import carzone.com.entity.contactus;
import carzone.com.servlet.addContactus;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AddContactusServletTest {

    private addContactus servlet;  // La servlet da testare
    private DAO5 daoMock;  // Mock del DAO5

    @Before
    public void setUp() throws Exception {
        servlet = new addContactus();  // Crea l'istanza della servlet

        daoMock = mock(DAO5.class);  // Crea il mock del DAO5
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("Name")).thenReturn("John Doe");
        when(request.getParameter("Email_Id")).thenReturn("johndoe@example.com");
        when(request.getParameter("Contact_Number")).thenReturn("1234567890");
        when(request.getParameter("Message")).thenReturn("This is a test message");

        // Simula che l'inserimento nel database vada a buon fine (addContactus ritorna un valore positivo)
        when(daoMock.addContactus(any(contactus.class))).thenReturn(1);

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento sia stato eseguito correttamente
        verify(response).sendRedirect("cupass.jsp");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula parametri non validi
        when(request.getParameter("Name")).thenReturn("");
        when(request.getParameter("Email_Id")).thenReturn("invalid");
        when(request.getParameter("Contact_Number")).thenReturn("123");
        when(request.getParameter("Message")).thenReturn("");

        // Simula che l'inserimento nel database fallisca (addContactus ritorna 0)
        when(daoMock.addContactus(any(contactus.class))).thenReturn(0);

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a cufail.jsp in caso di fallimento
        verify(response).sendRedirect("cufail.jsp");
    }
}
