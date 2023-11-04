package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Trucha Arcoiris
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * las interfaces PezMar y PezRio, que representan a los peces de mar y de rio
 */
public class TruchaArcoiris extends Carnivoro implements PezMar, PezRio {
    /**
     * Crea un nuevo pez TruchaArcoiris con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public TruchaArcoiris(boolean sexo) {
        super(sexo, AlmacenPropiedades.TRUCHA_ARCOIRIS);
    }

}
