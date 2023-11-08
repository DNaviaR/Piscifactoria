package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroComedido;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez abadejo
 * Extiende de la clase CarnivoroComedido, que representa a los peces carnívoros
 * comedidos, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Abadejo extends CarnivoroComedido implements PezMar {
    /**
     * Crea un nuevo pez Abadejo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Abadejo(boolean sexo) {
        super(sexo, AlmacenPropiedades.ABADEJO);
    }

    @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }
}
