package commons;

import java.util.List;
import java.util.Scanner;

import piscifactoria.Piscifactoria;
import piscifactoria.Tanque;

/**
 * Una clase que proporciona soporte al método menú de Simulador.
 */
public class ApoyoMenu {

    /**
     * Muestra la lista de piscifactorías actuales en forma de menú
     * 
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void menuPisc(List<Piscifactoria> piscifactorias) {
        System.out.println("[Peces vivos / Peces Totales / Espacio Total]");
        for (int i = 0; i < piscifactorias.size(); i++) {
            System.out.println(i + ".- " + piscifactorias.get(i).getNombrePiscifactoria() + " ["
                    + piscifactorias.get(i).pecesVivos() + "/" + piscifactorias.get(i).peces() + "/"
                    + piscifactorias.get(i).pecesMax() + "]");
        }
    }

    /**
     * Muestra un menú de piscifactorias y permite al usuario seleccionar una.
     *
     * @param piscifactorias Una lista de piscifactorias.
     * @return La piscifactoria seleccionada, o nula si el usuario cancela la
     *         operación.
     */
    public static Piscifactoria selectPisc(List<Piscifactoria> piscifactorias) {
        int opcion;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < piscifactorias.size(); i++) {
            System.out.println(i + ".- " + piscifactorias.get(i).getNombrePiscifactoria());
        }
        System.out.println("Seleccione una opcion: ");
        opcion = sc.nextInt();
        if (opcion < 0 || opcion >= piscifactorias.size()) {
            System.out.println("Opcion no válida.");
            return null;
        }
        return piscifactorias.get(opcion);
    }

    /**
     * Muestra un menú de tanques y permite al usuario seleccionar uno.
     *
     * @param piscifactorias Una lista de piscifactorias.
     * @return El tanque seleccionado, o nulo si el usuario cancela la operación.
     */
    public static Tanque selectTank(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        int opcion = 0;
        for (int i = 0; i < piscifactoria.getTanques().size(); i++) {
            System.out.println(i + ".- " + piscifactoria.getTanques().get(i));
        }
        System.out.println("Seleccione una opcion: ");
        opcion = sc.nextInt();
        if (opcion < 0 || opcion >= piscifactoria.getTanques().size()) {
            System.out.println("Opcion no válida.");
            return null;
        }
        return piscifactoria.getTanques().get(opcion);
    }

    /**
     * Muestra un informe general del estado de todas las piscifactorías.
     *
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void showGeneralStatus(List<Piscifactoria> piscifactorias) {
        for (Piscifactoria piscifactoria : piscifactorias) {
            System.out.println(piscifactoria.showStatus() + "\n");
        }
        /*
         * if (AlmacenCentral.isActivo()==true) {
         * System.out.println(AlmacenCentral+"\n");
         * }
         */
    }

    /**
     * Muestra un informe específico de la piscifactoría seleccionada.
     *
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void showSpecificStatus(List<Piscifactoria> piscifactorias) {
        ApoyoMenu.selectPisc(piscifactorias).showTankStatus();
    }

    /**
     * Muestra un menú para seleccionar un tanque de una piscifactoría
     * 
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void showTankStatus(List<Piscifactoria> piscifactorias) {
        ApoyoMenu.selectTank(piscifactorias).showFishStatus();
    }

    public static void showIctio() {

    }

    public static void nextDay(List<Piscifactoria> piscifactorias) {
        for (Piscifactoria piscifactoria : piscifactorias) {
            piscifactoria.nextDay(0);
        }
    }

    public static void addFish() {

    }
}
