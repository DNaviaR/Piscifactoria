package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class CarpaPlateada extends Pez implements PezRio {
    public CarpaPlateada(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA_PLATEADA);
    }

}
