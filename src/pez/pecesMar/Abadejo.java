package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Abadejo extends Pez implements PezMar {
    public Abadejo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ABADEJO);
    }

}
