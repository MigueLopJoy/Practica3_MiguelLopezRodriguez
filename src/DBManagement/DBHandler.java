package DBManagement;

import Gestion.Utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBHandler {
    public static void executeQuery(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
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
    public static int getId(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        int id = -1;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);

            if (resultset.next()) {
                id = resultset.getInt(1);
            }
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
        return id;
    }
    public static void executeUpdate(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Statement statement = null;
        int result = 0;

        try {
            statement = connection.createStatement();
            result = statement.executeUpdate(sql);
            if(result >= 1) {
                System.out.println(Utils.obtenerMensajeExecuteUpdate(sql));
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
    public static ResultSet getResulset(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
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
        return  resultset;
    }
    public static int obtenerCantidadRegistros(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
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
