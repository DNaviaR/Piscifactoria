package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

public class Robalo extends Carnivoro implements PezMar {
    public Robalo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ROBALO);
    }

}