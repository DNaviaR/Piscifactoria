package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroComedido;
import propiedades.AlmacenPropiedades;


public class CarpinTresEspinas extends OmnivoroComedido implements PezRio {
    public CarpinTresEspinas(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPIN_TRES_ESPINAS);
    }

}