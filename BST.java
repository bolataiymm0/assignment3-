import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST<K, V>.Node> {
    private Node root;
    private int size = 0;

    public class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
        public K getKey() { return key; }
        public V getValue() { return val; }
    }

    public void put(K key, V val) {
        if (root == null) {
            root = new Node(key, val);
            size++;
            return;
        }
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int cmp = key.compareTo(current.key);
            if (cmp < 0) current = current.left;
            else if (cmp > 0) current = current.right;
            else {
                current.val = val;
                return;
            }
        }
        size++;
        if (key.compareTo(parent.key) < 0) parent.left = new Node(key, val);
        else parent.right = new Node(key, val);
    }

    public void delete(K key) {
        Node current = root, parent = null;
        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) current = current.left;
            else current = current.right;
        }
        if (current == null) return;
        if (current.left == null || current.right == null) {
            Node temp = (current.left == null) ? current.right : current.left;
            if (parent == null) root = temp;
            else if (parent.left == current) parent.left = temp;
            else parent.right = temp;
            size--;
        } else {
            Node sP = current, s = current.right;
            while (s.left != null) { sP = s; s = s.left; }
            current.key = s.key; current.val = s.val;
            if (sP.left == s) sP.left = s.right;
            else sP.right = s.right;
            size--;
        }
    }

    public int size() { return size; }

    @Override
    public Iterator<Node> iterator() {
        return new Iterator<>() {
            private Stack<Node> stack = new Stack<>();
            private Node curr = root;
            { pushLeft(curr); }
            private void pushLeft(Node n) {
                while (n != null) { stack.push(n); n = n.left; }
            }
            public boolean hasNext() { return !stack.isEmpty(); }
            public Node next() {
                Node n = stack.pop();
                pushLeft(n.right);
                return n;
            }
        };
    }
}