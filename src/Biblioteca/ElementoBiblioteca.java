package Biblioteca;

public interface ElementoBiblioteca {
    public String getInsertString();
    public String getSelectString();
    public String getUpdateString();
    public String getDeleteString();
    public boolean isRegistrado();
    public int getIdFromDB();
    public int setIdFromDB();
    public String toString();
}
