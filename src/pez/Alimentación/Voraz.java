package pez.Alimentación;

import java.util.List;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces voraces.
 * Come 2 de alimento.
 *
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Voraz extends Pez {
    /**
     * Crea un nuevo pez activo con el sexo especificado y los datos de peces
     * proporcionados.
     *
     * @param sexo El sexo del pez.
     * @param pc   Los datos del pez.
     */
    public Voraz(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param comida La cantidad de comida disponible.
     */
    public void comer(List<Pez> peces, int comida) {
        if (comida > 1) {
            comida -= 2;
            this.setAlimentado(true);
        }
    }
}
