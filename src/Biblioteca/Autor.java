package Biblioteca;

import DBManagement.DBHandler;
public class Autor extends Persona implements ElementoBiblioteca {
    private int idAutor;
    public Autor() {
        super();
    }
    public Autor(String nombre, String apellido1) {
        super(nombre, apellido1);
        this.idAutor = getIdFromDB();
    }
    public Autor(String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.idAutor = getIdFromDB();
    }

    public Autor(int idAutor, String nombre, String apellido1) {
        super(nombre, apellido1);
        this.idAutor = idAutor;
    }
    public Autor(int idAutor, String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2);
        this.idAutor = idAutor;
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
}