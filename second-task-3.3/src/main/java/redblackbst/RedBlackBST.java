package redblackbst;

import edu.princeton.cs.introcs.StdOut;

public class RedBlackBST<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private Node lastAccessedNode;
    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    public Node getRoot(){
        return root;
    }

    public boolean contains(K key) {
        return get(root, key) != null;
    }
    public V get(Node node, K key) {
        if (lastAccessedNode != null && key.compareTo(lastAccessedNode.key) == 0) {
            return lastAccessedNode.value;
        }
        while (node != null) {
            int compare;
            compare = key.compareTo(node.key);
            if (compare < 0) node = node.left;
            else if (compare > 0) node = node.right;
            else {
                lastAccessedNode = node;
                return node.value;
            }
        }
        return null;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (value == null) {
            return;
        }
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            lastAccessedNode = new Node(key, value, RED);
            return lastAccessedNode;
        }
        int compare;
        compare = key.compareTo(node.key);
        compareForInput(node, key, value, compare);
        return node;
    }

    private void compareForInput(Node node, K key, V value, int compare) {
        if (compare == 0) {
            node.value = value;
            lastAccessedNode = node;
        } else if (compare < 0) {
            node.left = put(node.left, key, value);
            if (isRed(node.left) && isRed(node.left.left)) {
                node = rotateRight(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }
            lastAccessedNode = node.left;
        } else {
            node.right = put(node.right, key, value);
            if (isRed(node.right) && isRed(node.right.right)) {
                node = rotateLeft(node);
            }
            if (isRed(node.left) && isRed(node.right)) {
                flipColors(node);
            }
            lastAccessedNode = node.right;
        }
    }


    public V getLastAccessedNodeValue(){
        if (lastAccessedNode!=null) return lastAccessedNode.value;
        else {
            StdOut.println("No node was accessed!!");
            System.exit(0);
            return null;
        }
    }

    private Node rotateLeft(Node node) {
        Node rotateNode;
        rotateNode = node.right;
        node.right = rotateNode.left;
        rotateNode.left = node;
        rotateNode.color = node.color;
        node.color = RED;
        return rotateNode;
    }

    private Node rotateRight(Node node) {
        Node rotateNode;
        rotateNode = node.left;
        node.left = rotateNode.right;
        rotateNode.right = node;
        rotateNode.color = node.color;
        node.color = RED;
        return rotateNode;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node != null) {
            print(node.left);
            StdOut.print(node.key + " ");
            print(node.right);
        }
    }

}