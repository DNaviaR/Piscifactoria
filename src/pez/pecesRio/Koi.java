package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class Koi extends Pez implements PezRio {
    public Koi(boolean sexo) {
        super(sexo, AlmacenPropiedades.KOI);
    }

}