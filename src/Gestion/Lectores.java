package Gestion;

import Biblioteca.*;
import DBManagement.DBHandler;
import User.pedirDatos;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que contiene todos los metodos relacionados con el modulo de gestion de lectores (der de alta y de baja, y buscar lectores)
 *
 * @author Miguel Lopez Rodriguez
 */
public class Lectores {

    /**
     * Recupera una lista de lectores mediante una llamada al metodo encargado de realizar una busqueda en funcion
     * de una opcion de busqueda indicada por parametro y hace una llamada al metodo encargado de mostrar esta lista
     * de lectores por consola
     *
     * @param option opcion de busqueda escogida por el usuario y que sera enviada al metodo encargado de realizar la busqueda de prestamos
     */
    public static void consultarLectores(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            ArrayList<Lector> lectores = buscar(option);
            System.out.println("Lectores encontrados: " + lectores.size());
            mostrarLectores(lectores);
        } else {
            System.out.println("No hay lectores registrados");
        }
    }

    /**
     * Devuelve una lista con el/ los lectores recuperados de una busqueda efectuada en base a una opcion de busqueda escogida por el usuario
     *
     * @param option opcion de busqueda previamente escogida por el suaurio
     * @return Lista de lectores recuperada de la busqueda del usuario
     */
    private static ArrayList<Lector> buscar(int option) {
        ArrayList<Lector> lectores = new ArrayList<Lector>();

        switch (option) {
            case 1: {
                lectores = DBHandler.getLectores("SELECT * FROM lectores");
                break;
            }
            case 2: {
                lectores = escogerLectorNombre();
                break;
            }
            case 3: {
                lectores.add(escogerLectorNumero("SELECT * FROM lectores;"));
                break;
            }
        }
        return lectores;
    }

    /**
     * Llama al metodo encargado de pedir los datos del nuevo lector para despues enviarlos al metodo encargado de insertarlo en la bdd
     */
    public static void registrarLector() {
        Lector lector = crearLector();
        DBHandler.executeUpdate(lector.getInsertString());
    }

    /**
     * Recupera un lector existente de la bdd, llama al metodo encargado de pedir los nuevos datos del lector,
     * e intercambia los valores previos por los nuevos
     */
    public static void actualizarLector() {
        Lector lector;
        Lector nuevosDatos;

        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            // Recupera un letor de la bdd a traves de su numero de lector
            lector = escogerLectorNumero("SELECT * FROM lectores");
            System.out.println("Nuevos datos del lector:");
            // Crea un nuevo objeto lector con los datos actualizados
            nuevosDatos = crearLector(lector.getIdLector());
            // Se añade a este nuevo objeto el id vinculado al lector escogido en la bdd
            nuevosDatos.setIdLector(lector.getIdLector());
            // Actualiza el lector con los nuevos datos en base al id del lector original
            DBHandler.executeUpdate(nuevosDatos.getUpdateString());
        } else {
            System.out.println("No hay lectores registrados");
        }
    }

    /**
     * Llama al metodo encargado de efectuar la eliminacion de un lector pasandole como parametro el lector
     * recuperado de una busqeuda efectuada en base al numero de lector
     */
    public static void eliminar() {
        eliminarLector(escogerLectorNumero("SELECT * FROM lectores"));
    }

    /**
     * Lleva a efecto la eliminacion de la bdd del lector pasado por parametro siempre que no tenga prestamos vinculados
     *
     * @param lector lector a eliminar
     */
    private static void eliminarLector(Lector lector) {
        if (lector != null) {
            // Comprueba que no haya prestamos vinculados al lector
            if (!prestamosVinculados(lector)) {
                DBHandler.executeUpdate(lector.getDeleteString());
            } else {
                System.out.println("No puede eliminar al lector mientras haya prestamos vinculados");
            }
        }
    }

    /**
     * Comprueba si hay prestamos vinculados al lector pasado por parametro
     *
     * @param lector lector del cual se quiere comprobar si hay prestamos a el vinculados
     * @return booleano que indica si hay o no prestamos vinculados al lector pasado por parametro
     */
    private static boolean prestamosVinculados(Lector lector) {
        boolean prestamosVinuclados = false;
        if (DBHandler.hayRegistros("SELECT * FROM prestamos WHERE idLector = " + lector.getIdLector() + ";")) {
            prestamosVinuclados = true;
        }
        return prestamosVinuclados;
    }

    /**
     * Devuelve una lista de lectores cuyo nombre y apellidos coincidan con los indicados por el usuario
     *
     * @return lista de lectores cuyo nombre y apellidos coincen con los indicados por el usaurio
     */
    private static ArrayList<Lector> escogerLectorNombre() {
        ArrayList<Lector> lectores = new ArrayList<Lector>();
        String nombre;
        String apellidos;
        String getLectorSQL;

        if (DBHandler.hayRegistros("SELECT * FROM lectores;")) {
            nombre = pedirDatos.pedirString(" - Intorduzca el nombre del lector");
            apellidos = pedirDatos.pedirString(" - Intorduzca los apellidos del lector");

            getLectorSQL = "SELECT * FROM lectores WHERE nombre = '" + nombre + "' AND apellidos = '" + apellidos + "';";

            lectores = DBHandler.getLectores(getLectorSQL);
        }
        return lectores;
    }

    /**
     * Solicita un numero de usuario y devuelve al lector vinculado a dicho numero de usuario, si lo hubiere
     *
     * @param sql Consulta que delimita un ambito de busqueda en el cual se comprobaran si existen registros
     * @return lector asociado al numero de usuario indicado
     */
    public static Lector escogerLectorNumero(String sql) {
        Lector lector = null;
        String numLector;
        String getLectorSQL;

        if (DBHandler.hayRegistros(sql)) {
            numLector = pedirDatos.pedirCodigo("Introduzca el numero de lector");
            getLectorSQL = "SELECT * FROM lectores WHERE numero_lector = '" + numLector + "';";
            if (DBHandler.hayRegistros(getLectorSQL)) {
                lector = DBHandler.getLector(getLectorSQL);
            } else {
                System.out.println("No se encontro el lector buscado");
            }
        } else {
            System.out.println("No se encontraron registros de lectores");
        }
        return lector;
    }


    /**
     * Imprime por consola las caracteristicas de los lectores almacenados en la lista pasada por parametro
     *
     * @param lectores lista de lectores a imprimir por consola
     */
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
     *
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
        // Se vovlera a pedir el numero de telefono del lecgtor hasta que este no coincida con ningun numero de telefono registrado en la bdd
        do {
            telefono = pedirDatos.pedirTelefono("Introduzca el numero de telefono");
            if (isTelefonoRepetido(telefono)) {
                System.out.println("Numero de telefono asociado a otro lector");
            }
        } while (isTelefonoRepetido(telefono));
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            // Se volvera a pedir el email del lector hasta que este no coincida con ningun email registrado en la bdd
            do {
                email = pedirDatos.pedirCorreo("Introduzca el email");
                if (isEmailRepetido(email)) {
                    System.out.println("Email asociado a otro lector");
                }
            } while (isEmailRepetido(email));
        }
        // Se usa un constructor diferente segun el lector tenga o no email
        if (email != "") {
            lector = new Lector(nombre, apellidos, telefono, email);
        } else {
            lector = new Lector(nombre, apellidos, telefono);
        }
        return lector;
    }

    /**
     * Permite modificar los datos de un lector ya existente en la base de datos
     *
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
        // Se volvera a pedir el numero de telefono del lector hasta que este no coincida con ningun numero de telefono registrado en la bdd
        do {
            telefono = pedirDatos.pedirTelefono("Introduzca el numero de telefono");
            if (isTelefonoRepetido(telefono, idLector)) {
                System.out.println("Numero de telefono asociado a otro lector");
            }
        } while (isTelefonoRepetido(telefono, idLector));
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            // Se volvera a pedir el email del lector hasta que este no coincida con ningun email registrado en la bdd
            do {
                email = pedirDatos.pedirCorreo("Introduzca el email");
                if (isEmailRepetido(email, idLector)) {
                    System.out.println("Email asociado a otro lector");
                }
            } while (isEmailRepetido(email, idLector));
        } else {
            // Compruebo si anteriormente habia un correo asociado al lector y lo elimina
            if (DBHandler.hayRegistros("SELECT email FROM lectores WHERE idLector = " + idLector + ";")) {
                DBHandler.executeUpdate("UPDATE lectores SET email = null WHERE idLector = " + idLector + ";");
            }
        }
        // Se usa un constructor diferente segun el lector tenga o no email
        if (email != "") {
            lector = new Lector(nombre, apellidos, telefono, email);
        } else {
            lector = new Lector(nombre, apellidos, telefono);
        }
        return lector;
    }

    /**
     * Recibe una direccion de correo por parametro e indica si existe o no en la base de datos
     *
     * @param email email que quiere comprobarse si existe en la bdd
     * @return booleano que indica si el email pasado por parametro ya existe en la bdd
     */
    private static boolean isEmailRepetido(String email) {
        boolean repetido = false;

        if (DBHandler.hayRegistros("SELECT * FROM lectores WHERE email = '" + email + "';")) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe una direccion de correo por parametro e indica si existe o no en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id pasado por parametro
     *
     * @param email    email que quiere comprobarse si existe en la bdd
     * @param idLector id del lector al cual si puede estar asociada la direcciond de correo pasada por parametro
     * @return booleano que indica si el email pasado por parametro ya existe en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id señalado
     */
    private static boolean isEmailRepetido(String email, int idLector) {
        boolean repetido = false;

        if (DBHandler.hayRegistros("SELECT * FROM lectores WHERE email = '" + email + "' AND idLector != " + idLector + ";")) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe un numero de telefono por parametro e indica si existe o no en la base de datos
     *
     * @param numeroTelefono numero de telefono que quiere comprobarse si existe en la bdd
     * @return booleano que indica si el numero de telefono pasado por parametro ya existe en la bdd
     */
    private static boolean isTelefonoRepetido(String numeroTelefono) {
        boolean repetido = false;

        String sql = "SELECT * FROM lectores WHERE numero_telefono = '" + numeroTelefono + "';";
        if (DBHandler.hayRegistros(sql)) {
            repetido = true;
        }
        return repetido;
    }

    /**
     * Recibe un numero de telefono por parametro e indica si existe o no en la bdd, exluyendo la posibilidad de que exista asociado al lector vinculado al id pasado por parametro
     *
     * @param numeroTelefono numero de telefono que quiere comprobarse si existe en la bdd
     * @param idLector       id del lector al cual si puede estar asociada el numero de telefono pasado por parametro
     * @return booleano que indica si el numero DE telefono pasado por parametro ya existe en la bdd, exluyendo la posibilidad de que exista asociada al lector vinculado al id señalado
     */
    private static boolean isTelefonoRepetido(String numeroTelefono, int idLector) {
        boolean repetido = false;

        String sql = "SELECT * FROM lectores WHERE numero_telefono = '" + numeroTelefono + "' AND idLector != " + idLector + ";";
        if (DBHandler.hayRegistros(sql)) {
            repetido = true;
        }
        return repetido;
    }
}
