package Biblioteca;

import DBManagement.DBHandler;
import Gestion.Utils;

/**
 * Clase que permite crear ejemplares de libros de la biblioteca. Implementa las interfaces 'Comparable' para ordenar
 * listas de ejemplares, y la itnerfaz 'ElementoBiblioteca'
 *
 * @author Miguel Lopez Rodriguez
 */
public class Ejemplar implements Comparable<Ejemplar>, ElementoBiblioteca {
    private int idEjemplar;
    private String codigoEjemplar;
    private Libro libro;

    public Ejemplar() {
        super();
    }

    public Ejemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public Ejemplar(Libro libro) {
        super();
        this.codigoEjemplar = Utils.generarCodigo();
        this.libro = libro;
        this.idEjemplar = getIdFromDB();
    }

    public Ejemplar(String codigoEjemplar, Libro libro) {
        super();
        this.codigoEjemplar = codigoEjemplar;
        this.libro = libro;
        this.idEjemplar = getIdFromDB();
    }

    public Ejemplar(int idEjemplar, String codigoEjemplar, Libro libro) {
        super();
        this.idEjemplar = idEjemplar;
        this.codigoEjemplar = codigoEjemplar;
        this.libro = libro;
    }

    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(int idLibro) {
        this.libro = libro;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para inserta un ejemplar
     * @return sentencia sql para insertar un ejemplar
     */

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para seleccionar un ejemplar en base a su nombre y apellidos
     *
     * @return sentencia sql para seleccionar un ejemplar en base a su nombre y apellidos
     */
    @Override
    public String getInsertString() {
        return "INSERT INTO ejemplares (codigo_ejemplar, idLibro) VALUES ('" + codigoEjemplar + "', '" + libro.getIdLibro() + "');";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para seleccionar un ejemplar en base a su codigo de Ejemplar
     *
     * @return sentencia sql para seleccionar un ejemplar en base a su codigo de ejemplar
     */
    @Override
    public String getSelectString() {
        return "SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + codigoEjemplar + "';";
    }

    @Override
    public String getUpdateString() {
        return null;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para eliminar un ejemplar
     *
     * @return senencia sql para eliminar un ejemplar
     */
    @Override
    public String getDeleteString() {
        return "DELETE FROM ejemplares WHERE codigo_ejemplar = '" + codigoEjemplar + "';";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Comprueba si el ejemplar actual esta registrado en la bdd
     *
     * @return booleano que indica si el ejemplar esta o no registrado en la bdd
     */
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Obtiene el id asignado en la bdd al ejemplar actual
     *
     * @return id asignado en la bdd al ejemplar actual
     */
    @Override
    public int getIdFromDB() {
        int idEjemplar = 0;
        if (isRegistrado()) {
            idEjemplar = DBHandler.getInt(getSelectString(), 1);
        }
        return idEjemplar;
    }

    /**
     * Crea y retorna una cadena de texto con la informacion del ejemplar actual
     *
     * @return una cadena de texto con la informacion del ejemplar actual
     */
    @Override
    public String toString() {
        return libro.toString() + " - Codigo: " + codigoEjemplar;
    }

    /**
     * Compara este objeto Ejemplar con el objeto Ejemplar pasado por persona para establecer un orden
     * en base al titulo, en primer lugar, y al nombres y apellidos del autor, en segundo.
     *
     * @param ejemplar el objeto que se va a comparar.
     * @return un entero negativo, cero o un entero positivo según si este objeto es menor, igual o mayor que el objeto especificado.
     */
    @Override
    public int compareTo(Ejemplar ejemplar) {
        int resultadoComparacion;

        resultadoComparacion = libro.getTitulo().compareTo(ejemplar.getLibro().getTitulo());
        if (resultadoComparacion == 0) {
            resultadoComparacion = libro.getAutor().getNombre().compareTo(ejemplar.getLibro().getAutor().getNombre());
            if (resultadoComparacion == 0) {
                resultadoComparacion = libro.getAutor().getApellidos().compareTo(ejemplar.getLibro().getAutor().getApellidos());
                if (resultadoComparacion == 0) {
                    resultadoComparacion = codigoEjemplar.compareTo(ejemplar.codigoEjemplar);
                }
            }
        }
        return resultadoComparacion;
    }
}
