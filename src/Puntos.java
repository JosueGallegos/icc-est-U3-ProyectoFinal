import java.util.ArrayList;
import java.util.List;

public class Puntos {
     private final List<int[]> puntosRecorridos = new ArrayList<>();

    public void agregarPunto(int x, int y) {
        puntosRecorridos.add(new int[]{x, y});
        System.out.println("Recorriendo: (" + x + ", " + y + ")");
    }

    public List<int[]> getPuntosRecorridos() {
        return puntosRecorridos;
    }
    
}
