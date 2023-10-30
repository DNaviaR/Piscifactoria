package piscifactoria;

import almacen.Almacen;
import pez.PezRio;

/**
 * Clase que representa una piscifactoría de peces de rio.
 */
public class PiscifactoriaRio extends Piscifactoria implements PezRio {
    /**
     * Constructor de la clase.
     *
     * @param nombrePiscifactoria El nombre de la piscifactoría.
     */
    public PiscifactoriaRio(String nombrePiscifactoria) {
        super(nombrePiscifactoria);
        this.tanques.add(0, new TanqueRio(1));
        this.almacen = new Almacen(25);
    }
}
