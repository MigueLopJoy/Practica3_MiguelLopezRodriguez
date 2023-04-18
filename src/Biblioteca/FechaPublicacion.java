package Biblioteca;

import DBManagement.DBHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.time.Year;

public class FechaPublicacion {
    private int idFechaPublicacion;
    private LocalDate fechaPublicacion;
    private Year añoPublicacion;
    private Month mesPublicacion;
    private MonthDay diaPublicacion;
    private Libro libro;
    public FechaPublicacion(Libro libro) {
        this.libro = libro;
    }
    public FechaPublicacion(LocalDate fechaPublicacion, Year añoPublicacion, Month mesPublicacion, MonthDay diaPublicacion, Libro libro) {
        this.fechaPublicacion = fechaPublicacion;
        this.añoPublicacion = añoPublicacion;
        this.mesPublicacion = mesPublicacion;
        this.diaPublicacion = diaPublicacion;
        this.libro = libro;
    }
    public FechaPublicacion(int idFechaPublicacion, LocalDate fechaPublicacion, Year añoPublicacion, Month mesPublicacion, MonthDay diaPublicacion, Libro libro) {
        this.idFechaPublicacion = idFechaPublicacion;
        this.fechaPublicacion = fechaPublicacion;
        this.añoPublicacion = añoPublicacion;
        this.mesPublicacion = mesPublicacion;
        this.diaPublicacion = diaPublicacion;
        this.libro = libro;
    }

    public int getIdFechaPublicacion() {
        return idFechaPublicacion;
    }

    public void setIdFechaPublicacion(int idFechaPublicacion) {
        this.idFechaPublicacion = idFechaPublicacion;
    }

    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Year getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(Year añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public Month getMesPublicacion() {
        return mesPublicacion;
    }

    public void setMesPublicacion(Month mesPublicacion) {
        this.mesPublicacion = mesPublicacion;
    }

    public MonthDay getDiaPublicacion() {
        return diaPublicacion;
    }

    public void setDiaPublicacion(MonthDay diaPublicacion) {
        this.diaPublicacion = diaPublicacion;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
}
