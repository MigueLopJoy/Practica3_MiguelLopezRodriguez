package Biblioteca;

import DBManagement.DBHandler;
import DBManagement.MySQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
public class Libro {
    private int idLibro;
    private String titulo;
    private Autor autor;
    private LocalDate fechaPublicacion;
    private String editorial;
    public Libro(String titulo, Autor autor, LocalDate fechaPublicacion, String editorial) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }
    public Libro(int idLibro, String titulo, Autor autor, LocalDate fechaPublicacion, String editorial) {
        super();
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
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
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
    public String getInsertString() {
        return "INSERT INTO catalogo (titulo, idAutor, fechaPublicacion, editorial) VALUES ('"
                + titulo + "', '" + autor.getIdAutor() + "', '" + fechaPublicacion + "', '" + editorial + "');";
    }
    public boolean isRegistrado(Connection connection) {
        int numeroRegistros;
        boolean isRegistrado = false;
        numeroRegistros = DBHandler.obtenerCantidadRegistros(connection,
                "SELECT * FROM catalogo WHERE titulo = '" + titulo
                        + "' AND idAutor = '" + autor.getIdAutor() + "' AND editorial = " + editorial
                        + "' AND fecha_pblicacion = " + fechaPublicacion + "';");
        isRegistrado = numeroRegistros != 0 ? true : false;
        return  isRegistrado;
    }

    public int getIdLibroFromDB(Connection connection) {
        ResultSet resultSet = null;
        int idLibro = 0;
        resultSet = DBHandler.getResulset(connection, "SELECT idLibro FROM catalogo ORDER BY idLibro DESC LIMIT 1;");
        try {
            if (resultSet.next()) {
                idLibro = resultSet.getInt("idLibro");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idLibro;
    }
}
