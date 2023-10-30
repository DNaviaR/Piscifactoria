package piscifactoria;

import pez.PezMar;
/**
 * Clase que representa un tanque de peces de mar.
 */
public class TanqueMar extends Tanque implements PezMar {
    /**
     * Constructor de la clase.
     *
     * @param numeroTanque El número del tanque.
     */
    public TanqueMar(int numeroTanque) {
        super(numeroTanque);
        espacio = 100;
    }
}
