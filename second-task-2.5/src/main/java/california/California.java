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
            int n = Math.min(a.length(), b.length());
            for (int i = 0; i < n; i++) {
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
        Scanner rl = new Scanner(System.in);
        int n;
        StdOut.printf("n = ");
        n = rl.nextInt();
        String[] candidates = new String[n];
        String candidateName;
        for (int i =0; i < n; i++) {
            StdOut.printf("name: ");
            candidateName = rl.next();
            candidates[i] = Arrays.toString(candidateName.toUpperCase().split("\\n+"));
        }

        Arrays.sort(candidates, California.CANDIDATE_ORDER);
        for (String candidate : candidates) StdOut.println(candidate);
    }

}