package piscifactoria;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

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

import pez.Pez;
import pez.PezRio;
import pez.pecesDoble.Bagre;
import pez.pecesDoble.Dorada;
import pez.pecesDoble.LubinaEuropea;
import pez.pecesDoble.SalmonAtlantico;
import pez.pecesDoble.TruchaArcoiris;
import pez.pecesMar.Abadejo;
import pez.pecesMar.ArenqueAtlantico;
import pez.pecesMar.Besugo;
import pez.pecesMar.Caballa;
import pez.pecesMar.Cobia;
import pez.pecesMar.Corvina;
import pez.pecesMar.LenguadoEuropeo;
import pez.pecesMar.LubinaRayada;
import pez.pecesMar.Robalo;
import pez.pecesMar.Rodaballo;
import pez.pecesMar.Sargo;
import pez.pecesRio.Carpa;
import pez.pecesRio.CarpaPlateada;
import pez.pecesRio.CarpinTresEspinas;
import pez.pecesRio.Koi;
import pez.pecesRio.LucioDelNorte;
import pez.pecesRio.Pejerrey;
import pez.pecesRio.PercaEuropea;
import pez.pecesRio.SalmonChinook;
import pez.pecesRio.SiluroEuropeo;
import pez.pecesRio.TilapiaDelNilo;
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa un tanque de peces de rio.
 */
@JsonAdapter(TanqueRio.TanqueAdapter.class)
public class TanqueRio<T extends Pez> extends Tanque<T> implements PezRio {
    /**
     * Constructor de la clase.
     *
     * @param numeroTanque El número del tanque.
     */
    public TanqueRio(int numeroTanque) {
        super(numeroTanque);
        espacio = 25;
    }

    private class TanqueAdapter implements JsonDeserializer<Tanque<Pez>> {
        @Override
        public Tanque<Pez> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            HashMap<String, Class<?>> mapaDeClases = new HashMap<>();
            mapaDeClases.put("Carpa", Carpa.class);
            mapaDeClases.put("Carpa plateada", CarpaPlateada.class);
            mapaDeClases.put("Carpín de tres espinas", CarpinTresEspinas.class);
            mapaDeClases.put("Koi", Koi.class);
            mapaDeClases.put("Lucio del norte", LucioDelNorte.class);
            mapaDeClases.put("Pejerrey", Pejerrey.class);
            mapaDeClases.put("Perca europea", PercaEuropea.class);
            mapaDeClases.put("Salmón chinook", SalmonChinook.class);
            mapaDeClases.put("Siluro europeo", SiluroEuropeo.class);
            mapaDeClases.put("Tilapia del Nilo", TilapiaDelNilo.class);
            mapaDeClases.put("Bagre de canal", Bagre.class);
            mapaDeClases.put("Dorada", Dorada.class);
            mapaDeClases.put("Lubina europea", LubinaEuropea.class);
            mapaDeClases.put("Trucha arcoíris", TruchaArcoiris.class);
            mapaDeClases.put("Salmón atlántico", SalmonAtlantico.class);
            JsonObject jsonObject = json.getAsJsonObject();
            String nombrePez = jsonObject.getAsJsonPrimitive("pez").getAsString();
            int numeroTanque = jsonObject.getAsJsonPrimitive("num").getAsInt();

            // Crear una instancia de TanqueRio con el número de tanque
            TanqueRio<Pez> tanqueRio = new TanqueRio<>(numeroTanque);

            // Deserializar la lista de peces
            JsonArray pecesArray = jsonObject.getAsJsonArray("peces");
            ArrayList<Pez> peces = new ArrayList<>();

            for (JsonElement pezElement : pecesArray) {
                Pez pez = context.deserialize(pezElement, mapaDeClases.get(nombrePez));
                pez.setPecesDatos(AlmacenPropiedades.getPropByName(nombrePez));
                peces.add(pez);
            }

            // Establecer la lista de peces en el TanqueRio
            tanqueRio.setPeces(peces);

            return tanqueRio;
        }
    }
}
