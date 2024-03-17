package bd;

/**
 * Clase que representa un cliente
 */
public class Clientes {
    /**
     * Representa el nombre del cliente
     */
    private String nombre;
    /**
     * Representa el nif del cliente
     */
    private String nif;
    /**
     * Representa el telefono del cliente
     */
    private String telefono;

    /**
     * Crea un objeto cliente
     * @param nombre el nombre del cliente
     * @param nif el nif del cliente
     * @param telefono el telefono del cliente
     */
    public Clientes(String nombre, String nif, String telefono) {
        this.nombre = nombre;
        this.nif = nif;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}   
