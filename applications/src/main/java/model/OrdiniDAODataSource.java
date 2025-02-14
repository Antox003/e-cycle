package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdiniDAODataSource implements IBeanDAO<OrdiniBean> {

    private static DataSource ds;

    static {
        try {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");

            ds = (DataSource) envCtx.lookup("jdbc/ecycle");

            System.out.println("DataSource lookup successful");

        } catch (NamingException e) {
            System.out.println("Error during DataSource lookup: " + e.getMessage());
        }
    }

    private static final String TABLE_NAME = "ORDINI";
    private static final String COLUMN_ID_ORDINE = "ID_ORDINE"; 
    private static final String COLUMN_NUMERO = "Numero_Prodotti"; 
    private static final String COLUMN_DATA = "Data_acquisto";
    private static final String COLUMN_ACCOUNT = "ID_ACCOUNT";
    private static final String COLUMN_NOME = "Nome_prodotto";
    private static final String COLUMN_ID_INDIRIZZO = "ID_INDIRIZZO";
    private static final String COLUMN_CARTA = "Numero_carta";


    @Override
    public synchronized void doSave(OrdiniBean spedizione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        OrdiniBean ordini = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME + " (" +  COLUMN_NUMERO + ", " +
                COLUMN_DATA + ", " + COLUMN_ACCOUNT +  ", " +COLUMN_NOME + ", " + COLUMN_ID_INDIRIZZO + ", " + COLUMN_CARTA +") VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doSave");

            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, ordini.getNumeroprodotti());
            preparedStatement.setString(2, ordini.getDataacquisto());
            preparedStatement.setInt(3, ordini.getAccount());
            preparedStatement.setString(4, ordini.getProdotto());
            preparedStatement.setString(5, ordini.getIndirizzo());
            preparedStatement.setString(6, ordini.getCarta());

            preparedStatement.executeUpdate();
            System.out.println("Pagamento saved: " + ordini.getNumeroprodotti());

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
    }


    @Override
    public synchronized boolean doDelete(int idOrdine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID_ORDINE + " = ?";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doDelete");

            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idOrdine);

            result = preparedStatement.executeUpdate();
            System.out.println("Ordine deleted with ID_ORDINE: " + idOrdine);

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return (result != 0);
    }

    @Override
   public synchronized List<OrdiniBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<OrdiniBean> ordini = new ArrayList<>();

       String selectSQL = "SELECT " + COLUMN_ID_ORDINE + ", " + COLUMN_NUMERO + ", " + COLUMN_DATA + ", " + COLUMN_ACCOUNT + ", " + COLUMN_NOME + ", " + COLUMN_ID_INDIRIZZO + ", " + COLUMN_CARTA + " FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OrdiniBean ordine = new OrdiniBean();
                ordine.setNumeroprodotti(rs.getString(COLUMN_NUMERO));
                ordine.setDataacquisto(rs.getString(COLUMN_DATA));
                ordine.setCode(rs.getInt(COLUMN_ID_ORDINE));
                ordine.setAccount(rs.getInt(COLUMN_ACCOUNT));
                ordine.setProdotto(rs.getString(COLUMN_NOME));
                ordine.setIndirizzo(rs.getString(COLUMN_ID_INDIRIZZO));
                ordine.setCarta(rs.getString(COLUMN_CARTA));

                ordini.add(ordine);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving ordini from database.", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
      return ordini;
    }

    
    
    public synchronized List<OrdiniBean> doRetrieveAllByAccount(int idAccount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<OrdiniBean> ordini = new ArrayList<>();

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ACCOUNT + " = ?";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doRetrieveAllByAccount");

            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idAccount);

            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                OrdiniBean ordine = new OrdiniBean();
                ordine.setCode(rs.getInt(COLUMN_ID_ORDINE));
                ordine.setNumeroprodotti(rs.getString(COLUMN_NUMERO));
                ordine.setDataacquisto(rs.getString(COLUMN_DATA));
                ordine.setAccount(rs.getInt(COLUMN_ACCOUNT));
                ordine.setProdotto(rs.getString(COLUMN_NOME));
                ordine.setIndirizzo(rs.getString(COLUMN_ID_INDIRIZZO));
                ordine.setCarta(rs.getString(COLUMN_CARTA));

                ordini.add(ordine);
                System.out.println("Order retrieved: " + ordine.getCode()); // Output per debugging
            }
            System.out.println("Number of orders retrieved for account " + idAccount + ": " + ordini.size()); // Output per debugging

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving orders for account " + idAccount, e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ordini;
    }
    
    
    @Override
    public OrdiniBean doRetrieveByKey(int idOrdine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        OrdiniBean ordine = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID_ORDINE + " = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idOrdine);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
                ordine = new OrdiniBean();
                ordine.setNumeroprodotti(rs.getString(COLUMN_NUMERO));
                ordine.setDataacquisto(rs.getString(COLUMN_DATA));
                ordine.setCode(rs.getInt(COLUMN_ID_ORDINE));
                ordine.setAccount(rs.getInt(COLUMN_ACCOUNT));
                ordine.setProdotto(rs.getString(COLUMN_NOME));
                ordine.setIndirizzo(rs.getString(COLUMN_ID_INDIRIZZO));
                ordine.setCarta(rs.getString(COLUMN_CARTA));
            }

        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return ordine;
    }
    
    
   

    public void saveOrder(OrdiniBean ordini, ProductBean prodotto, UserBean user, SpedizioneBean spedizione, PagamentoBean pagamento) throws SQLException {
        String insertOrderSQL = "INSERT INTO ORDINI (Numero_Prodotti, Data_acquisto, ID_ACCOUNT, Nome_prodotto, ID_INDIRIZZO, Numero_carta) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(insertOrderSQL);
            
            // Verifica dei dati prima dell'inserimento
            if (ordini.getNumeroprodotti() != null && !ordini.getNumeroprodotti().isEmpty() &&
                prodotto.getNome() != null && !prodotto.getNome().isEmpty() &&
                spedizione.getIndirizzo() != null && pagamento.getNumerocarta() != null && !pagamento.getNumerocarta().isEmpty()) {
                
                // Imposta i parametri del PreparedStatement
                preparedStatement.setString(1, ordini.getNumeroprodotti());
                preparedStatement.setDate(2, Date.valueOf(LocalDate.now())); // Data di acquisto corrente
                preparedStatement.setInt(3, user.getCode()); // ID_ACCOUNT dell'utente
                preparedStatement.setString(4, prodotto.getNome()); // Nome del prodotto
                preparedStatement.setString(5, spedizione.getIndirizzo()); // ID_INDIRIZZO dell'utente
                preparedStatement.setString(6, pagamento.getNumerocarta()); // Numero della carta di pagamento
                
                // Esegui l'update nel database
                preparedStatement.executeUpdate();
            } else {
                throw new IllegalArgumentException("Dati dell'ordine non validi");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Errore durante il salvataggio dell'ordine nel database", e);
        } finally {
            // Chiudi le risorse
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

