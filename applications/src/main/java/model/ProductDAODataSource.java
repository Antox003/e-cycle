package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.util.ArrayList;
import java.util.List;

public class ProductDAODataSource implements IBeanDAO<ProductBean> {

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

    private static final String TABLE_NAME = "PRODOTTO";
    private static final String COLUMN_ID_PRODOTTO = "ID_PRODOTTO";
    private static final String COLUMN_NOME = "Nome";
    private static final String COLUMN_DESCRIZIONE = "Descrizione";
    private static final String COLUMN_HOUSE = "Casa";
    private static final String COLUMN_PREZZO = "Prezzo";
    private static final String COLUMN_DISPLAY = "Display";
    private static final String COLUMN_CAMERA = "Fotocamera";
    private static final String COLUMN_STORAGE = "Archiviazione";
    private static final String COLUMN_AUTH = "Autenticazione";
    private static final String COLUMN_CHIP = "Chip";
    private static final String COLUMN_SIM = "SIM";
    private static final String COLUMN_BLUE = "Bluetooth";
    private static final String COLUMN_CONNECT = "Connettori";
    private static final String COLUMN_RETE = "Rete";
    private static final String COLUMN_BATTERIA = "Batteria";
    private static final String COLUMN_DIMPES = "DimPes";
    private static final String COLUMN_SO = "SO";
    private static final String COLUMN_ACQUA = "Acqua";

    

    @Override
    public synchronized void doSave(ProductBean product) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NOME + ", " + COLUMN_DESCRIZIONE + ", " + COLUMN_HOUSE + ", " + COLUMN_PREZZO + ", " + COLUMN_DISPLAY  + ", " + COLUMN_CAMERA + ", " + COLUMN_STORAGE + ", " + COLUMN_AUTH+ ", " + COLUMN_CHIP + ", " + COLUMN_SIM + ", " + COLUMN_BLUE + ", " + COLUMN_CONNECT + ", " + COLUMN_RETE + ", " + COLUMN_BATTERIA + ", " + COLUMN_DIMPES + ", " + COLUMN_SO + ", " + COLUMN_ACQUA + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doSave");

            preparedStatement = connection.prepareStatement(insertSQL);
            int index = 1;
            preparedStatement.setString(index++, product.getNome());
            preparedStatement.setString(index++, product.getDescrizione());
            preparedStatement.setString(index++, product.getCasa());
            preparedStatement.setDouble(index++, product.getPrezzo());
            preparedStatement.setString(index++, product.getDisplay());
            preparedStatement.setString(index++, product.getFotocamera());
            preparedStatement.setString(index++, product.getArchiviazione());
            preparedStatement.setString(index++, product.getAutenticazione());
            preparedStatement.setString(index++, product.getChip());
            preparedStatement.setString(index++, product.getSIM());
            preparedStatement.setString(index++, product.getBluetooth());
            preparedStatement.setString(index++, product.getConnettori());
            preparedStatement.setString(index++, product.getRete());
            preparedStatement.setString(index++, product.getBatteria());
            preparedStatement.setString(index++, product.getDimPes());
            preparedStatement.setString(index++, product.getSO());
            preparedStatement.setString(index++, product.getAcqua());

            preparedStatement.executeUpdate();
            System.out.println("Product saved: " + product.getNome());

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

    
    
    public synchronized int doEdit(ProductBean product) throws SQLException {
        int output = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        // SQL di aggiornamento con la clausola WHERE corretta
        String updateSQL = "UPDATE " + TABLE_NAME + " SET " +
                COLUMN_NOME + " = ?, " +
                COLUMN_DESCRIZIONE + " = ?, " +
                COLUMN_HOUSE + " = ?, " +
                COLUMN_PREZZO + " = ?, " +
                COLUMN_DISPLAY + " = ?, " +
                COLUMN_CAMERA + " = ?, " +
                COLUMN_STORAGE + " = ?, " +
                COLUMN_AUTH + " = ?, " +
                COLUMN_CHIP + " = ?, " +
                COLUMN_SIM + " = ?, " +
                COLUMN_BLUE + " = ?, " +
                COLUMN_CONNECT + " = ?, " +
                COLUMN_RETE + " = ?, " +
                COLUMN_BATTERIA + " = ?, " +
                COLUMN_DIMPES + " = ?, " +
                COLUMN_SO + " = ?, " +
                COLUMN_ACQUA + " = ? " +
                "WHERE ID_PRODOTTO = ?"; 

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            // Imposta i valori per tutti i parametri
            preparedStatement.setString(1, product.getNome());
            preparedStatement.setString(2, product.getDescrizione());
            preparedStatement.setString(3, product.getCasa());
            preparedStatement.setDouble(4, product.getPrezzo());
            preparedStatement.setString(5, product.getDisplay());
            preparedStatement.setString(6, product.getFotocamera());
            preparedStatement.setString(7, product.getArchiviazione());
            preparedStatement.setString(8, product.getAutenticazione());
            preparedStatement.setString(9, product.getChip());
            preparedStatement.setString(10, product.getSIM());
            preparedStatement.setString(11, product.getBluetooth());
            preparedStatement.setString(12, product.getConnettori());
            preparedStatement.setString(13, product.getRete());
            preparedStatement.setString(14, product.getBatteria());
            preparedStatement.setString(15, product.getDimPes());
            preparedStatement.setString(16, product.getSO());
            preparedStatement.setString(17, product.getAcqua());
            // Imposta il parametro per la clausola WHERE
            preparedStatement.setInt(18, product.getCode());

            // Esegui l'aggiornamento
            int rowsAffected = preparedStatement.executeUpdate();

            // Debugging
            if (rowsAffected > 0) {
                System.out.println("Product updated: " + product.getNome());
            } else {
                System.out.println("No product found with ID: " + product.getCode());
            }

            output = rowsAffected;

        } catch (Exception e) {
            e.printStackTrace(); // Stampa l'eccezione per debug
            throw e; // Rilancia l'eccezione per la gestione in alto livello
        } finally {
            // Chiudi le risorse nel blocco finally
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }

        return output;
    }

    
    
    
    
    
    
    
    
    
    @Override
    public synchronized boolean doDelete(int code) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID_PRODOTTO + " = ?";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doDelete");

            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, code);

            result = preparedStatement.executeUpdate();
            System.out.println("Product deleted with ID: " + code);

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
    public synchronized List<ProductBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<ProductBean> products = new ArrayList<>();

        String selectSQL = "SELECT " + COLUMN_ID_PRODOTTO + ", " + COLUMN_NOME + ", " + COLUMN_DESCRIZIONE + ", " + COLUMN_HOUSE + ", "+ COLUMN_PREZZO + ", " + COLUMN_DISPLAY  +  ", " +  COLUMN_CAMERA + ", " +  COLUMN_STORAGE + ", " +  COLUMN_AUTH+ ", " + COLUMN_CHIP + ", " + COLUMN_SIM + " , " + COLUMN_BLUE + ", " + COLUMN_CONNECT + ", " + COLUMN_RETE + ", " + COLUMN_BATTERIA + ", " + COLUMN_DIMPES + ", " + COLUMN_SO + ", " + COLUMN_ACQUA + " FROM " + TABLE_NAME;


        if (order != null && !order.isEmpty()) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                ProductBean bean = new ProductBean();
                bean.setCode(rs.getInt("ID_PRODOTTO"));
                bean.setNome(rs.getString("Nome"));
                bean.setDescrizione(rs.getString("Descrizione"));
                bean.setCasa(rs.getString("Casa"));
                bean.setPrezzo(rs.getDouble("Prezzo"));
                bean.setDisplay(rs.getString("Display"));
                bean.setFotocamera(rs.getString("Fotocamera"));
                bean.setArchiviazione(rs.getString("Archiviazione"));
                bean.setAutenticazione(rs.getString("Autenticazione"));
                bean.setChip(rs.getString("Chip"));
                bean.setSIM(rs.getString("SIM"));
                bean.setBluetooth(rs.getString("Bluetooth"));
                bean.setConnettori(rs.getString("Connettori"));
                bean.setRete(rs.getString("Rete"));
                bean.setBatteria(rs.getString("Batteria"));
                bean.setDimPes(rs.getString("DimPes"));
                bean.setSO(rs.getString("SO"));
                bean.setAcqua(rs.getString("Acqua"));


                products.add(bean);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving products from database.", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return products;
    }


    @Override
    public synchronized ProductBean doRetrieveByKey(int idProdotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ProductBean bean = new ProductBean();
        String selectSQL = "SELECT " + COLUMN_ID_PRODOTTO + ", " + COLUMN_NOME + ", " + COLUMN_DESCRIZIONE + ", "+ COLUMN_HOUSE +", " + COLUMN_PREZZO + ", " + COLUMN_DISPLAY  +  ", " +  COLUMN_CAMERA + ", " +  COLUMN_STORAGE + ", " +  COLUMN_AUTH+ ", " + COLUMN_CHIP + ", " + COLUMN_SIM + " , " + COLUMN_BLUE + ", " + COLUMN_CONNECT + ", " + COLUMN_RETE + ", " + COLUMN_BATTERIA + ", " + COLUMN_DIMPES + ", " + COLUMN_SO + ", " + COLUMN_ACQUA  + " FROM " + TABLE_NAME + " WHERE " + COLUMN_ID_PRODOTTO + " = ?";
        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doRetrieveByKey");

            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idProdotto);
            System.out.println("Executing query: " + selectSQL);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                bean.setCode(rs.getInt(COLUMN_ID_PRODOTTO));
                bean.setNome(rs.getString(COLUMN_NOME));
                bean.setDescrizione(rs.getString(COLUMN_DESCRIZIONE));
                bean.setCasa(rs.getString(COLUMN_HOUSE));
                bean.setPrezzo(rs.getDouble(COLUMN_PREZZO));
                bean.setDisplay(rs.getString(COLUMN_DISPLAY));
                bean.setFotocamera(rs.getString(COLUMN_CAMERA));
                bean.setArchiviazione(rs.getString(COLUMN_STORAGE));
                bean.setAutenticazione(rs.getString(COLUMN_AUTH));
                bean.setChip(rs.getString(COLUMN_CHIP));
                bean.setSIM(rs.getString(COLUMN_SIM));
                bean.setBluetooth(rs.getString(COLUMN_BLUE));
                bean.setConnettori(rs.getString(COLUMN_CONNECT));
                bean.setRete(rs.getString(COLUMN_RETE));
                bean.setBatteria(rs.getString(COLUMN_BATTERIA));
                bean.setDimPes(rs.getString(COLUMN_DIMPES));
                bean.setSO(rs.getString(COLUMN_SO));
                bean.setAcqua(rs.getString(COLUMN_ACQUA));


                System.out.println("Retrieved product by key: " + bean.getNome() + ", " + bean.getDescrizione()+ ", " + bean.getCasa()+ ", " + bean.getPrezzo() + ", " + bean.getDisplay() + ", " + bean.getFotocamera() + ", " + bean.getArchiviazione() + ", " + bean.getAutenticazione() + ", " + bean.getChip() + ", " + bean.getSIM()  + ", " + bean.getBluetooth()  + ", " + bean.getConnettori() + ", " + bean.getRete()  + ", " + bean.getBatteria()+ ", " + bean.getDimPes()  + ", " + bean.getSO() + ", " + bean.getAcqua() );
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing query: " + selectSQL, e);
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {
                if (connection != null)
                    connection.close();
            }
        }
        return bean;
    }

}
