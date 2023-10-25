package piscifactoria;

import almacen.Almacen;
import pez.PezRio;

public class PiscifactoriaRio extends Piscifactoria implements PezRio {

    public PiscifactoriaRio(String nombrePiscifactoria) {
        super(nombrePiscifactoria);
        this.tanques.add(0, new TanqueRio(1));
        this.almacen=new Almacen(25);
    }
}
