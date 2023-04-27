package Biblioteca;

import DBManagement.DBHandler;

/**
 * Clase que permite crear los autores de los libros de biblioteca. Implementa la interfaz 'ElementoBiblioteca'
 *
 * @author Miguel Lopez Rodriguez
 */
public class Autor extends Persona implements ElementoBiblioteca {
    private int idAutor;

    public Autor() {
        super();
    }

    public Autor(String nombre, String apellidos) {
        super(nombre, apellidos);
        this.idAutor = getIdFromDB();
    }

    public Autor(int idAutor, String nombre, String apellidos) {
        super(nombre, apellidos);
        this.idAutor = idAutor;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    /**
     * Implementacion del metodo definido en la interfaz ElementoBiblioteca.
     * Retorna la sentencia sql para inserta un autor
     *
     * @return sentencia sql para insertar un autor
     */
    @Override
    public String getInsertString() {
        return "INSERT INTO autores (nombre, apellidos) VALUES ('" + getNombre() + "', '" + getApellidos() + "');";
    }

    /**
     * Implementacion del metodo definido en la interfaz ElementoBiblioteca.
     * Retorna la sentencia sql para seleccionar un autor en base a su nombre y apellidos
     *
     * @return sentencia sql para seleccionar un autor en base a su nombre y apellidos
     */
    @Override
    public String getSelectString() {
        return "SELECT * FROM autores WHERE nombre = '" + getNombre() + "' AND apellidos = '" + getApellidos() + "';";
    }

    /**
     * Implementacion del metodo definido en la interfaz ElementoBiblioteca.
     * Retorna la sentencia sql para actualizar los datos de un autor
     *
     * @return sentencia sql para actualizar los datos de un autor
     */
    @Override
    public String getUpdateString() {
        return "UPDATE autores SET nombre = '" + getNombre() + "', apellidos = '" + getApellidos() + "' WHERE idAutor = " + idAutor + ";";
    }

    /**
     * Implementacion del metodo definido en la interfaz ElemntoBiblioteca
     * Retorna la sentencia sql para eliminar un autor
     *
     * @return senencia sql para eliminar un autor
     */
    @Override
    public String getDeleteString() {
        return "DELETE FROM autores WHERE idAutor = " + idAutor;
    }

    /**
     * Implementacion del metodo definido en la interfaz ElementoBiblioteca,
     * Comprueba si el autor actual esta registrado en la bdd
     *
     * @return booleano que indica si el autor esta o no registrado en la bdd
     */
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }

    /**
     * Implementacion del metodo definido en la interfaz ElementoBiblioteca
     * Obtiene el id asignado en la bdd al autor actual
     *
     * @return id asignado en la bdd al autor actual
     */
    @Override
    public int getIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = DBHandler.getInt(getSelectString(), 1);
        }
        return id;
    }
}