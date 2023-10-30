package almacen;

/**
 * Clase que representa un almacén central.
 */
public class AlmacenCentral {
    /**
     * El espacio disponible en el almacén central.
     */
    protected int espacio;
    /**
     * Indica si el almacén central está activo.
     */
    protected boolean activo = false;

    /**
     * Constructor sin parámetros. El espacio disponible se inicializa a 200.
     */
    public AlmacenCentral() {
        this.espacio = 200;
    }

    /**
     * Obtiene el espacio disponible en el almacén central.
     *
     * @return El espacio disponible en el almacén central.
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio disponible en el almacén central.
     *
     * 
     * public void setEspacio(int espacio) {
     * this.espacio = espacio;
     * }
     * 
     * 
     * /**
     * Devuelve una representación en cadena del objeto AlmacenCentral.
     *
     * @return Una representación en cadena del objeto AlmacenCentral.
     */
    @Override
    public String toString() {
        return "AlmacenCentral [espacio=" + espacio + "]";
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
}
