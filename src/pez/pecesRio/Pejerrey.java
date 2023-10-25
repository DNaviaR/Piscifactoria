package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class Pejerrey extends Pez implements PezRio {
    public Pejerrey(boolean sexo) {
        super(sexo, AlmacenPropiedades.PEJERREY);
    }

}