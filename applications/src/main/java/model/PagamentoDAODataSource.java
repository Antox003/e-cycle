package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PagamentoDAODataSource implements IBeanDAO<PagamentoBean> {

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

    private static final String TABLE_NAME = "PAGAMENTO";
    private static final String COLUMN_TIPOCARTA = "Tipo_carta";
    private static final String COLUMN_NUMCARTA = "Numero_carta";
    private static final String COLUMN_SCADENZA = "Data_scadenza";
    private static final String COLUMN_ACCOUNT = "ID_ACCOUNT";

    @Override
    public synchronized void doSave(PagamentoBean spedizione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PagamentoBean pagamento = null;

        String insertSQL = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_TIPOCARTA + ", " + COLUMN_NUMCARTA + ", " +
        		COLUMN_SCADENZA + ", " + COLUMN_ACCOUNT + ") VALUES (?, ?, ?, ?)";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doSave");

            preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, pagamento.getTipocarta());
            preparedStatement.setString(2, pagamento.getNumerocarta());
            preparedStatement.setString(3, pagamento.getDatascadenza());
            preparedStatement.setInt(4, pagamento.getCode());

            preparedStatement.executeUpdate();
            System.out.println("Pagamento saved: " + pagamento.getTipocarta());

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
    public synchronized boolean doDelete(int idAccount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result = 0;

        String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ACCOUNT + " = ?";

        try {
            connection = ds.getConnection();
            System.out.println("Database connection established for doDelete");

            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, idAccount);

            result = preparedStatement.executeUpdate();
            System.out.println("Spedizione deleted with ID_ACCOUNT: " + idAccount);

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
    public synchronized List<PagamentoBean> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<PagamentoBean> spedizioni = new ArrayList<>();

        String selectSQL = "SELECT " + COLUMN_TIPOCARTA + ", " + COLUMN_NUMCARTA + ", " + COLUMN_SCADENZA + ", " + COLUMN_ACCOUNT + " FROM " + TABLE_NAME;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                PagamentoBean pagamento = new PagamentoBean();
                pagamento.setTipocarta(rs.getString(COLUMN_TIPOCARTA));
                pagamento.setNumerocarta(rs.getString(COLUMN_NUMCARTA));
                pagamento.setDatascadenza(rs.getString(COLUMN_SCADENZA));
                pagamento.setCode(rs.getInt(COLUMN_ACCOUNT));

                pagamento.add(pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving spedizioni from database.", e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return spedizioni;
    }

    @Override
    public PagamentoBean doRetrieveByKey(int idAccount) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        PagamentoBean pagamento = null;

        String selectSQL = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ACCOUNT + " = ?";

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, idAccount);

            rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	pagamento = new PagamentoBean();
            	pagamento.setTipocarta(rs.getString(COLUMN_TIPOCARTA));
            	pagamento.setNumerocarta(rs.getString(COLUMN_NUMCARTA));
            	pagamento.setDatascadenza(rs.getString(COLUMN_SCADENZA));
            	pagamento.setCode(rs.getInt(COLUMN_ACCOUNT));
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

        return pagamento;
    }
}
