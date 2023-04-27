package Biblioteca;

import DBManagement.DBHandler;

import java.time.Year;

/**
 * Clase que permite crear y gestionar los libros de la biblioteca. Implementa las interfaces 'ElementoBiblioteca' y 'Comparable',
 * que permite ordenar listas de libros.
 *
 * @author Miguel Lopez Rodriguez
 */
public class Libro implements Comparable<Libro>, ElementoBiblioteca {
    private int idLibro;
    private String titulo;
    private Autor autor;
    private Year añoPublicacion;
    private String editorial;

    public Libro() {
        super();
    }

    public Libro(String titulo, Autor autor, Year añoPublicacion, String editorial) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.editorial = editorial;
        this.idLibro = getIdFromDB();
    }

    public Libro(int idLibro, String titulo, Autor autor, Year añoPublicacion, String editorial) {
        super();
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.añoPublicacion = añoPublicacion;
        this.editorial = editorial;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Year getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(Year añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para inserta un libro
     *
     * @return sentencia sql para insertar un libro
     */
    public String getInsertString() {
        return "INSERT INTO catalogo (titulo, idAutor, año_publicacion, editorial) VALUES ('" + titulo + "', '" + autor.getIdAutor() + "', '" + añoPublicacion + "',  '" + editorial + "');";
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para seleccionar un libro
     *
     * @return sentencia sql para seleccionar un libro
     */
    public String getSelectString() {
        return "SELECT * FROM catalogo WHERE titulo = '" + titulo + "' AND idAutor = " + autor.getIdAutor() + " AND año_publicacion = '" + añoPublicacion + "' AND editorial = '" + editorial + "';";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para actualizar los datos de un libro
     *
     * @return sentencia sql para actualizar los datos de un libro
     */
    public String getUpdateString() {
        return "UPDATE catalogo SET titulo = '" + titulo + "', idAutor = " + autor.getIdAutor() + ", año_publicacion = " + añoPublicacion + ", editorial = '" + editorial + "' WHERE idLibro = " + idLibro;
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para eliminar un libro
     *
     * @return senencia sql para eliminar un libro
     */
    public String getDeleteString() {
        return "DELETE FROM catalogo WHERE idLibro = " + idLibro;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Comprueba si el libro actual esta registrado en la bdd
     *
     * @return booleano que indica si el libro esta o no registrado en la bdd
     */
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Obtiene el id asignado en la bdd al libro actual
     *
     * @return id asignado en la bdd al libro actual
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
     * Crea y retorna una cadena de texto con la informacion del libro actual
     *
     * @return una cadena de texto con la informacion del libro actual
     */
    @Override
    public String toString() {
        return titulo + " / " + autor.toString() + " - " + editorial + ", " + añoPublicacion;
    }

    /**
     * Compara este objeto Libro con el objeto Libro pasado por persona para establecer un orden
     * en base al titulo del libro, en primer lugar, y a los nombres y apellidos del autor, en segundo
     *
     * @param libro el objeto que se va a comparar.
     * @return un entero negativo, cero o un entero positivo según si este objeto es menor, igual o mayor que el objeto especificado.
     */
    @Override
    public int compareTo(Libro libro) {
        int resultadoComparacion;

        resultadoComparacion = titulo.toLowerCase().compareTo(libro.getTitulo().toLowerCase());
        if (resultadoComparacion == 0) {
            resultadoComparacion = autor.getNombre().compareTo(libro.getAutor().getNombre());
            if (resultadoComparacion == 0) {
                resultadoComparacion = autor.getApellidos().compareTo(libro.getAutor().getApellidos());
            }
        }
        return resultadoComparacion;
    }
}
