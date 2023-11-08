package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVoraz;
import piscifactoria.Piscifactoria;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Rodaballo
 * Extiende de la clase CarnivoroVoraz, que representa a los peces carnívoros
 * voraces, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Rodaballo extends CarnivoroVoraz implements PezMar {
    /**
     * Crea un nuevo pez Rodaballo con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Rodaballo(boolean sexo) {
        super(sexo, AlmacenPropiedades.RODABALLO);
    }

    @Override
    public void grow(List<Pez> peces, int espacio, Piscifactoria pisci) {
        this.comer(peces, pisci);
        super.grow(peces, espacio, pisci);
    }
}