package Biblioteca;


/**
 * Clase abstracta de la cual heredan las clases 'Lector' y 'Autor', estableciendo los atributos y metodos comunes a ambas,
 * e implementando la interfaz 'Comparable' para ordenar listas de objetos que hereden de esta clase.
 */
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

    /**
     * Crea y retorna una cadena de texto con la informacion del prestamo actual
     *
     * @return una cadena de texto con la informacion del prestamo actual
     */
    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }

    /**
     * Compara este objeto Persona con el objeto Persona pasado por persona para establecer un orden
     * en base a los nombres y apellidos de la persona
     *
     * @param persona el objeto que se va a comparar.
     * @return un entero negativo, cero o un entero positivo según si este objeto es menor, igual o mayor que el objeto especificado.
     */
    public int compareTo(Persona persona) {
        int resultadoComparacion;

        resultadoComparacion = getNombre().compareTo(persona.getNombre());
        if (resultadoComparacion == 0) {
            resultadoComparacion = getApellidos().compareTo(persona.getApellidos());
        }
        return resultadoComparacion;
    }
}
