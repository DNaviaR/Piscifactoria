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

    private class DataJsonAdapter implements JsonSerializer<DataJson>, JsonDeserializer<DataJson> {

        @Override
        public JsonElement serialize(DataJson dataJson, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("implementados", context.serialize(dataJson.implementados));
            jsonObject.addProperty("empresa", dataJson.empresa);
            jsonObject.addProperty("dia", dataJson.dia);
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
                    return null;  
        }
    }
}
