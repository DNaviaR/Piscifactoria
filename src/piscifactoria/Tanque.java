package piscifactoria;

import java.util.ArrayList;

import pez.Pez;

public abstract class Tanque {
    ArrayList<Pez> peces = new ArrayList<>();
    int espacio;
    int numeroTanque;

    public Tanque(int numeroTanque) {
        this.numeroTanque=numeroTanque;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    public int getNumeroTanque() {
        return numeroTanque;
    }

    public void setNumeroTanque(int numeroTanque) {
        this.numeroTanque = numeroTanque;
    }

    @Override
    public String toString() {
        return "Tanque [peces=" + peces + ", espacio=" + espacio + ", numeroTanque=" + numeroTanque + "]";
    }

    public void showStatus() {// Deberia funcionar
        System.out.println("--------------- Tanque" + numeroTanque + " ---------------");
        System.out.println("Ocupación " + peces.size() + "/" + espacio + " (" + ((peces.size() / espacio) * 100) + ")");
        int contadorVivos = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo()) {
                contadorVivos++;
            }
        }
        System.out.println("Peces vivos " + contadorVivos + "/" + peces.size() + " ("
                + ((contadorVivos / peces.size()) * 100) + ")");
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isAlimentado()) {
                contador++;
            }
        }
        System.out.println("Peces alimentados: " + contador + "/" + contadorVivos + " ("
                + ((contador / contadorVivos) * 100) + ")");
        contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isAdulto()) {
                contador++;
            }
        }
        System.out.println(
                "Peces adultos: " + contador + "/" + contadorVivos + " (" + ((contador / contadorVivos) * 100) + ")");
        contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).isSexo()) {
                contador++;
            }
        }
        System.out.println("Hembras/Machos: " + contador + "/" + (contadorVivos - contador));
        contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEsFertil()) {
                contador++;
            }
        }
        System.out.println("Fértiles: " +contador + "/" + contadorVivos);
    }

    /**
     * Método que llama al método showStatus de cada uno de los peces
     * para mostrar el estado de estos. Se muestra el nombre
     * la edad en días, el sexo, si esta vivo, si esta alimentado,
     * si es adulto y si es fertil
     */
    public void showFishStatus() {// done
        for (Pez pez : peces) {
            pez.showStatus();
        }
    }

    public void showCapacity() {// Deberia funcionar
        System.out.println("Tanque " + numeroTanque + " de la piscifactoria x al " + ((this.peces.size() / espacio) * 100)
                + "% de capacidad. [" + this.peces.size() + "/" + espacio + "]");
    }

    public void nextDay() {
        for (Pez pez : peces) {
            pez.grow();
        }
    }

    public ArrayList<Pez> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Pez> peces) {
        this.peces = peces;
    }
}
