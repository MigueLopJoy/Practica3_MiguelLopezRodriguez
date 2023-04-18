package Gestion;
import DBManagement.MySQLConnection;
import User.Menus;
import User.askForData;

import java.sql.Connection;

public class Program {
    public static void main(String[] args){
        MySQLConnection mySQLConnection = new MySQLConnection("localhost", "3307", "biblioteca", "root", "1234");
        Connection connection = mySQLConnection.getConnection();
        Menus.mainMenu(connection);
    }
}
