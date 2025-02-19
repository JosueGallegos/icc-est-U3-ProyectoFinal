import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MazeGenerator {

    private final JTextField widthField;
    private final JTextField heightField;
    private final JTextField startXField;
    private final JTextField startYField;
    private final JPanel gridPanel;
    private final JButton generateButton;
    private final JButton clearPathButton;
    private final JButton StartwithBFS;
    private final JButton StartwithDFS;
    private final JButton StartNormal;
    private final JButton Startwithcache;
    private LaberintoControlador controlador;
    private final Puntos puntosLogger;
    private final JLabel coordinatesLabel; // Etiqueta para mostrar las coordenadas
    private final JLabel timeLabel; // Etiqueta para mostrar el tiempo de recorrido
    private final JTextArea coordinatesTextArea; // Área de texto para mostrar las coordenadas

    private static final Color CELL_WALL = Color.BLACK;
    private static final Color CELL_PATH_BFS = Color.GREEN;
    private static final Color CELL_PATH_DFS = Color.BLUE;
    private static final Color CELL_PATH_NORMAL = Color.RED;
    private static final Color CELL_PATH_CACHE = Color.ORANGE;
    private static final Color CELL_OPEN = Color.WHITE;

    public MazeGenerator() {
        JFrame frame = new JFrame("Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Ancho:"));
        widthField = new JTextField(5);
        inputPanel.add(widthField);
        inputPanel.add(new JLabel("Alto:"));
        heightField = new JTextField(5);
        inputPanel.add(heightField);

        inputPanel.add(new JLabel("Inicio X:"));
        startXField = new JTextField(5);
        inputPanel.add(startXField);
        inputPanel.add(new JLabel("Inicio Y:"));
        startYField = new JTextField(5);
        inputPanel.add(startYField);

        generateButton = new JButton("Generar Laberinto");
        inputPanel.add(generateButton);

        clearPathButton = new JButton("Eliminar Camino");
        inputPanel.add(clearPathButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        gridPanel = new JPanel();
        frame.add(gridPanel, BorderLayout.CENTER);

        JPanel actionsPanel = new JPanel(new FlowLayout());
        StartwithBFS = new JButton("Start with BFS");
        StartwithDFS = new JButton("Start with DFS");
        StartNormal = new JButton("Start Normal");
        Startwithcache = new JButton("Start with Cache");
        actionsPanel.add(StartwithBFS);
        actionsPanel.add(StartwithDFS);
        actionsPanel.add(StartNormal);
        actionsPanel.add(Startwithcache);
        frame.add(actionsPanel, BorderLayout.SOUTH);

        // Crear una etiqueta para mostrar las coordenadas
        coordinatesLabel = new JLabel("Coordenadas: ");
        actionsPanel.add(coordinatesLabel);

        // Crear un área de texto para mostrar las coordenadas
        coordinatesTextArea = new JTextArea(5, 20);
        coordinatesTextArea.setEditable(false); // Deshabilitar la edición
        coordinatesTextArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(coordinatesTextArea);
        actionsPanel.add(scrollPane);

        // Crear una etiqueta para mostrar el tiempo de recorrido
        timeLabel = new JLabel("Tiempo: 0 ms");
        actionsPanel.add(timeLabel);

        generateButton.addActionListener(e -> generateGrid());
        StartwithBFS.addActionListener(e -> startMazeGeneration("BFS"));
        StartwithDFS.addActionListener(e -> startMazeGeneration("DFS"));
        StartNormal.addActionListener(e -> startMazeGeneration("Normal"));
        Startwithcache.addActionListener(e -> startMazeGeneration("Cache"));
        clearPathButton.addActionListener(e -> clearPath());

        frame.setVisible(true);
        puntosLogger = new Puntos();
    }

    // Método para generar el laberinto
    private void generateGrid() {
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());

            controlador = new LaberintoControlador(width, height);
            gridPanel.removeAll(); // Limpiar el panel de la grid
            gridPanel.setLayout(new GridLayout(height, width));

            // Crear el laberinto (celdas)
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    JButton cell = new JButton();
                    cell.setBackground(CELL_OPEN);

                    // Agregar un MouseListener para hacer clic en la celda y marcarla como obstáculo
                    final int row = i;
                    final int col = j;
                    cell.addMouseListener(new java.awt.event.MouseAdapter() {
                        @Override
                        public void mousePressed(java.awt.event.MouseEvent evt) {
                            if (cell.getBackground() == CELL_OPEN) {
                                // Si la celda está libre, la convertimos en obstáculo
                                cell.setBackground(CELL_WALL);
                                controlador.markObstacle(row, col); // Marcar la celda como obstáculo en el controlador
                            } else if (cell.getBackground() == CELL_WALL) {
                                // Si la celda es un obstáculo, la volvemos a dejar libre
                                cell.setBackground(CELL_OPEN);
                                controlador.removeObstacle(row, col); // Remover el obstáculo en el controlador
                            }
                        }
                    });

                    gridPanel.add(cell);
                }
            }

            gridPanel.revalidate();
            gridPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese dimensiones válidas.");
        }
    }

   // Método para limpiar el camino generado
   private void clearPath() {
    if (controlador != null) {
        Component[] cells = gridPanel.getComponents();
        int width = controlador.getLaberinto().getAncho();
        int height = controlador.getLaberinto().getAlto();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                JButton cell = (JButton) cells[i * width + j];
                Color currentColor = cell.getBackground();
                
                // Si la celda no es un obstáculo ni está abierta, la limpiamos (es decir, si es un camino)
                if (currentColor.equals(CELL_PATH_BFS) || currentColor.equals(CELL_PATH_DFS) ||
                    currentColor.equals(CELL_PATH_NORMAL) || currentColor.equals(CELL_PATH_CACHE)) {
                    cell.setBackground(CELL_OPEN);
                    cell.removeAll(); // Eliminar cualquier componente en la celda (coordenadas)
                    cell.revalidate(); // Refrescar el componente
                    cell.repaint(); // Actualizar la visualización
                }
            }
        }
        
        // Limpiar el área de texto de coordenadas
        coordinatesTextArea.setText(""); 
    }
}




    // Método para iniciar el algoritmo de generación de laberinto
    public void startMazeGeneration(String mode) {
        try {
            int startX = Integer.parseInt(startXField.getText());
            int startY = Integer.parseInt(startYField.getText());
            int endX = controlador.getLaberinto().getAlto() - 1;
            int endY = controlador.getLaberinto().getAncho() - 1;
    
            // Verificar si la celda de inicio es un obstáculo
            Component[] cells = gridPanel.getComponents();
            JButton startCell = (JButton) cells[startX * controlador.getLaberinto().getAncho() + startY];
    
            if (startCell.getBackground().equals(CELL_WALL)) {
                JOptionPane.showMessageDialog(null, "El punto de inicio es un obstáculo. Elija otra coordenada.");
                return; // Salir del método sin ejecutar el algoritmo
            }
    
            new SwingWorker<Void, int[]>() {
                boolean success = false;
                Color pathColor;
                List<int[]> path;
                long startTime, endTime;
    
              
                @Override
protected Void doInBackground() throws Exception {
    startTime = System.currentTimeMillis();
    switch (mode) {
        case "BFS" -> {
            pathColor = CELL_PATH_BFS;
            success = controlador.solveBFS(startX, startY, endX, endY);
        }
        case "DFS" -> {
            pathColor = CELL_PATH_DFS;
            success = controlador.solveDFS(startX, startY, endX, endY);
        }
        case "Normal" -> {
            pathColor = CELL_PATH_NORMAL;
            success = controlador.solveNormal(startX, startY, endX, endY);
        }
        case "Cache" -> {
            pathColor = CELL_PATH_CACHE;
            success = controlador.solveWithCache(startX, startY, endX, endY);
        }
        default -> throw new IllegalArgumentException("Modo desconocido: " + mode);
    }

    if (!success) {
        endTime = System.currentTimeMillis();
        return null; // Salimos si no hay solución
    }

    path = controlador.getPath();
    for (int[] step : path) {
        controlador.markPath(step[0], step[1], pathColor); // Pintar la celda con el color del camino
        publish(step);
        Thread.sleep(100);
    }
    
    
    endTime = System.currentTimeMillis();
    return null;
}

    
                @Override
                protected void process(List<int[]> chunks) {
                    for (int[] step : chunks) {
                        actualizarInterfaz(step[0], step[1]);
                    }
                }
    
                private void actualizarInterfaz(int i, int j) {
                    Component[] cells = gridPanel.getComponents();
                    JButton cell = (JButton) cells[i * controlador.getLaberinto().getAncho() + j];
                    cell.setBackground(pathColor); // Aquí se cambia el color de la celda
                
                    // También asegúrate de limpiar cualquier otro componente anterior en la celda
                    cell.removeAll();
                
                    JLabel coordinateLabel = new JLabel("(" + i + ", " + j + ")");
                    coordinateLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    coordinateLabel.setVerticalAlignment(SwingConstants.CENTER);
                    coordinateLabel.setForeground(Color.BLACK);
                
                    cell.add(coordinateLabel);
                    cell.revalidate();
                    cell.repaint();
                
                    // Actualizar el JTextArea con las coordenadas
                    coordinatesTextArea.append("(" + i + ", " + j + ")\n");
                    coordinatesTextArea.setCaretPosition(coordinatesTextArea.getDocument().getLength()); // Auto-scroll
                }
                

                @Override
                protected void done() {
                    long duration = endTime - startTime;
                    timeLabel.setText("Tiempo: " + duration + " ms");
                
                    if (!success) {
                        JOptionPane.showMessageDialog(null, "No se encontró un camino.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Laberinto resuelto con éxito!");
                    }
                }
                
                
            }.execute();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese valores válidos para X e Y de inicio.");
        }
    }
    
}
