package Biblioteca;

import DBManagement.DBHandler;
public class Autor extends Persona  {
    private int idAutor;
    public Autor() {
        super();
    }
    public Autor(String nombre, String apellido1) {
        super(nombre, apellido1);
        this.idAutor = setIdFromDB();
    }
    public Autor(String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.idAutor = setIdFromDB();
    }
    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
    public String getInsertString() {
        String inserString;
        if (getApellido2() != null) {
            inserString = "INSERT INTO autores (nombre, apellido1, apellido2) VALUES ('"
                    + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "');";
        } else {
            inserString = "INSERT INTO autores (nombre, apellido1) VALUES ('"
                    + getNombre() + "', '" + getApellido1() + "');";
        }
        return  inserString;
    }
    public String getSelectString() {
        String selectString;
        if (getApellido2() != null) {
            selectString = "SELECT * FROM autores WHERE nombre = '" + getNombre()
                    + "' AND apellido1 = '" + getApellido1() + "' AND apellido2 = '" + getApellido2() + "';";
        } else {
            selectString = "SELECT * FROM autores WHERE nombre = '" + getNombre()
                    + "' AND apellido1 = '" + getApellido1() + "';";
        }
        return  selectString;
    }
    public String getUpdateString() {
        String updateString;
        if (getApellido2() != null) {
            updateString = "UPDATE autores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1()
                    + "', apellido2 = '" + getApellido2() + "' WHERE idAutor = " + idAutor + ";";
        } else {
            updateString = "UPDATE autores SET nombre = '" + getNombre() + "', apellido1 = '"
                    + getApellido1() + "' WHERE idAutor = " + idAutor + ";";
        }
        return updateString;
    }
    public String getDeleteString() {
        return "DELETE FROM autores WHERE idAutor = " + idAutor;
    }
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }
    public int getIdFromDB() {
        int idAutor = 0;
        idAutor = DBHandler.getInt(getSelectString(), 1);
        return idAutor;
    }
    public int setIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = getIdFromDB();
        }
        return id;
    }
}