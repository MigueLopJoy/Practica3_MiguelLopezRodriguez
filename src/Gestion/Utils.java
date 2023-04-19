package Gestion;

import Biblioteca.Autor;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Utils {
    public static boolean isNumeric(String data) {
        boolean isNumeric = true;
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            isNumeric = false;
        }
        return isNumeric;
    }

    public static int convertirEnNumero(String data) {
        int number = 0;
        try {
            number = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static int calcularDiasMes(int numeroMes) {
        int numDias = 0;

        switch (numeroMes) {
            case 1: {
                numDias = 31;
                break;
            }
            case 2: {
                numDias = 28;
                break;
            }
            case 3: {
                numDias = 31;
                break;
            }
            case 4: {
                numDias = 30;
                break;
            }
            case 5: {
                numDias = 31;
                break;
            }
            case 6: {
                numDias = 30;
                break;
            }
            case 7: {
                numDias = 31;
                break;
            }
            case 8: {
                numDias = 31;
                break;
            }
            case 9: {
                numDias = 30;
                break;
            }
            case 10: {
                numDias = 31;
                break;
            }
            case 11: {
                numDias = 30;
                break;
            }
            case 12: {
                numDias = 31;
                break;
            }
        }
        return numDias;
    }

    public static String convertirMayuscula(String texto){
        String textoMayusculas = "";
        textoMayusculas = texto.toUpperCase().charAt(0) + texto.substring(1, texto.length());
        return textoMayusculas;
    }
    public static boolean validarNombre(String nombre) {
        boolean nombreValido = true;
        boolean encontrado = false;
        int contador = 0;
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char letraNombre;
        do {
            letraNombre = nombre.charAt(contador);
            if (!Character.isLetter(letraNombre)) {
                encontrado = true;
                nombreValido = false;
            } else {
                contador++;
            }
        } while (contador < nombre.length() && !encontrado);
        return nombreValido;
    }

    public static String obtenerMensajeExecuteUpdate(String sql) {
        String operacion = "";
        String elementoInsertado = "";
        String mensaje = "";

        if (sql.indexOf("INSERT") != -1) {
            operacion = "insertado";
            if (sql.indexOf("autores") != -1) {
                elementoInsertado = "autor";
            } else if (sql.indexOf("catalogo") != -1) {
                elementoInsertado = "libro";
            } else if (sql.indexOf("ejemplares") != -1) {
                elementoInsertado = "ejemplar";
            } else if (sql.indexOf("lectores") != -1) {
                elementoInsertado = "lector";
            }
        }
        mensaje = "Nuevo " + elementoInsertado + " " + operacion + " con exito";
        return mensaje;
    }
}
