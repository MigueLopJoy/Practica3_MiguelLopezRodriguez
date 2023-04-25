package Biblioteca;

import DBManagement.DBHandler;
import User.pedirDatos;

public abstract class Persona implements Comparable<Persona> {
    private String nombre;
    private String apellidos;
    public Persona() {
        super();
    }
    public Persona(String nombre, String apellidos) {
        super();
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String toString() {
        return nombre + " " + apellidos;
    }
    public int compareTo(Persona persona) {
        int resultadoComparacion;

        resultadoComparacion = getNombre().compareTo(persona.getNombre());
        if (resultadoComparacion == 0) {
            resultadoComparacion = getApellidos().compareTo(persona.getApellidos());
        }
        return resultadoComparacion;
    }
}
