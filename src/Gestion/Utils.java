package Gestion;

public class Utils {
    public static String convertirPrimeraMayuscula(String texto) {
        String[] palabrasTexto;
        String textoMayusculas = "";

        palabrasTexto = texto.split(" ");
        for (String palabraTexto : palabrasTexto) {
            if (palabraTexto.length() > 0 && Character.isLetter(palabraTexto.charAt(0))) {
                textoMayusculas += (palabraTexto.toUpperCase().charAt(0) + palabraTexto.substring(1, palabraTexto.length()) + " ");
            }
        }
        textoMayusculas = textoMayusculas.trim();
        System.out.println(textoMayusculas);

        return textoMayusculas;
    }
    public static boolean validarNombre(String nombre) {
        boolean nombreValido = false;

        if (nombre.matches("^[a-zA-ZÁÉÍÓÚáéíóúÑñ]+(\\s[A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
            nombreValido = true;
        } else {
            System.out.println("Nombre no valido");
        }
        return nombreValido;
    }

    public static boolean validarTelefono(String telefono) {
        boolean telefonoValido = false;

        if (telefono.matches("^[0-9]{9}$")) {
            telefonoValido = true;
        } else {
            System.out.println("Telefono no valido");
        }
        return telefonoValido;
    }

    public static boolean validarCorreo(String correo) {
        boolean correoValido = false;

        if (correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            correoValido = true;
        } else {
            System.out.println("Nombre no valido");
        }
        return correoValido;
    }
    public static boolean validarCodigo(String codigo) {
        boolean codigoValido = false;

        if (codigo.matches("^[0-9]{8}[A-Z]$")){
            codigoValido = true;
        } else {
            System.out.println("Codigo no valido");
        }
        return codigoValido;
    }

    public static String obtenerMensajeExecuteUpdate(String sql) {
        String operacion = "";
        String elementoInsertado = "";
        String mensaje = "";

        if (sql.indexOf("prestamos") == -1) {
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
        }
        return mensaje;
    }
}
