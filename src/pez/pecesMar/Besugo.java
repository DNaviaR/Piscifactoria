package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Besugo extends Pez implements PezMar {
    public Besugo(boolean sexo) {
        super(sexo, AlmacenPropiedades.BESUGO);
    }

}
