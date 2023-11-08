package pez.Alimentación;

import pez.Pez;
import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces filtradores.
 * 50% de posibilidades de no consumir comida.
 *
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Filtrador extends Pez {
    /**
     * Crea un nuevo pez activo con el sexo especificado y los datos de peces
     * proporcionados.
     *
     * @param sexo El sexo del pez.
     * @param pc   Los datos del pez.
     */
    public Filtrador(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param comida La cantidad de comida disponible.
     */
    public void comer(Piscifactoria pisci) {
        int comida=pisci.getAlmacen().getEspacioOcupado();
        double aleatorio = Math.random();
        if (aleatorio <= 0.5) {
            this.setAlimentado(true);
        } else {
            if (comida > 0) {
                comida--;
                this.setAlimentado(true);
            }
        }
        pisci.getAlmacen().setEspacioOcupado(comida);
    }
}
