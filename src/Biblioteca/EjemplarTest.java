package Biblioteca;

import DBManagement.DBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class EjemplarTest {

    Autor autor;
    Libro libro;
    Ejemplar ejemplar;
    String sql;
    String expected;

    @BeforeEach
    void setUp() {
        DBHandler.executeUpdate("DELETE FROM ejemplares");
        DBHandler.executeUpdate("DELETE FROM catalogo");
        DBHandler.executeUpdate("DELETE FROM autores");

        DBHandler.executeUpdate("ALTER TABLE ejemplares AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE catalogo AUTO_INCREMENT = 1");
        DBHandler.executeUpdate("ALTER TABLE autores AUTO_INCREMENT = 1");

        autor = new Autor("Fernando", "Aramburu");
        DBHandler.executeUpdate(autor.getInsertString());
        autor.setIdAutor(autor.getIdFromDB());

        libro = new Libro("Patria", autor, Year.of(2011), "Planeta");
        DBHandler.executeUpdate(libro.getInsertString());
        libro.setIdLibro(libro.getIdFromDB());

        DBHandler.executeUpdate("INSERT INTO ejemplares (codigo_ejemplar, idLibro) VALUES ('1234A', 1);");
        ejemplar = DBHandler.getEjemplar("SELECT * FROM ejemplares WHERE codigo_ejemplar = '1234A';");
        System.out.println(ejemplar.getIdEjemplar());

        sql = "";
        expected = "";
    }

    @Test
    void getInsertStringTest() {
        sql = "INSERT INTO ejemplares (codigo_ejemplar, idLibro) VALUES ('" + ejemplar.getCodigoEjemplar() + "', '" + libro.getIdLibro() + "');";
        assertEquals(sql, ejemplar.getInsertString());
    }

    @Test
    void getSelectStringTest() {
        sql =  "SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + ejemplar.getCodigoEjemplar() + "';";
        assertEquals(sql, ejemplar.getSelectString());
    }

    @Test
    void getDeleteStringTest() {
        sql = "DELETE FROM ejemplares WHERE codigo_ejemplar = '" + ejemplar.getCodigoEjemplar() + "';";
        assertEquals(sql, ejemplar.getDeleteString());
    }

    @Test
    void isRegistradoTest() {
        assertTrue(ejemplar.isRegistrado());
        assertFalse(new Ejemplar( "2222B").isRegistrado());
    }

    @Test
    void getIdFromDBTest() {
        assertEquals(ejemplar.getIdEjemplar(), ejemplar.getIdFromDB());
        assertEquals(1, ejemplar.getIdFromDB());
    }

    @Test
    void toStringTest() {
        String toString = libro.toString() + " - Codigo: " + ejemplar.getCodigoEjemplar();
        assertEquals(toString, ejemplar.toString());
    }
}