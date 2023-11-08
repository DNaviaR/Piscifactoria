package pez.pecesRio;

import java.util.List;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.OmnivoroComedido;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Carpín Tres Espinas
 * Extiende de la clase OmnivoroComedido, que representa a los peces omnívoros
 * comedidos, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class CarpinTresEspinas extends OmnivoroComedido implements PezRio {
    /**
     * Crea un nuevo pez CarpinTresEspinas con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public CarpinTresEspinas(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPIN_TRES_ESPINAS);
    }

   @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }
}