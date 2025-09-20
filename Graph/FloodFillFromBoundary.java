package Graph;

class FloodFillFromBoundary {

        boolean[][] pac;
        boolean[][] atl;
        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        public List<List<Integer>> pacificAtlantic(int[][] heights) {
        
        int row = heights.length, col = heights[0].length;

        pac = new boolean[row][col];
        atl = new boolean[row][col];

        Queue<int[]> pacQ = new LinkedList<>();
        Queue<int[]> atlQ = new LinkedList<>();


        for(int i = 0; i < col; i++) {

            pacQ.add(new int[]{0, i});
            pac[0][i] = true;

            atlQ.add(new int[]{row - 1, i});
            atl[row - 1][i] = true;
        }

        
        for(int i = 0; i < row; i++) {
            pacQ.add(new int[]{i, 0});
            pac[i][0] = true;

            atlQ.add(new int[]{i, col - 1});
            atl[i][col - 1] = true;
        }

        bfs(pac, pacQ, row, col, heights);
        bfs(atl, atlQ, row, col, heights);

        List<List<Integer>> res = new ArrayList<>();

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(pac[i][j] && atl[i][j]) {
                    res.add(new ArrayList<>(List.of(i, j)));
                }
            }
        }

        return res;
    }

    public void bfs(boolean[][] visited, Queue<int[]> q, int row, int col, int[][] heights) {


        while(!q.isEmpty()) {
            int[] coords = q.remove();

            for(int[] dir : dirs) {
                int x = coords[0] + dir[0];
                int y = coords[1] + dir[1];

                if(x >= 0 && y >= 0 && x < row && y < col && !visited[x][y] && 
                    heights[coords[0]][coords[1]] <= heights[x][y]) {
                        q.add(new int[]{x, y});
                        visited[x][y] = true;
                    }
            }
        }
    }
}