package bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import commons.ApoyoMenu;
import commons.Simulador;
import pez.Pez;
import piscifactoria.Piscifactoria;
import piscifactoria.Tanque;

/**
 * Clase que inserta valores en la tabla pedidos de la base de datos
 */
public class DAOPedidos {
    /**
     * La conexion a la base de datos
     */
    private Connection conexion;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que inserten
     * datos específicos de un pedido en la base de datos.
     */
    private PreparedStatement insertarPedidos = null;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que recuperan
     * datos específicos de un pedido desde la base de datos.
     */
    private PreparedStatement datosPedidoStatement = null;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que listan
     * los pedidos completados de la base de datos
     */
    private PreparedStatement listarPedidoCompletadoStatement = null;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que listan
     * los pedidos no completados de la base de datos
     */
    private PreparedStatement listarPedidoStatement = null;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que eliminan
     * los pedidos de la base de datos
     */
    private PreparedStatement eliminarPedidosStatement = null;
    /**
     * Esta variable se utiliza para preparar y ejecutar consultas SQL que envian
     * peces a los pedidos de la base de datos
     */
    private PreparedStatement enviarPedidoStatement = null;

    /**
     * Constructor de la clase
     */
    public DAOPedidos() {
        conexion = Conexion.abrirConexion();
        try {
            insertarPedidos = conexion.prepareStatement(
                    "INSERT INTO `pedido`(`IdCliente`, `IdPez`, `CantidadPedida`, `CantidadEnviada`) VALUES (?,?,?,?)");
            datosPedidoStatement = conexion.prepareStatement(
                    "SELECT pz.NombreComun AS pez, p.CantidadPedida, p.CantidadEnviada FROM pedido p JOIN pez pz ON p.IdPez = pz.IdPez WHERE p.IdPedido = ?");
            listarPedidoCompletadoStatement = conexion.prepareStatement(
                    "SELECT p.IdPedido, c.NombreCliente AS cliente, pz.NombreComun AS pez, p.CantidadPedida, p.CantidadEnviada FROM pedido p JOIN cliente c ON p.IdCliente = c.IdCliente JOIN pez pz ON p.IdPez = pz.IdPez WHERE p.CantidadPedida <= p.CantidadEnviada ORDER BY p.IdPedido");
            listarPedidoStatement = conexion.prepareStatement(
                    "SELECT p.IdPedido, c.NombreCliente AS cliente, pz.NombreComun AS pez, p.CantidadPedida, p.CantidadEnviada FROM pedido p JOIN cliente c ON p.IdCliente = c.IdCliente JOIN pez pz ON p.IdPez = pz.IdPez WHERE p.CantidadPedida > p.CantidadEnviada ORDER BY p.IdPedido");
            eliminarPedidosStatement = conexion.prepareStatement("DELETE FROM Pedido");
            enviarPedidoStatement = conexion
                    .prepareStatement("UPDATE pedido SET CantidadEnviada = ? WHERE IdPedido = ?");
        } catch (SQLException e) {
            Simulador.escribirError("Error generando los pedidos");
        }
    }

    /**
     * Inserta pedidos con su IdCliente, IdPez, cantidad pedida y cantidad enviada
     * en la tabla pedidos
     */
    public void insertPedidos() {
        Random rd = new Random();
        ArrayList<Integer> idsPeces = this.idsPeces();
        ArrayList<Integer> idsClientes = this.idsClientes();
        try {
            insertarPedidos.setInt(1, idsClientes.get(rd.nextInt(idsClientes.size())));
            insertarPedidos.setInt(2, idsPeces.get(rd.nextInt(idsPeces.size())));
            insertarPedidos.setInt(3, rd.nextInt(40) + 10);
            insertarPedidos.setInt(4, 0);
            insertarPedidos.addBatch();
            insertarPedidos.executeBatch();
            Simulador.registros.escribirTranscripcion("Generado nuevo pedido");
            Simulador.registros.escribirLog("Generado nuevo pedido");
        } catch (SQLException e) {
            Simulador.escribirError("Error insertando pedidos");
        }
    }

    /**
     * Cierra los Prepared Statements
     */
    public void cerrarPedidos() {
        if (insertarPedidos != null) {
            try {
                insertarPedidos.close();
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando insertarPedidos");
            }
        }
        try {
            if (datosPedidoStatement != null) {
                datosPedidoStatement.close();
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error cerrando el statement de listarPedidosCompletados");
        }
        try {
            if (listarPedidoCompletadoStatement != null) {
                listarPedidoCompletadoStatement.close();
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error cerrando el statement de listarPedidosCompletados");
        }
        try {
            if (listarPedidoStatement != null) {
                listarPedidoStatement.close();
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error cerrando el statement de listarPedidos");
        }
        try {
            if (eliminarPedidosStatement != null) {
                eliminarPedidosStatement.close();
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error cerrando el statement de eliminarPedidos");
        }
        try {
            if (enviarPedidoStatement != null) {
                enviarPedidoStatement.close();
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error cerrando el statement de enviarPedido");
        }
    }

    /**
     * Obtiene los ids de los peces de la base de datos
     * 
     * @return los ids de los peces
     */
    private ArrayList<Integer> idsPeces() {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement ps = null;
        String query = ("SELECT idPez FROM pez;");
        ResultSet rs = null;
        try {
            ps = conexion.createStatement();
            rs = ps.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                ids.add(id);
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error recuperando los valores de idsPeces");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el statement de idsPeces");
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el resultSet de idsPeces");
            }
        }
        return ids;
    }

    /**
     * Obtiene los ids de los clientes de la base de datos
     * 
     * @return los ids de los clientes
     */
    private ArrayList<Integer> idsClientes() {
        ArrayList<Integer> ids = new ArrayList<>();
        Statement ps = null;
        String query = ("SELECT idCliente FROM cliente;");
        ResultSet rs = null;
        try {
            ps = conexion.createStatement();
            rs = ps.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);
                ids.add(id);
            }
        } catch (SQLException e) {
            Simulador.escribirError("Error recuperando los valores de idsClientes");
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el statement de idsClientes");
            }
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el resultSet de idsClientes");
            }
        }
        return ids;
    }

    /**
     * Muestra todos los pedidos que han sido completados
     */
    public void listarPedidosCompletados() {
        ResultSet rs = null;
        try {
            rs = listarPedidoCompletadoStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay pedidos completados.");
            } else {
                while (rs.next()) {
                    String pedido = "[" + rs.getString("p.IdPedido") + "] " + rs.getString("cliente") + ": "
                            + rs.getString("pez") + " " + (rs.getInt("p.CantidadEnviada")) + "/"
                            + rs.getInt("p.CantidadPedida") + "("
                            + (rs.getInt("p.CantidadEnviada") * 100 / rs.getInt("p.CantidadPedida")) + "%)";
                    System.out.println(pedido);
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error de listarPedidosCompletados");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el rs de listarPedidosCompletados");
            }
        }
    }

    /**
     * Muestra todos los pedidos que no han sido completados
     */
    public void listarPedidos() {
        ResultSet rs = null;
        try {
            rs = listarPedidoStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay pedidos sin completar.");
            } else {
                while (rs.next()) {
                    String pedido = "[" + rs.getString("p.IdPedido") + "] " + rs.getString("cliente") + ": "
                            + rs.getString("pez") + " " + (rs.getInt("p.CantidadEnviada")) + "/"
                            + rs.getInt("p.CantidadPedida") + "("
                            + (rs.getInt("p.CantidadEnviada") * 100 / rs.getInt("p.CantidadPedida")) + "%)";
                    System.out.println(pedido);
                }
            }
        } catch (Exception e) {
            Simulador.escribirError("Error de listarPedidos");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el rs de listarPedidos");
            }
        }
    }

    /**
     * Añade una cantidad al pedido
     * 
     * @param idPedido        El pedido
     * @param cantidadEnviada La cantidad a enviar
     */
    public void enviarPedido(int idPedido, int cantidadEnviada) {
        try {
            enviarPedidoStatement.setInt(1, cantidadEnviada);
            enviarPedidoStatement.setInt(2, idPedido);
            enviarPedidoStatement.executeUpdate();
            System.out.println("Peces enviados correctamente");
        } catch (Exception e) {
            Simulador.escribirError("Error de enviarPedido");
        }
    }

    /**
     * Elimina todos los pedidos de la base de datos
     */
    public void eliminarPedidos() {
        try {
            eliminarPedidosStatement.executeUpdate();
            System.out.println("Pedidos eliminados satisfactoriamente");
        } catch (Exception e) {
            Simulador.escribirError("Error de eliminarPedidos");
        }
    }

    /**
     * Envia peces para completar un pedido
     */
    public void datosPedido(int idPedido) {
        ResultSet rs = null;
        try {
            String nombrePez = "";
            int cantidadNecesaria = 0;
            int cantidadEnviada = 0;
            datosPedidoStatement.setInt(1, idPedido);
            rs = datosPedidoStatement.executeQuery();
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay pedidos con id " + idPedido);
            } else {
                while (rs.next()) {
                    nombrePez = rs.getString("pez");
                    cantidadEnviada = rs.getInt("p.CantidadEnviada");
                    cantidadNecesaria = rs.getInt("p.CantidadPedida") - rs.getInt("p.CantidadEnviada");
                }
            }
            ArrayList<Tanque> tanques = comprobarTanques(nombrePez);
            int cantidadEnviar = 0;
            for (Tanque<? extends Pez> tanque : tanques) {
                ArrayList<Pez> peces = tanque.getPeces();
                for (Pez pez : peces) {
                    if (pez.isAdulto() && pez.isvivo()) {
                        Simulador.estadisticas.registrarVenta(pez.getNombre(), pez.getPecesDatos().getMonedas());
                        Simulador.monedas.ingresar(pez.getPecesDatos().getMonedas());
                        peces.set(peces.indexOf(pez), null);
                        cantidadEnviar++;
                        if (cantidadEnviar >= cantidadNecesaria) {
                            Simulador.registros.escribirTranscripcion(
                                    "Pedido de " + nombrePez + " con referencia " + idPedido + " enviado.");
                            Simulador.registros.escribirLog(
                                    "Pedido de " + nombrePez + " con referencia " + idPedido + " enviado.");
                            ApoyoMenu.generarRecompensa();
                            break;
                        }
                    }
                }
                Simulador.registros
                        .escribirTranscripcion("Enviados " + cantidadEnviar + " peces al pedido de " + nombrePez
                                + " con referencia " + idPedido + " enviado.");
                Simulador.registros.escribirLog("Enviados " + cantidadEnviar + " peces al pedido de " + nombrePez
                        + " con referencia " + idPedido + " enviado.");
                tanque.eliminarNulos();
            }
            this.enviarPedido(idPedido, cantidadEnviar + cantidadEnviada);
        } catch (Exception e) {
            Simulador.escribirError("Error de listarPedidosCompletados");
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                Simulador.escribirError("Error cerrando el rs de listarPedidosCompletados");
            }
        }
    }

    /**
     * Comprueba las piscifactorias del tipo de pez indicado
     * 
     * @param nombrePez El nombre del pez indicado
     * @return Las piscifactorias del tipo de pez indicado
     */
    private ArrayList<Tanque> comprobarTanques(String nombrePez) {
        ArrayList<Tanque> tanques = new ArrayList<>();
        for (Piscifactoria piscifactoria : Simulador.piscifactorias) {
            for (Tanque tanque : piscifactoria.getTanques()) {
                if (((Pez) tanque.getPeces().get(0)).getPecesDatos().getNombre().equals(nombrePez)) {
                    tanques.add(tanque);
                }
            }
        }
        return tanques;
    }
}
