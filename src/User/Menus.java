package User;

import Gestion.Catalogo;
import Gestion.Lectores;

public class Menus {
    public static void mainMenu() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja el modulo que desea gestionar:"
                    + " \n 1 - Catalogo;" + "\n 2 - Lectores;"
                    + "\n 3 - Circulacion;" + "\n 4 - Salir;");
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
                    System.out.println("Sin implementar");
                    break;
                }
                case 4: {
                    salir = true;
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
                    + "\n 3 - Modificar registros" + "\n 4 - Eliminar registros" + "\n 5 - Salir;");
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
                    menuEliminar();
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
                    + " \n 1 - Consultar catalogo completo;" + "\n 2 - Formular busqueda;" + "\n 3 - Salir;");
            switch (option) {
                case 1: {
                    Catalogo.consultarCatalogo(1);
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Buscar por titulo;" + "\n 2 - Buscar por autor;" + "\n 3 - Salir;");
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
                    + " \n 1 - Modificar libro;" + "\n 2 - Modificar autor;" + "\n 3 - Salir;");
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

    private static void menuEliminar() {
        int option;
        boolean salir = false;
        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"+ " \n 1 - Eliminar ejemplar;"
                    + "\n 2 - Eliminar libro;" + "\n 3 - Eliminar autor;"  + "\n 4 - Salir;");
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
                    + "\n 3 - Modificar datos de lector" + "\n 4 - Dar de baja lector" + "\n 5 - Salir;");
            switch (option) {
                case 1: {
                    menuBusquedaLectores();
                    break;
                }
                case 2: {
                    System.out.println("Sin implementar");
                    break;
                }
                case 3: {
                    System.out.println("Sin implementar");
                    break;
                }
                case 4: {
                    Lectores.eliminarLector();
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
                    + " \n 1 - Todos los lectores;" + "\n 2 - Formular busqueda;" + "\n 3 - Salir;");
            switch (option) {
                case 1: {
                    Lectores.consultarLectores(1);
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Buscar por datos personales;" + "\n 2 - Introducir numero de lector;" + "\n 3 - Salir;");
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
}