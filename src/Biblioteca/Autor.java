package Biblioteca;

public class Autor {
    private int idAutor;
    private String nombre;
    private String apellido1;
    private String apellido2;
    public Autor(String nombre, String apellido1, String apellido2) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
    public Autor(int idAutor, String nombre, String apellido1, String apellido2) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
}
