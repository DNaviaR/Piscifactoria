package commons;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Clase Singleton que permite registrar las acciones del usuario
 */
public class Registros {
    /**
     * Instancia de la clase Registros
     */
    private static Registros registros;
    /**
     * Stream que permite la transcripción de las acciones del usuario
     */
    private BufferedWriter transcripcion;
    /**
     * Stream que crea un log de las acciones del usuario
     */
    private BufferedWriter log;

    /**
     * Constructor de clase que impide la creación de instancias de la clase
     */
    private Registros() {
    }

    /**
     * Método que permite crear una sola instancia de la clase
     * 
     * @return la instancia de la clase
     */
    public static Registros getInstance() {
        if (registros == null) {
            registros = new Registros();
        }
        return registros;
    }

    /**
     * Metodo que inicia los streams de datos en la ruta especificada
     * 
     * @param nombrePartida     el nombre de la partida
     * @param rutaTranscripcion la ruta del archivo de transcripción
     * @param rutaLog           la ruta del archivo del log
     */
    public void init(String nombrePartida, String rutaTranscripcion, String rutaLog) {
        try {
            transcripcion = new BufferedWriter(new FileWriter(rutaTranscripcion + nombrePartida + ".tr"));
            log = new BufferedWriter(new FileWriter(rutaLog + nombrePartida + ".log"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que registra la accion del usuario en el archivo de transcripciones
     * 
     * @param accionUsuario la accion del usuario
     */
    public void escribirTranscripción(String accionUsuario) {
        try {
            transcripcion.write(accionUsuario);
            transcripcion.newLine();
            transcripcion.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo que registra la accion del usuario en el archivo de logs
     * 
     * @param accionUsuario la accion del usuario
     */
    public void escribirLog(String accionUsuario) {
        try {
            log.write(accionUsuario);
            log.newLine();
            log.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Metodo que cierra los streams de datos iniciados anteriormente
     */
    public void cerrarRegistros() {
        try {
            if (transcripcion == null) {
                transcripcion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (log == null) {
                log.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
