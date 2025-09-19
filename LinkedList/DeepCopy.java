/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class DeepCopy {
    public Node copyRandomList(Node head) {
        
        Map<Node, Node> map = new HashMap<>();

        if(head == null) return null;

        Node node = head;
        
        while(node != null) {
            Node dupNode = new Node(node.val);
            map.put(node, dupNode);
            node = node.next;
        }

        node = head;
        while(node != null) {
            Node currNode = map.get(node);
            currNode.next = map.get(node.next);
            currNode.random = map.get(node.random);
            node = node.next;
        }

        return map.get(head);
    }
}