package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Filtrador;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;


public class CarpaPlateada extends Filtrador implements PezRio {

    public CarpaPlateada(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA_PLATEADA);
    }


}
