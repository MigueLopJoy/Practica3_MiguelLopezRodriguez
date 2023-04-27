package DBManagement;

import Biblioteca.*;
import Gestion.Utils;

import java.sql.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

/**
 * Clase de metodos estaticos utilizados para el manejo de la base de datos del sistema
 *
 * @author Miguel Lopez Rodriguez
 */
public class DBHandler {

    /**
     * Recupera un Prestamo de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye un objeto Prestamo a partir de los datos obtenidos, el cual es devuelto
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un Prestamo de la BDD
     * @return Prestamo recuperado de la BDD
     */
    public static Prestamo getPrestamo(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        Prestamo prestamo = null;
        int idPrestamo;
        Ejemplar ejemplar;
        int idEjemplar;
        Lector lector;
        int idLector;
        LocalDate fechaPrestamo;
        LocalDate fechaDevolucion;
        int devuelto;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idPrestamo = resultset.getInt("idPrestamo");
                idEjemplar = resultset.getInt("idEjemplar");
                ejemplar = getEjemplar("SELECT * FROM ejemplares WHERE idEjemplar = '" + idEjemplar + "';");
                idLector = resultset.getInt("idLector");
                lector = getLector("SELECT * FROM lectores WHERE idLector = " + idLector + ";");
                fechaPrestamo = resultset.getDate("fecha_prestamo").toLocalDate();
                fechaDevolucion = resultset.getDate("fecha_devolucion").toLocalDate();
                devuelto = resultset.getInt("devuelto");
                prestamo = new Prestamo(idPrestamo, ejemplar, lector, fechaPrestamo, fechaDevolucion, devuelto);
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
        return prestamo;
    }

    /**
     * Recupera un conjunto de Prestamos de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye una lista de objetos Prestamo a partir de los datos obtenidos, la cual es devuelta
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de conjunto de Prestamos de la BDD
     * @return Lista de Prestamos construida a partir de los datos recuperados de la BDD
     */
    public static ArrayList<Prestamo> getPrestamos(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        Prestamo prestamo;
        int idPrestamo;
        Ejemplar ejemplar;
        int idEjemplar;
        Lector lector;
        int idLector;
        LocalDate fechaPrestamo;
        LocalDate fechaDevolucion;
        int devuelto;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idPrestamo = resultset.getInt("idPrestamo");
                idEjemplar = resultset.getInt("idEjemplar");
                ejemplar = getEjemplar("SELECT * FROM ejemplares WHERE idEjemplar = '" + idEjemplar + "';");
                idLector = resultset.getInt("idLector");
                lector = getLector("SELECT * FROM lectores WHERE idLector = " + idLector + ";");
                fechaPrestamo = resultset.getDate("fecha_prestamo").toLocalDate();
                fechaDevolucion = resultset.getDate("fecha_devolucion").toLocalDate();
                devuelto = resultset.getInt("devuelto");
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

    /**
     * Recupera un Lector de la BDD en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye un objeto Lector a partir de los datos obtenidos, el cual es devuelto
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un Lector de la BDD
     * @return Lector recuperado de la BDD
     */
    public static Lector getLector(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        Lector lector = null;
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
        return lector;
    }

    /**
     * Recupera un conjunto de Lectores de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye una lista de objetos Lector a partir de los datos obtenidos, la cual es devuelta
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un conjunto de Lectores de la BDD
     * @return Lista de lectores construida a partir de los datos recuperados de la BDD
     */
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


    /**
     * Recupera un Ejemplar de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye un objeto Ejemplar a partir de los datos obtenidos, el cual es devuelto
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un Ejemplar de la BDD
     * @return Ejemplar recuperado de la BDD
     */
    public static Ejemplar getEjemplar(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        Ejemplar ejemplar = null;
        int idEjemplar;
        String codigoEjemplar;
        Libro libro = null;
        int idLibro;

        try {
            statement = connection.createStatement();
            resultset = statement.executeQuery(sql);
            while (resultset.next()) {
                idEjemplar = resultset.getInt("idEjemplar");
                codigoEjemplar = resultset.getString("codigo_ejemplar");
                idLibro = resultset.getInt("idLibro");
                libro = getLibro("SELECT * FROM catalogo WHERE idLibro = " + idLibro + ";");
                ejemplar = new Ejemplar(idEjemplar, codigoEjemplar, libro);
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
        return ejemplar;
    }

    /**
     * Recupera un conjunto de Ejemplares de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye una lista de objetos Ejemplar a partir de los datos obtenidos, la cual es devuelta
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un conjunto de Ejemplares de la BDD
     * @return Lista de Ejemplares construida a partir de los datos recuperados de la BDD
     */
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
                libro = getLibro("SELECT * FROM libros WHERE idLibro = " + idLibro + ";");
                ejemplar = new Ejemplar(idEjemplar, codigoEjemplar, libro);
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


    /**
     * Recupera un Libro de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye un objeto Libro a partir de los datos obtenidos, el cual es devuelto
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un Libro de la BDD
     * @return Libro recuperado de la BDD
     */
    public static Libro getLibro(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        Libro libro = null;
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
                autor = getAutor("SELECT * FROM autores WHERE idAutor = " + idAutor + ";");
                añoPublicacion = Year.of(resultset.getInt("año_publicacion"));
                editorial = resultset.getString("editorial");
                libro = new Libro(idLibro, titulo, autor, añoPublicacion, editorial);
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
        return libro;
    }

    /**
     * Recupera un conjunto de Libros de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye una lista de objetos Libro a partir de los datos obtenidos, la cual es devuelta
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un conjunto de Libros de la BDD
     * @return Lista de Libros construida a partir de los datos recuperados de la BDD
     */
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
                autor = getAutor("SELECT * FROM autores WHERE idAutor = " + idAutor + ";");
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


    /**
     * Recupera un Autor de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye un objeto Autor a partir de los datos obtenidos, el cual es devuelto
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un Autor de la BDD
     * @return Autor recuperado de la BDD
     */
    public static Autor getAutor(String sql) {
        DBConnection dbConnection = new DBConnection("root", "1234");
        Connection connection = dbConnection.getConnection();
        Statement statement = null;
        ResultSet resultset = null;
        Autor autor = null;
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
        return autor;
    }


    /**
     * Recupera un conjunto de Autores de la bdd en base a las restricciones establecidas por la consulta pasada por parametro
     * y consturye una lista de objetos Autor a partir de los datos obtenidos, la cual es devuelta
     *
     * @param sql consulta sql que establece las restricciones para la recuperacion de un conjunto de Autores de la BDD
     * @return Lista de Autores construida a partir de los datos recuperados de la BDD
     */
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


    /**
     * Recupera un numero entero de la BDD a partir de dos datos pasados por parametro: una consulta sql, y un nombre
     * del campo dentro del conjunto de columnas recuperadas por dicha consulta
     *
     * @param sql        consulta sql a partir de la cual recuperar el numero entero buscado
     * @param columnName nombre de la columna dentro del conjunto de campos recuperados de la cual se quiere extraer
     *                   el numero entero buscado
     * @return numero entero encontrado
     */
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

    /**
     * Recupera un numero entero de la BDD a partir de dos datos pasados por parametro: una consulta sql,
     * y una posicion del campo dentro del conjunto de columnas recuperadas por dicha consulta
     *
     * @param sql         consulta sql a partir de la cual recuperar el numero entero buscado
     * @param columnIndex posicion de la columna dentro del conjunto de campos recuperados con la consulta, de la
     *                    cual se quiere extraer el numero entero buscado
     * @return numero entero encontrado
     */
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


    /**
     * Recupera una cadena de texto de la BDD a partir de dos datos pasados por parametro: una consulta sql, y un nombre
     * del campo dentro del conjunto de columnas recuperadas por dicha consulta
     *
     * @param sql        consulta sql a partir de la cual recuperar la cadena de texto buscada
     * @param columnName nombre de la columna dentro del conjunto de campos recuperados de la cual se quiere extraer
     *                   la cadena de texto buscada
     * @return cadena de texto encontrada
     */
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

    /**
     * Recupera una cadena de texto de la BDD a partir de dos datos pasados por parametro: una consulta sql,
     * y una posicion del campo dentro del conjunto de columnas recuperadas por dicha consulta
     *
     * @param sql         consulta sql a partir de la cual recuperar la cadena de texto buscada
     * @param columnIndex posicion de la columna dentro del conjunto de campos recuperados con la consulta, de la
     *                    cual se quiere extraer la cadena de texto buscada
     * @return cadena de texto encontrada
     */
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

    /**
     * Comprueba si la consulta sql pasada por parametro ofrece o no resultados en la BDD
     *
     * @param sql consulta a efectuar para saber si devuelve o no registros
     * @return booleano que indica si existen o no registros a partir de la consulta realizada
     */
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

    /**
     * Efectua una actualizacion de la base de datos a partir de la consulta sql pasada por parametro
     *
     * @param sql consulta sql a partir de la cual efectuar una actualizacion de la base de datos
     */
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

    /**
     * Cierra el objeto Statement pasado por parametro
     *
     * @param statement objeto Statement a cerrar
     */
    public static void closeStatement(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cierra el objeto ResultSet pasado por parametro
     *
     * @param resultset objeto ResultSet a cerrar
     */
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
