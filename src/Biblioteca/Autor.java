package Biblioteca;

import DBManagement.DBHandler;
public class Autor extends Persona implements ElementoBiblioteca {
    private int idAutor;
    public Autor() {
        super();
    }
    public Autor(String nombre, String apellidos) {
        super(nombre, apellidos);
        this.idAutor = getIdFromDB();
    }
    public Autor(int idAutor, String nombre, String apellidos) {
        super(nombre, apellidos);
        this.idAutor = idAutor;
    }
    public int getIdAutor() {
        return idAutor;
    }
    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }
    public String getInsertString() {
        return "INSERT INTO autores (nombre, apellidos) VALUES ('"
                    + getNombre() + "', '" + getApellidos() + "');";
    }
    public String getSelectString() {
        return "SELECT * FROM autores WHERE nombre = '" + getNombre()
                    + "' AND apellidos = '" + getApellidos() + "';";
    }
    public String getUpdateString() {
        return "UPDATE autores SET nombre = '" + getNombre() + "', apellidos = '"
                    + getApellidos() + "' WHERE idAutor = " + idAutor + ";";
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