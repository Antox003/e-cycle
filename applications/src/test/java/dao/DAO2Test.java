package carzone.com.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import carzone.com.conn.DBConnect;
import carzone.com.dao.DAO;
import carzone.com.dao.DAO2;
import carzone.com.entity.*;
import carzone.com.entity.customer;
import carzone.com.entity.cart;
import carzone.com.entity.usermaster;
import carzone.com.entity.viewlist;

class DAO2Test {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;
    
    @Mock
    private HttpServletRequest request;
    
    @Mock
    private ServletFileUpload servletFileUpload;
    
    private DAO dao;
    private DAO2 dao2;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        dao = new DAO(connection); // Usa la connessione mockata
        dao2 = new DAO2(connection);
    }
    
    @Test
    void testGetAllviewlist() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto nella lista

        List<viewlist> viewlist = dao2.getAllviewlist();
        assertNotNull(viewlist, "La lista di viewlist non dovrebbe essere nulla");
        assertFalse(viewlist.isEmpty(), "La lista di viewlist dovrebbe contenere almeno un elemento");
    }

    
    
    @Test
    void testCheckCustomerLogin() throws SQLException {
        customer cust = new customer();
        cust.setEmail_Id("test@example.com");
        cust.setPassword("password123");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        boolean result = dao2.checkcust(cust);
        assertTrue(result, "Il login del cliente dovrebbe avere successo");
    }

 
    @Test
    void testCheckAdminLogin() throws SQLException {
        usermaster admin = new usermaster();
        admin.setName("admin");
        admin.setPassword("admin123");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        boolean result = dao2.checkadmin(admin);
        assertTrue(result, "Il login dell'admin dovrebbe avere successo");
    }

    @Test
    void testAddCustomer() throws SQLException {
        customer cust = new customer();
        cust.setName("Mario Rossi");
        cust.setEmail_Id("mario.rossi@example.com");
        cust.setPassword("securePass");
        cust.setContact_Number(123456789);
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        int result = dao2.addcustomer(cust);
        assertEquals(1, result, "Il cliente dovrebbe essere registrato con successo");
    }
    
    @Test
    void testGetSelecteditem() throws SQLException {
        String carImage = "image.jpg";  // L'immagine da cercare

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto trovato
        when(resultSet.getString(1)).thenReturn("Fiat");
        when(resultSet.getString(2)).thenReturn("Berlina");
        when(resultSet.getString(3)).thenReturn("500X");
        when(resultSet.getInt(4)).thenReturn(20000);
        when(resultSet.getInt(5)).thenReturn(10);
        when(resultSet.getString(6)).thenReturn("image.jpg");

        List<viewlist> viewList = dao2.getSelecteditem(carImage);
        assertNotNull(viewList, "La lista di viewlist non dovrebbe essere nulla");
        assertEquals(1, viewList.size(), "Dovrebbe esserci un solo prodotto con questa immagine");
    }

    @Test
    void testCheckAddToCartNull() throws SQLException {
        cart cartItem = new cart();
        cartItem.setBname("Fiat");
        cartItem.setCname("Berlina");
        cartItem.setCarname("500X");
        cartItem.setCarprice(20000);
        cartItem.setCarimage("image.jpg");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);  // Mock di prodotto trovato nel carrello

        boolean result = dao2.checkaddtocartnull(cartItem);
        assertTrue(result, "Il prodotto dovrebbe essere già presente nel carrello");
    }
    
    @Test
    void testUpdateAddToCartNull() throws SQLException {
        cart cartItem = new cart();
        cartItem.setBname("Fiat");
        cartItem.setCname("Berlina");
        cartItem.setCarname("500X");
        cartItem.setCarprice(20000);
        cartItem.setCarimage("image.jpg");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nell'aggiornamento

        int result = dao2.updateaddtocartnull(cartItem);
        assertEquals(1, result, "La quantità del prodotto nel carrello dovrebbe essere aggiornata");
    }

    
    @Test
    void testAddToCartNull() throws SQLException {
        cart cartItem = new cart();
        cartItem.setBname("Fiat");
        cartItem.setCname("Berlina");
        cartItem.setCarname("500X");
        cartItem.setCarprice(20000);
        cartItem.setCarquantity(1);
        cartItem.setCarimage("image.jpg");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nell'inserimento

        int result = dao2.addtocartnull(cartItem);
        assertEquals(1, result, "Il prodotto dovrebbe essere aggiunto al carrello con successo");
    }

    @Test
    void testGetSelectedCart() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto trovato
        when(resultSet.getString(1)).thenReturn("Fiat");
        when(resultSet.getString(2)).thenReturn("Berlina");
        when(resultSet.getString(3)).thenReturn("500X");
        when(resultSet.getInt(4)).thenReturn(20000);
        when(resultSet.getInt(5)).thenReturn(10);
        when(resultSet.getString(6)).thenReturn("image.jpg");

        List<cart> cartList = dao2.getSelectedcart();
        assertNotNull(cartList, "La lista del carrello non dovrebbe essere nulla");
        assertFalse(cartList.isEmpty(), "La lista del carrello dovrebbe contenere almeno un prodotto");
    }


    @Test
    void testGetCart() throws SQLException {
        String customerName = "Mario Rossi";

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto nel carrello per il cliente
        when(resultSet.getString(1)).thenReturn("Fiat");
        when(resultSet.getString(2)).thenReturn("Berlina");
        when(resultSet.getString(3)).thenReturn("500X");
        when(resultSet.getInt(4)).thenReturn(20000);
        when(resultSet.getInt(5)).thenReturn(10);
        when(resultSet.getString(6)).thenReturn("image.jpg");

        List<cart> cartList = dao2.getcart(customerName);
        assertNotNull(cartList, "La lista del carrello non dovrebbe essere nulla");
        assertFalse(cartList.isEmpty(), "La lista del carrello per il cliente dovrebbe contenere almeno un prodotto");
    }

    
    @Test
    void testRemoveCartNull() throws SQLException {
        cart cartItem = new cart();
        cartItem.setCarimage("image.jpg");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nella rimozione

        int result = dao2.removecartnull(cartItem);
        assertEquals(1, result, "Il prodotto dovrebbe essere rimosso dal carrello con successo");
    }

    
    @Test
    void testRemoveCart() throws SQLException {
        cart cartItem = new cart();
        cartItem.setName("Mario Rossi");
        cartItem.setCarimage("image.jpg");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nella rimozione

        int result = dao2.removecart(cartItem);
        assertEquals(1, result, "Il prodotto dovrebbe essere rimosso correttamente dal carrello");
    }

    
    @Test
    void testCheckCustomerDuplicate() throws SQLException {
        customer customer = new customer();
        customer.setName("Mario Rossi");
        customer.setEmail_Id("mario.rossi@example.com");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);  // Mock di cliente esistente nel sistema

        boolean result = dao2.checkcust2(customer);
        assertTrue(result, "Il cliente con questo nome o email dovrebbe esistere già nel sistema");
    }
    
    @Test
    void testRemoveOrders() throws SQLException {
        orders order = new orders();
        order.setOrder_Id(1);  // ID dell'ordine da rimuovere

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nella rimozione

        int result = dao2.removeorders(order);
        assertEquals(1, result, "L'ordine dovrebbe essere rimosso correttamente dal sistema");
    }


    
    
    
    


    
}