package pez.pecesRio;

import java.util.List;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroVoraz;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Carpa
 * Extiende de la clase OmnivoroVoraz, que representa a los peces omnívoros
 * voraces, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class Carpa extends OmnivoroVoraz implements PezRio {
    /**
     * Crea un nuevo pez Carpa con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Carpa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA);
    }

    @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }

}