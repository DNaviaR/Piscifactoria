package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import propiedades.AlmacenPropiedades;

public class Dorada extends Pez implements PezMar, PezRio {
    public Dorada(boolean sexo) {
        super(sexo, AlmacenPropiedades.DORADA);
    }

}

