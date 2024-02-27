package commons;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.google.gson.Gson;
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
import piscifactoria.Tanque;

@JsonAdapter(DataJson.DataJsonAdapter.class)
public class DataJson {
    public String[] implementados;
    public String empresa;
    public int dia;
    public int monedas;
    public String orca;
    public AlmacenCentral edificios;
    public List<Piscifactoria> piscifactorias;

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

    private class DataJsonAdapter implements JsonSerializer<DataJson>, JsonDeserializer<DataJson> {

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
