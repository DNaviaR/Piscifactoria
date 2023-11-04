package pez.Alimentación;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces filtradores.
 * Al llegar a la edad óptima, tienen un 10% de seguir otro día más, aumentando
 * su precio de venta en 5 monedas.
 *
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Longevo extends Pez {

    public Longevo(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    public void crecer() {// Pendiente de modificar
        double aleatorio = Math.random();
        if (aleatorio < 0.10) {
            this.getPecesDatos().getOptimo();
        }
    }
}
