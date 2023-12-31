package pez.pecesRio;

import java.util.List;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroLongevo;
import piscifactoria.Piscifactoria;
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

    @Override
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, pisci);
    }

}