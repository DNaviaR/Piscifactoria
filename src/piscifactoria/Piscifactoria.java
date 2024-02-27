package piscifactoria;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import almacen.Almacen;
import pez.Pez;

/**
 * Clase abstracta que representa una piscifactoría.
 */
@JsonAdapter(Piscifactoria.PiscifactoriaAdapter.class)
public abstract class Piscifactoria {
    /**
     * El nombre de la piscifactoría.
     */
    private String nombrePiscifactoria;
    /**
     * Indica el tipo de la piscifactoria. 0 es rio, 1 mar
     */
    protected int tipo = 0;
    /**
     * La lista de tanques de la piscifactoría.
     */
    protected Almacen almacen;
    /**
     * Lleva la cuenta de las mejoras del almacen
     */
    protected ArrayList<Tanque<? extends Pez>> tanques = new ArrayList<Tanque<? extends Pez>>();
    /**
     * El almacén de comida de la piscifactoría.
     */
    protected int contadorMejoraAlmacen = 1;
    /**
     * Lleva la cuenta de los peces vendidos en la piscifactoria
     */
    protected int contadorPecesVendidos = 0;

    /**
     * Constructor que recibe el nombre de la piscifactoría.
     *
     * @param nombrePiscifactoria El nombre de la piscifactoría.
     */
    public Piscifactoria(String nombrePiscifactoria) {
        this.nombrePiscifactoria = nombrePiscifactoria;
        contadorMejoraAlmacen = 1;
    }

    /**
     * Obtiene la lista de tanques de la piscifactoría.
     *
     * @return La lista de tanques de la piscifactoría.
     */
    public ArrayList<Tanque<? extends Pez>> getTanques() {
        return tanques;
    }

    /**
     * Establece la lista de tanques de la piscifactoría.
     *
     * @param tanques La nueva lista de tanques de la piscifactoría.
     */
    public void setTanques(ArrayList<Tanque<? extends Pez>> tanques) {
        this.tanques = tanques;
    }

    /**
     * Obtiene el nombre de la piscifactoría.
     *
     * @return El nombre de la piscifactoría.
     */
    public String getNombrePiscifactoria() {
        return nombrePiscifactoria;
    }

    /**
     * Establece el nombre de la piscifactoría.
     *
     * @param nombrePiscifactoria El nuevo nombre de la piscifactoría.
     */
    public void setNombrePiscifactoria(String nombrePiscifactoria) {
        this.nombrePiscifactoria = nombrePiscifactoria;
    }

    /**
     * Obtiene el almacén de comida de la piscifactoría.
     *
     * @return El almacén de comida de la piscifactoría.
     */
    public Almacen getAlmacen() {
        return almacen;
    }

    /**
     * Establece el almacén de comida de la piscifactoría.
     *
     * @param almacen El nuevo almacén de comida de la piscifactoría.
     */
    public void setAlmacen(Almacen almacen) {
        this.almacen = almacen;
    }

    /**
     * Obtiene el contador de mejoras del almacén de comida de la piscifactoría.
     *
     * @return El contador de mejoras del almacén de comida de la piscifactoría.
     */
    public int getContadorMejoraAlmacen() {
        return contadorMejoraAlmacen;
    }

    /**
     * Establece el contador de mejoras del almacén de comida de la piscifactoría.
     *
     * @param contadorMejoraAlmacen El nuevo contador de mejoras del almacén de
     *                              comida de la piscifactoría.
     */
    public void setContadorMejoraAlmacen(int contadorMejoraAlmacen) {
        this.contadorMejoraAlmacen = contadorMejoraAlmacen;
    }

    /**
     * Obtiene el contador de peces vendidos de la piscifactoría.
     *
     * @return El contador de peces vendidos de la piscifactoría.
     */
    public int getContadorPecesVendidos() {
        return contadorPecesVendidos;
    }

    /**
     * Establece el contador de peces vendidos de la piscifactoría.
     *
     * @param contadorMejoraAlmacen El nuevo contador de peces vendidos de la
     *                              piscifactoría.
     */
    public void setContadorPecesVendidos(int contadorPecesVendidos) {
        this.contadorPecesVendidos = contadorPecesVendidos;
    }

    /**
     * Devuelve una representación en cadena de la piscifactoría.
     * 
     * @return Una cadena con el nombre de la piscifactoría y la lista de tanques.
     */
    @Override
    public String toString() {
        return "Piscifactoria [tanques=" + tanques + ", nombrePiscifactoria=" + nombrePiscifactoria + "]";
    }

    /**
     * Muestra toda la información de la piscifactoría.
     *
     */
    public void showStatus() {
        System.out.println("=============== " + this.nombrePiscifactoria + " ===============\n" +
                "Tanques: " + tanques.size() + "\n" +
                "Ocupacion: " + peces() + " / " + pecesMax() + " ("
                + (pecesMax() > 0 ? (float) ((peces() * 100) / pecesMax()) : 0) + "%)\n" +
                "Peces vivos: " + pecesVivos() + " / " + peces() + " ("
                + (pecesVivos() > 0 ? (float) ((pecesVivos() * 100) / peces()) : 0) + "%)\n" +
                "Peces alimentados: " + pecesAlimentados() + " / " + pecesVivos() + " ("
                + (pecesVivos() > 0 ? +(float) ((pecesAlimentados() * 100) / pecesVivos()) : 0) + "%)\n" +
                "Peces adultos: " + pecesAdultos() + " / " + pecesVivos() + " ("
                + (pecesVivos() > 0 ? (float) ((pecesAdultos() * 100) / pecesVivos()) : 0) + "%)\n" +
                "Hembras / Machos: " + pecesHembras() + " / " + (pecesVivos() - pecesHembras()) + "\n" +
                "Fertiles: " + pecfertiles() + " / " + pecesVivos() + "\n" +
                "Almacen de comida: " + almacen.getEspacioOcupado() + "/" + almacen.getEspacioMaximo() + " ("
                + ((almacen.getEspacioOcupado() / almacen.getEspacioMaximo()) * 100) + "%)");
    }

    /**
     * Muestra la información de cada tanque
     */
    public void showTankStatus() {
        for (Tanque<? extends Pez> tanque : tanques) {
            tanque.showCapacity(this.nombrePiscifactoria);
        }
    }

    /**
     * Muestra la información de todos los peces de un tanque determinado.
     * 
     * @param posicionTanque El tanque que queremos observar
     */
    public void showFishStatus(int posicionTanque) {
        tanques.get(posicionTanque).showFishStatus();
    }

    /**
     * Muestra la ocupación de un tanque determinado
     * 
     * @param posicionTanque El tanque que queremos observar
     */
    public void showCapacity(int posicionTanque) {
        tanques.get(posicionTanque).showCapacity(nombrePiscifactoria);
    }

    /**
     * Muestra el estado del almacén de comida
     */
    public void showFood() {
        System.out.println("Depósito de comida de la piscifactoria " + nombrePiscifactoria + " al "
                + ((almacen.getEspacioOcupado() / almacen.getEspacioMaximo()) * 100) + "% de su capacidad. ["
                + almacen.getEspacioOcupado() + "/" + almacen.getEspacioMaximo() + "]");
    }

    /**
     * Hace la lógica de pasar de día de todos los peces de la piscifactoría.
     */
    public void nextDay() {
        for (Tanque<? extends Pez> tanque : tanques) {
            tanque.nextDay(tanque.getEspacio(), this);
        }
    }

    /**
     * Calcula el espacio total para peces de los tanques de la piscifactoria
     * 
     * @return El espacio para peces que hay en la piscifactoria
     */
    public int pecesMax() {
        int pecesMax = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesMax += tanques.get(i).getEspacio();
        }
        return pecesMax;
    }

    /**
     * Calcula el numero de peces que hay en total en la piscifactoria
     * 
     * @return El número de peces que hay en la piscifactoria
     */
    public int peces() {
        int peces = 0;
        for (int i = 0; i < tanques.size(); i++) {
            peces += tanques.get(i).getPeces().size();
        }
        return peces;
    }

    /**
     * Calcula el numero de peces vivos que hay en total en la piscifactoria
     * 
     * @return El número de peces vivos que hay en la piscifactoria
     */
    public int pecesVivos() {
        int pecesVivos = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesVivos += tanques.get(i).getVivos();
        }
        return pecesVivos;
    }

    /**
     * Calcula el numero de peces alimentados que hay en total en la piscifactoria
     * 
     * @return El número de peces alimentados que hay en la piscifactoria
     */
    public int pecesAlimentados() {
        int pecesAlimentados = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesAlimentados += tanques.get(i).getAlimentados();
        }
        return pecesAlimentados;
    }

    /**
     * Calcula el numero de peces adultos que hay en total en la piscifactoria
     * 
     * @return El número de peces adultos que hay en la piscifactoria
     */
    public int pecesAdultos() {
        int pecesAdultos = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesAdultos += tanques.get(i).getAdultos();
        }
        return pecesAdultos;
    }

    /**
     * Calcula el numero de hembras que hay en total en la piscifactoria
     * 
     * @return El número de hembras que hay en la piscifactoria
     */
    public int pecesHembras() {
        int pecesHembras = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesHembras += tanques.get(i).getHembras();
        }
        return pecesHembras;
    }

    /**
     * Calcula el numero de peces que se pueden reproducir que hay en total en la
     * piscifactoria
     * 
     * @return El número de peces que se pueden reproducir que hay en la
     *         piscifactoria
     */
    public int pecfertiles() {
        int pecfertiles = 0;
        for (int i = 0; i < tanques.size(); i++) {
            pecfertiles += tanques.get(i).getFertiles();
        }
        return pecfertiles;
    }

    private class PiscifactoriaAdapter implements JsonSerializer<Piscifactoria>, JsonDeserializer<Piscifactoria> {

        @Override
        public JsonElement serialize(Piscifactoria piscifactoria, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("nombre", piscifactoria.nombrePiscifactoria);
            jsonObject.addProperty("tipo", piscifactoria.tipo);
            jsonObject.addProperty("capacidad", piscifactoria.getAlmacen().getEspacioMaximo());
            JsonObject comidaObject = new JsonObject();
            comidaObject.addProperty("general", piscifactoria.getAlmacen().getEspacioOcupado());
            jsonObject.add("comida", comidaObject);
            jsonObject.add("tanques",
                    context.serialize(piscifactoria.getTanques(), new TypeToken<ArrayList<Tanque<?>>>() {
                    }.getType()));
            return jsonObject;
        }

        @Override
        public Piscifactoria deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            Piscifactoria piscifactoria = null;

            // Obtener datos principales de la piscifactoria
            String nombrePiscifactoria = jsonObject.get("nombre").getAsString();
            int tipo = jsonObject.get("tipo").getAsInt();
            int capacidadAlmacen = jsonObject.get("capacidad").getAsInt();

            // Obtener datos adicionales del almacen
            JsonObject comidaObject = jsonObject.getAsJsonObject("comida");
            int espacioOcupado = comidaObject.get("general").getAsInt();

            // Obtener la lista de tanques de la piscifactoria
            JsonArray tanquesArray = jsonObject.getAsJsonArray("tanques");
            ArrayList<Tanque<?>> tanques = new ArrayList<>();

            for (JsonElement tanqueElement : tanquesArray) {
                if (tipo==0) {
                    TanqueRio<Pez> tanque = context.deserialize(tanqueElement, TanqueRio.class);
                    tanques.add(tanque);
                }else{
                    TanqueMar<Pez> tanque = context.deserialize(tanqueElement, TanqueMar.class);
                    tanques.add(tanque);
                }
            }

            // Crear una instancia de Piscifactoria con los datos obtenidos
            switch (tipo) {
                case 0:
                    piscifactoria = new PiscifactoriaRio(nombrePiscifactoria);
                    break;
                case 1:
                    piscifactoria = new PiscifactoriaMar(nombrePiscifactoria);
                    break;
                default:
                    break;
            }
            Almacen almacen = new Almacen();
            almacen.setEspacioMaximo(capacidadAlmacen);
            almacen.setEspacioOcupado(espacioOcupado);
            piscifactoria.setAlmacen(almacen);
            piscifactoria.setTanques(tanques);

            return piscifactoria;
        }
    }
}
