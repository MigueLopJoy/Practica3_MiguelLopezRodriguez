package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import User.pedirDatos;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

public class Catalogo {
    public static void consultarCatalogo(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM catalogo")) {
            ArrayList<Libro> libros = buscar(option);
            System.out.println("Libros encontrados: " + libros.size());
            mostrarLibros(libros);
        } else {
            System.out.println("No hay registros disponibles");
        }
    }

    private static ArrayList<Libro> buscar(int option) {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        Autor autor;
        String sql;
        String tituloLibro;

        switch (option) {
            case 1: {
                libros = DBHandler.getLibros("SELECT * FROM catalogo");
                break;
            }
            case 2: {
                tituloLibro = pedirDatos.pedirString("Introduzca el titulo del libro");
                sql = "SELECT * FROM catalogo WHERE titulo = '" + tituloLibro + "';";
                libros = DBHandler.getLibros(sql);
                break;
            }
            case 3: {
                autor = crearAutor();
                sql = "SELECT * FROM catalogo c INNER JOIN autores a ON c.idAutor = a.idAutor WHERE nombre = '"
                        + autor.getNombre() + "' AND apellido1 = '" + autor.getApellido1() + "' AND apellido2 = '"
                        + autor.getApellido2() + "';";
                libros = DBHandler.getLibros(sql);
                break;
            }
        }
        return libros;
    }

    public static void registrarEjemplar() {
        Ejemplar ejemplar;
        Libro libro;

        ejemplar = crearEjemplar();
        libro = ejemplar.getLibro();
        registrarLibro(libro);

        DBHandler.executeUpdate(ejemplar.getInsertString());
    }

    private static void registrarLibro(Libro libro) {
        Autor autor = libro.getAutor();

        if (!libro.isRegistrado()) {
            registrarAutor(autor);
            DBHandler.executeUpdate(libro.getInsertString());
            libro.setIdLibro(libro.getIdFromDB());
        }
    }

    private static void registrarAutor(Autor autor) {
        if (!autor.isRegistrado()) {
            DBHandler.executeUpdate(autor.getInsertString());
            autor.setIdAutor(autor.getIdFromDB());
        }
    }

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

    private static void actualizarLibro() {
        Libro libro;
        Libro nuevosDatos;
        String sql;

        if (DBHandler.hayRegistros("SELECT * FROM catalogo")) {
            libro = escogerLibro("SELECT * FROM catalogo");
            System.out.println("Nuevos datos del libro:");
            nuevosDatos = crearLibro();
            nuevosDatos.setIdLibro(libro.getIdLibro());
            registrarAutor(nuevosDatos.getAutor());
            eliminarAutor(libro.getAutor());
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay libros registrados");
        }
    }

    private static void actualizarAutor() {
        Autor autor;
        Autor nuevosDatos;
        String sql;

        if (DBHandler.hayRegistros("SELECT * FROM AUTORES")) {
            autor = escogerAutor("SELECT * FROM autores");
            System.out.println("Nuevos datos del autor:");
            nuevosDatos = crearAutor();
            nuevosDatos.setIdAutor(autor.getIdAutor());
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay autores registrados");
        }
    }

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
    private static void eliminarEjemplar(Ejemplar ejemplar) {
        if (DBHandler.hayRegistros(ejemplar.getSelectString())) {
            DBHandler.executeUpdate(ejemplar.getDeleteString());
        } else {
            System.out.println("No se encontro el ejemplar");
        }
    }
    private static void eliminarLibro(Libro libro) {
        if (DBHandler.hayRegistros(libro.getSelectString())) {
            if (!DBHandler.hayRegistros("SELECT * FROM ejemplares WHERE idLibro = " + libro.getIdLibro() + ";")) {
                DBHandler.executeUpdate(libro.getDeleteString());
            } else {
                System.out.println("No puede eliminar el libro mientras haya ejemplares vinculados.");
            }
        } else {
            System.out.println("No se ha encontrado el libro");
        }
    }

    private static void eliminarAutor(Autor autor) {
        if (DBHandler.hayRegistros(autor.getSelectString())) {
            if (!DBHandler.hayRegistros("SELECT * FROM catalogo WHERE idAutor = " + autor.getIdAutor() + ";")) {
                DBHandler.executeUpdate(autor.getDeleteString());
            } else {
                System.out.println("No puede eliminar el autor mientras haya libros vinculados.");
            }
        } else {
            System.out.println("No se ha encontrado el autor");
        }
    }

    private static Ejemplar escogerEjemplar(String sql) {
        Ejemplar ejemplar = null;
        String codigoEjemplar;

        if (DBHandler.hayRegistros(sql)) {
            codigoEjemplar = pedirDatos.pedirString("Introduzca el codigo del ejemplar");
            ejemplar = new Ejemplar(codigoEjemplar);
            return ejemplar;
        } else {
            System.out.println("No se encontraron registros");
        }
        return ejemplar;
    }

    private static Libro escogerLibro(String sql) {
        ArrayList<Libro> libros;
        Libro libro = null;

        if (DBHandler.hayRegistros(sql)) {
            libros = DBHandler.getLibros(sql);
            System.out.println("Escoja un libro:");
            mostrarLibros(libros);
            libro = libros.get(pedirDatos.pedirInt(1, libros.size()) - 1);
        } else {
            System.out.println("No se encontraron registros");
        }

        return libro;
    }

    private static Autor escogerAutor(String sql) {
        ArrayList<Autor> autores;
        Autor autor = null;

        if (DBHandler.hayRegistros(sql)) {
            autores = DBHandler.getAutores(sql);
            System.out.println("Escoja un autor:");
            mostrarAutores(autores);
            autor = autores.get(pedirDatos.pedirInt(1, autores.size()) - 1);
        } else {
            System.out.println("No se encontraronr registros");
        }
        return autor;
    }

    private static void mostrarLibros(ArrayList<Libro> libros) {
        Libro libro;
        String mensaje;
        // Ordenar alfabeticamente por titulos los libros obtendios en la busqueda
        Collections.sort(libros);

        for (int i = 0; i < libros.size(); i++) {
            libro = libros.get(i);
            mensaje = " - " + (i + 1) + ". " + libro.toString()
                    + " (Ejemplares: " + getNumEjemplaresEditorial(libro.getEditorial()) + ")";
            ;
            System.out.println(mensaje);
        }
    }

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

    private static Ejemplar crearEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;

        libro = crearLibro();

        ejemplar = new Ejemplar(libro);
        return ejemplar;
    }

    private static Libro crearLibro() {
        Libro libro;
        String titulo;
        Autor autor;
        Year añoPublicacion;
        String editorial;

        titulo = pedirDatos.pedirString(" - Intorduzca el titulo del libro");
        autor = crearAutor();
        añoPublicacion = pedirDatos.pedirAño(" - Introduzca el año de publicacion");
        editorial = pedirDatos.pedirNombre(" - Introduzca el nombre de la editorial");
        libro = new Libro(titulo, autor, añoPublicacion, editorial);
        return libro;
    }

    private static Autor crearAutor() {
        Autor autor;
        String nombre;
        String apellido1;
        String apellido2 = "";

        nombre = pedirDatos.pedirNombre(" - Introduzca el nombre del autor");
        apellido1 = pedirDatos.pedirNombre(" - Introduzca el primer apellido del autor");
        if (pedirDatos.confirmacion("El autor tiene un segundo apellido? (s/n)")) {
            apellido2 = pedirDatos.pedirNombre(" - Introduzca el segundo apellido del autor");
        }
        if (apellido2 != "") {
            autor = new Autor(nombre, apellido1, apellido2);
        } else {
            autor = new Autor(nombre, apellido1);
        }
        return autor;
    }

    private static int getNumEjemplaresEditorial(String editorial) {
        int numeroEjemplares = 0;
        String sql =
                "SELECT sub.cantidadEjemplares FROM ( " +
                        "SELECT c.editorial, COUNT(e.idEjemplar) cantidadEjemplares FROM ejemplares e " +
                        "INNER JOIN catalogo c ON e.idLibro = c.idLibro GROUP BY c.editorial ) AS sub " +
                        "WHERE sub.editorial = '" + editorial + "';";

        numeroEjemplares = DBHandler.getInt(sql, 1);
        return numeroEjemplares;
    }
}
