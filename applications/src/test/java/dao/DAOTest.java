package carzone.com.dao;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import carzone.com.dao.DAO;
import carzone.com.dao.DAO2;
import carzone.com.entity.Product;
import carzone.com.entity.*;
import carzone.com.entity.cart;
import carzone.com.entity.viewlist;

class DAOTest {

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

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        dao = new DAO(connection); // Usa la connessione mockata
    }
    
    @Test
    void testGetAllbrand() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1); // Mocking the brand ID
        when(resultSet.getString(2)).thenReturn("Fiat"); // Mocking the brand name
        
        List<brand> brandList = dao.getAllbrand();
        assertNotNull(brandList, "La lista dei brand non dovrebbe essere nulla");
        assertFalse(brandList.isEmpty(), "La lista dei brand dovrebbe contenere almeno un elemento");
    }

    @Test
    void testGetAllcategory() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1); // Mocking the category ID
        when(resultSet.getString(2)).thenReturn("SUV"); // Mocking the category name
        
        List<category> categoryList = dao.getAllcategory();
        assertNotNull(categoryList, "La lista delle categorie non dovrebbe essere nulla");
        assertFalse(categoryList.isEmpty(), "La lista delle categorie dovrebbe contenere almeno un elemento");
    }

    
    
    @Test
    void testGetProductById() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("carid")).thenReturn(1);
        when(resultSet.getString("carname")).thenReturn("Fiat 500");
        when(resultSet.getInt("carprice")).thenReturn(20000);
        
        Product product = dao.getProductById(1);
        assertNotNull(product, "Il prodotto non dovrebbe essere nullo");
        assertEquals(1, product.getCarid(), "L'ID del prodotto dovrebbe essere 1");
        assertEquals("Fiat 500", product.getCarname(), "Il nome del prodotto dovrebbe essere 'Fiat 500'");
    }

    @Test
    void testUpdateProductName() throws SQLException {
        Product product = new Product();
        product.setCarid(1);
        product.setCarname("New Car Name");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        int result = dao.updateaproductname(product);
        assertEquals(1, result, "Il nome del prodotto dovrebbe essere aggiornato correttamente");
    }

    @Test
    void testDeleteProduct() throws SQLException {
        Product product = new Product();
        product.setCarid(1);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = dao.deleteProduct(product);
        assertTrue(result, "Il prodotto dovrebbe essere eliminato correttamente");
    }

    @Test
    void testGetAllProducts() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto presente

        List<Product> productList = dao.getAllProducts();
        assertNotNull(productList, "La lista dei prodotti non dovrebbe essere nulla");
        assertFalse(productList.isEmpty(), "La lista dei prodotti dovrebbe contenere almeno un elemento");
    }

    @Test
    void testGetAllCustomer() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<customer> customerList = dao.getAllCustomer();
        assertNotNull(customerList, "La lista dei clienti non dovrebbe essere nulla");
        assertFalse(customerList.isEmpty(), "La lista dei clienti dovrebbe contenere almeno un elemento");
    }
    
    @Test
    void testDeleteCustomer() throws SQLException {
        customer cust = new customer();
        cust.setName("Mario Rossi");
        cust.setEmail_Id("mario.rossi@example.com");

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = dao.deleteCustomer(cust);
        assertTrue(result, "Il cliente dovrebbe essere eliminato correttamente");
    }
    
    
}
