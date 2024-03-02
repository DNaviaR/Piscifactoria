package almacen;

import java.lang.reflect.Type;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.JsonAdapter;

/**
 * Clase que representa un almacén central.
 */
@JsonAdapter(AlmacenCentral.AlmacenCentralAdapter.class)
public class AlmacenCentral {
    /**
     * El espacio maximo en el almacén.
     */
    protected int espacioMaximo;
    /**
     * El espacio ocupado en el almacen
     */
    protected int espacioOcupado;
    /**
     * El estado del almacén.
     */
    protected boolean activo = false;

    /**
     * Constructor sin parámetros. El espacio disponible se inicializa a 200.
     */
    public AlmacenCentral() {
        this.espacioMaximo = 200;
    }

    /**
     * Obtiene el espacio maximo disponible en el almacén.
     *
     * @return El espacio disponible en el almacén.
     */
    public int getEspacioMaximo() {
        return espacioMaximo;
    }

    /**
     * Establece el espacio maximo disponible en el almacén.
     *
     * @param espacio El espacio disponible en el almacén.
     */
    public void setEspacioMaximo(int espacioMaximo) {
        this.espacioMaximo = espacioMaximo;
    }

    /**
     * Obtiene el espacio ocupado en el almacén.
     *
     * @return El espacio ocupado en el almacén.
     */
    public int getEspacioOcupado() {
        return espacioOcupado;
    }

    /**
     * Establece el espacio ocupado en el almacén.
     *
     * @param espacio El espacio ocupado en el almacén.
     */
    public void setEspacioOcupado(int espacioOcupado) {
        this.espacioOcupado = espacioOcupado;
    }

    /**
     * Indica si el almacén central está activo.
     *
     * @return true si el almacén central está activo, false en caso contrario.
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * Establece el estado del almacén central.
     *
     * @param activo El nuevo estado del almacén central.
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Devuelve una representación en cadena del objeto AlmacenCentral.
     *
     * @return Una representación en cadena del objeto AlmacenCentral.
     */
    @Override
    public String toString() {
        return "Almacen Central: " + espacioOcupado + "/" + espacioMaximo + " ("
                + ((espacioOcupado / espacioMaximo) * 100) + "%)";
    }

    /**
     * Añade la cantidad especificada al almacen
     * 
     * @param cantidad La cantidad a introducir
     */
    public void masComida(int cantidad) {
        int espacioLibre = espacioMaximo - espacioOcupado;
        if (espacioLibre >= cantidad) {
            this.espacioOcupado += cantidad;
        } else {
            this.espacioOcupado += espacioLibre;
        }
    }

    /**
     * Obtiene el espacio libre en el almacén.
     *
     * @return El espacio libre en el almacén.
     */
    public int getEspacioDisponible() {
        int espacioLibre = espacioMaximo - espacioOcupado;
        return espacioLibre;
    }

    private class AlmacenCentralAdapter implements JsonSerializer<AlmacenCentral>, JsonDeserializer<AlmacenCentral> {

        @Override
        public JsonElement serialize(AlmacenCentral almacenCentral, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("disponible", almacenCentral.activo);
            jsonObject.addProperty("capacidad", almacenCentral.getEspacioMaximo());
            JsonObject comidaObject = new JsonObject();
            comidaObject.addProperty("general", almacenCentral.getEspacioOcupado());
            jsonObject.add("comida", comidaObject);
            return jsonObject;
        }

        @Override
        public AlmacenCentral deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();

            boolean activo = jsonObject.getAsJsonPrimitive("disponible").getAsBoolean();
            int capacidad = jsonObject.getAsJsonPrimitive("capacidad").getAsInt();

            int espacioOcupado = jsonObject.getAsJsonObject("comida").getAsJsonPrimitive("general").getAsInt();

            AlmacenCentral almacenCentral = new AlmacenCentral();
            almacenCentral.setActivo(activo);
            almacenCentral.setEspacioMaximo(capacidad);
            almacenCentral.setEspacioOcupado(espacioOcupado);

            return almacenCentral;
        }
    }
}
