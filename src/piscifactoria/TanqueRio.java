package piscifactoria;

import pez.PezRio;

public class TanqueRio extends Tanque implements PezRio{

    public TanqueRio(int numeroTanque) {
        super(numeroTanque);
        espacio=25;
    }
    
}
