package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import User.pedirDatos;

import java.lang.reflect.Array;
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

public class Catalogo {
    public static void buscar(int option){
        ArrayList<Libro> libros;
        Autor autor;
        String sql;
        String tituloLibro;

        switch (option) {
            case 1: {
                libros = DBHandler.getLibros("SELECT * FROM catalogo");
                mostrarLibros(libros);
                break;
            }
            case 2: {
                tituloLibro = pedirDatos.pedirString("Introduzca el titulo del libro");
                sql = "SELECT * FROM catalogo WHERE titulo = '" + tituloLibro + "';";
                libros = DBHandler.getLibros(sql);
                mostrarLibros(libros);
                break;
            }
            case 3: {
                autor = crearAutor();
                sql = "SELECT * FROM catalogo c INNER JOIN autores a ON c.idAutor = a.idAutor WHERE nombre = '"
                        + autor.getNombre() + "' AND apellido1 = '" + autor.getApellido1() + "' AND apellido2 = '"
                        + autor.getApellido2() + "';";
                libros = DBHandler.getLibros(sql);
                mostrarLibros(libros);
                break;
            }
        }
    }
    private static void mostrarLibros(ArrayList<Libro> libros) {
        Autor autor;
        String mensaje;

        // Ordenar alfabeticamente por titulos los libros obtendios en la busqueda
        Collections.sort(libros);

        System.out.println("Libros encontrados: " + libros.size());
        for (Libro libro : libros) {
            autor = libro.getAutor();
            mensaje = " - " + libro.getTitulo() + " / " + autor.getNombre() +  " " + autor.getApellido1() + " "
                    + autor.getApellido2() + " - " + libro.getEditorial() + ", " + libro.getAñoPublicacion()
                    + " (Ejemplares: " + getNumEjemplaresEditorial(libro.getEditorial()) + ")";
            System.out.println(mensaje);
        }
    }
    public static int getNumEjemplaresEditorial(String editorial) {
        int numeroEjemplares = 0;
        String sql =
                "SELECT sub.cantidadEjemplares FROM ( " +
                        "SELECT c.editorial, COUNT(e.idEjemplar) cantidadEjemplares FROM ejemplares e " +
                        "INNER JOIN catalogo c ON e.idLibro = c.idLibro GROUP BY c.editorial ) AS sub " +
                        "WHERE sub.editorial = '" + editorial + "';";

        numeroEjemplares = DBHandler.getInt(sql, 1);
        return  numeroEjemplares;
    }
    public static int getCantidadRegistrosTabla(String table){
        int cantidadEjemplares;
        String sql = "SELECT COUNT(*) FROM " + table;
        cantidadEjemplares = DBHandler.getInt(sql, 1);
        return  cantidadEjemplares;
    }
    public static void registrarEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;
        Autor autor = null;

        // Crear ejemplar - Pedir datos del ejemplar - Pedir datos del libro - Pedir datos del autor
        ejemplar = crearEjemplar();
        libro = ejemplar.getLibro();
        autor = libro.getAutor();

        // Comprobar si el autor esta registrarlo para asociarlo al objeto libro actual
        if (autor.isRegistrado()){
            libro.getAutor().setIdAutor(libro.getAutor().getIdAutorFromDB());
        }
        // Comprobar si el libro asociado al ejemplar esta registrado
        if (!libro.isRegistrado()) {
            // Si no lo esta, crearlo y asociarlo al ejemplar.
            registrarLibro(libro);
        } else {
            // Si lo esta, vincular el objeto libro actual con su registro en la BBDD
            libro.setIdLibro(libro.getIdLibroFromDB());
        }
        DBHandler.executeUpdate(ejemplar.getInsertString());
    }
    public static void registrarLibro(Libro libro) {
        Autor autor = libro.getAutor();;
        // Comprobar si el autor asociado al libro esta registrado
        if (!autor.isRegistrado()){
            // Si no lo esta, crearlo y asociarlo al libro
            registrarAutor(autor);
        } else {
            // Si lo esta, vincular el objeto autor actual con su registro en la BBDD
            autor.setIdAutor(autor.getIdAutorFromDB());
        }
        // Registrar libro
        DBHandler.executeUpdate(libro.getInsertString());
        // Almacenar el id del libro asignado en la BBDD
        libro.setIdLibro(libro.getIdLibroFromDB());
    }
    public static void registrarAutor(Autor autor) {
        // Registrar autor
        DBHandler.executeUpdate(autor.getInsertString());
        // Almacenar el id del autor asignado en la BBDD
        autor.setIdAutor(autor.getIdAutorFromDB());
    }
    public static Ejemplar crearEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;

        libro = crearLibro();

        ejemplar = new Ejemplar(libro);
        return ejemplar;
    }
    public static Libro crearLibro() {
        Libro libro = null;
        String titulo = "";
        Autor autor = null;
        Year añoPublicacion = null;
        String editorial = "";

        titulo = pedirDatos.pedirString("Intorduzca el titulo del libro");
        autor = crearAutor();
        añoPublicacion = pedirDatos.pedirAño("Introduzca el año de publicacion");
        editorial = pedirDatos.pedirNombre("Introduzca el nombre de la editorial");
        libro = new Libro(titulo, autor, añoPublicacion, editorial);
        return libro;
    }
    public static Autor crearAutor() {
        Autor autor = null;
        String nombre = "";
        String apellido1 = "";
        String apellido2 = "";

        nombre = pedirDatos.pedirNombre("Introduzca el nombre del autor");
        apellido1 = pedirDatos.pedirNombre("Introduzca el primer apellido del autor");
        apellido2 = pedirDatos.pedirNombre("Introduzca el segundo apellido del autor (Dejar en blanco si no lo tuviera)");
        autor = new Autor(nombre, apellido1, apellido2);

        return autor;
    }
}
