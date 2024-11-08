import java.util.*;

public class Grafo {
    private int V; // vertices
    private List<List<Integer>> listaAdj;

    // Constructor que convierte la matriz de adyacencia a una lista de adyacencia
    public Grafo(int[][] matrizAdyacencia) {
        this.V = matrizAdyacencia.length;
        this.listaAdj = new ArrayList<>();

        // Inicializar lista de adyacencia con listas vacías
        for (int i = 0; i < V; i++) {
            listaAdj.add(new ArrayList<>());
        }

        // Llenar la lista de adyacencia a partir de la matriz
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (matrizAdyacencia[i][j] == 1) {
                    listaAdj.get(i).add(j); // Agregar j a la lista de adyacencia de i
                }
            }
            // Ordenar los vecinos de cada nodo en orden ascendente
            Collections.sort(listaAdj.get(i));
        }
    }

    // Metodo BFS
    public List<Integer> bfs(int inicio) {
        List<Integer> camino = new ArrayList<>(); // Lista para guardar el recorrido
        boolean[] visitados = new boolean[V]; // Arreglo para marcar los nodos visitados
        Queue<Integer> cola = new LinkedList<>();

        cola.offer(inicio); // Iniciar desde el nodo "inicio"
        visitados[inicio] = true;

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            camino.add(actual);

            // Revisar los nodos vecinos del actual
            for (int vecino : listaAdj.get(actual)) {
                if (!visitados[vecino]) {
                    visitados[vecino] = true;
                    cola.offer(vecino);
                }
            }
        }

        return camino;
    }

    // Metodo DFS
    public List<Integer> dfs(int inicio) {
        List<Integer> camino = new ArrayList<>();
        boolean[] visitados = new boolean[V]; // Arreglo para marcar los nodos visitados
        dfsRecursivo(inicio, visitados, camino);
        return camino;
    }

    // Metodo auxiliar recursivo para DFS
    private void dfsRecursivo(int nodo, boolean[] visitados, List<Integer> camino) {
        visitados[nodo] = true;
        camino.add(nodo);

        // Revisar los vecinos del nodo actual
        for (int vecino : listaAdj.get(nodo)) {
            if (!visitados[vecino]) {
                dfsRecursivo(vecino, visitados, camino);
            }
        }
    }

    // Imprimir la lista de adyacencia
    public void imprimirListaAdyacencia() {
        for (int i = 0; i < V; i++) {
            System.out.print((char) ('A' + i) + ": ");
            System.out.println(listaAdj.get(i));
        }
    }

    public static void main(String[] args) {
        int[][] matriz = {
                {0, 1, 0, 0, 1, 0, 1, 0, 0, 0}, // A
                {1, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // B
                {0, 1, 0, 0, 1, 0, 0, 1, 1, 0}, // C
                {0, 1, 0, 0, 0, 0, 0, 1, 1, 0}, // D
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, // E
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, // F
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // G
                {0, 0, 1, 1, 0, 1, 0, 0, 1, 1}, // H
                {0, 0, 1, 1, 0, 1, 0, 1, 0, 0}, // I
                {0, 0, 0, 0, 1, 0, 1, 1, 0, 0}  // J
        };

        Grafo grafo = new Grafo(matriz);

        System.out.println("Lista de Adyacencia:");
        grafo.imprimirListaAdyacencia();

        System.out.println("\nRecorrido BFS:");
        List<Integer> caminoBFS = grafo.bfs(0);
        System.out.println(caminoBFS);

        System.out.println("\nRecorrido DFS:");
        List<Integer> caminoDFS = grafo.dfs(0);
        System.out.println(caminoDFS);
    }
}
