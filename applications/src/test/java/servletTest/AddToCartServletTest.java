package carzone.com.servletTest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import carzone.com.dao.DAO3;
import carzone.com.entity.cart;
import carzone.com.servlet.addtocart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import static org.mockito.Mockito.*;

public class AddToCartServletTest {

    private addtocart servlet;  // La servlet da testare
    private DAO3 daoMock;  // Mock del DAO

    @Before
    public void setUp() throws Exception {
        servlet = new addtocart();  // Crea l'istanza della servlet

        daoMock = mock(DAO3.class);  // Crea il mock del DAO3
        servlet.setDao(daoMock);  // Inietta il mock del DAO nella servlet
    }

    @Test
    public void testDoGetAddProductSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("N")).thenReturn("John");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("Fiat");
        when(request.getParameter("ig")).thenReturn("Fiat 500");
        when(request.getParameter("ih")).thenReturn("15000");  // Prezzo dell'auto
        when(request.getParameter("ii")).thenReturn("2");  // Quantità dell'auto
        when(request.getParameter("ij")).thenReturn("fiat500.jpg");  // Immagine dell'auto

        // Crea un oggetto cart da aggiungere
        cart c = new cart();
        c.setName("John");
        c.setBname("1");  // Marchio Fiat
        c.setCname("Berlina");  // Categoria Berlina
        c.setCarname("Fiat 500");
        c.setCarprice(15000);
        c.setCarquantity(2);
        c.setCarimage("fiat500.jpg");

        // Simula che il prodotto non esista ancora nel carrello (checkaddtocartnull ritorna false)
        when(daoMock.checkaddtocartnull(any(cart.class))).thenReturn(false);

        // Simula che l'aggiunta al carrello vada a buon fine (addtocartnull ritorna 1)
        when(daoMock.addtocartnull(any(cart.class))).thenReturn(1);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il cookie venga aggiunto correttamente
        Cookie cartCookie = new Cookie("cart", "cartt");
        cartCookie.setMaxAge(10);
        verify(response).addCookie(cartCookie);

        // Verifica che il reindirizzamento vada a cart.jsp
        verify(response).sendRedirect("cart.jsp");
    }

    @Test
    public void testDoGetAddProductFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("N")).thenReturn("John");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("Fiat");
        when(request.getParameter("ig")).thenReturn("Fiat 500");
        when(request.getParameter("ih")).thenReturn("15000");  // Prezzo dell'auto
        when(request.getParameter("ii")).thenReturn("2");  // Quantità dell'auto
        when(request.getParameter("ij")).thenReturn("fiat500.jpg");  // Immagine dell'auto

        // Simula che il prodotto non esista ancora nel carrello (checkaddtocartnull ritorna false)
        when(daoMock.checkaddtocartnull(any(cart.class))).thenReturn(false);

        // Simula che l'aggiunta al carrello fallisca (addtocartnull ritorna 0)
        when(daoMock.addtocartnull(any(cart.class))).thenReturn(0);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a selecteditemc.jsp in caso di fallimento
        verify(response).sendRedirect("selecteditemc.jsp");
    }

    @Test
    public void testDoGetUpdateProductSuccess() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("N")).thenReturn("John");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("Fiat");
        when(request.getParameter("ig")).thenReturn("Fiat 500");
        when(request.getParameter("ih")).thenReturn("15000");  // Prezzo dell'auto
        when(request.getParameter("ii")).thenReturn("3");  // Quantità dell'auto aggiornata
        when(request.getParameter("ij")).thenReturn("fiat500.jpg");  // Immagine dell'auto

        // Simula che il prodotto esista già nel carrello (checkaddtocartnull ritorna true)
        when(daoMock.checkaddtocartnull(any(cart.class))).thenReturn(true);

        // Simula che l'aggiornamento del prodotto nel carrello vada a buon fine (updateaddtocartnull ritorna 1)
        when(daoMock.updateaddtocartnull(any(cart.class))).thenReturn(1);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il cookie venga aggiunto correttamente
        Cookie cartCookie = new Cookie("cart", "cartt");
        cartCookie.setMaxAge(10);
        verify(response).addCookie(cartCookie);

        // Verifica che il reindirizzamento vada a cart.jsp
        verify(response).sendRedirect("cart.jsp");
    }

    @Test
    public void testDoGetUpdateProductFailure() throws Exception {
        // Crea mock di HttpServletRequest e HttpServletResponse
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Simula i parametri della richiesta
        when(request.getParameter("N")).thenReturn("John");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("ie")).thenReturn("Fiat");
        when(request.getParameter("ig")).thenReturn("Fiat 500");
        when(request.getParameter("ih")).thenReturn("15000");  // Prezzo dell'auto
        when(request.getParameter("ii")).thenReturn("3");  // Quantità dell'auto aggiornata
        when(request.getParameter("ij")).thenReturn("fiat500.jpg");  // Immagine dell'auto

        // Simula che il prodotto esista già nel carrello (checkaddtocartnull ritorna true)
        when(daoMock.checkaddtocartnull(any(cart.class))).thenReturn(true);

        // Simula che l'aggiornamento del prodotto nel carrello fallisca (updateaddtocartnull ritorna 0)
        when(daoMock.updateaddtocartnull(any(cart.class))).thenReturn(0);

        // Esegui il metodo doGet della servlet
        servlet.doGet(request, response);

        // Verifica che il reindirizzamento vada a selecteditemc.jsp in caso di fallimento
        verify(response).sendRedirect("selecteditemc.jsp");
    }
}
