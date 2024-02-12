package pez;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa un pez.
 */
public abstract class Pez implements Cloneable {
    /**
     * Los datos del tipo de pez
     */
    protected PecesDatos pc;
    /**
     * Los dias de vida del pez
     */
    protected int edad;
    /**
     * El sexo del pez. True es Hembra, false es Macho
     */
    protected boolean sexo; // True=H / False=M
    /**
     * El estado de vida del pez
     */
    protected boolean estaVivo = true;
    /**
     * El estado de alimentación del pez
     */
    protected boolean alimentado = false;
    /**
     * Si el pez es adulto
     */
    protected boolean adulto = false;
    /**
     * Si el pez es fértil
     */
    protected boolean esFertil;
    /**
     * Los ciclos de reproducción
     */
    protected int countCiclos = 0;

    /**
     * Constructor de la clase
     * 
     * @param sexo El sexo del pez
     * @param pc   Los datos del tipo de pez
     */
    public Pez(boolean sexo, PecesDatos pc) {
        this.sexo = sexo;
        this.pc = pc;
    }

    /**
     * Obtiene el nombre del pez.
     *
     * @return El nombre del pez.
     */
    public String getNombre() {
        return pc.getNombre();
    }

    /**
     * Obtiene el nombre científico del pez.
     *
     * @return El nombre científico del pez.
     */
    public String getCientifico() {
        return pc.getCientifico();
    }

    /**
     * Obtiene los dias de vida del pez.
     *
     * @return Los dias de vida del pez.
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Obtiene el sexo del pez.
     *
     * @return El sexo del pez.
     */
    public boolean getSexo() {
        return sexo;
    }

    /**
     * Establece si el pez es macho o hembra.
     * 
     * @param sexo el nuevo sexo del pez
     */
    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtiene el estado de vida del pez.
     *
     * @return El estado de vida del pez.
     */
    public boolean isEstaVivo() {
        return estaVivo;
    }

    /**
     * Obtiene las propiedades del pez.
     *
     * @return Las propiedades del pez.
     */
    public PecesDatos getPecesDatos() {
        return pc;
    }

    /**
     * Establece si el pez está vivo o no.
     * 
     * @param estaVivo el nuevo estado del pez
     */
    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    /**
     * Comprueba si el pez está alimentado
     *
     * @return Si el pez está alimentado o no
     */
    public boolean isAlimentado() {
        return alimentado;
    }

    /**
     * Establece si el pez está alimentado o no.
     * 
     * @param alimentado el nuevo estado del pez
     */
    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    /**
     * Comprueba si el pez es adulto
     *
     * @return Si el pez es adulto o no
     */
    public boolean isAdulto() {
        return adulto;
    }

    /**
     * Establece si el pez es adulto o no.
     * 
     * @param adulto el nuevo estado del pez
     */
    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    /**
     * Comprueba si el pez es fértil
     *
     * @return Si el pez es fértil o no
     */
    public boolean isEsFertil() {
        return esFertil;
    }

    /**
     * Establece si el pez es fértil o no.
     * 
     * @param esFertil el nuevo estado del pez
     */
    public void setEsFertil(boolean esFertil) {
        this.esFertil = esFertil;
    }

    /**
     * Devuelve una representación en cadena del pez.
     * 
     * @return Una cadena con información del pez.
     */
    @Override
    public String toString() {
        return "Pez [nombre=" + pc.getNombre() + ", cientifico=" + pc.getCientifico() + ", edad=" + edad
                + ", sexo=" + sexo + ", estaVivo=" + estaVivo + ", alimentado=" + alimentado + ", adulto=" + adulto
                + ", esFertil=" + esFertil + "]";
    }

    /**
     * Muestra el estado del pez
     */
    public void showStatus() {
        System.out.println("--------------- " + pc.getNombre() + " ---------------");
        System.out.println("Edad: " + edad + " días");
        System.out.println("Sexo: " + (getSexo() ? "H" : "M"));
        System.out.println("Vivo: " + (estaVivo ? "Si" : "No"));
        System.out.println("Alimentado: " + (alimentado ? "Si" : "No"));
        System.out.println("Adulto: " + (adulto ? "Si" : "No"));
        System.out.println("Fértil: " + (esFertil ? "Si" : "No"));
    }

    /**
     * Hace crecer un día el pez, realizando toda la lógica:
     * 1. Se alimenta.
     * 2. Si no está alimentado tiene, por defecto, un 50% de morir.
     * 3. Aumenta en 1 su edad.
     * 4. Se verifica su madurez y su fertilidad.
     * 
     * @param comida La comida que se utiliza
     * @param peces La lista de peces
     */
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        if (this.isEstaVivo()) {
            if (this.isAlimentado() == false) {
                this.morir();
            }
            // Aumentar la edad en 1 día.
            edad++;
            // Verificar la madurez y fertilidad.
            if (edad == pc.getMadurez()) {
                adulto = true;
                esFertil = true;
            }
        }
        this.setAlimentado(false);
    }

    /**
     * Reinicia el pez, estableciendo su edad a cero y el resto de atributos a sus
     * estados iniciales
     */
    public void reset() {
        edad = 0;
        estaVivo = true;
        alimentado = false;
        adulto = false;
        esFertil = false;
    }

    /**
     * Comprueba el sexo de los peces del tanque y devuelve el
     * sexo menos común
     * 
     * @param peces Lista de peces del tanque
     * @return El sexo menos predominante
     */
    public boolean obtenerSexosTanque(List<Pez> peces) {
        int contadorHembras = 0;
        int contadorMachos = 0;
        for (Pez pez : peces) {
            if (pez != null && pez.getSexo() == true) {
                contadorHembras++;
            } else {
                contadorMachos++;
            }
        }
        if (contadorHembras > contadorMachos) {
            return false;
        } else
            return true;
    }

    /**
     * Comprueba si el pez puede reproducirse. Si puede,
     * realiza la lógica de reproducción
     * 
     * @param peces Lista de peces candidatos a la reproducción
     * @param espacio Espacio total de la lista
     */
    public void reproducirse(List<Pez> peces, int espacio) {
        ArrayList<Pez> nuevosPeces = new ArrayList<>();
        if (this.getSexo() == true) {
            if (this.esFertil == true && countCiclos <= 0) {
                for (Pez pez : peces) {
                    if (pez != null && pez.getSexo() != this.getSexo()) {
                        if (pez.isEsFertil()) {
                            int libre=espacio - peces.size();
                            int disponible =libre-nuevosPeces.size();
                            if (disponible >= this.getPecesDatos().getHuevos()) {
                                for (int i = 0; i < this.getPecesDatos().getHuevos(); i++) {
                                    nuevoPez(peces, nuevosPeces);
                                }
                                countCiclos = this.getPecesDatos().getCiclo();
                            } else {
                                for (int i = 0; i < disponible; i++) {
                                    nuevoPez(peces, nuevosPeces);
                                }
                                countCiclos = this.getPecesDatos().getCiclo();
                            }
                        }
                    }
                }
            } else {
                countCiclos--;
            }
        }
        peces.addAll(nuevosPeces);
    }

    /**
     * Posibilidades de morir del pez
     */
    public void morir() {
        double aleatorio = Math.random();
        if (aleatorio <= 0.5) {
            this.setEstaVivo(false);
        }
    }

    /**
     * Añade un pez a la lista de peces
     * 
     * @param peces La lista de peces
     */
    public void nuevoPez(List<Pez> peces, List<Pez> nuevosPeces) {

        boolean sexoNuevoPez = obtenerSexosTanque(peces);
        try {
            Pez nuevoPez = (Pez) peces.get(0).clone();
            nuevoPez.reset();
            nuevoPez.sexo = sexoNuevoPez;
            nuevosPeces.add(nuevoPez);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}