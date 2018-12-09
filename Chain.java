
public class Chain<Key, Value> {
    
    private class Node {
        Key key;
        Value val;
        Node next;
        
        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
    
    private Node first;
    private int size;
    
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) 
            if (key.equals(x.key))
                return x.val;
        return null;
    }
    
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) // check if
            if (key.equals(x.key)) {                // already there
                x.val = val;
                return;
            }
        first = new Node(key,val,first);  // insert at beginning
        size++;
    }
    
    public int size() {
        return size;
    }
    
    public String toString() {
        String s = "";
        for (Node n = first; n != null; n = n.next) {
            s += n.key;
        }
        return s;
    }
}
