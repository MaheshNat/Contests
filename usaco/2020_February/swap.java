import java.util.*;
import java.io.*;

public class swap {

    private static int n, m, k;
    private static int[] cows;
    private static List<Pair> pairs;

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(new BufferedReader(new FileReader("swap.in")));
        // PrintWriter out = new PrintWriter(new FileWriter("swap.out"));
        Scanner in = new Scanner(System.in);

        n = in.nextInt();
        m = in.nextInt();
        k = in.nextInt();
        cows = new int[n];
        pairs = new ArrayList<Pair>();

        for (int i = 0; i < cows.length; i++)
            cows[i] = i + 1;

        for (int i = 0; i < m; i++)
            pairs.add(new Pair(in.nextInt(), in.nextInt()));

        while(k)
        for (int i = 0; i < k; i++)
            for (Pair p : pairs) {
                reverse(p.l, p.r);
                System.out.println(Arrays.toString(cows));
            }

        System.out.println(Arrays.toString(cows));

        // in.close();
        // out.close();
    }

    private static void reverse(int l, int r) {
        for (int i = 0; i <= (r - l) / 2; i++) {
            int t = cows[l - 1 + i];
            cows[l - 1 + i] = cows[r - 1 - i];
            cows[r - 1 - i] = t;
        }
    }

    private static class Pair {
        int l, r;

        Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }
}