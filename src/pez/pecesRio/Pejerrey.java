package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;


public class Pejerrey extends Carnivoro implements PezRio {
    public Pejerrey(boolean sexo) {
        super(sexo, AlmacenPropiedades.PEJERREY);
    }

}