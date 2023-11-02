package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVoraz;
import propiedades.AlmacenPropiedades;

public class Rodaballo extends CarnivoroVoraz implements PezMar {
    public Rodaballo(boolean sexo) {
        super(sexo, AlmacenPropiedades.RODABALLO);
    }

}