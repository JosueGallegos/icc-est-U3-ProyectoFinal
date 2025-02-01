import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MazeGenerator {
    private JTextField widthField;
    private JTextField heightField;
    private JPanel gridPanel;
    private JButton generateButton;
    private LaberintoControlador controlador;
    private JButton StartwithBFS;
    private JButton StartwithDFS;
    private JButton StartNormal;
    private JButton Startwithcache;
    private JButton clearPathButton;

    // Definir colores para las celdas
    private static final Color CELL_WALL = Color.BLACK;
    private static final Color CELL_PATH_BFS = Color.GREEN;
    private static final Color CELL_PATH_DFS = Color.BLUE;
    private static final Color CELL_PATH_NORMAL = Color.RED;
    private static final Color CELL_PATH_CACHE = Color.ORANGE;
    private static final Color CELL_OPEN = Color.WHITE;

    public MazeGenerator() {
        // Crear la ventana principal
        JFrame frame = new JFrame("Maze Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout());

        // Crear el panel para la entrada de dimensiones
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        inputPanel.add(new JLabel("Ancho:"));
        widthField = new JTextField(5);
        inputPanel.add(widthField);

        inputPanel.add(new JLabel("Alto:"));
        heightField = new JTextField(5);
        inputPanel.add(heightField);

        generateButton = new JButton("Generar Laberinto");
        inputPanel.add(generateButton);

        clearPathButton = new JButton("Eliminar Camino");
        inputPanel.add(clearPathButton);

        frame.add(inputPanel, BorderLayout.NORTH);

        // Crear el panel para la cuadrícula del laberinto
        gridPanel = new JPanel();
        frame.add(gridPanel, BorderLayout.CENTER);

        // Crear el panel para los botones de acciones
        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new FlowLayout());

        StartwithBFS = new JButton("Start with BFS");
        StartwithDFS = new JButton("Start with DFS");
        StartNormal = new JButton("Start Normal");
        Startwithcache = new JButton("Start with Cache");

        actionsPanel.add(StartwithBFS);
        actionsPanel.add(StartwithDFS);
        actionsPanel.add(StartNormal);
        actionsPanel.add(Startwithcache);

        frame.add(actionsPanel, BorderLayout.SOUTH);

        // Añadir el ActionListener al botón de generar
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateGrid();
            }
        });

        // Añadir ActionListeners a los botones de acciones
        StartwithBFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMazeGeneration("BFS");
            }
        });

        StartwithDFS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMazeGeneration("DFS");
            }
        });

        StartNormal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMazeGeneration("Normal");
            }
        });

        Startwithcache.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startMazeGeneration("Cache");
            }
        });

        clearPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearPath();
            }
        });

        // Mostrar la ventana
        frame.setVisible(true);
    }

    public void generateGrid() {
        long startTime = System.currentTimeMillis(); // Inicio del temporizador
        try {
            int width = Integer.parseInt(widthField.getText());
            int height = Integer.parseInt(heightField.getText());
     
            // Inicializar el controlador del laberinto
            controlador = new LaberintoControlador(width, height);
     
            // Configurar el panel de la cuadrícula
            gridPanel.removeAll();
            gridPanel.setLayout(new GridLayout(height, width));
     
            // Crear los botones para las celdas del laberinto
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    JButton cellButton = new JButton();
                    cellButton.setBackground(CELL_OPEN); // Celdas abiertas por defecto
                    cellButton.setOpaque(true);
                    cellButton.setBorderPainted(true);
     
                    int finalRow = row;
                    int finalCol = col;
     
                    cellButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            controlador.toggleCelda(finalRow, finalCol);
                            boolean estado = controlador.getLaberinto().getCelda(finalRow, finalCol).getEstado();
                            cellButton.setBackground(estado ? CELL_WALL : CELL_OPEN);
                        }
                    });
     
                    gridPanel.add(cellButton);
                }
            }
     
            // Actualizar la interfaz gráfica
            gridPanel.revalidate();
            gridPanel.repaint();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese números enteros válidos para ancho y alto.");
        }
        long endTime = System.currentTimeMillis(); // Fin del temporizador
        double elapsedSeconds = (endTime - startTime) / 1000.0;
        System.out.println("Tiempo de ejecución de generateGrid: " + elapsedSeconds + " segundos");
    }
    
    public void startMazeGeneration(String mode) {
        new SwingWorker<Void, int[]>() {
            boolean success = false;
            Color pathColor;
            List<int[]> path;
     
            @Override
            protected Void doInBackground() throws Exception {
                long startTime = System.currentTimeMillis(); // Inicio del temporizador
                switch (mode) {
                    case "BFS":
                        pathColor = CELL_PATH_BFS;
                        success = controlador.solveBFS(0, 0, controlador.getLaberinto().getAlto() - 1, controlador.getLaberinto().getAncho() - 1);
                        break;
                    case "DFS":
                        pathColor = CELL_PATH_DFS;
                        success = controlador.solveDFS(0, 0, controlador.getLaberinto().getAlto() - 1, controlador.getLaberinto().getAncho() - 1);
                        break;
                    case "Normal":
                        pathColor = CELL_PATH_NORMAL;
                        success = controlador.solveNormal(0, 0, controlador.getLaberinto().getAlto() - 1, controlador.getLaberinto().getAncho() - 1);
                        break;
                    case "Cache":
                        pathColor = CELL_PATH_CACHE;
                        success = controlador.solveWithCache(0, 0, controlador.getLaberinto().getAlto() - 1, controlador.getLaberinto().getAncho() - 1);
                        break;
                    default:
                        throw new IllegalArgumentException("Modo de generación desconocido: " + mode);
                }
                path = controlador.getPath();
                for (int[] step : path) {
                    controlador.markPath(step[0], step[1], pathColor);
                    publish(step);
                    Thread.sleep(100); // Pausa para animación
                }
                long endTime = System.currentTimeMillis(); // Fin del temporizador
                double elapsedSeconds = (endTime - startTime) / 1000.0;
                System.out.println("Tiempo de ejecución para " + mode + ": " + elapsedSeconds + " segundos");
                return null;
            }
     
            @Override
            protected void process(List<int[]> chunks) {
                for (int[] step : chunks) {
                    actualizarInterfaz(step[0], step[1]);
                }
            }
     
            @Override
            protected void done() {
                if (success) {
                    JOptionPane.showMessageDialog(null, "Laberinto resuelto con éxito usando " + mode + "!");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un camino en el laberinto usando " + mode + ".");
                }
            }
        }.execute();
    }
    
    

    private void actualizarInterfaz(int row, int col) {
        Component component = gridPanel.getComponent(row * controlador.getLaberinto().getAncho() + col);
        if (component instanceof JButton) {
            JButton cellButton = (JButton) component;
            boolean estado = controlador.getLaberinto().getCelda(row, col).getEstado();
            Color cellColor = controlador.getLaberinto().getCelda(row, col).getColor();
            cellButton.setBackground(estado ? CELL_WALL : (cellColor != null ? cellColor : CELL_OPEN));
        }
    }

    private void clearPath() {
        // Recorre todas las celdas del laberinto
        for (int row = 0; row < controlador.getLaberinto().getAlto(); row++) {
            for (int col = 0; col < controlador.getLaberinto().getAncho(); col++) {
                // Si la celda no es una pared, borra el color de la celda
                if (!controlador.getLaberinto().getCelda(row, col).getEstado()) {
                    controlador.getLaberinto().getCelda(row, col).setColor(null);
                    actualizarInterfaz(row, col); // Actualiza la interfaz
                }
            }
        }
    }
}
