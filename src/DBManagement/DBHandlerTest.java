package DBManagement;

import Biblioteca.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DBHandlerTest {

    Prestamo prestamo;
    Autor autor;
    Libro libro;
    Ejemplar ejemplar;
    Lector lector;

    String sql;

    String expected;

    @BeforeEach
    void setUp() {
        DBHandler.executeUpdate("DELETE FROM prestamos");
        DBHandler.executeUpdate("DELETE FROM lectores");
        DBHandler.executeUpdate("DELETE FROM ejemplares");
        DBHandler.executeUpdate("DELETE FROM catalogo");
        DBHandler.executeUpdate("DELETE FROM autores");

        DBHandler.executeUpdate("ALTER TABLE prestamos AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE autores AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE catalogo AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE ejemplares AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE lectores AUTO_INCREMENT = 1");

        lector = new Lector(1, "Miguel", "Lopez Rodriguez", "1234A", "626100833", "m.lop.rod.97@gmail.com");
        DBHandler.executeUpdate(lector.getInsertString());
        lector.setIdLector(lector.getIdFromDB());

        autor = new Autor("Fernando", "Aramburu");
        DBHandler.executeUpdate(autor.getInsertString());
        autor.setIdAutor(autor.getIdFromDB());

        libro = new Libro("Patria", autor, Year.of(2011), "Planeta");
        DBHandler.executeUpdate(libro.getInsertString());
        libro.setIdLibro(libro.getIdFromDB());

        DBHandler.executeUpdate("INSERT INTO ejemplares (codigo_ejemplar, idLibro) VALUES ('1234A', 1);");
        ejemplar = DBHandler.getEjemplar("SELECT * FROM ejemplares WHERE codigo_ejemplar = '1234A';");

        prestamo = new Prestamo(1, ejemplar, lector, LocalDate.now(), LocalDate.now().plusDays(15), 0);
        DBHandler.executeUpdate(prestamo.getInsertString());

        sql = "";
        expected = "";
    }

    @Test
    void getPrestamoTest(){
        assertEquals(prestamo, DBHandler.getPrestamo(prestamo.getSelectString()));
        assertEquals(null, DBHandler.getPrestamo("SELECT * FROM prestamos WHERE idPrestamo = 1000;"));
    }

    @Test
    void getPrestamosTest() {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        prestamos.add(prestamo);

        assertEquals(prestamos, DBHandler.getPrestamo("SELECT * FROM prestamos;"));
        assertEquals(new ArrayList<Prestamo>(), DBHandler.getPrestamo("SELECT * FROM prestamos WHERE fecha_devolucion > '2025-01-01';"));
    }

    @Test
    void getLectorTest(){
        assertEquals(lector, DBHandler.getLector(lector.getSelectString()));
        assertEquals(null, DBHandler.getLector("SELECT * FROM lectores WHERE idLector = 1000;"));
    }

    @Test
    void getLectoresTest() {
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        lectores.add(lector);

        assertEquals(lector, DBHandler.getLectores("SELECT * FROM lectores;"));
        assertEquals(new ArrayList<Lector>(), DBHandler.getLectores("SELECT * FROM lectores WHERE idLector > 1000;"));
    }

    @Test
    void getEjemplarTest(){
        assertEquals(lector, DBHandler.getEjemplar(lector.getSelectString()));
        assertEquals(null, DBHandler.getEjemplar("SELECT * FROM lectores WHERE idLector = 1000;"));
    }

    @Test
    void getEjemplaresTest() {
        ArrayList<Ejemplar> ejemplares = new ArrayList<Ejemplar>();
        ejemplares.add(ejemplar);

        assertEquals(ejemplares, DBHandler.getEjemplares("SELECT * FROM ejemplares;"));
        assertEquals(new ArrayList<Lector>(), DBHandler.getEjemplares("SELECT * FROM ejemplares WHERE idEjemplar > 1000;"));
    }

    @Test
    void getLibroTest(){
        assertEquals(libro, DBHandler.getLibro(libro.getSelectString()));
        assertEquals(null, DBHandler.getLibro("SELECT * FROM catalogo WHERE idLibro = 1000;"));
    }

    @Test
    void getLibrosTest() {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        libros.add(libro);

        assertEquals(libros, DBHandler.getLectores("SELECT * FROM catalogo;"));
        assertEquals(new ArrayList<Lector>(), DBHandler.getLectores("SELECT * FROM catalogo WHERE idLibro > 1000;"));
    }

    @Test
    void getAutorTest(){
        assertEquals(autor, DBHandler.getAutor(autor.getSelectString()));
        assertEquals(null, DBHandler.getAutor("SELECT * FROM autores WHERE idAutor = 1000;"));
    }

    @Test
    void getAutoresTest() {
        ArrayList<Autor> autores = new ArrayList<Autor>();
        autores.add(autor);

        assertEquals(autores, DBHandler.getLectores("SELECT * FROM autores;"));
        assertEquals(new ArrayList<Lector>(), DBHandler.getLectores("SELECT * FROM autores WHERE idAutor > 1000;"));
    }

    @Test
    void getIntByIndexTest() {
        assertEquals(1, DBHandler.getInt("SELECT FROM ejemplares WHERE idEjemplar = 1;", 1));
    }

    @Test
    void getIntByColumnNameTest() {
        assertEquals(1, DBHandler.getInt("SELECT FROM ejemplares WHERE idEjemplar = 1;", "idEjemplar"));
    }

    @Test
    void getStringByIndexTest() {
        assertEquals("Miguel", DBHandler.getInt("SELECT FROM lectores WHERE idEjemplar = 2;", 1));
    }

    @Test
    void getStringByColumnNameTest() {
        assertEquals("Miguel", DBHandler.getInt("SELECT FROM lectores WHERE idLector = 1;", "nombre"));
    }

}