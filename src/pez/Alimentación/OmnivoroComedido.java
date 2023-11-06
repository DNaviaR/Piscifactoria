package pez.Alimentación;

import java.util.List;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * 
 * Clase abstracta que representa a los peces omnívoros comedidos.
 * 
 * Extiende a la clase Omnivoro, que representa a los peces omnívoros en
 * general.
 */
public abstract class OmnivoroComedido extends Omnivoro {
    /**
     * Crea un nuevo pez omnívoro con el sexo especificado y los datos de peces
     * proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public OmnivoroComedido(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param peces  Una lista de peces en el tanque.
     * @param comida La cantidad de comida disponible.
     */
    public void comer(List<Pez> peces, int comida) {
        double aleatorio = Math.random();
        if (aleatorio > 0.25) {
            aleatorio = Math.random();
            if (aleatorio <= 0.75) {
                this.setAlimentado(true);
            } else {
                Pez pezMuerto = buscarPezMuertoEnTanque(peces);
                // Si encuentra un pez muerto, lo come.
                if (pezMuerto != null) {
                    comerPezMuerto(pezMuerto);
                }
                // Si no hay pez muerto, consume comida
                else {
                    if (comida > 0) {
                        comida--;
                        this.setAlimentado(true);
                    }
                }
            }
        } else {
            this.setAlimentado(true);
        }
    }
}
