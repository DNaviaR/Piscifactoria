package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Robalo extends Pez implements PezMar {
    public Robalo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ROBALO);
    }

}