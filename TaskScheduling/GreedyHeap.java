package TaskScheduling;

class GreedyHeap {
    public int[] getOrder(int[][] tasks) {
        
        int n = tasks.length;
        int[][] work = new int[n][3];

        for(int i = 0; i < n; i++) {
            work[i][0] = tasks[i][0]; // enqueue time
            work[i][1] = tasks[i][1]; // processing time
            work[i][2] = i;           // original index
        }

        Arrays.sort(work, (a, b) -> Integer.compare(a[0], b[0]));
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> {
                if (a[1] != b[1]) return Integer.compare(a[1], b[1]); // processing time
                return Integer.compare(a[2], b[2]);                   // index tie-break
            }
        );

        int currTime = 0;
        int res[] = new int[tasks.length];

        int index = 0;
        int resIndex = 0;

        while(index < n || !pq.isEmpty()) {

            if(index < n && pq.isEmpty() && currTime < work[index][0] ) {
                currTime = work[index][0];
            }

            while(index < n && currTime >= work[index][0]) {
                pq.add(work[index]);
                index++;
            }

            if(!pq.isEmpty()) {
                int currWork[] = pq.remove();
                res[resIndex] = currWork[2];
                resIndex++;
                currTime += currWork[1];
            }
        }

        return res;
    }
}

