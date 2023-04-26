package Gestion;

import Biblioteca.*;
import DBManagement.DBHandler;

import java.util.ArrayList;
import java.util.Collections;

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
            lector = Lectores.escogerLectorNumero("SELECT * FROM lectores");
            // Comprueba que el lector escogido existe
            if (lector != null) {
                // Comprueba que el ejemplar no esta prestado y efectua el prestamo al lector
                if (!isPrestado(ejemplar)) {
                    efectuarPrestamo(lector, ejemplar);
                } else {
                    System.out.println("El ejemplar ya esta prestado");
                }
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
        if (hayPrestamosViguentes()) {
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

    private static void efectuarDevolucion(Ejemplar ejemplar) {
        Prestamo prestamo;

        prestamo = DBHandler.getPrestamo("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + ";");
        DBHandler.executeUpdate(prestamo.getUpdateString());
        System.out.println("Ejemplar devuelto con exito");
    }


    public static void consultarPrestamos(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM prestamos;")) {
            ArrayList<Prestamo> prestamos = buscar(option);
            System.out.println("Prestamos en vigor: " + prestamos.size());
            mostrarPrestamos(prestamos);
        } else {
            System.out.println("No se han encontrado prestamos");
        }
    }

    private static ArrayList<Prestamo> buscar(int option) {
        ArrayList<Prestamo> prestamos = new ArrayList<Prestamo>();
        Prestamo prestamo;

        switch (option) {
            case 1: {
                prestamos = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE devuelto = 0;");
                break;
            }
            case 2: {
                prestamo = escogerPrestamoEjemplar();
                if (prestamo != null) {
                    prestamos.add(escogerPrestamoEjemplar());
                }
                break;
            }
            case 3: {
                prestamos = escogerPrestamosLector();
                break;
            }
        }
        return prestamos;
    }

    public static Prestamo escogerPrestamoEjemplar() {
        Prestamo prestamo = null;
        Ejemplar ejemplar;
        String sql;

        if (hayPrestamosViguentes()) {
            sql = "SELECT * FROM ejemplares e INNER JOIN prestamos p " +
                    "ON e.idEjemplar = p.idEjemplar WHERE p.devuelto = 0;";
            ejemplar = Catalogo.escogerEjemplar(sql);
            if (ejemplar != null) {
                prestamo = new Prestamo(ejemplar);
            }
        }
        return prestamo;
    }

    public static ArrayList<Prestamo> escogerPrestamosLector() {
        ArrayList<Prestamo> prestamosLector = new ArrayList<Prestamo>();
        Prestamo prestamo = null;
        Lector lector;
        String sql;

        if (hayPrestamosViguentes()) {
            sql = "SELECT * FROM lectores l INNER JOIN prestamos p ON l.idLector = p.idEjemplar " +
                    "WHERE devuelto = 0;";
            lector = Lectores.escogerLectorNumero(sql);
            if (lector != null) {
                prestamosLector = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE idLector = " + lector.getIdLector() + ";");
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
    public static boolean hayPrestamosViguentes() {
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
