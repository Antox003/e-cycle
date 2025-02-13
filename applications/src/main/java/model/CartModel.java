package model;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.ProductBean;
import model.SpedizioneBean;
import control.DriverManagerConnectionPool;

public class CartModel {
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
    public synchronized CartBean updateCarrello(ProductBean bean, CartBean cart) {
        Collection<ProductBean> collection = cart.getCarrello();
        Collection<ProductBean> lista = new LinkedList<ProductBean>();

        if (collection != null && collection.size() != 0) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                ProductBean prodotto = (ProductBean) it.next();
                if (prodotto.getCode() == bean.getCode()) {
                    prodotto.setNome(bean.getNome());
                    prodotto.setDescrizione(bean.getDescrizione());
                    prodotto.setPrezzo(bean.getPrezzo());
                }
                lista.add(prodotto);
            }
            cart.setLista(lista);
        }
        return cart;
    }

    public synchronized CartBean acquista(CartBean cart, UserBean user) throws SQLException {
        Connection con = null;
        Collection<ProductBean> carrello = cart.getCarrello();
        String sql = "INSERT INTO ORDINI (ID_ORDINE, Numero_prodotti, Data_aquisto, ID_ACCOUNT, Nome_prodotto, ID_INDIRIZZO, Numero_carta) VALUES (?, ?, current_date(), ?, ?, ?, ?)";

        try {
            con = DriverManagerConnectionPool.getConnection();

            if (carrello != null && carrello.size() != 0) {
                for (Iterator<ProductBean> i = cart.getCarrello().iterator(); i.hasNext();) {
                    ProductBean bean = (ProductBean) i.next();
                    Double prezzoTot = bean.getPrezzo() * bean.getQuantity();
                    SpedizioneBean spedizione = new SpedizioneBean();
                    PagamentoBean pagamento = new PagamentoBean();
                    
                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setInt(1, bean.getCode());  
                    ps.setInt(2, bean.getQuantity());
                    ps.setInt(3, bean.getCode()); 
                    ps.setInt(4, bean.getCode()); 
                    ps.setString(4, bean.getNome());
                    ps.setString(5, spedizione.getIndirizzo()); 
                    ps.setString(6, pagamento.getNumerocarta());
                    ps.executeUpdate();
                }
                con.commit();
                cart.removeAllItems();
            }
            return cart;
        } catch (Exception e) {
            throw new SQLException("Errore nell'acquisto del carrello", e);
        } finally {
            if (con != null) {
                DriverManagerConnectionPool.releaseConnection(con);
            }
        }
    }

    public synchronized CartBean aggiungiAlCarrello(CartBean carrello, int codiceProdotto) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;        
        ProductBean bean = new ProductBean();

        String selectSQL = "SELECT * FROM PRODOTTO WHERE ID_PRODOTTO = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, codiceProdotto);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                bean.setCode(rs.getInt("ID_PRODOTTO"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setQuantity(1);  // Imposta la quantità iniziale a 1
            }

            carrello.addItem(bean);  // Aggiunge il prodotto al carrello
            return carrello;
        } catch (Exception e) {
            throw new SQLException("Errore nell'aggiunta del prodotto al carrello", e);
        } finally {
            if (connection != null) {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }
    
    
    
    
    public synchronized CartBean doOrder(CartBean carrello, UserBean user) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ds.getConnection(); // Ottieni la connessione al database
            conn.setAutoCommit(false); // Disabilita l'auto-commit per gestire la transazione manualmente

            Collection<ProductBean> carrelloProdotti = carrello.getCarrello();
            if (carrelloProdotti == null || carrelloProdotti.isEmpty()) {
                throw new SQLException("Il carrello è vuoto");
            }

            String insertOrderQuery = "INSERT INTO ORDINI (Numero_Prodotti, Data_acquisto, ID_ACCOUNT, Nome_prodotto) VALUES (?, ?, ?, ?)";

            for (ProductBean prodotto : carrelloProdotti) {
                stmt = conn.prepareStatement(insertOrderQuery);
                stmt.setInt(1, prodotto.getQuantity()); // Numero di prodotti
                stmt.setDate(2, new java.sql.Date(System.currentTimeMillis())); // Data di acquisto
                stmt.setInt(3, user.getCode()); // ID dell'utente
                stmt.setString(4, prodotto.getNome()); // Nome del prodotto

                stmt.executeUpdate();
            }

            conn.commit(); // Commit della transazione
            carrello.clear(); // Svuota il carrello dopo l'ordine

            return carrello;

        } catch (SQLException e) {
            if (conn != null) {
                conn.rollback(); // Rollback in caso di errore
            }
            throw new SQLException("Errore durante l'inserimento dell'ordine", e);
        } finally {
            // Chiudi le risorse PreparedStatement e Connection
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.setAutoCommit(true); // Riabilita l'auto-commit
                conn.close();
            }
        }
    }

    
    
    

}
