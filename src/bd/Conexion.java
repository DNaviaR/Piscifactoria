package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import commons.Simulador;

/**
 * Gestiona la conexión a la base de datos
 */
public class Conexion {
    /**
     * El usuario gestor de la base de datos
     */
    private final static String USERNAME = "piscifactor";
    /**
     * La contraseña del usuario gestor de la base de datos
     */
    private final static String PASSWORD = "abc123.";
    /**
     * El servidor en el que se aloja la base de datos
     */
    private final static String SERVER = "127.0.0.1";
    /**
     * El puerto de escucha
     */
    private final static String PORT = "3306";
    /**
     * La base de datos
     */
    private final static String DATABASE = "piscifactoria";
    /**
     * Las propiedades de la conexión
     */
    static Properties connectionProperties = new Properties();
    /**
     * La instancia de conexión en el singleton
     */
    static Connection connection = null;

    /**
     * Constructor de clase privado
     */
    private Conexion() {

    }

    /**
     * Permite abrir la conexion en caso de que no haya ninguna abierta.
     * Si ya está abierta, la devuelve
     * @return la conexión
     */
    public static Connection abrirConexion() {
        if (connection == null) {
            connectionProperties.put("user", USERNAME);
            connectionProperties.put("password", PASSWORD);
            try {
                connection = DriverManager
                        .getConnection("jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE
                                + " ?rewriteBatchedStatements=true", connectionProperties);
            } catch (SQLException e) {
                Simulador.escribirError("Error al crear la conexion");
            }
        }
        return connection;
    }

    /**
     * Cierra la conexión
     */
    public static void cerrarConexion() {
        try {
            if (connection!=null) {
                connection.close();
            }
        } catch (Exception e) {
            Simulador.escribirError("Error al cerrar la conexion");
        }
    }
}