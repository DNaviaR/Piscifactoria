package pez.Alimentación;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces comedidos.
 * 75% de no consumir comida.
 * 
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Comedido extends Pez {
    /**
     * Crea un nuevo pez comido con el sexo especificado y los datos de peces
     * proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public Comedido(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     * 
     * @param comida La cantidad de comida disponible.
     */
    public void comer(int comida) {
        double aleatorio = Math.random();
        if (aleatorio <= 0.75) {
            this.setAlimentado(true);
        } else {
            if (comida > 0) {
                comida--;
                this.setAlimentado(true);
            }
        }
    }
}
