package binarytree;

public class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;
    int size;
    public Node(T value, int size) {
        this.value = value;
        this.size = size;
    }
}