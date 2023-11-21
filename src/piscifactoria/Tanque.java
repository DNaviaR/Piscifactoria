package piscifactoria;

import java.util.ArrayList;
import java.util.List;

import commons.Simulador;
import pez.Pez;

/**
 * Clase abstracta que representa un tanque de una piscifactoría.
 */
public abstract class Tanque<T extends Pez> {
    /**
     * Lista de peces del tanque.
     */
    protected ArrayList<Pez> peces = new ArrayList<>();
    /**
     * Espacio del tanque para peces.
     */
    protected int espacio;
    /**
     * Número del tanque.
     */
    private int numeroTanque;

    /**
     * Constructor de la clase.
     *
     * @param numeroTanque El número del tanque.
     */
    public Tanque(int numeroTanque) {
        this.numeroTanque = numeroTanque;
    }

    /**
     * Devuelve el espacio del tanque en litros.
     *
     * @return El espacio del tanque en litros.
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio del tanque en litros.
     *
     * @param espacio El espacio del tanque en litros.
     */
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    /**
     * Devuelve el número del tanque.
     *
     * @return El número del tanque.
     */
    public int getNumeroTanque() {
        return numeroTanque;
    }

    /**
     * Establece el número del tanque.
     *
     * @param numeroTanque El número del tanque.
     */
    public void setNumeroTanque(int numeroTanque) {
        this.numeroTanque = numeroTanque;
    }

    /**
     * Devuelve la lista de peces del tanque.
     *
     * @return La lista de peces del tanque.
     */
    public ArrayList<Pez> getPeces() {
        return peces;
    }

    /**
     * Establece la lista de peces del tanque.
     *
     * @param peces La lista de peces del tanque.
     */
    public void setPeces(ArrayList<Pez> peces) {
        this.peces = peces;
    }

    /**
     * Devuelve una representación en cadena del tanque.
     *
     * @return Una cadena con información del tanque.
     */
    @Override
    public String toString() {
        return "Tanque " + numeroTanque;
    }

    /**
     * Muestra la información del tanque
     */
    public void showStatus() {
        System.out.println("--------------- Tanque " + numeroTanque + " ---------------");
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

    /**
     * Método que llama al método showStatus de cada uno de los peces
     * para mostrar el estado de estos. Se muestra el nombre
     * la edad en días, el sexo, si esta vivo, si esta alimentado,
     * si es adulto y si es fertil
     */
    public void showFishStatus() {
        for (Pez pez : peces) {
            pez.showStatus();
        }
    }

    /**
     * Muestra la ocupación del tanque.
     * 
     * @param nombrePiscifactoria El nombre de la piscifactoria a la que pertenece
     *                            el tanque
     */
    public void showCapacity(String nombrePiscifactoria) {
        System.out.println("Tanque " + numeroTanque + " de la piscifactoria " + nombrePiscifactoria + " al "
                + ((float) (this.peces.size() * 100) / espacio)
                + "% de capacidad. [" + this.peces.size() + "/" + espacio + "]");
    }

    /**
     * Hace crecer todos los peces del tanque, luego realiza el proceso de
     * reproducción y, por último, vende aquellos que hayan llegado a la edad
     * óptima.
     * 
     * @param comida La comida que se utiliza
     * @param pisci La piscifactoria a la que pertenece
     */
    public void nextDay(int espacio, Piscifactoria pisci) {
        List<Pez> copiaPeces = new ArrayList<>(peces);
        for (Pez pez : copiaPeces) {
            if (pez != null && pez.isEstaVivo()) {
                pez.grow(peces, pisci);
                pez.reproducirse(peces, espacio);
            }
        }
        eliminarNulos();
        this.sell();
    }

    /**
     * Calcula el número de peces vivos del tanque
     * 
     * @return El número de peces vivos del tanque
     */
    public int getVivos() {
        int contadorVivos = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo()) {
                contadorVivos++;
            }
        }
        return contadorVivos;
    }

    /**
     * Calcula el número de peces alimentados del tanque
     * 
     * @return El número de peces alimentados del tanque
     */
    public int getAlimentados() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).isAlimentado()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Calcula el número de peces adultos del tanque
     * 
     * @return El número de peces adultos del tanque
     */
    public int getAdultos() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).isAdulto()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Calcula el número de hembras del tanque
     * 
     * @return El número de hembras del tanque
     */
    public int getHembras() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).getSexo()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Calcula el número de peces fértiles del tanqueA
     * 
     * @return El número de peces fértiles del tanque
     */
    public int getFertiles() {
        int contador = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isEstaVivo() && peces.get(i).isEsFertil()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Vende peces y registra su venta
     */
    public void sell() {
        for (Pez pez : peces) {
            if (pez.getPecesDatos().getOptimo() == pez.getEdad() && pez.isEstaVivo()) {
                Simulador.estadisticas.registrarVenta(pez.getNombre(), pez.getPecesDatos().getMonedas());
                Simulador.monedas.ingresar(pez.getPecesDatos().getMonedas());
                peces.set(peces.indexOf(pez), null);
            }
        }
        eliminarNulos();
    }

    /**
     * Elimina los nulos de la lista
     */
    public void eliminarNulos() {
        while (peces.remove(null)) {
        }
    }
}
