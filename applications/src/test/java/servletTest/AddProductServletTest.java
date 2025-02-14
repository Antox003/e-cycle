package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.servlet.addproduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class AddProductServletTest {

    private addproduct servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new addproduct();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta per l'auto
        when(request.getParameter("ProductName")).thenReturn("Fiat 500");
        when(request.getParameter("ProductDescription")).thenReturn("Description of Fiat 500");
        when(request.getParameter("Price")).thenReturn("15000");  // Prezzo dell'auto
        when(request.getParameter("Quantity")).thenReturn("10");  // Quantità dell'auto
        when(request.getParameter("CarImage")).thenReturn("fiat500.jpg");  // Immagine dell'auto
        when(request.getParameter("BrandID")).thenReturn("1");  // ID del marchio Fiat
        when(request.getParameter("CategoryID")).thenReturn("1");  // ID della categoria (ad esempio Berlina)

        // Simula che l'inserimento nel database vada a buon fine (addproduct ritorna 1)
        when(daoMock.addproduct(request)).thenReturn(1);  // Successo

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a passc.jsp
        verify(response).sendRedirect("passc.jsp");
    }

    @Test
    public void testDoPostFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula parametri non validi (ad esempio, prezzo non valido)
        when(request.getParameter("ProductName")).thenReturn("Fiat 500");
        when(request.getParameter("ProductDescription")).thenReturn("Description of Fiat 500");
        when(request.getParameter("Price")).thenReturn("invalid");  // Prezzo non valido
        when(request.getParameter("Quantity")).thenReturn("10");
        when(request.getParameter("CarImage")).thenReturn("fiat500.jpg");
        when(request.getParameter("BrandID")).thenReturn("1");
        when(request.getParameter("CategoryID")).thenReturn("1");

        // Simula che l'inserimento nel database fallisca (addproduct ritorna 0)
        when(daoMock.addproduct(request)).thenReturn(0);  // Fallimento

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }

}
