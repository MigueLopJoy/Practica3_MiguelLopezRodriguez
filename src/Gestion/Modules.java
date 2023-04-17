package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.Libro;
import User.askForData;

import java.time.LocalDate;

public class Modules {
    public static void registrarEjemplar() {
        // Crear ejemplar
        // Pedir datos del ejemplar - Pedir datos del libro - Pedir datos del autor
        // Comprobar si el libro asociado al ejemplar existe en la base de datos
        //

        Ejemplar ejemplar = crearEjemplar();

    };
    public static Ejemplar crearEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;

        libro = crearLibro();

        return ejemplar;
    }

    public static Libro crearLibro() {
        Libro libro = null;
        String titulo = "";
        Autor autor = null;
        LocalDate fechaPublicacion = null;
        String editorial = "";

        titulo = askForData.PedirString("Intorduzca el titulo del libro");
        autor = crearAutor();
        libro = new Libro(titulo, autor, fechaPublicacion, editorial);
        return libro;
    }

    public static Autor crearAutor() {
        Autor autor = null;
        String nombre = "";
        String apellido1 = "";
        String apellido2 = "";

        nombre = askForData.PedirString("Introduzca el nombre del autor");
        apellido1 = askForData.pedirString("Introduzca elprimer  apellido del autor");
        apellido2 = askForData.pedirString("Introduzca el segundo apellido del autor (Dejar en blanco si no lo tuviera)");
        autor = new Autor(nombre, apellido1, apellido2);

        return autor;
    }
}
