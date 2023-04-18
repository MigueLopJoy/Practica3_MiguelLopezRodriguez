package DBManagement;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBHandler {
    public static void executeQuery(Connection DBConnection, String sql) {
        Connection connection = DBConnection;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResultset(resultset);
                closeStatement(statement);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void executeUpdate(Connection DBConnection, String sql) {
        Connection connection = DBConnection;
        Statement statement = null;
        int result = 0;

        try {
            statement = connection.createStatement();
            result = statement.executeUpdate(sql);
            if(result >= 1) {
                System.out.println("Operacion realizada con exito");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeStatement(statement);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static ResultSet getResulset(Connection DBConnection, String sql) {
        Connection connection = DBConnection;
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResultset(resultset);
                closeStatement(statement);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return  resultset;
    }
    public static int obtenerCantidadRegistros(Connection connection, String sql) {
        boolean hayRegistros = false;
        Statement statement = null;
        ResultSet resultSet = null;
        int numeroRegistros = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                numeroRegistros++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeStatement(statement);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return numeroRegistros;
    }
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResultset(ResultSet resultset) {
        try {
            if (resultset != null) {
                resultset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
