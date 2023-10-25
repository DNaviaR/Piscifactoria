package piscifactoria;

import almacen.Almacen;
import pez.PezMar;

public class PiscifactoriaMar extends Piscifactoria implements PezMar {

    public PiscifactoriaMar(String nombrePiscifactoria) {
        super(nombrePiscifactoria);
        this.tanques.add(0, new TanqueMar(1));
        this.almacen=new Almacen(100);
    }
}
