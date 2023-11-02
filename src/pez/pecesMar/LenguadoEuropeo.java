package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class LenguadoEuropeo extends Carnivoro implements PezMar {
    public LenguadoEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.LENGUADO_EUROPEO);
    }

}