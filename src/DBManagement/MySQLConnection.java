package DBManagement;

import java.sql.Connection;

public class MySQLConnection extends DBConnection {
    public MySQLConnection(String host, String port, String DB, String user, String password) {
        super("com.mysql.cj.jdbc.Driver", "jdbc:mysql://" + host + ":" + port + "/" + DB, user, password);
    }
    public Connection getConnection() {
        return super.getConnection();
    }
}
