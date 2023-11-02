package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class Caballa extends Carnivoro implements PezMar {
    public Caballa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CABALLA);
    }

}