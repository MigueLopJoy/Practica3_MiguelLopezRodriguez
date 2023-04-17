package Gestion;

import java.time.LocalDate;
public class Libro {
    private int idLibro;
    private String titulo;
    private int idAutor;
    private LocalDate fechaPublicacion;
    private String editorial;
    public Libro(String titulo, int idAutor, LocalDate fechaPublicacion, String editorial) {
        super();
        this.titulo = titulo;
        this.idAutor = idAutor;
        this.fechaPublicacion = fechaPublicacion;
        this.editorial = editorial;
    }
    public Libro(int idLibro, String titulo, int idAutor, LocalDate fechaPublicacion, String editorial) {
        super();
        this.idLibro = idLibro;
        this.titulo = titulo;
        this.idAutor = idAutor;
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
    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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
        return "INSERT INTO catalogo (titulo, idAutor, fechaPublicacion, editorial) VALUES ('" + titulo + "', '" + idAutor + "', '" + fechaPublicacion + "', '" + editorial + "');";
    }
}
