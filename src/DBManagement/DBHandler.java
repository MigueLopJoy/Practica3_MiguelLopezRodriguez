package DBManagement;

import Biblioteca.Autor;
import Biblioteca.Libro;
import Gestion.Utils;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class DBHandler {
    public static ArrayList<Libro> getLibros(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Statement statement = null;
        ResultSet resultset = null;
        Libro libro = null;
        String titulo;
        Autor autor = null;
        int idAutor;
        LocalDate fechaPublicacion;
        String editorial;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                titulo = resultset.getString("titulo");
                idAutor = resultset.getInt("idAutor");
                autor = getAutores("SELECT * FROM autores WHERE idAutor = '" + idAutor + "';").get(0);
                fechaPublicacion = resultset.getDate("fecha_publicacion").toLocalDate();
                editorial = resultset.getString("editorial");
                libro = new Libro(titulo, autor, fechaPublicacion, editorial);
                libros.add(libro);
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
        return libros;
    }
    public static ArrayList<Autor> getAutores(String sql) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        ArrayList<Autor> autores = new ArrayList<Autor>();
        Statement statement = null;
        ResultSet resultset = null;
        Autor autor = null;
        String nombre;
        String apellido1;
        String apellido2;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                nombre = resultset.getString("nombre");
                apellido1 = resultset.getString("apellido1");
                if (resultset.getString("apellido2") != null) {
                    apellido2 = resultset.getString("apellido2");
                } else {
                    apellido2 = "";
                }
                autor = new Autor(nombre, apellido1, apellido2);
                autores.add(autor);
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
        return autores;
    }
    public static int getInt(String sql, String columnName) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        int result = 0;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            if (resultset.next()){
                result = resultset.getInt(columnName);
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
        return result;
    }

    public static String getString(String sql, String columnName) {
        MySQLConnection mySQLConnection = new MySQLConnection("root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        String result = "";

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            if (resultset.next()){
                result = resultset.getString(columnName);
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
        return result;
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
