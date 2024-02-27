package piscifactoria;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;

import commons.Simulador;
import pez.Pez;

/**
 * Clase abstracta que representa un tanque de una piscifactoría.
 */
@JsonAdapter(Tanque.TanqueAdapter.class)
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
    int numeroTanque;

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
     * @param pisci  La piscifactoria a la que pertenece
     */
    public void nextDay(int espacio, Piscifactoria pisci) {
        List<Pez> copiaPeces = new ArrayList<>(peces);
        for (Pez pez : copiaPeces) {
            if (pez != null && pez.isvivo()) {
                pez.grow(peces, pisci);
                pez.reproducirse(peces, espacio);
            }
        }
        eliminarNulos();
        this.sell(pisci);
    }

    /**
     * Calcula el número de peces vivos del tanque
     * 
     * @return El número de peces vivos del tanque
     */
    public int getVivos() {
        int contadorVivos = 0;
        for (int i = 0; i < peces.size(); i++) {
            if (peces.get(i).isvivo()) {
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
            if (peces.get(i).isvivo() && peces.get(i).isAlimentado()) {
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
            if (peces.get(i).isvivo() && peces.get(i).isAdulto()) {
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
            if (peces.get(i).isvivo() && peces.get(i).getSexo()) {
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
            if (peces.get(i).isvivo() && peces.get(i).isfertil()) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Vende peces y registra su venta
     */
    public void sell(Piscifactoria piscifactoria) {
        for (Pez pez : peces) {
            if (pez.getPecesDatos().getOptimo() == pez.getEdad() && pez.isvivo()) {
                Simulador.estadisticas.registrarVenta(pez.getNombre(), pez.getPecesDatos().getMonedas());
                Simulador.monedas.ingresar(pez.getPecesDatos().getMonedas());
                peces.set(peces.indexOf(pez), null);
                piscifactoria.setContadorPecesVendidos(piscifactoria.getContadorPecesVendidos() + 1);
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
    private class TanqueAdapter implements JsonSerializer<Tanque<Pez>> {
        @Override
        public JsonElement serialize(Tanque<Pez> tanque, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            if (tanque.getPeces().isEmpty()) {
                jsonObject.addProperty("pez", "");
            } else {
                jsonObject.addProperty("pez", tanque.getPeces().get(0).getNombre());
            }
            jsonObject.addProperty("num", tanque.numeroTanque);
            JsonObject datosObject = new JsonObject();
            datosObject.addProperty("vivos", tanque.getVivos());
            datosObject.addProperty("maduros", tanque.getAdultos());
            datosObject.addProperty("fertiles", tanque.getFertiles());
            jsonObject.add("datos", datosObject);
            jsonObject.add("peces", context.serialize(tanque.getPeces(), new TypeToken<ArrayList<Pez>>() {
            }.getType()));
            return jsonObject;
        }
    }
}
