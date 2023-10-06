package piscifactoria;

import java.util.ArrayList;

public class PiscifactoriaRio extends Piscifactoria{

    public PiscifactoriaRio(String nombrePiscifactoria) {
        super(nombrePiscifactoria);
        this.tanques.add(0, new TanqueRio(1));
    }

    
    
}
