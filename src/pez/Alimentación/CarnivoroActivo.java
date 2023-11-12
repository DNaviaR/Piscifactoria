package pez.Alimentación;

import java.util.List;

import pez.Pez;
import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa a los peces carnívoros activos.
 * 
 * Extiende a la clase Carnivoro, que representa a los peces carnívoros en
 * general.
 */
public abstract class CarnivoroActivo extends Carnivoro {
    /**
     * Crea un nuevo pez carnívoro activo con el sexo especificado y los datos de
     * peces proporcionados.
     * 
     * @param sexo El sexo del pez.
     * @param pc   Los datos de peces del pez.
     */
    public CarnivoroActivo(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
    }

    /**
     * Hace que el pez coma de la cantidad total de comida.
     * 
     * @param peces  Una lista de peces en el tanque.
     * @param comida La cantidad de comida disponible.
     */
    @Override
    public void comer(List<Pez> peces, Piscifactoria pisci) {
        int comida=pisci.getAlmacen().getEspacioOcupado();
        Pez pezMuerto = buscarPezMuertoEnTanque(peces);

        // Si encuentra un pez muerto, lo come.
        if (pezMuerto != null) {
            comerPezMuerto(pezMuerto,peces);
        }
        // Si no hay pez muerto, consume comida
        else {
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
        pisci.getAlmacen().setEspacioOcupado(comida);
    }

}
