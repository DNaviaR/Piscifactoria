package commons;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import almacen.AlmacenCentral;
import estadisticas.Estadisticas;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaRio;
import propiedades.AlmacenPropiedades;
import propiedades.CriaTipo;

/**
 * Clase que representa un simulador de una actividad relacionada con
 * piscifactorías.
 * Permite gestionar diversas estadísticas, monedas, y operaciones asociadas a
 * las piscifactorías.
 */
public class Simulador {
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
    Scanner sc = new Scanner(System.in);
    /**
     * Número de días de simulador
     */
    public static int dias;
    /**
     * Lista de piscifactorías que forman parte del simulador.
     */
    protected ArrayList<Piscifactoria> piscifactorias = new ArrayList<>();
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
     * Inicializa los elementos del sistema
     * 
     * @param nombrePartida        El nombre de la partida
     * @param primeraPiscifactoria el nombre de la primera piscifactoria
     */
    public void init(String nombrePartida, String primeraPiscifactoria) {
        dias = 0;
        File transcripciones = new File("transcripciones");
        if (!transcripciones.exists()) {
            transcripciones.mkdir();
        }
        File logs = new File("logs");
        if (!logs.exists()) {
            logs.mkdir();
        }
        registros.iniciar(nombrePartida, "transcripciones/", "logs/");
        piscifactorias.add(new PiscifactoriaRio(primeraPiscifactoria));
        piscifactorias.get(0).getAlmacen().setEspacioOcupado(piscifactorias.get(0).getAlmacen().getEspacioMaximo());
        monedas.setMonedas(100);
        registrosIniciales(nombrePartida, primeraPiscifactoria);
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
                }
                break;
            case 14:
                System.out.println("Saliendo...");
                break;
            case 98:
                ApoyoMenu.caso98(piscifactorias);
                break;
            case 99:
                System.out.println("Añadiendo 1000 monedas...");
                Simulador.monedas.ingresar(1000);
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
        System.out.println("Introduce el nombre de la partida");
        String nombre = sc.nextLine();
        System.out.println("Introduce el nombre de tu primera piscifactoria");
        String piscifactoria = sc.nextLine();
        init(nombre, piscifactoria);
        menu();
    }

    /**
     * Realiza las transcripciones iniciales del sistema
     * @param nombrePartida el nombre de la partida
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
    }

    /**
     * Escribe la transcripcion de todos los peces del tipo indicado
     * @param criaTipo el tipo de pez indicado
     */
    void escribirPeces(CriaTipo criaTipo){
        for (String pez : nombresPeces) {
            if (AlmacenPropiedades.getPropByName(pez).getPiscifactoria() == criaTipo) {
                registros.escribirTranscripcion("\t-" + pez);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Simulador simulador = new Simulador();
        simulador.logica();
    }
}
