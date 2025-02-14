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

class DAO4Test {

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
    void testCheckCartNull() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        
        boolean result = dao4.checkcart();
        assertTrue(result, "Il carrello con elementi nulli dovrebbe esistere");
    }
    
    @Test
    void testCheckCartByName() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        
        boolean result = dao4.checkcart2("Mario Rossi");
        assertTrue(result, "Il carrello per l'utente dovrebbe esistere");
    }
    
    @Test
    void testAddOrders() throws SQLException {
        orders order = new orders();
        order.setCustomer_Name("Mario Rossi");
        order.setCustomer_City("Roma");
        order.setDate("2024-02-15");
        order.setTotal_Price(50000);
        order.setStatus("In corso");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao4.addOrders(order);
        assertEquals(1, result, "L'ordine dovrebbe essere aggiunto con successo");
    }
    
    @Test
    void testAddOrderDetails() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nell'inserimento dei dettagli ordine

        int result = dao4.addOrder_details();
        assertEquals(1, result, "I dettagli dell'ordine dovrebbero essere aggiunti correttamente dalla tabella cart");
    }
    
    @Test
    void testAddOrderDetails2() throws SQLException {
        String customerName = "Mario Rossi";

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nell'inserimento dei dettagli ordine per cliente

        int result = dao4.addOrder_details2(customerName);
        assertEquals(1, result, "I dettagli dell'ordine per il cliente dovrebbero essere aggiunti correttamente dalla tabella cart");
    }


    
    
    @Test
    void testDeleteCartNull() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao4.deletecart();
        assertEquals(1, result, "Il carrello con valori nulli dovrebbe essere eliminato con successo");
    }
    
    @Test
    void testDeleteCartByName() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao4.deletecart2("Mario Rossi");
        assertEquals(1, result, "Il carrello dell'utente dovrebbe essere eliminato con successo");
    }
    
    @Test
    void testUpdateOrderDetails() throws SQLException {
        order_details od = new order_details();
        od.setDate("2024-02-15");
        od.setName("Mario Rossi");
        
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);
        
        int result = dao4.updateOrder_details(od);
        assertEquals(1, result, "I dettagli dell'ordine dovrebbero essere aggiornati");
    }
    
    @Test
    void testUpdateOrderDetails2() throws SQLException {
        // Creiamo un oggetto `order_details` con la data che vogliamo aggiornare
        order_details orderDetails = new order_details();
        orderDetails.setDate("2024-02-15");

        // Mock delle operazioni sul PreparedStatement
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);  // Mock di successo nell'aggiornamento

        int result = dao4.updateOrder_details2(orderDetails);

        // Verifica che il risultato dell'operazione di aggiornamento sia corretto
        assertEquals(1, result, "La data dell'ordine dovrebbe essere aggiornata correttamente");
    }

}

