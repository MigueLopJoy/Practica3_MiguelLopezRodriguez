package User;

import Gestion.Utils;

import java.time.Year;
import java.util.Scanner;

/**
 * Clase con metodos encargados de pedir diferentes tipos de datos al usaurio
 *
 * @author Miguel Lopez Rodriguez
 */
public class pedirDatos {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Pidede al usaurio un año que debera estar comprendido entre el 1500 y el 2022
     *
     * @param texto Texto que aparecera en pantalla para solicitar el dato al usuario
     * @return año introducido por el usuario
     */
    public static Year pedirAño(String texto) {
        Year año;
        año = Year.of(pedirInt(texto, 1500, 2022));
        return año;
    }

    /**
     * Pidea uanc adena de texto que debera encajar con el formato establecido en 'validarCodigo()'. Uilizada para pedir
     * codigos de ejemplar o numeros de lector.
     *
     * @param texto Texto que aparecera en pantalla para solicitar el dato al usuario
     * @return codigo introducido por el usuario
     */
    public static String pedirCodigo(String texto) {
        String codigo;
        do {
            codigo = pedirDatos.pedirString(texto);
        } while (!Utils.validarCodigo(codigo));
        return codigo;
    }

    /**
     * Pide una cadena de texto que debera encajar con el formato establecido en 'validarNombre()' y que sera corregida
     * segun los criterios de 'corregirNombre()';
     *
     * @param texto texto que aparecera en consola para solicitar al usuario el dato
     * @return nombre introducido por el usuario
     */
    public static String pedirNombre(String texto) {
        String nombre;
        do {
            nombre = pedirDatos.pedirString(texto);
            Utils.normalizarNombre(nombre);
        } while (!Utils.validarNombre(nombre));
        return nombre;
    }

    /**
     * Pide una cadena de texto que debera encajar con el formato establecido en 'validarCorreo()' y cuyos caracteres alfabeticos
     * seran convertidos a minusculas
     *
     * @param texto Texto que aparecera en pantalla para solicitar el dato al usuario
     * @return correo introducido por el usuario
     */
    public static String pedirCorreo(String texto) {
        String correo;
        do {
            correo = pedirString(texto);
            correo = correo.toLowerCase();
        } while (!Utils.validarCorreo(correo));
        return correo;
    }

    /**
     * Pide una cadena de texto que debera encajar con el formato de establecido en 'validarTelefono()'
     *
     * @param texto Texto que aparecera en pantalla para solicitar el dato al usuario
     * @return telefono introducido por el usuario
     */
    public static String pedirTelefono(String texto) {
        String telefono;
        do {
            telefono = pedirString(texto);
        } while (!Utils.validarTelefono(telefono));
        return telefono;
    }

    /**
     * Pide al usuario una cadena de texto que no podra consistir en una cadena vacia ("") ni en un espacio en blanco (" ")
     *
     * @param texto texto a mostrar en consola para pedir el dato al usaurio
     * @return cadena de texto introducido por el usuario
     */
    public static String pedirString(String texto) {
        String retorno;
        do {
            System.out.println(texto + "\n");
            retorno = scanner.nextLine();
            if (retorno.isEmpty() || retorno.trim().isEmpty()) {
                System.out.println("Debe introducir algún dato");
            }
            retorno = retorno.trim();
        } while (retorno.isEmpty() || retorno.trim().isEmpty());
        return retorno;
    }

    /**
     * Permite al usuario introducir un numero entero comprendido en un rango
     * delimitado entre dos valores pasados por parametro
     *
     * @param min limite inferior del rango
     * @param max limite superior del rango
     * @return numero entero introducido por el usuario
     */
    public static int pedirInt(int min, int max) {
        int retorno;
        int corrector;

        // Intercambia los valores de los parametros min y max si es necesario
        if (min > max) {
            corrector = max;
            max = Math.max(max, min);
            min = Math.min(corrector, min);
        }

        do {
            while (!scanner.hasNextInt()) {
                System.out.println("Debe introducir un numero");
                scanner.next();
            }
            retorno = scanner.nextInt();
            scanner.nextLine();
            if (retorno < min || retorno > max) {
                System.out.println("Numero fuera de rango");
            }
        } while (retorno < min || retorno > max);
        return retorno;
    }

    /**
     * Muestra por consola un texto pasado por parametro y permite al usuario
     * introducir un numero entero comprendido en un rango delimitado por dos
     * valores, tambien pasados por parametro
     *
     * @param texto texto a mostrar por consola
     * @param min   limite inferior del rango
     * @param max   limite superior del rango
     * @return numero entero introducido por el usuario
     */
    public static int pedirInt(String texto, int min, int max) {
        int retorno;
        int corrector;

        if (min > max) {
            corrector = max;
            max = Math.max(max, min);
            min = Math.min(corrector, min);
        }

        do {
            System.out.print(texto + "\n");
            while (!scanner.hasNextInt()) {
                System.out.println("Debe introducir un numero");
                System.out.print(texto + "\n");
                scanner.next();
            }
            retorno = scanner.nextInt();
            scanner.nextLine();
            if (retorno < min || retorno > max) {
                System.out.println("Numero fuera de rango");
            }
        } while (retorno < min || retorno > max);
        return retorno;
    }

    /**
     * Muestra un texto por consola y permite al usuario responder afirmativa o
     * negativamente
     *
     * @param texto texto a mostrar por consola
     * @return confirmacion o negacion del usuario
     */
    public static boolean confirmacion(String texto) {
        String userInput;
        char confirmacion = ' ';
        boolean confirmado = false;
        do {
            System.out.println(texto);
            if ((userInput = scanner.nextLine()).matches("[a-zA-Z]")) {
                confirmacion = userInput.charAt(0);
                if (confirmacion != 's' && confirmacion != 'n') {
                    System.out.println("El dato introducido no se corresponde con el solicitado");
                }
            } else {
                System.out.println("Dato no valido");
            }
        } while (confirmacion != 's' && confirmacion != 'n');

        if (confirmacion == 's') {
            confirmado = true;
        }
        return confirmado;
    }
}
