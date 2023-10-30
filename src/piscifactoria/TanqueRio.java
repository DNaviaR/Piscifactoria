package piscifactoria;

import pez.PezRio;
/**
 * Clase que representa un tanque de peces de rio.
 */
public class TanqueRio extends Tanque implements PezRio{
    /**
     * Constructor de la clase.
     *
     * @param numeroTanque El número del tanque.
     */
    public TanqueRio(int numeroTanque) {
        super(numeroTanque);
        espacio=25;
    }
    
}
