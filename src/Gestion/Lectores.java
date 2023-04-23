package Gestion;

import Biblioteca.Autor;
import Biblioteca.Direccion;
import Biblioteca.Lector;
import Biblioteca.Libro;
import DBManagement.DBHandler;
import User.pedirDatos;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;ç

public class Lectores {
    public static void consultarLectores(int option) {
        if (DBHandler.hayRegistros("SELECT * FROM catalogo")) {
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
        String numeroLector;

        switch (option) {
            case 1: {
                libros = DBHandler.getLectores("SELECT * FROM lectores");
                break;
            }
            case 2: {
                lector = crearLector();
                sql = "SELECT * FROM lectores c INNER JOIN autores a ON c.idAutor = a.idAutor WHERE nombre = '"
                        + autor.getNombre() + "' AND apellido1 = '" + autor.getApellido1() + "' AND apellido2 = '"
                        + autor.getApellido2() + "';";
                lectores = DBHandler.getLibros(sql);
                break;
            }
            case 3: {
                numeroLector = pedirDatos.pedirString("Introduzca el numero de lector");
                sql = "SELECT * FROM catalogo WHERE titulo = '" + numeroLector + "';";
                lectores = DBHandler.getLectores(sql);
                break;
            }
        }
        return libros;
    }

    public static void registrarEjemplar() {
        Lector lector;
        Direccion direccion;

        lector = crearLector();
        direccion = lector.getDireccion();

        if (!direccion.isRegistrado()) {
            registrarDireccion(direccion);
        }
        DBHandler.executeUpdate(lector.getInsertString());
    }
    private static void registrarDireccion(Direccion direccion) {
        if (!direccion.isRegistrado()) {
            // Registrar autor
            DBHandler.executeUpdate(direccion.getInsertString());
            // Almacenar el id del autor asignado en la BBDD
            direccion.setIdDireccion(direccion.setIdFromDB());
        }
    }
    public static void actualizar(int option) {
        switch (option) {
            case 1: {
                actualizarLibro();
                break;
            }
            case 2: {
                actualizarDireccion();
                break;
            }
        }
    }

    private static void actualizarLibro() {
        Lector lector;
        Lector nuevosDatos;
        String sql;
        int idLector;

        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            lector = escogerLector("SELECT * FROM catalogo");
            idLector = lector.getIdFromDB();
            System.out.println("Nuevos datos del libro:");
            nuevosDatos = crearLector();
            nuevosDatos.setIdLector(idLector);
            if (!nuevosDatos.getDireccion().isRegistrado()) {
                nuevosDatos.getDireccion().setIdDireccion(lector.getDireccion().getIdDireccion());
                DBHandler.executeUpdate(nuevosDatos.getDireccion().getUpdateString());
            }
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay libros registrados");
        }
    }

    private static void actualizarDireccion() {
        Lector lector;
        Direccion direccion;
        Direccion nuevosDatos;
        String sql;
        int idDireccion;

        if (DBHandler.hayRegistros("SELECT * FROM direcciones")) {
            lector = buscar();
            idDireccion = lector.getIdFromDB();
            System.out.println("Nueva direccion:");
            nuevosDatos = crearDireccion();
            nuevosDatos.setIdDireccion(idDireccion);
            sql = nuevosDatos.getUpdateString();
            DBHandler.executeUpdate(sql);
        } else {
            System.out.println("No hay direcciones registradas");
        }
    }
    public static void eliminarLector() {
        Lector lector;
        if (DBHandler.hayRegistros("SELECT * FROM lectores")) {
            lector = escogerLector("SELECT * FROM lectores");
            if (DBHandler.hayRegistros(lector.getSelectString())) {
                lector.setIdLector(lector.setIdFromDB());
                if (!DBHandler.hayRegistros("SELECT * FROM direcciones WHERE idLector = " + lector.getIdLector() + ";")) {
                    DBHandler.executeUpdate(lector.getDeleteString());
                    eliminarDireccion(lector.getDireccion());
                } else {
                    System.out.println("No puede eliminar el lector mientras haya direcciones vinculadas.");
                }
            } else {
                System.out.println("No se ha encontrado al lector");
            }
        } else {
            System.out.println("No hay lectores registrados");
        }
    }
    private static void eliminarDireccion(Direccion direccion) {
        if (DBHandler.hayRegistros("SELECT * FROM direcciones")) {
            if (DBHandler.hayRegistros(direccion.getSelectString())) {
                if (!DBHandler.hayRegistros("SELECT * FROM lectores WHERE idDireccion = " + direccion.getIdDireccion() + ";")) {
                    DBHandler.executeUpdate(direccion.getDeleteString());
                }
            }
        }
    }
    private static Lector escogerLector(String sql) {
        ArrayList<Lector> lectores;
        Lector lector;

        lectores = DBHandler.getLectores(sql);
        System.out.println("Escoja un lector:");
        mostrarLectores(lectores);
        lector = lectores.get(pedirDatos.pedirInt(1, lectores.size()) - 1);

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
        String apellido1;
        String apellido2 = "";
        String telefono;
        String email;
        Direccion direccion;

        nombre = pedirDatos.pedirString(" - Intorduzca el titulo del libro");
        apellido1 = pedirDatos.pedirString(" - Intorduzca el primer apellido");
        if (pedirDatos.confirmacion("El lector tiene un segundo apellido? (s/n)")) {
            apellido2 = pedirDatos.pedirString(" - Intorduzca el titulo del libro");
        }
        telefono = pedirDatos.pedirString("Introduzca el numero de telefono");
        if (pedirDatos.confirmacion("El lector tiene email? (s/n)")) {
            email = pedirDatos.pedirString("Introduzca el email");
        }
        direccion = crearDireccion();

        return lector;
    }
    private static Direccion crearDireccion() {
        Direccion direccion;
        String tipoVia;
        String nombreVia;
        int numero;
        int piso = 0;
        char portal = '\u0000';
        int codigoPostal;
        String localidad;
        String provincia;

        tipoVia = pedirDatos.pedirNombre(" - Introduzca el tipo de via");
        nombreVia = pedirDatos.pedirNombre(" - Introduzca el nombre de la via");
        numero = pedirDatos.pedirInt("Introduzca el numero", 1, 500);
        if (pedirDatos.confirmacion("La vivienda tiene varias plantas? (s/n)")) {
            piso = pedirDatos.pedirInt("Introduzca la planta", 1, 30);
            if (pedirDatos.confirmacion("La planta tiene varios portales? (s/n)")) {
                portal = pedirDatos.pedirString("Introduzca el portal").charAt(0);
            }
        }
        codigoPostal = pedirDatos.pedirInt("Introduzca el codigo postañ", 11111, 99999);
        localidad = pedirDatos.pedirNombre("Introduzca el nombre de la localidad");
        provincia = pedirDatos.pedirNombre("Introduzca el nombre de la provincia");

        if (piso != 0) {
            if (portal != '\u0000') {
                direccion = new Direccion(tipoVia, nombreVia, numero, piso, portal, codigoPostal, localidad, provincia);
            } else {
                direccion = new Direccion(tipoVia, nombreVia, numero, piso, codigoPostal, localidad, provincia);
            }
        } else{
            direccion = new Direccion(tipoVia, nombreVia, numero, codigoPostal, localidad, provincia);
        }
        return direccion;
    }
}
