package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Cobia extends Pez implements PezMar {
    public Cobia(boolean sexo) {
        super(sexo, AlmacenPropiedades.COBIA);
    }

}
