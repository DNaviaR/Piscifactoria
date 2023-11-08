package pez.pecesDoble;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.CarnivoroVoraz;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Bagre
 * Extiende de la clase CarnivoroVoraz, que representa a los peces carnivoros
 * voraces, e implementa
 * las interfaces PezMar y PezRio, que representan a los peces de mar y de rio
 */
public class Bagre extends CarnivoroVoraz implements PezMar, PezRio {
    /**
     * Crea un nuevo pez Bagre con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Bagre(boolean sexo) {
        super(sexo, AlmacenPropiedades.BAGRE_CANAL);
    }

    @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }
}