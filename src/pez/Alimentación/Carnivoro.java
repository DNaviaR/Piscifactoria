package pez.Alimentación;

import java.util.List;

import pez.Pez;
import propiedades.PecesDatos;

public abstract class Carnivoro extends Pez {

    public Carnivoro(boolean sexo, PecesDatos pc) {
        super(sexo, pc);
        // TODO Auto-generated constructor stub
    }

    public void comer() {
        // Genera un número aleatorio entre 0 y 1.
        double aleatorio = Math.random();

        // Si el número aleatorio es menor o igual a 0.5, el pez carnivoro comerá un pez
        // muerto.
        if (aleatorio <= 0.5) {
            // Busca un pez muerto en el tanque.
            Pez pezMuerto = buscarPezMuertoEnTanque();

            // Si encuentra un pez muerto, lo come.
            if (pezMuerto != null) {
                comerPezMuerto(pezMuerto);
            }
        } else {
            // El pez carnivoro no consumió la comida.
        }
    }

    private Pez buscarPezMuertoEnTanque(List<Pez> peces) {
        for (Pez pez : peces) {
            if (!pez.isEstaVivo()) {
                return pez;
            }
        }
        return null;
    }

    private void comerPezMuerto(Pez pezMuerto) {
        // ...

        // Elimina el pez muerto del tanque.
        pezMuerto.eliminarDelTanque();
    }
}
