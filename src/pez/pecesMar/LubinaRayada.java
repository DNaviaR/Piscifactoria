package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class LubinaRayada extends Carnivoro implements PezMar {
    public LubinaRayada(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_RAYADA);
    }

}