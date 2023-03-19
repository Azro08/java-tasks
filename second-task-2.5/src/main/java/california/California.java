package california;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Scanner;

import edu.princeton.cs.introcs.StdOut;

public class California {
    public static final Comparator<String> CANDIDATE_ORDER = new CandidateComparator();

    private static class CandidateComparator implements Comparator<String> {

        @Override
        public int compare(String a, String b) {
            if (Objects.equals(a, b))
                return 0;
            int minName = Math.min(a.length(), b.length());
            for (int i = 0; i < minName; i++) {
                String order = "RWQOJMVAHBSGZXNTCIEKUPDYFL";
                int aindex = order.indexOf(a.charAt(i));
                int bindex = order.indexOf(b.charAt(i));
                if (aindex < bindex)
                    return -1;
                else if (aindex > bindex)
                    return +1;
            }
            return a.length() - b.length();
        }
    }

    // test client
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int amount;
        StdOut.printf("how many names to enter?");
        amount = input.nextInt();
        String[] candidates = new String[amount];
        String candidateName;
        input.nextLine();
        for (int i =0; i < amount; i++) {
            StdOut.printf("name: ");
            candidateName = input.nextLine();
            candidates[i] = candidateName.toUpperCase();
        }

        Arrays.sort(candidates, California.CANDIDATE_ORDER);
        for (String candidate : candidates) {
            String name = candidate.toLowerCase();
            StdOut.println(name.substring(0, 1).toUpperCase() + name.substring(1));
        }
    }

}