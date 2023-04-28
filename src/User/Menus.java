package User;

import Biblioteca.Lector;
import Biblioteca.Prestamo;
import Gestion.Catalogo;
import Gestion.Lectores;
import Gestion.Prestamos;
import Gestion.Utils;

public class Menus {
    public static void mainMenu() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja el modulo que desea gestionar:"
                    + " \n 1 - Catalogo;" + "\n 2 - Lectores;"
                    + "\n 3 - Circulacion;" + "\n 4 - Salir;", 1, 4);
            switch (option) {
                case 1: {
                    menuCatalogo();
                    break;
                }
                case 2: {
                    menuLectores();
                    break;
                }
                case 3: {
                    menuCirculacion();
                    break;
                }
                case 4: {
                    if (pedirDatos.confirmacion("Realmente deasea salir del programa? (s/n)")) {
                        salir = true;
                    }
                    break;
                }
            }
        } while (!salir);
    }
    public static void menuCatalogo() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo;" + "\n 2 - Registrar ejemplar;"
                    + "\n 3 - Modificar registros" + "\n 4 - Eliminar registros" + "\n 5 - Salir;", 1, 5);
            switch (option) {
                case 1: {
                    menuBusquedaCatalogo();
                    break;
                }
                case 2: {
                    Catalogo.registrarEjemplar();
                    break;
                }
                case 3: {
                    menuActualizarCatalogo();
                    break;
                }
                case 4: {
                    menuEliminarCatalogo();
                    break;
                }
                case 5: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    private static void menuBusquedaCatalogo() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo completo;" + "\n 2 - Formular busqueda;" + "\n 3 - Salir;", 1, 3);
            switch (option) {
                case 1: {
                    Catalogo.consultarCatalogo(1);
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Buscar por titulo;" + "\n 2 - Buscar por autor;" + "\n 3 - Salir;", 1, 3);
                        switch (option) {
                            case 1: {
                                Catalogo.consultarCatalogo(2);
                                break;
                            }
                            case 2: {
                                Catalogo.consultarCatalogo(3);
                                break;
                            }
                            case 3: {
                                salir = true;
                                break;
                            }
                        }
                    } while (!salir);
                    salir = false;
                    break;
                }
                case 3: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    private static void menuActualizarCatalogo() {
        int option;
        boolean salir = false;
        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Modificar libro;" + "\n 2 - Modificar autor;" + "\n 3 - Salir;", 1, 3);
            switch (option) {
                case 1: {
                    Catalogo.actualizar(1);
                    break;
                }
                case 2: {
                    Catalogo.actualizar(2);
                    break;
                }
                case 3: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    private static void menuEliminarCatalogo() {
        int option;
        boolean salir = false;
        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"+ " \n 1 - Eliminar ejemplar;"
                    + "\n 2 - Eliminar libro;" + "\n 3 - Eliminar autor;"  + "\n 4 - Salir;", 1, 4);
            switch (option) {
                case 1: {
                    Catalogo.eliminar(1);
                    break;
                }
                case 2: {
                    Catalogo.eliminar(2);
                    break;
                }
                case 3: {
                    Catalogo.eliminar(3);
                    break;
                }
                case 4: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    private static void menuLectores() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Buscar lector;" + "\n 2 - Dar de alta lector;"
                    + "\n 3 - Modificar datos de lector" + "\n 4 - Eliminar lector" + "\n 5 - Salir;", 1, 5);
            switch (option) {
                case 1: {
                    menuBusquedaLectores();
                    break;
                }
                case 2: {
                    Lectores.registrarLector();
                    break;
                }
                case 3: {
                    Lectores.actualizarLector();
                    break;
                }
                case 4: {
                    Lectores.eliminar();
                    break;
                }
                case 5: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }

    private static void menuBusquedaLectores() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Todos los lectores;" + "\n 2 - Formular busqueda;" + "\n 3 - Salir;", 1, 3);
            switch (option) {
                case 1: {
                    Lectores.consultarLectores(1);
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Buscar por nombre;" + "\n 2 - Buscar por nº de lector;" + "\n 3 - Salir;", 1, 3);
                        switch (option) {
                            case 1: {
                                Lectores.consultarLectores(2);
                                break;
                            }
                            case 2: {
                                Lectores.consultarLectores(3);
                                break;
                            }
                            case 3: {
                                salir = true;
                                break;
                            }
                        }
                    } while (!salir);
                    salir = false;
                    break;
                }
                case 3: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }

    public static void menuCirculacion() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Prestamos y devoluciones;" + "\n 2 - Consultar prestamos;" + "\n 3 - Salir;", 1, 3);
            switch (option) {
                case 1: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Prestar ejemplar;" + "\n 2 - Devolver ejemplar;" + "\n 3 - Salir;",1 , 3);
                        switch (option) {
                            case 1: {
                                Prestamos.prestar();
                                break;
                            }
                            case 2: {
                                Prestamos.devolver();
                                break;
                            }
                            case 3: {
                                salir = true;
                                break;
                            }
                        }
                    } while(!salir);
                    salir = false;
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Consultar todos los prestamos;" + "\n 2 - Buscar por codigo de ejemplar;"
                                + "\n 3 - Buscar por numero de lector;" + "\n 4 - Salir;", 1, 4);
                        switch (option) {
                            case 1: {
                                Prestamos.consultarPrestamos(1);
                                break;
                            }
                            case 2: {
                                Prestamos.consultarPrestamos(2);
                                break;
                            }
                            case 3: {
                                Prestamos.consultarPrestamos(3);
                                break;
                            }
                            case 4: {
                                salir = true;
                                break;
                            }
                        }
                    } while (!salir);
                    salir = false;
                    break;
                }
                case 3: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
}