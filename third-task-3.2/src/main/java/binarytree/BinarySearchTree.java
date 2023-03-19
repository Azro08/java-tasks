package binarytree;

import edu.princeton.cs.introcs.StdOut;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;
        public Node(T value) {
            this.value = value;
        }
    }

    public void add(T value) {
        root = add(root, value);
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) return new Node<>(value);
        int compare = value.compareTo(node.value);
        if (compare < 0) node.left  = add(node.left, value);
        else if (compare > 0) node.right = add(node.right, value);
        return node;
    }
    public boolean isOrdered(Node<Integer> node, int minValue, int maxValue) {
        if (node == null) {
            return true; // пустое поддерево является упорядоченным
        }
        int value = node.value;
        if (value < minValue || value > maxValue) {
            return false; // текущий узел находится вне диапазона [minValue, maxValue]
        }
        boolean leftOrdered = isOrdered(node.left, minValue, value - 1);
        boolean rightOrdered = isOrdered(node.right, value + 1, maxValue);
        return leftOrdered && rightOrdered; // поддеревья также должны быть упорядочены
    }

    public int minValue(Node<Integer> node) {
        if (node.left == null) {
            return node.value;
        }
        return minValue(node.left);
    }

    public int maxValue(Node<Integer> node) {
        if (node.right == null) {
            return node.value;
        }
        return maxValue(node.right);
    }

    public void printTree(Node<T> node) {
        if (node != null) {
            printTree(node.left);
            StdOut.print(node.value + " ");
            printTree(node.right);
        }
    }

}