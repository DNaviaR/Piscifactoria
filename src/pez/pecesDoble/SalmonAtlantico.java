package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class SalmonAtlantico extends Carnivoro implements PezMar, PezRio {
    public SalmonAtlantico(boolean sexo) {
        super(sexo, AlmacenPropiedades.SALMON_ATLANTICO);
    }

}
