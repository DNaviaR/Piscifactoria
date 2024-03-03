package commons;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import almacen.AlmacenCentral;
import estadisticas.Estadisticas;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaRio;
import propiedades.AlmacenPropiedades;
import propiedades.CriaTipo;
import com.google.gson.*;

/**
 * Clase que representa un simulador de una actividad relacionada con
 * piscifactorías.
 * Permite gestionar diversas estadísticas, monedas, y operaciones asociadas a
 * las piscifactorías.
 */
public class Simulador implements Serializable {
    /**
     * Nombres de los peces disponibles en el simulador.
     */
    public static String[] nombresPeces = { AlmacenPropiedades.CARPA.getNombre(),
            AlmacenPropiedades.CARPA_PLATEADA.getNombre(), AlmacenPropiedades.CARPIN_TRES_ESPINAS.getNombre(),
            AlmacenPropiedades.LUCIO_NORTE.getNombre(), AlmacenPropiedades.PEJERREY.getNombre(),
            AlmacenPropiedades.PERCA_EUROPEA.getNombre(), AlmacenPropiedades.SALMON_CHINOOK.getNombre(),
            AlmacenPropiedades.TILAPIA_NILO.getNombre(),
            AlmacenPropiedades.ABADEJO.getNombre(), AlmacenPropiedades.ARENQUE_ATLANTICO.getNombre(),
            AlmacenPropiedades.BESUGO.getNombre(), AlmacenPropiedades.CABALLA.getNombre(),
            AlmacenPropiedades.COBIA.getNombre(), AlmacenPropiedades.CORVINA.getNombre(),
            AlmacenPropiedades.LENGUADO_EUROPEO.getNombre(), AlmacenPropiedades.LUBINA_RAYADA.getNombre(),
            AlmacenPropiedades.ROBALO.getNombre(), AlmacenPropiedades.RODABALLO.getNombre(),
            AlmacenPropiedades.SARGO.getNombre(), AlmacenPropiedades.BAGRE_CANAL.getNombre(),
            AlmacenPropiedades.DORADA.getNombre(), AlmacenPropiedades.LUBINA_EUROPEA.getNombre(),
            AlmacenPropiedades.SALMON_ATLANTICO.getNombre(), AlmacenPropiedades.TRUCHA_ARCOIRIS.getNombre() };
    /**
     * Estadísticas asociadas al simulador, basadas en los nombres de los peces.
     */
    public static Estadisticas estadisticas = new Estadisticas(nombresPeces);
    /**
     * Scanner utilizado para obtener información desde la entrada estándar.
     */
    transient Scanner sc = new Scanner(System.in);
    /**
     * Número de días de simulador
     */
    public static int dias;
    /**
     * Lista de piscifactorías que forman parte del simulador.
     */
    public static ArrayList<Piscifactoria> piscifactorias = new ArrayList<>();
    /**
     * Monedas utilizadas en el simulador.
     */
    public static Monedas monedas = Monedas.getInstance();
    /**
     * Clase utilizada para registrar las acciones del usuario.
     */
    public static Registros registros = Registros.getInstance();
    /**
     * Almacén central utilizado en el simulador.
     */
    public static AlmacenCentral almacenCentral = new AlmacenCentral();
    /**
     * Nombre de la partida
     */
    private String nombrePartida;
    private transient Rewards rewards;

    /**
     * Inicializa los elementos del sistema
     * 
     * @param nombrePartida        El nombre de la partida
     * @param primeraPiscifactoria el nombre de la primera piscifactoria
     */
    public void init(String nombrePartida, String primeraPiscifactoria) {
        this.nombrePartida = nombrePartida;
        dias = 0;
        File transcripciones = new File("transcripciones");
        if (!transcripciones.exists()) {
            transcripciones.mkdir();
        }
        File logs = new File("logs");
        if (!logs.exists()) {
            logs.mkdir();
        }
        File rewardsFile = new File("rewards");
        if (!rewardsFile.exists()) {
            rewardsFile.mkdir();
        }
        registros.iniciar(nombrePartida, "transcripciones/", "logs/");
        piscifactorias.add(new PiscifactoriaRio(primeraPiscifactoria));
        piscifactorias.get(0).getAlmacen().setEspacioOcupado(piscifactorias.get(0).getAlmacen().getEspacioMaximo());
        monedas.setMonedas(100);
        registrosIniciales(nombrePartida, primeraPiscifactoria);
        rewards = new Rewards();
    }

    /**
     * Muestra el menu de opciones del sistema
     */
    void menu() {
        int condition = 0;
        do {
            System.out.println(
                    "1. Estado general\n" +
                            "2. Estado piscifactoria\n" +
                            "3. Estado tanques\n" +
                            "4. Informes\n" +
                            "5. Ictiopedia\n" +
                            "6. Pasar día\n" +
                            "7. Comprar comida\n" +
                            "8. Comprar peces\n" +
                            "9. Vender peces\n" +
                            "10. Limpiar tanques\n" +
                            "11. Vaciar tanque\n" +
                            "12. Mejorar\n" +
                            "13. Pasar varios días\n" +
                            "14. Salir\n" +
                            "Dia:" + dias + "\t" +
                            "Monedas: " + monedas);
            String snum1;
            do {
                System.out.println("Seleccione una opcion");
                snum1 = sc.nextLine();
            } while (!ApoyoMenu.IsInteger(snum1));
            condition = Integer.parseInt(snum1);
            opcion(condition);
        } while (condition != 14);
    }

    /**
     * Hace la lógica de los menús del sistema
     * 
     * @param condicion La opcion del menu seleccionada
     */
    void opcion(int condicion) {
        switch (condicion) {
            case 1:
                ApoyoMenu.showGeneralStatus(piscifactorias);
                break;
            case 2:
                ApoyoMenu.selectPisc(piscifactorias).showStatus();
                break;
            case 3:
                ApoyoMenu.showSpecificStatus(piscifactorias);
                break;
            case 4:
                ApoyoMenu.showStats();
                break;
            case 5:
                ApoyoMenu.showIctio();
                break;
            case 6:
                ApoyoMenu.nextDay(piscifactorias);
                dias++;
                Simulador.registros.escribirTranscripcion(">>>Inicio del dia " + Simulador.dias);
                guardarPartida();
                break;
            case 7:
                ApoyoMenu.addFood(piscifactorias);
                break;
            case 8:
                ApoyoMenu.addFish(piscifactorias);
                break;
            case 9:
                ApoyoMenu.sell(piscifactorias);
                break;
            case 10:
                ApoyoMenu.cleanTank(piscifactorias);
                break;
            case 11:
                ApoyoMenu.emptyTank(piscifactorias);
                break;
            case 12:
                ApoyoMenu.upgrade(piscifactorias);
                break;
            case 13:
                Scanner sc = new Scanner(System.in);
                System.out.println("¿Cuantos dias quieres avanzar?");
                int avanzarDias = sc.nextInt();
                for (int i = 0; i < avanzarDias; i++) {
                    ApoyoMenu.nextDay(piscifactorias);
                    dias++;
                    Simulador.registros.escribirTranscripcion(">>>Inicio del dia " + Simulador.dias);
                    guardarPartida();
                }
                break;
            case 14:
                guardarPartida();
                System.out.println("Saliendo...");
                Simulador.registros.escribirLog("Cierre de la partida.");
                Simulador.registros.cerrarRegistros();
                break;
            case 15:
                rewards.generarMonedas(5);
                rewards.generarAlmacen('b');
                rewards.generarAlmacen('c');
                rewards.generarAlmacen('d');
                rewards.generarComida(2);
                rewards.generarPiscifactoriaMar('a');
                rewards.generarPiscifactoriaMar('b');
                rewards.generarPiscifactoriaRio('a');
                rewards.generarPiscifactoriaRio('b');
                rewards.generarTanqueMar();
                rewards.generarTanqueRio();
                rewards.generarAlmacen('a');
                break;
            case 98:
                ApoyoMenu.caso98(piscifactorias);
                Simulador.registros.escribirTranscripcion("Añadidos peces mediante la opción oculta");
                Simulador.registros.escribirLog("Añadidos peces mediante la opción oculta");
                break;
            case 99:
                System.out.println("Añadiendo 1000 monedas...");
                Simulador.monedas.ingresar(1000);
                Simulador.registros
                        .escribirTranscripcion("Añadidas 1000 monedas mediante la opción oculta. Monedas actuales, "
                                + Simulador.monedas.getMonedas());
                Simulador.registros.escribirLog("Añadidas monedas mediante la opción oculta.");
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    /**
     * Hace la logica del sistema
     */
    void logica() {
        try {
            File saves = new File("saves");
            if (saves.exists() && saves.isDirectory()) {
                // Listar archivos dentro del directorio
                File[] archivos = saves.listFiles();
                if (archivos != null && archivos.length > 0) {
                    int i = 1;
                    System.out.println("0: Crear nueva partida");
                    for (File archivo : archivos) {
                        System.out.println(i + ": " + archivo.getName());
                        i++;
                    }
                    String snum1;
                    do {
                        System.out.println("Seleccione una opcion");
                        snum1 = sc.nextLine();
                    } while (!ApoyoMenu.IsInteger(snum1));
                    i = Integer.parseInt(snum1);
                    if (i == 0) {
                        crearPartida();
                    } else {
                        this.load(archivos[i - 1]);
                        rewards = new Rewards();
                    }
                } else {
                    crearPartida();
                }
            } else {
                saves.mkdir();
                crearPartida();
            }
            menu();
        } catch (Exception e) {
            escribirError("Error en el proceso principal");
            registros.cerrarRegistros();
        }
    }

    /**
     * Metodo que crea una partida
     */
    void crearPartida() {
        System.out.println("Introduce el nombre de la partida");
        String nombre = sc.nextLine();
        System.out.println("Introduce el nombre de tu primera piscifactoria");
        String piscifactoria = sc.nextLine();
        init(nombre, piscifactoria);
        guardarPartida();
    }

    /**
     * Realiza las transcripciones iniciales del sistema
     * 
     * @param nombrePartida       el nombre de la partida
     * @param nombrePiscifactoria el nombre de la primera piscifactoria
     */
    void registrosIniciales(String nombrePartida, String nombrePiscifactoria) {
        registros.escribirTranscripcion("==========ARRANQUE==========");
        registros.escribirTranscripcion("Inicio de la simulación " + nombrePartida);
        registros.escribirTranscripcion("==========DINERO_INICIAL==========");
        registros.escribirTranscripcion("Dinero: " + monedas + " monedas");
        registros.escribirTranscripcion("==========PECES_IMPLEMENTADOS==========");
        registros.escribirTranscripcion("Rio:");
        escribirPeces(CriaTipo.RIO);

        registros.escribirTranscripcion("Mar:");
        escribirPeces(CriaTipo.MAR);

        registros.escribirTranscripcion("Doble:");
        escribirPeces(CriaTipo.DOBLE);

        registros.escribirTranscripcion("-----------------------------------");
        registros.escribirTranscripcion("Piscifactoría inicial: " + nombrePiscifactoria);

        registros.escribirLog("Inicio de la simulación " + nombrePartida);
        registros.escribirLog("Piscifactoría inicial: " + nombrePiscifactoria);
    }

    /**
     * Escribe la transcripcion de todos los peces del tipo indicado
     * 
     * @param criaTipo el tipo de pez indicado
     */
    void escribirPeces(CriaTipo criaTipo) {
        for (String pez : nombresPeces) {
            if (AlmacenPropiedades.getPropByName(pez).getPiscifactoria() == criaTipo) {
                registros.escribirTranscripcion("\t-" + pez);
            }
        }
    }

    /**
     * Escribe un error en en el archivo de errores
     * 
     * @param mensaje el mensaje de error
     */
    public static void escribirError(String mensaje) {
        String nombreArchivo = "logs/0_errors.log";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            writer.write("[" + formatter.format(date) + "] " + mensaje);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Guarda todos los datos de la partida en un archivo JSON
     */
    public void guardarPartida() {
        BufferedWriter bw = null;
        try {
            DataJson dataJson = new DataJson(nombresPeces, nombrePartida, dias, monedas.getMonedas(),
                    estadisticas.exportarDatos(nombresPeces), almacenCentral, piscifactorias);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String jsonString = gson.toJson(dataJson);
            bw = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream("saves/" + nombrePartida + ".save"), "UTF-8"));
            bw.write(jsonString);
            bw.flush();
            registros.escribirLog("Sistema guardado");
        } catch (IOException e) {
            Simulador.escribirError("Error al guardar la partida");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                Simulador.escribirError("Error al cerrar el buffer");
            }
        }
    }

    /**
     * Carga la información de un JSON
     * 
     * @param file el archivo JSON
     */
    public void load(File file) {
        Gson gson = new Gson();
        Reader reader = null;
        System.out.println("Cargando partida");
        try {
            reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            DataJson dataJson = gson.fromJson(reader, DataJson.class);
            this.nombrePartida = dataJson.empresa;
            Simulador.dias = dataJson.getDia();
            Simulador.monedas.setMonedas(dataJson.getMonedas());
            Estadisticas estadisticas = new Estadisticas(dataJson.getImplementados(), dataJson.getOrca());
            Simulador.estadisticas = estadisticas;
            Simulador.almacenCentral = dataJson.getEdificios();
            this.piscifactorias = (ArrayList<Piscifactoria>) dataJson.getPiscifactorias();
            registros.iniciar(nombrePartida, "transcripciones/", "logs/");
            registros.escribirLog("Sistema cargado");
            System.out.println("Partida cargada");
        } catch (IOException e) {
            Simulador.escribirError("Error al cargar la partida");
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                Simulador.escribirError("Error al cerrar el reader");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Simulador simulador = new Simulador();
        simulador.logica();
    }
}
