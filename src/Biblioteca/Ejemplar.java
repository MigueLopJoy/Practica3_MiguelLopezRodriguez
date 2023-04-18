package Biblioteca;

import DBManagement.DBHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        return "INSERT INTO ejemplares (codigoEjemplar, idLibro) VALUES ('" + codigoEjemplar + "', '" + libro.getIdLibro() + "');";
    }
    private String generarCodigoEjemplar() {
        String codigoEjemplar = "";

        for (int i = 0; i < 6; i++) {
            codigoEjemplar += (int)(Math.random()*10)+ 1;
        }
        for (int i = 0; i < 2; i++) {
            codigoEjemplar += (char)(Math.random() * 90 - 65) + 90;
        }

        return codigoEjemplar;
    }

    public int getIdEjemplarFromDB(Connection connection) {
        ResultSet resultSet = null;
        int idEjemplar = 0;
        resultSet = DBHandler.getResulset(connection, "SELECT idEjemplar FROM catalogo ORDER BY idEjemplar DESC LIMIT 1;");
        try {
            if (resultSet.next()) {
                idEjemplar = resultSet.getInt("idEjemplar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idEjemplar;
    }
}
