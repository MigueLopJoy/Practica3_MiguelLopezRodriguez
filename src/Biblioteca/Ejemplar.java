package Biblioteca;

import DBManagement.DBHandler;

import java.sql.ResultSet;
public class Ejemplar {
    private int idEjemplar;
    private String codigoEjemplar;
    private Libro libro;
    public Ejemplar(Libro libro) {
        this.codigoEjemplar = generarCodigoEjemplar();
        this.libro = libro;
    }
    public Ejemplar(int idEjemplar, String codigoEjemplar, int idLibro) {
        this.idEjemplar = idEjemplar;
        this.codigoEjemplar = codigoEjemplar;
        this.libro = libro;
    }
    public int getIdEjemplar() {
        return idEjemplar;
    }

    public void setIdEjemplar(int idEjemplar) {
        this.idEjemplar = idEjemplar;
    }

    public String getCodigoEjemplar() {
        return codigoEjemplar;
    }

    public void setCodigoEjemplar(String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(int idLibro) {
        this.libro = libro;
    }
    public String getInsertString() {
        return "INSERT INTO ejemplares (codigo_ejemplar, idLibro) VALUES ('" + codigoEjemplar + "', '" + libro.getIdLibro() + "');";
    }
    public String getSelectString() {
        return "SELECT * FROM ejemplares WHERE idLibro = '" + libro.getIdLibro()
                + "' AND codigo_ejemplar = '" + codigoEjemplar + "';";
    }
    public int getIdEjemplarFromDB() {
        int idEjemplar;
        idEjemplar = DBHandler.getInt(getSelectString(), "idEjemplar");
        return idEjemplar;
    }
    private String generarCodigoEjemplar() {
        String codigoEjemplar = "";
        char[] letras = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        for (int i = 0; i < 6; i++) {
            codigoEjemplar += (int)(Math.random()*10);
        }
        for (int i = 0; i < 2; i++) {
            codigoEjemplar += letras[(int)(Math.random() * 26)];
        }
        return codigoEjemplar;
    }
}
