package sortstability;

import edu.princeton.cs.introcs.StdOut;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class SortStability {
    private static class Wrapper implements Comparable<Wrapper> {

        Comparable<Integer> keyValue;
        int originalIndex;

        Wrapper(Comparable<Integer> keyValue, int originalIndex) {
            this.keyValue = keyValue;
            this.originalIndex = originalIndex;
        }

        @Override
        public int compareTo(Wrapper that) {
            int compare = this.keyValue.compareTo((Integer) that.keyValue);

            //Different keys
            if (compare != 0) {
                return compare;
            }

            //Equal keys
            return Integer.compare(this.originalIndex, that.originalIndex);
        }
        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random random = new SecureRandom();

        int size;
        StdOut.println("Enter array size");
        size = input.nextInt();

        Comparable[] array = new Comparable[size];

        int choice;
        StdOut.println("1. Enter values manually \n2. Enter values randomly\n");
        choice = input.nextInt();
        while (choice != 1 && choice != 2){
            StdOut.println("Wrong input, try again");
            choice = input.nextInt();
        }

        if (choice == 1){
            for (int i = 0; i < size; i ++)
                array[i] = input.nextInt();
        } else {
            for (int i = 0; i < size; i ++)
                array[i] = random.nextInt(9);
        }


        String originalArray = Arrays.toString(array);
        StdOut.println("Original array: " + originalArray);

        new SortStability().sortInAStableWay(array);

        String sortedArray = Arrays.toString(array);
        StdOut.println("Sorted array: " + sortedArray);
    }

    private void sortInAStableWay(Comparable<Integer>[] array) {

        Wrapper[] wrappedKeys = new Wrapper[array.length];
        int wrappedKeysIndex = 0;
        for (int i = 0; i < array.length; i++) {
            Wrapper wrapper = new Wrapper(array[i], i);
            wrappedKeys[wrappedKeysIndex++] = wrapper;
        }

        Arrays.sort(wrappedKeys);

        for (int i = 0; i < array.length; i++) {
            array[i] = wrappedKeys[i].keyValue;
        }
    }
}