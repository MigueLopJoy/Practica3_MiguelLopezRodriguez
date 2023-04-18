package User;

import DBManagement.MySQLConnection;
import Gestion.Catalog;

import java.sql.Connection;

public class Menus {
    public static void mainMenu(Connection connection) {
        int option;
        boolean salir = false;

        do {
            option = askForData.pedirInt("Escoja el modulo que desea gestionar:"
                    + " \n 1 - Catalogo;" + "\n 2 - Lectores;"
                    + "\n 3 - Circulacion;" + "\n 4 - Salir;");
            switch (option) {
                case 1: {
                    catalogMenu(connection);
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
    public static void catalogMenu(Connection connection) {
        Catalog catalog = new Catalog(connection);
        int option;
        boolean salir = false;

        do {
            option = askForData.pedirInt("Escoja la accion que desea realizar:"
                    + " \n 1 - Consultar catalogo;" + "\n 2 - Registrar ejemplar;" + "\n 4 - Salir;");
            switch (option) {
                case 1: {
                    System.out.println("Sin implementar");
                }
                case 2: {
                    catalog.registrarEjemplar();
                }
                case 3: {
                    salir = true;
                }
            }
        } while (!salir);
    }
}
