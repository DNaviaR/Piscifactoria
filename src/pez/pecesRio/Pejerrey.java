package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Pejerrey
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class Pejerrey extends Carnivoro implements PezRio {
    /**
     * Crea un nuevo pez Pejerrey con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Pejerrey(boolean sexo) {
        super(sexo, AlmacenPropiedades.PEJERREY);
    }

}