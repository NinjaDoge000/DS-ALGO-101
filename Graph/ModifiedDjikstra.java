package Graph;

class Node {

    int x; int y;
    int diff;

    public Node(int x, int y, int diff) {
        this.x = x;
        this.y = y;
        this.diff = diff;
    }

}
class ModifiedDjkistra {

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int minimumEffortPath(int[][] heights) {
        
        int row = heights.length, col = heights[0].length;
        int[][] cost = new int[row][col];

        for(int arr[] : cost) Arrays.fill(arr, Integer.MAX_VALUE);
        cost[0][0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.diff, b.diff));

        pq.add(new Node(0, 0, 0));

        while(!pq.isEmpty()) {
            
            Node node = pq.remove();

            // The pq always pops the node with the smallest current effort.
            // Any other path that could lead to (x,y) later will have equal or larger effort.
            if(node.x == row - 1 && node.y == col - 1) {
                break;
            }

            for(int[] dir : dirs) {
                int x = node.x + dir[0];
                int y = node.y + dir[1];

                if( x >= 0 && y >= 0 && x < row && y < col) {
                    int currDiff = Math.max(node.diff, Math.abs(heights[x][y] - heights[node.x][node.y]));
                    if(cost[x][y] > currDiff) {
                        cost[x][y] = currDiff;
                        pq.add(new Node(x, y, currDiff));
                    }
                }
            }
        }
            
        return cost[row - 1][col - 1];
    }
}