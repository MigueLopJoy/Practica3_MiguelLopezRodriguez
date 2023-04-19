package Biblioteca;

import DBManagement.DBHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
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

    public String getInsertString() {
        String insertString = "";

        if (apellido2 == "") {
            insertString = "INSERT INTO autores (nombre, apellido1) VALUES ('"
                    + nombre + "', '" + apellido1 + "');";
        } else {
            insertString = "INSERT INTO autores (nombre, apellido1, apellido2) VALUES ('"
                    + nombre + "', '" + apellido1 + "', '" + apellido2 + "');";
        }
        return insertString;
    }

    public String getSelectString() {
        String selectString = "";

        if (apellido2 == "") {
            selectString = "SELECT * FROM autores WHERE nombre = '" + nombre
                    + "' AND apellido1 = '" + apellido1 + "';";
        } else {
            selectString = "SELECT * FROM autores WHERE nombre = '" + nombre
                    + "' AND apellido1 = '" + apellido1 + "' AND apellido2 = '" + apellido2 + "';";
        }
        return selectString;
    }
    public boolean isRegistrado() {
        int numeroRegistros;
        boolean isRegistrado = false;
        numeroRegistros = DBHandler.obtenerCantidadRegistros(getSelectString());
        isRegistrado = numeroRegistros != 0 ? true : false;
        return  isRegistrado;
    }
    public int getIdAutorFromDB() {
        int idAutor = 0;
        idAutor = DBHandler.getId(getSelectString());
        return idAutor;
    }
}