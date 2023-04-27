package Gestion;

import DBManagement.DBHandler;

/**
 * Clase que contiene metodos genericos utilizados desde diferentes partes del sistema, como validaciones de datos
 * o normalizaciones.
 *
 * @author Miguel Lopez Rodriguez
 */
public class Utils {
    /**
     * Valida una cadena de texto contra un formato de nombre establecido por una expresion regular
     *
     * @param nombre cadena de texto que quiere ser valdiada contra el formato de nombre establecido
     * @return booleano que indica si la cadena de texto es un nombre valido o no
     */
    public static boolean validarNombre(String nombre) {
        boolean nombreValido = false;

        // Admite varias palabras separadas por espacios en las que solo haya letras de la 'a' a la 'z' en mayuscula
        // y minuscla, asi como vocales acentuadas
        if (nombre.matches("^[a-zA-ZÁÉÍÓÚáéíóúÑñ]+(\\s[A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")) {
            nombreValido = true;
        } else {
            System.out.println("Nombre no valido");
        }
        return nombreValido;
    }

    /**
     * Valida una cadena de texto contra un formato de numero de telefono establecido por una expresion regular
     *
     * @param telefono cadena de texto que quiere ser validada contra el formato de telefono establecido
     * @return booleano que indica si la cadena de texto es un telefono valido
     */
    public static boolean validarTelefono(String telefono) {
        boolean telefonoValido = false;

        // Admite nueve caracteres numericos (Formato de nº de tfno en España, por simplificar)
        if (telefono.matches("^[0-9]{9}$")) {
            telefonoValido = true;
        } else {
            System.out.println("Telefono no valido");
        }
        return telefonoValido;
    }

    /**
     * Valida una cadena de texto contra un formato de correo establecido por una expresion regular
     *
     * @param correo cadena de texto que quiere ser valdiada contra el formato de correo establecido
     * @return booleano que indica si la cadena de texto es un correo valido o no
     */
    public static boolean validarCorreo(String correo) {
        boolean correoValido = false;

        if (correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            correoValido = true;
        } else {
            System.out.println("Nombre no valido");
        }
        return correoValido;
    }

    /**
     * Valida una cadena de texto contra un formato de codigo de ejemplar o numero de lector
     * establecido por una expresion regular
     *
     * @param codigo cadena de texto que quiere ser validada contra el formato de codigo establecido
     * @return booleano que indica si el codigo es valido o no
     */
    public static boolean validarCodigo(String codigo) {
        boolean codigoValido = false;

        if (codigo.matches("^[0-9]{8}[A-Z]$")) {
            codigoValido = true;
        } else {
            System.out.println("Codigo no valido");
        }
        return codigoValido;
    }

    /**
     * Corrige un nombre para adecuarlo a un formato estandar (primera mayuscula y demas minuscula)
     *
     * @param nombre nombre que quiere ser normalizado
     * @return nombre normalizado
     */
    public static String corregirNombre(String nombre) {
        String nombreCorregido = "";

        if (validarNombre(nombre)) {
            // Convierte a mayuscula la primera letra del nombre
            nombreCorregido += convertirPrimeraMayuscula(nombre);
            // Convierte a minuscula el resto del nombre
            nombreCorregido += nombre.substring(1).toLowerCase();
        }
        return nombreCorregido;
    }

    /**
     * Convierte a mayuscula la primera letra de cada palabra de una cadena de texto
     *
     * @param texto Texto a modificar convirtiendo a mayuscula la primera letra de cada palabra de la cadena
     * @return cadena de texto con la primera letra de cada palabra convertida a mayuscula
     */
    public static String convertirPrimeraMayuscula(String texto) {
        String[] palabrasTexto;
        String textoMayusculas = "";

        // Llena el array 'palabrasTexto' con los elementos de la cadena pasada por parametro obtenidos de aplicar
        // el separador " " (espacio en blanco) a la misma
        palabrasTexto = texto.split(" ");
        for (String palabraTexto : palabrasTexto) {
            // Comprueba que el primer caracter cada elemento del array es una letra
            if (palabraTexto.length() > 0 && Character.isLetter(palabraTexto.charAt(0))) {
                // Se suma a 'textoMayusculas' la palabra con la primera letra convertida a mayuscula mas un espacio en blanco
                textoMayusculas += (palabraTexto.toUpperCase().charAt(0) + palabraTexto.substring(1, palabraTexto.length()) + " ");
            }
        }
        // Eliminar espacios en blanco sobrantes
        textoMayusculas = textoMayusculas.trim();
        return textoMayusculas;
    }

    /**
     * Construye y retorna un mensaje que indica que una operacion lanzada contra lña bdd se ha realizado con exito
     *
     * @param sql query lanzada contra la bdd
     * @return texto que indica que la operacion lanzada contra la bdd se ha realizado cone exito
     */
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

    /**
     * Genera aleatoreamente un codigo alfanumerico compuesto por 4 numeros y una letra mayuscula que podra
     * ser utilizado tanto como codigo de lector como de numero de lector
     *
     * @return codigo alfanumerico que sera empleado como codigo de ejemplar o como numero de lector
     */
    public static String generarCodigo() {
        String codigo = "";
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        do {
            for (int i = 0; i < 4; i++) {
                codigo += (int) (Math.random() * 10);
            }
            codigo += letras[(int) (Math.random() * 26)];
        } while (isRepetido(codigo));

        return codigo;
    }

    /**
     * Comprueba si el codigo pasado por parametro se encuentra ya asignado en la bdd a un lector o ejemplar
     *
     * @param codigo codigo cuya existencia se quiere comprobar en la bdd
     * @return booleano que indica si el codigo pasado por parametro se encuentra vinculado o no a un ejemplar o lector
     * en la bdd
     */
    private static boolean isRepetido(String codigo) {
        boolean repetido = false;

        if (DBHandler.hayRegistros("SELECT * FROM lectores WHERE numero_lector = '" + codigo + "';") ||
                DBHandler.hayRegistros("SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + codigo + "';")) {
            repetido = true;
        }
        return repetido;
    }
}
