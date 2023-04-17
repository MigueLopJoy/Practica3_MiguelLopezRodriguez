package Biblioteca;

public class Ejemplar {
    private int idEjemplar;
    private String codigoEjemplar;
    private int idLibro;

    public Ejemplar() {
        super();
    }
    public Ejemplar(String codigoEjemplar, int idLibro) {
        this.codigoEjemplar = codigoEjemplar;
        this.idLibro = idLibro;
    }
    public Ejemplar(int idEjemplar, String codigoEjemplar, int idLibro) {
        this.idEjemplar = idEjemplar;
        this.codigoEjemplar = codigoEjemplar;
        this.idLibro = idLibro;
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

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }
    public String getInsertString() {
        return "INSERT INTO ejemplares (codigoEjemplar, idLibro) VALUES ('" + codigoEjemplar + "', '" + idLibro + "');";
    }
}
