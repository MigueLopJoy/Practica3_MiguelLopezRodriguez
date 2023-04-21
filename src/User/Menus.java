package User;

import Gestion.Catalogo;

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
                    catalogMenu();
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
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    public static void catalogMenu() {
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
                    System.out.println("Sin implementar");
                    break;
                }
                case 4: {
                    System.out.println("Sin implementar");
                }
                case 5: {
                    salir = true;
                    break;
                }
            }
        } while (!salir);
    }
    public static void menuBusquedaCatalogo() {
        int option;
        boolean salir = false;

        do {
            option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo completo;" + "\n 2 - Formular busqueda;" + "\n 3 - Salir;");
            switch (option) {
                case 1: {
                    Catalogo.buscar(1);
                    break;
                }
                case 2: {
                    do {
                        option = pedirDatos.pedirInt("Escoja la accion que desea realizar:"
                                + " \n 1 - Buscar por titulo;" + "\n 2 - Buscar por autor;" + "\n 3 - Salir;");
                        switch (option) {
                            case 1: {
                                Catalogo.buscar(2);
                                break;
                            }
                            case 2: {
                                Catalogo.buscar(3);
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