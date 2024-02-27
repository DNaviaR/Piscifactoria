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
import pez.PezMar;
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
import propiedades.AlmacenPropiedades;

/**
 * Clase que representa un tanque de peces de mar.
 */
@JsonAdapter(TanqueMar.TanqueAdapter.class)
public class TanqueMar<T extends Pez> extends Tanque<T> implements PezMar {
    /**
     * Constructor de la clase.
     *
     * @param numeroTanque El número del tanque.
     */
    public TanqueMar(int numeroTanque) {
        super(numeroTanque);
        espacio = 100;
    }

    private class TanqueAdapter implements JsonDeserializer<Tanque<Pez>> {
        @Override
        public Tanque<Pez> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            HashMap<String, Class<?>> mapaDeClases = new HashMap<>();
            mapaDeClases.put("Abadejo", Abadejo.class);
            mapaDeClases.put("Arenque del Atlántico", ArenqueAtlantico.class);
            mapaDeClases.put("Besugo", Besugo.class);
            mapaDeClases.put("Caballa", Caballa.class);
            mapaDeClases.put("Cobia", Cobia.class);
            mapaDeClases.put("Corvina", Corvina.class);
            mapaDeClases.put("Lenguado europeo", LenguadoEuropeo.class);
            mapaDeClases.put("Lubina rayada", LubinaRayada.class);
            mapaDeClases.put("Robalo", Robalo.class);
            mapaDeClases.put("Rodaballo", Rodaballo.class);
            mapaDeClases.put("Sargo", Sargo.class);
            mapaDeClases.put("Bagre de canal", Bagre.class);
            mapaDeClases.put("Dorada", Dorada.class);
            mapaDeClases.put("Lubina europea", LubinaEuropea.class);
            mapaDeClases.put("Trucha arcoíris", TruchaArcoiris.class);
            mapaDeClases.put("Salmón atlántico", SalmonAtlantico.class);
            JsonObject jsonObject = json.getAsJsonObject();
            String nombrePez = jsonObject.getAsJsonPrimitive("pez").getAsString();
            int numeroTanque = jsonObject.getAsJsonPrimitive("num").getAsInt();

            // Crear una instancia de TanqueRio con el número de tanque
            TanqueMar<Pez> tanqueMar = new TanqueMar<>(numeroTanque);

            // Deserializar la lista de peces
            JsonArray pecesArray = jsonObject.getAsJsonArray("peces");
            ArrayList<Pez> peces = new ArrayList<>();

            for (JsonElement pezElement : pecesArray) {
                Pez pez = context.deserialize(pezElement, mapaDeClases.get(nombrePez));
                pez.setPecesDatos(AlmacenPropiedades.getPropByName(nombrePez));
                peces.add(pez);
            }

            // Establecer la lista de peces en el TanqueRio
            tanqueMar.setPeces(peces);

            return tanqueMar;
        }
    }
}
