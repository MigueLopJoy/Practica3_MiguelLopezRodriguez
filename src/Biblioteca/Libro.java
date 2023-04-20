package Biblioteca;

import DBManagement.DBHandler;
import User.pedirDatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;

public class Libro {
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
                + titulo + "', '" + autor.getIdAutor() + "', '" + añoPublicacion + "', '" + editorial + "');";
    }

    public void getDataFromDB() {

    }
    public String getSelectString() {
        return "SELECT * FROM catalogo WHERE titulo = '" + titulo
                + "' AND idAutor = " + autor.getIdAutor()
                + " AND año_publicacion = '" + añoPublicacion
                + "' AND editorial = '" + editorial + "';";
    }
    public boolean isRegistrado() {
        int numeroRegistros;
        boolean isRegistrado = false;
        numeroRegistros = DBHandler.obtenerCantidadRegistros(getSelectString());
        isRegistrado = numeroRegistros != 0 ? true : false;
        return isRegistrado;
    }
    public int getIdLibroFromDB() {
        ResultSet resultSet = null;
        int idLibro = DBHandler.getInt(getSelectString(), "idLibro");
        return idLibro;
    }
}
