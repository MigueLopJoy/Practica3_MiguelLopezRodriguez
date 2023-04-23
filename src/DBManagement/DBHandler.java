package DBManagement;

import Biblioteca.*;
import Gestion.Lectores;
import Gestion.Utils;
import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
public class DBHandler {

    public static ArrayList<Lector> getLectores(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        Statement statement = null;
        ResultSet resultset = null;
        Lector lector;
        Direccion direccion;
        String nombre;
        String apellido1;
        String apellido2;
        String numeroLector;
        String telefono;
        String email;
        Direccion direccion;
        int idDireccion;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                nombre = resultset.getString("nombre");
                apellido1 = resultset.getString("apellido1");
                apellido2 = resultset.getString("apellido2");
                numeroLector = resultset.getString("numero_lector");
                telefono = resultset.getString("telefono");
                email = resultset.getString("email");
                idDireccion = resultset.getInt("idDireccion");
                direccion = getDirecciones("SELECT * FROM direcciones WHERE idDireccion = " + idDireccion + ";").get(0);
                lector = new Lector(nombre, apellido1, apellido2, numeroLector, telefono, email, idDireccion);
                lectores.add(lector);
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
        return lectores;
    }
    public static ArrayList<Direccion> getDirecciones(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Direccion> direcciones = new ArrayList<Direccion>();
        Statement statement = null;
        ResultSet resultset = null;
        Direccion direccion;
        String nombre;
        String apellido1;
        String apellido2;
        String numeroLector;
        String telefono;
        String email;
        int idDireccion;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                nombre = resultset.getString("nombre");
                apellido1 = resultset.getString("apellido1");
                apellido2 = resultset.getString("apellido2");
                numeroLector = resultset.getString("numero_lector");
                telefono = resultset.getString("telefono");
                idDireccion = resultset.getInt("idDireccion");
                direccion = getDirecciones("SELECT * FROM direcciones WHERE idDireccion = " + idDireccion + ";");
                telefono = resultset.getString("telefono");
                libro = getLibros("SELECT * FROM libros WHERE idLibro = " + idLibro + ";").get(0);
                ejemplar = new Ejemplar(codigoEjemplar, libro);
                ejemplares.add(ejemplar);
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
        return direcciones;
    }
    public static ArrayList<Libro> getLibros(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Statement statement = null;
        ResultSet resultset = null;
        Libro libro;
        String titulo;
        Autor autor;
        int idAutor;
        Year añoPublicacion;
        String editorial;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()){
                titulo = resultset.getString("titulo");
                idAutor = resultset.getInt("idAutor");
                autor = getAutores("SELECT * FROM autores WHERE idAutor = " + idAutor + ";").get(0);
                añoPublicacion = Year.of(resultset.getInt("año_publicacion"));
                editorial = resultset.getString("editorial");
                libro = new Libro(titulo, autor, añoPublicacion, editorial);
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
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        ArrayList<Autor> autores = new ArrayList<Autor>();
        Autor autor;
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
                    autor = new Autor(nombre, apellido1, apellido2);
                } else {
                    autor = new Autor(nombre, apellido1);
                }
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
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
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
    public static int getInt(String sql, int columnIndex) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        int result = 0;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            if (resultset.next()){
                result = resultset.getInt(columnIndex);
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
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
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
    public static String getString(String sql, int columnIndex) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        String result = "";

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            if (resultset.next()){
                result = resultset.getString(columnIndex);
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
    public static boolean hayRegistros(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        boolean hayRegistros = false;
        Statement statement = null;
        ResultSet resultSet;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                hayRegistros = true;
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
        return hayRegistros;
    }
    public static void executeUpdate(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
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
