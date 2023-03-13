package binarytree;

public class BinarySearchTree<Value extends Comparable<Value>> {
    private Node root;

    private class Node {
        private final Value value;

        private Node left, right;
        private int size;
        public Node(Value value, int size) {
            this.value = value;
            this.size = size;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    public double avgCompares() {
        return avgCompares(root);
    }

    private double avgCompares(Node node) {
        if (node == null) return 0;
        return 1 + (double) size(node.left) / size(node) * avgCompares(node.left)
                + (double) size(node.right) / size(node) * avgCompares(node.right);
    }

    public void put(Value value) {
        root = put(root, value);
    }

    private Node put(Node node, Value value) {
        if (node == null) return new Node(value, 1);
        int compare = value.compareTo(node.value);
        if (compare < 0) node.left  = put(node.left, value);
        else if (compare > 0) node.right = put(node.right, value);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
}