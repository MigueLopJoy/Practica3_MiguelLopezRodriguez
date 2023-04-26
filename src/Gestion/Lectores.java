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

        switch (option) {
            case 1: {
                lectores = DBHandler.getLectores("SELECT * FROM lectores");
                break;
            }
            case 2: {
                lectores = escogerLectorNombre("SELECT * FROM lectores;");
                break;
            }
            case 3: {
                lectores.add(escogerLectorNumero("SELECT * FROM lectores;"));
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
            lector = escogerLectorNumero("SELECT * FROM lectores");
            System.out.println("Nuevos datos del lector:");
            nuevosDatos = crearLector(lector.getIdLector());
            nuevosDatos.setIdLector(lector.getIdLector());
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay lectores registrados");
        }
    }

    public static void eliminar() {
        eliminarLector(escogerLectorNumero("SELECT * FROM lectores"));
    }

    private static void eliminarLector(Lector lector) {
        if (lector != null) {
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
    }

    private static boolean prestamosVinculados(Lector lector) {
        boolean prestamosVinuclados = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idEjemplar = " + lector.getIdLector() + ";")){
            prestamosVinuclados = true;
        }
        return prestamosVinuclados;
    }

    private static ArrayList<Lector> escogerLectorNombre(String sql) {
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        String nombre;
        String apellidos;
        String getLectorSQL;

        if (DBHandler.hayRegistros(sql)) {
            nombre = pedirDatos.pedirString(" - Intorduzca el nombre del lector");
            apellidos = pedirDatos.pedirString(" - Intorduzca los apellidos del lector");

            getLectorSQL = "SELECT * FROM lectores WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "';";

            lectores = DBHandler.getLectores(getLectorSQL);
        }
        return lectores;
    }
    public static Lector escogerLectorNumero(String sql) {
        Lector lector = null;
        String numLector;
        String getLectorSQL;

        if (DBHandler.hayRegistros(sql)) {
            numLector = pedirDatos.pedirCodigo("Introduzca el numero de lector");
            getLectorSQL = "SELECT * FROM lectores WHERE numero_lector = '" + numLector + "';";
            if (DBHandler.hayRegistros(sql)) {
                lector = DBHandler.getLector(getLectorSQL);
            } else {
                System.out.println("No se encontro el lector buscado");
            }
        } else {
            System.out.println("No se encontraron registros de lectores");
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

    /**
     * Permite crear un lector pidiendo todos sus datos al usuario bibliotecario
     * @return Lector creado a partir de los datos introducidos por consola
     */
    private static Lector crearLector() {
        Lector lector = null;
        String nombre;
        String apellidos;
        String telefono;
        String email = "";

        nombre = pedirDatos.pedirNombre(" - Intorduzca el nombre del lector");
        apellidos = pedirDatos.pedirNombre(" - Intorduzca los apellidos del lector");
        do {
            telefono = pedirDatos.pedirTelefono("Introduzca el numero de telefono");
            if (isTelefonoRepetido(telefono)) {
                System.out.println("Numero de telefono asociado a otro lector");
            }
        } while(isTelefonoRepetido(telefono));
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            do {
                email = pedirDatos.pedirCorreo("Introduzca el email");
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

    /**
     * Permite modificar los datos de un lector ya existente en la base de datos
     * @param idLector id vinculado en la bdd al lector cuyos datos quieren ser modificados
     * @return objeto lector con los nuevos datos del lector modificados
     */
    private static Lector crearLector(int idLector) {
        Lector lector;
        String nombre;
        String apellidos;
        String telefono;
        String email = "";

        nombre = pedirDatos.pedirNombre(" - Intorduzca el nombre del lector");
        apellidos = pedirDatos.pedirNombre(" - Intorduzca los apellidos del lector");
        do {
            telefono = pedirDatos.pedirTelefono("Introduzca el numero de telefono");
            if (isTelefonoRepetido(telefono, idLector)) {
                System.out.println("Numero de telefono asociado a otro lector");
            }
        } while(isTelefonoRepetido(telefono, idLector));
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            do {
                email = pedirDatos.pedirCorreo("Introduzca el email");
                if (isEmailRepetido(email, idLector)) {
                    System.out.println("Email asociado a otro lector");
                }
            } while(isEmailRepetido(email, idLector));
        } else {
            // Compruebo si anteriormente habia un correo asociado al lector y lo elimina
            if (DBHandler.hayRegistros("SELECT email FROM lectores WHERE idLector = " + idLector + ";")){
                DBHandler.executeUpdate("UPDATE lectores SET email = null WHERE idLector = " + idLector + ";");
            }
        }
        if (email != "") {
            lector = new Lector(nombre, apellidos, telefono, email);
        } else {
            lector = new Lector(nombre, apellidos, telefono);
        }
        return lector;
    }

    /**
     * Recibe una direccion de correo por parametro e indica si existe o no en la base de datos
     * @param email email que quiere comprobarse si existe en la bdd
     * @return booleano que indica si el email pasado por parametro ya existe en la bdd
     */
    private static boolean isEmailRepetido(String email) {
        boolean repetido = false;

        if(DBHandler.hayRegistros("SELECT * FROM lectores WHERE email = '" + email + "';")) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe una direccion de correo por parametro e indica si existe o no en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id pasado por parametro
     * @param email email que quiere comprobarse si existe en la bdd
     * @param idLector id del lector al cual si puede estar asociada la direcciond de correo pasada por parametro
     * @return booleano que indica si el email pasado por parametro ya existe en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id señalado
     */
    private static boolean isEmailRepetido(String email, int idLector) {
        boolean repetido = false;

        if(DBHandler.hayRegistros("SELECT * FROM lectores WHERE email = '" + email +  "' AND idLector != " + idLector + ";")) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe un numero de telefono por parametro e indica si existe o no en la base de datos
     * @param numeroTelefono numero de telefono que quiere comprobarse si existe en la bdd
     * @return booleano que indica si el numero de telefono pasado por parametro ya existe en la bdd
     */
    private static boolean isTelefonoRepetido(String numeroTelefono) {
        boolean repetido = false;

        String sql = "SELECT * FROM lectores WHERE numero_telefono = '" + numeroTelefono + "';";
        if(DBHandler.hayRegistros(sql)) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe un numero de telefono por parametro e indica si existe o no en la bdd, exluyendo la posibilidad de que exista asociado al lector vinculado al id pasado por parametro
     * @param numeroTelefono numero de telefono que quiere comprobarse si existe en la bdd
     * @param idLector id del lector al cual si puede estar asociada el numero de telefono pasado por parametro
     * @return booleano que indica si el numero de telefono pasado por parametro ya existe en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id señalado
     */
    private static boolean isTelefonoRepetido(String numeroTelefono, int idLector) {
        boolean repetido = false;

        String sql = "SELECT * FROM lectores WHERE numero_telefono = '" + numeroTelefono + "' AND idLector != " + idLector + ";";
        if(DBHandler.hayRegistros(sql)) {
            repetido = true;
        }
        return repetido;
    }
}
