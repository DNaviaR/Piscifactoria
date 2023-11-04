package pez.pecesRio;

import pez.Pez;
import pez.PezRio;
import pez.Alimentación.Filtrador;
import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

/**
 * Clase que representa el tipo de pez Carpa Plateada
 * Extiende de la clase Filtrador, que representa a los peces filtradores, e
 * implementa
 * la interfaz PezRio, que representa a los peces de río
 */
public class CarpaPlateada extends Filtrador implements PezRio {
    /**
     * Crea un nuevo pez CarpaPlateada con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public CarpaPlateada(boolean sexo) {
        super(sexo, AlmacenPropiedades.CARPA_PLATEADA);
    }

}
