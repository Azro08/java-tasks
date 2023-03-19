package binarytree;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;

    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null) return 0;
        else return node.size;
    }

    public double avgCompares() {
        return avgCompares(root);
    }

    private double avgCompares(Node<T> node) {
        if (node == null) return 0;
        return 1 + (double) size(node.left) / size(node) * avgCompares(node.left)
                + (double) size(node.right) / size(node) * avgCompares(node.right);
    }

    public void put(T value) {
        root = put(root, value);
    }

    private Node<T> put(Node<T> node, T value) {
        if (node == null) return new Node<>(value, 1);
        int compare;
        compare = value.compareTo(node.value);
        if (compare < 0) node.left  = put(node.left, value);
        else if (compare > 0) node.right = put(node.right, value);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }
}