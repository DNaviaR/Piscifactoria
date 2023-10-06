package commons;

import java.util.ArrayList;
import java.util.Scanner;

import estadisticas.Estadisticas;
import pez.CarpaPlateada;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaRio;
import piscifactoria.Tanque;
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
                    "1. Estado general\n2. Estado piscifactoria\n3. Estado tanques\n4. Informes\n5. Ictiopedia\n6. Pasar día\n7. Comprar comida\n8. Comprar peces\n9. Vender peces\n10. Limpiar tanques\n11. Vaciar tanque\n12. Mejorar\n13. Pasar varios días\n14. Salir\n");
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
        CarpaPlateada cp = new CarpaPlateada(true);
        cp.showStatus();
    }
}
