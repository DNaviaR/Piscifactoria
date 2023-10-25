package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import propiedades.AlmacenPropiedades;

public class TruchaArcoiris extends Pez implements PezMar, PezRio {
    public TruchaArcoiris(boolean sexo) {
        super(sexo, AlmacenPropiedades.TRUCHA_ARCOIRIS);
    }

}
