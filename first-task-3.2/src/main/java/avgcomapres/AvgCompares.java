package avgcomapres;


import edu.princeton.cs.introcs.StdOut;

public class AvgCompares {

    private static class BinarySearchTree<KEY extends Comparable<KEY>, VALUE>{

        private class Node {
            private final KEY key;
            private VALUE value;

            private Node left;
            private Node right;

            private int size; //# of nodes in subtree rooted here
            private int totalNumberOfComparesRequired; //number of compares required to reach all nodes in the subtree rooted here

            public Node(KEY key, VALUE value, int size) {
                this.key = key;
                this.value = value;
                this.size = size;
            }
        }

        private Node root;

        public int size() {
            return size(root);
        }

        private int size(Node node) {
            if (node == null) {
                return 0;
            }
            return node.size;
        }

        public double avgComparesRecursive() {
            if (root == null) {
                return 0;
            }
            int internalPathLength = avgComparesRecursive(root);
            return internalPathLength / (double) size() + 1;
        }

        private int avgComparesRecursive(Node node) {
            if (node == null) {
                return 0;
            }
            return node.size - 1 +
                    avgComparesRecursive(node.left) +
                    avgComparesRecursive(node.right);
        }

        public double avgComparesConstant() {
            if (root == null) {
                return 0;
            }
            return totalNumberOfComparesRequired(root) / (double) size() + 1;
        }

        private int totalNumberOfComparesRequired(Node node) {
            if (node == null) {
                return 0;
            }
            return node.totalNumberOfComparesRequired;
        }

        public void put(KEY key, VALUE value) {
            root = put(root, key, value);
        }

        private Node put(Node node, KEY key, VALUE value) {
            if (node == null) {
                return new Node(key, value, 1);
            }

            int compare = key.compareTo(node.key);

            if (compare < 0) {
                node.left = put(node.left, key, value);
            } else if (compare > 0) {
                node.right = put(node.right, key, value);
            } else {
                node.value = value;
            }

            node.size = size(node.left) + 1 + size(node.right);
            node.totalNumberOfComparesRequired = node.size - 1 +
                    totalNumberOfComparesRequired(node.left) +
                    totalNumberOfComparesRequired(node.right);
            return node;
        }

        private Node min(Node node) {
            if (node.left == null) {
                return node;
            }
            return min(node.left);
        }

        public void deleteMin() {
            root = deleteMin(root);
        }

        private Node deleteMin(Node node) {
            if (node == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            node.left = deleteMin(node.left);

            node.size = size(node.left) + 1 + size(node.right);
            node.totalNumberOfComparesRequired = node.size - 1 +
                    totalNumberOfComparesRequired(node.left) +
                    totalNumberOfComparesRequired(node.right);
            return node;
        }

        public void deleteMax() {
            root = deleteMax(root);
        }

        private Node deleteMax(Node node) {
            if (node == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }

            node.right = deleteMax(node.right);

            node.size = size(node.left) + 1 + size(node.right);
            node.totalNumberOfComparesRequired = node.size - 1 +
                    totalNumberOfComparesRequired(node.left) +
                    totalNumberOfComparesRequired(node.right);
            return node;
        }

        public void delete(KEY key) {
            root = delete(root, key);
        }

        private Node delete(Node node, KEY key) {
            if (node == null) {
                return null;
            }

            int compare = key.compareTo(node.key);
            if (compare < 0) {
                node.left = delete(node.left, key);
            } else if  (compare > 0) {
                node.right = delete(node.right, key);
            } else {
                if (node.left == null) {
                    return node.right;
                } else if (node.right == null) {
                    return node.left;
                } else {
                    Node aux = node;
                    node = min(aux.right);
                    node.right = deleteMin(aux.right);
                    node.left = aux.left;
                }
            }

            node.size = size(node.left) + 1 + size(node.right);
            node.totalNumberOfComparesRequired = node.size - 1 +
                    totalNumberOfComparesRequired(node.left) +
                    totalNumberOfComparesRequired(node.right);
            return node;
        }

    }

    public static void main(String[] args) {

        testAvgComparesRecursive();
        testAvgComparesNonRecursive();
    }

    private static void testAvgComparesRecursive() {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree<>();

        StdOut.println("Recursive average number of compares method tests");
        StdOut.printf("AVG Compares 1: %.1f Expected: 0.0\n", binarySearchTree.avgComparesRecursive());

        binarySearchTree.put(0, 0);
        binarySearchTree.put(1, 1);
        binarySearchTree.put(2, 2);
        binarySearchTree.put(3, 3);

        StdOut.printf("AVG Compares 2: %.1f Expected: 2.5\n", binarySearchTree.avgComparesRecursive());

        binarySearchTree.put(-1, -1);
        binarySearchTree.put(-2, -2);

        StdOut.printf("AVG Compares 3: %.1f Expected: 2.5\n", binarySearchTree.avgComparesRecursive());

        binarySearchTree.put(-10, -10);
        binarySearchTree.put(-7, -7);

        StdOut.printf("AVG Compares 4: %.1f Expected: 3.0\n", binarySearchTree.avgComparesRecursive());

        binarySearchTree.delete(-7);
        StdOut.printf("AVG Compares 5: %.1f Expected: 2.7\n", binarySearchTree.avgComparesRecursive());

        binarySearchTree.deleteMin();
        binarySearchTree.deleteMax();
        StdOut.printf("AVG Compares 6: %.1f Expected: 2.2\n", binarySearchTree.avgComparesRecursive());
    }

    private static void testAvgComparesNonRecursive() {
        BinarySearchTree<Integer, Integer> binarySearchTree = new BinarySearchTree<>();

        StdOut.println("\nAdded-field average number of compares method tests");
        StdOut.printf("AVG Compares 1: %.1f Expected: 0.0\n", binarySearchTree.avgComparesConstant());

        binarySearchTree.put(0, 0);
        binarySearchTree.put(1, 1);
        binarySearchTree.put(2, 2);
        binarySearchTree.put(3, 3);

        StdOut.printf("AVG Compares 2: %.1f Expected: 2.5\n", binarySearchTree.avgComparesConstant());

        binarySearchTree.put(-1, -1);
        binarySearchTree.put(-2, -2);

        StdOut.printf("AVG Compares 3: %.1f Expected: 2.5\n", binarySearchTree.avgComparesConstant());

        binarySearchTree.put(-10, -10);
        binarySearchTree.put(-7, -7);

        StdOut.printf("AVG Compares 4: %.1f Expected: 3.0\n", binarySearchTree.avgComparesConstant());

        binarySearchTree.delete(-7);
        StdOut.printf("AVG Compares 5: %.1f Expected: 2.7\n", binarySearchTree.avgComparesConstant());

        binarySearchTree.deleteMin();
        binarySearchTree.deleteMax();
        StdOut.printf("AVG Compares 6: %.1f Expected: 2.2\n", binarySearchTree.avgComparesConstant());
    }
}