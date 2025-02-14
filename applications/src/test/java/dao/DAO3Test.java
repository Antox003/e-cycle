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
import carzone.com.dao.DAO3;
import carzone.com.dao.DAO4;
import carzone.com.dao.DAO5;
import carzone.com.entity.Product;
import carzone.com.entity.customer;
import carzone.com.entity.cart;
import carzone.com.entity.usermaster;
import carzone.com.entity.viewlist;
import carzone.com.entity.berlina;
import carzone.com.entity.suv;
import carzone.com.entity.elettrica;
import carzone.com.entity.dilusso;
import carzone.com.entity.orders;
import carzone.com.entity.order_details;

class DAO3Test {

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
    private DAO3 dao3;
    private DAO4 dao4;
    private DAO5 dao5;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        dao = new DAO(connection); // Usa la connessione mockata
        dao2 = new DAO2(connection);
        dao3 = new DAO3(connection);
        dao4 = new DAO4(connection);
        dao5 = new DAO5(connection);
    }

    
    @Test
    void testGetAllberlina() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un prodotto berlina
        when(resultSet.getString(1)).thenReturn("Fiat");
        when(resultSet.getString(2)).thenReturn("Berlina");
        when(resultSet.getString(3)).thenReturn("500X");
        when(resultSet.getInt(4)).thenReturn(20000);
        when(resultSet.getInt(5)).thenReturn(10);
        when(resultSet.getString(6)).thenReturn("image.jpg");

        List<berlina> berlinaList = dao3.getAllberlina();
        assertNotNull(berlinaList, "La lista delle berlina non dovrebbe essere nulla");
        assertEquals(1, berlinaList.size(), "Dovrebbe esserci un solo prodotto berlina nella lista");
    }
    
    @Test
    void testGetAllsuv() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un SUV
        when(resultSet.getString(1)).thenReturn("Fiat");
        when(resultSet.getString(2)).thenReturn("SUV");
        when(resultSet.getString(3)).thenReturn("500X");
        when(resultSet.getInt(4)).thenReturn(30000);
        when(resultSet.getInt(5)).thenReturn(5);
        when(resultSet.getString(6)).thenReturn("image_suv.jpg");

        List<suv> suvList = dao3.getAllsuv();
        assertNotNull(suvList, "La lista degli SUV non dovrebbe essere nulla");
        assertEquals(1, suvList.size(), "Dovrebbe esserci un solo prodotto SUV nella lista");
    }
    
    @Test
    void testGetAllelettrica() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un'auto elettrica
        when(resultSet.getString(1)).thenReturn("Tesla");
        when(resultSet.getString(2)).thenReturn("Elettrica");
        when(resultSet.getString(3)).thenReturn("Model S");
        when(resultSet.getInt(4)).thenReturn(50000);
        when(resultSet.getInt(5)).thenReturn(2);
        when(resultSet.getString(6)).thenReturn("tesla_image.jpg");

        List<elettrica> elettricaList = dao3.getAllelettrica();
        assertNotNull(elettricaList, "La lista delle auto elettriche non dovrebbe essere nulla");
        assertEquals(1, elettricaList.size(), "Dovrebbe esserci un solo prodotto elettrico nella lista");
    }
    
    @Test
    void testGetAlldilusso() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);  // Mock di un'auto di lusso
        when(resultSet.getString(1)).thenReturn("Ferrari");
        when(resultSet.getString(2)).thenReturn("Di Lusso");
        when(resultSet.getString(3)).thenReturn("Portofino");
        when(resultSet.getInt(4)).thenReturn(200000);
        when(resultSet.getInt(5)).thenReturn(1);
        when(resultSet.getString(6)).thenReturn("ferrari_image.jpg");

        List<dilusso> dilussoList = dao3.getAlldilusso();
        assertNotNull(dilussoList, "La lista delle auto di lusso non dovrebbe essere nulla");
        assertEquals(1, dilussoList.size(), "Dovrebbe esserci un solo prodotto di lusso nella lista");
    }

    
    @Test
    void testCheckAddToCartNull() throws SQLException {
        cart c = new cart();
        c.setName("Mario Rossi");
        c.setBname("Fiat");
        c.setCname("Berlina");
        c.setCarname("500X");
        c.setCarprice(20000);
        c.setCarimage("image.jpg");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        
        boolean result = dao3.checkaddtocartnull(c);
        assertTrue(result, "Il prodotto dovrebbe già esistere nel carrello");
    }

    @Test
    void testUpdateAddToCartNull() throws SQLException {
        cart c = new cart();
        c.setName("Mario Rossi");
        c.setBname("Fiat");
        c.setCname("Berlina");
        c.setCarname("500X");
        c.setCarprice(20000);
        c.setCarimage("image.jpg");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao3.updateaddtocartnull(c);
        assertEquals(1, result, "La quantità del prodotto nel carrello dovrebbe essere aggiornata");
    }

    @Test
    void testGetOrders() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<orders> orderList = dao3.getOrders("Mario Rossi");
        assertNotNull(orderList, "La lista degli ordini non dovrebbe essere nulla");
    }
    
    @Test
    void testGetOrdersByDate() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<orders> orderList = dao3.getOrdersbydate("2024-02-15");
        assertNotNull(orderList, "La lista degli ordini per data non dovrebbe essere nulla");
    }
    
    @Test
    void testGetOrderDetails() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<order_details> orderDetails = dao3.getOrderdetails("2024-02-15");
        assertNotNull(orderDetails, "I dettagli dell'ordine non dovrebbero essere nulli");
    }
}
