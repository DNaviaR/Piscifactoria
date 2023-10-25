package piscifactoria;

import pez.PezMar;

public class TanqueMar extends Tanque implements PezMar {
    
    public TanqueMar(int numeroTanque) {
        super(numeroTanque);
        espacio = 100;
    }
}
