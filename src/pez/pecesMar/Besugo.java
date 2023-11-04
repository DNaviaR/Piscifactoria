package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Besugo
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Besugo extends Carnivoro implements PezMar {
    /**
     * Crea un nuevo pez Besugo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Besugo(boolean sexo) {
        super(sexo, AlmacenPropiedades.BESUGO);
    }

}
