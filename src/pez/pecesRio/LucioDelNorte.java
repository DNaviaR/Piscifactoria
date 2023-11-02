package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.CarnivoroActivo;
import propiedades.AlmacenPropiedades;


public class LucioDelNorte extends CarnivoroActivo implements PezRio {
    public LucioDelNorte(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUCIO_NORTE);
    }

}