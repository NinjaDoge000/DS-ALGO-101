
### Adjacency Matrix

```java

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

```java
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

### BFS - Flood Fill

```java

class Cell {

    int x, y;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

}

class Solution {

    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {

        int col = grid[0].length, row = grid.length;

        visited = new boolean[row][col];

        int maxArea = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++){
                
                if(grid[i][j] == 1) {
                    maxArea = Math.max(maxArea, bfs(grid, i, j, row, col));
                }
            }
        }

        return maxArea;
       
    }

    public int bfs(int[][] grid, int i, int j, int row, int col) {
        
        int area = 0;
        Queue<Cell> q = new LinkedList();

        q.add(new Cell(i, j));
        visited[i][j] = true;
        area++;

        while(!q.isEmpty()) {

            Cell cell = q.remove();

            for(int[] dir : dirs) {
                int x = cell.x + dir[0];
                int y = cell.y + dir[1];

                if(x >= 0 && x < row &&
                   y >= 0 && y < col &&
                   grid[x][y] == 1 && !visited[x][y]) {
                    
                    q.add(new Cell(x, y));
                    visited[x][y] = true;
                    area++;
                }
            }
        }
    
        return area;
    }
}
```