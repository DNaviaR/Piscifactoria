package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class LenguadoEuropeo extends Pez implements PezMar {
    public LenguadoEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.LENGUADO_EUROPEO);
    }

}