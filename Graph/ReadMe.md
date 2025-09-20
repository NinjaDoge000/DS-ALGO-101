
### Adjacency Matrix

```{java}

import java.util.*;

public class GraphMatrix {
    private int[][] adjMatrix;
    private int vertices;

    public GraphMatrix(int vertices) {
        this.vertices = vertices;
        adjMatrix = new int[vertices][vertices];
    }

    // Add edge (for undirected, add both ways)
    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;  // or weight
        adjMatrix[v][u] = 1;  // comment this if directed graph
    }

    // Print matrix
    public void printGraph() {
        System.out.println(Arrays.deepToString(adjMatrix));
    }

    public static void main(String[] args) {
        GraphMatrix g = new GraphMatrix(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.printGraph();
    }
}

```



### Adjacency List

```{java}
import java.util.*;

public class GraphList {
    private List<List<Integer>> adjList;

    public GraphList(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add edge (for undirected, add both ways)
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u); // comment this if directed graph
    }

    // Print list
    public void printGraph() {
        System.out.println(adjList);
    }

    public static void main(String[] args) {
        GraphList g = new GraphList(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        g.printGraph();
    }
}

```