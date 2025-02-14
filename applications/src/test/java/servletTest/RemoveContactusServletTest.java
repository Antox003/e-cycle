package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO5;
import carzone.com.entity.contactus;
import carzone.com.servlet.remove_contactus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class RemoveContactusServletTest {

    private remove_contactus servlet;  // La servlet da testare
    private DAO5 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new remove_contactus();  // Crea l'istanza della servlet

        daoMock = mock(DAO5.class);  // Crea il mock del DAO5
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro id della richiesta
        when(request.getParameter("id")).thenReturn("1");

        // Crea un oggetto contactus da passare al DAO
        contactus c = new contactus();
        c.setId(1);

        // Simula che il DAO esegua correttamente la rimozione (removecont ritorna 1)
        when(daoMock.removecont(c)).thenReturn(1);  // Rimozione riuscita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a table_contactus.jsp
        verify(response).sendRedirect("table_contactus.jsp");
    }

    @Test
    public void testDoGetDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro id della richiesta
        when(request.getParameter("id")).thenReturn("1");

        // Crea un oggetto contactus da passare al DAO
        contactus c = new contactus();
        c.setId(1);

        // Simula che il DAO fallisca nel rimuovere il contatto (removecont ritorna 0)
        when(daoMock.removecont(c)).thenReturn(0);  // Rimozione fallita

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a table_contactus.jsp
        verify(response).sendRedirect("table_contactus.jsp");
    }
}

