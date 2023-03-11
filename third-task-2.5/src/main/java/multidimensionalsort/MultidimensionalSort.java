package multidimensionalsort;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class MultidimensionalSort {
    private static class VectorSort implements Comparator<int[]> {
        int dimension;

        public VectorSort(int dimension) {
            this.dimension = dimension;
        }

        public int compare(int[] a, int[] b) {
            return Integer.compare(a[dimension], b[dimension]);
        }
    }

    public static void sortByDimension(int[][] vectors, int currentDimension, int maxDimension, int leftBound, int rightBound)
    {
        VectorSort comparator = new VectorSort(currentDimension);
        sort(vectors, comparator, leftBound, rightBound);

        if (currentDimension == maxDimension) {
            return;
        }

        int leftBoundNew = leftBound;
        int rightBoundNew = -1;
        boolean hasDifferentValues = false;

        for (int i = leftBound; i < rightBound - 1; i++) {
            if (vectors[i][currentDimension] != vectors[i + 1][currentDimension]) {
                hasDifferentValues = true;
                rightBoundNew = i + 1;
                sortByDimension(vectors, currentDimension + 1, maxDimension, leftBoundNew, rightBoundNew);
                leftBoundNew = i + 1;
            }
        }

        if (!hasDifferentValues) {
            sortByDimension(vectors, currentDimension + 1, maxDimension, leftBound, rightBound);
        }
        if (rightBoundNew != -1) {
            sortByDimension(vectors, currentDimension + 1, maxDimension, rightBoundNew, rightBound);
        }
    }

    public static void sort(int[][] array, Comparator<int[]> comparator, int low, int high) {
        for (int i = low + 1; i < high; i++) {
            for (int j = i; j > low && isLess(array[j], array[j - 1], comparator); j--) {
                exchange(array, j, j - 1);
            }
        }
    }

    // Is value1 < value2 ?
    private static boolean isLess(int[] value1, int[] value2, Comparator<int[]> comparator) {
        return comparator.compare(value1, value2) < 0;
    }

    // Exchange array[i] and array[j]
    private static void exchange(Object[] array, int i, int j) {
        Object aux = array[i];
        array[i] = array[j];
        array[j] = aux;
    }

    private static void printArray(int[][] vectors){
        for (int[] vector : vectors) {
            for (int i : vector) {
                StdOut.printf(i + "    ");
            }
            StdOut.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new SecureRandom();
        StdOut.println("Enter array dimensions [n][m]");
        int n;
        int m;
        int choice;
        StdOut.printf("n = ");
        n = input.nextInt();
        StdOut.printf("m = ");
        m = input.nextInt();
        int[][] vectors = new int[n][m];
        StdOut.println("1. Enter values manually \n2. Enter values randomly");
        choice = input.nextInt();
        while (choice != 1 && choice != 2){
            StdOut.println("Wrong input, try again");
            choice = input.nextInt();
        }

        if (choice == 1){
            for (int i = 0; i < vectors.length; i++) {
                for (int j = 0; j < vectors[i].length; j++) {
                    StdOut.println("vectors[" + i + "][" + j + "] = ");
                    vectors[i][j] = input.nextInt();
                }
            }
        } else {
            for (int i = 0; i < vectors.length; i++) {
                for (int j = 0; j < vectors[i].length; j++) {
                    vectors[i][j] = random.nextInt(9);
                }
            }
        }

        StdOut.println("Before");
        printArray(vectors);

        sortByDimension(vectors, 0, vectors[0].length - 1, 0, vectors.length);

        StdOut.println("\nAfter\n");
        printArray(vectors);

    }
}