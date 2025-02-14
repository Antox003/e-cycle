package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO;
import carzone.com.entity.Product;
import carzone.com.servlet.deleteProduct;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

public class DeleteProductServletTest {

    private deleteProduct servlet;  // La servlet da testare
    private DAO daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new deleteProduct();  // Crea l'istanza della servlet

        daoMock = mock(DAO.class);  // Crea il mock del DAO
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoPostDeleteSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro carid della richiesta
        when(request.getParameter("carid")).thenReturn("1");

        // Simula il comportamento del DAO (getProductById restituisce un prodotto)
        Product productToDelete = new Product();
        productToDelete.setCarid(1);
        when(daoMock.getProductById(1)).thenReturn(productToDelete);  // Il prodotto esiste

        // Simula che l'eliminazione del prodotto vada a buon fine (deleteProduct ritorna true)
        when(daoMock.deleteProduct(productToDelete)).thenReturn(true);

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a removec.jsp
        verify(response).sendRedirect("removec.jsp");
    }

    @Test
    public void testDoPostDeleteProductNotFound() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro carid della richiesta
        when(request.getParameter("carid")).thenReturn("1");

        // Simula che il prodotto con carid 1 non esista nel database (getProductById ritorna null)
        when(daoMock.getProductById(1)).thenReturn(null);  // Prodotto non trovato

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }

    @Test
    public void testDoPostDeleteFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula il parametro carid della richiesta
        when(request.getParameter("carid")).thenReturn("1");

        // Simula il comportamento del DAO (getProductById restituisce un prodotto)
        Product productToDelete = new Product();
        productToDelete.setCarid(1);
        when(daoMock.getProductById(1)).thenReturn(productToDelete);  // Il prodotto esiste

        // Simula che l'eliminazione del prodotto fallisca (deleteProduct ritorna false)
        when(daoMock.deleteProduct(productToDelete)).thenReturn(false);

        // Esegui il metodo doPost della servlet
        servlet.doPost(request, response);

        // Verifica che il reindirizzamento vada a failc.jsp
        verify(response).sendRedirect("failc.jsp");
    }
}
