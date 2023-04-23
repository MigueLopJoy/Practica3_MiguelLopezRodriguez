package Biblioteca;

import DBManagement.DBHandler;

public class Direccion implements ElementoBiblioteca {
    private int idDireccion;
    private String tipoVia;
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

    public Direccion(String tipoVia, String nombreVia, int numero, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Direccion(String tipoVia, String nombreVia, int numero, int piso, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.piso = piso;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Direccion(String tipoVia, String nombreVia, int numero, int piso, char portal, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tipoVia = tipoVia;
        this.nombreVia = nombreVia;
        this.numero = numero;
        this.piso = piso;
        this.portal = portal;
        this.codigoPostal = codigoPostal;
        this.localidad = localidad;
        this.provincia = provincia;
    }
    public Direccion(int idDireccion, String tipoVia, String nombreVia, int numero, int piso, char portal, int codigoPostal, String localidad, String provincia) {
        super();
        this.idDireccion = idDireccion;
        this.tipoVia = tipoVia;
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

        public String getTipoVia() {
            return tipoVia;
        }

        public void setTipoVia(String tripoVia) {
            this.tipoVia = tripoVia;
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
        String insertString;
        if (piso != 0) {
            if (portal != '\u0000'){
                insertString = "INSERT INTO lectores (tipo_via, nombre_via, numero, piso, portal, codigo_postal, localidad, provincia) " +
                        "VALUES ('" + tipoVia + "', '" + nombreVia + "', " + numero + ", " + piso + ", '" + portal + "', "
                        + codigoPostal + ", '" + localidad + "', '" + provincia + "');";
            } else {
                insertString = "INSERT INTO lectores (tipo_via, nombre_via, numero, piso, codigo_postal, localidad, provincia) " +
                        "VALUES ('" + tipoVia + "', '" + nombreVia + "', " + numero + ", " + piso + ", " + codigoPostal
                        + ", '" + localidad + "', '" + provincia + "');";
            }
        } else {
            insertString = "INSERT INTO lectores (tipo_via, nombre_via, numero, codigo_postal, localidad, provincia) " +
                    "VALUES ('" + tipoVia + "', '" + nombreVia + "', " + numero + ", " + codigoPostal + ", '" + localidad
                    + "', '" + provincia + "');";
        }
        return insertString;
    }
    @Override
    public String getSelectString() {
        String selectString;
        if (piso != 0) {
            if (portal != '\u0000'){
                selectString = "SELECT * FROM direcciones WHERE tipo_via = '" + tipoVia + "' AND nombre_via = '" + nombreVia
                        + "' AND numero = " + numero + " AND piso = " + piso + " AND portal = '" + portal + "' AND codigo_postal = "
                        + codigoPostal + " AND localidad = '" + localidad + "' AND provincia = '" + provincia + "';";
            } else {
                selectString = "SELECT * FROM direcciones WHERE tipo_via = '" + tipoVia + "' AND nombre_via = '" + nombreVia
                        + "' AND numero = '" + numero + "' AND piso = " + piso + " AND portal = '" + portal + "' AND codigo_postal = "
                        + codigoPostal + " AND localidad = '" + localidad + "' AND provincia = '" + provincia + "';";
            }
        } else {
            selectString = "SELECT * FROM direcciones WHERE tipo_via = '" + tipoVia + "' AND nombre_via = '" + nombreVia
                    + "' AND numero = '" + numero + " AND portal = '" + portal + "' AND codigo_postal = "
                    + codigoPostal + " AND localidad = '" + localidad + "' AND provincia = '" + provincia + "';";
        }
        return selectString;
    }
    @Override
    public String getUpdateString() {
        String updateString;
        if (piso != 0) {
            if (portal != '\u0000'){
                updateString = "UPDATE direcciones SET tipo_via = '" + tipoVia + "', nombre_via = '" + nombreVia
                        + "', numero = " + numero + ", piso = " + piso + ", portal = '" + portal + "', codigo_postal = "
                        + codigoPostal + ", localidad = '" + localidad + "', provincia = '" + provincia + "';";
            } else {
                updateString = "UPDATE direcciones SET tipo_via = '" + tipoVia + "', nombre_via = '" + nombreVia
                        + "', numero = '" + numero + "', piso = " + piso + ", portal = '" + portal + "', codigo_postal = "
                        + codigoPostal + ", localidad = '" + localidad + "', provincia = '" + provincia + "';";
            }
        } else {
            updateString = "UPDATE direcciones SET tipo_via = '" + tipoVia + "', nombre_via = '" + nombreVia
                    + "', numero = '" + numero + ", portal = '" + portal + "', codigo_postal = " + codigoPostal
                    + ", localidad = '" + localidad + "', provincia = '" + provincia + "';";
        }
        return updateString;
    }
    public String getDeleteString() {
        return "DELETE FROM direcciones WHERE idDireccion = " + idDireccion;
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
