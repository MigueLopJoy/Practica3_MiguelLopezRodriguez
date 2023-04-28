package Biblioteca;

import DBManagement.DBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class LibroTest {

    Autor autor;
    Libro libro;

    String sql;
    String expected;

    @BeforeEach
    void setUp() {
        DBHandler.executeUpdate("DELETE FROM autores");
        DBHandler.executeUpdate("DELETE FROM catalogo");

        DBHandler.executeUpdate("ALTER TABLE autores AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE catalogo AUTO_INCREMENT = 1");

        autor = new Autor("Emilia", "Pardo Bazan");
        libro = new Libro("La Tribuna", autor, Year.of(1882), "Círculo De Lectores");

        DBHandler.executeUpdate(autor.getInsertString());
        DBHandler.executeUpdate(libro.getInsertString());

        sql = "";
        expected = "";
    }

    @Test
    void getInsertStringTest() {
        sql = "INSERT INTO catalogo (titulo, idAutor, año_publicacion, editorial) VALUES ('" + libro.getTitulo() + "', '"
                + autor.getIdAutor() + "', '" + libro.getAñoPublicacion() + "',  '" + libro.getEditorial() + "');";
        assertEquals(sql, libro.getInsertString());
    }

    @Test
    void getSelectStringTest() {
        sql = "SELECT * FROM catalogo WHERE titulo = '" + libro.getTitulo() + "' AND idAutor = " + autor.getIdAutor()
            + " AND año_publicacion = '" + libro.getAñoPublicacion() + "' AND editorial = '" + libro.getEditorial() + "';";

        assertEquals(sql, libro.getSelectString());
    }

    @Test
    void getUpdateStringTest() {
        sql = "UPDATE catalogo SET titulo = '" + libro.getTitulo() + "', idAutor = " + libro.getAutor().getIdAutor()
                + ", año_publicacion = " + libro.getAñoPublicacion() + ", editorial = '" + libro.getEditorial()
                + "' WHERE idLibro = " + libro.getIdLibro();
        assertEquals(sql, libro.getDeleteString());
    }

    @Test
    void getDeleteStringTest(){
        sql = "DELETE FROM catalogo WHERE idLibro = " + libro.getIdLibro();
        assertEquals(sql, libro.getDeleteString());
    }

    @Test
    void isRegistradoTest() {
        assertTrue(libro.isRegistrado());
        assertFalse(new Lector().isRegistrado());
    }

    @Test
    void getIdFromDBTest() {
        assertEquals(libro.getIdLibro(), libro.getIdFromDB());
        assertEquals(1, libro.getIdFromDB());
    }

}