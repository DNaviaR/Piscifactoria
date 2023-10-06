package piscifactoria;

import java.util.ArrayList;

import pez.Pez;

public abstract class Piscifactoria {
    ArrayList<Tanque> tanques = new ArrayList<Tanque>();
    String nombrePiscifactoria;

    public Piscifactoria(String nombrePiscifactoria) {
        this.nombrePiscifactoria=nombrePiscifactoria;
    }

    public ArrayList<Tanque> getTanques() {
        return tanques;
    }

    public void setTanques(ArrayList<Tanque> tanques) {
        this.tanques = tanques;
    }

    public String getNombrePiscifactoria() {
        return nombrePiscifactoria;
    }

    public void setNombrePiscifactoria(String nombrePiscifactoria) {
        this.nombrePiscifactoria = nombrePiscifactoria;
    }

    @Override
    public String toString() {
        return "=============== "+this.nombrePiscifactoria+" ===============\nTanques: "+tanques.size()+"\nOcupacion: \nPeces vivos: \nPeces alimentados: \nPeces adultos: \nHembras / Machos: /\nFertiles: \nAlmacen de comida: ";
    }

}
