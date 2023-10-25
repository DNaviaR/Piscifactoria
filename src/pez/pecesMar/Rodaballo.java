package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Rodaballo extends Pez implements PezMar {
    public Rodaballo(boolean sexo) {
        super(sexo, AlmacenPropiedades.RODABALLO);
    }

}