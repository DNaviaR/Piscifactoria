package commons;

import java.util.ArrayList;
import java.util.Scanner;

import estadisticas.Estadisticas;
import pez.CarpaPlateada;
import pez.Pez;
import pez.PezRio;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaRio;
import piscifactoria.Tanque;
import piscifactoria.TanqueRio;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class Simulador {
    Estadisticas estadisticas;
    Scanner sc = new Scanner(System.in);
    private int dias;
    ArrayList<Piscifactoria> piscifactorias = new ArrayList<>();

    public void init(String nombrePartida, String primeraPiscifactoria) {
        this.dias = 0;
        piscifactorias.add(0, new PiscifactoriaRio(primeraPiscifactoria));

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
            condition = sc.nextInt();
        } while (condition != 14);
    }

    void logica() {
        System.out.println("Introduce el nombre de la partida");
        String nombre = sc.nextLine();
        System.out.println("Introduce el nombre de tu primera piscifactoria");
        String piscifactoria = sc.nextLine();
        init(nombre, piscifactoria);
        menu();
    }

    public static void main(String[] args) throws Exception {
        // Simulador simulador=new Simulador();
        // simulador.logica();
        /*TanqueRio tanque = new TanqueRio(1);
        ArrayList<Pez> peces = new ArrayList<>();
        peces.add(0, new CarpaPlateada(true));

        tanque.setPeces(peces);

        tanque.showStatus();
        tanque.showFishStatus();
        tanque.showCapacity();*/
        /*PezRio pez=new PezRio(false, AlmacenPropiedades.CARPA_PLATEADA);
        pez.showStatus();
        pez.grow();
        pez.showStatus();
        pez.grow();
        pez.showStatus();*/
    }
}
