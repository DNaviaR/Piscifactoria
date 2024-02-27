package pez;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import commons.Simulador;
import piscifactoria.Piscifactoria;
import propiedades.PecesDatos;

/**
 * Clase abstracta que representa un pez.
 */
public abstract class Pez implements Cloneable {
    /**
     * Los datos del tipo de pez
     */
    protected transient PecesDatos pc;
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
    protected boolean vivo = true;
    /**
     * Si el pez es adulto
     */
    @SerializedName("maduro")
    protected boolean adulto = false;
    /**
     * Si el pez es fértil
     */
    protected boolean fertil;
    /**
     * Los ciclos de reproducción
     */
    @SerializedName("ciclo")
    protected int countCiclos = 0;
    /**
     * El estado de alimentación del pez
     */
    protected boolean alimentado = false;
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
     * Establece los dias de vida del pez.
     * 
     * @param edad la nueva edad del pez
     */
    public void setEdad(int edad) {
        this.edad = edad;
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
    public boolean isvivo() {
        return vivo;
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
     * Establece las propiedades del pez
     * 
     * @param pc las nuevas propiedades del pez
     */
    public void setPecesDatos(PecesDatos pc) {
        this.pc = pc;
    }

    /**
     * Establece si el pez está vivo o no.
     * 
     * @param vivo el nuevo estado del pez
     */
    public void setvivo(boolean vivo) {
        this.vivo = vivo;
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
    public boolean isfertil() {
        return fertil;
    }

    /**
     * Establece si el pez es fértil o no.
     * 
     * @param fertil el nuevo estado del pez
     */
    public void setfertil(boolean fertil) {
        this.fertil = fertil;
    }

    /**
     * Comprueba los dias de ciclo de fertilidad del pez
     *
     * @return El ciclo de fertilidad del pez
     */
    public int getCountCiclos() {
        return countCiclos;
    }

    /**
     * Establece el ciclo de fertilidad del pez.
     * 
     * @param countCiclos el nuevo estado del pez
     */
    public void setCountCiclos(int countCiclos) {
        this.countCiclos = countCiclos;
    }

    /**
     * Devuelve una representación en cadena del pez.
     * 
     * @return Una cadena con información del pez.
     */
    @Override
    public String toString() {
        return "Pez [nombre=" + pc.getNombre() + ", cientifico=" + pc.getCientifico() + ", edad=" + edad
                + ", sexo=" + sexo + ", vivo=" + vivo + ", alimentado=" + alimentado + ", adulto=" + adulto
                + ", fertil=" + fertil + "]";
    }

    /**
     * Muestra el estado del pez
     */
    public void showStatus() {
        System.out.println("--------------- " + pc.getNombre() + " ---------------");
        System.out.println("Edad: " + edad + " días");
        System.out.println("Sexo: " + (getSexo() ? "H" : "M"));
        System.out.println("Vivo: " + (vivo ? "Si" : "No"));
        System.out.println("Alimentado: " + (alimentado ? "Si" : "No"));
        System.out.println("Adulto: " + (adulto ? "Si" : "No"));
        System.out.println("Fértil: " + (fertil ? "Si" : "No"));
    }

    /**
     * Hace crecer un día el pez, realizando toda la lógica:
     * 1. Se alimenta.
     * 2. Si no está alimentado tiene, por defecto, un 50% de morir.
     * 3. Aumenta en 1 su edad.
     * 4. Se verifica su madurez y su fertilidad.
     * 
     * Modificación: Ahora el pez tiene un 5% de morir en los dias pares
     * 
     * @param comida La comida que se utiliza
     * @param peces  La lista de peces
     */
    public void grow(List<Pez> peces, Piscifactoria pisci) {
        if (this.isvivo()) {
            this.morir();
            // Aumentar la edad en 1 día.
            edad++;
            // Verificar la madurez y fertilidad.
            if (edad == pc.getMadurez()) {
                adulto = true;
                fertil = true;
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
        vivo = true;
        alimentado = false;
        adulto = false;
        fertil = false;
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
     * @param peces   Lista de peces candidatos a la reproducción
     * @param espacio Espacio total de la lista
     */
    public void reproducirse(List<Pez> peces, int espacio) {
        ArrayList<Pez> nuevosPeces = new ArrayList<>();
        if (this.getSexo() == true) {
            if (this.fertil == true && countCiclos <= 0) {
                for (Pez pez : peces) {
                    if (pez != null && pez.getSexo() != this.getSexo() && pez.isfertil()) {
                        int libre = espacio - peces.size();
                        int disponible = libre - nuevosPeces.size();
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
        if ((this.alimentado == false && aleatorio < 0.5) || (this.edad % 2 == 0 && aleatorio < 0.05)) {
            this.setvivo(false);
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
            Simulador.estadisticas.registrarNacimiento(nuevoPez.getNombre());
        } catch (CloneNotSupportedException e) {
            Simulador.escribirError("Error al crear un nuevo pez");
        }

    }
}