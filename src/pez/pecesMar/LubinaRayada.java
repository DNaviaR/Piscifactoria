package pez.pecesMar;

import java.util.List;

import pez.Pez;
import pez.PezMar;
import pez.Alimentación.Carnivoro;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa el tipo de pez Lubina Rayada
 * Extiende de la clase Carnivoro, que representa a los peces carnívoros, e
 * implementa
 * la interfaz PezMar, que representa a los peces de mar
 */
public class LubinaRayada extends Carnivoro implements PezMar {
    /**
     * Crea un nuevo pez LubinaRayada con el sexo especificado
     *
     * @param sexo El sexo del pez.
     */
    public LubinaRayada(boolean sexo) {
        super(sexo, AlmacenPropiedades.LUBINA_RAYADA);
    }

    @Override
    public void grow(List<Pez> peces, int comida,int espacio) {
        this.comer(peces, comida);
        super.grow(peces, comida, espacio);
    }
}