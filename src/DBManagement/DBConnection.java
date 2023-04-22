package DBManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private String className;
    private String url;
    private Connection connection;
    public DBConnection(String user, String password) {
        this.className = "com.mysql.cj.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3307/biblioteca";
        this.connection = null;

        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
