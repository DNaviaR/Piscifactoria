package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Caballa
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Caballa extends Carnivoro implements PezMar {
    /**
     * Crea un nuevo pez Caballa con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Caballa(boolean sexo) {
        super(sexo, AlmacenPropiedades.CABALLA);
    }

    @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }
}