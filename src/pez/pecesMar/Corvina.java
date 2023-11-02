package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVoraz;
import propiedades.AlmacenPropiedades;

public class Corvina extends CarnivoroVoraz implements PezMar {
    public Corvina(boolean sexo) {
        super(sexo, AlmacenPropiedades.CORVINA);
    }

}
