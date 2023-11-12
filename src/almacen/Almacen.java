package almacen;

/**
 * Clase que representa un almacén.
 */
public class Almacen {

    /**
     * El espacio maximo en el almacén.
     */
    protected int espacioMaximo;
    /**
     * 
     * El espacio ocupado en el almacen
     */
    protected int espacioOcupado;

    /**
     * Constructor que recibe el espacio disponible en el almacén como parámetro.
     *
     * @param espacio El espacio disponible en el almacén.
     */
    public Almacen() {
    }
    

    public Almacen(int espacioMaximo) {
        this.espacioMaximo = espacioMaximo;
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
     * Devuelve una representación en cadena del objeto Almacen.
     *
     * @return Una representación en cadena del objeto Almacen.
     */
    @Override
    public String toString() {
        return "Almacen [espacioMaximo=" + espacioMaximo + ", espacioOcupado=" + espacioOcupado + "]";
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
        } else if (espacioLibre>0) {
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
}
