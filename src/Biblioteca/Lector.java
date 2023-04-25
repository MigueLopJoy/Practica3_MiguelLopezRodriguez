package Biblioteca;

import DBManagement.DBHandler;

public class Lector extends Persona implements ElementoBiblioteca {
    private int idLector;
    private String numeroLector;
    private String telefono;
    private String email;

    public Lector( ) {
        super();
    }
    public Lector(String numeroLector) {
        super();
        this.numeroLector = numeroLector;
    }
    public Lector(String nombre, String apellidos, String telefono) {
        super(nombre, apellidos);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.idLector = getIdFromDB();
    }

    public Lector(String nombre, String apellidos, String telefono, String email) {
        super(nombre, apellidos);
        this.numeroLector = generarNumeroLector();
        this.telefono = telefono;
        this.email = email;
        this.idLector = getIdFromDB();
    }

    public Lector(int idLector, String nombre, String apellidos, String numeroLector, String telefono) {
        super(nombre, apellidos);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
    }

    public Lector(int idLector, String nombre, String apellidos, String numeroLector, String telefono, String email) {
        super(nombre, apellidos);
        this.idLector = idLector;
        this.numeroLector = numeroLector;
        this.telefono = telefono;
        this.email = email;
    }

    public int getIdLector() {
        return idLector;
    }

    public void setIdLector(int idLector) {
        this.idLector = idLector;
    }

    public String getNumeroLector() {
        return numeroLector;
    }

    public void setNumeroLector(String numeroLector) {
        this.numeroLector = numeroLector;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getInsertString() {
        String insertString;
        System.out.println(email == null);
        System.out.println(email);
        if (email != null) {
            insertString = "INSERT INTO lectores (nombre, apellidos, numero_lector, telefono, email) " +
                    "VALUES ('" + getNombre() + "', '" + getApellidos() + "', '" + numeroLector
                    + "', '" + telefono + "', '" + email + "');";
        } else {
            insertString = "INSERT INTO lectores (nombre, apellidos, numero_lector, telefono) " +
                    "VALUES ('" + getNombre() + "', '" + getApellidos() + "', '" + numeroLector
                    + "', '" + telefono + "');";
        }
        return insertString;
    }

    @Override
    public String getSelectString() {
        String selectString;

        if (email != null) {
            selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellidos = '" + getApellidos()
                    + "' AND numero_lector = '" + numeroLector + "' AND telefono = '" + telefono + "' AND email = '" + email + "';";
        } else {
            selectString = "SELECT * FROM lectores WHERE nombre = '" + getNombre() + "' AND apellidos = '" + getApellidos()
                    + "' AND numero_lector = '" + numeroLector + "' AND telefono = '" + telefono + "';";
        }
        return selectString;
    }

    @Override
    public String getUpdateString() {
        String updateString;
        if (email != null) {
            updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellidos = '" + getApellidos()
                    + "', numero_lector = '" + numeroLector + "', telefono = '" + telefono + "', email = '" + email
                    + "' WHERE idLector = " + idLector;
        } else {
            updateString = "UPDATE lectores SET nombre = '" + getNombre() + "', apellidos = '" + getApellidos()
                    + "', numero_lector = '" + numeroLector + "', telefono = '" + telefono + "' WHERE idLector = " + idLector;
        }
        return updateString;
    }

    public String getDeleteString() {
        return "DELETE FROM lectores WHERE numero_lector = '" + numeroLector + "';";
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
        return super.toString() + " - Nº lector: " + this.numeroLector;
    }

    private String generarNumeroLector() {
        String codigoEjemplar = "";
        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        do {
            for (int i = 0; i < 8; i++) {
                codigoEjemplar += (int) (Math.random() * 10);
            }
            codigoEjemplar += letras[(int) (Math.random() * 26)];
        } while (isRepetido(codigoEjemplar));

        return codigoEjemplar;
    }

    private static boolean isRepetido(String numLector) {
        boolean repetido = false;

        if(DBHandler.hayRegistros("SELECT * FROM lectores WHERE numero_lector = '" + numLector + "';")) {
            repetido = true;
        }
        return repetido;
    }
}
