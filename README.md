## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

PROYECTO FINAL
En el proyecto final de Estructura de datos hemos hecho un programa donde se trata de un laberinto donde donde se debe utilizar unos metodos como el metodo DFS y BFS donde hemos utilizado 5 clases las cuales osn las siguientes clases (App.java), (Celda.java), (Laberinto.java), (LaberintoControlador.java) y (MazeGenerator.java).

La clase(Celdas.java):
las propiedades que utilizamos son estados, esCamino, color.

estado: es un tipo boolean en la cual indica si la celda es un obstaculo de vuelve un (true) o un camino devuelve un (false).

esCamino: tambien es un tipo boolean en la cual señala si la celda es parte del camino encontrado por un algoritmo de busqueda.

color: es del tipo Color en donde almacena el color de la celda, usando para representar visualmente direfentes estados o caminos.

Los metodos que utilizamos:
utilizamos un metodo Celda que es un tipo boolean en a cual es la inicializacion del estado de la celda la celda comienza como no parte del camino(esCamino = false) y sin color (color = null).

Tambien se hace los get y los set de la propiedades que asignamos:

getEstado: devuelve el estado de la celda (obtaculo o camino).

setEstado: es un tipo boolean en la cual establece el estado de la celda (puede cambiar entre obtaculo y camino).

tooggleEstado: Cambia el estado de la celda a su opuesto (de obstaculo a camino o viceversa).

setPath: Marca la celda como parte del camino encontrado o no.

isPath: Verifica si la celda es parte del camino encontrado.

isNotVisited: Determina si la celda es un camino no visitado (es decir, no es un obtaculo y no es parte del camino).

getColor: Obtiene el color actual de la celda.

setColor: Establece el color de la celda que puede usarse para representar visualmente el estado de la celda en una interfaz grafica.

La clase Celda se uasa para representar una celda individual en una cuadricula en aplicaciones como laberinto maneja la informacion sobre si la celda es un obtaculo o un camino si es parte del camino encontrado por un algoritmo y permitir cambiar su color para represntar visualmente estos estados.


La clase(Laberinto.java)
se utilizaron Celdas, Ancho, Alto como propiedades de esta clase Laberinto.

Celda: es un tipo Celda en donde es una matriz bidimensional que almacena las celdas del laberinto cada celda es un objetivo de la clase celda.

Ancho: es de tipo (int) entero el cual es el numero de columnas del laberinto.

Alto: es de tipo (int) entero el cual es el numero de filas del laberinto.

Para los metodos utilizamos:

Laberinto: en la cual creamos un constructor para la inicializacion del laberinto con las dimensiones especificas (ancho y alto).

getCeldas: es un constructor en el cual devuelve la matriz de celdas del laberinto.

getAncho: es un contructor en el cual devuelve el Ancho osea el numero de columnas que se le pide para que forme el laberinto.

getAlto: es un contructor en el cual devuelve el Alto osea el numero de filas quese le pide para que forme el laberinto.

getCelda: constructor en el que devuelve la celdas en la posicion especifica por los indices de (row)fila y col(columnas). lanza una expresion (indexOutOfBoundsException) si los indices estan fuera de los limites de la matriz.

ToogleCelda: es un constructor en donde altera el estado de las celdas en la posicion especificadas por los indices de row(filas) y col (columnas) en la cual lanza una excepcion si los indices esta fuera de los limites de la matriz.

la clase Laberinto se usa para representar un laberinto como una cuadricula de celdas cada celda puede ser un obstaculo o un camino  la clase proporciona metodos para acceder y modificar las celdas en el laberinto como obtener una celda especifica alterna su estado y acceder a la matriz completa de celda.


La clase (LaberintoControlador.java):

las Propiedades de la clase son Laberinto y Path:

Laberinto: es el tipo laberinto en el que representa el laberinto en el que se realiza las busquedas de caminos para utilizar los metodos en los que vamos a utilizar.

Path: es un tipo List<int[]> que describe la lista que almacena el acmino encontrado desde el punto de inicio hasta el punto de fin.

Metodos de la clase LaberintoControlador:

LaberintoControlador: es la inicializacion del Controlador con un nuevo laberinto de dimensiones Ancho por Alto y vrea una lista vacia para almacenar el camino.

getLaberinto: el que devuelve el objeto Laberinto Asociado con el controlador.

ToggleCelda: Altera el estado de la celda en la posicion especifica por row(filas) y col(columnas) en el laberinto.

SolveBFS: Es la que resuelve el laberinto utilizando el algoritmo de busqueda en anchura(BFS). Marca el camino encontrado uy delvuelve (true) si se encontro un camino, y si no devueleve (false).

SolveDFS: Es la que resuelve el laberinto utilizando el algoritmo de busqueda en Profundidad (DFS). Marca el camino encontrado y devuelve un (true) si se encontro un camino y si no se encontro un camino un (false).

SolveNormal: Es la que resuelve el laberinto utilizando una busqueda normal, que en este caso es equivalente a la busqueda BFS. Marca el camino encontrado y devuelve (true) si se encontro un camino y si no se encontro un camino (false).

SolveWithCache: Es la que resuleve el laberinto utilizando BFS con una cache para evitar revistar celdas y utilizar un conjunto (cache) para realizar un seguimiento de las celdas visitadas y evitar procesarlas nuevamente.

RecontructPath: es quien reconstruye el acmino desde el punto final hasta el inicio usando el mapa de padres(parentMap). Invierte el acmino para que este en el orden correcto desde el inicio hasta el final.

getPath: este es el que devuelve el camino encontrado con  cualquier metodo que se seleccione.

markPath: Marca el camino encontrado en ek laberinto con el color especificado(PathColor) establece el color de cada Celda en el camino.

La clase LaberintoControlador gestiona la Logica de busqueda e caminos dentro del laberinto Proporciona metodos para alterar el estado de las celdas resolver el laberinto utilixando varios algoritmos de Busquedas (BFS, DFS, BFS con cache) y para marcar el acmino encontrado en el laberinto.

La clase (MazeGenerator.java):

Las propiedades de la clase:

Campo de Entrada: es donde se ingresa el valor del Ancho del laberinto y el Alto del Laberinto.

Los Paneles: 

GrindPanel: Es donde se muestra la cuadricula del laberinto segun las medidas que el usuario dio para que se forme el laberinto.

inputPanel: Es un panel que contiene los campos de entrada y el boton para generar el Laberinto.

ActionsPanel: Panel en donde estan los botones para iniciar la busqueda del laberinto usando diferentes algoritmos.

Botones: 

GenerateButton: Boton para que genere un nuevo laberinto con las dimensiones ingresadas.

StartwithBFS: Boton para iniciar la resolucion del laberinto usando el algoritmo BFS(Busqueda en Anchura).

StartwithDFS: bton para iniciar la resolucion del laberinto usando el algoritmo DFS(Buqueda en Profundidad).

StartNormal: Boton para Inicar la resolucion del Laberinto usando la busqueda normal(BFS en este caso).

StartwithCache: Boton para Iniciar la resolucion del alberin to usando BFS con Cache.

clearPathButton: Boton para eliminar el camino encontrado y limpiar los colores en la cuadricula.

Colores:

Cell Wall: el color de las Celdas que son Paredes en el cual son el color blanco el color de la cuadricula.

Cell Path BFS: Color para las celdas del camino encontrado usando BFS en el cual es el color Verde que es para este metodo.

Cell Path DFS: Color para las celdas del camino encontrado usando las Buqueda normal en el cual este es el color Azul.

Cell Path Normal: Color para las celdas del camino encontrado usando la busqueda Normal en el cuale es el color Rojo.

Cell Path Cache: Color para las celdas del camino encontrado usando BFS con cache en cual es el color Naranja.

Cell Open: Color para la Celda Abierta o vacia en el cuale es el color Negro.

Metodos:

MazeGenerator: Configura la interfaz grafica Incluyendo la creacion de la ventana principal, paneles y botones tambien agrega ActionListeners para manejar los eventos de los botones.

generateGrid: Crea una nueva cuadratica de botones para representar las celdas del laberinto segun las dimensiones ingresadas cada boton respresenta una celda del laberinto que puede ser alterada entre pared y camino al hacer clic.

StartMazaGeneration: Inicia a resolucion del laberinto usando el algoritmo especificado (BFS, DFS, NORMAL, CACHE) utilizando la interfaz grafica y mostrando el camino encontrado con color correspondiente.

ActualizarInterfaz: Actualiza el color de una celda en la cuadricula grfica para reflejar su estado (Pared, Camino, o Vacio).

ClearPath: Limpia los colores de las celdas que no son paredes para eliminar el camino encontrado anteriormente.

la Clase MazeGenerator proporciona una intefaz grafica de usuario (GUI) para generar laberintos visualizar y manipular su estado y resolver usando diferentes algoritmos de busquedas que permite a los usuarios ingresar las dimensiones del laberinto generar una cuadricula de celdas altenar el estado de las celdas y resolver el laberinto utilizando algoritmos de busqueda.

EL la clase App.java
 esta clase nos permite correr el porgrama donde podemos ver que esta unidad todas las clases 
 


