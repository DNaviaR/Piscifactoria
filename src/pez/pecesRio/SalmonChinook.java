package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;


public class SalmonChinook extends Carnivoro implements PezRio {
    public SalmonChinook(boolean sexo) {
        super(sexo, AlmacenPropiedades.SALMON_CHINOOK);
    }

}