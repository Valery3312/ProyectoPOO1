package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://bntq1uqomannbewz0gwy-mysql.services.clever-cloud.com:3306/bntq1uqomannbewz0gwy";
    private static final String USUARIO = "usm0uvbiuwx7dg9r";
    private static final String CONTRASENA = "9XDTZMEBcKhAmOAnIo9B";

    public static Connection obtenerConexion() {
        try {
            return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
            return null;
        }
    }
}
