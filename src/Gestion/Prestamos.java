package Gestion;

import Biblioteca.*;
import DBManagement.DBHandler;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que contiene todos los metos vinculados al modulo de circulacion del sistema (prestamos y devoluciones, y consultas de prestamos)
 *
 * @author Miguel Lopez Rodriguez
 */
public class Prestamos {

    /**
     * Permite efectuar el prestamo de un ejemplar a un lector
     */
    public static void prestar() {
        Ejemplar ejemplar;
        Lector lector;

        // Selecciona el ejemplar a prestar
        ejemplar = Catalogo.escogerEjemplar("SELECT * FROM ejemplares");
        // Comprueba que el ejemplar escogido existe
        if (ejemplar != null) {
            if (!isPrestado(ejemplar)) {
                lector = Lectores.escogerLectorNumero("SELECT * FROM lectores");
                // Comprueba que el lector escogido existe
                if (lector != null) {
                    // Comprueba que el ejemplar no esta prestado y efectua el prestamo al lector
                    efectuarPrestamo(lector, ejemplar);
                }
            } else {
                System.out.println("El ejemplar ya esta prestado");
            }
        }
    }

    /**
     * Lleva a efecto el prestamo del ejemplar indicado al lector que se especifica
     *
     * @param lector   Lector que recibira el prestamo
     * @param ejemplar Ejemplar que sera prestado
     */
    public static void efectuarPrestamo(Lector lector, Ejemplar ejemplar) {
        Prestamo prestamo;

        prestamo = new Prestamo(ejemplar, lector);
        DBHandler.executeUpdate(prestamo.getInsertString());
        System.out.println("Prestamo efectuado con exito");
    }

    /**
     * Permite efectuar la devolucion de ejempares prestados y sin devolver si los hubiere
     */
    public static void devolver() {
        Prestamo prestamo;
        Ejemplar ejemplar;

        // Comprueba que hay ejemplares prestados sin devolver
        if (hayPrestamosVigentes()) {
            // Escoger ejemplar a devolver
            ejemplar = Catalogo.escogerEjemplar("SELECT * FROM ejemplares");
            // Comprueba que el ejemplar buscado existe
            if (ejemplar != null) {
                // Comrpueba si el ejemplar esta prestado y lo devuelve
                if (isPrestado(ejemplar)) {
                    efectuarDevolucion(ejemplar);
                } else {
                    System.out.println("El ejemplar no esta prestado");
                }
            }
        }
    }

    /**
     * Lleva a efecto la devolucion del ejemplar pasado por parametro
     *
     * @param ejemplar Ejemplar que sera devuelto
     */
    private static void efectuarDevolucion(Ejemplar ejemplar) {
        Prestamo prestamo;

        prestamo = DBHandler.getPrestamo("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + ";");
        DBHandler.executeUpdate(prestamo.getUpdateString());
        System.out.println("Ejemplar devuelto con exito");
    }


    /**
     * Recupera una lista de prestamos mediante una llamada al metodo encargado de realizar una busqueda en funcion
     * de una opcion de busqueda indicada por parametro y hace una llamada al metodo encargado de mostrar esta lista
     * de prestamos por consola
     *
     * @param option opcion de busqueda escogida por el usuario y que sera enviada al metodo encargado de realizar la busqueda de prestamos
     */
    public static void consultarPrestamos(int option) {
        if (hayPrestamosVigentes()) {
            ArrayList<Prestamo> prestamos = buscar(option);
            if (prestamos.size() > 0) {
                System.out.println("Prestamos en vigor: " + prestamos.size());
                mostrarPrestamos(prestamos);
            }
        }
    }

    /**
     * Devuelve una lista con el/ los prestamos recuperados de una busqueda efectuada en base a una opcion de busqueda escogida por el usuario
     *
     * @param option opcion de busqueda previamente escogida por el suaurio
     * @return Lista de prestamos recuperada de la busqueda del usuario
     */
    private static ArrayList<Prestamo> buscar(int option) {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        Prestamo prestamo;
        Lector lector;
        Ejemplar ejemplar;
        String sql;

        switch (option) {
            case 1: {
                // Recupera todos los prestamos de libros no devueltos
                prestamos = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE devuelto = 0;");
                break;
            }
            case 2: {
                // Recupera el unico prestamo que puede estar asociado a un ejemplar
                prestamos = recuperarPrestamoEjemplar();
                break;
            }
            case 3: {
                // Recupera todos los prestamos que pueden estar asociados a un lector
                prestamos = recuperarPrestamosLector();
                break;
            }
        }
        return prestamos;
    }


    /**
     * Recupera una lista que contendra el unico posible prestamo asociado al ejemplar escogido por el usuario
     *
     * @return lista con el unico posible prestamo asociado al ejemplar escogido por el ususairo
     */
    private static ArrayList<Prestamo> recuperarPrestamoEjemplar() {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        String sql = "SELECT * FROM ejemplares e INNER JOIN prestamos p ON e.idEjemplar = p.idEjemplar WHERE p.devuelto = 0;";
        Ejemplar ejemplar = Catalogo.escogerEjemplar(sql);
        Prestamo prestamo = escogerPrestamoEjemplar(ejemplar);
        if (prestamo != null) {
            prestamos.add(prestamo);
        }
        return prestamos;
    }

    /**
     * Recupera una lista con todos los posibles prestamos vinculados al lector seleccionado por el usuario
     *
     * @return lista con todos los posibles prestamos vinculados a un lector escogido por el usuario
     */
    private static ArrayList<Prestamo> recuperarPrestamosLector() {
        ArrayList<Prestamo> prestamos;
        String sql = "SELECT * FROM lectores l INNER JOIN prestamos p ON l.idLector = p.idLector WHERE devuelto = 0;";
        Lector lector = Lectores.escogerLectorNumero(sql);
        prestamos = escogerPrestamosLector(lector);
        return prestamos;
    }

    /**
     * Recupera los prestamos asociados al ejemplar pasado por parametro si lo hubiere
     *
     * @param ejemplar ejemplar del cual se quieren recuperar los prestamos asociados
     * @return prestamo asocaido al ejemplar pasado por parametro
     */
    public static Prestamo escogerPrestamoEjemplar(Ejemplar ejemplar) {
        Prestamo prestamo = null;
        String sql;

        if (hayPrestamosVigentes()) {
            if (ejemplar != null) {
                prestamo = DBHandler.getPrestamo("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar());
            }
        }
        return prestamo;
    }

    /**
     * Recupera el conjunto de prestamos que puede haber asociado a un lector indicado por parametro
     *
     * @param lector lector del cual se quiere recuperar el conjunto de prestamos a el asociados, si lo hubiere
     * @return conjunto de prestamos asociados al lector
     */
    public static ArrayList<Prestamo> escogerPrestamosLector(Lector lector) {
        ArrayList<Prestamo> prestamosLector = new ArrayList<Prestamo>();
        Prestamo prestamo = null;
        String sql;

        if (hayPrestamosVigentes()) {
            if (lector != null) {
                prestamosLector = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE idLector = " + lector.getIdLector() + " AND devuelto = 0;");
            }
        } else {
            prestamosLector.add(prestamo);
        }
        return prestamosLector;
    }

    /**
     * Imprime por consola un listado de prestamos pasados por parametro
     *
     * @param prestamos prestamos que quieren ser imprimidos por consola
     */
    private static void mostrarPrestamos(ArrayList<Prestamo> prestamos) {
        Prestamo prestamo;
        String mensaje;
        // Ordenar alfabeticamente por fecha de prestamo
        Collections.sort(prestamos);

        for (int i = 0; i < prestamos.size(); i++) {
            prestamo = prestamos.get(i);
            mensaje = (i + 1) + ". " + prestamo.toString();
            System.out.println(mensaje);
        }
    }

    /**
     * Comprueba si hay registros de libros prestados sin devolver. Si no los hay, lo indica por consola.
     *
     * @return booleano que indica si hay libros prestados sin devovler
     */
    public static boolean hayPrestamosVigentes() {
        boolean hayPrestamosVigentes = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE devuelto = 0;")) {
            hayPrestamosVigentes = true;
        } else {
            System.out.println("No hay prestamos vigentes");
        }
        return hayPrestamosVigentes;
    }

    /**
     * Comrpueba si hay registros de un ejemplar prestado y sin devolver
     *
     * @param ejemplar ejemplar que quiere comprobarse si esta prestado
     * @return booleano que indica si el ejemplar pasado por parametro esta prestado y sin devolver
     */
    public static boolean isPrestado(Ejemplar ejemplar) {
        boolean prestado = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + " AND devuelto = 0;")) {
            prestado = true;
        }
        return prestado;
    }
}
