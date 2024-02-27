package pez.Alimentación;

import java.util.List;

import pez.Pez;
import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;

/**
 * 
 * Clase abstracta que representa a los peces omnívoros.
 * Se pueden alimentar de peces muertos del tanque. 50% de eliminar al pez
 * muerto tras comer. 25% de no consumir comida.
 * 
 * Extiende a la clase Pez, que representa a los peces en general.
 */
public abstract class Omnivoro extends Pez {
    /**
     * Crea un nuevo pez omnívoro con el sexo especificado y los datos de peces
     * proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public Omnivoro(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param peces  Una lista de peces en el tanque.
     * @param comida La cantidad de comida disponible.
     */
    public void comer(List<Pez> peces, Piscifactoria pisci) {
        int comida = pisci.getAlmacen().getEspacioOcupado();
        double aleatorio = Math.random();
        if (aleatorio > 0.25) {
            Pez pezMuerto = buscarPezMuertoEnTanque(peces);
            // Si encuentra un pez muerto, lo come.
            if (pezMuerto != null) {
                comerPezMuerto(pezMuerto, peces);
            }
            // Si no hay pez muerto, consume comida
            else {
                if (comida > 0) {
                    comida--;
                    this.setAlimentado(true);
                }
            }
        } else {
            this.setAlimentado(true);
        }
        pisci.getAlmacen().setEspacioOcupado(comida);
    }

    /**
     * Busca un pez muerto en el tanque.
     *
     * @param peces Una lista de peces en el tanque.
     * @return El primer pez muerto encontrado, o null si no hay ninguno.
     */
    protected Pez buscarPezMuertoEnTanque(List<Pez> peces) {
        for (Pez pez : peces) {
            if (pez != null && !pez.isvivo()) {
                return pez;
            }
        }
        return null;
    }

    /**
     * Hace que el pez coma un pez muerto.
     *
     * @param pezMuerto El pez muerto que el pez va a comer.
     */
    protected void comerPezMuerto(Pez pezMuerto, List<Pez> peces) {
        double aleatorio = Math.random();
        if (aleatorio <= 0.5) {
            int index = peces.indexOf(pezMuerto);
            if (index != -1) {
                peces.set(index, null);
            }
        }
    }

}
