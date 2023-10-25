package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import propiedades.AlmacenPropiedades;

public class SalmonAtlantico extends Pez implements PezMar, PezRio {
    public SalmonAtlantico(boolean sexo) {
        super(sexo, AlmacenPropiedades.SALMON_ATLANTICO);
    }

}
