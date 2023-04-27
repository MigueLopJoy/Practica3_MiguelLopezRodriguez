package Biblioteca;

import DBManagement.DBHandler;

import java.time.LocalDate;

/**
 * Clase que permite crear y gestionar los prestamos de ejemplares a lectores en la biblioteca
 *
 * @author Miguel Lopez Rodriguez
 */
public class Prestamo implements Comparable<Prestamo>, ElementoBiblioteca {
    int idPrestamo;
    Ejemplar ejemplar;
    Lector lector;
    LocalDate fechaPrestamo;
    LocalDate fechaDevolucion;
    int devuelto;

    public Prestamo() {
        super();
    }

    public Prestamo(Lector lector) {
        super();
        this.lector = lector;
    }

    public Prestamo(Ejemplar ejemplar) {
        super();
        this.ejemplar = ejemplar;
    }

    public Prestamo(Ejemplar ejemplar, Lector lector) {
        super();
        this.ejemplar = ejemplar;
        this.lector = lector;
        this.fechaPrestamo = LocalDate.now();
        this.fechaDevolucion = fechaPrestamo.plusDays(15);
        this.devuelto = 0;
    }

    public Prestamo(int idPrestamo, Ejemplar ejemplar, Lector lector, LocalDate fechaPrestamo, LocalDate fechaDevolucion, int devuelto) {
        this.idPrestamo = idPrestamo;
        this.ejemplar = ejemplar;
        this.lector = lector;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Ejemplar getEjemplar() {
        return ejemplar;
    }

    public void setEjemplar(Ejemplar ejemplar) {
        this.ejemplar = ejemplar;
    }

    public Lector getLector() {
        return lector;
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para inserta un prestamo
     *
     * @return sentencia sql para insertar un prestamo
     */
    @Override
    public String getInsertString() {
        return "INSERT INTO prestamos (idEjemplar, idLector, fecha_prestamo, fecha_devolucion, devuelto) " +
                "VALUES (" + ejemplar.getIdEjemplar() + ", " + lector.getIdLector() + ", '" + fechaPrestamo + "', '"
                + fechaDevolucion + "', '" + devuelto + "');";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para seleccionar un prestamo
     *
     * @return sentencia sql para seleccionar un prestamo
     */
    @Override
    public String getSelectString() {
        return "SELECT FROM prestamos WHERE idEjemplar = '" + ejemplar.getIdEjemplar() + "', OR idLector = '" + lector.getIdLector() + "';";
    }


    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para actualizar los datos de un prestamo
     *
     * @return sentencia sql para actualizar los datos de un prestamo
     */
    @Override
    public String getUpdateString() {
        return "UPDATE prestamos SET devuelto = 1, fecha_devolucion = '" + LocalDate.now() + "';";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Retorna la sentencia sql para eliminar un prestamo
     *
     * @return senencia sql para eliminar un prestamo
     */
    @Override
    public String getDeleteString() {
        return "DELETE FROM prestamos WHERE idPrestamo = " + idPrestamo + ";";
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Comprueba si el prestamo actual esta registrado en la bdd
     *
     * @return booleano que indica si el prestamo esta o no registrado en la bdd
     */
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }

    /**
     * Implementacion del metodo definido en la interfaz 'ElementoBiblioteca'
     * Obtiene el id asignado en la bdd al prestamo actual
     *
     * @return id asignado en la bdd al prestamo actual
     */
    @Override
    public int getIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = DBHandler.getInt(getSelectString(), 1);
        }
        return id;
    }

    /**
     * Crea y retorna una cadena de texto con la informacion del prestamo actual
     *
     * @return una cadena de texto con la informacion del prestamo actual
     */
    @Override
    public String toString() {
        return ejemplar.toString()
                + "\n - " + lector.toString()
                + "\n - Fecha prestamo: " + fechaPrestamo
                + "\n - Fecha devolucion: " + fechaDevolucion
                + "\n";
    }

    /**
     * Compara este objeto Prestamo con el objeto Prestamo pasado por persona para establecer un orden
     * en base a la fecha de prestamo
     *
     * @param prestamo el objeto que se va a comparar.
     * @return un entero negativo, cero o un entero positivo según si este objeto es menor, igual o mayor que el objeto especificado.
     */
    @Override
    public int compareTo(Prestamo prestamo) {
        return prestamo.getFechaPrestamo().compareTo(prestamo.getFechaPrestamo());
    }
}
