package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Filtrador;
import propiedades.AlmacenPropiedades;

public class ArenqueAtlantico extends Filtrador implements PezMar {
    public ArenqueAtlantico(boolean sexo) {
        super(sexo, AlmacenPropiedades.ARENQUE_ATLANTICO);
    }

}