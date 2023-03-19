package binarytree;

import edu.princeton.cs.introcs.StdOut;

public class BinaryTree<T extends Comparable<T>> {
    Node<T> root;

    public void add(T val) {
        root = addRecursive(root, val);
    }

    private Node<T> addRecursive(Node<T> current, T val) {
        if (current == null) {
            return new Node<>(val);
        }

        int compareResult = val.compareTo(current.value);
        if (compareResult == 0) {
            current.left = addRecursive(current.left, val);
        } else if (compareResult < 0) {
            current.left = addRecursive(current.left, val);
        } else {
            current.right = addRecursive(current.right, val);
        }

        return current;
    }
    public boolean hasNoDuplicates(Node<T> node) {
        if (node == null) {
            return true; // базовый случай — если узла нет, то значит, все хорошо
        }

        if (hasDuplicate(node.left, node.value) || hasDuplicate(node.right, node.value)) {
            return false; // если нашли два узла с одинаковым ключом, то возвращаем false
        }

        // проверяем левое и правое поддеревья на отсутствие дубликатов
        return hasNoDuplicates(node.left) && hasNoDuplicates(node.right);
    }

    // вспомогательный метод для поиска дубликатов в поддереве
    private boolean hasDuplicate(Node<T> node, T value) {
        if (node == null) {
            return false; // нет дубликатов
        }

        if (node.value == value) {
            return true; // нашли дубликат
        }

        return hasDuplicate(node.left, value) || hasDuplicate(node.right, value);
    }

    public void printTree(Node<T> node) {
        if (node != null) {
            printTree(node.left);
            StdOut.print(node.value + " ");
            printTree(node.right);
        }
    }

}