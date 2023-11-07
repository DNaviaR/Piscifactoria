package piscifactoria;

import pez.Pez;
import pez.PezRio;
/**
 * Clase que representa un tanque de peces de rio.
 */
public class TanqueRio<T extends Pez> extends Tanque<T> implements PezRio{
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
