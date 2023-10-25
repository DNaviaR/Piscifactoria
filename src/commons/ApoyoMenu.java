package commons;

import java.util.List;
import java.util.Scanner;

import piscifactoria.Piscifactoria;
import piscifactoria.Tanque;

public class ApoyoMenu {
    public static void menuPisc(List<Piscifactoria> piscifactorias) {
        System.out.println("[Peces vivos / Peces Totales / Espacio Total]");
        for (int i = 0; i < piscifactorias.size(); i++) {
            System.out.println(i + ".- " + piscifactorias.get(i).getNombrePiscifactoria() + " ["
                    + piscifactorias.get(i).pecesVivos() + "/" + piscifactorias.get(i).peces() + "/"
                    + piscifactorias.get(i).pecesMax() + "]");
        }
    }

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

    public static Tanque selectTank(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        int opcion=0;
        for (int i = 0; i <piscifactoria.getTanques().size(); i++) {
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
}
