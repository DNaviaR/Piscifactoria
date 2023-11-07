package pez.pecesRio;

import java.util.List;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Filtrador;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Tilapia del Nilo
 * Extiende de la clase Filtrador, que representa a los peces filtradores, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class TilapiaDelNilo extends Filtrador implements PezRio {
    /**
     * Crea un nuevo pez TilapiaDelNilo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public TilapiaDelNilo(boolean sexo) {
        super(sexo, AlmacenPropiedades.TILAPIA_NILO);
    }

    @Override
    public void grow(List<Pez> peces, int comida, int espacio) {
        this.comer(comida);
        super.grow(peces, comida, espacio);
    }
}