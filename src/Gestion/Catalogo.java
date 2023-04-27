package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import User.pedirDatos;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que contiene todos los metodos necesarios para la gestion del modulo Catalogo (registro y correccion de datos de
 * ejemplares, libros y autores, asi como de la busqueda y eliminacion de los mismos)
 *
 * @author Miguel Lopez Rodriguez
 */
public class Catalogo {

    /**
     * Recupera una lista de libros mediante una llamada al metodo encargado de realizar una busqueda en funcion de una
     * opcion de busqueda indicada por parametro, y hace una llamada al metodo encargado de mostrar esta lista de libros
     * por consola
     *
     * @param option opciond e busqueda escogida por el usuario y que sera enviada al metodo encargado de realizar la busqueda de prestamos
     */
    public static void consultarCatalogo(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM catalogo")) {
            ArrayList<Libro> libros = buscar(option);
            System.out.println("Libros encontrados: " + libros.size());
            mostrarLibros(libros);
        } else {
            System.out.println("No hay registros disponibles");
        }
    }

    /**
     * Devuelve una lista con el /los libros recuperados de una busqeuda efectuada en base a una opcion de busqueda ecogida por el usuario
     *
     * @param option opcion de busqueda previamente escogida por el usuario
     * @return lista de libros recuperada de la busqeuda del usuario
     */
    private static ArrayList<Libro> buscar(int option) {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Autor autor;
        String sql;
        String tituloLibro;

        switch (option) {
            case 1: {
                // Recupera la lista de todos los libros disponibles en el catalogo
                libros = DBHandler.getLibros("SELECT * FROM catalogo");
                break;
            }
            case 2: {
                // Recupera la lista de todos los libros con un titulo indicado por el usuario
                libros = DBHandler.getLibros(getSQLBusquedaTitulo());
                break;
            }
            case 3: {
                // Recupera la lista de todos los libros de un autor indicado por el usuario
                libros = DBHandler.getLibros(getSQLBusquedaAutor());
                break;
            }
        }
        return libros;
    }

    /**
     * Pide al usuario el titulo de un libro y construye una query de busqueda en el catalogo a partir de dicho titulo
     *
     * @return query de busqeuda en el catalogo por titulo del libro
     */
    private static String getSQLBusquedaTitulo() {
        String tituloLibro = pedirDatos.pedirString("Introduzca el titulo del libro");
        String sql = "SELECT * FROM catalogo WHERE titulo = '" + tituloLibro + "';";
        return sql;
    }

    /**
     * Crea un nuevo autor y construye una query de busqueda de libros en el catalogo a partir de los datos del autor
     *
     * @return query de busqueda de libros a partir de los datos del autor
     */
    private static String getSQLBusquedaAutor() {
        Autor autor = crearAutor();
        String sql = "SELECT * FROM catalogo c INNER JOIN autores a ON c.idAutor = a.idAutor WHERE nombre = '" + autor.getNombre() + "' AND apellidos = '" + autor.getApellidos() + "';";
        return sql;
    }

    /**
     * Crea un nuevo ejemplar, registra el libro vinculado a este si no existiera en la bdd,
     * y finalmente registra el ejemplar
     */
    public static void registrarEjemplar() {
        Ejemplar ejemplar;
        Libro libro;

        ejemplar = crearEjemplar();
        libro = ejemplar.getLibro();
        registrarLibro(libro);

        DBHandler.executeUpdate(ejemplar.getInsertString());
    }

    /**
     * Comrpueba que el libro pasado por parametro no esta registrado en la bdd, llama al metodo encargado de registrar
     * al autor del libro si este no lo estuviere, y finalmente registra el libro
     *
     * @param libro libro a registrar en la bdd
     */
    private static void registrarLibro(Libro libro) {
        Autor autor = libro.getAutor();
        // Comrpueba que el libro no este registrado
        if (!libro.isRegistrado()) {
            // Registra al autor del libro si no lo estuviera
            registrarAutor(autor);
            // Registra el libro
            DBHandler.executeUpdate(libro.getInsertString());
            // Inserta en el objeto libro el id asignado en la bdd
            libro.setIdLibro(libro.getIdFromDB());
        }
    }

    /**
     * Comprueba que el autor pasado por parametro no esta registrado en la bdd y lo registra
     *
     * @param autor autor a registrar en la bdd
     */
    private static void registrarAutor(Autor autor) {
        // Comprueba que el autor no este registrado en la bdd
        if (!autor.isRegistrado()) {
            // Registra al autor
            DBHandler.executeUpdate(autor.getInsertString());
            // Inserta en el objeto autor el id asignado en la bdd
            autor.setIdAutor(autor.getIdFromDB());
        }
    }

    /**
     * Llama al metodo de actualizacion de libros o al de actualizacion de autores en funcion de la opcion escogida por el usuario
     *
     * @param option opcion de actualizacion escogida por el usuario
     */
    public static void actualizar(int option) {
        switch (option) {
            case 1: {
                actualizarLibro();
                break;
            }
            case 2: {
                actualizarAutor();
                break;
            }
        }
    }

    /**
     * Permite escoger un libro de la bdd y modificar los datos del registro. Si los datos del autor cambian,
     * registra un nuevo autor y, en caso de que el anterior no tuviese mas libros vinculados, lo elimina.
     */
    private static void actualizarLibro() {
        Libro libro;
        Libro nuevosDatos;
        String sql;

        // Comprueba que hay libros registrados en la bdd
        if (DBHandler.hayRegistros("SELECT * FROM catalogo")) {
            // Escoge el libro
            libro = escogerLibro("SELECT * FROM catalogo");
            // Crea un nuevo libro e inserta en el el id asignado en la bdd al libro que se quiere modificar
            System.out.println("Nuevos datos del libro:");
            nuevosDatos = crearLibro();
            nuevosDatos.setIdLibro(libro.getIdLibro());
            // Registra al autor del libro en caso de que se hayan modificado estos datos
            registrarAutor(nuevosDatos.getAutor());
            // Comprueba si el autor asociado en un principio al libro seleccionado no esta vinculado a ningun libro y,
            // si es asi, lo elimina
            if (!librosVinculados(libro.getAutor())) {
                eliminarAutor(libro.getAutor());
            }
            // Ejecuta la sentencia de actualizacion obtenida del nuevo libro creado, que afecta al libro registrado
            // en la bdd gracias al id asignado al nuevo objeto
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay libros registrados");
        }
    }

    /**
     * Permite escoger un autor de la bdd y modificar los datos del registro
     */
    private static void actualizarAutor() {
        Autor autor;
        Autor nuevosDatos;
        String sql;

        // Comprueba que hay autores registrados en la bdd
        if (DBHandler.hayRegistros("SELECT * FROM AUTORES")) {
            // Escoge el autor a modificar
            autor = escogerAutor("SELECT * FROM autores");
            // Crea un nuevo autor, al cual se le inserta el id asignado en la bdd al autor escogido
            System.out.println("Nuevos datos del autor:");
            nuevosDatos = crearAutor();
            nuevosDatos.setIdAutor(autor.getIdAutor());
            // Ejecuta la sentencia de actualizacion obtenida del nuevo autor creado, que afecta al autor registrado
            // en la bdd gracias al id asignado al nuevo objeto
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay autores registrados");
        }
    }

    /**
     * Llama al metodo encargado de eliminar un ejemplar, un libro o un autor, en funcion de la opcion escogida por el usuario
     *
     * @param option Opcion de eliminacion escogida por el usuario
     */
    public static void eliminar(int option) {
        switch (option) {
            case 1: {
                eliminarEjemplar(escogerEjemplar("SELECT * FROM ejemplares"));
                break;
            }
            case 2: {
                eliminarLibro(escogerLibro("SELECT * FROM catalogo"));
                break;
            }
            case 3: {
                eliminarAutor(escogerAutor("SELECT * FROM autores"));
                break;
            }
        }
    }


    /**
     * Permite eliminar un ejemplar de la bdd siempre que no haya prestamos vinculados al mismo.
     * Tambien elimina al libro asociado al ejemplar si ya no tuviera mas ejemplares vinculados.
     *
     * @param ejemplar ejemplar a eliminar
     */
    private static void eliminarEjemplar(Ejemplar ejemplar) {
        if (ejemplar != null) {
            if (DBHandler.hayRegistros(ejemplar.getSelectString())) {
                if (!prestamosVinculados(ejemplar)) {
                    DBHandler.executeUpdate(ejemplar.getDeleteString());
                    // Comprueba si hay ejemplares vinculados al libro asociado al ejemplar eliminado.
                    // Si no los hubiera, elimina al libro
                    if (!ejemplaresVinculados(ejemplar.getLibro())) {
                        eliminarLibro(ejemplar.getLibro());
                    }
                } else {
                    System.out.println("No puede eliminar el ejemplar mientras haya prestamos vinculados.");
                }
            } else {
                System.out.println("No se encontro el ejemplar");
            }
        }
    }

    /**
     * Permite eliminar un libro siempre que no tenga ejemplares vinculados. Tambien elimina al autor
     * del libro si ya no tiene mas libros vinculados
     *
     * @param libro libro a eliminar
     */
    private static void eliminarLibro(Libro libro) {
        if (libro != null) {
            if (DBHandler.hayRegistros(libro.getSelectString())) {
                if (!ejemplaresVinculados(libro)) {
                    DBHandler.executeUpdate(libro.getDeleteString());
                    // Comprueba si hay autores vinculados al libro eliminado. Si no los hubiera, lo elimina
                    if (!librosVinculados(libro.getAutor())) {
                        eliminarAutor(libro.getAutor());
                    }
                } else {
                    System.out.println("No puede eliminar el libro mientras haya ejemplares vinculados.");
                }
            } else {
                System.out.println("No se encontro el libro");
            }
        }
    }

    /**
     * Permite eliminar un autor siempre que no tenga libros vinculados
     *
     * @param autor autor a eliminar
     */
    private static void eliminarAutor(Autor autor) {
        if (autor != null) {
            if (DBHandler.hayRegistros(autor.getSelectString())) {
                if (!librosVinculados(autor)) {
                    DBHandler.executeUpdate(autor.getDeleteString());
                } else {
                    System.out.println("No puede eliminar el autor mientras haya libros vinculados.");
                }
            } else {
                System.out.println("No se encontro el autor");
            }
        }
    }


    /**
     * Comrpueba si existen prestamos vinculados al ejemplar pasado por parametro
     *
     * @param ejemplar ejemplar del cual se quiere saber si existen prestamos a el vinculados
     * @return booleano que indica si hay o no prestamos vinculados al ejemplar
     */
    private static boolean prestamosVinculados(Ejemplar ejemplar) {
        boolean prestamosVinuclados = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + ";")) {
            prestamosVinuclados = true;
        }
        return prestamosVinuclados;
    }


    /**
     * Comprueba si existen ejemplares vinculados al libro pasado por parametro
     *
     * @param libro libro del cual se quiere saber si existen ejemplares vinculados
     * @return booleano que indica si existen ejemplares vinculados al libro
     */
    private static boolean ejemplaresVinculados(Libro libro) {
        boolean ejemplaresVinculados = false;
        if (DBHandler.hayRegistros("SELECT * FROM ejemplares WHERE idLibro = " + libro.getIdLibro() + ";")) {
            ejemplaresVinculados = true;
        }
        return ejemplaresVinculados;
    }


    /**
     * Comprueba si existen libros vinculados al autor pasado por parametro
     *
     * @param autor autor del cual se quiere saber si existen ejemplares vinculados
     * @return booleano que indica si existen libros vinculados al autor
     */
    private static boolean librosVinculados(Autor autor) {
        boolean librosVinculados = false;
        if (DBHandler.hayRegistros("SELECT * FROM catalogo WHERE idAutor = " + autor.getIdAutor() + ";")) {
            librosVinculados = true;
        }
        return librosVinculados;
    }

    /**
     * Permite escoger un ejemplar mediante la introduccion de su codigo de ejemplar
     *
     * @param sql sentencia sql que define un ambito de busqueda en el cual se comprobara si existen registros
     * @return ejemplar escogido por el usuario
     */
    public static Ejemplar escogerEjemplar(String sql) {
        Ejemplar ejemplar = null;
        String codigoEjemplar;
        String escogerEjemplarSQL;

        // Comprueba que existan registros en el ambito de busqueda definido por la sentencia sql pasada por parametro
        if (DBHandler.hayRegistros(sql)) {
            codigoEjemplar = pedirDatos.pedirCodigo("Introduzca el codigo del ejemplar");
            escogerEjemplarSQL = "SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + codigoEjemplar + "';";
            // Comprueba que el ejemplar con el codigo señalado existe
            if (DBHandler.hayRegistros(escogerEjemplarSQL)) {
                ejemplar = DBHandler.getEjemplar(escogerEjemplarSQL);
            } else {
                System.out.println("No se encontro el ejemplar buscado");
            }
        } else {
            System.out.println("No se encontraron ejemplares registrados");
        }
        return ejemplar;
    }

    /**
     * Permite escoger un libro mediante la seleccion de entre los libros desplegados en un listado
     *
     * @param sql sentencia sql que define un ambito de busqueda en el cual se comprobara si existen registros
     * @return libro escogido por el usuario
     */
    private static Libro escogerLibro(String sql) {
        ArrayList<Libro> libros;
        Libro libro = null;

        // Comprueba que existan registros en el ambito de busqueda definido por la sentencia sql pasada por parametro
        if (DBHandler.hayRegistros(sql)) {
            libros = DBHandler.getLibros(sql);
            System.out.println("Escoja un libro:");
            mostrarLibros(libros);
            libro = libros.get(pedirDatos.pedirInt(1, libros.size()) - 1);
        } else {
            System.out.println("No se encontraron registros en el catalogo");
        }
        return libro;
    }

    /**
     * Permite escoger un autor mediante la seleccion de entre los autores desplegados en un listado
     *
     * @param sql sentencia sql que define un ambito de busqueda en el cual se comprobara si existen registros
     * @return autor escogido por el usuario
     */
    private static Autor escogerAutor(String sql) {
        ArrayList<Autor> autores;
        Autor autor = null;

        // Comprueba que existan registros en el ambito de busqueda definido por la sentencia sql pasada por parametro
        if (DBHandler.hayRegistros(sql)) {
            autores = DBHandler.getAutores(sql);
            System.out.println("Escoja un autor:");
            mostrarAutores(autores);
            autor = autores.get(pedirDatos.pedirInt(1, autores.size()) - 1);
        } else {
            System.out.println("No se encontraronr autores registrados");
        }
        return autor;
    }

    /**
     * Muestra por consola el listado de libros pasado por parametros ordenandolo previamente
     *
     * @param libros listado de libros a mostrar por consola
     */
    private static void mostrarLibros(ArrayList<Libro> libros) {
        Libro libro;
        String mensaje;
        // Ordenar alfabeticamente por titulos los libros obtendios en la busqueda
        Collections.sort(libros);

        for (int i = 0; i < libros.size(); i++) {
            libro = libros.get(i);
            mensaje = " - " + (i + 1) + ". " + libro.toString() + " (Ejemplares: " + getNumEjemplaresEditorial(libro.getEditorial()) + ")";
            ;
            System.out.println(mensaje);
        }
    }

    /**
     * Muestra por consola un listado de autores pasado por parametro, ordenandolo previamente
     *
     * @param autores
     */
    private static void mostrarAutores(ArrayList<Autor> autores) {
        Autor autor;
        String mensaje;
        // Ordenar alfabeticamente por titulos los libros obtendios en la busqueda
        Collections.sort(autores);

        for (int i = 0; i < autores.size(); i++) {
            autor = autores.get(i);
            mensaje = " - " + (i + 1) + ". " + autor.toString();
            System.out.println(mensaje);
        }
    }

    /**
     * Crea un nuevo ejemplar pidiendo los datos necesarios al usuario, incluido el libro vinculado al mismo
     *
     * @return nuevo ejemplar creado
     */
    private static Ejemplar crearEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;

        libro = crearLibro();

        ejemplar = new Ejemplar(libro);
        return ejemplar;
    }

    /**
     * Crea un nuevo libro pidiendo sus datos al usuario, incluido los del autor vinculado al mismo
     *
     * @return nuevo libro creado
     */
    private static Libro crearLibro() {
        Libro libro;
        String titulo;
        Autor autor;
        Year añoPublicacion;
        String editorial;

        titulo = pedirDatos.pedirString(" - Intorduzca el titulo del libro");
        autor = crearAutor();
        añoPublicacion = pedirDatos.pedirAño(" - Introduzca el año de publicacion");
        editorial = pedirDatos.pedirString(" - Introduzca el nombre de la editorial");
        libro = new Libro(titulo, autor, añoPublicacion, editorial);
        return libro;
    }

    /**
     * Crea un nuevo autor pidiendo sus datos al usaurio
     *
     * @return nuevo autor credo
     */
    private static Autor crearAutor() {
        Autor autor;
        String nombre;
        String apellidos;

        nombre = pedirDatos.pedirNombre(" - Introduzca el nombre del autor");
        apellidos = pedirDatos.pedirNombre(" - Introduzca los apellidos del autor");
        autor = new Autor(nombre, apellidos);

        return autor;
    }

    /**
     * Permite conocer el numero de ejemplares vinculados a un libro editado por una determinada editorial
     *
     * @param editorial Nombre de la editorial que edita un libro del cual se quiere saber cuantos ejemplares existe
     * @return numero de ejemplares de un libro editado por la editorial indicada
     */
    private static int getNumEjemplaresEditorial(String editorial) {
        int numeroEjemplares = 0;
        String sql = "SELECT sub.cantidadEjemplares FROM ( " + "SELECT c.editorial, COUNT(e.idEjemplar) cantidadEjemplares FROM ejemplares e " + "INNER JOIN catalogo c ON e.idLibro = c.idLibro GROUP BY c.editorial ) AS sub " + "WHERE sub.editorial = '" + editorial + "';";

        numeroEjemplares = DBHandler.getInt(sql, 1);
        return numeroEjemplares;
    }
}
