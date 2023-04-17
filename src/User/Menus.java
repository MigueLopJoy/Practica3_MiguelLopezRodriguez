package User;

import DBManagement.DBHandler;
import DBManagement.MySQLConnection;
import Gestion.Modules;

public class Menus {
    public static void mainMenu() {
        int option;
        boolean salir = false;

        do {
            option = askForData.PedirInt("Escoja el modulo que desea gestionar:"
                    + " \n 1 - Catalogo;" + "\n 2 - Lectores;"
                    + "3 - Circulacion;" + "\n 4 - Salir;");
            switch (option) {
                case 1: {
                    catalogMenu();
                }
                case 2: {
                    System.out.println("Sin implementar");
                }
                case 3: {
                    System.out.println("Sin implementar");
                }
                case 4: {
                    salir = true;
                }
            }
        } while (!salir);
    }
    public static void catalogMenu() {
        int option;
        boolean salir = false;

        do {
            option = askForData.PedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo;" + "2 - Registrar ejemplar;" + "\n 4 - Salir;");
            switch (option) {
                case 1: {
                    System.out.println("Sin implementar");
                }
                case 2: {
                    Modules.catalogExemplar();
                }
                case 3: {
                    salir = true;
                }
            }
        } while (!salir);
    }
}
