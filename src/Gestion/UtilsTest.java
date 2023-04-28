package Gestion;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void validarNombreTrue() {
        assertTrue(Utils.validarNombre("Miguel"));
        assertTrue(Utils.validarNombre("Miguel López"));
        assertTrue(Utils.validarNombre("Miguel Lopez"));
        assertTrue(Utils.validarNombre("miguel"));
        assertTrue(Utils.validarNombre("miguel lopez"));
    }

    @Test
    void validarNombreFalse(){
        assertFalse(Utils.validarNombre("1123"));
        assertFalse(Utils.validarNombre("Migu12el"));
        assertFalse(Utils.validarNombre(".-_"));
        assertFalse(Utils.validarNombre("Miguel."));
        assertFalse(Utils.validarNombre(""));
        assertFalse(Utils.validarNombre(" "));
    }

    @Test
    void validarTelefonoTrue(){
        assertTrue(Utils.validarTelefono("666666666"));
    }

    @Test
    void validarTelefonoFalse(){
        assertFalse(Utils.validarTelefono("abc"));
        assertFalse(Utils.validarTelefono("+34 666666666"));
        assertFalse(Utils.validarTelefono(" "));
        assertFalse(Utils.validarTelefono(""));
        assertFalse(Utils.validarTelefono("666 666 666"));
        assertFalse(Utils.validarTelefono(". -"));
        assertFalse(Utils.validarTelefono("666/"));
    }

    @Test
    void validarCorreoTrue() {
        assertTrue(Utils.validarCorreo("correo@correo.com"));
        assertTrue(Utils.validarCorreo("correo@correo.es"));

        assertTrue(Utils.validarCorreo("correo_correo@correo.com"));
        assertTrue(Utils.validarCorreo("correo.correo@correo.com"));
        assertTrue(Utils.validarCorreo("correo-correo@correo.com"));
        assertTrue(Utils.validarCorreo("correo_123@correo.com"));
        assertTrue(Utils.validarCorreo("correo.123@correo.com"));
        assertTrue(Utils.validarCorreo("correo-123@correo.com"));
        assertTrue(Utils.validarCorreo("correo_-.@correo.com"));
        assertTrue(Utils.validarCorreo("correo-123-correo@correo.com"));
        assertTrue(Utils.validarCorreo(".@correo.com"));
        assertTrue(Utils.validarCorreo("123@correo.com"));
    }

    @Test
    void validarCorreoFalse(){
        assertFalse(Utils.validarCorreo("correo@correo"));
        assertFalse(Utils.validarCorreo("@correo"));
        assertFalse(Utils.validarCorreo("@correo.es"));
        assertFalse(Utils.validarCorreo(""));
        assertFalse(Utils.validarCorreo(" "));
        assertFalse(Utils.validarCorreo("123"));
        assertFalse(Utils.validarCorreo("-.&"));
    }
    @Test
    void valirdarCodigoTrue(){
        assertTrue(Utils.validarCodigo("1234A"));
    }

    @Test
    void valirdarCodigoFalse(){
        assertTrue(Utils.validarCodigo("1234A"));
        assertFalse(Utils.validarCodigo("12A"));
        assertFalse(Utils.validarCodigo("123456a"));
        assertFalse(Utils.validarCodigo(""));
        assertFalse(Utils.validarCodigo(" "));
        assertFalse(Utils.validarCodigo("123-A"));
    }

    @Test
    void corregirNombreTest(){
        assertEquals("Miguel", Utils.normalizarNombre("miguel"));
        assertEquals("Miguel Lopez", Utils.normalizarNombre("Miguel Lopez"));
        assertEquals("Miguel Lopez", Utils.normalizarNombre("Miguel lopez"));
        assertEquals("Miguel Lopez", Utils.normalizarNombre("miguel Lopez"));
        assertEquals("Miguel Lopez", Utils.normalizarNombre("miguel lopez"));
        assertEquals("Miguel Lopez Rodriguez", Utils.normalizarNombre("miguel lopez rodriguez"));
        assertEquals("", Utils.normalizarNombre("1234"));
        assertEquals("", Utils.normalizarNombre("miguel 123"));
        assertEquals("", Utils.normalizarNombre(""));
        assertEquals("", Utils.normalizarNombre(" "));
        assertEquals("", Utils.normalizarNombre("Miguel.-"));
    }

    @Test
    void obtenerMensajeExecuteUpdateTest(){
        String sql;
        String expected;

        sql = "INSERT INTO autores";
        expected = "autor insertado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "DELETE FROM autores";
        expected = "autor eliminado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "UPDATE autores";
        expected = "autor actualizado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "INSERT INTO catalogo";
        expected = "libro insertado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "DELETE FROM catalogo";
        expected = "libro eliminado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "UPDATE catalogo";
        expected = "libro actualizado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "INSERT INTO ejemplares";
        expected = "ejemplar insertado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "DELETE FROM ejemplares";
        expected = "ejemplar eliminado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "UPDATE ejemplares";
        expected = "ejemplar actualizado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "INSERT INTO lectores";
        expected = "lector insertado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "DELETE FROM lectores";
        expected = "lector eliminado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "UPDATE lectores";
        expected = "lector actualizado con exito";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "INSERT INTO prestamos";
        expected = "prestamo insertado con exito";
        assertNotEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "DELETE FROM prestamos";
        expected = "prestamo eliminado con exito";
        assertNotEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "UPDATE prestamos";
        expected = "prestamo actualizado con exito";
        assertNotEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));

        sql = "INSERT INTO prestamos";
        expected = "";
        assertEquals(expected, Utils.obtenerMensajeExecuteUpdate(sql));
    }
}