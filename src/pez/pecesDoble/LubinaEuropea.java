package pez.pecesDoble;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.PezRio;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Lubina Europea
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * las interfaces PezMar y PezRio, que representan a los peces de mar y de rio
 */
public class LubinaEuropea extends Carnivoro implements PezMar, PezRio {
    /**
     * Crea un nuevo pez LubinaEuropea con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public LubinaEuropea(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_EUROPEA);
    }

    @Override
    public void grow(List<Pez> peces, int comida, int espacio) {
        this.comer(peces, comida);
        super.grow(peces, comida, espacio);
    }
}
