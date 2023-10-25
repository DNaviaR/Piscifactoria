package almacen;

public class AlmacenCentral {
    protected int espacio;

    public AlmacenCentral() {
        this.espacio = 200;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "AlmacenCentral [espacio=" + espacio + "]";
    }
    
    
}
