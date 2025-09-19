package LinkedList;


class ListNode {

    ListNode next;
    ListNode prev;
    int val;

    ListNode(int val) {
        this.val = val;
    }
}

class CircularQueue {


    ListNode head, tail;
    int size;
    int currSize;

    public MyCircularQueue(int k) {
        head = new ListNode(0);
        tail = new ListNode(0);

        tail.next = head;
        head.prev = tail;
        head.next = tail;
        tail.prev = head;

        size = k;

    }
    
    public boolean enQueue(int value) {
        
        if(currSize == size) return false;

        ListNode node = new ListNode(value);

        node.next = tail;
        tail.prev.next = node;
        node.prev = tail.prev;
        tail.prev = node;

        currSize++;
        return true;

    }
    
    public boolean deQueue() {
        
        if(currSize == 0) return false;


        head.next = head.next.next;
        head.next.prev = head;

        currSize--;
        return true;

    }
    
    public int Front() {

        if(isEmpty()) return -1;
        return head.next.val;

    }
    
    public int Rear() {

        if(isEmpty()) return -1;
        return tail.prev.val;
    
    }
    
    public boolean isEmpty() {
        return (currSize == 0);
    }
    
    public boolean isFull() {
        return currSize == size;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */