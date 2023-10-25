package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class SiluroEuropeo extends Pez implements PezRio {
    public SiluroEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SILURO_EUROPEO);
    }

}