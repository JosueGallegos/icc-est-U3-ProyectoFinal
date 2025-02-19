public class Laberinto {
    private final Celda[][] celdas;
    private final int ancho;
    private final int alto;

    
    public Laberinto(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.celdas = new Celda[alto][ancho];
        
        
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                this.celdas[i][j] = new Celda(false); 
            }
        }
    }

   
    public Celda[][] getCeldas() {
        return this.celdas;
    }

    public int getAncho() {
        return this.ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public Celda getCelda(int row, int col) {
        if (row >= 0 && row < alto && col >= 0 && col < ancho) {
            return this.celdas[row][col];
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de los límites del laberinto");
        }
    }

    public void toggleCelda(int row, int col) {
        if (row >= 0 && row < alto && col >= 0 && col < ancho) {
            this.celdas[row][col].toggleEstado();
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de los límites del laberinto");
        }
    }
}
