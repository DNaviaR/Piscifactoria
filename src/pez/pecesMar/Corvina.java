package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVoraz;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Corvina
 * Extiende de la clase CarnivoroVoraz, que representa a los peces carnívoros
 * voraces, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Corvina extends CarnivoroVoraz implements PezMar {
    /**
     * Crea un nuevo pez Corvina con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Corvina(boolean sexo) {
        super(sexo, AlmacenPropiedades.CORVINA);
    }

}
