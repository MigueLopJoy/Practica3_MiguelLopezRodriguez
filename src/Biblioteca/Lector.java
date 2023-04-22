package Biblioteca;

import DBManagement.DBHandler;

public class Lector extends Persona implements ElementoBiblioteca {
    private int idLector;
    private String numeroLector;
    private String telefono;
    private String email;
    private int idDireccion;

    public Lector(String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, int idDireccion) {
        super(nombre, apellido1, apellido2);
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
        this.idDireccion = idDireccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, int idDireccion) {
        super(nombre, apellido1, apellido2);
        this.idLector = idLector;
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
        return "INSERT INTO lectores (nombre, apellido1, apellido2, numeroLector, telefono, email, idDireccion) " +
                "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "', '" + numeroLector
                + "', '" + telefono + "', '" + email + "', '" + idDireccion + "');";
    }
    @Override
    public String getSelectString() {
        return null;
    }
    @Override
    public String getUpdateString() {
        return null;
    }
    public String getDeleteString() {
        return "DELETE FROM ejemplares WHERE numeroLector = " + numeroLector;
    }
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }
    @Override
    public int getIdFromDB() {
        int idDireccion = 0;
        idDireccion = DBHandler.getInt(getSelectString(), 1);
        return idDireccion;
    }
    @Override
    public int setIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = getIdFromDB();
        }
        return id;
    }
}
