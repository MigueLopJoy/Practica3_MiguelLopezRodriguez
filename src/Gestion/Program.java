package Gestion;
import DBManagement.MySQLConnection;
import User.Menus;

import java.sql.Connection;

public class Program {
    private MySQLConnection connection = new MySQLConnection("localhost", "3307", "biblioteca", "root", "1234");
    public static void main(String[] args){
        Menus.mainMenu();
    }
}
