package commons;

/**
 * Una clase que representa una cantidad de monedas
 */
public class Monedas {
    private static Monedas instancia = null;
    /**
     * El número de monedas
     */
    protected int monedas;

    /**
     * Constructor por defecto
     */
    private Monedas() {
    }

    public static Monedas getInstance() {
        if (instancia == null) {
            instancia = new Monedas();
        }
        return instancia;
    }

    /**
     * Ver el número de monedas
     * 
     * @return El número de monedas
     */
    public int getMonedas() {
        return monedas;
    }

    /**
     * Establece el número de monedas
     * 
     * @param monedas El número de monedas
     */
    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    /**
     * Devuelve una representación en cadena del objeto Monedas.
     *
     * @return Una representación en cadena del objeto Monedas.
     */
    @Override
    public String toString() {
        return "Monedas [" + monedas + "]";
    }

}
