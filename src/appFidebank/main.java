
package appFidebank;

import java.sql.*;

public class main {

    public static void main(String[] args) {
        Connection conn = Conexion.getConnection();
        
        if (conn != null) {
            System.out.println("Conexión exitosa a la base de datos.");
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }

        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Conexión cerrada correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
