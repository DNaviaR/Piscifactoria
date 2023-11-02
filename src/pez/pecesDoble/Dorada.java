package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Omnivoro;
import propiedades.AlmacenPropiedades;

public class Dorada extends Omnivoro implements PezMar, PezRio {
    public Dorada(boolean sexo) {
        super(sexo, AlmacenPropiedades.DORADA);
    }

}

