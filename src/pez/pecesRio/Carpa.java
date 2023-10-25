package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class Carpa extends Pez implements PezRio {
    public Carpa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA);
    }

}