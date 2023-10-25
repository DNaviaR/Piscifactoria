package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class LucioDelNorte extends Pez implements PezRio {
    public LucioDelNorte(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUCIO_NORTE);
    }

}