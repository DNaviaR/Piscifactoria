package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import commons.Simulador;
import propiedades.AlmacenPropiedades;

/**
 * Clase que crea las tablas e inserta valores en la base de datos
 */
public class GeneradorBD {
    /**
     * La conexion a la base de datos
     */
    private Connection conexion;
    /**
     * Inserta clientes en la tabla clientes
     */
    private PreparedStatement insertarClientes = null;
    /**
     * Inserta peces en la tabla peces
     */
    private PreparedStatement insertarPeces = null;
    /**
     * Lista de clientes
     */
    private ArrayList<Clientes> clientes = new ArrayList<>();

    /**
     * Constructor de la clase
     */
    public GeneradorBD() {
        conexion = Conexion.abrirConexion();
        this.crearTablas();
        clientes.add(new Clientes("Shichibukai", "34539964J", "960083282"));
        clientes.add(new Clientes("Don Flamingo", "76638050A", "518835760"));
        clientes.add(new Clientes("Yonkous", "09723067R", "925017750"));
        clientes.add(new Clientes("Gorosei", "63493120X", "745928710"));
        clientes.add(new Clientes("Shanks", "06262855R", "876476506"));
        clientes.add(new Clientes("Shirohige", "92055063R", "738759619"));
        clientes.add(new Clientes("Roronoa", "47326886Q", "736258148"));
        clientes.add(new Clientes("Kurohige", "55002371X", "836960324"));
        clientes.add(new Clientes("Mugiwaras", "49625502N", "639749989"));
        clientes.add(new Clientes("Nika", "99249782K", "969700904"));
        try {
            insertarClientes = conexion
                    .prepareStatement("INSERT INTO `cliente`(`NombreCliente`, `NIF`, `Telefono`) VALUES (?,?,?);");
            insertarPeces = conexion
                    .prepareStatement("INSERT INTO `pez`(`NombreComun`, `NombreCientifico`) VALUES (?,?)");
        } catch (SQLException e) {
            Simulador.escribirError("Error generando la base de datos");
        }
        this.insertarTablas();
    }

    /**
     * Crea las tablas indicadas
     */
    private void crearTablas() {
        tablaCliente();
        tablaPez();
        tablaPedido();
    }

    /**
     * Inserta en las tablas indicadas
     */
    private void insertarTablas() {
        insertClientes();
        insertPeces();
    }

    /**
     * Crea la tabla Cliente en la base de datos
     */
    private void tablaCliente() {
        Statement statement = null;
        try {
            statement = conexion.createStatement();
            String query = ("CREATE TABLE IF NOT EXISTS cliente (IdCliente INT NOT NULL AUTO_INCREMENT, NombreCliente VARCHAR(255) NOT NULL, NIF VARCHAR(14) UNIQUE NOT NULL, Telefono VARCHAR(20) NOT NULL, PRIMARY KEY (IdCliente));");
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            Simulador.escribirError("Error en la creación de la tabla cliente");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error al cerrar la tabla clientes");
            }
        }
    }

    /**
     * Crea la tabla Pedido en la base de datos
     */
    private void tablaPedido() {
        Statement statement = null;
        try {
            statement = conexion.createStatement();
            String query = ("CREATE TABLE IF NOT EXISTS pedido (IdPedido INT NOT NULL AUTO_INCREMENT, IdCliente INT NOT NULL, IdPez INT NOT NULL, CantidadPedida INT NOT NULL, CantidadEnviada INT NOT NULL, FOREIGN KEY (IdCliente) REFERENCES Cliente (IdCliente), PRIMARY KEY (IdPedido), FOREIGN KEY (IdPez) REFERENCES Pez (IdPez));");
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            Simulador.escribirError("Error en la creación de la tabla pedido");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error al cerrar la tabla pedido");
            }
        }
    }

    /**
     * Crea la tabla Pez en la base de datos
     */
    private void tablaPez() {
        Statement statement = null;
        try {
            statement = conexion.createStatement();
            String query = ("CREATE TABLE IF NOT EXISTS pez (IdPez INT NOT NULL AUTO_INCREMENT, NombreComun VARCHAR(255) NOT NULL, NombreCientifico VARCHAR(255) NOT NULL, PRIMARY KEY (IdPez));");
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            Simulador.escribirError("Error en la creación de la tabla pez");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error al cerrar la tabla pez");
            }
        }
    }

    /**
     * Inserta clientes con su nombre, nif y telefono en la tabla clientes
     */
    public void insertClientes() {
        try {
            for (int i = 0; i < clientes.size(); i++) {
                insertarClientes.setString(1, clientes.get(i).getNombre());
                insertarClientes.setString(2, clientes.get(i).getNif());
                insertarClientes.setString(3, clientes.get(i).getTelefono());
                insertarClientes.addBatch();
            }
            insertarClientes.executeBatch();
        } catch (SQLException e) {
            Simulador.escribirError("Error insertando clientes");
        } finally {
            if (insertarClientes != null) {
                try {
                    insertarClientes.close();
                } catch (SQLException e) {
                    Simulador.escribirError("Error cerrando insertarClientes");
                }
            }
        }
    }

    /**
     * Inserta peces con su nombre y nombre cientifico en la tabla peces
     */
    public void insertPeces() {
        try {
            for (int i = 0; i < Simulador.nombresPeces.length; i++) {
                insertarPeces.setString(1, Simulador.nombresPeces[i]);
                insertarPeces.setString(2, AlmacenPropiedades.getPropByName(Simulador.nombresPeces[i]).getCientifico());
                insertarPeces.addBatch();
            }
            insertarPeces.executeBatch();
        } catch (SQLException e) {
            Simulador.escribirError("Error insertando peces");
        } finally {
            if (insertarPeces != null) {
                try {
                    insertarPeces.close();
                } catch (SQLException e) {
                    Simulador.escribirError("Error cerrando insertarPeces");
                }
            }
        }
    }
}
