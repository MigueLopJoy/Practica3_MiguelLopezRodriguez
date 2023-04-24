package Biblioteca;

import DBManagement.DBHandler;
public class Ejemplar implements Comparable<Ejemplar>, ElementoBiblioteca {
    private int idEjemplar;
    private String codigoEjemplar;
    private Libro libro;

    public Ejemplar (String codigoEjemplar) {
        this.codigoEjemplar = codigoEjemplar;
    }
    public Ejemplar(Libro libro) {
        super();
        this.codigoEjemplar = generarCodigoEjemplar();
        this.libro = libro;
        this.idEjemplar = getIdFromDB();
    }
    public Ejemplar(String codigoEjemplar, Libro libro) {
        super();
        this.codigoEjemplar = codigoEjemplar;
        this.libro = libro;
        this.idEjemplar = getIdFromDB();
    }
    public Ejemplar(int idEjemplar, String codigoEjemplar, int idLibro) {
        super();
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
        return "SELECT * FROM ejemplares WHERE codigo_ejemplar = '" + codigoEjemplar + "';";
    }
    @Override
    public String getUpdateString() {
        return "";
    }
    @Override
    public String getDeleteString() {
        return "DELETE FROM ejemplares WHERE codigo_ejemplar = '" + codigoEjemplar + "';";
    }
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }
    @Override
    public int getIdFromDB() {
        int idEjemplar = 0;
        if (isRegistrado()) {
            idEjemplar = DBHandler.getInt(getSelectString(), 1);
        }
        return idEjemplar;
    }
    @Override
    public String toString() {
        return codigoEjemplar + " - " + libro.toString();
    }
    @Override
    public int compareTo(Ejemplar ejemplar) {
        int resultadoComparacion;

        resultadoComparacion = libro.getTitulo().compareTo(ejemplar.getLibro().getTitulo());
        if (resultadoComparacion == 0) {
            resultadoComparacion = libro.getAutor().getNombre().compareTo(ejemplar.getLibro().getAutor().getNombre());
            if (resultadoComparacion == 0) {
                resultadoComparacion = libro.getAutor().getApellido1().compareTo(ejemplar.getLibro().getAutor().getApellido1());
                if (resultadoComparacion == 0) {
                    resultadoComparacion = libro.getAutor().getApellido2().compareTo(ejemplar.getLibro().getAutor().getApellido2());
                    if (resultadoComparacion == 0) {
                        resultadoComparacion = codigoEjemplar.compareTo(ejemplar.codigoEjemplar);
                    }
                }
            }
        }
        return resultadoComparacion;
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
