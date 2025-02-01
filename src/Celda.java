import java.awt.Color;

public class Celda {
    private boolean estado; 
    private boolean esCamino; 
    private Color color; 

    // Constructor para inicializar el estado de la celda
    public Celda(boolean estadoInicial) {
        this.estado = estadoInicial;
        this.esCamino = false; 
        this.color = null; 
    }

    // Método para obtener el estado de la celda
    public boolean getEstado() {
        return this.estado;
    }

    // Método para establecer el estado de la celda
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    // Método para alternar el estado de la celda
    public void toggleEstado() {
        this.estado = !this.estado;
    }

    // Método para marcar la celda como parte del camino
    public void setPath(boolean esCamino) {
        this.esCamino = esCamino;
    }

    // Método para verificar si la celda es parte del camino
    public boolean isPath() {
        return this.esCamino;
    }

    // Método para verificar si la celda no ha sido visitada (es un camino y no es un obstáculo)
    public boolean isNotVisited() {
        return !this.estado && !this.esCamino;
    }

    // Método para obtener el color de la celda
    public Color getColor() {
        return this.color;
    }

    // Método para establecer el color de la celda
    public void setColor(Color color) {
        this.color = color;
    }
}
