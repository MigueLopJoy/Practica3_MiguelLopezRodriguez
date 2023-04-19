package DBManagement;

import java.sql.Connection;

public class MySQLConnection extends DBConnection {
    public MySQLConnection(String user, String password) {
        super("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3307/biblioteca", user , password);
    }
    public Connection getConnection() {
        return super.getConnection();
    }
}
