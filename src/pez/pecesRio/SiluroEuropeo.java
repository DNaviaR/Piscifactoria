package pez.pecesRio;

import pez.PezRio;
import pez.Alimentación.CarnivoroLongevoVoraz;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Siluro Europeo
 * Extiende de la clase CarnivoroLongevoVoraz, que representa a los peces
 * carnívoros
 * longevos voraces, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class SiluroEuropeo extends CarnivoroLongevoVoraz implements PezRio {
    /**
     * Crea un nuevo pez SiluroEuropeo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public SiluroEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SILURO_EUROPEO);
    }

}