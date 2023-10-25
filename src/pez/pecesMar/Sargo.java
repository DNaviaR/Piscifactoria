package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Sargo extends Pez implements PezMar {
    public Sargo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SARGO);
    }

}