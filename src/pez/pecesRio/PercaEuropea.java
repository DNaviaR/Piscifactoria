package pez.pecesRio;

import java.util.List;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.CarnivoroActivo;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Perca Europea
 * Extiende de la clase CarnivoroActivo, que representa a los peces carnívoros
 * activos, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class PercaEuropea extends CarnivoroActivo implements PezRio {
    /**
     * Crea un nuevo pez PercaEuropea con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public PercaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.PERCA_EUROPEA);
    }

    @Override
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, pisci);
    }
}