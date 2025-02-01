import java.awt.Color;
import java.util.*;

public class LaberintoControlador {
    private Laberinto laberinto;
    private List<int[]> path; 
    
    public LaberintoControlador(int ancho, int alto) {
        this.laberinto = new Laberinto(ancho, alto);
        this.path = new ArrayList<>();
    }

    
    public Laberinto getLaberinto() {
        return this.laberinto;
    }

    
    public void toggleCelda(int row, int col) {
        this.laberinto.toggleCelda(row, col);
    }

    // Método para resolver el laberinto utilizando BFS
    public boolean solveBFS(int startRow, int startCol, int endRow, int endCol) {
        boolean[][] visited = new boolean[laberinto.getAlto()][laberinto.getAncho()];
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], int[]> parentMap = new HashMap<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                reconstructPath(parentMap, current);
                return true;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < laberinto.getAlto() && newCol >= 0 && newCol < laberinto.getAncho() &&
                    !visited[newRow][newCol] && !laberinto.getCelda(newRow, newCol).getEstado()) {
                    int[] neighbor = new int[]{newRow, newCol};
                    queue.add(neighbor);
                    visited[newRow][newCol] = true;
                    parentMap.put(neighbor, current);
                }
            }
        }

        return false; // No se encontró un camino
    }

    // Método para resolver el laberinto utilizando DFS
    public boolean solveDFS(int startRow, int startCol, int endRow, int endCol) {
        boolean[][] visited = new boolean[laberinto.getAlto()][laberinto.getAncho()];
        Stack<int[]> stack = new Stack<>();
        Map<int[], int[]> parentMap = new HashMap<>();
        stack.push(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!stack.isEmpty()) {
            int[] current = stack.pop();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                reconstructPath(parentMap, current);
                return true;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < laberinto.getAlto() && newCol >= 0 && newCol < laberinto.getAncho() &&
                    !visited[newRow][newCol] && !laberinto.getCelda(newRow, newCol).getEstado()) {
                    int[] neighbor = new int[]{newRow, newCol};
                    stack.push(neighbor);
                    visited[newRow][newCol] = true;
                    parentMap.put(neighbor, current);
                }
            }
        }

        return false; // No se encontró un camino
    }

    // Método para resolver el laberinto utilizando una búsqueda normal (por ejemplo, BFS)
    public boolean solveNormal(int startRow, int startCol, int endRow, int endCol) {
        return solveBFS(startRow, startCol, endRow, endCol);
    }

    // Método para resolver el laberinto utilizando BFS con caché
    public boolean solveWithCache(int startRow, int startCol, int endRow, int endCol) {
        boolean[][] visited = new boolean[laberinto.getAlto()][laberinto.getAncho()];
        Queue<int[]> queue = new LinkedList<>();
        Set<String> cache = new HashSet<>();
        Map<int[], int[]> parentMap = new HashMap<>();
        queue.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        cache.add(startRow + "," + startCol);

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            if (row == endRow && col == endCol) {
                reconstructPath(parentMap, current);
                return true;
            }

            for (int[] direction : directions) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (newRow >= 0 && newRow < laberinto.getAlto() && newCol >= 0 && newCol < laberinto.getAncho() &&
                    !visited[newRow][newCol] && !laberinto.getCelda(newRow, newCol).getEstado()) {
                    
                    String key = newRow + "," + newCol;
                    if (!cache.contains(key)) {
                        int[] neighbor = new int[]{newRow, newCol};
                        queue.add(neighbor);
                        visited[newRow][newCol] = true;
                        cache.add(key);
                        parentMap.put(neighbor, current);
                    }
                }
            }
        }

        return false; // No se encontró un camino
    }

    // Método para reconstruir el camino desde el final hasta el inicio
    private void reconstructPath(Map<int[], int[]> parentMap, int[] end) {
        path.clear();
        for (int[] at = end; at != null; at = parentMap.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Invertir el camino para que vaya desde el inicio hasta el final
    }

    public List<int[]> getPath() {
        return path;
    }

    public void markPath(int row, int col, Color pathColor) {
        for (int[] coords : path) {
            laberinto.getCelda(coords[0], coords[1]).setColor(pathColor);
        }
    }
}
