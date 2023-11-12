package pez.Alimentación;

import java.util.List;

import pez.Pez;
import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;
/**
 * 
 * Clase abstracta que representa a los peces omnívoros voraces.
 * 
 * Extiende a la clase Omnivoro, que representa a los peces omnívoros en
 * general.
 */
public abstract class OmnivoroVoraz extends Omnivoro {
    /**
     * Crea un nuevo pez omnívoro con el sexo especificado y los datos de peces
     * proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public OmnivoroVoraz(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     *
     * @param peces  Una lista de peces en el tanque.
     * @param comida La cantidad de comida disponible.
     */
    public void comer(List<Pez> peces, Piscifactoria pisci) {
        int comida=pisci.getAlmacen().getEspacioOcupado();
        double aleatorio = Math.random();
        if (aleatorio > 0.25) {
            Pez pezMuerto = buscarPezMuertoEnTanque(peces);
            // Si encuentra un pez muerto, lo come.
            if (pezMuerto != null) {
                comerPezMuerto(pezMuerto,peces);
            }
            // Si no hay pez muerto, consume comida
            else {
                if (comida > 1) {
                    comida -= 2;
                    this.setAlimentado(true);
                }
            }
        } else {
            this.setAlimentado(true);
        }
        pisci.getAlmacen().setEspacioOcupado(comida);
    }
}
