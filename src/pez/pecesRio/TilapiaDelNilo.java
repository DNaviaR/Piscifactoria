package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class TilapiaDelNilo extends Pez implements PezRio {
    public TilapiaDelNilo(boolean sexo) {
        super(sexo, AlmacenPropiedades.TILAPIA_NILO);
    }

}