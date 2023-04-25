package Biblioteca;

import DBManagement.DBHandler;
public class Ejemplar implements Comparable<Ejemplar>, ElementoBiblioteca {
    private int idEjemplar;
    private String codigoEjemplar;
    private Libro libro;

    public Ejemplar () {
        super();
    }

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
    public Ejemplar(int idEjemplar, String codigoEjemplar, Libro libro) {
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
        return codigoEjemplar + ": " + libro.toString();
    }
    @Override
    public int compareTo(Ejemplar ejemplar) {
        int resultadoComparacion;

        resultadoComparacion = libro.getTitulo().compareTo(ejemplar.getLibro().getTitulo());
        if (resultadoComparacion == 0) {
            resultadoComparacion = libro.getAutor().getNombre().compareTo(ejemplar.getLibro().getAutor().getNombre());
            if (resultadoComparacion == 0) {
                resultadoComparacion = libro.getAutor().getApellidos().compareTo(ejemplar.getLibro().getAutor().getApellidos());
                if (resultadoComparacion == 0) {
                    resultadoComparacion = codigoEjemplar.compareTo(ejemplar.codigoEjemplar);
                }
            }
        }
        return resultadoComparacion;
    }
    private String generarCodigoEjemplar() {
        String codigoEjemplar = "";
        char[] letras = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

        do {
            for (int i = 0; i < 8; i++) {
                codigoEjemplar += (int)(Math.random()*10);
            }
            codigoEjemplar += letras[(int)(Math.random() * 26)];
        } while (isRepetido(codigoEjemplar));

        return codigoEjemplar;
    }

    private boolean isRepetido(String codigoEjemplar) {
        boolean repetido = false;

        if(DBHandler.hayRegistros(getSelectString())){
            repetido = true;
        }
        return repetido;
    }
}
