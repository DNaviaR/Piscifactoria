package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class LubinaEuropea extends Carnivoro implements PezMar, PezRio {
    public LubinaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_EUROPEA);
    }

}
