package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import User.askForData;

import java.time.LocalDate;

public class Catalogo {
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
        LocalDate fechaPublicacion = null;
        String editorial = "";

        titulo = askForData.pedirString("Intorduzca el titulo del libro");
        autor = crearAutor();
        fechaPublicacion = askForData.pedirFecha("Introduzca la fecha de publicacion");
        editorial = askForData.pedirString("Introduzca el nombre de la editorial");
        libro = new Libro(titulo, autor, fechaPublicacion, editorial);
        return libro;
    }

    public static Autor crearAutor() {
        Autor autor = null;
        String nombre = "";
        String apellido1 = "";
        String apellido2 = "";

        nombre = askForData.pedirString("Introduzca el nombre del autor");
        apellido1 = askForData.pedirString("Introduzca el primer apellido del autor");
        apellido2 = askForData.pedirString("Introduzca el segundo apellido del autor (Dejar en blanco si no lo tuviera)");
        autor = new Autor(nombre, apellido1, apellido2);

        return autor;
    }
}
