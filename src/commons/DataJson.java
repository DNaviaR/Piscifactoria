package commons;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import almacen.AlmacenCentral;
import piscifactoria.Piscifactoria;

/**
 * Clase que representa un objeto de datos JSON con anotación Gson para
 * especificar
 * el uso del adaptador personalizado {@code DataJsonAdapter}.
 */
@JsonAdapter(DataJson.DataJsonAdapter.class)
public class DataJson {
    /**
     * Array de peces implementados.
     */
    public String[] implementados;
    /**
     * Nombre de la partida.
     */
    public String empresa;
    /**
     * Día actual.
     */
    public int dia;
    /**
     * Cantidad de monedas.
     */
    public int monedas;
    /**
     * Instancia de la libreria.
     */
    public String orca;
    /**
     * Instancia de {@code AlmacenCentral} que representa los edificios.
     */
    public AlmacenCentral edificios;
    /**
     * Lista de instancias de {@code Piscifactoria}.
     */
    public List<Piscifactoria> piscifactorias;

    /**
     * Constructor de la clase {@code DataJson}.
     *
     * @param implementados  Arreglo de implementados.
     * @param empresa        Nombre de la empresa.
     * @param dia            Día actual.
     * @param monedas        Cantidad de monedas.
     * @param orca           Instancia de la libreria.
     * @param edificios      Instancia de {@code AlmacenCentral} que representa los
     *                       edificios.
     * @param piscifactorias Lista de instancias de {@code Piscifactoria}.
     */
    public DataJson(String[] implementados, String empresa, int dia, int monedas, String orca,
            AlmacenCentral edificios, List<Piscifactoria> piscifactorias) {
        this.implementados = implementados;
        this.empresa = empresa;
        this.dia = dia;
        this.monedas = monedas;
        this.orca = orca;
        this.edificios = edificios;
        this.piscifactorias = piscifactorias;
    }

    public String[] getImplementados() {
        return implementados;
    }

    public void setImplementados(String[] implementados) {
        this.implementados = implementados;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    public String getOrca() {
        return orca;
    }

    public void setOrca(String orca) {
        this.orca = orca;
    }

    public AlmacenCentral getEdificios() {
        return edificios;
    }

    public void setEdificios(AlmacenCentral edificios) {
        this.edificios = edificios;
    }

    public List<Piscifactoria> getPiscifactorias() {
        return piscifactorias;
    }

    public void setPiscifactorias(List<Piscifactoria> piscifactorias) {
        this.piscifactorias = piscifactorias;
    }

    /**
     * Clase interna que actúa como adaptador Gson personalizado para la
     * serialización y
     * deserialización de objetos {@code DataJson}.
     */
    private class DataJsonAdapter implements JsonSerializer<DataJson>, JsonDeserializer<DataJson> {
        /**
         * Serializa un objeto {@code DataJson} a formato JSON.
         *
         * @param dataJson  Objeto {@code DataJson} a serializar.
         * @param typeOfSrc Tipo de origen (no utilizado en este contexto).
         * @param context   Contexto de serialización Gson.
         * @return Elemento JSON que representa el objeto {@code DataJson}.
         */
        @Override
        public JsonElement serialize(DataJson dataJson, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("implementados", context.serialize(dataJson.implementados));
            jsonObject.addProperty("empresa", dataJson.empresa);
            jsonObject.addProperty("dia", dataJson.dia);
            jsonObject.addProperty("monedas", dataJson.monedas);
            jsonObject.addProperty("orca", dataJson.orca);
            JsonObject edificiosObject = new JsonObject();
            edificiosObject.add("almacen", context.serialize(dataJson.edificios, new TypeToken<AlmacenCentral>() {
            }.getType()));
            jsonObject.add("comida", edificiosObject);
            JsonObject piscifactoriasObject = new JsonObject();
            piscifactoriasObject.add("piscifactoria",
                    context.serialize(dataJson.piscifactorias, new TypeToken<List<Piscifactoria>>() {
                    }.getType()));
            jsonObject.add("piscifactorias", piscifactoriasObject);
            return jsonObject;
        }

        /**
         * Deserializa un objeto {@code DataJson} desde formato JSON.
         *
         * @param json    Elemento JSON que representa el objeto {@code DataJson}.
         * @param typeOfT Tipo de destino (no utilizado en este contexto).
         * @param context Contexto de deserialización Gson.
         * @return Objeto {@code DataJson} deserializado.
         * @throws JsonParseException Si hay un error durante la deserialización.
         */
        @Override
        public DataJson deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String[] implementados = context.deserialize(jsonObject.get("implementados"),
                    new TypeToken<String[]>() {
                    }.getType());
            String empresa = jsonObject.getAsJsonPrimitive("empresa").getAsString();
            int dia = jsonObject.getAsJsonPrimitive("dia").getAsInt();
            int monedas = jsonObject.getAsJsonPrimitive("monedas").getAsInt();
            String orca = jsonObject.getAsJsonPrimitive("orca").getAsString();
            AlmacenCentral almacen = context.deserialize(jsonObject.getAsJsonObject("comida").get("almacen"),
                    AlmacenCentral.class);
            JsonArray piscifactoriasArray = jsonObject.getAsJsonObject("piscifactorias")
                    .getAsJsonArray("piscifactoria");
            List<Piscifactoria> piscifactorias = new ArrayList<>();
            for (JsonElement element : piscifactoriasArray) {
                Piscifactoria piscifactoria = context.deserialize(element, Piscifactoria.class);
                piscifactorias.add(piscifactoria);
            }
            DataJson dataJson = new DataJson(implementados, empresa, dia, monedas, orca, almacen, piscifactorias);
            return dataJson;
        }
    }
}
