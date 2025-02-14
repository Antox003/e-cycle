package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO2;
import carzone.com.entity.usermaster;
import carzone.com.servlet.checkadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

public class CheckAdminServletTest {

    private checkadmin servlet;  // La servlet da testare
    private DAO2 daoMock;  // Mock del DAO2

    @Before
    public void setUp() throws Exception {
        servlet = new checkadmin();  // Crea l'istanza della servlet

        daoMock = mock(DAO2.class);  // Crea il mock del DAO2
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta (username e password corretti)
        when(request.getParameter("Username")).thenReturn("admin");
        when(request.getParameter("Password")).thenReturn("admin123");

        // Simula il comportamento del DAO (checkadmin ritorna true per successo)
        usermaster um = new usermaster();
        um.setName("admin");
        um.setPassword("admin123");

        when(daoMock.checkadmin(any(usermaster.class))).thenReturn(true);  // Successo nel login

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie venga aggiunto con il nome dell'amministratore
        Cookie u = new Cookie("tname", "admin");
        u.setMaxAge(9999);
        verify(response).addCookie(u);

        // Verifica che il reindirizzamento vada a adminhome.jsp
        verify(response).sendRedirect("adminhome.jsp");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta (username e password errati)
        when(request.getParameter("Username")).thenReturn("admin");
        when(request.getParameter("Password")).thenReturn("wrongpassword");

        // Simula il comportamento del DAO (checkadmin ritorna false per errore)
        usermaster um = new usermaster();
        um.setName("admin");
        um.setPassword("wrongpassword");

        when(daoMock.checkadmin(any(usermaster.class))).thenReturn(false);  // Fallimento nel login

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il cookie di errore venga aggiunto
        Cookie up = new Cookie("un", "up");
        up.setMaxAge(10);
        verify(response).addCookie(up);

        // Verifica che il reindirizzamento vada a adminlogin.jsp
        verify(response).sendRedirect("adminlogin.jsp");
    }
}
