package DBManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    private Connection connection = null;
    public DBConnection(String className, String url, String user, String password) {
        try {
            Class.forName(className);
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected Connection getConnection() {
        return this.connection;
    }
}
