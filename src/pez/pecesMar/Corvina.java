package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import propiedades.AlmacenPropiedades;

public class Corvina extends Pez implements PezMar {
    public Corvina(boolean sexo) {
        super(sexo, AlmacenPropiedades.CORVINA);
    }

}
