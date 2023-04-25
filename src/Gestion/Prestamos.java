package Gestion;

import Biblioteca.*;
import DBManagement.DBHandler;
import User.pedirDatos;

import java.util.ArrayList;
import java.util.Collections;

public class Prestamos {

    public static void prestar() {
        Prestamo prestamo;
        Ejemplar ejemplar;
        Lector lector;

        ejemplar = Catalogo.escogerEjemplar("SELECT * FROM ejemplares");
        lector = Lectores.escogerLector("SELECT * FROM lectores");

        if (DBHandler.hayRegistros(ejemplar.getSelectString())) {
            if (DBHandler.hayRegistros(lector.getSelectString())) {
                if (!isPrestado(ejemplar)) {
                    prestamo = new Prestamo(ejemplar, lector);
                    DBHandler.executeUpdate(prestamo.getInsertString());
                    System.out.println("Prestamo efectuado con exito");
                } else {
                    System.out.println("El ejemplar ya esta prestado");
                }
            } else {
                System.out.println("No se encontro el lector");
            }
        } else {
            System.out.println("No se encontro el ejemplar");
        }
    }

    public static void devolver() {
        Prestamo prestamo;
        Ejemplar ejemplar;

        ejemplar = Catalogo.escogerEjemplar("SELECT * FROM ejemplares");

        if (DBHandler.hayRegistros(ejemplar.getSelectString())) {
            if (isPrestado(ejemplar)) {
                prestamo = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + ";").get(0);
                DBHandler.executeUpdate(prestamo.getUpdateString());
                System.out.println("Ejemplar devuelto con exito");
            } else {
                System.out.println("El ejemplar no esta prestado");
            }
        } else {
            System.out.println("No se encontro el ejemplar");
        }

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


        switch (option) {
            case 1: {
                prestamos = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE devuelto = 'false';");
                break;
            }
            case 2: {
                prestamos.add(escogerPrestamosEjemplar());
                break;
            }
            case 3: {
                prestamos = escogerPrestamosLector();
                break;
            }
        }
        return prestamos;
    }

    public static Prestamo escogerPrestamosEjemplar() {
        Prestamo prestamo;
        Ejemplar ejemplar;
        String sql;

        if (DBHandler.hayRegistros("SELECT * FROM prestamos")) {
            sql = "SELECT * FROM ejemplares e INNER JOIN prestamos p " +
                    "ON e.idEjemplar = p.idEjemplar WHERE p.devuelto = 'false';";
            ejemplar = Catalogo.escogerEjemplar(sql);
            prestamo = new Prestamo(ejemplar);
        } else {
            System.out.println("No hay prestamos vigentes");
            prestamo = new Prestamo();
        }

        return prestamo;
    }

    public static ArrayList<Prestamo> escogerPrestamosLector() {
        ArrayList<Prestamo> prestamosLector = new ArrayList<Prestamo>();
        Prestamo prestamo;
        Lector lector;
        String sql;

        if (DBHandler.hayRegistros("SELECT * FROM prestamos")) {
            sql = "SELECT * FROM lectores l INNER JOIN prestamos p ON l.idLector = p.idEjemplar " +
                    "WHERE devuelto = 'false';";
            lector = Lectores.escogerLector(sql);
            prestamosLector = DBHandler.getPrestamos("SELECT * FROM prestamos WHERE idLector = " + lector.getIdLector() + ";");
        } else {
            System.out.println("No hay prestamos vigentes");
            prestamo = new Prestamo();
            prestamosLector.add(prestamo);
        }
        return prestamosLector;
    }
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

    public static boolean isPrestado(Ejemplar ejemplar) {
        boolean prestado = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idEjemplar = " + ejemplar.getIdEjemplar() + ";")) {
            prestado = true;
        }
        return prestado;
    }

}
