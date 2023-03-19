package binarysearchtree;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node<T> root;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        int size;
        public Node(T value, int size) {
            this.value = value;
            this.size = size;
        }
    }

    public void add(T value) {
        root = add(root, value);
    }

    private Node<T> add(Node<T> node, T value) {
        if (node == null) return new Node<>(value, 1);
        int compare = value.compareTo(node.value);
        if (compare < 0) node.left  = add(node.left, value);
        else if (compare > 0) node.right = add(node.right, value);
        node.size = 1 + size(node.left) + size(node.right);
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node<T> node) {
        if (node == null) return 0;
        else return node.size;
    }
    public int optCompares(int number) {
        /*
        В методе optCompares() сначала находим глубину идеально сбалансированного дерева,
         затем находим общее количество узлов и количество нулевых ссылок в дереве.
         Затем для каждого уровня дерева вычисляем количество узлов и количество нулевых ссылок на этом уровне
         и вычитаем количество единичных узлов (которых на уровне всегда один меньше, чем количество узлов),
         чтобы получить общее количество сравнений,
         необходимых для поиска ключа в этом уровне.
         Все эти количества сравнений затем суммируются и возвращаются в методе.
         */
        double v = Math.log(number + 1.0) / Math.log(2);
        return (int) v * number - (int) (Math.pow(2, (int) v)) + 1;
    }
}