package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Salmón Chinook
 * Extiende de la clase Carnivoro, que representa a los peces carrnívoros, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class SalmonChinook extends Carnivoro implements PezRio {
    /**
     * Crea un nuevo pez SalmonChinook con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public SalmonChinook(boolean sexo) {
        super(sexo, AlmacenPropiedades.SALMON_CHINOOK);
    }

}