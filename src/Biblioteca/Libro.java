package Biblioteca;

import DBManagement.DBHandler;

import java.time.Year;

public class Libro implements Comparable<Libro>, ElementoBiblioteca {
    private int idLibro;
    private String titulo;
    private Autor autor;
    private Year añoPublicacion;
    private String editorial;
    public Libro(){
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
    public String getInsertString() {
        return "INSERT INTO catalogo (titulo, idAutor, año_publicacion, editorial) VALUES ('"
                + titulo + "', '" + autor.getIdAutor() + "', '" + añoPublicacion + "',  '" + editorial + "');";
    }
    public String getSelectString() {
        return "SELECT * FROM catalogo WHERE titulo = '" + titulo
                + "' AND idAutor = " + autor.getIdAutor()
                + " AND año_publicacion = '" + añoPublicacion
                + "' AND editorial = '" + editorial + "';";
    }
    public String getUpdateString() {
        return "UPDATE catalogo SET titulo = '" + titulo + "', idAutor = " + autor.getIdAutor()
                + ", año_publicacion = " + añoPublicacion + ", editorial = '" + editorial
                + "' WHERE idLibro = " + idLibro;
    }
    public String getDeleteString() {
        return "DELETE FROM catalogo WHERE idLibro = " + idLibro;
    }
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }
    @Override
    public int getIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = DBHandler.getInt(getSelectString(), 1);
        }
        return id;
    }

    @Override
    public String toString() {
        return titulo + " / " + autor.toString() + " - " + editorial + ", " + añoPublicacion;
    }
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
    }}
