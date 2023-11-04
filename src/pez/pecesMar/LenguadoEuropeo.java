package pez.pecesMar;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Lenguado Europeo
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class LenguadoEuropeo extends Carnivoro implements PezMar {
    /**
     * Crea un nuevo pez LenguadoEuropeo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public LenguadoEuropeo(boolean sexo) {
        super(sexo, AlmacenPropiedades.LENGUADO_EUROPEO);
    }

}