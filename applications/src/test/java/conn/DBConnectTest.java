package carzone.com.conn;

import java.sql.Connection;
import java.sql.DriverManager;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectTest {

    @Test
    public void testGetConn() {
        try {
            Connection conn = DBConnect.getConn();
            assertNotNull(conn, "La connessione al database non dovrebbe essere nulla");
            assertTrue(conn.isValid(2), "La connessione al database dovrebbe essere valida");
        } catch (SQLException e) {
            fail("Errore durante la connessione al database: " + e.getMessage());
        }
    }
}
