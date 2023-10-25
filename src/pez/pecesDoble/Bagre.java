package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import propiedades.AlmacenPropiedades;

public class Bagre extends Pez implements PezMar, PezRio {
    public Bagre(boolean sexo) {
        super(sexo, AlmacenPropiedades.BAGRE_CANAL);
    }

}

