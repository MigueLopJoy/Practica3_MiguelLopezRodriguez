package User;

import Gestion.Catalogo;

public class Menus {
    public static void mainMenu() {
        int option;
        boolean salir = false;

        do {
            option = askForData.pedirInt("Escoja el modulo que desea gestionar:"
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
            option = askForData.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo;" + "\n 2 - Registrar ejemplar;"
                    + "\n 3 - Modificar registros" + "\n 4 - Eliminar registros" + "\n 5 - Salir;");
            switch (option) {
                case 1: {
                    System.out.println("Sin implementar");
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
}
