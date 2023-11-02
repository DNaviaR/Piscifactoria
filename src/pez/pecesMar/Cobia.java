package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVorazComedido;
import propiedades.AlmacenPropiedades;

public class Cobia extends CarnivoroVorazComedido implements PezMar {
    public Cobia(boolean sexo) {
        super(sexo, AlmacenPropiedades.COBIA);
    }

}
