package Biblioteca;

public class Lector {
    private int idLector;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String numeroLector;
    private String telefono;
    private String email;
    private int idDireccion;

    public Lector(String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, int idDireccion) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
        this.idDireccion = idDireccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, int idDireccion) {
        this.idLector = idLector;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
        this.idDireccion = idDireccion;
    }
    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
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

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    public String getInsertString() {
        return "INSERT INTO lectores (nombre, apellido1, apellido2, numeroLector, telefono, email, idDireccion) VALUES ('" + nombre + "', '" + apellido1 + "', '" + apellido2 + "', '" + numeroLector + "', '" + telefono + "', '" + email + "', '" + idDireccion + "');";
    }
}
