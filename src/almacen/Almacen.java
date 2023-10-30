package almacen;

/**
 * Clase que representa un almacén.
 */
public class Almacen {

    /**
     * El espacio disponible en el almacén.
     */
    protected int espacio;

    /**
     * Constructor que recibe el espacio disponible en el almacén como parámetro.
     *
     * @param espacio El espacio disponible en el almacén.
     */
    public Almacen(int espacio) {
        this.espacio = espacio;
    }

    /**
     * Obtiene el espacio disponible en el almacén.
     *
     * @return El espacio disponible en el almacén.
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio disponible en el almacén.
     *
     * @param espacio El espacio disponible en el almacén.
     */
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    /**
     * Devuelve una representación en cadena del objeto Almacen.
     *
     * @return Una representación en cadena del objeto Almacen.
     */
    @Override
    public String toString() {
        return "Almacen [espacio=" + espacio + "]";
    }

}
