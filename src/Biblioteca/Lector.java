package Biblioteca;

import DBManagement.DBHandler;
public class Lector extends Persona implements ElementoBiblioteca {
    private int idLector;
    private String numeroLector;
    private String telefono;
    private String email;
    private Direccion direccion;

    public Lector(String nombre, String apellido1, String telefono, Direccion direccion) {
        super(nombre, apellido1);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.direccion = direccion;
        this.idLector = getIdFromDB();
    }
    public Lector(String nombre, String apellido1, String apellido2, String telefono, Direccion direccion, int tipo) {
        super(nombre, apellido1, apellido2);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.direccion = direccion;
        this.idLector = getIdFromDB();
    }
    public Lector(String nombre, String apellido1, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.idLector = getIdFromDB();
    }
    public Lector(String nombre, String apellido1, String apellido2, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1, apellido2);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.idLector = getIdFromDB();
    }
    public Lector(int idLector, String nombre, String apellido1, String numeroLector, String telefono, Direccion direccion) {
        super(nombre, apellido1);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String numeroLector, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String apellido2, String numeroLector, String telefono, Direccion direccion, int tipo) {
        super(nombre, apellido1, apellido2);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    public Lector(int idLector, String nombre, String apellido1, String apellido2, String numeroLector, String telefono, String email, Direccion direccion) {
        super(nombre, apellido1, apellido2);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
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
        String insertString;
        if (getApellido2() != null) {
            if (this.email != null) {
                insertString = "INSERT INTO lectores (nombre, apellido1, apellido2, numeroLector, telefono, email, idDireccion) " +
                        "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "', '" + numeroLector
                        + "', '" + telefono + "', '" + email + "', '" + direccion.getIdDireccion() + "');";
            } else {
                insertString = "INSERT INTO lectores (nombre, apellido1, apellido2, numeroLector, telefono, idDireccion) " +
                        "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "', '" + numeroLector
                        + "', '" + telefono + "', '" + direccion.getIdDireccion() + "');";            }
        } else {
            if (email != null) {
                insertString = "INSERT INTO lectores (nombre, apellido1, numeroLector, telefono, email, idDireccion) " +
                        "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "', '" + numeroLector
                        + "', '" + telefono + "', '" + email + "', '" + direccion.getIdDireccion() + "');";
            } else {
                insertString = "INSERT INTO lectores (nombre, apellido1, numeroLector, telefono, idDireccion) " +
                        "VALUES ('" + getNombre() + "', '" + getApellido1() + "', '" + numeroLector
                        + "', '" + telefono + "', '" + "', '" + direccion.getIdDireccion() + "');";
            }
        }
        return insertString;
    }
    @Override
    public String getSelectString() {
        String selectString;

        if (getApellido2() != null) {
            if (this.email != null) {
                selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellido1 = '" + getApellido1()
                        + "' AND apellido2 = '" + getApellido2() + "' AND numeroLector = '" + numeroLector + "' AND telefono = '" + telefono
                        + "' AND email = '" + email + "' AND idDireccion = '" + direccion.getIdDireccion() + "';";
            } else {
                selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellido1 = '" + getApellido1()
                        + "' AND apellido2 = '" + getApellido2() + "' AND numeroLector = '" + numeroLector + "' AND telefono = '" + telefono
                       + "' AND idDireccion = '" + direccion.getIdDireccion() + "';";
            }
        } else {
            if (email != null) {
                selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellido1 = '" + getApellido1()
                        + "' AND numeroLector = '" + numeroLector + "' AND telefono = '" + telefono + "' AND email = '" + email
                        + "' AND idDireccion = '" + direccion.getIdDireccion() + "';";
            } else {
                selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellido1 = '" + getApellido1()
                        + "' AND numeroLector = '" + numeroLector + "' AND telefono = '" + telefono + "' AND idDireccion = '"
                        + direccion.getIdDireccion() + "';";
            }
        }
        return selectString;
    }
    @Override
    public String getUpdateString() {
        String updateString;

        if (getApellido2() != null) {
            if (this.email != null) {
                updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1() + "', apellido2 = '" + getApellido2()
                        + "', numeroLector = '" + numeroLector + "', telefono = '" + telefono + "', email = '" + email
                        + "', idDireccion = '" + direccion.getIdDireccion() + "' WHERE idLector = " + idLector;
            } else {
                updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1() + "', apellido2 = '" + getApellido2()
                        + "', numeroLector = '" + numeroLector + "', telefono = '" + telefono + "', idDireccion = '"
                        + direccion.getIdDireccion() + "' WHERE idLector = " + idLector;
            }
        } else {
            if (email != null) {
                updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1()
                        + "', numeroLector = '" + numeroLector + "', telefono = '" + telefono + "', email = '" + email
                        + "', idDireccion = '" + direccion.getIdDireccion() + "' WHERE idLector = " + idLector;
            } else {
                updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1()
                        + "', numeroLector = '" + numeroLector + "', telefono = '" + telefono + "', idDireccion = '"
                        + direccion.getIdDireccion() + "' WHERE idLector = " + idLector;
            }
        }
        return updateString;
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
        int id = 0;
        if (isRegistrado()) {
            id = DBHandler.getInt(getSelectString(), 1);
        }
        return id;
    }
    @Override
    public String toString() {
        return super.toString() + " Numero de lector: " + this.numeroLector;
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
