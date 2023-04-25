package DBManagement;

import Biblioteca.*;
import Gestion.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

public class DBHandler {

    public static ArrayList<Prestamo> getPrestamos(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        Statement statement = null;
        ResultSet resultset = null;
        Prestamo prestamo;
        int idPrestamo;
        Ejemplar ejemplar;
        int idEjemplar;
        Lector lector;
        int idLector;
        LocalDate fechaPrestamo;
        LocalDate fechaDevolucion;
        Boolean devuelto;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idPrestamo = resultset.getInt("idPrestamo");
                idEjemplar = resultset.getInt("idEjemplar");
                ejemplar = getEjemplares("SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + idEjemplar + "';").get(0);
                idLector = resultset.getInt("idLector");
                lector = getLectores("SELECT * FROM lectores WHERE idLector = " + idLector + ";").get(0);
                fechaPrestamo = resultset.getDate("fecha_prestamo").toLocalDate();
                fechaDevolucion = resultset.getDate("fecha_devolucion").toLocalDate();
                devuelto = resultset.getBoolean("devuelto");
                prestamo = new Prestamo(idPrestamo, ejemplar, lector, fechaPrestamo, fechaDevolucion, devuelto);
                prestamos.add(prestamo);
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
        return prestamos;
    }
    public static ArrayList<Lector> getLectores(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        Statement statement = null;
        ResultSet resultset = null;
        Lector lector;
        int idLector;
        String nombre;
        String apellidos;
        String numeroLector;
        String telefono;
        String email;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idLector = resultset.getInt("idLector");
                nombre = resultset.getString("nombre");
                apellidos = resultset.getString("apellidos");
                numeroLector = resultset.getString("numero_lector");
                telefono = resultset.getString("numero_telefono");
                email = resultset.getString("email");
                if (!resultset.wasNull()) {
                    lector = new Lector(idLector, nombre, apellidos, numeroLector, telefono, email);
                } else {
                    lector = new Lector(idLector, nombre, apellidos, numeroLector, telefono);
                }
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

    public static ArrayList<Ejemplar> getEjemplares(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
        Statement statement = null;
        ResultSet resultset = null;
        Ejemplar ejemplar;
        int idEjemplar;
        String codigoEjemplar;
        Libro libro;
        int idLibro;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idEjemplar = resultset.getInt("idEjemplar");
                codigoEjemplar = resultset.getString("codigo_ejemplar");
                idLibro = resultset.getInt("idLibro");
                libro = getLibros("SELECT * FROM autores WHERE idAutor = " + idLibro + ";").get(0);
                ejemplar = new Ejemplar(idEjemplar,codigoEjemplar, libro);
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
        return ejemplares;
    }
    public static ArrayList<Libro> getLibros(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Statement statement = null;
        ResultSet resultset = null;
        Libro libro;
        int idLibro;
        String titulo;
        Autor autor;
        int idAutor;
        Year añoPublicacion;
        String editorial;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idLibro = resultset.getInt("idLibro");
                titulo = resultset.getString("titulo");
                idAutor = resultset.getInt("idAutor");
                autor = getAutores("SELECT * FROM autores WHERE idAutor = " + idAutor + ";").get(0);
                añoPublicacion = Year.of(resultset.getInt("año_publicacion"));
                editorial = resultset.getString("editorial");
                libro = new Libro(idLibro, titulo, autor, añoPublicacion, editorial);
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
        int idAutor;
        String nombre;
        String apellidos;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idAutor = resultset.getInt("idAutor");
                nombre = resultset.getString("nombre");
                apellidos = resultset.getString("apellidos");
                autor = new Autor(idAutor, nombre, apellidos);
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
            if (resultset.next()) {
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
            if (resultset.next()) {
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
            if (resultset.next()) {
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
            if (resultset.next()) {
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
            if (result >= 1) {
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
