package pez.Alimentación;

import java.util.List;

import pez.Pez;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces carnívoros voraces comedidos.
 * 
 * Extiende a la clase CarnivoroVoraz, que representa a los peces carnívoros
 * voraces en general.
 */
public abstract class CarnivoroVorazComedido extends CarnivoroVoraz {
    /**
     * Crea un nuevo pez carnívoro voraz comido con el sexo especificado y los datos
     * de peces proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public CarnivoroVorazComedido(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     * 
     * @param peces  Una lista de peces en el tanque.
     * 
     * @param comida La cantidad de comida disponible.
     */
    @Override
    public void comer(List<Pez> peces, int comida) {
        Pez pezMuerto = buscarPezMuertoEnTanque(peces);

        // Si encuentra un pez muerto, lo come.
        if (pezMuerto != null) {
            comerPezMuerto(pezMuerto);
        }
        // Si no hay pez muerto, consume comida
        else {
            double aleatorio = Math.random();
            if (aleatorio <= 0.75) {
                this.setAlimentado(true);
            } else {
                if (comida > 1) {
                    comida -= 2;
                    this.setAlimentado(true);
                }
            }
        }
    }
}
