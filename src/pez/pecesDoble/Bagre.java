package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.CarnivoroVoraz;
import propiedades.AlmacenPropiedades;

public class Bagre extends CarnivoroVoraz implements PezMar, PezRio {
    public Bagre(boolean sexo) {
        super(sexo, AlmacenPropiedades.BAGRE_CANAL);
    }

}

