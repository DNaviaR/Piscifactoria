package pez;

import java.util.Random;

import propiedades.AlmacenPropiedades;
import propiedades.PecesDatos;

public abstract class Pez {
    PecesDatos pc;
    private String nombre;
    private String cientifico;
    private int edad;
    private char sexo;
    private boolean estaVivo=true;
    private boolean alimentado=false;
    private boolean adulto=false;
    private boolean esFertil;

    public Pez(char sexo, PecesDatos pc) {
        this.sexo=sexo;
        this.pc=pc;
        this.nombre=pc.getNombre();
        this.cientifico=pc.getCientifico();
    }

    public String getNombre() {
        return nombre;
    }

    public String getCientifico() {
        return cientifico;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getSexo() {
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
        return "Pez [nombre=" + nombre + ", cientifico=" + cientifico + ", edad=" + edad
                + ", sexo=" + sexo + ", estaVivo=" + estaVivo + ", alimentado=" + alimentado + ", adulto=" + adulto
                + ", esFertil=" + esFertil + "]";
    }

    public void showStatus() {
        System.out.println("--------------- " + nombre + " ---------------");
        System.out.println("Edad: " + edad + " días");
        System.out.println("Sexo: " + sexo);
        System.out.println("Vivo: " + (estaVivo ? "Si" : "No"));
        System.out.println("Alimentado: " + (alimentado ? "Si" : "No"));
        System.out.println("Adulto: " + (adulto ? "Si" : "No"));
        System.out.println("Fértil: " + (esFertil ? "Si" : "No"));
    }

    public void grow() {
        if (!estaVivo) {
            return; // Si el pez está muerto, no realiza ninguna acción.
        }

        // Simular la alimentación (50% de probabilidad de alimentarse).
        Random random = new Random();
        alimentado = random.nextBoolean();

        // Aumentar la edad en 1 día.
        edad++;

        // Verificar la madurez y fertilidad.
        if (edad >= 10) {
            adulto = true;
            esFertil = true;
        }

        // Verificar si el pez muere (50% de probabilidad si no está alimentado).
        if (!alimentado) {
            double probabilidadMuerte = random.nextDouble();
            if (probabilidadMuerte <= 0.5) {
                estaVivo = false;
            }
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
