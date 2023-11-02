package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroComedido;
import propiedades.AlmacenPropiedades;

public class Abadejo extends CarnivoroComedido implements PezMar {
    public Abadejo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ABADEJO);
    }

}
