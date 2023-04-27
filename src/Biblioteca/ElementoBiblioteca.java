package Biblioteca;

/**
 * Interfaz que define los metodos que deberan implementar todos los elementos que conforman la estructura de clases
 * de la biblioteca
 */
public interface ElementoBiblioteca {


    /**
     * Retorna la sentencia sql para inserta un elemento
     *
     * @return sentencia sql para insertar un elemento
     */
    public String getInsertString();

    /**
     * Retorna la sentencia sql para seleccionar un elemento
     *
     * @return sentencia sql para seleccionar un elemento
     */
    public String getSelectString();

    /**
     * Retorna la sentencia sql para actualizar los datos de un elemento
     *
     * @return sentencia sql para actualizar los datos de un auelemento
     */
    public String getUpdateString();

    /**
     * Retorna la sentencia sql para eliminar un elemento
     *
     * @return senencia sql para eliminar un elemento
     */
    public String getDeleteString();

    /**
     * Comprueba si el elemento actual esta registrado en la bdd
     *
     * @return booleano que indica si el elemento esta o no registrado en la bdd
     */
    public boolean isRegistrado();

    /**
     * Obtiene el id asignado en la bdd al elemento actual
     *
     * @return id asignado en la bdd al elemento actual
     */
    public int getIdFromDB();

    /**
     * Crea y retorna una cadena de texto con la informacion del elemnto actual
     *
     * @return una cadena de texto con la informacion del elemento actual
     */
    public String toString();
}
