class ListNode {

    int key, value;
    ListNode prev, next;

    ListNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {

    Map<Integer, ListNode> map;
    int capacity;
    ListNode head, tail;

    public LRUCache(int capacity) {
        
        map = new HashMap<>();
        this.capacity = capacity;
        head = new ListNode(0, 0);
        tail = new ListNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        
        if(!map.containsKey(key)) return -1;

        ListNode node = map.get(key);
        remove(node);
        insertToHead(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        
        if(map.containsKey(key)) {
            remove(map.get(key));
        }

        else if(map.size() == capacity) {
            remove(tail.prev);
        }

        insertToHead(new ListNode(key, value));
    }

    public void remove(ListNode node) {

        node.prev.next = node.next;
        node.next.prev = node.prev;
        map.remove(node.key);
        node = null;

    }

    public void insertToHead(ListNode node) {


        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        map.put(node.key, node);

    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */