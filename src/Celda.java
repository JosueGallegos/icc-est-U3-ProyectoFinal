import java.awt.Color;

public class Celda {
    private boolean estado; 
    private boolean esCamino; 
    private Color color; 

    
    public Celda(boolean estadoInicial) {
        this.estado = estadoInicial;
        this.esCamino = false; 
        this.color = null; 
    }

    
    public boolean getEstado() {
        return this.estado;
    }

    
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    public void toggleEstado() {
        this.estado = !this.estado;
    }

    
    public void setPath(boolean esCamino) {
        this.esCamino = esCamino;
    }

    
    public boolean isPath() {
        return this.esCamino;
    }

    
    public boolean isNotVisited() {
        return !this.estado && !this.esCamino;
    }

   
    public Color getColor() {
        return this.color;
    }

    
    public void setColor(Color color) {
        this.color = color;
    }
}
