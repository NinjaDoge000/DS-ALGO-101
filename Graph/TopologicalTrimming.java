package Graph;

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        if(n == 1) return new ArrayList<>(List.of(0));

        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < n; i++) adjList.add(new ArrayList<>());

        for(int edge[] : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        List<Integer> leaves = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            if(adjList.get(i).size() == 1) leaves.add(i);
        }

        int remainingEdges = n;

        while(remainingEdges > 2) {
            
            remainingEdges -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for(Integer leaf : leaves) {
                
                // remove
                Integer nei = adjList.get(leaf).iterator().next();
                adjList.get(nei).remove(leaf);
                adjList.get(leaf).clear();

                if(adjList.get(nei).size() == 1) {
                    newLeaves.add(nei);
                }
            }

            leaves = newLeaves;
        }

        return leaves;

    }
}