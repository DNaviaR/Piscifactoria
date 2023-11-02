package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class Besugo extends Carnivoro implements PezMar {
    public Besugo(boolean sexo) {
        super(sexo, AlmacenPropiedades.BESUGO);
    }

}
