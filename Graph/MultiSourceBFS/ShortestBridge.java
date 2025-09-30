package Graph.MultiSourceBFS;

class Solution {
    public int shortestBridge(int[][] grid) {
        
        int r = grid.length, c = grid[0].length;

        Queue<int[]> q = new LinkedList<>();

        int[][] vis = new int[r][c];

        for(int i = 0; i < r; i++) {
            boolean found = false;
            for(int j = 0; j < c; j++) {

                if(grid[i][j] == 1) {
                    q.add(new int[]{i, j});
                    vis[i][j] = 1;
                    found = true;
                    break;
                }
            }
            if(found) break;
        }

        Set<Integer> boundaries = new HashSet<>();

        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

        while(!q.isEmpty()) {

            int[] cell = q.remove();
            int i = cell[0], j = cell[1];
            
            for(int[] dir : dirs) {
                int x = i + dir[0], y = j + dir[1];
  
                if((x >= 0 && x < r && y >= 0 && y < c)) {
                    if(grid[x][y] == 0) boundaries.add(i * c + j);

                    else if(vis[x][y] == 0){
                        q.add(new int[]{x, y});
                        vis[x][y] = 1;
                    }
                }
            }

        }

        // do a multi-source bfs;

        Queue<int[]> queue = new LinkedList<>();
        for(int cell : boundaries) queue.add(new int[]{cell / c, cell % c});
        
        

        int flips = 0;
        while(!queue.isEmpty()) {

            int len = queue.size();
            for(int[] cell : queue) {
                System.out.print(Arrays.toString(cell));
            }
            System.out.println();

            for(int i = 0; i < len; i++) {
                
                int x = queue.peek()[0];
                int y = queue.peek()[1];
                queue.remove();

                for(int[] dir : dirs) {
                    int nx = x + dir[0], ny = y + dir[1];
                    if((nx >= 0 && nx < r && ny >= 0 && ny < c)) {

                        if(vis[nx][ny] == 1) continue;
                        
                        if(grid[nx][ny] == 1) return flips;
                        
                        queue.add(new int[]{nx, ny});
                        vis[nx][ny] = 1;
                    
                        
                    }
                }
            }

            flips++;
        }

        return flips;
    }
}