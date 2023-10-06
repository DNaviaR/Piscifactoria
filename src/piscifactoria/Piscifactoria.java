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
        return "=============== "+this.nombrePiscifactoria+" ===============\n" +
            "Tanques: "+tanques.size()+"\n" +
            "Ocupacion: \n" +
            "Peces vivos: \n" + 
            "Peces alimentados: \n" +
            "Peces adultos: \n" +
            "Hembras / Machos: /\n" +
            "Fertiles: \n" +
            "Almacen de comida: ";
    }

}
