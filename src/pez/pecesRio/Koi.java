package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroLongevo;
import propiedades.AlmacenPropiedades;


public class Koi extends OmnivoroLongevo implements PezRio {
    public Koi(boolean sexo) {
        super(sexo, AlmacenPropiedades.KOI);
    }

}