package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import propiedades.AlmacenPropiedades;


public class CarpinTresEspinas extends Pez implements PezRio {
    public CarpinTresEspinas(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPIN_TRES_ESPINAS);
    }

}