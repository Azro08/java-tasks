package task;

import edu.princeton.cs.introcs.StdOut;

public class BinarySearchTree<T extends Comparable<T>> {

    Node root;

    private class Node {
        T value;
        Node left;
        Node right;
        public Node(T value) {
            this.value = value;
        }
    }

    public void put(T value) {
        root = put(root, value);
    }

    private Node put(Node node, T value) {
        if (node == null) return new Node(value);
        int compare;
        compare = value.compareTo(node.value);
        if (compare < 0) node.left  = put(node.left, value);
        else if (compare > 0) node.right = put(node.right, value);
        return node;
    }

    public void draw() {
        drawTree(root, "", true);
    }

    private void drawTree(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }
        drawTree(node.right, prefix + (isTail ? "│   " : "    "), false);
        StdOut.println(prefix + (isTail ? "└── " : "┌── ") + node.value);
        drawTree(node.left, prefix + (isTail ? "    " : "│   "), true);
    }


    public int leftHeight() {
        if (root == null) {
            return -1;
        }
        return height(root.left);
    }

    public int rightHeight() {
        if (root == null) {
            return -1;
        }
        return height(root.right);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }


}