package pez;

import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public class CarpaPlateada extends Pez {
    PecesDatos carpaPlateada=AlmacenPropiedades.CARPA_PLATEADA;
    public CarpaPlateada(char sexo, PecesDatos carpaPlateada) {
        super(sexo, carpaPlateada);
    }
}
