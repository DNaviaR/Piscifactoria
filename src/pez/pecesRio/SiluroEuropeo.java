package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.CarnivoroLongevoVoraz;
import propiedades.AlmacenPropiedades;


public class SiluroEuropeo extends CarnivoroLongevoVoraz implements PezRio {
    public SiluroEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SILURO_EUROPEO);
    }

}