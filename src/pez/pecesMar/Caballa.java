package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Caballa extends Pez implements PezMar {
    public Caballa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CABALLA);
    }

}