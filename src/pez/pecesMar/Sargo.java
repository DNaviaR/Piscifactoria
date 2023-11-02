package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Omnivoro;
import propiedades.AlmacenPropiedades;

public class Sargo extends Omnivoro implements PezMar {
    public Sargo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SARGO);
    }

}