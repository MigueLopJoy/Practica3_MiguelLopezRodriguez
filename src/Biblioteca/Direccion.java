package Biblioteca;

import DBManagement.DBHandler;

public class Direccion implements ElementoBiblioteca {
    private int idDireccion;
    private String tripoVia;
    private String nombreVia;
    private int numero;
    private int piso;
    private char portal;
    private int codigoPostal;
    private String localidad;
    private String provincia;

    public Direccion() {
        super();
    }
    public Direccion(String tripoVia, String nombreVia, int numero, int piso, char portal, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tripoVia = tripoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.piso = piso;
        this.portal = portal;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Direccion(int idDireccion, String tripoVia, String nombreVia, int numero, int piso, char portal, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tripoVia = tripoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.piso = piso;
        this.portal = portal;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getTripoVia() {
        return tripoVia;
    }

    public void setTripoVia(String tripoVia) {
        this.tripoVia = tripoVia;
    }

    public String getNombreVia() {
        return nombreVia;
    }

    public void setNombreVia(String nombreVia) {
        this.nombreVia = nombreVia;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public char getPortal() {
        return portal;
    }

    public void setPortal(char portal) {
        this.portal = portal;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(int codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    @Override
    public String getInsertString() {
        return null;
    }
    @Override
    public String getSelectString() {
        return null;
    }
    @Override
    public String getUpdateString() {
        return null;
    }
    public String getDeleteString() {
        return "DELETE FROM ejemplares WHERE idDireccion = " + idDireccion;
    }
    @Override
    public boolean isRegistrado() {
        return DBHandler.hayRegistros(getSelectString());
    }
    @Override
    public int getIdFromDB() {
        int idDireccion = 0;
        idDireccion = DBHandler.getInt(getSelectString(), 1);
        return idDireccion;
    }
    @Override
    public int setIdFromDB() {
        int id = 0;
        if (isRegistrado()) {
            id = getIdFromDB();
        }
        return id;
    }
}
