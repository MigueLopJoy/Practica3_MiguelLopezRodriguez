package Biblioteca;

import Gestion.Utils;
import DBManagement.DBHandler;

/**
 * Clase que permite crear y manejar los lectores de la Biblioteca. Implementa la interfaz 'ElementoBiblioteca'
 *
 * @author Miguel Lopez Rodriguez
 */
public class Lector extends Persona implements ElementoBiblioteca {
    private int idLector;
    private String numeroLector;
    private String telefono;
    private String email;

    public Lector() {
        super();
    }

    public Lector(String numeroLector) {
        super();
        this.numeroLector = numeroLector;
    }

    public Lector(String nombre, String apellidos, String telefono) {
        super(nombre, apellidos);
        this.numeroLector = Utils.generarCodigo();
        this.telefono = telefono;
        this.idLector = getIdFromDB();
    }

    public Lector(String nombre, String apellidos, String telefono, String email) {
        super(nombre, apellidos);
        this.numeroLector = Utils.generarCodigo();
        this.telefono = telefono;
        this.email = email;
        this.idLector = getIdFromDB();
    }

    public Lector(int idLector, String nombre, String apellidos, String numeroLector, String telefono) {
        super(nombre, apellidos);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
    }

    public Lector(int idLector, String nombre, String apellidos, String numeroLector, String telefono, String email) {
        super(nombre, apellidos);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public String getNumeroLector() {
        return numeroLector;
    }

    public void setNumeroLector(String numeroLector) {
        this.numeroLector = numeroLector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para inserta un lector, que tomara una forma u otra en funcion de
     * si el lector tiene o no email
     *
     * @return sentencia sql para insertar un lector
     */
    @Override
    public String getInsertString() {
        String insertString;
        if (email != null) {
            insertString = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono, email) " + "VALUES ('" + getNombre() + "', '" + getApellidos() + "', '" + numeroLector + "', '" + telefono + "', '" + email + "');";
        } else {
            insertString = "INSERT INTO lectores (nombre, apellidos, numero_lector, numero_telefono) " + "VALUES ('" + getNombre() + "', '" + getApellidos() + "', '" + numeroLector + "', '" + telefono + "');";
        }
        return insertString;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para seleccionar un lector en base a sus datos personales
     *
     * @return sentencia sql para seleccionar un lector en base a sus datos personales
     */
    @Override
    public String getSelectString() {
        String selectString;

        if (email != null) {
            selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellidos = '" + getApellidos() + "' AND numero_lector = '" + numeroLector + "' AND numero_telefono = '" + telefono + "' AND email = '" + email + "';";
        } else {
            selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellidos = '" + getApellidos() + "' AND numero_lector = '" + numeroLector + "' AND numero_telefono = '" + telefono + "';";
        }
        return selectString;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para actualizar los datos de un lector, que tomara una forma u otra en
     * funcion de si el lector tiene o no email
     *
     * @return sentencia sql para actualizar los datos de un lector
     */
    @Override
    public String getUpdateString() {
        String updateString;
        if (email != null) {
            updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellidos = '" + getApellidos() + "', numero_lector = '" + numeroLector + "', numero_telefono = '" + telefono + "', email = '" + email + "' WHERE idLector = " + idLector;
        } else {
            updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellidos = '" + getApellidos() + "', numero_lector = '" + numeroLector + "', numero_telefono = '" + telefono + "' WHERE idLector = " + idLector;
        }
        return updateString;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para eliminar un lector
     *
     * @return senencia sql para eliminar un lector
     */
    public String getDeleteString() {
        return "DELETE FROM lectores WHERE numero_lector = '" + numeroLector + "';";
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Comprueba si el lector actual esta registrado en la bdd
     *
     * @return booleano que indica si el lector esta o no registrado en la bdd
     */
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Obtiene el id asignado en la bdd al lector actual
     *
     * @return id asignado en la bdd al lector actual
     */
    @Override
    public int getIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = DBHandler.getInt(getSelectString(), 1);
        }
        return id;
    }

    /**
     * Sobreescribe el metodo toString de su clase padre Persona, haciendo una llamada al mismo y añadiendo informacion
     * propia del lector.
     * Crea y retorna una cadena de texto con la informacion del lector actual, que tomara una forma u otra segun
     * tenga o no email
     *
     * @return una cadena de texto con la informacion del lector actual
     */
    @Override
    public String toString() {
        String toString;
        if (email != null) {
            toString = super.toString() + " - Nº lector: " + this.numeroLector + " - Contacto: " + telefono + ", " + email;
        } else {
            toString = super.toString() + " - Nº lector: " + this.numeroLector + " - Contacto: " + telefono;
        }
        return toString;
    }
}
