package Biblioteca;

import DBManagement.DBHandler;

public abstract class Persona implements Comparable<Persona> {
    private String nombre;
    private String apellido1;
    private String apellido2;
    public Persona() {
        super();
    }
    public Persona(String nombre, String apellido1) {
        super();
        this.nombre = nombre;
        this.apellido1 = apellido1;
    }
    public Persona(String nombre, String apellido1, String apellido2) {
        super();
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
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
    public String toString() {
        String toString;

        if (getApellido2() != null) {
            toString = getNombre() + " " + getApellido1() + " " + getApellido2();
        } else {
            toString = getNombre() + " " + getApellido1();
        }
        return toString;
    }
    public int compareTo(Persona persona) {
        int resultadoComparacion;

        resultadoComparacion = getNombre().compareTo(persona.getNombre());
        if (resultadoComparacion == 0) {
            resultadoComparacion = getApellido1().compareTo(persona.getApellido2());
            if (resultadoComparacion == 0) {
                if (getApellido2() != null) {
                    resultadoComparacion = getApellido2().compareTo(persona.getApellido2());
                } else {
                    resultadoComparacion = -1;
                }
            }
        }
        return resultadoComparacion;
    }
}
