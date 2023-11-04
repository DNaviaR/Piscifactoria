package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroLongevo;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Koi
 * Extiende de la clase OmnivoroLongevo, que representa a los peces omnívoros
 * longevos, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class Koi extends OmnivoroLongevo implements PezRio {
    /**
     * Crea un nuevo pez Koi con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Koi(boolean sexo) {
        super(sexo, AlmacenPropiedades.KOI);
    }

}