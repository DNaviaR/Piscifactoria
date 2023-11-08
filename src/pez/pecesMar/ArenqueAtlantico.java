package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Filtrador;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Arenque Atlantico
 * Extiende de la clase Filtrador, que representa a los peces filtradores, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class ArenqueAtlantico extends Filtrador implements PezMar {
    /**
     * Crea un nuevo pez ArenqueAtlantico con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public ArenqueAtlantico(boolean sexo) {
        super(sexo, AlmacenPropiedades.ARENQUE_ATLANTICO);
    }

    @Override
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        this.comer(pisci);
        super.grow(peces, pisci);
    }
}