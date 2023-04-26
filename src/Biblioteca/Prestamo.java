package Biblioteca;

import DBManagement.DBHandler;
import Gestion.Lectores;
import Gestion.Prestamos;

import java.time.LocalDate;

public class Prestamo implements Comparable<Prestamo>, ElementoBiblioteca{
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

    @Override
    public String getInsertString() {
        return "INSERT INTO prestamos (idEjemplar, idLector, fecha_prestamo, fecha_devolucion, devuelto) " +
                "VALUES (" + ejemplar.getIdEjemplar() + ", " + lector.getIdLector() + ", '" + fechaPrestamo + "', '"
                + fechaDevolucion + "', '" + devuelto + "');";
    }

    @Override
    public String getSelectString() {
        return "SELECT FROM prestamos WHERE idEjemplar = '" + ejemplar.getIdEjemplar() + "', OR idLector = '" + lector.getIdLector() + "';";
    }

    @Override
    public String getUpdateString() {
        return "UPDATE prestamos SET devuelto = 'true', fecha_devolucion = '" + LocalDate.now() + "';";
    }

    @Override
    public String getDeleteString() {
        return "DELETE FROM prestamos WHERE idPrestamo = " + idPrestamo + ";";
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
        return ejemplar.toString()
                + "\n - " + lector.toString()
                + "\n Fecha prestamo: " + fechaPrestamo
                + "\n Fecha devolucion: " + fechaDevolucion;
    }

    @Override
    public int compareTo(Prestamo prestamo) {
        return prestamo.getFechaPrestamo().compareTo(prestamo.getFechaPrestamo());
    }
}
