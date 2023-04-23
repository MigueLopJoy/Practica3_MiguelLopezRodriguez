package Biblioteca;

import DBManagement.DBHandler;
public class Autor extends Persona implements Comparable<Autor>, ElementoBiblioteca {
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
        return  "INSERT INTO autores (nombre, apellido1, apellido2) VALUES ('"
                    + getNombre() + "', '" + getApellido1() + "', '" + getApellido2() + "');";
    }
    public String getSelectString() {
        return "SELECT * FROM autores WHERE nombre = '" + getNombre()
                    + "' AND apellido1 = '" + getApellido1() + "' AND apellido2 = '" + getApellido2() + "';";
    }
    public String getUpdateString() {
        return "UPDATE autores SET nombre = '" + getNombre() + "', apellido1 = '" + getApellido1()
                    + "', apellido2 = '" + getApellido2() + "' WHERE idAutor = " + idAutor;
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
    public String toString() {
        String toString;

        if (getApellido2() != null) {
            toString = getNombre() + " " + getApellido1() + " " + getApellido2();
        } else {
            toString = getNombre() + " " + getApellido1();
        }
        return toString;
    }
    @Override
    public int compareTo(Autor autor) {
        int resultadoComparacion;

        resultadoComparacion = getNombre().compareTo(autor.getNombre());
        if (resultadoComparacion == 0) {
            resultadoComparacion = getApellido1().compareTo(autor.getApellido2());
            if (resultadoComparacion == 0) {
                if (getApellido2() != null) {
                    resultadoComparacion = getApellido2().compareTo(autor.getApellido2());
                } else {
                    resultadoComparacion = -1;
                }
            }
        }
        return resultadoComparacion;
    }
}