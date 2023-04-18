package Gestion;

import Biblioteca.Autor;
import Biblioteca.Ejemplar;
import Biblioteca.FechaPublicacion;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import DBManagement.MySQLConnection;
import User.askForData;

import java.sql.Connection;
import java.time.LocalDate;

public class Catalog {
    Connection connection;

    public Catalog(Connection connection) {
        this.connection = connection;
    }
    public void registrarEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;
        // Crear ejemplar - Pedir datos del ejemplar - Pedir datos del libro - Pedir datos del autor
        ejemplar = crearEjemplar();
        libro = ejemplar.getLibro();
        // Comprobar si el libro asociado al ejemplar esta registrado
        if (!libro.isRegistrado(connection)) {
            // Si no lo esta, crearlo y asociarlo al ejemplar.
            registrarLibro(libro);
        }
        DBHandler.executeUpdate(connection, ejemplar.getInsertString());
        ejemplar.setIdEjemplar(crearEjemplar().getIdEjemplar());
    }
    public void registrarLibro(Libro libro) {
        Autor autor = libro.getAutor();;
        // Comprobar si el autor asociado al libro esta registrado
        if (!autor.isRegistrado(connection)){
            // Si no lo esta, crearlo y asociarlo al libro.
            registrarAutor(autor);
        }
        // Registrar libro
        DBHandler.executeUpdate(connection, libro.getInsertString());
        // Almacenar el id del libro asignado en la BBDD
        libro.setIdLibro(libro.getIdLibroFromDB(connection));
    }
    public void registrarAutor(Autor autor) {
        // Registrar autor
        DBHandler.executeUpdate(connection, autor.getInsertString());
        // Almacenar el id del autor asignado en la BBDD
        autor.setIdAutor(autor.getIdAutorFromDB(connection));
    }
    public Ejemplar crearEjemplar() {
        Ejemplar ejemplar = null;
        Libro libro = null;

        libro = crearLibro();

        ejemplar = new Ejemplar(libro);
        return ejemplar;
    }
    public Libro crearLibro() {
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

    public Autor crearAutor() {
        Autor autor = null;
        String nombre = "";
        String apellido1 = "";
        String apellido2 = "";

        nombre = askForData.pedirString("Introduzca el nombre del autor");
        apellido1 = askForData.pedirString("Introduzca elprimer  apellido del autor");
        apellido2 = askForData.pedirString("Introduzca el segundo apellido del autor (Dejar en blanco si no lo tuviera)");
        autor = new Autor(nombre, apellido1, apellido2);

        return autor;
    }
}
