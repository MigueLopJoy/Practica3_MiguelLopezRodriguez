package Biblioteca;

import DBManagement.DBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutorTest {

    Autor autor;
    String sql;
    String expected;

    @BeforeEach
    void setUp() {
        autor = new Autor("Eva María", "Sáenz De Urturi");
        DBHandler.executeUpdate("DELETE FROM autores");
        DBHandler.executeUpdate("ALTER TABLE ejemplares AUTO_INCREMENT = 1");
        DBHandler.executeUpdate(autor.getInsertString());
        autor.setIdAutor(autor.getIdFromDB());
        sql = "";
        expected = "";
    }

    @Test
    void getInsertStringTest() {
        sql = "INSERT INTO autores (nombre, apellidos) VALUES ('" + autor.getNombre() + "', '" + autor.getApellidos() + "');";
        assertEquals(sql, autor.getInsertString());
    }

    @Test
    void getSelectStringTest() {
        sql = "SELECT * FROM autores WHERE nombre = '" + autor.getNombre() + "' AND apellidos = '" + autor.getApellidos() + "';";
        assertEquals(sql, autor.getSelectString());
    }

    @Test
    void getUpdateStringTest() {
        sql = "UPDATE autores SET nombre = '" + autor.getNombre() + "', apellidos = '" + autor.getApellidos() + "' WHERE idAutor = " + autor.getIdAutor() + ";";
        assertEquals(sql, autor.getUpdateString());
    }

    @Test
    void getDeleteStringTest() {
        sql = "DELETE FROM autores WHERE idAutor = " + autor.getIdAutor();
        assertEquals(sql, autor.getDeleteString());
    }

    @Test
    void isRegistradoTest() {
        assertTrue(autor.isRegistrado());
        assertFalse(new Autor( "Dolores", "Redondo").isRegistrado());
    }

    @Test
    void getIdFromDBTest() {
        assertEquals(autor.getIdAutor(), autor.getIdFromDB());
        assertEquals(1, autor.getIdFromDB());
    }
}