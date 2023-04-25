package Gestion;

import Biblioteca.*;
import DBManagement.DBHandler;
import User.pedirDatos;
import java.util.ArrayList;
import java.util.Collections;

public class Lectores {
    public static void consultarLectores(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            ArrayList<Lector> lectores = buscar(option);
            System.out.println("Lectores encontrados: " + lectores.size());
            mostrarLectores(lectores);
        } else {
            System.out.println("No hay lectores registrados");
        }
    }

    private static ArrayList<Lector> buscar(int option) {
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        Lector lector;
        String sql;
        String nombre;
        String apellidos;

        switch (option) {
            case 1: {
                lectores = DBHandler.getLectores("SELECT * FROM lectores");
                break;
            }
            case 2: {
                nombre = pedirDatos.pedirString(" - Intorduzca el nombre del lector");
                apellidos = pedirDatos.pedirString(" - Intorduzca los apellidos del lector");

                sql = "SELECT * FROM lectores WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "';";
                lectores = DBHandler.getLectores(sql);
                break;
            }
            case 3: {
                lector = escogerLector("SELECT * FROM lectores");
                lectores.add(lector);
                break;
            }
        }
        return lectores;
    }
    public static void registrarLector() {
        Lector lector = crearLector();
        DBHandler.executeUpdate(lector.getInsertString());
    }

    public static void actualizarLector() {
        Lector lector;
        Lector nuevosDatos;
        String sql;

        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            lector = escogerLector("SELECT * FROM lectores");
            System.out.println("Nuevos datos del lector:");
            nuevosDatos = crearLector();
            nuevosDatos.setIdLector(lector.getIdLector());
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay libros registrados");
        }
    }

    public static void eliminar() {
        eliminarLector(escogerLector("SELECT * FROM lectores"));
    }

    private static void eliminarLector(Lector lector) {
        if (DBHandler.hayRegistros(lector.getSelectString())) {
            if (!prestamosVinculados(lector)) {
                DBHandler.executeUpdate(lector.getDeleteString());
            } else {
                System.out.println("No puede eliminar al lector mientras haya prestamos vinculados");
            }
        } else {
            System.out.println("No se ha encontrado al lector");
        }
    }

    private static boolean prestamosVinculados(Lector lector) {
        boolean prestamosVinuclados = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idEjemplar = " + lector.getIdLector() + ";")){
            prestamosVinuclados = true;
        }
        return prestamosVinuclados;
    }

    public static Lector escogerLector(String sql) {
        Lector lector;
        String numLector;

        if (DBHandler.hayRegistros(sql)) {
            numLector = pedirDatos.pedirCodigo("Introduzca el numero de lector");
            lector = new Lector(numLector);
        } else {
            System.out.println("No se encontraron registros");
            lector = new Lector();
        }
        return lector;
    }
    private static void mostrarLectores(ArrayList<Lector> lectores) {
        Lector lector;
        String mensaje;
        Collections.sort(lectores);

        for (int i = 0; i < lectores.size(); i++) {
            lector = lectores.get(i);
            mensaje = " - " + (i + 1) + ". " + lector.toString();
            System.out.println(mensaje);
        }
    }
    private static Lector crearLector() {
        Lector lector;
        String nombre;
        String apellidos;
        String telefono;
        String email = "";

        nombre = pedirDatos.pedirString(" - Intorduzca el nombre del lector");
        apellidos = pedirDatos.pedirString(" - Intorduzca los apellidos del lector");
        do {
            telefono = pedirDatos.pedirString("Introduzca el numero de telefono");
            if (isTelefonoRepetido(telefono)) {
                System.out.println("Numero de telefono asociado a otro lector");
            }
        } while(isTelefonoRepetido(telefono));
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            do {
                email = pedirDatos.pedirString("Introduzca el email");
                if (isEmailRepetido(email)) {
                    System.out.println("Email asociado a otro lector");
                }
            } while(isEmailRepetido(email));
        }
        if (email != "") {
                lector = new Lector(nombre, apellidos, telefono, email);
            } else {
                lector = new Lector(nombre, apellidos, telefono);
            }
        return lector;
    }
    private static boolean isEmailRepetido(String email) {
        boolean repetido = false;

        if(DBHandler.hayRegistros("SELECT * FROM lectores WHERE email = '" + email + "';")) {
            repetido = true;
        }
        return repetido;
    }

    private static boolean isTelefonoRepetido(String numeroTelefono) {
        boolean repetido = false;

        if(DBHandler.hayRegistros("SELECT * FROM lectores WHERE numero_telefono = '" + numeroTelefono + "';")) {
            repetido = true;
        }
        return repetido;
    }
}
