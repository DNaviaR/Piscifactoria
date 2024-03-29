package piscifactoria;

import almacen.Almacen;
import pez.Pez;
import pez.PezMar;

/**
 * Clase que representa una piscifactoría de peces de mar.
 */
public class PiscifactoriaMar extends Piscifactoria implements PezMar {
    /**
     * Constructor de la clase.
     *
     * @param nombrePiscifactoria El nombre de la piscifactoría.
     */
    public PiscifactoriaMar(String nombrePiscifactoria) {
        super(nombrePiscifactoria);
        this.tanques.add(0, new TanqueMar<Pez>(1));
        this.almacen=new Almacen(100);
        this.tipo=1;
    }
}
