package Biblioteca;

import DBManagement.DBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class PrestamoTest {

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
    void getInsertStringTest() {
        sql = "INSERT INTO prestamos (idEjemplar, idLector, fecha_prestamo, fecha_devolucion, devuelto) " +
                "VALUES (" + ejemplar.getIdEjemplar() + ", " + lector.getIdLector() + ", '" + prestamo.getFechaPrestamo() + "', '"
                + prestamo.getFechaDevolucion() + "', '" + prestamo.isDevuelto() + "');";
        assertEquals(sql, prestamo.getInsertString());
    }

    @Test
    void getSelectStringTest() {
        sql = "SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + " AND idLector = " + lector.getIdLector() + ";";
        assertEquals(sql, prestamo.getSelectString());
    }

    @Test
    void getUpdateStringTest() {
        sql = "UPDATE prestamos SET devuelto = 1, fecha_devolucion = '" + LocalDate.now() + "' WHERE idPrestamo = "
                + prestamo.getIdPrestamo() + ";";
        assertEquals(sql, prestamo.getUpdateString());
    }

    @Test
    void getDeleteStringTest(){
        sql = "DELETE FROM prestamos WHERE idPrestamo = " + prestamo.getIdPrestamo() + ";";
        assertEquals(sql, prestamo.getDeleteString());
    }

    @Test
    void isRegistradoTest() {
        assertTrue(prestamo.isRegistrado());
        assertFalse(new Prestamo(new Ejemplar(), new Lector()).isRegistrado());
    }

    @Test
    void getIdFromDBTest() {
        assertEquals(libro.getIdLibro(), libro.getIdFromDB());
        assertEquals(1, libro.getIdFromDB());
    }
}