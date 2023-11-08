package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Omnivoro;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Sargo
 * Extiende de la clase Omnivoro, que representa a los peces omnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Sargo extends Omnivoro implements PezMar {
    /**
     * Crea un nuevo pez Sargo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Sargo(boolean sexo) {
        super(sexo, AlmacenPropiedades.SARGO);
    }

    @Override
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, pisci);
    }
}