package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroVoraz;
import propiedades.AlmacenPropiedades;


public class Carpa extends OmnivoroVoraz implements PezRio {
    public Carpa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA);
    }
}