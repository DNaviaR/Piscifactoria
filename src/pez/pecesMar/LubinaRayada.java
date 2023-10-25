package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class LubinaRayada extends Pez implements PezMar {
    public LubinaRayada(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_RAYADA);
    }

}