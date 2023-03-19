package binarysearchtree;

public class Node<T> {
    T value;
    Node<T> left;
    Node<T> right;
    int size;

    Node(T value) {
        this.value = value;
        this.size = 1;
    }
}