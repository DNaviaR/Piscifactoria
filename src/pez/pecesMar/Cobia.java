package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.CarnivoroVorazComedido;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Cobia
 * Extiende de la clase CarnivoroVorazComedido, que representa a los peces
 * carnívoros
 * voraces comedidos, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class Cobia extends CarnivoroVorazComedido implements PezMar {
    /**
     * Crea un nuevo pez Cobia con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public Cobia(boolean sexo) {
        super(sexo, AlmacenPropiedades.COBIA);
    }

    @Override
    public void grow(List<Pez> peces, int comida, int espacio) {
        this.comer(peces, comida);
        super.grow(peces, comida, espacio);
    }
}
