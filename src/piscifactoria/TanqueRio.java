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
import com.google.gson.annotations.JsonAdapter;
import pez.Pez;
import pez.PezRio;
import pez.pecesDoble.Bagre;
import pez.pecesDoble.Dorada;
import pez.pecesDoble.LubinaEuropea;
import pez.pecesDoble.SalmonAtlantico;
import pez.pecesDoble.TruchaArcoiris;
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

    /**
     * Esta clase proporciona la lógica de deserialización para convertir un objeto
     * JSON
     * en una instancia de la clase {@code Tanque<Pez>}.
     * Utiliza un mapa de clases para mapear el nombre del pez a la clase
     * correspondiente
     * durante el proceso de deserialización.
     */
    private class TanqueAdapter implements JsonDeserializer<Tanque<Pez>> {
        /**
         * Deserializa un objeto JSON representando un tanque de peces.
         *
         * @param json    Elemento JSON que se va a deserializar.
         * @param typeOfT Tipo de la instancia que se debe deserializar.
         * @param context Contexto de deserialización proporcionado por Gson.
         * @return Una instancia de la clase {@code Tanque<Pez>} deserializada.
         * @throws JsonParseException Si hay un error durante el proceso de
         *                            deserialización JSON.
         */
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
            TanqueRio<Pez> tanqueRio = new TanqueRio<>(numeroTanque);
            JsonArray pecesArray = jsonObject.getAsJsonArray("peces");
            ArrayList<Pez> peces = new ArrayList<>();
            for (JsonElement pezElement : pecesArray) {
                Pez pez = context.deserialize(pezElement, mapaDeClases.get(nombrePez));
                pez.setPecesDatos(AlmacenPropiedades.getPropByName(nombrePez));
                peces.add(pez);
            }
            tanqueRio.setPeces(peces);
            return tanqueRio;
        }
    }
}
