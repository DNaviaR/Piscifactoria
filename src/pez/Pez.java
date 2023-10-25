package pez;

import java.util.Random;
import propiedades.PecesDatos;

public abstract class Pez {
    protected PecesDatos pc;
    protected int edad;
    protected boolean sexo; // True=H / False=M
    protected boolean estaVivo = true;
    protected boolean alimentado = false;
    protected boolean adulto = false;
    protected boolean esFertil;

    public Pez(boolean sexo, PecesDatos pc) {
        this.sexo = sexo;
        this.pc = pc;

    }

    public String getNombre() {
        return pc.getNombre();
    }

    public String getCientifico() {
        return pc.getCientifico();
    }

    public int getEdad() {
        return edad;
    }

    public boolean getSexo() {
        return sexo;
    }

    public boolean isEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(boolean estaVivo) {
        this.estaVivo = estaVivo;
    }

    public boolean isAlimentado() {
        return alimentado;
    }

    public void setAlimentado(boolean alimentado) {
        this.alimentado = alimentado;
    }

    public boolean isAdulto() {
        return adulto;
    }

    public void setAdulto(boolean adulto) {
        this.adulto = adulto;
    }

    public boolean isEsFertil() {
        return esFertil;
    }

    public void setEsFertil(boolean esFertil) {
        this.esFertil = esFertil;
    }

    @Override
    public String toString() {
        return "Pez [nombre=" + pc.getNombre() + ", cientifico=" + pc.getCientifico() + ", edad=" + edad
                + ", sexo=" + sexo + ", estaVivo=" + estaVivo + ", alimentado=" + alimentado + ", adulto=" + adulto
                + ", esFertil=" + esFertil + "]";
    }

    public void showStatus() {
        System.out.println("--------------- " + pc.getNombre() + " ---------------");
        System.out.println("Edad: " + edad + " días");
        System.out.println("Sexo: " + (getSexo() ? "H" : "M"));
        System.out.println("Vivo: " + (estaVivo ? "Si" : "No"));
        System.out.println("Alimentado: " + (alimentado ? "Si" : "No"));
        System.out.println("Adulto: " + (adulto ? "Si" : "No"));
        System.out.println("Fértil: " + (esFertil ? "Si" : "No"));
    }

    public void grow(int comida) {
        if (estaVivo) {
            // Simular la alimentación (50% de probabilidad de alimentarse).
            Random random = new Random();
            if (!alimentado) {//Cambiar cando teña metodo para alimentar
                double probabilidadMuerte = random.nextDouble();
                if (probabilidadMuerte <= 0.5) {
                    estaVivo = false;
                    return;
                }
            }

            // Aumentar la edad en 1 día.
            edad++;

            // Verificar la madurez y fertilidad.
            if (edad == pc.getMadurez()) {
                adulto = true;
                esFertil = true;
            }           
            //Añadir reproduçao dos peixiños
        }
    }

    public void reset() {
        edad = 0;
        estaVivo = true;
        alimentado = false;
        adulto = false;
        esFertil = false;
    }
}
