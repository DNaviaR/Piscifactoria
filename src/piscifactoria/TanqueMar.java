package piscifactoria;

import pez.Pez;
import pez.PezMar;
/**
 * Clase que representa un tanque de peces de mar.
 */
public class TanqueMar<T extends Pez> extends Tanque<T> implements PezMar {
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
