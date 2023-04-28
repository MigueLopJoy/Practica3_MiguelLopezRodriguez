package Biblioteca;

import DBManagement.DBHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class LectorTest {

    Lector lector1;
    Lector lector2;
    String sql;
    String expected;

    @BeforeEach
    void setUp() {
        DBHandler.executeUpdate("DELETE FROM lectores");
        DBHandler.executeUpdate("ALTER TABLE lectores AUTO_INCREMENT = 1");

        lector1 = new Lector(1, "Miguel", "Lopez Rodriguez", "1234A", "626100833", "m.lop.rod.97@gmail.com");
        lector2 = new Lector(2, "Miguel", "Lopez Rodriguez", "1234A", "626100833");

        DBHandler.executeUpdate(lector1.getInsertString());
        DBHandler.executeUpdate(lector2.getInsertString());

        sql = "";
        expected = "";
    }

    @Test
    void getInsertStringTest() {
        sql = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono, email) "
                + "VALUES ('" + lector1.getNombre() + "', '" + lector1.getApellidos() + "', '" + lector1.getNumeroLector() + "', '"
                + lector1.getTelefono() + "', '" + lector1.getEmail() + "');";
        assertEquals(sql, lector1.getInsertString());

        sql = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono, email) "
                + "VALUES ('" + lector1.getNombre() + "', '" + lector1.getApellidos() + "', '" + lector1.getNumeroLector() + "', '"
                + lector1.getTelefono() + "');";
        assertNotEquals(sql, lector1.getInsertString());

        sql = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono, email) "
                + "VALUES ('" + lector2.getNombre() + "', '" + lector2.getApellidos() + "', '" + lector2.getNumeroLector() + "', '"
                + lector2.getTelefono() + "', '" + lector2.getEmail() + "');";
        assertNotEquals(sql, lector2.getInsertString());

        sql = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono, email) "
                + "VALUES ('" + lector2.getNombre() + "', '" + lector2.getApellidos() + "', '" + lector2.getNumeroLector() + "', '"
                + lector2.getTelefono() + "');";
        assertEquals(sql, lector2.getInsertString());
    }

    @Test
    void getSelectStringTest() {
        sql =  "SELECT * FROM lectores WHERE nombre = '" + lector1.getNombre() + "' AND apellidos = '" + lector1.getApellidos()
                + "' AND numero_lector = '" + lector1.getNumeroLector() + "' AND numero_telefono = '" + lector1.getTelefono()
                + "' AND email = '" + lector1.getEmail() + "';";
        assertEquals(sql, lector1.getSelectString());

        sql =  "SELECT * FROM lectores WHERE nombre = '" + lector1.getNombre() + "' AND apellidos = '" + lector1.getApellidos()
                + "' AND numero_lector = '" + lector1.getNumeroLector() + "' AND numero_telefono = '" + lector1.getTelefono() + "';";
        assertNotEquals(sql, lector1.getSelectString());

        sql =  "SELECT * FROM lectores WHERE nombre = '" + lector2.getNombre() + "' AND apellidos = '" + lector2.getApellidos()
                + "' AND numero_lector = '" + lector2.getNumeroLector() + "' AND numero_telefono = '" + lector2.getTelefono()
                + "' AND email = '" + lector2.getEmail() + "';";
        assertNotEquals(sql, lector1.getSelectString());

        sql =  "SELECT * FROM lectores WHERE nombre = '" + lector2.getNombre() + "' AND apellidos = '" + lector2.getApellidos()
                + "' AND numero_lector = '" + lector2.getNumeroLector() + "' AND numero_telefono = '" + lector2.getTelefono() + "';";
        assertEquals(sql, lector2.getSelectString());
    }

    @Test
    void getUpdateStringTest() {
        sql = "UPDATE lectores SET nombre = '" + lector1.getNombre() + "', apellidos = '" + lector1.getApellidos()
                + "', numero_lector = '" + lector1.getNumeroLector() + "', numero_telefono = '" + lector1.getTelefono()
                + "', email = '" + lector1.getEmail() + "' WHERE idLector = " + lector1.getIdLector();
        assertEquals(sql, lector1.getDeleteString());

        sql = "UPDATE lectores SET nombre = '" + lector1.getNombre() + "', apellidos = '" + lector1.getApellidos()
                + "', numero_lector = '" + lector1.getNumeroLector() + "', numero_telefono = '" + lector1.getTelefono()
                + "' WHERE idLector = " + lector1.getIdLector();
        assertNotEquals(sql, lector1.getDeleteString());

        sql = "UPDATE lectores SET nombre = '" + lector2.getNombre() + "', apellidos = '" + lector2.getApellidos()
                + "', numero_lector = '" + lector2.getNumeroLector() + "', numero_telefono = '" + lector2.getTelefono()
                + "', email = '" + lector2.getEmail() + "' WHERE idLector = " + lector2.getIdLector();
        assertNotEquals(sql, lector2.getDeleteString());

        sql = "UPDATE lectores SET nombre = '" + lector2.getNombre() + "', apellidos = '" + lector2.getApellidos()
                + "', numero_lector = '" + lector2.getNumeroLector() + "', numero_telefono = '" + lector2.getTelefono()
                + "' WHERE idLector = " + lector2.getIdLector();
        assertEquals(sql, lector2.getDeleteString());
    }

    @Test
    void getDeleteStringTest(){
        sql = "DELETE FROM lectores WHERE numero_lector = '" + lector1.getNumeroLector() + "';";
        assertEquals(sql, lector1.getDeleteString());
        assertEquals(sql, lector2.getDeleteString());
    }

    @Test
    void isRegistradoTest() {
        assertTrue(lector1.isRegistrado());
        assertTrue(lector2.isRegistrado());
        assertFalse(new Lector().isRegistrado());
    }

    @Test
    void getIdFromDBTest() {
        assertEquals(lector1.getIdLector(), lector1.getIdFromDB());
        assertEquals(lector2.getIdLector(), lector2.getIdFromDB());
        assertEquals(1, lector1.getIdFromDB());
        assertEquals(2, lector2.getIdFromDB());
    }
}