package almacen;

public class Almacen {
    protected int espacio;

    public Almacen(int espacio) {
        this.espacio = espacio;
    }

    public int getEspacio() {
        return espacio;
    }

    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    @Override
    public String toString() {
        return "Almacen [espacio=" + espacio + "]";
    }
    
}
