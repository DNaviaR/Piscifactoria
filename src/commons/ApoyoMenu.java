package commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.pecesDoble.Bagre;
import pez.pecesDoble.Dorada;
import pez.pecesDoble.LubinaEuropea;
import pez.pecesDoble.SalmonAtlantico;
import pez.pecesDoble.TruchaArcoiris;
import pez.pecesMar.Abadejo;
import pez.pecesMar.ArenqueAtlantico;
import pez.pecesMar.Besugo;
import pez.pecesMar.Caballa;
import pez.pecesMar.Cobia;
import pez.pecesMar.Corvina;
import pez.pecesMar.LenguadoEuropeo;
import pez.pecesMar.LubinaRayada;
import pez.pecesMar.Robalo;
import pez.pecesMar.Rodaballo;
import pez.pecesMar.Sargo;
import pez.pecesRio.Carpa;
import pez.pecesRio.CarpaPlateada;
import pez.pecesRio.CarpinTresEspinas;
import pez.pecesRio.LucioDelNorte;
import pez.pecesRio.Pejerrey;
import pez.pecesRio.PercaEuropea;
import pez.pecesRio.SalmonChinook;
import pez.pecesRio.TilapiaDelNilo;
import piscifactoria.Piscifactoria;
import piscifactoria.PiscifactoriaMar;
import piscifactoria.PiscifactoriaRio;
import piscifactoria.Tanque;
import piscifactoria.TanqueRio;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;
import propiedades.PecesProps;

/**
 * Una clase que proporciona soporte al método menú de Simulador.
 */
public class ApoyoMenu {
    /**
     * Comprueba si el valor introducido es un entero
     * 
     * @param text La cadena de texto a comprobar
     * @return Si es entero o no
     */
    public static boolean IsInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException ex) {
            System.out.println("Opción no valida");
            Simulador.escribirError("Opcion introducida no valida");
            return false;
        }
    }

    /**
     * Muestra la lista de piscifactorías actuales en forma de menú
     * 
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void menuPisc(List<Piscifactoria> piscifactorias) {
        System.out.println("[Peces vivos / Peces Totales / Espacio Total]");
        for (int i = 1; i <= piscifactorias.size(); i++) {
            System.out.println(i + ".- " + piscifactorias.get(i - 1).getNombrePiscifactoria() + " ["
                    + piscifactorias.get(i - 1).pecesVivos() + "/" + piscifactorias.get(i - 1).peces() + "/"
                    + piscifactorias.get(i - 1).pecesMax() + "]");
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
        ApoyoMenu.menuPisc(piscifactorias);
        System.out.println("Seleccione una piscifactoria: ");
        opcion = sc.nextInt();
        if (opcion <= 0 || opcion > piscifactorias.size()) {
            System.out.println("Opcion no válida.");
            return null;
        }
        return piscifactorias.get(opcion - 1);
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
        for (int i = 1; i <= piscifactoria.getTanques().size(); i++) {
            System.out.print(i + ".- " + piscifactoria.getTanques().get(i - 1) + " ");
            if (!piscifactoria.getTanques().get(i - 1).getPeces().isEmpty()) {
                System.out.println(piscifactoria.getTanques().get(i - 1).getPeces().get(0).getNombre());
            } else {
                System.out.println("Vacio");
            }
        }
        System.out.println("Seleccione un tanque: ");
        opcion = sc.nextInt();
        if (opcion <= 0 || opcion > piscifactoria.getTanques().size()) {
            System.out.println("Opcion no válida.");
            return null;
        }
        return piscifactoria.getTanques().get(opcion - 1);
    }

    /**
     * Muestra un informe general del estado de todas las piscifactorías.
     *
     * @param piscifactorias Una lista de piscifactorias.
     */
    public static void showGeneralStatus(List<Piscifactoria> piscifactorias) {
        for (Piscifactoria piscifactoria : piscifactorias) {
            piscifactoria.showStatus();
        }
        if (Simulador.almacenCentral.isActivo() == true) {
            System.out.println("==============================");
            System.out.println(Simulador.almacenCentral + "\n");
        }

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

    /**
     * Muestra un desglose de los peces del sistema indicando su nombre, el número
     * de ellos que han nacido, cuantos fueron vendidos y el dinero
     * ganado con ello.
     * Por último, muestra un total general con las tres cantidades
     */
    public static void showStats() {
        Simulador.estadisticas.mostrar();
    }

    /**
     * Permite consultar la información de cada pez mostrando un menú de selección
     * de los disponibles
     */
    public static void showIctio() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        for (int i = 1; i <= Simulador.nombresPeces.length; i++) {
            System.out.println(i + ".- " + Simulador.nombresPeces[i - 1]);
        }
        System.out.println("Seleccione una opcion: ");
        opcion = sc.nextInt();
        if (opcion <= 0 || opcion > Simulador.nombresPeces.length) {
            System.out.println("Opcion no válida.");
        } else {
            String nombre = Simulador.nombresPeces[opcion - 1];
            PecesDatos pc = AlmacenPropiedades.getPropByName(nombre);
            System.out.println("Nombre: " + pc.getNombre() + "\n" +
                    "Nombre cientifico: " + pc.getCientifico() + "\n" +
                    "Coste: " + pc.getCoste() + "\n" +
                    "Monedas: " + pc.getMonedas() + "\n" +
                    "Huevos: " + pc.getHuevos() + "\n" +
                    "Ciclo: " + pc.getCiclo() + "\n" +
                    "Madurez: " + pc.getMadurez() + "\n" +
                    "Optimo: " + pc.getOptimo());
        }
    }

    /**
     * Método que avanza un día en todas las piscifactorías, realizando el
     * crecimiento, reproducción
     * y venta de peces óptimos
     * 
     * @param piscifactorias Una lista de piscifactorias
     */
    public static void nextDay(List<Piscifactoria> piscifactorias) {
        int monedasIniciales = Simulador.monedas.getMonedas();
        int pecesVendidos = 0;
        int pecesMar = 0;
        int pecesRio = 0;
        for (Piscifactoria piscifactoria : piscifactorias) {
            int monedasPiscifactoria = Simulador.monedas.getMonedas();
            int pecesVendidosPiscifactoria = piscifactoria.getContadorPecesVendidos();
            piscifactoria.nextDay();
            pecesVendidosPiscifactoria = piscifactoria.getContadorPecesVendidos() - pecesVendidosPiscifactoria;
            System.out.println("Piscifactoría " + piscifactoria.getNombrePiscifactoria() + ": "
                    + pecesVendidosPiscifactoria + " peces vendidos por "
                    + (Simulador.monedas.getMonedas() - monedasPiscifactoria) + " monedas");
            pecesVendidos += pecesVendidosPiscifactoria;
            if (piscifactoria instanceof PiscifactoriaMar) {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    pecesMar += tanque.getPeces().size();
                }
            } else {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    pecesRio += tanque.getPeces().size();
                }
            }
        }
        System.out.println(pecesVendidos + " peces vendidos por un total de "
                + (Simulador.monedas.getMonedas() - monedasIniciales) + " monedas");
        Simulador.registros.escribirTranscripcion("Fin del dia " + Simulador.dias);
        Simulador.registros.escribirTranscripcion("Peces actuales, " + pecesRio + " de río " + pecesMar + " de mar");
        Simulador.registros.escribirTranscripcion((Simulador.monedas.getMonedas() - monedasIniciales)
                + " monedas ganadas por un total de " + Simulador.monedas.getMonedas());
        Simulador.registros.escribirTranscripcion("---------------------------------------------------");
        Simulador.registros.escribirLog("Fin del dia " + Simulador.dias);
    }

    /**
     * Añade comida al almacen de una piscifactoria
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void addFood(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        if (Simulador.almacenCentral.isActivo() == true) {
            if (Simulador.almacenCentral.getEspacioDisponible() > 0) {
                System.out.println("Introduce la cantidad a añadir:\n1.-5\n2.-10\n3.-25\n4.-Llenar");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        if (Simulador.monedas.getMonedas() >= 5) {
                            if (Simulador.almacenCentral.getEspacioDisponible() >= 5) {
                                Simulador.almacenCentral.masComida(5);
                                System.out.println("Añadida " + 5 + " de comida");
                                Simulador.monedas.pagar(5);
                                Simulador.registros.escribirTranscripcion(5 + " de comida comprada por " + 5
                                        + " monedas. Se almacena en el almacén central");
                                Simulador.registros
                                        .escribirLog(5 + " de comida comprada. Se almacena en el almacén central");
                            } else {
                                System.out.println(
                                        "Añadida " + Simulador.almacenCentral.getEspacioDisponible() + " de comida");
                                Simulador.monedas.pagar(Simulador.almacenCentral.getEspacioDisponible());
                                Simulador.registros.escribirTranscripcion(
                                        (Simulador.almacenCentral.getEspacioDisponible()) + " de comida comprada por "
                                                + (Simulador.almacenCentral.getEspacioDisponible()) +
                                                " monedas. Se almacena en el almacén central");
                                Simulador.registros.escribirLog(Simulador.almacenCentral.getEspacioDisponible()
                                        + " de comida comprada. Se almacena en el almacén central");
                                Simulador.almacenCentral.masComida(Simulador.almacenCentral.getEspacioDisponible());
                            }
                        } else {
                            System.out.println("No hay monedas suficientes");
                        }
                        break;
                    case 2:
                        if (Simulador.monedas.getMonedas() >= 10) {
                            if (Simulador.almacenCentral.getEspacioDisponible() >= 10) {
                                Simulador.almacenCentral.masComida(10);
                                System.out.println("Añadida " + 10 + " de comida");
                                Simulador.monedas.pagar(10);
                                Simulador.registros.escribirTranscripcion(10 + " de comida comprada por " + 10
                                        + " monedas. Se almacena en el almacén central");
                                Simulador.registros
                                        .escribirLog(10 + " de comida comprada. Se almacena en el almacén central");
                            } else {
                                System.out.println(
                                        "Añadida " + Simulador.almacenCentral.getEspacioDisponible() + " de comida");
                                Simulador.monedas.pagar(Simulador.almacenCentral.getEspacioDisponible());
                                Simulador.registros.escribirTranscripcion(
                                        (Simulador.almacenCentral.getEspacioDisponible()) + " de comida comprada por "
                                                + (Simulador.almacenCentral.getEspacioDisponible()) +
                                                " monedas. Se almacena en el almacén central");
                                Simulador.registros.escribirLog(Simulador.almacenCentral.getEspacioDisponible()
                                        + " de comida comprada. Se almacena en el almacén central");
                                Simulador.almacenCentral.masComida(Simulador.almacenCentral.getEspacioDisponible());
                            }
                        } else {
                            System.out.println("No hay monedas suficientes");
                        }
                        break;
                    case 3:
                        if (Simulador.monedas.getMonedas() >= 25) {
                            if (Simulador.almacenCentral.getEspacioDisponible() >= 25) {
                                Simulador.almacenCentral.masComida(25);
                                System.out.println("Añadida " + 25 + " de comida");
                                Simulador.monedas.pagar(20);
                                Simulador.registros.escribirTranscripcion(25 + " de comida comprada por " + 20
                                        + " monedas. Se almacena en el almacén central");
                                Simulador.registros
                                        .escribirLog(25 + " de comida comprada. Se almacena en el almacén central");
                            } else {
                                System.out.println(
                                        "Añadida " + Simulador.almacenCentral.getEspacioDisponible() + " de comida");
                                Simulador.monedas.pagar(Simulador.almacenCentral.getEspacioDisponible());
                                Simulador.registros.escribirTranscripcion(
                                        (Simulador.almacenCentral.getEspacioDisponible()) + " de comida comprada por "
                                                + (Simulador.almacenCentral.getEspacioDisponible()) +
                                                " monedas. Se almacena en el almacén central");
                                Simulador.registros.escribirLog(Simulador.almacenCentral.getEspacioDisponible()
                                        + " de comida comprada. Se almacena en el almacén central");
                                Simulador.almacenCentral.masComida(Simulador.almacenCentral.getEspacioDisponible());
                            }
                        } else {
                            System.out.println("No hay monedas suficientes");
                        }
                        break;
                    case 4:
                        int descuento = (Simulador.almacenCentral.getEspacioDisponible() / 25) * 5;
                        if (Simulador.monedas
                                .getMonedas() >= (Simulador.almacenCentral.getEspacioDisponible() - descuento)) {
                            System.out.println(
                                    "Añadida " + Simulador.almacenCentral.getEspacioDisponible() + " de comida");
                            Simulador.monedas.pagar((Simulador.almacenCentral.getEspacioDisponible()) - descuento);
                            Simulador.registros.escribirTranscripcion(
                                    (Simulador.almacenCentral.getEspacioDisponible()) + " de comida comprada por "
                                            + ((Simulador.almacenCentral.getEspacioDisponible()) - descuento) +
                                            " monedas. Se almacena en el almacén central");
                            Simulador.registros.escribirLog(Simulador.almacenCentral.getEspacioDisponible()
                                    + " de comida comprada. Se almacena en el almacén central");
                            Simulador.almacenCentral.setEspacioOcupado(Simulador.almacenCentral.getEspacioMaximo());
                        } else {
                            System.out.println("No hay monedas suficientes");
                        }
                        break;
                    default:
                        System.out.println("Opción no valida");
                        break;
                }
                repartirEquitativamente(piscifactorias);
            } else {
                System.out.println("El almacén está lleno");
            }
        } else {
            Piscifactoria p = ApoyoMenu.selectPisc(piscifactorias);
            if (p.getAlmacen().getEspacioDisponible() > 0) {
                System.out.println("Introduce la cantidad a añadir:\n1.-5\n2.-10\n3.-25\n4.-Llenar");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        comerYPagar(p, 5);
                        break;
                    case 2:
                        comerYPagar(p, 10);
                        break;
                    case 3:
                        comerYPagar(p, 25);
                        break;
                    case 4:
                        int descuento = (p.getAlmacen().getEspacioDisponible() / 25) * 5;
                        if (Simulador.monedas.getMonedas() >= (p.getAlmacen().getEspacioDisponible() - descuento)) {
                            System.out.println("Añadida " + p.getAlmacen().getEspacioDisponible() + " de comida");
                            Simulador.monedas.pagar((p.getAlmacen().getEspacioDisponible()) - descuento);
                            Simulador.registros.escribirTranscripcion(
                                    p.getAlmacen().getEspacioDisponible() + " de comida comprada por "
                                            + ((p.getAlmacen().getEspacioDisponible()) - descuento) +
                                            " monedas. Se almacena en la piscifactoría " + p.getNombrePiscifactoria());
                            Simulador.registros.escribirLog(p.getAlmacen().getEspacioDisponible()
                                    + " de comida comprada. Se almacena en la piscifactoría "
                                    + p.getNombrePiscifactoria());
                            p.getAlmacen().setEspacioOcupado(p.getAlmacen().getEspacioMaximo());
                        }
                        break;
                    default:
                        System.out.println("Opción no valida");
                        break;
                }
                System.out.println("Depósito de comida de la piscifactoría " + p.getNombrePiscifactoria() + " al "
                        + ((p.getAlmacen().getEspacioOcupado() / p.getAlmacen().getEspacioMaximo()) * 100)
                        + "% de su capacidad. [" + p.getAlmacen().getEspacioOcupado() + "/"
                        + p.getAlmacen().getEspacioMaximo()
                        + "]");
            } else {
                System.out.println("El almacén está lleno");
            }
        }
    }

    /**
     * Añade un pez a la piscifactoria escogida
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void addFish(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        System.out.println("¿Que tipo de pez quieres añadir?\n1.-De rio\n2.-De mar\n3.-Doble");
        System.out.println("Selecciona el tipo: ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                opcion = mostrarPecesRio();
                switch (opcion) {
                    case 1:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.CARPA.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new Carpa(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 2:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.CARPA_PLATEADA.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new CarpaPlateada(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 3:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.CARPIN_TRES_ESPINAS.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new CarpinTresEspinas(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 4:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.LUCIO_NORTE.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new LucioDelNorte(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 5:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.PEJERREY.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new Pejerrey(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 6:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.PERCA_EUROPEA.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new PercaEuropea(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 7:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.SALMON_CHINOOK.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new SalmonChinook(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 8:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.TILAPIA_NILO.getCoste()) {
                            comprobarTanquesDisponiblesPecesRio(piscifactorias, new TilapiaDelNilo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                opcion = mostrarPecesMar();
                switch (opcion) {
                    case 1:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.ABADEJO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Abadejo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 2:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.ARENQUE_ATLANTICO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new ArenqueAtlantico(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 3:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.BESUGO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Besugo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 4:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.CABALLA.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Caballa(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 5:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.COBIA.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Cobia(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 6:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.CORVINA.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Corvina(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 7:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.LENGUADO_EUROPEO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new LenguadoEuropeo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 8:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.LUBINA_RAYADA.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new LubinaRayada(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 9:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.ROBALO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Robalo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 10:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.RODABALLO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Rodaballo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 11:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.SARGO.getCoste()) {
                            comprobarTanquesDisponiblesPecesMar(piscifactorias, new Sargo(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                opcion = mostrarPecesDobles();
                switch (opcion) {
                    case 1:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.BAGRE_CANAL.getCoste()) {
                            comprobarTanquesDisponiblesPecesDobles(piscifactorias, new Bagre(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 2:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.DORADA.getCoste()) {
                            comprobarTanquesDisponiblesPecesDobles(piscifactorias, new Dorada(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 3:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.LUBINA_EUROPEA.getCoste()) {
                            comprobarTanquesDisponiblesPecesDobles(piscifactorias, new LubinaEuropea(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 4:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.SALMON_ATLANTICO.getCoste()) {
                            comprobarTanquesDisponiblesPecesDobles(piscifactorias, new SalmonAtlantico(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    case 5:
                        if (Simulador.monedas.getMonedas() >= AlmacenPropiedades.TRUCHA_ARCOIRIS.getCoste()) {
                            comprobarTanquesDisponiblesPecesDobles(piscifactorias, new TruchaArcoiris(false));
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operación");
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }

    /**
     * Muestra los peces de rio disponibles y devuelve el pez seleccionado
     * 
     * @return el pez seleccionado
     */
    public static int mostrarPecesRio() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "1.-Carpa\n2.-Carpa Plateada\n3.-Carpin Tres Espinas\n4.-Lucio del Norte\n5.-Pejerrey\n6.-Perca Europea\n7.-Salmon Chinook\n8.-Tilapia del Nilo");
        System.out.println("Seleccione una opción");
        int opcion = sc.nextInt();
        return opcion;
    }

    /**
     * Muestra los peces de mar disponibles y devuelve el pez seleccionado
     * 
     * @return el pez seleccionado
     */
    public static int mostrarPecesMar() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "1.-Abadejo\n2.-Arenque Atlántico\n3.-Besugo\n4.-Caballa\n5.-Covia\n6.-Corvina\n7.-Lenguado Europeo\n8.-Lubina Rayada\n9.-Robalo\n10.-Rodaballo\n11.-Sargo");
        System.out.println("Seleccione una opción");
        int opcion = sc.nextInt();
        return opcion;
    }

    /**
     * Muestra los peces doble disponibles y devuelve el pez seleccionado
     * 
     * @return el pez seleccionado
     */
    public static int mostrarPecesDobles() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.-Bagre\n2.-Dorada\n3.-Lubina Europea\n4.-Salmon Atlantico\n5.-Trucha Arcoiris");
        System.out.println("Seleccione una opción");
        int opcion = sc.nextInt();
        return opcion;
    }

    /**
     * Comprueba los tanques en los que puede insertar el pez de rio deseado y
     * permite
     * elegir uno
     * 
     * @param piscifactorias La lista de piscifactorias
     * @param tipoPez        El pez de rio que se inserta
     */
    public static void comprobarTanquesDisponiblesPecesRio(List<Piscifactoria> piscifactorias, Pez tipoPez) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tanque> tanquesDisponibles = new ArrayList<>();
        ArrayList<Piscifactoria> piscifactoriasDisponibles = new ArrayList<>();
        for (Piscifactoria piscifactoria : piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaRio) {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    if (tanque.getPeces().isEmpty()) {
                        tanquesDisponibles.add(tanque);
                        piscifactoriasDisponibles.add(piscifactoria);
                    } else if (tanque.getPeces().get(0).getClass() == tipoPez.getClass()) {
                        tanquesDisponibles.add(tanque);
                        piscifactoriasDisponibles.add(piscifactoria);
                    }
                }
            }
        }
        if (!tanquesDisponibles.isEmpty()) {
            System.out.println("Los tanques disponibles para " + tipoPez.getNombre() + " son: ");
            for (int i = 1; i <= tanquesDisponibles.size(); i++) {
                System.out.println(i + " Tanque " + tanquesDisponibles.get(i - 1).getNumeroTanque()
                        + " de la piscifactoria " + piscifactorias.get(i - 1).getNombrePiscifactoria());
            }
            System.out.println("Seleccione un tanque: ");
            int opcion = sc.nextInt();
            boolean sexo = true;
            if (!tanquesDisponibles.get(opcion - 1).getPeces().isEmpty()) {
                sexo = tipoPez.obtenerSexosTanque(tanquesDisponibles.get(opcion - 1).getPeces());
            }
            tipoPez.setSexo(sexo);
            tanquesDisponibles.get(opcion - 1).getPeces().add(tipoPez);
            Simulador.monedas.pagar(tipoPez.getPecesDatos().getCoste());
            Simulador.registros.escribirTranscripcion(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado por " + tipoPez.getPecesDatos().getCoste() + " monedas. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
            Simulador.registros.escribirLog(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
        } else {
            System.out.println("No hay tanques disponibles");
        }
    }

    /**
     * Comprueba los tanques en los que puede insertar el pez de mar deseado y
     * permite
     * elegir uno
     * 
     * @param piscifactorias La lista de piscifactorias
     * @param tipoPez        El pez de mar que se inserta
     */
    public static void comprobarTanquesDisponiblesPecesMar(List<Piscifactoria> piscifactorias, Pez tipoPez) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tanque> tanquesDisponibles = new ArrayList<>();
        ArrayList<Piscifactoria> piscifactoriasDisponibles = new ArrayList<>();
        for (Piscifactoria piscifactoria : piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaMar) {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    if (tanque.getPeces().isEmpty()) {
                        tanquesDisponibles.add(tanque);
                        piscifactoriasDisponibles.add(piscifactoria);
                    } else if (tanque.getPeces().get(0).getClass() == tipoPez.getClass()) {
                        tanquesDisponibles.add(tanque);
                        piscifactoriasDisponibles.add(piscifactoria);
                    }
                }
            }
        }
        if (!tanquesDisponibles.isEmpty()) {
            System.out.println("Los tanques disponibles para " + tipoPez.getNombre() + " son: ");
            for (int i = 1; i <= tanquesDisponibles.size(); i++) {
                System.out.println(i + " Tanque " + tanquesDisponibles.get(i - 1).getNumeroTanque()
                        + " de la piscifactoria " + piscifactorias.get(i - 1).getNombrePiscifactoria());
            }
            System.out.println("Seleccione un tanque: ");
            int opcion = sc.nextInt();
            boolean sexo = true;
            if (!tanquesDisponibles.get(opcion - 1).getPeces().isEmpty()) {
                sexo = tipoPez.obtenerSexosTanque(tanquesDisponibles.get(opcion - 1).getPeces());
            }
            tipoPez.setSexo(sexo);
            tanquesDisponibles.get(opcion - 1).getPeces().add(tipoPez);
            Simulador.monedas.pagar(tipoPez.getPecesDatos().getCoste());
            Simulador.registros.escribirTranscripcion(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado por " + tipoPez.getPecesDatos().getCoste() + " monedas. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
            Simulador.registros.escribirLog(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
        } else {
            System.out.println("No hay tanques disponibles");
        }
    }

    /**
     * Comprueba los tanques en los que puede insertar el pez doble deseado y
     * permite
     * elegir uno
     * 
     * @param piscifactorias La lista de piscifactorias
     * @param tipoPez        El pez doble que se inserta
     */
    public static void comprobarTanquesDisponiblesPecesDobles(List<Piscifactoria> piscifactorias, Pez tipoPez) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Tanque> tanquesDisponibles = new ArrayList<>();
        ArrayList<Piscifactoria> piscifactoriasDisponibles = new ArrayList<>();
        for (Piscifactoria piscifactoria : piscifactorias) {
            ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
            for (Tanque tanque : tanques) {
                if (tanque.getPeces().isEmpty()) {
                    tanquesDisponibles.add(tanque);
                    piscifactoriasDisponibles.add(piscifactoria);
                } else if (tanque.getPeces().get(0).getClass() == tipoPez.getClass()) {
                    tanquesDisponibles.add(tanque);
                    piscifactoriasDisponibles.add(piscifactoria);
                }
            }
        }
        if (!tanquesDisponibles.isEmpty()) {
            System.out.println("Los tanques disponibles para " + tipoPez.getNombre() + " son: ");
            for (int i = 1; i <= tanquesDisponibles.size(); i++) {
                System.out.println(i + " Tanque " + tanquesDisponibles.get(i - 1).getNumeroTanque()
                        + " de la piscifactoria " + piscifactoriasDisponibles.get(i - 1).getNombrePiscifactoria());
            }
            System.out.println("Seleccione un tanque: ");
            int opcion = sc.nextInt();
            boolean sexo = true;
            if (!tanquesDisponibles.get(opcion - 1).getPeces().isEmpty()) {
                sexo = tipoPez.obtenerSexosTanque(tanquesDisponibles.get(opcion - 1).getPeces());
            }
            tipoPez.setSexo(sexo);
            tanquesDisponibles.get(opcion - 1).getPeces().add(tipoPez);
            Simulador.monedas.pagar(tipoPez.getPecesDatos().getCoste());
            Simulador.registros.escribirTranscripcion(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado por " + tipoPez.getPecesDatos().getCoste() + " monedas. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
            Simulador.registros.escribirLog(tipoPez.getNombre() + " " + ((sexo == true) ? "H" : "M") +
                    " comprado. Añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1) +
                    " de la piscifactoría " + piscifactorias.get(opcion - 1).getNombrePiscifactoria());
            System.out.println(tipoPez.getNombre() + " añadido al tanque "
                    + tanquesDisponibles.get(opcion - 1).getNumeroTanque() + " de la piscifactoria "
                    + piscifactoriasDisponibles.get(opcion - 1).getNombrePiscifactoria() + " por "
                    + tipoPez.getPecesDatos().getCoste() + " monedas");
        } else {
            System.out.println("No hay tanques disponibles");
        }
    }

    /**
     * Añade la cantidad de comida introducida si hay monedas suficientes para
     * pagarla
     * 
     * @param piscifactoria La piscifactoria a la cual le insertamos comida
     * @param cantidad      La cantidad de comida a añadir
     */
    public static void comerYPagar(Piscifactoria piscifactoria, int cantidad) {
        if (Simulador.monedas.getMonedas() >= cantidad) {
            if (piscifactoria.getAlmacen().getEspacioDisponible() >= cantidad) {
                piscifactoria.getAlmacen().masComida(cantidad);
                System.out.println("Añadida " + cantidad + " de comida");
                Simulador.monedas.pagar(cantidad);
                Simulador.registros.escribirTranscripcion(cantidad + " de comida comprada por " + cantidad +
                        " monedas. Se almacena en la piscifactoría " + piscifactoria.getNombrePiscifactoria());
                Simulador.registros.escribirLog(cantidad + " de comida comprada. Se almacena en la piscifactoría "
                        + piscifactoria.getNombrePiscifactoria());
            } else {
                System.out.println("Añadida " + piscifactoria.getAlmacen().getEspacioDisponible() + " de comida");
                Simulador.monedas.pagar(piscifactoria.getAlmacen().getEspacioDisponible());
                Simulador.registros.escribirTranscripcion(piscifactoria.getAlmacen().getEspacioDisponible()
                        + " de comida comprada por "
                        + piscifactoria.getAlmacen().getEspacioDisponible()
                        + " monedas. Se almacena en la piscifactoría " + piscifactoria.getNombrePiscifactoria());
                Simulador.registros.escribirLog(piscifactoria.getAlmacen().getEspacioDisponible()
                        + " de comida comprada. Se almacena en la piscifactoría "
                        + piscifactoria.getNombrePiscifactoria());
                piscifactoria.getAlmacen().masComida(piscifactoria.getAlmacen().getEspacioDisponible());
            }
        } else {
            System.out.println("No hay monedas suficientes");
        }
    }

    /**
     * Reparte la comida del almacen central entre los almacenes de las
     * piscifactorias
     * 
     * @param piscifactorias La lista de las piscifactorias
     */
    public static void repartirEquitativamente(List<Piscifactoria> piscifactorias) {
        int cantidadTotal = Simulador.almacenCentral.getEspacioOcupado();
        int cantidadPorPiscifactoria = cantidadTotal / piscifactorias.size();
        int cantidadExtra = cantidadTotal % piscifactorias.size();
        for (Piscifactoria piscifactoria : piscifactorias) {
            int cantidadDistribuir = cantidadPorPiscifactoria + (cantidadExtra > 0 ? 1 : 0);
            int espacioDisponible = piscifactoria.getAlmacen().getEspacioDisponible();
            int cantidadRealDistribuir = Math.min(cantidadDistribuir, espacioDisponible);
            piscifactoria.getAlmacen().masComida(cantidadRealDistribuir);
            Simulador.almacenCentral
                    .setEspacioOcupado(Simulador.almacenCentral.getEspacioOcupado() - cantidadRealDistribuir);
            cantidadExtra -= cantidadDistribuir - cantidadRealDistribuir;
        }
        if (cantidadTotal != Simulador.almacenCentral.getEspacioOcupado()) {
            System.out.println("La comida del almacén central ha sido distribuida");
        }
        System.out.println("Depósito de comida del Almacen Central al "
                + ((double) Simulador.almacenCentral.getEspacioOcupado()
                        / (double) Simulador.almacenCentral.getEspacioMaximo() * 100)
                + "% de su capacidad. [" + Simulador.almacenCentral.getEspacioOcupado() + "/"
                + Simulador.almacenCentral.getEspacioMaximo()
                + "]");
    }

    /**
     * Vende todos los peces adultos que estén vivos de la piscifactoria
     * seleccioanda
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void sell(List<Piscifactoria> piscifactorias) {
        int monedasIniciales = Simulador.monedas.getMonedas();
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        int pecesVendidosPiscifactoria = piscifactoria.getContadorPecesVendidos();
        ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
        for (Tanque<? extends Pez> tanque : tanques) {
            ArrayList<Pez> peces = tanque.getPeces();
            for (Pez pez : peces) {
                if (pez.isAdulto() && pez.isEstaVivo()) {
                    Simulador.estadisticas.registrarVenta(pez.getNombre(), pez.getPecesDatos().getMonedas() / 2);
                    Simulador.monedas.ingresar(pez.getPecesDatos().getMonedas() / 2);
                    peces.set(peces.indexOf(pez), null);
                    piscifactoria.setContadorPecesVendidos(piscifactoria.getContadorPecesVendidos() + 1);
                }
            }
            tanque.eliminarNulos();
        }
        pecesVendidosPiscifactoria = piscifactoria.getContadorPecesVendidos() - pecesVendidosPiscifactoria;
        System.out.println("Piscifactoría " + piscifactoria.getNombrePiscifactoria() + ": "
                + pecesVendidosPiscifactoria + " peces vendidos por "
                + (Simulador.monedas.getMonedas() - monedasIniciales) + " monedas");
        Simulador.registros
                .escribirTranscripcion("Vendidos " + pecesVendidosPiscifactoria + " peces de la piscifactoría "
                        + piscifactoria.getNombrePiscifactoria() + " de forma manual por " +
                        (Simulador.monedas.getMonedas() - monedasIniciales) + " monedas");
        Simulador.registros
                .escribirLog("Vendidos " + pecesVendidosPiscifactoria + " peces de la piscifactoría "
                        + piscifactoria.getNombrePiscifactoria() + " de forma manual");
    }

    /**
     * Elimina todos los peces muertos de los tanques de una piscifactoría.
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void cleanTank(List<Piscifactoria> piscifactorias) {
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
        for (Tanque<? extends Pez> tanque : tanques) {
            ArrayList<Pez> peces = tanque.getPeces();
            for (Pez pez : peces) {
                if (!pez.isEstaVivo()) {
                    peces.set(peces.indexOf(pez), null);
                }
            }
            tanque.eliminarNulos();
            Simulador.registros.escribirTranscripcion("Limpiado el tanque " + tanque.getNumeroTanque()
                    + " de la piscifactoría " + piscifactoria.getNombrePiscifactoria());
            Simulador.registros.escribirLog("Limpiado el tanque " + tanque.getNumeroTanque()
                    + " de la piscifactoría " + piscifactoria.getNombrePiscifactoria());
        }
        System.out.println("Piscifactoria " + piscifactoria.getNombrePiscifactoria() + " limpia");
    }

    /**
     * Elimina todos los peces de un tanque concreto independientemente de su
     * estado.
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void emptyTank(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        int opcion = 0;
        for (int i = 1; i <= piscifactoria.getTanques().size(); i++) {
            System.out.print(i + ".- " + piscifactoria.getTanques().get(i - 1) + " ");
            if (!piscifactoria.getTanques().get(i - 1).getPeces().isEmpty()) {
                System.out.println(piscifactoria.getTanques().get(i - 1).getPeces().get(0).getNombre());
            } else {
                System.out.println("Vacio");
            }
        }
        System.out.println("Seleccione un tanque: ");
        opcion = sc.nextInt();
        if (opcion <= 0 || opcion > piscifactoria.getTanques().size()) {
            System.out.println("Opcion no válida.");
        }
        Tanque tanque = piscifactoria.getTanques().get(opcion - 1);
        tanque.getPeces().clear();
        System.out.println("Tanque " + tanque.getNumeroTanque() + " ha sido vaciado");
        Simulador.registros.escribirTranscripcion("Vaciado el tanque " + tanque.getNumeroTanque() +
                " de la piscifactoría " + piscifactoria.getNombrePiscifactoria());
        Simulador.registros.escribirLog("Vaciado el tanque " + tanque.getNumeroTanque() +
                " de la piscifactoría " + piscifactoria.getNombrePiscifactoria());
    }

    /**
     * Método que permite comprar o mejorar edificios
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void upgrade(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1.-Comprar edificios\n2.-Mejorar edificios\n3.-Cancelar");
        System.out.println("Seleccione una opción: ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                if (!Simulador.almacenCentral.isActivo()) {
                    System.out.println("1.-Piscifactoria");
                    System.out.println("2.-Almacen central");
                    System.out.println("Seleccione una opción: ");
                    opcion = sc.nextInt();
                    switch (opcion) {
                        case 1:
                            comprarPiscifactoria(piscifactorias);
                            break;
                        case 2:
                            comprarAlmacenCentral();
                            break;
                    }
                } else {
                    comprarPiscifactoria(piscifactorias);
                }
                break;
            case 2:
                if (!Simulador.almacenCentral.isActivo()) {

                }
                System.out.println("1.-Piscifactoria\n2.-Almacen central");
                System.out.println("Seleccione una opción: ");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        mejorarPiscifactoria(piscifactorias);
                        break;
                    case 2:
                        if (Simulador.monedas.getMonedas() >= 50) {
                            Simulador.monedas.pagar(50);
                            Simulador.almacenCentral.setEspacioMaximo(Simulador.almacenCentral.getEspacioMaximo() + 50);
                            System.out.println(
                                    "La capacidad del Almacen central ha sido mejorada en 50. Nueva capacidad: "
                                            + Simulador.almacenCentral.getEspacioMaximo());
                        } else {
                            System.out.println("No hay monedas suficientes para realizar esta operacion");
                        }
                        break;
                    default:
                        break;
                }
                break;
            case 3:
                System.out.println("Cancelando...");
                break;
            default:
                break;
        }
    }

    /**
     * Compra una piscifactoria del tipo indicado y la añade a la lista de
     * piscifactorias
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void comprarPiscifactoria(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("Seleccione el tipo de piscifactoria:\n1.-Rio\n2.-Mar");
            opcion = sc.nextInt();
            sc.nextLine();
        } while (opcion != 1 && opcion != 2);
        System.out.println("Indique el nombre de la nueva piscifactoria: ");
        String nombreNuevaPiscifactoria = sc.nextLine();
        switch (opcion) {
            case 1:
                int cantidadPagar = 500 * contarPiscifactoriasRio(piscifactorias);
                if (Simulador.monedas.getMonedas() >= cantidadPagar) {
                    piscifactorias.add(new PiscifactoriaRio(nombreNuevaPiscifactoria));
                    Simulador.monedas.pagar(cantidadPagar);
                    System.out.println("Añadida nueva piscifactoria de rio por " + cantidadPagar + " monedas");
                    Simulador.registros.escribirTranscripcion("Comprada la piscifactoría de río "
                            + nombreNuevaPiscifactoria + " por " + cantidadPagar + " monedas");
                    Simulador.registros.escribirLog("Comprada la piscifactoría de río "
                            + nombreNuevaPiscifactoria);
                } else {
                    System.out.println("No hay monedas suficientes para realizar esta operacion");
                }
                break;
            case 2:
                cantidadPagar = 2000 * contarPiscifactoriasMar(piscifactorias);
                if (Simulador.monedas.getMonedas() >= cantidadPagar) {
                    piscifactorias.add(new PiscifactoriaMar(nombreNuevaPiscifactoria));
                    Simulador.monedas.pagar(cantidadPagar);
                    System.out.println("Añadida nueva piscifactoria de mar por " + cantidadPagar + " monedas");
                    Simulador.registros.escribirTranscripcion("Comprada la piscifactoría de mar "
                            + nombreNuevaPiscifactoria + " por " + cantidadPagar + " monedas");
                    Simulador.registros.escribirLog("Comprada la piscifactoría de mar "
                            + nombreNuevaPiscifactoria);
                } else {
                    System.out.println("No hay monedas suficientes para realizar esta operacion");
                }
                break;
        }
    }

    /**
     * Comprueba el número de piscifactorias de mar que hay en la lista
     * 
     * @param piscifactorias La lista de piscifactorias
     * @return El número de piscifactorias de mar que hay
     */
    public static int contarPiscifactoriasMar(List<Piscifactoria> piscifactorias) {
        int contadorPiscifactoriasMar = 0;
        for (Piscifactoria piscifactoria : piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaMar) {
                contadorPiscifactoriasMar++;
            }
        }
        return contadorPiscifactoriasMar;
    }

    /**
     * Comprueba el número de piscifactorias de rio que hay en la lista
     * 
     * @param piscifactorias La lista de piscifactorias
     * @return El número de piscifactorias de rio que hay
     */
    public static int contarPiscifactoriasRio(List<Piscifactoria> piscifactorias) {
        int contadorPiscifactoriasRio = 0;
        for (Piscifactoria piscifactoria : piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaRio) {
                contadorPiscifactoriasRio++;
            }
        }
        return contadorPiscifactoriasRio;
    }

    /**
     * Compra un Almacen central que permite guardar comida en él y distribuirla a
     * las piscifactorias
     */
    public static void comprarAlmacenCentral() {
        if (Simulador.monedas.getMonedas() >= 2000) {
            Simulador.monedas.pagar(2000);
            Simulador.almacenCentral.setActivo(true);
            System.out.println("Añadido Almacen central por 2000 monedas");
            Simulador.registros.escribirTranscripcion("Comprado el almacén central.");
            Simulador.registros.escribirLog("Comprado el almacén central.");
        } else {
            System.out.println("No hay monedas suficientes para realizar esta operacion");
        }
    }

    /**
     * Permite seleccionar una piscifactoria y comprar un tanque o mejorar el
     * almacen de comida
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void mejorarPiscifactoria(List<Piscifactoria> piscifactorias) {
        Scanner sc = new Scanner(System.in);
        Piscifactoria piscifactoria = selectPisc(piscifactorias);
        System.out.println("1.-Comprar tanque\n2.-Mejorar almacen");
        System.out.println("Seleccione una opción: ");
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1:
                if (piscifactoria instanceof PiscifactoriaRio) {
                    int cantidadPagar = 150 * piscifactoria.getTanques().size();
                    if (Simulador.monedas.getMonedas() >= cantidadPagar && piscifactoria.getTanques().size() < 10) {
                        Simulador.monedas.pagar(cantidadPagar);
                        piscifactoria.getTanques().add(new TanqueRio<>(piscifactoria.getTanques().size() + 1));
                        System.out.println("Añadido nuevo tanque a piscifactoria "
                                + piscifactoria.getNombrePiscifactoria() + " por " + cantidadPagar + " monedas");
                        Simulador.registros
                                .escribirTranscripcion("Comprado un tanque numero " + piscifactoria.getTanques() +
                                        "de la piscifactoria " + piscifactoria.getNombrePiscifactoria());
                        Simulador.registros
                                .escribirLog("Comprado un tanque para la piscifactoria " + piscifactoria.getNombrePiscifactoria());
                    } else {
                        System.out.println("No hay monedas suficientes para realizar esta operacion");
                    }
                } else {
                    int cantidadPagar = 600 * piscifactoria.getTanques().size();
                    if (Simulador.monedas.getMonedas() >= cantidadPagar && piscifactoria.getTanques().size() < 10) {
                        Simulador.monedas.pagar(cantidadPagar);
                        piscifactoria.getTanques().add(new TanqueRio<>(piscifactoria.getTanques().size() + 1));
                        System.out.println("Añadido nuevo tanque a piscifactoria "
                                + piscifactoria.getNombrePiscifactoria() + " por " + cantidadPagar + " monedas");
                        Simulador.registros
                                .escribirTranscripcion("Comprado un tanque numero " + piscifactoria.getTanques() +
                                        "de la piscifactoria " + piscifactoria.getNombrePiscifactoria());
                        Simulador.registros
                                        .escribirLog("Comprado un tanque para la piscifactoria " + piscifactoria.getNombrePiscifactoria());
                    } else {
                        System.out.println("No hay monedas suficientes para realizar esta operacion");
                    }
                }
                break;
            case 2:
                if (piscifactoria instanceof PiscifactoriaRio) {
                    if (Simulador.monedas.getMonedas() >= 100 && piscifactoria.getContadorMejoraAlmacen() < 10) {
                        Simulador.monedas.pagar(100);
                        piscifactoria.getAlmacen().setEspacioMaximo(piscifactoria.getAlmacen().getEspacioMaximo() + 25);
                        System.out.println("Mejorado el almacen de comida de piscifactoria "
                                + piscifactoria.getNombrePiscifactoria() + " por 100 monedas para un espacio maximo de "
                                + piscifactoria.getAlmacen().getEspacioMaximo());
                        Simulador.registros.escribirTranscripcion(
                                "Mejorada la piscifactoría " + piscifactoria.getNombrePiscifactoria()
                                        + " aumentando su capacidad de comida hasta un total de "
                                        + piscifactoria.getAlmacen().getEspacioMaximo() + " por 100 monedas.");
                        Simulador.registros.escribirLog(
                                "Mejorada la piscifactoría " + piscifactoria.getNombrePiscifactoria()
                                        + " aumentando su capacidad de comida");
                    } else {
                        System.out.println("No hay monedas suficientes para realizar esta operacion");
                    }
                } else {
                    if (Simulador.monedas.getMonedas() >= 200 && piscifactoria.getContadorMejoraAlmacen() < 10) {
                        Simulador.monedas.pagar(200);
                        piscifactoria.getAlmacen()
                                .setEspacioMaximo(piscifactoria.getAlmacen().getEspacioMaximo() + 100);
                        System.out.println("Mejorado el almacen de comida de piscifactoria "
                                + piscifactoria.getNombrePiscifactoria() + " por 200 monedas para un espacio maximo de "
                                + piscifactoria.getAlmacen().getEspacioMaximo());
                        Simulador.registros.escribirTranscripcion(
                                "Mejorada la piscifactoría " + piscifactoria.getNombrePiscifactoria()
                                        + " aumentando su capacidad de comida hasta un total de "
                                        + piscifactoria.getAlmacen().getEspacioMaximo() + " por 200 monedas.");
                        Simulador.registros.escribirLog(
                                "Mejorada la piscifactoría " + piscifactoria.getNombrePiscifactoria()
                                        + " aumentando su capacidad de comida");
                    } else {
                        System.out.println("No hay monedas suficientes para realizar esta operacion");
                    }
                }
                break;
        }
    }

    /**
     * Añade 4 peces gratuitamente y de forma aleatoria
     * 
     * @param piscifactorias La lista de piscifactorias
     */
    public static void caso98(List<Piscifactoria> piscifactorias) {
        ArrayList<Tanque> tanquesDisponibles = new ArrayList<>();
        for (Piscifactoria piscifactoria : piscifactorias) {
            if (piscifactoria instanceof PiscifactoriaMar) {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    if (tanque.getPeces().size() < tanque.getEspacio()) {
                        tanquesDisponibles.add(tanque);
                    }
                }
            } else {
                ArrayList<Tanque<? extends Pez>> tanques = piscifactoria.getTanques();
                for (Tanque tanque : tanques) {
                    if (tanque.getPeces().size() < tanque.getEspacio()) {
                        tanquesDisponibles.add(tanque);
                    }
                }
            }
        }
        Random rd = new Random();
        ArrayList<PezRio> pecesRio = new ArrayList<>();
        pecesRio.add(new Carpa(false));
        pecesRio.add(new CarpaPlateada(false));
        pecesRio.add(new CarpinTresEspinas(false));
        pecesRio.add(new LucioDelNorte(false));
        pecesRio.add(new Pejerrey(false));
        pecesRio.add(new PercaEuropea(false));
        pecesRio.add(new SalmonChinook(false));
        pecesRio.add(new TilapiaDelNilo(false));
        pecesRio.add(new Bagre(false));
        pecesRio.add(new Dorada(false));
        pecesRio.add(new LubinaEuropea(false));
        pecesRio.add(new SalmonAtlantico(false));
        pecesRio.add(new TruchaArcoiris(false));
        ArrayList<PezMar> pecesMar = new ArrayList<>();
        pecesMar.add(new Abadejo(false));
        pecesMar.add(new ArenqueAtlantico(false));
        pecesMar.add(new Besugo(false));
        pecesMar.add(new Caballa(false));
        pecesMar.add(new Cobia(false));
        pecesMar.add(new Corvina(false));
        pecesMar.add(new LenguadoEuropeo(false));
        pecesMar.add(new LubinaRayada(false));
        pecesMar.add(new Robalo(false));
        pecesMar.add(new Rodaballo(false));
        pecesMar.add(new Sargo(false));
        pecesMar.add(new Bagre(false));
        pecesMar.add(new Dorada(false));
        pecesMar.add(new LubinaEuropea(false));
        pecesMar.add(new SalmonAtlantico(false));
        pecesMar.add(new TruchaArcoiris(false));

        for (int i = 0; i < 4; i++) {
            int opcion = rd.nextInt(tanquesDisponibles.size());
            if (tanquesDisponibles.get(opcion).getPeces().isEmpty()) {
                if (tanquesDisponibles.get(opcion) instanceof TanqueRio) {
                    int eleccion = rd.nextInt(pecesRio.size());
                    Pez pez = (Pez) pecesRio.get(eleccion);
                    tanquesDisponibles.get(opcion).getPeces().add(pez);
                    System.out.println("Añadido " + pez.getNombre() + " a tanque "
                            + tanquesDisponibles.get(opcion).getNumeroTanque());
                } else {
                    int eleccion = rd.nextInt(pecesMar.size());
                    Pez pez = (Pez) pecesMar.get(eleccion);
                    tanquesDisponibles.get(opcion).getPeces().add(pez);
                    System.out.println("Añadido " + pez.getNombre() + " a tanque "
                            + tanquesDisponibles.get(opcion).getNumeroTanque());
                }
            } else {
                Tanque tanqueSeleccionado = tanquesDisponibles.get(opcion);
                Pez pezExistente = (Pez) tanqueSeleccionado.getPeces().get(0);
                ArrayList<Pez> nuevosPeces = new ArrayList<>();
                pezExistente.nuevoPez(tanqueSeleccionado.getPeces(), nuevosPeces);
                tanqueSeleccionado.getPeces().addAll(nuevosPeces);
                System.out.println("Añadido " + pezExistente.getNombre() + " a tanque "
                        + tanquesDisponibles.get(opcion).getNumeroTanque());
            }
        }
    }
}
