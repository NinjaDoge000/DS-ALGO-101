package TaskScheduling;

class Cooldown {
    public int leastInterval(char[] tasks, int n) {
        

        int[] freq = new int[26];


        for(char c : tasks) {
            freq[c - 'A']++;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) ->  Integer.compare(b[0], a[0])
        );

        Queue<int[]> q = new LinkedList<>();

        for(int f : freq) {
            if(f != 0)
                pq.add(new int[]{f, 0});
        }

        int interval = 0;
    
        while(!pq.isEmpty() || !q.isEmpty()) {

            interval++;

            // remove stale elements from cooldown
            while(!q.isEmpty() && q.peek()[1] <= interval) {
                pq.add(q.remove());
            }

            if(pq.isEmpty()) {
                // jump to the next interval
                interval = q.peek()[1] - 1;
                continue;
            }

            int elem[] = pq.remove();

            elem[0]--;
            elem[1] = interval + n + 1;

            if(elem[0] != 0)
                q.add(elem);

        }

        return interval;

    }
}