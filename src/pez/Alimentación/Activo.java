package pez.Alimentación;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces activos.
 * 50% de comer 2 de alimento ese día.
 *
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Activo extends Pez {
    /**
     * Crea un nuevo pez activo con el sexo especificado y los datos de peces
     * proporcionados.
     *
     * @param sexo El sexo del pez.
     * @param pc   Los datos del pez.
     */
    public Activo(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param comida La cantidad de comida disponible.
     */
    public void comer(int comida) {
        double aleatorio = Math.random();
        if (aleatorio <= 0.5) {
            if (comida > 1) {
                comida -= 2;
                this.setAlimentado(true);
            }
        } else {
            if (comida > 0) {
                comida--;
                this.setAlimentado(true);
            }
        }
    }
}
