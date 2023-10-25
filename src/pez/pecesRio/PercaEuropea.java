package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class PercaEuropea extends Pez implements PezRio {
    public PercaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.PERCA_EUROPEA);
    }

}