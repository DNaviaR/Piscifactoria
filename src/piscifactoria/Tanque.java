package piscifactoria;

import java.util.ArrayList;

import pez.Pez;

public abstract class Tanque {
    ArrayList<Pez> peces = new ArrayList<>();
    int espacio;
    int numeroTanque;

    public Tanque(int numeroTanque) {
        this.numeroTanque = numeroTanque;
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
        System.out.println("Peces vivos " + getVivos() + "/" + peces.size() + " ("
                + (peces.size() == 0 ? "0/0" : ((getVivos() / peces.size()) * 100)) + ")");
        System.out.println("Peces alimentados: " + getAlimentados() + "/" + getVivos() + " ("
                + (peces.size() == 0 ? "0/0" : ((getAlimentados() / getVivos()) * 100)) + ")");
        System.out.println("Peces adultos: " + getAdultos() + "/" + getVivos() + " ("
                + (peces.size() == 0 ? "0/0" : (getAdultos() / getVivos()) * 100) + ")");
        System.out.println("Hembras/Machos: " + getHembras() + "/" + (getVivos() - getHembras()));
        System.out.println("Fértiles: " + getFertiles() + "/" + getVivos());
    }

    public int getVivos() {
        int contadorVivos = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo()) {
                contadorVivos++;
            }
        }
        return contadorVivos;
    }

    public int getAlimentados() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isAlimentado()) {
                contador++;
            }
        }
        return contador;
    }

    public int getAdultos() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isAdulto()) {
                contador++;
            }
        }
        return contador;
    }

    public int getHembras() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).getSexo()) {
                contador++;
            }
        }
        return contador;
    }

    public int getFertiles() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEsFertil()) {
                contador++;
            }
        }
        return contador;
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

    public void showCapacity(String nombrePiscifactoria) {// Deberia funcionar pero falta nombre piscifactoria
        System.out
                .println("Tanque " + numeroTanque + " de la piscifactoria "+nombrePiscifactoria+" al "
                        + ((float) (this.peces.size() * 100) / espacio)
                        + "% de capacidad. [" + this.peces.size() + "/" + espacio + "]");
    }

    public void nextDay(int comida) {
        for (Pez pez : peces) {
            pez.grow(comida);
        }
    }

    public ArrayList<Pez> getPeces() {
        return peces;
    }

    public void setPeces(ArrayList<Pez> peces) {
        this.peces = peces;
    }

}
