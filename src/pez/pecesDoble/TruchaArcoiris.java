package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class TruchaArcoiris extends Carnivoro implements PezMar, PezRio {
    public TruchaArcoiris(boolean sexo) {
        super(sexo, AlmacenPropiedades.TRUCHA_ARCOIRIS);
    }

}
