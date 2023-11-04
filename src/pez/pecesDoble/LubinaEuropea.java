package pez.pecesDoble;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Lubina Europea
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * las interfaces PezMar y PezRio, que representan a los peces de mar y de rio
 */
public class LubinaEuropea extends Carnivoro implements PezMar, PezRio {
    /**
     * Crea un nuevo pez LubinaEuropea con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public LubinaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_EUROPEA);
    }

}
