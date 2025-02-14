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
import carzone.com.entity.contactus;

class DAO5Test {

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
    void testGetAllCart() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<cart> cartList = dao5.getAllcart();
        assertNotNull(cartList, "La lista del carrello non dovrebbe essere nulla");
    }
    
    @Test
    void testGetAllOrders() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<orders> ordersList = dao5.getAllorders();
        assertNotNull(ordersList, "La lista degli ordini non dovrebbe essere nulla");
    }
    
    @Test
    void testGetAllOrderDetails() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<order_details> orderDetailsList = dao5.getAllorder_details();
        assertNotNull(orderDetailsList, "La lista dei dettagli degli ordini non dovrebbe essere nulla");
    }
    
    @Test
    void testRemoveOrderDetails() throws SQLException {
        order_details orderDetail = new order_details();
        orderDetail.setDate("2024-02-15");
        orderDetail.setCarimage("image.jpg");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao5.removeorder_details(orderDetail);
        assertEquals(1, result, "I dettagli dell'ordine dovrebbero essere rimossi con successo");
    }
    
    @Test
    void testAddContactUs() throws SQLException {
        contactus contact = new contactus();
        contact.setName("Mario Rossi");
        contact.setEmail_Id("mario.rossi@example.com");
        contact.setContact_Number(123456789);
        contact.setMessage("Domanda di supporto");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao5.addContactus(contact);
        assertEquals(1, result, "Il messaggio di contatto dovrebbe essere aggiunto con successo");
    }
    
    @Test
    void testGetContactUs() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        
        List<contactus> contactList = dao5.getcontactus();
        assertNotNull(contactList, "La lista dei contatti non dovrebbe essere nulla");
    }
    
    @Test
    void testRemoveContactUs() throws SQLException {
        contactus contact = new contactus();
        contact.setId(1);
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao5.removecont(contact);
        assertEquals(1, result, "Il messaggio di contatto dovrebbe essere rimosso con successo");
    }
}
