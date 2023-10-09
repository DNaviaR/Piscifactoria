package piscifactoria;

import java.util.ArrayList;

public abstract class Piscifactoria {
    ArrayList<Tanque> tanques = new ArrayList<Tanque>();
    String nombrePiscifactoria;

    public Piscifactoria(String nombrePiscifactoria) {
        this.nombrePiscifactoria = nombrePiscifactoria;
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
        return "Piscifactoria [tanques=" + tanques + ", nombrePiscifactoria=" + nombrePiscifactoria + "]";
    }

    /**
     * Método que muestra toda la información de la piscifactoria
     * 
     * @return
     */
    public String showStatus() {
        return "=============== " + this.nombrePiscifactoria + " ===============\n" +
                "Tanques: " + tanques.size() + "\n" +
                "Ocupacion: "+peces()+" / "+pecesMax()+" ("+(float)((peces()*100)/pecesMax())+"%)\n" +
                "Peces vivos: "+pecesVivos()+" / "+peces()+" ("+(float)((pecesVivos()*100)/peces())+"%)\n" +
                "Peces alimentados: "+pecesAlimentados()+" / "+pecesVivos()+" ("+(float)((pecesAlimentados()*100)/pecesVivos())+"%)\n" +
                "Peces adultos: "+pecesAdultos()+" / "+pecesVivos()+" ("+(float)((pecesAdultos()*100)/pecesVivos())+"%)\n" +
                "Hembras / Machos: "+pecesHembras()+" / "+(pecesVivos()-pecesHembras())+"\n" +
                "Fertiles: "+pecesFertiles()+" / "+pecesVivos()+"\n" +
                "Almacen de comida: ";
    }
    /**
     * Calcula es espacio total de los tanques de la piscifactoria
     * @return
     */
    public int pecesMax(){
        int pecesMax=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesMax+=tanques.get(i).getEspacio();
        }
        return pecesMax;
    }
    /**
     * Calcula el numero de peces que hay en total en la piscifactoria
     * @return
     */
    public int peces(){
        int peces=0;
        for (int i = 0; i < tanques.size(); i++) {
            peces+=tanques.get(i).getPeces().size();
        }
        return peces;
    }

    public int pecesVivos(){
        int pecesVivos=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesVivos+=tanques.get(i).getVivos();
        }
        return pecesVivos;
    }

    public int pecesAlimentados(){
        int pecesAlimentados=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesAlimentados+=tanques.get(i).getAlimentados();
        }
        return pecesAlimentados;
    }

    public int pecesAdultos(){
        int pecesAdultos=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesAdultos+=tanques.get(i).getAdultos();
        }
        return pecesAdultos;
    }

    public int pecesHembras(){
        int pecesHembras=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesHembras+=tanques.get(i).getHembras();
        }
        return pecesHembras;
    }

    public int pecesFertiles(){
        int pecesFertiles=0;
        for (int i = 0; i < tanques.size(); i++) {
            pecesFertiles+=tanques.get(i).getFertiles();
        }
        return pecesFertiles;
    }

    /**
     * Método que muestra la información de todos los tanques
     */
    public void showTankStatus() {
        for (Tanque tanque : tanques) {
            tanque.showCapacity(this.nombrePiscifactoria);
        }
    }

    /**
     * Muestra la información de los peces del tanque
     * @param posicionTanque
     */
    public void showFishStatus(int posicionTanque) {
        tanques.get(posicionTanque).showFishStatus();
    }

    /**
     * Método que llama a un tanque concreto
     * 
     * @param posicionTanque
     */
    public void showCapacity(int posicionTanque) {
        tanques.get(posicionTanque).showCapacity(nombrePiscifactoria);
    }

    public void nextDay(int comida){
        for (Tanque tanque : tanques) {
            tanque.nextDay(comida);
        }
    }
}
