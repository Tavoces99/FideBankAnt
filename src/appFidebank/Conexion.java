
package appFidebank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    static Connection conn = null;

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/fidebank_bd";
        String user = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
            conn = null;
        }
        return conn;
    }
}