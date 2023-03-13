package frequency;

import edu.princeton.cs.introcs.StdOut;

import java.util.*;

public class SortFrequency {
    static class Frequency {
        int freq;
        String word;

        @Override
        public String toString() {
            return word + " - " + freq;
        }

        public Frequency(int freq, String word) {
            this.freq = freq;
            this.word = word;
        }
    }

    private static void sortFrequency(String [] str, int size){
        List<Frequency> records = new ArrayList<>();
        List<String> checker = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            String word = str[i];
            int freq = 0;

            if (!checker.contains(word)) {
                for (int j = i; j < size; j++) {
                    if (str[j].equals(word))
                        freq++;
                }
                records.add(new Frequency(freq, word));

                checker.add(word);
            }
        }

        records.sort(Comparator.comparingInt(o -> o.freq));

        StdOut.println("Слова, отсортированные по частоте: ");
        for (int i = records.size() - 1; i >= 0; i--) {
            StdOut.println(records.get(i));
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        StdOut.println("Введите размер массива: ");
        int size = input.nextInt();
        String[] str = new String[size];

        StdOut.println("Введите слова: ");
        input.nextLine();
        for (int i = 0; i < size; i++)
            str[i] = input.nextLine();

        sortFrequency(str,size);
    }
}