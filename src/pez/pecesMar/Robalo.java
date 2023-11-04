package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Robalo
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Robalo extends Carnivoro implements PezMar {
    /**
     * Crea un nuevo pez Robalo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Robalo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ROBALO);
    }

}