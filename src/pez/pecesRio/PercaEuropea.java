package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.CarnivoroActivo;
import propiedades.AlmacenPropiedades;


public class PercaEuropea extends CarnivoroActivo implements PezRio {
    public PercaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.PERCA_EUROPEA);
    }

}