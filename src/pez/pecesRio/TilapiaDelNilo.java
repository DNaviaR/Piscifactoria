package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Filtrador;
import propiedades.AlmacenPropiedades;


public class TilapiaDelNilo extends Filtrador implements PezRio {
    public TilapiaDelNilo(boolean sexo) {
        super(sexo, AlmacenPropiedades.TILAPIA_NILO);
    }

}