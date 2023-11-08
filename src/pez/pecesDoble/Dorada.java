package pez.pecesDoble;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Omnivoro;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Dorada
 * Extiende de la clase Omnivoro, que representa a los peces omnívoros, e
 * implementa
 * las interfaces PezMar y PezRio, que representan a los peces de mar y de rio
 */
public class Dorada extends Omnivoro implements PezMar, PezRio {
    /**
     * Crea un nuevo pez Dorada con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Dorada(boolean sexo) {
        super(sexo, AlmacenPropiedades.DORADA);
    }

    @Override
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, pisci);
    }
}
