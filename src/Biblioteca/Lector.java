package Biblioteca;

import DBManagement.DBHandler;

public class Lector extends Persona implements ElementoBiblioteca {
    private int idLector;
    private String numeroLector;
    private String telefono;
    private String email;
    private Direccion direccion;

    public Lector(String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1, apellido2);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1, apellido2);
        this.idLector = idLector;
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
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
    public Direccion getDireccion() {
        return direccion;
    }
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    @Override
    public String getInsertString() {
        return "INSERT INTO lectores (nombre, apellido1, apellido2, numeroLector, telefono, email, idDireccion) " +
                "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "', '" + numeroLector
                + "', '" + telefono + "', '" + email + "', '" + direccion.getIdDireccion() + "');";
    }
    @Override
    public String getSelectString() {
        return "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellido1 = '" + getApellido1()
                + "' AND apellido2 = '" + getApellido2() + "' AND numeroLector = '" + numeroLector + "' AND telefono = '" + telefono
                + "' AND email = '" + email + "' AND idDireccion = '" + direccion.getIdDireccion() + "';";
    }
    @Override
    public String getUpdateString() {
        return "UPDATE lectores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1() + "', apellido2 = '" + getApellido2()
                + "', numeroLector = '" + numeroLector + "', telefono = '" + telefono + "', email = '" + email
                + "', idDireccion = '" + direccion.getIdDireccion() + "' WHERE idLector = " + idLector;
    }
    public String getDeleteString() {
        return "DELETE FROM lectores WHERE numeroLector = " + numeroLector;
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
    private String generarNumeroLector() {
        String codigoEjemplar = "";
        char[] letras = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        for (int i = 0; i < 8; i++) {
            codigoEjemplar += (int)(Math.random()*10);
        }
        codigoEjemplar += letras[(int)(Math.random() * 26)];
        return codigoEjemplar;
    }
}
