package commons;

import java.util.ArrayList;
import java.util.Scanner;
import estadisticas.Estadisticas;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaMar;
import piscifactoria.PiscifactoriaRio;


public class Simulador {
    protected Estadisticas estadisticas;
    Scanner sc = new Scanner(System.in);
    protected int dias;
    protected ArrayList<Piscifactoria> piscifactorias = new ArrayList<>();

    
    /** 
     * @param nombrePartida
     * @param primeraPiscifactoria
     */
    public void init(String nombrePartida, String primeraPiscifactoria) {
        this.dias = 0;
        piscifactorias.add(0, new PiscifactoriaRio(primeraPiscifactoria));
        piscifactorias.add(0, new PiscifactoriaRio("pepe"));
        piscifactorias.add(0, new PiscifactoriaMar("lo"));

    }

    void menu() {
        int condition = 0;
        do {
            System.out.print(
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
                            "14. Salir\n");
            System.out.println("Seleccione una opcion: ");
            condition = sc.nextInt();
            opcion(condition);
        } while (condition != 14);
    }

    
    /** 
     * @param condicion
     */
    void opcion(int condicion) {
        switch (condicion) {
            case 1:
                ApoyoMenu.showGeneralStatus(piscifactorias);
                break;
            case 2:
                System.out.println(ApoyoMenu.selectPisc(piscifactorias).showStatus());
                break;
            case 3:
                ApoyoMenu.selectTank(piscifactorias).showStatus();
                break;
            case 4:
                ApoyoMenu.menuPisc(piscifactorias);
                break;
            case 5:
                ApoyoMenu.showSpecificStatus(piscifactorias);
                break;
            case 6:
                ApoyoMenu.showTankStatus(piscifactorias);
                break;
            case 7:
                ApoyoMenu.showIctio();
                break;
            default:
                break;
        }
    }

    void logica() {
        System.out.println("Introduce el nombre de la partida");
        String nombre = sc.nextLine();
        System.out.println("Introduce el nombre de tu primera piscifactoria");
        String piscifactoria = sc.nextLine();
        init(nombre, piscifactoria);
        menu();
    }

    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Simulador simulador = new Simulador();
        simulador.logica();
    }
}
