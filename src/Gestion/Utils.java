package Gestion;

public class Utils {
    public static String convertirMayuscula(String texto) {
        String textoMayusculas = "";
        textoMayusculas = texto.toUpperCase().charAt(0) + texto.substring(1, texto.length());
        return textoMayusculas;
    }

    public static boolean validarNombre(String nombre) {
        boolean nombreValido = true;

        if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ&]+((\\s[a-zA-ZáéíóúÁÉÍÓÚñÑ&]+)+)?$")) {
            nombreValido = false;
        }
        return nombreValido;
    }

    public static String obtenerMensajeExecuteUpdate(String sql) {
        String operacion = "";
        String elementoInsertado = "";
        String mensaje = "";

        if (sql.indexOf("INSERT") != -1) {
            operacion = "insertado";
        } else if (sql.indexOf("UPDATE") != -1) {
            operacion = "actualizado";
        } else if (sql.indexOf("DELETE") != -1) {
            operacion = "eliminado";
        }

        if (sql.indexOf("autores") != -1) {
            elementoInsertado = "autor";
        } else if (sql.indexOf("catalogo") != -1) {
            elementoInsertado = "libro";
        } else if (sql.indexOf("ejemplares") != -1) {
            elementoInsertado = "ejemplar";
        } else if (sql.indexOf("lectores") != -1) {
            elementoInsertado = "lector";
        }

        mensaje = elementoInsertado + " " + operacion + " con exito";
        return mensaje;
    }
}
